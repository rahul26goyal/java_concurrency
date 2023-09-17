package com.rahulg.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaArrayList {

    @Test
    public void testDeclarations(){
        List<String> list = new ArrayList<>();
        Assertions.assertTrue(list.isEmpty());
        list.add("Rahul");
        Assertions.assertEquals(1, list.size());

        // with initial capacity
        List<Integer> numList = new ArrayList<>(20); // initial size is 10
        Assertions.assertEquals(0, numList.size()); // size is still 0

        List<Integer> numbers = IntStream.range(0, 5).boxed().collect(Collectors.toList());
        Assertions.assertEquals(5, numbers.size()); // size is still 0
        for(int i : numbers){
            System.out.println("i = " + i);
        }

    }

    @Test
    public void test(){
        List<Integer> l1 = new ArrayList<Integer>();
        // l1.remove(0); // IOBE exception

        List<Integer> fixedList = List.of(1,2,3);
        System.out.println("fixedList = " + fixedList);
        //fixedList.add(12); // USOE exception.. this is not supported as its a immutable list
        System.out.println("fixedList = " + fixedList);
    }


}
