package ru.sbrf.cu.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Реализация MyList должна")
class MyListTest {
    private MyList<Integer> list;

    @BeforeEach
    void beforeAll() {
        list = new MyLinkedList<>();
        list.add( 1 );
        list.add( 2 );
        list.add( 10 );
    }

    @DisplayName("добавлять элементы и корректно давать размер")
    @Test
    void testAdd() {
        assertEquals( 3, list.size() );
    }

    @DisplayName("находить элемент по индексу")
    @Test
    void testGet() {
        assertEquals( Integer.valueOf( 2 ), list.get( 1 ) );
    }

    @DisplayName("возвращать null если выходим за размер")
    @Test
    void testGetWithNull() {
        assertNull( list.get( 3 ) );
    }

    @DisplayName("находить удалять по элемент по совпадению")
    @Test
    void testRemoveFirst() {
        list.add( 2 );
        boolean result = list.remove( 2 );
        assertTrue( result );
        assertEquals( Integer.valueOf( 2 ), list.get( 10 ) );
        assertEquals( 3, list.size() );
    }

    @DisplayName("возвращать false если удаление по элементу не удалось")
    @Test
    void testRemoveFalse() {
        boolean result = list.remove( 3 );
        assertFalse( result );
        assertEquals( 3, list.size() );
    }

    @DisplayName("кооректно сортировать Integer")
    @Test
    void testSort() {
        list.add( 6 );
        list.add( 4 );
        // 1,2,10,6,4
        list.sort();

        assertEquals( 5, list.size() );
        assertEquals( Integer.valueOf( 1 ), list.get( 0 ) );
        assertEquals( Integer.valueOf( 2 ), list.get( 1 ) );
        assertEquals( Integer.valueOf( 4 ), list.get( 2 ) );
        assertEquals( Integer.valueOf( 6 ), list.get( 3 ) );
        assertEquals( Integer.valueOf( 10 ), list.get( 4 ) );
    }
}
