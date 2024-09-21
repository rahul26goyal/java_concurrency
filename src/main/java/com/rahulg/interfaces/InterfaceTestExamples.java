package com.rahulg.interfaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://www.baeldung.com/java-interfaces
public class InterfaceTestExamples {

    @Test
    public void test_empty_interface() {
        //
        EmptyInterface empty = new EmptyInterface() {
        };
        // check if interface type
        System.out.println("Class: " + empty.getClass());
        Assertions.assertNotEquals(EmptyInterface.class, empty.getClass());
    }

    @Test
    public void test_single_interface() {
        // ananomous interface
        SingleFuncInterface singleFuncInterface = new SingleFuncInterface() {
            @Override
            public void work() {
                System.out.println("Working..");
            }
        };
        singleFuncInterface.work();
        System.out.println(singleFuncInterface.getClass().getName());
        Assertions.assertTrue(singleFuncInterface.getClass().getName().contains("com.rahulg.interfaces.InterfaceTestExamples"));
    }

    @Test
    public void test_SimpleInterfaceWithVariables() {

        SimpleInterfaceWithVariables interfaceInstance = new SimpleInterfaceWithVariables() {
            @Override
            public String work() {
                System.out.println("Working");
                test();
                test2();
                return "Completed";
            }

            @Override
            public void printDetails() {
                SimpleInterfaceWithVariables.super.printDetails();
                System.out.println("hello");
                test();
                test2();
            }
            // can define new methods.
            private void test() {
                System.out.println("new method called..");
            }

            // can not be invoked from outside as this is not part of the interface definition
            public String test2() {
                System.out.println("new method test2 called..");
                return "123";
            }
        };
        Assertions.assertEquals("Completed", interfaceInstance.work());
        Assertions.assertEquals("RED", interfaceInstance.COLOR);
        //Assertions.assertEquals("RandomString", SimpleInterfaceWithVariables::getColor(12));
        String color = SimpleInterfaceWithVariables.getColor(122);
        // interfaceInstance.getColor(12); can not be invoked.
        Assertions.assertEquals("RandomString", color);
        interfaceInstance.printDetails();

        //Assertions.assertEquals("123", interfaceInstance.test2());
    }
}
