package com.rahulg.datatypes;

public class PrimitiveDataTypes {

    // Primitive data types are always pass by Value.
    public static int modify(int x) {
        return x + 10;
    }


    public static void main(String[] args) {
        System.out.println("The eight primitives defined in Java are int, byte, short, long, float, double, boolean and char.");
        int i = 100;
        int j = -100;
        int n=-12 ;//Integer.parseInt(null);
        System.out.println(String.format("int i = %d, n = %d", i, n));
        byte b = 69; // 8 bits -128 to 127
        System.out.println("Byte b = " + b);
        System.out.println("Byte b = " + (char)b);

        char ch = 'A';
        System.out.println("Character ch = " + ch);
        System.out.println("Character ch = " + (int)ch);
        System.out.println("Character ch = " + (byte)ch);

        byte byteOverflow = (byte) 128; // 8 bits -128 to 127
        System.out.println("Byte byteOverflow = " + byteOverflow); // this will print -128
        byteOverflow = (byte) 129;
        System.out.println("Byte byteOverflow = " + byteOverflow); // this will print -127

        int intOverflow = Integer.MAX_VALUE + 1;
        System.out.println("Integer intOverflow = " + intOverflow); // negative equivalent

        int k = modify(i);
        System.out.println(String.format("Integer i = %d, k = %d", i, k));
    }
}
