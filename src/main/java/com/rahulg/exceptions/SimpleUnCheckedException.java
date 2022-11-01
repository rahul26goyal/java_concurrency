package com.rahulg.exceptions;


import org.junit.jupiter.api.Test;

class IncorrectFileNameException extends RuntimeException {

    IncorrectFileNameException(String errmsg) {
        super(errmsg);
    }

    IncorrectFileNameException(String errmsg, Throwable cause) {
        super(errmsg, cause);
    }
}

public class SimpleUnCheckedException {

    private void throwRuntimeException() {
        throw new ArrayIndexOutOfBoundsException("Throwing runtime exception!!");
    }

    @Test
    public void testCheckedException() throws SimpleCheckedException {
        try {
            throwRuntimeException();
        }
        catch (RuntimeException ex) { // catch and throw a custom exception.
            throw new IncorrectFileNameException("An runtime Exception happened!");
        }
    }

    @Test
    public void testCheckedExceptionWithCausePreservation() throws SimpleCheckedException {
        try {
            throwRuntimeException();
        }
        catch (RuntimeException ex) { // catch and throw a custom exception.
            throw new IncorrectFileNameException("An runtime Exception happened!", ex);
            // passing the ex preserves the root cause of the exception.
        }
    }
}
