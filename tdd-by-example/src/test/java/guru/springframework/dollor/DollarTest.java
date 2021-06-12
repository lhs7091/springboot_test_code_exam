package guru.springframework.dollor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DollarTest {

    @Test
    void testMultiplication(){
        Dollar five = new Dollar(5);
        five.times(2);

        assertEquals(10, five.amount);
    }
}