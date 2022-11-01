package com.rahulg.testing;

//import org.junit.Test;
//import static  org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BasicTest {

    @Test
    public void checkString() {
        String message = "Hello, JUnit";
        assertEquals("Hello, JUnit", message);
    }
}
