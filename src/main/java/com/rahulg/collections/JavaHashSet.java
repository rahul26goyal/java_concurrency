package com.rahulg.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaHashSet {

    @Test
    public void test_initialization(){

        Set<Integer> numSet = new HashSet<>(); // default initialization.
        System.out.println("numSet = " + numSet);
        Set<Integer> numSet2 = new HashSet<>(List.of(1,2,3,4,5,6)); // default initialization.
        numSet2.add(12);
        System.out.println("numSet2 = " + numSet2);
        Set<Integer> numSet3 = new HashSet<>(Arrays.asList(1,2,3,4)); // default initialization.
        System.out.println("numSet3 = " + numSet3);

    }

    @Test
    public void test_simpleSet(){

        Set<String> names = new HashSet<>(List.of("a", "a", "b", "c"));
        Collection<String> someCollection = new HashSet<>();
        //someCollection will have have access to methos present in Collection Class and not additional method in HashSet calss.
        System.out.println("names = " + names);

        Integer[] nums = new Integer[]{1,7,2,3,4,5,12,3,4,6,7,8,};

        Set<Integer> uniqueNums = Arrays.asList(nums).stream()
            .collect(Collectors.toSet());
        System.out.println("uniqueNums = " + uniqueNums);
    }

    @Test
    public void test_set_iterator(){
        Set<String> hashset = new HashSet<>();
        hashset.add("First");
        hashset.add("Second");
        hashset.add("Third");

        Iterator<String> it = hashset.iterator();
        while (it.hasNext()) {
            System.out.println("it.next() = " + it.next());
            // If the set is modified at any time after the iterator is created in any way except through the iterator's own remove method,
            // the Iterator throws a ConcurrentModificationException.
            it.remove(); // does not throw any exception.
        }
        System.out.println("it = " + it);
        Assertions.assertFalse(it.hasNext(), "Should not have any more element");
        //it.next();
        Exception ex = Assertions.assertThrows(NoSuchElementException.class, () -> it.next(), "Should throw exception");
        System.out.println("ex.toString() = " + ex.toString());
    }

    @Test
    public void test_set_iterator_xception() {
        Set<String> hashset = new HashSet<>();
        hashset.add("First");
        hashset.add("Second");
        hashset.add("Third");

        Iterator<String> it = hashset.iterator();
        try {
            while (it.hasNext()) {
                it.next();
                // any out of box modification will lead to error.
                hashset.remove("Third");
                hashset.add("Fourth");
            }
        }
        catch (ConcurrentModificationException cx) {
            System.out.println("cx = " + cx);
        }
    }

    // This is a correct function definition of Java Consumer<E>
    public void printStringSizeConsumer(String input){
        System.out.println("input.length() = " + input.length());
    }

    //// This is a correct function definition of consumer but its a valid Function<T, E>
    public Integer returnStringSizeConsumer(String input){
        System.out.println("input.length() = " + input.length());
        return input.length();
    }

    @Test
    public void test_set_iterator_forEach() {
        Set<String> hashset = new HashSet<>();
        hashset.add("First");
        hashset.add("Second");
        hashset.add("Third");

        hashset.forEach(this::printStringSizeConsumer);
        hashset.forEach(this::returnStringSizeConsumer); // this also works but will not return anything
    }


    public boolean startsWithF(String input) {
        return input.startsWith("F") || input.startsWith("f");
    }

    @Test
    public void test_set_with_treams() {
        Set<String> hashset = new HashSet<>();
        hashset.add("First");
        hashset.add("Second");
        hashset.add("Third");
        hashset.add("Fourth");
        hashset.add("Fifth");
        hashset.add("FifthFifthFifthFifth");
        hashset.add("Sixth");
        System.out.println("Input hashset = " + hashset);

        // filter based on string op
        Set<String> statsWithF = hashset.stream()
            .filter(this::startsWithF)
            .collect(Collectors.toSet());
        System.out.println("statsWithF = " + statsWithF);

        Set<String> lenGrt6 = hashset.stream()
            .filter(input -> input.length() > 5)
            .collect(Collectors.toSet());
        System.out.println("lenGrt6 = " + lenGrt6);

        // get the max string length
        Integer maxleg = hashset.stream()
            .map(name -> (Integer) name.length())
            .max(Comparator.comparing(Integer::valueOf))
            .get();
        System.out.println("maxleg = " + maxleg);

        // check if any string len less than 3 exist

        Boolean checkIfLessThanThreeExist = hashset.stream()
            .anyMatch((s) -> s.length() < 3);
        System.out.println("checkIfLessThanThreeExist = " + checkIfLessThanThreeExist);
        
        Boolean checkIfAllGreaterThan3 = hashset.stream()
            .allMatch((s) -> s.length() > 3);
        System.out.println("checkIfAllGreaterThan3 = " + checkIfAllGreaterThan3);

        Boolean checkIfAnyGreaterThan20 = hashset.stream()
            .anyMatch((s) ->  s.length() > 20);
        System.out.println("checkIfAnyGreaterThan20 = " + checkIfAnyGreaterThan20);

        Boolean checkIfNoneGreaterThan20 = hashset.stream()
            .noneMatch((s) -> s.length() > 20);
        System.out.println("checkIfNoneGreaterThan20 = " + checkIfNoneGreaterThan20);

        //
        String firstOccuracnewithS = hashset.stream()
            .filter((s) -> s.startsWith("S"))
            .findFirst()
            .get();
            //.orElse(null);
        System.out.println("firstOccuracnewithS = " + firstOccuracnewithS);

        String firstOccuracnewithX = hashset.stream()
            .filter((s) -> s.startsWith("x"))
            .findFirst()
            //.get(); // this will throw exception if not found
            .orElse("Not FOUND");
        System.out.println("firstOccuracnewithX = " + firstOccuracnewithX);
    }

}
