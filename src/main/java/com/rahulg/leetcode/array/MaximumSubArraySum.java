package com.rahulg.leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MaximumSubArraySum {

    public int maxSubArrayBruteForce(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        int size = nums.length;
        for(int i=0;i<size;i++) {
            int currMaxSum = 0;
            for(int j=i;j<size; j++) {
                currMaxSum += nums[j];
                if(currMaxSum > maxValue) {
                    maxValue = currMaxSum;
                }
            }
        }
        return maxValue;
    }

    public int maxSubArraySumSimple(int[] nums) {
        int maximum, currectSum;
        maximum = nums[0]; // assume this to be max
        currectSum = 0;

        for (int i = 0;i < nums.length; i++) {

            if(currectSum < 0) {
                // if current sum is negative, then we will not consider it
                // as it will only decrease the sum of the subarray
                // so we will start the currectSum from 0
                // and will not consider the previous elements
                currectSum = 0;
            }
            currectSum += nums[i]; // add the current element to the currectSum
            maximum = Math.max(maximum, currectSum);
        }
        return maximum;

    }

    // recursive solution for calculating max subarray sum

    public int maxSubArraySumRecur(int[] nums) {
        return maxSubArraySumRecursive(nums, 0, false);
    }
    public int maxSubArraySumRecursive(int[] nums, int currentIndex, boolean mustCurrentIndex) {
        if (currentIndex >= nums.length) {
            return mustCurrentIndex ? 0: Integer.MIN_VALUE;
        }
        if(mustCurrentIndex) {
            return Integer.max(0, nums[currentIndex] + maxSubArraySumRecursive(nums, currentIndex + 1, true));
        }
        return Integer.max(
            nums[currentIndex] + maxSubArraySumRecursive(nums, currentIndex + 1, true),
            maxSubArraySumRecursive(nums, currentIndex + 1, false)
        );
    }

    @Test
    public void testmaxSubArraySumRecur() {
        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int output = maxSubArraySumRecur(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(6, output);

        input = new int[]{-10,-9,-8,-7,-6,-5};
        output = maxSubArraySumRecur(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(-5, output);

        input = new int[]{-10,-9,-8,-1, -7,-6,-5};
        output = maxSubArraySumRecur(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(-1, output);

        input = new int[]{1,2,3,4,5,6};
        output = maxSubArraySumRecur(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(21, output);
    }

    @Test
    public void testmaxSubArraySumSimple() {
        // generate code same as testMaxSubArrayBruteForce
        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int output = maxSubArraySumSimple(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(6, output);

        input = new int[]{-10,-9,-8,-7,-6,-5};
        output = maxSubArraySumSimple(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(-5, output);

        input = new int[]{-10,-9,-8,-1, -7,-6,-5};
        output = maxSubArraySumSimple(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(-1, output);

        input = new int[]{1,2,3,4,5,6};
        output = maxSubArraySumSimple(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(21, output);
    }


    @Test
    public void testMaxSubArrayBruteForce() {
        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int output = maxSubArrayBruteForce(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(6, output);

        input = new int[]{-10,-9,-8,-7,-6,-5};
        output = maxSubArrayBruteForce(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(-5, output);

        input = new int[]{-10,-9,-8,-1, -7,-6,-5};
        output = maxSubArrayBruteForce(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(-1, output);

        input = new int[]{1,2,3,4,5,6};
        output = maxSubArrayBruteForce(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + output);
        Assertions.assertEquals(21, output);
    }
}
