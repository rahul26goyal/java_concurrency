package com.rahulg.exceptions;


import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SimpleChainedExceptions {

    private void initAndThrowChainedException() throws Throwable {
        Throwable chainedException =  new NullPointerException("Can not parse Null value")
            .initCause(new IOException("Failed to read file someone.txt"));
        throw chainedException;
    }



    @Test
    public void understandChainedException() {

        try {
            initAndThrowChainedException();
        }
        catch (Throwable throwable) {
            System.out.println("Caught : " + throwable);
            System.out.println("Actual cause: "+ throwable.getCause());
            // print the stace as we are not throwing it back.
            throwable.printStackTrace();
        }
    }

    private void multiLevelChainedException() throws Throwable {

        try {
            initAndThrowChainedException();
        }
        catch (Throwable throwable) {
            throw new ArrayIndexOutOfBoundsException("Multilevel exception")
                .initCause(throwable);
        }
    }

    @Test
    public void testMultilevelChainedExce() throws Throwable {

        try {
            multiLevelChainedException();
        }
        catch (Throwable throwable) {
            System.out.println("Caught : " + throwable);
            System.out.println("Actual cause: "+ throwable.getCause());
            // print the stace as we are not throwing it back.
            throwable.printStackTrace(); // this will show all the error messages that has hapened on the way.
            // or
            // throw new Throwable(throwable);
        }
    }

}
