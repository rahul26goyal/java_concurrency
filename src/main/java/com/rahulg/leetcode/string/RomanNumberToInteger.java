package com.rahulg.leetcode.string;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RomanNumberToInteger {

    public static Map<String, Integer> ROMANNUMERAL_INTEGER_MAP = new HashMap<>(){{
        put("I", 1);
        put("V", 5);
        put("X", 10);
        put("L", 50);
        put("C", 100);
        put("D", 500);
        put("M", 1000);
        // special consideration
        put("IV", 4);
        put("IX", 9);
        put("XL", 40);
        put("XC", 90);
        put("CD", 400);
        put("CM", 900);
    }};
    public int romanToInt(String input) {
        int size = input.length();
        int result = 0;
        for(int i=0; i< size; i++) {
            if (i +1 < size) {
                String doubleChar = input.substring(i, i +2);
                if(ROMANNUMERAL_INTEGER_MAP.containsKey(doubleChar)) {
                    result += ROMANNUMERAL_INTEGER_MAP.get(doubleChar);
                    i++;
                    continue;
                }
            }
            String singleChar = String.valueOf(input.charAt(i));
            if(ROMANNUMERAL_INTEGER_MAP.containsKey(singleChar)) {
                result += ROMANNUMERAL_INTEGER_MAP.get(singleChar);
            }
        }
        return result;
    }

    @Test
    public void test() {
        String input = "III";
        System.out.println("Input: " + input);
        System.out.println("Output: " + romanToInt(input));
        input = "LVIII";
        System.out.println("Input: " + input);
        System.out.println("Output: " + romanToInt(input));
        input = "MCMXCIV";
        System.out.println("Input: " + input);
        System.out.println("Output: " + romanToInt(input));

        input = "MCMXCIV";
        System.out.println("Input: " + input);
        System.out.println("Output: " + romanToInt(input));
    }
}
