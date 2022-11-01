package com.rahulg.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

// https://www.baeldung.com/java-exceptions
class PlayerUtil {

    public static int getScore(int playerId) throws Exception, SimpleCheckeException {

        if (playerId <= 0) {
            // this is a checked exception which needs to be communicated
            // by adding throws in the function signature!
            throw new Exception("Player Not Found");
        }
        else if (playerId < 100) {
            return new Random().nextInt(10);
        }
        else if (playerId < 1000) {
            // this is a unchecked / runtime exception which does not need to be
            // commmunicated to caller
            throw new RuntimeException("The id is out of range!!");
        }
        else {
            // we may not necessarily need to add this exception to func sinature as we have base class
            // added already `Exception`
            throw new SimpleCheckeException(String.format("We got playerId as %s", playerId));
        }
    }

    public static void updateScope(int playerId) {

        if (playerId <= 0) {
            throw new IllegalArgumentException("Invalid PaylerId"); // this is an unchecked exception.
        }
    }

    public static void updateScope2(int playerId) {
        try {
            if (playerId <= 0) {
                throw new IllegalArgumentException("Invalid PaylerId"); // this is an unchecked exception.
            }
        } catch (Throwable t) { // we can catch the runtime exception and throw a new exception if required.
            throw t; // this does not require any change to method sig
            // throw new SimpleCheckeException("ddd"); // this will require us to add this checked exception to signature.
        }
    }
}

class SimpleAutoClosableClass implements AutoCloseable {

    int value; // package scope
    SimpleAutoClosableClass() {
        System.out.println("Creating the object for the SimpleAutoClosableClass class");
        value = 123;
    }

    public void display() {
        System.out.println("Do something here...");
        value = new Random().nextInt(100);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing this class automatically!!!");
        value = 0;
    }
}

class SimpleCheckeException extends Exception {

    SimpleCheckeException(String message) {
        super(message);
    }
}

public class SimpleExceptionHandling {

    @Test
    public void testUncheckedException() {
        try {
            PlayerUtil.updateScope(-1);
        }
        catch (IllegalArgumentException npe) {
            System.out.println("Caught NPE..");
        }
    }

    @Test
    public void testUncheckedExceptionHandled() {
        try {
            PlayerUtil.updateScope2(-1);
        }
        catch (IllegalArgumentException npe) {
            System.out.println("Caught NPE..");
        }
    }

    @Test
    public void testMixedExceptions() {
        Assertions.assertThrows(Exception.class, () -> {
           PlayerUtil.getScore(-1);
        });

        Assertions.assertDoesNotThrow(() -> {
            PlayerUtil.getScore(11);
        });

        Assertions.assertThrows(RuntimeException.class, () -> {
            PlayerUtil.getScore(111);
        });

        Assertions.assertThrows(SimpleCheckeException.class, () -> {
            PlayerUtil.getScore(1111);
        });
    }

    @Test
    public void testAutoCLosable() throws Exception{
        // this is similar to python Context
        SimpleAutoClosableClass objRef = new SimpleAutoClosableClass();
        try(SimpleAutoClosableClass obj = new SimpleAutoClosableClass()) {
            obj.display();
            objRef = obj;
            Assertions.assertNotEquals(0, obj.value);
        }
        finally {
            // since the close methiod was auto called, the value will be zero.
            Assertions.assertEquals(0, objRef.value);
            System.out.println("Done");
        }
    }
}

