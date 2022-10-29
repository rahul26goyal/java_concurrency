package com.rahulg.basics;

//import javax.rmi.CORBA.Util;

public class MyThreadExample extends  Thread{

    public  MyThreadExample(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Executing MyThreadExample ...");
        //Utils.printThreadDetails(Thread.currentThread(), "MyThreadExample");
    }
}
