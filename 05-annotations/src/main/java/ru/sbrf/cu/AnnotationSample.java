package ru.sbrf.cu;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class AnnotationSample {

    private static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void setFieldValue(Object object, String name, Object value) {
        try {
            var field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object callMethod(Object object, String name, Object... args) {
        try {
            var method = object.getClass().getDeclaredMethod(name, toClasses(args));
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static void main(String[] args) throws InterruptedException {

       // Задание на лекции:

       // 1. Создаем экземпляр класса WorkerClass

        WorkerClass objectWorker = instantiate(WorkerClass.class);

        // 2. Вызываем метод 1 с помощью рефлексии

        callMethod(objectWorker, "makeWork1");

        // 3. Вызываем сеттер и метод 2 с помощью рефлексии

        callMethod(objectWorker, "setName", "work");
        callMethod(objectWorker, "makeWork2");

        // 4. Поменяем значение напрямую и вызовем метод 3

        setFieldValue(objectWorker, "name", "code");
        callMethod(objectWorker, "makeWork3");


        // Запуск класса-теста:

        ArrayList<String> runBefore = new ArrayList<>();
        ArrayList<String> runTest = new ArrayList<>();
        ArrayList<String> runAfter = new ArrayList<>();

        Class<TestClass> clazz = TestClass.class;

        Method[] methods = clazz.getMethods();
        for (Method method: methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation: annotations) {
                String annotationName = annotation.annotationType().getSimpleName();
                if (annotationName.equals("Before")) {
                    runBefore.add(method.getName());
                }
                if (annotationName.equals("Test")) {
                    runTest.add(method.getName());
                }
                if (annotationName.equals("After")) {
                    runAfter.add(method.getName());
                }
            }
        }

        String exceptionLog = "";

        if (runTest.size() > 0) {
            for (String methodTest : runTest) {
                try {
                    TestClass object = instantiate(TestClass.class);
                    if (runBefore.size() > 0) {
                        for (String methodBefore : runBefore) {
                            exceptionLog = "Before: "+methodBefore;
                            callMethod(object, methodBefore);
                        }
                    }
                    exceptionLog = "Test: "+methodTest;
                    callMethod(object, methodTest);
                    if (runAfter.size() > 0) {
                        for (String methodAfter : runAfter) {
                            exceptionLog = "After: "+methodAfter;
                            callMethod(object, methodAfter);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка при выполнении ("+exceptionLog+"): "+e.toString());
                } finally {
                    Thread.sleep(300);
                }
            }
        }
    }
}
