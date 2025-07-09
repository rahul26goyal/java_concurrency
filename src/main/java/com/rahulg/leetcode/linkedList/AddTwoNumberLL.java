package com.rahulg.leetcode.linkedList;

public class AddTwoNumberLL {

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumber(ListNode num1, ListNode num2) {
        ListNode result = null;
        ListNode current = null;
        int carryOver = 0;
        ListNode first = num1;
        ListNode second = num2;
        while (first != null && second != null) {
            int sum = first.val + second.val + carryOver;
            carryOver = sum / 10;
            sum = sum % 10;
            ListNode newNode = new ListNode(sum);
            if(result == null) {
                result = newNode;
                current = newNode;
            }
            else {
                current.next = newNode;
                current = newNode;
            }
            first = first.next;
            second = second.next;
        }

        while (first != null) {
            int sum = first.val + carryOver;
            carryOver = sum / 10;
            sum = sum % 10;
            ListNode newNode = new ListNode(sum);
            if(result == null) {
                result = newNode;
                current = newNode;
            }
            else {
                current.next = newNode;
                current = newNode;
            }
            first = first.next;
        }

        while (second != null) {
            int sum = second.val + carryOver;
            carryOver = sum / 10;
            sum = sum % 10;
            ListNode newNode = new ListNode(sum);
            if(result == null) {
                result = newNode;
                current = newNode;
            }
            else {
                current.next = newNode;
                current = newNode;
            }
            second = second.next;
        }
        // tricky part.
        if (carryOver != 0 ) {
            ListNode newNode = new ListNode(carryOver);
            current.next = newNode;
        }
        return result;
    }

    public void printListNode(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
