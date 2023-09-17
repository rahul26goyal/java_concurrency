package com.rahulg.collections;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JavaLinkedList {

    @Test
    public void test_simple(){
        LinkedList<String> names = new LinkedList<>();

        // insert some names
        names.add("Rahul");
        names.addAll(List.of("XYZ", "ABC"));
        names.addFirst("First Name");
        names.addLast("Last Name");

        Iterator<String> it = names.listIterator();
        while(it.hasNext()) {
            System.out.println(String.format("Name: {%s}", it.next()));
        }

        // shallow copy
        LinkedList<String> newNames = (LinkedList<String>) names.clone();
        newNames.add("New Member"); // both arraylist gets updated.
        System.out.println("names = " + newNames);
        System.out.println("newNames = " + newNames);

        boolean exist = names.contains("Rahul");
        if (exist) {
            System.out.println("Index of Rahul: " + names.indexOf("Rahul"));
        }

        System.out.println("Start polling...");
        LinkedList<String> deepList = new LinkedList<>();
        while(names.size() != 0) {
            String name = names.poll();
            System.out.println("name = " + name);
            deepList.offer(name); // adds element at end of the list
        }
        names.poll();
        //names.pop(); // this will throw java.util.NoSuchElementException
        System.out.println("deepList = " + deepList);


        // String[] namesArray = (String[]) deepList.toArray(); // this will throw java.lang.ClassCastException:class [Ljava.lang.Object; cannot be cast to class [Ljava.lang.String;
        Object[] namesObject = deepList.toArray();
        // correct way to cast to String
        String[] namesArray = deepList.toArray(new String[0]);
        for(String n: namesArray){
            System.out.println("Array Name = " + n);
        }

    }
}
