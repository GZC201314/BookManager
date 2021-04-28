package org.bsm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bsm.leetcode.model.ListNode;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		if (nums == null) {
			return null;
		}
		Arrays.sort(nums);
		Set<List<Integer>> result = new HashSet<>();
		List<Integer> ans = new ArrayList<Integer>();
		dfs_subsetsWithDup(nums, 0, ans, result);
		return new ArrayList<>(result);
	}

	public static void dfs_subsetsWithDup(int[] nums, int length, List<Integer> ans, Set<List<Integer>> result) {
		if (nums.length == length) {
			result.add(new ArrayList<>(ans));
			return;
		}
		ans.add(nums[length]);
		dfs_subsetsWithDup(nums, length + 1, ans, result);
		ans.remove(ans.size() - 1);
		dfs_subsetsWithDup(nums, length + 1, ans, result);

	}

	public static int numDecodings(String s) {
		int length = s.length();
		int[] result = new int[length];
		int i = 0;
		while (i < length) {
			if (i < 2) {
				if (i == 0) {
					if (s.charAt(i) == '0') {
						return 0;
					}
					result[i] = 1;
				} else {
					if ("00".equals(s.substring(i - 1, i + 1))) {
						return 0;
					}
					if (Integer.parseInt(s.substring(i - 1, i + 1)) < 27 && s.charAt(i) != '0') {
						result[i] = 2;
					} else {
						if (s.charAt(i) == '0') {
							if (Integer.parseInt(s.substring(0, 2)) < 27) {
								result[i] = 1;
							} else {
								return 0;
							}
						} else {
							result[i] = result[i - 1];
						}
					}
				}
			} else {
				if ("00".equals(s.substring(i - 1, i + 1))) {
					return 0;
				}
				if (Integer.parseInt(s.substring(i - 1, i + 1)) < 27 && s.charAt(i) != '0') {
					if (s.charAt(i - 1) == '0') {
						result[i] = result[i - 1];
					} else {
						result[i] = result[i - 1] + result[i - 2];
					}
				} else {
					if (s.charAt(i) == '0') {
						if (Integer.parseInt(s.substring(i - 1, i + 1)) < 27) {
							result[i] = result[i - 2];
						} else {
							return 0;
						}
					} else {
						result[i] = result[i - 1];
					}
				}
			}
			i++;
		}
		return result[length - 1];
	}

	public static ListNode reverseBetween(ListNode head, int left, int right) {
		ListNode p = head;
		ListNode start = new ListNode();
		int[] nums = new int[right - left + 1];
		int i = 1;
		int count = 1;
		while (i <= right) {
			if (i < left) {
				p = p.next;
				i++;
				continue;
			}
			if (left == i) {
				start = p;
			}
			nums[nums.length - count++] = p.val;
			p = p.next;
			i++;

		}
		int index = 0;
		while (left <= right) {
			start.val = nums[index++];
			start = start.next;
			left++;
		}
		return head;
	}

	public static void main(String[] args) {
//		ListNode five = new ListNode(2);
//		ListNode four = new ListNode(1, five);
//		ListNode l1 = new ListNode(1, four);
//
//		ListNode f = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		ListNode f = new ListNode(5);
//		ListNode l2 = new ListNode(1, three);
//		[[1,2],[3,5],[6,7],[8,10],[12,16]]
//		ListNode[] nodeArr = new ListNode[] { l1, l2, l3 };
//		int[][] arr = new int[][] { { 1, 3, 5, 7 } };
//		char[][] arr = new char[][] {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//		int[][] arr = new int[][] { { 1, 5 } };
		int[] arr = new int[] { 1, 2, 2 };
		int[] arr1 = new int[] { 1 };
//		String[] strArr = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};

//		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
//		String word = "ABCCED";
//		nextPermutation(arr);
//		String[] strarr = new String[] { "What", "must", "be", "acknowledgment", "shall", "be" };
		long start = new Date().getTime();
		/**
		 * [1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1] 2
		 */
//		merge(arr,0, arr1,1);

		System.out.println(reverseBetween(f, 1, 1));
//		partition1(three, 0);
		long end = new Date().getTime();
		System.out.println("程序运行时间: " + (end - start));
//		solveSudoku(suduku);
//		System.out.println();
//		rotate(arr);
//		System.out.println(rotateRight(l1,2));
//		System.out.println(159002%6);
//		Random random = new Random();
//		for (int i = 0; i < 20; i++) {
//			System.out.println(random.nextInt(2));
//		}
//		ListNode result = reverseKGroup(l1, 2);
//		while (result != null) {
//			System.out.println(result.val);
//			result = result.next;
//		}
	}
}
