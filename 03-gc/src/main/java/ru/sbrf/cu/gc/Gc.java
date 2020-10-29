package ru.sbrf.cu.gc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static ru.sbrf.cu.gc.CalendarUtils.ConvertMilliSecondsToFormattedDate;

class Gc implements GcMBean {

  private final int counter;
  private volatile int size = 0;

  Gc(int counter) {
    this.counter = counter;
  }

  void run() throws InterruptedException {

    // Для вызова OutOfMemory
    Map<OOMObject, Integer> gcMap = new HashMap<>();
    //Map<NormalObject, Integer> gcMap = new HashMap<>();

    for (int i = 0; i < counter; i++) {

      /*
        HashMap не позволяет использовать дубликаты ключей. В случае неверно определенного метода equals()
        многочисленные объекты которые добавляются в качестве ключа будут накапливаться в HashMap и в конечном итоге приведут к OutOfMemory
     */

      // Benchmarking (очистка HashMap в каждом цикле)
       //Map<OOMObject, Integer> gcMap = new HashMap<>();
       //Map<NormalObject, Integer> gcMap = new HashMap<>();

      for (int j = 0; j < size; j++) {
        gcMap.put(new OOMObject("someField"), j);
        //gcMap.put(new NormalObject("someField"), j);
      }

      System.out.println(ConvertMilliSecondsToFormattedDate(System.currentTimeMillis())+
              ": Шаг "+i+". Добавлено "+size+" объектов");

      Thread.sleep(1000);

      gcMap.entrySet().removeIf(entry -> entry.getValue() < size / 2);

      System.out.println("Удалено "+(size/2)+" объектов. Размер HashMap: "+gcMap.size());

    }

  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public void setSize(int size) {
    System.out.println("new size:" + size);
    this.size = size;
  }

}
