package ru.sbrf.cu.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Реализация MyList должна")
class MyListTest {
    private MyList<Integer> list;

    @BeforeEach
    public void beforeAll() {
        list = new MyLinkedList<>();
        list.sortedAdd( 1 );
        list.sortedAdd( 2 );
        list.sortedAdd( 10 );
    }

    @DisplayName("добавлять элементы и корректно давать размер")
    @Test
    public void testAdd() {
        list.print();
        assertEquals( 3, list.size() );
    }

    @DisplayName("находить элемент по индексу")
    @Test
    public void testGet() {
        assertEquals( Integer.valueOf( 2 ), list.get( 1 ) );
    }

    @DisplayName("возвращать null если выходим за размер")
    @Test
    public void testGetWithNull() {
        assertNull( list.get( 3 ) );
    }

    @DisplayName("находить удалять по элемент по совпадению")
    @Test
    public void testRemoveFirst() {
        list.sortedAdd( 2 );
        list.print();
        boolean result = list.remove( 2 );
        assertTrue( result );
        assertEquals( Integer.valueOf( 2 ), list.get( 1 ) );
        assertEquals( 3, list.size() );
        list.print();
    }

    @DisplayName("возвращать false если удаление по элементу не удалось")
    @Test
    public void testRemoveFalse() {
        boolean result = list.remove( 3 );
        assertFalse( result );
        assertEquals( 3, list.size() );
    }

    @DisplayName("кооректно сортировать Integer")
    @Test
    public void testSort() {
        list.sortedAdd( 6 );
        list.sortedAdd( 4 );
        // 1,2,10,6,4
        //list.sort();
        list.print();

        assertEquals( 5, list.size() );
        assertEquals( Integer.valueOf( 1 ), list.get( 0 ) );
        assertEquals( Integer.valueOf( 2 ), list.get( 1 ) );
        assertEquals( Integer.valueOf( 4 ), list.get( 2 ) );
        assertEquals( Integer.valueOf( 6 ), list.get( 3 ) );
        assertEquals( Integer.valueOf( 10 ), list.get( 4 ) );
    }

    @DisplayName("ошибка если индекс больше размера")
    @Test
    void testIndexException() {
        assertThrows( MyLinkedListIndexException.class, () ->
                list.get( 999 )
        );
    }
}
