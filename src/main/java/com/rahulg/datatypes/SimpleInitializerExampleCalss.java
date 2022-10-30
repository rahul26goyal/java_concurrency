package com.rahulg.datatypes;

public class SimpleInitializerExampleCalss {

    String name; // pckage-private scope.. can be accessed by classed within this package.
    private int id; // class scope.. can only be accessed within this class.
    // instance Initializer.
    {
        id = 100;  // default value for all object.
    }

    private static String forum; // class scope
    static {
        System.out.println("Initialize static code block..."); // this is executed when the class is loaded by JVM
        forum = "JAva";
    }


    public  static void main(String ...args) {
        SimpleInitializerExampleCalss s = new SimpleInitializerExampleCalss();
        System.out.println("ID===" + s.id);
        System.out.println("Forum===" + SimpleInitializerExampleCalss.forum);
    }
}
