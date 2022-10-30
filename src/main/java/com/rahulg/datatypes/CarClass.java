package com.rahulg.datatypes;
// the default class modifier  is private which means this class
// in only accessible within this package and not outside.
class CarClass {

    String color; // the default private allows this to be accessible within package
    private String model; // this prevents access to model outside this class.

    CarClass(){}

    CarClass(String str) {
        color = str;
        model = "Hunda";
    }

    public void display() {
        System.out.println("Car color:" + this.color);
    }
}
