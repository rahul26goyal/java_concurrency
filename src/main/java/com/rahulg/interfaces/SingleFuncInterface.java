package com.rahulg.interfaces;

// package scoped
interface SingleFuncInterface {

    void work();
}

interface EmptyInterface {
}

interface SimpleInterfaceWithVariables {
    // constants are allowed
    ////implicitly public, static and final
    String COLOR = "RED";
    // instance variable not allowed.
    // private int AGE=123;

    // Interfaces can’t have constructors because we can’t instantiate them a
    // and interfaces can’t have a method with body.
//    SimpleInterfaceWithVariables() {
//
//    }

    //void SimpleInterfaceWithVariables();

    //interface methods are implicitly abstract and public
    public String work();

    // can have public static methods.
    static String getColor(int someValue) {
        return "RandomString";
    }

    default void printDetails() {
        System.out.println("Some details...");
    }

    // implementation can not be changed by the implementing class.
    //https://www.baeldung.com/java-interface-private-methods
    private void somePrivateMethod() {
        System.out.println("Private method called..");
    }

    // we can define static provate method too.
}