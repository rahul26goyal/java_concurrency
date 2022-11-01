package com.rahulg.exceptions;


import org.junit.jupiter.api.Test;

/*
A custom Exception class
 */
class SimpleCheckedException extends Exception{

    SimpleCheckedException(String errorMsg) {
        super(errorMsg); // in this scenario the original cause is lost.
        //We are consequently losing the root cause of the exception.
    }

    // we preserve the root cause of the exception
    SimpleCheckedException(String erroMsg, Throwable originalCause) {
        super(erroMsg, originalCause);
    }

}

public class SimpleCheckedExceptionExample {

    private void throwNPE() {
        throw new NullPointerException("Found Null value!");
    }

    @Test
    public void testCheckedException() throws SimpleCheckedException {
        try {
            throwNPE();
        }
        catch (Exception ex) { // catch and throw a custom exception.
            throw new SimpleCheckedException("An Exception happened!");
        }
    }

    @Test
    public void testCheckedExceptionWithCausePreservation() throws SimpleCheckedException {
        try {
            throwNPE();
        }
        catch (Exception ex) { // catch and throw a custom exception.
            throw new SimpleCheckedException("An Exception happened!", ex);
            // passing the ex preserves the root cause of the exception.
        }
    }
}
