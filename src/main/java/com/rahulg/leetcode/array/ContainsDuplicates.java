package com.rahulg.leetcode.array;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicates {

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        int i = 0;
        while(i < nums.length) {
            if (map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], Boolean.TRUE);
            i++;
        }
        return false;
    }

    @Test
    public void test() {
        int[] input = new int[]{1,2,3,1};
        boolean output = containsDuplicate(input);
        System.out.println("output: " + output);
    }
}
