package com.rahulg.testing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class SimpleJunit5Test {

    @Test
    public void test() {
        System.out.println("Working as expected...");
        Assertions.assertTrue(5 == 5);
        Assertions.assertEquals("Rahul", new String("Rahul"));
        Assertions.assertThrows(Exception.class, () -> {
            int a = 10;
            throw new Exception("Some Exception");
        });
    }

}
