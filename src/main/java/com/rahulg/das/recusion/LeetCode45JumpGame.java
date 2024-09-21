package com.rahulg.das.recusion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * The Jump Game 2 problem states Given an array of non-negative integers where each element represents the maximum number of steps you can take forward from that position. In other words, if you are at index ‘ ‘, then nums[i] represents that you can jump to any nums[i + j] where:
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * It’s guaranteed that you can reach nums[n - 1].
 * Write a program to find the minimum number of jumps required to reach the end of the array (starting from the first element).
 * Source: https://medium.com/@teachingbee/leetcode-45-jump-game-2-solution-java-dc8fc76abf18
 */
public class LeetCode45JumpGame {

    public int minJumpToReachEnd(ArrayList<Integer> input) {
        // base cases
        if (input.size() == 0 || input.size() == 1) {
            return input.size();
        }
        // validate input
        input.stream()
            .forEach((num) -> {
                if (num < 0) {
                    // unchecked exception.
                    throw new RuntimeException("Invalid argument: Negative number found");
                }
            });
        int startIndex = 0;
        Integer result = getMinJumpRequiredRecursive(input, startIndex);
        System.out.println("result = " + result);
        return result;
    }

    private int getMinJumpRequiredRecursive(ArrayList<Integer> input, int currIndex) {
        if (currIndex == input.size() - 1) {
            return 0;
        }
        if (currIndex >= input.size() || input.get(currIndex) == 0) {
            // no solution with this jump selections.
            return Integer.MAX_VALUE;
        }
        int minJump = Integer.MAX_VALUE;
        // number of jumps allowed from 1 to allowedjump
        int allowedJump = input.get(currIndex);
        // todo: convert to stream.
        for(int i = 1; i <= allowedJump; i++) {
            int currJump = getMinJumpRequiredRecursive(input, currIndex + i);
            if (currJump != Integer.MAX_VALUE) {
                // update only if we have a solution
                minJump = Integer.min(minJump, currJump + 1);
            }
        }
        return minJump;
    }


    @Test
    public void test_base_cases_with_recursion() {
        Assertions.assertEquals(0, minJumpToReachEnd(new ArrayList<>()));
        Assertions.assertEquals(1, minJumpToReachEnd(new ArrayList<>(List.of(10))));
        Assertions.assertThrows(RuntimeException.class, () -> {
            minJumpToReachEnd(new ArrayList<>(List.of(1,2,3,4,5,-8)));
        });
        // no solution to reach the end.
        Assertions.assertEquals(Integer.MAX_VALUE, minJumpToReachEnd(new ArrayList<>(List.of(0,0,0,0))));
        // we may need to handle if thi is not expected
        Assertions.assertEquals(1, minJumpToReachEnd(new ArrayList<>(List.of(0))));
        Assertions.assertEquals(Integer.MAX_VALUE, minJumpToReachEnd(new ArrayList<>(List.of(0, 0))));
        // if the first element is 0, we can not move forward at all.
        Assertions.assertEquals(Integer.MAX_VALUE, minJumpToReachEnd(new ArrayList<>(List.of(0, 1,2,3,4,5))));
    }

    @Test
    public void test_cases_with_recursion() {
        Assertions.assertEquals(2, minJumpToReachEnd(new ArrayList<>(List.of(2,3,1,1,4))));
        Assertions.assertEquals(3, minJumpToReachEnd(new ArrayList<>(List.of(1, 3, 5, 8, 10, 2, 6, 7, 6, 8, 9))));
    }

    /**
     * DP solution
     */
    public int minJumpToReachEndUsingDp(ArrayList<Integer> input) {
        // base cases
        if (input.size() == 0 || input.size() == 1) {
            return input.size();
        }
        // validate input
        input.stream()
            .forEach((num) -> {
                if (num < 0) {
                    // unchecked exception.
                    throw new RuntimeException("Invalid argument: Negative number found");
                }
            });
        ArrayList<Integer> dpCacheSteps = new ArrayList<>(input.size());
        IntStream.range(0,input.size()).forEach(i -> dpCacheSteps.add(-1));
        System.out.println("dpCacheSteps = " + dpCacheSteps);
        int startIndex = 0;
        int result = Integer.MAX_VALUE;
        result = getMinJumpWithDP(input, startIndex, dpCacheSteps);
        System.out.println("result = " + result);
        return result;
    }

    private Integer getMinJumpWithDP(ArrayList<Integer> input, int currIndex, ArrayList<Integer> dpSolutionCache) {
        if (currIndex  == input.size() -1) {
            return 0;
        }
        if (currIndex >= input.size() || input.get(currIndex) == 0) {
            return Integer.MAX_VALUE;
        }
        if (dpSolutionCache.get(currIndex) != -1) {
            return dpSolutionCache.get(currIndex);
        }
        Integer minJump = Integer.MAX_VALUE;
        int allowedJumps = input.get(currIndex);
        for(int i = 1; i <= allowedJumps; i++) {
            Integer jump = getMinJumpWithDP(input, currIndex+i, dpSolutionCache);
            if(jump != Integer.MAX_VALUE) {
                    minJump = Integer.min(minJump, jump + 1);
                }
        }
        dpSolutionCache.set(currIndex, minJump);
        return minJump;
//        AtomicInteger minJump = new AtomicInteger(Integer.MAX_VALUE);
//        int allowedJumps = input.get(currIndex);
//        IntStream.range(1, allowedJumps+1)
//            .forEach(i -> {
//                int jump = 1 + getMinJumpWithDP(input, currIndex+i, dpSolutionCache);
//                if(jump != Integer.MAX_VALUE && jump < minJump.get()) {
//                    minJump.set(jump);
//                }
//            });
//        dpSolutionCache.set(currIndex, minJump.get());
//        return dpSolutionCache.get(currIndex);
    }

    @Test
    public void test_base_cases_with_dp() {
        Assertions.assertEquals(0, minJumpToReachEndUsingDp(new ArrayList<>()));
        Assertions.assertEquals(1, minJumpToReachEndUsingDp(new ArrayList<>(List.of(10))));
        Assertions.assertThrows(RuntimeException.class, () -> {
            minJumpToReachEndUsingDp(new ArrayList<>(List.of(1,2,3,4,5,-8)));
        });
        // no solution to reach the end.
        Assertions.assertEquals(Integer.MAX_VALUE, minJumpToReachEndUsingDp(new ArrayList<>(List.of(0,0,0,0))));
        // we may need to handle if thi is not expected
        Assertions.assertEquals(1, minJumpToReachEnd(new ArrayList<>(List.of(0))));
        Assertions.assertEquals(Integer.MAX_VALUE, minJumpToReachEnd(new ArrayList<>(List.of(0, 0))));
        // if the first element is 0, we can not move forward at all.
        Assertions.assertEquals(Integer.MAX_VALUE, minJumpToReachEnd(new ArrayList<>(List.of(0, 1,2,3,4,5))));
    }

    @Test
    public void test_cases_with_DP() {
        Assertions.assertEquals(2, minJumpToReachEndUsingDp(new ArrayList<>(List.of(2,3,1,1,4))));
        Assertions.assertEquals(3, minJumpToReachEndUsingDp(new ArrayList<>(List.of(1, 3, 5, 8, 10, 2, 6, 7, 6, 8, 9))));
        Assertions.assertEquals(10, minJumpToReachEndUsingDp(new ArrayList<>(List.of(1,1,1,1,1,1,1,1,1,1,1))));
    }

    @Test
    public void tes_streams() {

    }



}
