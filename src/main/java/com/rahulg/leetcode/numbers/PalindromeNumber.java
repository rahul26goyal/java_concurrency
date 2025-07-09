package com.rahulg.leetcode.numbers;

import org.junit.jupiter.api.Test;

public class PalindromeNumber {

    // reverses the number
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        int temp = x;
        int reverse = 0;
        while(temp  > 0) {
            int rem = temp % 10;
            reverse = reverse * 10 + rem;
            temp = temp / 10;
        }
        return reverse == x;
    }

    // doing half reverse
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        if (x %10 == 0) { // anything ending with 0 is non-palindrome
            return false;
        }

        int halfReverse = 0;
        while(x > halfReverse) {
            halfReverse = halfReverse * 10 + x % 10;
            x = x / 10;
        }
        // if even number of digits, the number would be equal
        // if odd number of digit, halfReverse will have one additional middle digit in the while loop computation.
        // remove it and compare
        return (x == halfReverse) || (x == halfReverse / 10); //
    }

    @Test
    public void test() {
        int x = 121;
        System.out.println("Input: " + x);
        System.out.println("Output: " + isPalindrome(x));
        x = -121;
        System.out.println("Input: " + x);
        System.out.println("Output: " + isPalindrome(x));
        x = 10;
        System.out.println("Input: " + x);
        System.out.println("Output: " + isPalindrome(x));
        x = 123;
        System.out.println("Input: " + x);
        System.out.println("Output: " + isPalindrome(x));
    }

    @Test
    public void test2() {
        int x = 121;
        System.out.println("Input: " + x);
        System.out.println("Output: " + isPalindrome2(x));
        x = -121;
        System.out.println("Input: " + x);
        System.out.println("Output: " + isPalindrome2(x));
        x = 10;
        System.out.println("Input: " + x);
        System.out.println("Output: " + isPalindrome2(x));
        x = 234565432;
        System.out.println("Input: " + x);
        System.out.println("Output: " + isPalindrome2(x));
    }
}
