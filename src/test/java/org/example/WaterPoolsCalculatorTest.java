package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WaterPoolsCalculatorTest {

    @Test
    void calculateWaterAmountTest() {
        var calculator = new WaterPoolsCalculator();
        assertEquals(7, calculator.calculateWaterAmount(new int[]{0, 1, 4, 2, 3, 1, 5, 1, 2}));
        assertEquals(9, calculator.calculateWaterAmount(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}));
        assertEquals(2, calculator.calculateWaterAmount(new int[]{1, 1, 0, 0, 1, 1}));
        assertEquals(2, calculator.calculateWaterAmount(new int[]{1, 1, 0, 0, 1, 1}));
        //
        assertEquals(0, calculator.calculateWaterAmount(new int[]{1, 1, 1, 1, 1, 1}));
        assertEquals(0, calculator.calculateWaterAmount(new int[]{0, 0, 0, 1, 0, 0}));
        assertEquals(0, calculator.calculateWaterAmount(new int[]{1, 2, 3, 3, 2, 1}));
        assertEquals(0, calculator.calculateWaterAmount(new int[]{3, 6, 8, 12}));
        assertEquals(0, calculator.calculateWaterAmount(new int[]{22, 14, 4, 0}));
    }

    @Test
    void calculateWaterAmountValidationTest() {
        var calculator = new WaterPoolsCalculator();
        {
            int[] ints = new int[32111];
            Arrays.fill(ints, 1);
            var exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculateWaterAmount(ints));
            assertEquals("Invalid number of landscape positions: 32111. Maximum is: 32000", exception.getMessage());
        }
        {
            var exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculateWaterAmount(new int[]{32838}));
            assertEquals("Invalid height in position 0: 32838. Maximum height is: 32000", exception.getMessage());
        }
        {
            int[] ints = new int[32111];
            Arrays.fill(ints, 100000);
            var exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculateWaterAmount(ints));
            assertEquals("Invalid number of landscape positions: 32111. Maximum is: 32000", exception.getMessage());
        }

    }
}