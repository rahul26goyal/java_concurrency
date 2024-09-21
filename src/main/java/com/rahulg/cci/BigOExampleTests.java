package com.rahulg.cci;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BigOExampleTests {

    @BeforeEach
    public  void setUp() {
        System.out.println("HI");
    }

    @Test
    public void test() {
        Assertions.assertEquals("HI", "HI");
    }
}
