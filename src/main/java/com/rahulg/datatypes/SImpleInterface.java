package com.rahulg.datatypes;

import java.util.Comparator;

interface ShapeInterface { // default scoped interface

    // interfaces can define constants.
    public final String type="Geometry";

    // private int sides; // private or protected modifier is not allowed.

    // interface can have static variables.
    public static int MAX_SIDES = 10;

    public String printColor();  // this is an abstract method.

    default String getType() {
        return type;
    }


    static boolean isGemoetryShape() {
        return type.equals("Geometry");
    }
}

class Shape {
    private int sides;

    Shape(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

}

class Circle extends Shape implements ShapeInterface {

    private String color;

    Circle(int sides, String color) {
        super(sides);
        this.color = color;
    }

    public String printColor() {  // must implement abstract class of interface
        System.out.println("share color:::");
        return color;
    }
}



class ShareComarator implements Comparator<Shape> {

    @Override
    public int compare(Shape s1, Shape s2) {
        if (s1.getSides() > s2.getSides()) {
            return 1;
        }
        else if (s1.getSides() < s2.getSides()) {
            return -1;
        }
        return 0;
    }
}

public class SImpleInterface {

    public static void main(String ...args) {
        Circle c1 = new Circle(1, "red");
        System.out.println(c1.printColor());
        System.out.println(c1.getSides());
        System.out.println(c1.getType());
    }

}
