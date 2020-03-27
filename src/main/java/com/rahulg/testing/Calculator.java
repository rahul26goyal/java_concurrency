package com.rahulg.testing;

public class Calculator {

    public  int add(int a, int b) {
        return a + b;
    }

    public  int sub(int a, int b) throws Exception {
        if(a < b) {
            throw new Exception("a should be greater than b");
        }
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public String message() {
        return "This is a Calculator!";
    }
}
