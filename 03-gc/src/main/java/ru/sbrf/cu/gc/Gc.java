package ru.sbrf.cu.gc;

import java.util.HashMap;
import java.util.Map;

class Gc implements GcMBean {

  private final int loopCounter;
  private volatile int size = 0;

  Gc(int loopCounter) {
    this.loopCounter = loopCounter;
  }

  void run() throws InterruptedException {

    // Для вызова OutOfMemory
    Map<OOMObject, Integer> gcMap = new HashMap<>();
    //Map<NormalObject, Integer> gcMap = new HashMap<>();

    for (int i = 0; i < loopCounter; i++) {

        /*
          HashMap не позволяет использовать дубликаты ключей. В случае неверно определенного метода equals()
          многочисленные объекты которые добавляются в качестве ключа будут накапливаться в HashMap и в конечном итоге приведут к OutOfMemory
       */

        // Benchmarking (очистка HashMap в каждом цикле)
        // Map<OOMObject, Integer> gcMap = new HashMap<>();
        // Map<NormalObject, Integer> gcMap = new HashMap<>();

        for (int j = 0; j < size; j++) {
          gcMap.put(new OOMObject("someField"), 1);
          //gcMap.put(new NormalObject("someField"), 1);
        }

      //System.out.println(gcMap.size());
      Thread.sleep(27500);
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
