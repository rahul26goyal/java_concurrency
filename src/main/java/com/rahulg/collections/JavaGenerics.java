package com.rahulg.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaGenerics {

    // simple generic method
    public <T> List<T> arrayToList(T[] inputArray) {
        List<T> result = Arrays.asList(inputArray);
        return result;
    }

    // converts an input array of type T to list of type G
    public static <T, G> List<G> arrayToListWithOperation(T[] inputArray, Function<T, G> mapperFunction){
        return Arrays.stream(inputArray)
            .map(mapperFunction)
            .collect(Collectors.toList());
    }

    // this is an operations.
    public static Boolean checkIfEven(Integer num){
        return num % 2 == 0;
    }

    @Test
    public void test_simpleGenericMethod(){
        Integer [] input = new Integer[]{1,2,3,4,5,6};
        List<Integer> inputList = arrayToList(input);
        for(Integer n : inputList) {
            System.out.println("n = " + n);
        }

        String[] names = new String[]{"sa", "sasa", "sasa"};
        List<String> namesList = arrayToList(names);
        for(String name: namesList) {
            System.out.println("name = " + name);
        }

    }

    @Test
    public void test_twoGenericTypeMethod(){
        Integer [] input = new Integer[]{1,2,3,4,5,6};
        List<String> convertedString  = arrayToListWithOperation(input, Object::toString);
        for(String name: convertedString) {
            System.out.println("Num = " + name);
        }

        List<Boolean> isEvenInputList = arrayToListWithOperation(input, JavaGenerics::checkIfEven);
        //Arrays.stream(input).sequential().forEach(System.out::print);
        isEvenInputList.stream().forEach(System.out::println);
    }

//    public static Map<Integer, Boolean> mapToEven(Integer x) {
//        if (x%2 == 0) {
//            return Map.of(x, true);
//        }
//        return Map.of(x, false);
//    }
//
//    @Test
//    public void test(){
//        Integer [] input = new Integer[]{1,2,3,4,5,6};
//        List<Map<Integer, Boolean>> result = Arrays.stream(input)
//            .map(JavaGenerics::mapToEven)
//            .collect(Collectors.toList());
//
//        System.out.println("result = " + result);
//
//    }

}
