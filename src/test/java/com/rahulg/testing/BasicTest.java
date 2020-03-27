package com.rahulg.testing;

import org.junit.Test;
import static  org.junit.Assert.assertEquals;

public class BasicTest {

    @Test
    public void checkString() {
        String message = "Hello, JUnit";
        assertEquals("Hello, JUnit", message);
    }
}
