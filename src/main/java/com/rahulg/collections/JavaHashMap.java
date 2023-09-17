package com.rahulg.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JavaHashMap {

    

    @Test
    public void test_simple_Declaration(){

        Map<String, Integer> stringValue = new HashMap<>();
        stringValue.put("First", 1);
        stringValue.put("Second", 2);
        stringValue.put("Third", 3);
        stringValue.put("Fourth", 4);
        System.out.println("stringValue = " + stringValue);

        Assertions.assertEquals(stringValue.get("Second"), 2);
        Assertions.assertTrue(stringValue.containsKey("Fourth"));
        Assertions.assertFalse(stringValue.containsKey("fourth")); // case-sensitive keys
        Assertions.assertNull(stringValue.remove("Five"));
        Assertions.assertEquals(stringValue.remove("Fourth"), 4);
        Assertions.assertTrue(stringValue.size() == 3);
        //stringValue.put("Fifth", 5);
        Assertions.assertEquals(stringValue.put("Fifth", 5), null); // add returns previous / existing element.
        Assertions.assertEquals(stringValue.put("Fifth", 15), 5); // prev value.
        Assertions.assertEquals(stringValue.get("Fifth"), 15);
    }

    @Test
    public void test_map_view_methods(){
        Map<String, Integer> stringValue = new HashMap<>();
        stringValue.put("First", 1);
        stringValue.put("Second", 2);
        stringValue.put("Third", 3);
        stringValue.put("Fourth", 4);
        System.out.println("stringValue = " + stringValue);

        for(String key : stringValue.keySet()){
            System.out.println("key = " + key);
        }
        
        for(Integer value: stringValue.values()) {
            System.out.println("value = " + value);
        }

        for(Map.Entry<String, Integer> ele: stringValue.entrySet()) {
            System.out.println("ele.getKey() = " + ele.getKey() + ":" + ele.getValue());
        }

        Iterator<String> it =  stringValue.keySet().iterator();
        while (it.hasNext()) {
            System.out.println("it.next() = " + it.next());
        }

        String[] keys = stringValue.keySet().toArray(new String[0]);
        Integer[] values = stringValue.values().toArray(new Integer[0]);
        System.out.println("values = " + values);
    }

    @Test
    public void test_java8_additions(){
        Map<String, Integer> stringValue = new HashMap<>();
        stringValue.put("First", 1);
        stringValue.put("Second", 2);
        stringValue.put("Third", 3);
        stringValue.put("Fourth", 4);
        System.out.println("stringValue = " + stringValue);

        stringValue.forEach((key, value) -> {
            System.out.println("key = " + key + " :" + value);
        });
        
        Integer defaultValue = stringValue.getOrDefault("UnKnown", 101);
        System.out.println("defaultValue = " + defaultValue);
    }

    public static final Map<String, String> fixedMap = new HashMap<>();
    {
        fixedMap.put("First", "1st");
        fixedMap.put("Second", "2nd");
        fixedMap.put("Third", "3rd");
    }
    
    @Test
    public void test_static_map(){
        fixedMap.forEach((k, v) -> {
            System.out.println("k = " + k);
        });
    }

}
