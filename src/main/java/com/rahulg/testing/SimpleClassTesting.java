package com.rahulg.testing;

import com.rahulg.datatypes.SimpleClass;
 //import com.rahulg.datatypes.CarClass; // this import will not work as
// 'com.rahulg.datatypes.CarClass' is not public in 'com.rahulg.datatypes'. Cannot be accessed from outside package

public class SimpleClassTesting {

    public static void main(String ...args) {

//        SimpleClass s1 = new SimpleClass("sa", "sa", "as");
//        java: no suitable constructor found for SimpleClass(java.lang.String,java.lang.String,java.lang.String)
//        constructor com.rahulg.datatypes.SimpleClass.SimpleClass() is not applicable
        SimpleClass s2 = new SimpleClass("sa", "sa", "as", 123);
        s2.display();
        //System.out.println("Car spedd: " + s2.color); // 'color' is not public in 'com.rahulg.datatypes.SimpleClass'. Cannot be accessed from outside package
        System.out.println("Car spedd: " + s2.speed); // public variable so can be access outisde package.


    }
}
