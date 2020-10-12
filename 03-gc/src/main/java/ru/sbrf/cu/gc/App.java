package ru.sbrf.cu.gc;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class App {

    private static int actionCnt = 0; // Число событий GC
    private static int allDuration = 0; // Время сборок в миллисек.

    public static void main(String... args) throws Exception {

        /*
            -Xms512m
            -Xmx512m
            -Xlog:gc=debug:file=C:\Temp\gc-test.log:tags,uptime,time,level:filecount=5,filesize=10m
            -XX:+HeapDumpOnOutOfMemoryError
            -XX:HeapDumpPath=C:\Temp
            -XX:+UseG1GC
         */

        //UseSerialGC
        //UseParallelGC
        //UseConcMarkSweepGC
        //UseG1GC

        System.out.println("Новый процесс pid: " + ManagementFactory.getRuntimeMXBean().getName());
        switchOnMonitoring();
        long beginTime = System.currentTimeMillis();

        int size = 5 * 1000 * 100;
        int counter = 100;

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.sbrf:type=Gc");

        Gc mbean = new Gc(counter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(size);
        mbean.run();

        System.out.println("Общее время:" + (System.currentTimeMillis() - beginTime) / 1000 + " сек.");
        System.out.println("Количество сборок GC: " + actionCnt);
        System.out.println("Общее время сборок GC: " + allDuration / 1000 + " сек.");
    }

    private static void switchOnMonitoring() {

        List<GarbageCollectorMXBean> gcBeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcBean : gcBeans) {

            System.out.println("GC name:" + gcBean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");

                    actionCnt++;
                    allDuration += duration;
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }

    }
}
