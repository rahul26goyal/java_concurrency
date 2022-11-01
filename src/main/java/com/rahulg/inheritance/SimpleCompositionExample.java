package com.rahulg.inheritance;

import java.util.Optional;

class Computer {

    private Processor processor;
    private Memory memory;
    private SoundCard soundCard;

    // standard getters/setters/constructors

    public Optional<SoundCard> getSoundCard() {
        return Optional.ofNullable(soundCard);
    }
}

class StandardProcessor implements Processor {

    private String model;

    // standard getters/setters
}

interface Processor{}

class StandardMemory implements Memory {

    private String brand;
    private String size;

    // standard constructors, getters, toString
}

interface Memory{}


class StandardSoundCard implements SoundCard {

    private String brand;

    // standard constructors, getters, toString
}

interface SoundCard{}

public class SimpleCompositionExample {
}
