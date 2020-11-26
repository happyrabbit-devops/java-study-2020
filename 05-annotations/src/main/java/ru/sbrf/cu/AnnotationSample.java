package ru.sbrf.cu;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public static void main(String[] args) {

       // Задание на лекции:

       // 1. Создаем экземпляр класса WorkerClass

        WorkerClass objectWorker = instantiate( WorkerClass.class );

        // 2. Вызываем метод 1 с помощью рефлексии

        callMethod( objectWorker, "makeWork1" );

        // 3. Вызываем сеттер и метод 2 с помощью рефлексии

        callMethod( objectWorker, "setName", "work" );
        callMethod( objectWorker, "makeWork2");

        // 4. Поменяем значение напрямую и вызовем метод 3

        setFieldValue( objectWorker, "name", "code" );
        callMethod( objectWorker, "makeWork3" );


        // Запуск класса-теста:

        var runBefore = new ArrayList<>();
        var runTest = new ArrayList<>();
        var runAfter = new ArrayList<>();

        Class<TestClass> clazz = TestClass.class;

        Method[] methods = clazz.getMethods();
        for ( Method method: methods ) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for ( Annotation annotation: annotations ) {
                String annotationName = annotation.annotationType().getSimpleName();
                if ( annotationName.equals("Before") ) {
                    runBefore.add(method.getName());
                }
                if ( annotationName.equals("Test") ) {
                    runTest.add(method.getName());
                }
                if ( annotationName.equals("After") ) {
                    runAfter.add(method.getName());
                }
            }
        }

        System.out.println( "----------" );
        System.out.println( "FrameWork!" );
        System.out.println( "----------" );

        runTest.forEach(methodTest -> {
            AtomicReference<String> exceptionLog = new AtomicReference<>("");
            try {
                TestClass object = instantiate( TestClass.class);
                runBefore.forEach(methodBefore -> {
                    exceptionLog.set( "Before: " + methodBefore);
                    callMethod( object, (String)methodBefore );
                });
                exceptionLog.set( "Test: " + methodTest );
                callMethod(object, (String)methodTest);
                runAfter.forEach(methodAfter -> {
                    exceptionLog.set( "After: " + methodAfter );
                    callMethod( object, (String) methodAfter );
                });
            } catch ( Exception e ) {
                System.out.println("Ошибка при выполнении ("+exceptionLog+"): "+e.toString());
            } finally {
                System.out.println( "Тест " + methodTest + " завершен!" );
            }
        });

    }
}
