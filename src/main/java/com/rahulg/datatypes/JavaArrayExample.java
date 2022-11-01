package com.rahulg.datatypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JavaArrayExample {

    @Test
    public void testSimpleArray(){

        // array declaration
        int[] numbers;
        int nums[];
        // numbers[0] = 1; initialization error
        //System.out.println("nums = " + nums); // this will also throw initialization error
        // array declaration + initialization
        int[] anArray = new int[100]; // array
        int [] someNumbers = new int[]{2,4,6,8};
        
        // only array initialization
        numbers = new int[55];
        nums = new int[]{1,2,3,4,5};
        System.out.println("nums length= " + nums.length);
        System.out.println("numbers length= " + numbers.length);
        System.out.println("anArray length= " + anArray.length);
        System.out.println("someNumbers length= " + someNumbers.length);

        // adding elements
        numbers[0] = 1;
        anArray[0] = 10;
        
        // iterating elements
        for(int i : nums) {
            System.out.println("i = " + i);
        }
    }


    @Test
    public void testArrayToListCOnversion(){

        String[] names = new String[]{"a", "b", "c"};
        System.out.println("names = " + names.length);
        List<String> l1 = new ArrayList<>();
        for(String name: names) {
            l1.add(name);
        }
        System.out.println("l1 = " + l1);
        l1.add("New Name");
        System.out.println("l1 = " + l1);

        List<String> l2 = Arrays.asList(names);
        System.out.println("l2 = " + l2);
        
        

    }

    @Test
    public void testArrayToStreamConversion(){
        String[] names = new String[]{"a", "b", "c"};

        Stream<String> namesStream = Arrays.stream(names);

        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};

        //int[] evenNums = new int[10];
        int[] evenNums = Arrays.stream(nums).filter(n -> (n % 2 == 0)).toArray();
        for(int i : evenNums){
            System.out.println("i = " + i);
        }

        int[] multiply = Arrays.stream(nums).map((i) -> {return i * 2;}).toArray();
        System.out.println("Multiplied arrya::::::");
        for(int j : multiply){
            System.out.println("j = " + j);
        }
    }

    @Test
    public void testArrayMerge() {

        int arr1[] = new int[]{1,2,3,4,5,6};
        int[] arr2 = new int[]{10,20,30,40,50,60};
        int [] arr3 = new int[arr1.length + arr2.length];

        System.arraycopy(arr1, 0, arr3, 0, arr1.length);
        System.arraycopy(arr2, 0, arr3,  arr1.length, arr2.length);
        for (int i: arr3){
            System.out.println("i = " + i);
        }
        Assertions.assertEquals(arr3.length, 12);

    }

}
