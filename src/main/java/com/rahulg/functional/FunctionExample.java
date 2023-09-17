package com.rahulg.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionExample {


    @Test
    public  void testSimpleFunctionInterface() {

        Function<Integer, String> randomString = (length) -> {
            Random random = new Random();
            byte [] input = new byte[length];
            random.nextBytes(input);
            String s  = new String(input, StandardCharsets.US_ASCII);
            return s;
        };

        System.out.println("randomString.apply(10) = " + randomString.apply(10));

    }

    @Test
    public void testSimpleIdentifyFunction() {
        // creating it manually

        Function<Integer, Integer> identityFunction = (input) -> {return input;};

        Assertions.assertEquals(10, identityFunction.apply(10));

        Function<Integer, Integer> buildinIdentifyFunction = Function.identity();

        Assertions.assertEquals(20, buildinIdentifyFunction.apply(20));

        List<Integer> input = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        List<Integer> output = input.stream()
            .map(Function.identity())
            .collect(Collectors.toList());
    }

}
