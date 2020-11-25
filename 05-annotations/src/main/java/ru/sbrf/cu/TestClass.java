package ru.sbrf.cu;

public class TestClass {

    private int modify = 0;

    private int getModify() {
        return modify;
    }

    private void incModify() {
        this.modify++;
    }

    public TestClass() {
        System.out.println( "Объект: " + this.toString() + " Хэш: " + this.hashCode() + " Значение = " + getModify() );
    }


    @Before
    public void before() {
        incModify();
        System.out.println( "Вызван метод before. Значение = " + getModify() );
    }

    @Test
    public void test1() {
        incModify();
        System.out.println( "Вызван метод test1. Значение = " + getModify() );
    }

    @Test
    public void test2() {
        incModify();
        System.out.println( "Вызван метод test2. Значение = " + getModify() );
        throw new RuntimeException();
    }

    @Test
    public void test3() {
        incModify();
        System.out.println( "Вызван метод test3. Значение = " + getModify() );
    }

    @After
    public void after() {
        incModify();
        System.out.println( "Вызван метод after. Значение = " + getModify() );
    }

}
