package com.rahulg.inheritance;


class Car {
    int wheels;
    String model;
    void start() {
        // Check essential parts
    }
}

class ArmoredCar extends Car {
    int bulletProofWindows;

    void remoteStartCar() {
        // this vehicle can be started by using a remote control
    }
}


public class SimpleCarExample {
}
