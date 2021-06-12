package guru.springframework.dollor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DollarTest {

    @Test
    void testMultiplication(){
        Dollar five = new Dollar(5);

        Dollar product = five.times(2);
        assertEquals(new Dollar(10), product);

        product = five.times(3);
        assertEquals(new Dollar(15), product);
    }

    @Test
    void testEquality(){
        assertEquals(new Dollar(5), new Dollar(5));
        assertNotEquals(new Dollar(5), new Dollar(8));
    }
}