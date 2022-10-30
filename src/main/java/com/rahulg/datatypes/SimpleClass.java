package com.rahulg.datatypes;

public class SimpleClass {
    // fields
    String type;
    String model;
    String color;
    public int speed;

    // default constructor
    //This constructor simply initializes all fields of the object with their default values. Strings are initialized to null and integers to zero.
    SimpleClass(){
    }

    //args constructor with private modifier.
    // the default-package-private modifier allows access to the class from any other class in the same package.
    // one can not create an object for this class from a different package hirarchy.
    SimpleClass(String type, String model, String color) {
        this.type = type;
        this.model = model;
        this.color = color;
    }

    // constructor with package-public access modifier which would allow this class to be created from different package hirarchy.
    public SimpleClass(String type, String model, String color, int speed) {
        this.type = type;
        this.model = model;
        this.color = color;
    }

    public void display() {
        System.out.println(String.format("Car details: type: %s model %s color %s speed %d", type, model, color, speed));
    }
    // methods
    int increaseSpeed(int increment) {
        this.speed = this.speed + increment;
        return this.speed;
    }

    public static void main(String[] args) {

        SimpleClass s = new SimpleClass(); // default
        s.display();

        SimpleClass mycar = new SimpleClass("Sedan", "i20", "starry night"); // default
        mycar.display();
        System.out.println("Car spedd: " + mycar.speed); // this allowed as we are access from a static function within the same class.
        System.out.println("Car spedd: " + mycar.color); // this allowed as we are access from a static function within the same class.

        // Test another default class.
        CarClass c1 = new CarClass("REDDD");
        c1.display();
        System.out.println("Car color:" + c1.color);
        // System.out.println("Car Modex:" + c1.model); // cen not be accessed.
    }
}

