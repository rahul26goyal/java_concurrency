package com.rahulg.leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Q20EasyValidParenthesis {

    public static final Set<Character> OPENING = new HashSet<>(Arrays.asList('(', '{', '['));
    public static final Map<Character, Character> CLOSING_TO_OPENING = new HashMap<Character, Character>() {{
        put(')', '(');
        put('}', '{');
        put(']', '[');
    }};

    public boolean isValidParenthesisString(String input) {
        if(input == null || input.length() == 0) {
            return true;
        }
        if(input.length() % 2 != 0) {
            return false;
        }
        final Stack<Character> stack = new Stack<>();
        // iterate the input string
        for (char ch: input.toCharArray()) {
            if (OPENING.contains(ch)) {
                stack.push(ch);
            } else if (CLOSING_TO_OPENING.containsKey(ch)) {
               if(stack.isEmpty()) {
                   return false;
               }
               char top = stack.pop();
               if(!CLOSING_TO_OPENING.containsKey(top) && CLOSING_TO_OPENING.get(ch) != top) {
                   return false;
               }
            } else {
                System.out.println("ch = " + ch + " is not valid character. existing..");
                return false;
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        String input = "()";
        System.out.println("Input: " + input);
        Assertions.assertTrue(isValidParenthesisString(input));
        input =  "()[]{}";
        System.out.println("Input: " + input);
        Assertions.assertTrue(isValidParenthesisString(input));
        input =  "(]";
        System.out.println("Input: " + input);
        Assertions.assertFalse(isValidParenthesisString(input));
        input =  "([)]";
        System.out.println("Input: " + input);
        Assertions.assertFalse(isValidParenthesisString(input));
        input = "{[]}";
        System.out.println("Input: " + input);
        Assertions.assertTrue(isValidParenthesisString(input));

        input =  "([sa])";
        System.out.println("Input: " + input);
        Assertions.assertFalse(isValidParenthesisString(input));

    }

}
