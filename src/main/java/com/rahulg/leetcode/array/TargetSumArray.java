package com.rahulg.leetcode.array;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TargetSumArray {

    public int[] findTargetSumWaysBF(ArrayList <Integer> nums, int target) {
        int[] result = new int[2];
        int left;
        for(int i=0; i<nums.size(); i++) {
            left = target - nums.get(i);
            for (int j= i +1; j < nums.size(); j++) {
                if(left == nums.get(j)) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * Optimal Solution with O(N) complexity
     * using a map to store the complement and index
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * @param nums
     * @param target
     * @return
     */
    public int[] findTwoSum(ArrayList<Integer> nums, int target) {
        final Map<Integer, Integer> numToIndexMap = new HashMap<>();
        for(int i = 0;i<nums.size();i++) {
            int num = nums.get(i);
            int complement = target - num;
            if(numToIndexMap.containsKey(complement)) {
                return new int[] {numToIndexMap.get(complement), i};
            } else {
                numToIndexMap.put(num, i);
            }
        }
        return new int[] {};
    }


    @Test
    public void test() {
        int[] input = new int[]{2,4,5,6,3,0};
        ArrayList<Integer> nums = new ArrayList<>();
        // push input into nums
        for(int i : input) {
            nums.add(i);
        }
        int target = 5;
        int[] result = findTwoSum(nums, target);
        System.out.println("Result: " + Arrays.toString(result));
    }

}
