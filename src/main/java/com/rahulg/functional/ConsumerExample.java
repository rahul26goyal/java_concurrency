package com.rahulg.functional;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class ConsumerExample {

    private Integer testRunCount;

    public ConsumerExample() {
        this.testRunCount = 0;
    }


    @Test
    public void testSimpleConsumer() {
        this.testRunCount += 1;
        AtomicInteger sum = new AtomicInteger();
        // int num = 10; conflicting variable num
        int count = 10;
        Consumer<Integer> printConsumer = (num) -> {
             // int count = 100; // this is also illegal
            for(int i=0;i<=num;i++) {
                System.out.println("i = " + i);
                sum.addAndGet(i);
            }
        };
        printConsumer.accept(10);
        System.out.println("printConsumer sum= " + sum);
    }

    @Test
    public void testAccessConsumer() {
        this.testRunCount += 1;
        Consumer<String> someConsumer = (str) -> {
            System.out.println(String.format("This is accessible: %d", this.testRunCount));
        };
        someConsumer.accept("test");
    }

    @Test
    public void testConsumerAndthen(){
        Consumer<Integer> first = (num) -> {
            System.out.println("Executing 1st task on input :: " + Integer.toString(num));
        };

        Consumer<String> second = (str) -> {
            System.out.println(String.format("Executing 2nd task: %s", str));
        };

        // first.andThen(second).accept(123); this will not work as the data type of seconds is not same.
        Consumer<Integer> third = (num2) -> {
            System.out.println(String.format("Executing 3nd task input: %s", num2));
        };

        Consumer<Integer> combined = first.andThen(third);

        second.accept("INSERT");

        // input to both the consumers will be same...
        combined.accept(123);
    }

    @Test
    public void testConsumerwithPrimitive() {

        // Consumer<int> test = (i) -> System.out.println(i);
        // argument can not be primitive data types

        IntConsumer consumer = (i) -> {
            System.out.println("i = " + i);
        };
        consumer.accept((int) 123);
        // DoubleConsumer
        // LongConsumer

    }


}
