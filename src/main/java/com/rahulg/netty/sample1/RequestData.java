package com.rahulg.netty.sample1;

public class RequestData {

    private  int intValue;
    private String stringValue;

    public RequestData() {
        System.out.println("hi");
    }

    public RequestData(int val) {
        intValue = val;
    }

    public int calculate() {
        return 2 * intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
