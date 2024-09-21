package com.rahulg.das.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Source: https://medium.com/@mukhopadhyaypushan42/lets-solve-bcktracking-problems-like-a-pro-part-1-ed0fb1abb897
 * Problem 1: Combinations
 * Let’s solve the below problem:
 *
 * _Given a list of distinct positive numbers, find all combinations that add up to a given number.
 *
 * Input: [1, 2, 3, 4], target = 5 Output: [[2, 3], [1, 4]]_
 */
public class CombinationSumTarget {


    /**
     * This is the entry point for the test cases to invoke with inputs.
     * Inputs are not necessarily sorted.
     * there could be duplicates.
     * Each number can be used only once.
     * Returns the expected response back to caller.
     * @return
     */
    public List<List<Integer>> solutionForCombinationSum(List<Integer> input, Integer targetSum) {
        List<List<Integer>> finalResult = new ArrayList<>(); // final list to be returned
        List<Integer> curList = new ArrayList<>(); // for tracking the current state
        int curIndex = 0; // start index
        int currSum = 0; // current sum of elements in curlist
        backtrackingSum(input, finalResult, curList, targetSum, currSum, curIndex);
        return finalResult;
    }

    /**
     * This solution goed through all the combination of the given inputs without repaeating itself and chosed
     * the solution on the way.
     * Input [1,2,3] =combinations> {[],[1],[1,2],[1,2,3],[1,3],[2],[2,3], [3]}
     * @param input
     * @param finalResult
     * @param curList
     * @param targetSum
     * @param currSum
     * @param curIndex
     */
    private void backtrackingSum(List<Integer> input, List<List<Integer>> finalResult, List<Integer> curList, int targetSum, int currSum, int curIndex) {
        // corner cases.
        // best case if sum is reached
        if (targetSum == currSum) {
            // create a copy and add to result list.
            System.out.println("Solution = " + curList);
            finalResult.add(new ArrayList<>(curList));
            return;
        }
        // stop case if sum is greater than what we are looking for
        if (targetSum < currSum) {
            // we dont have to do anything
            return;
        }
        // further processing. iterate elements from `curIndex`
//        IntStream.range(curIndex, input.size())
//            .forEach();
        for(int i= curIndex; i < input.size(); i++) {
            Integer ele = input.get(i);
            curList.add(ele);
            backtrackingSum(input, finalResult, curList, targetSum, currSum + ele, i + 1);
            curList.remove(curList.size() - 1); // remove the last element which will  surely exist.
        }
    }


    @Test
    public void test_case1() {
        List<Integer> input = List.of(1,2,3,4,5);
        Integer targetSum = 5;
        System.out.println(" Running test case..");
        List<List<Integer>> result = solutionForCombinationSum(input, targetSum);
    }

    @Test
    public void test_case2() {
        List<Integer> input = List.of(1,2,2,3,4,5);
        Integer targetSum = 5;
        System.out.println(" Running test case..");
        List<List<Integer>> result = solutionForCombinationSum(input, targetSum);
    }

    @Test
    public void test_case3() {
        List<Integer> input = List.of(1,2,2,3,4,1);
        Integer targetSum = 5;
        System.out.println(" Running test case..");
        List<List<Integer>> result = solutionForCombinationSum(input, targetSum);
    }

    /**
     * This is the entry point for the test cases to invoke with inputs.
     * Inputs are not necessarily sorted.
     * there could be duplicates numbers in the input.
     * Each number can be used multiple times but there should not be repeated individual results.
     * Returns the expected response back to caller.
     * @return
     */
    public List<List<Integer>> solutionForCombinationSumWithRepetition(List<Integer> input, Integer targetSum) {
        List<List<Integer>> finalResult = new ArrayList<>(); // final list to be returned
        List<Integer> curList = new ArrayList<>(); // for tracking the current state
        int curIndex = 0; // start index
        int currSum = 0; // current sum of elements in curlist
        backtrackingSumWithRepetition(input, finalResult, curList, targetSum, currSum, curIndex);
        return finalResult;
    }

    /**
     * This solution goes through all the combination of the given inputs with repeating itself and choses
     * the solution on the way.
     * Input [1,2] =combinations> {[],[1],[1,1],[1,2],[2],[2,2]}
     * @param input
     * @param finalResult
     * @param curList
     * @param targetSum
     * @param currSum
     * @param curIndex
     */
    private void backtrackingSumWithRepetition(List<Integer> input, List<List<Integer>> finalResult, List<Integer> curList, int targetSum, int currSum, int curIndex) {
        // corner cases.
        // best case if sum is reached
        if (targetSum == currSum) {
            // create a copy and add to result list.
            System.out.println("Solution = " + curList);
            finalResult.add(new ArrayList<>(curList));
            return;
        }
        // stop case if sum is greater than what we are looking for
        if (targetSum < currSum) {
            // we dont have to do anything
            return;
        }
        // further processing. iterate elements from `curIndex`
//        IntStream.range(curIndex, input.size())
//            .forEach();
        Set<Integer> alreadyProcessed = new HashSet<>(); // track ele which are processed in the current iteration
        for(int i= curIndex; i < input.size(); i++) {
            Integer ele = input.get(i);
            // if the currEle is already processed in the same stack, dont process it again to avoid duplicate result sets..
            if (alreadyProcessed.contains(ele)) {
                continue;
            }
            curList.add(ele);
            backtrackingSumWithRepetition(input, finalResult, curList, targetSum, currSum + ele, i );
            alreadyProcessed.add(ele); // add element to already processed list so that we dont prcess this again.
            curList.remove(curList.size() - 1); // remove the last element which will  surely exist.
        }
    }

    @Test
    public void test_case4_repeat() {
        List<Integer> input = List.of(1,2,3,4);
        Integer targetSum = 5;
        System.out.println(" Running test case..");
        List<List<Integer>> result = solutionForCombinationSumWithRepetition(input, targetSum);
    }

    @Test
    public void test_case5_repeat() {
        List<Integer> input = List.of(1,2,1);
        Integer targetSum = 5;
        System.out.println(" Running test case..");
        List<List<Integer>> result = solutionForCombinationSumWithRepetition(input, targetSum);
        System.out.println("result = " + result);
    }

    @Test
    public void test_case6_repeat() {
        List<Integer> input = List.of(1,2,1);
        Integer targetSum = 0;
        System.out.println(" Running test case..");
        List<List<Integer>> result = solutionForCombinationSumWithRepetition(input, targetSum);
        System.out.println("result = " + result);
    }
}
