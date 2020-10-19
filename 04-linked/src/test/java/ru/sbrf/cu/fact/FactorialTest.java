package ru.sbrf.cu.fact;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Проверяем факториал")
class FactorialTest {
    private Factorial factorial = new Factorial();
    // 0, 1, -5, 10, слишком большой

    @DisplayName("от 0 равен 1")
    @Test
    void testZero() {
        assertEquals( 1, factorial.calc( 0 ) );
    }

    @DisplayName("от 1 равен 1")
    @Test
    void testOne() {
        assertEquals( 1, factorial.calc( 1 ) );
    }

    @DisplayName("от 5 равен 120")
    @Test
    void testFive() {
        assertEquals( 120, factorial.calc( 5 ) );
    }

    @DisplayName("ошибка если отрицательное значение")
    @Test
    void testNegative() {
        assertThrows( FactorialNegativeException.class, () ->
                factorial.calc( -5 )
        );
    }

    @DisplayName("ошибка если результат не влезает в Integer")
    @Test
    void testTooBig() {
        assertThrows( FactorialException.class, () ->
                factorial.calc( 90 )
        );
    }
}
