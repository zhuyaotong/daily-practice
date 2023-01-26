package com.github.zhuyaotong.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;

            if (head == null) {
                tail = new ListNode(sum % 10);
                head = tail;
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return head;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode[] arr = s.init();
        System.out.println("初始化的两条链表的val如下：");
        s.print(arr);

        System.out.println("执行addTwoNumbers的结果如下：");
        ListNode result = s.addTwoNumbers(arr[0], arr[1]);
        s.print(result);
    }

    private ListNode[] init() {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l4.next = l5;
        l5.next = l6;

        ListNode[] result = new ListNode[2];
        result[0] = l1;
        result[1] = l4;

        return result;
    }

    private void print(ListNode[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        ListNode l1 = arr[0];
        ListNode l2 = arr[1];
        print(l1);
        print(l2);
    }

    private void print(ListNode ln) {
        List<Integer> result = new ArrayList<>();

        while (ln != null) {
            result.add(ln.val);
            ln = ln.next;
        }
        System.out.println(result);
    }
}

class ListNode {
    protected int val;
    protected ListNode next;

    protected ListNode() {
    }

    protected ListNode(int val) {
        this.val = val;
    }

    protected ListNode(int val, ListNode next) {
        this.next = next;
    }
}