package com.rahulg.testing;

//import org.junit.Test;
//import static  org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calc  = new Calculator();

    @Test
    public  void testAddition(){
        int result = calc.add(4,3);
        assertEquals(result, 7);
    }

    @Test
    public  void testSubtract() throws Exception {
        int result = calc.sub(4,3);
        assertEquals(result, 1);
    }

    //@Test(expected = Exception.class)
    public void testSubtractException() throws Exception {
        int result = calc.sub(0,3);
    }

    @Test
    public  void testMultiplication(){
        int result = calc.mul(4,3);
        assertEquals(result, 12);
    }
}
