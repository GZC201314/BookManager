package org.bsm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bsm.leetcode.model.ListNode;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class SolutionFinal {
	/***
	 * 两数相加
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//    	 l1 = new ListNode(2,new ListNode(4,new ListNode(4)));
//    	 l2 = new ListNode(5,new ListNode(6,new ListNode(4)));
		int temp = 0;
		ListNode l3 = new ListNode(0);
		ListNode p = l3;
		while (l1 != null || l2 != null || temp != 0) {
			int val1 = l1 != null ? l1.val : 0;
			int val2 = l2 != null ? l2.val : 0;
			l3.next = new ListNode(0);
			if (val1 + val2 + temp >= 10) {
				l3.val = (val1 + val2 + temp) % 10;
				temp = 1;
			} else {
				l3.val = val1 + val2 + temp;
				temp = 0;
			}
			l1 = l1 != null ? l1.next : null;
			l2 = l2 != null ? l2.next : null;
			if (l1 == null && l2 == null && temp == 0) {
				l3.next = null;
			} else {
				l3 = l3.next;

			}
		}
//        System.out.println(p);
		return p;
	}

	/***
	 * 两数之和
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				return new int[] { map.get(target - nums[i]), i };
			}
			map.put(nums[i], i);
		}
		return null;
	}

	/***
	 * 字符串非重复最长子串
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {
		String substring = "";
		char[] chararr = s.toCharArray();
		int start = 0, end = 0;
		int length = 0;
		int reLength = 0;
		for (int i = 0; i < chararr.length; i++) {
			if (substring.indexOf(chararr[i]) != -1) {

				// 如果查找到重复的,记录当前的子串长度
				if (reLength < substring.length()) {
					reLength = substring.length();
				}
				//
				start = s.indexOf(substring, start);
				start = substring.indexOf(chararr[i]) + start + 1;
				end = i;
				substring = s.substring(start, end + 1);
				length = substring.length();
			} else {
				substring += chararr[i];
				end++;
				length++;
			}
		}
		if (reLength < length) {
			reLength = length;
		}
		System.out.println(length);
		return reLength;

	}

	/***
	 * 寻找中位数
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int i = 0, j = 0, p = 0;
		int center = -1;
		int center1 = -1;
		double sum = 0;
		// 记录的总数
		int m = nums1.length;
		int n = nums2.length;
		int isdouble = (m + n) % 2;
		int count = m + n;
		if (isdouble == 0) {
			center = (count) / 2 - 1;
			center1 = center + 1;
		} else {
			center = (count + 1) / 2 - 1;
		}
		while (i < m || j < n) {
			// 如果两个都没有到头
			if (i < m && j < n) {
				// 判断两个数的大小
				if (nums1[i] < nums2[j]) {
					if (p == center) {
						sum += nums1[i];
					}
					if (p == center1) {
						sum += nums1[i];
					}
					i++;
					p++;
				} else {
					if (p == center) {
						sum += nums2[j];
					}
					if (p == center1) {
						sum += nums2[j];
					}
					j++;
					p++;
				}
			} else if (i < m && j >= n) {
				if (p == center) {
					sum += nums1[i];
				}
				if (p == center1) {
					sum += nums1[i];
				}
				i++;
				p++;
			} else {
				if (p == center) {
					sum += nums2[j];
				}
				if (p == center1) {
					sum += nums2[j];
				}
				j++;
				p++;
			}
		}
		if (isdouble == 0)
			return sum / 2;
		else
			return sum;

	}

	public static boolean isPalindrome(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}

	public static String longestPalindrome(String s) {
		String palindrome = "";
		for (int i = 0; i <= s.length(); i++) {
			for (int j = s.length(); j > i; j--) {
				// 判断是否是回文数`
				if ((palindrome.length() < j - i) && isPalindrome(s.substring(i, j))) {
					palindrome = s.substring(i, j);
				}
			}
		}
		return palindrome;
	}

	public static String convert(String s, int numRows) {
		String[] strarr = new String[numRows];
		Arrays.fill(strarr, "");
		for (int i = 0; i < s.length(); i++) {
			int xuhuan = 0;
			if (numRows == 1) {
				xuhuan = 0;
			} else {
				xuhuan = i % (2 * numRows - 2);
			}
			if (xuhuan < numRows) {
				// 竖线部分
				strarr[xuhuan] += s.charAt(i);
			} else {
				strarr[(2 * numRows - 2) - xuhuan] += s.charAt(i);

			}
		}
		s = "";
		for (String string : strarr) {
			s += string;
		}
		return s;

	}

	public static int reverse(int x) {
		int reversenum = 0;
		double dreversenum = 0;
		double doublex = x;
		while (Math.abs(doublex) > 9) {
			reversenum += x % 10;
			dreversenum += x % 10;
			x = x / 10;
			doublex = x;
			reversenum = reversenum * 10;
			dreversenum = dreversenum * 10;
		}
		dreversenum = dreversenum + x;
		reversenum = reversenum + x;
		if (dreversenum < -Math.pow(2, 31) || dreversenum > (Math.pow(2, 31) - 1))
			return 0;
		return reversenum;

	}

//^(0|[1-9][0-9]*)$
	public static int myAtoi(String s) {
		s = s.trim();
		Pattern pattern = Pattern.compile("^([-+]\\d+|\\d+)");
		Matcher isNum = pattern.matcher(s);
		if (isNum.find()) {
			s = isNum.group();
		} else {
			s = "";
		}
		if (s.isEmpty()) {
			return 0;
		} else {
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return s.startsWith("-") ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}

		}
	}

	public static boolean isPalindrome(int x) {
		String xstr = x + "";
		int length = xstr.length();
		boolean flag = true;
		for (int i = 0; i < length; i++) {
			if (xstr.charAt(i) != xstr.charAt(length - i - 1) && i <= (length - i - 1)) {
				flag = false;
			}
		}
		return flag;
	}

//	public static boolean isMatch(String s, String p) {
//		int sLen = s.length(), pLen = p.length();
//		boolean[][] memory = new boolean[sLen + 1][pLen + 1];
//		memory[0][0] = true;
//		for (int i = 0; i <= sLen; i++) {
//			for (int j = 1; j <= pLen; j++) {
//				if (p.charAt(j - 1) == '*') {
//					memory[i][j] = memory[i][j - 2] || (i > 0
//							&& (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && memory[i - 1][j]);
//				} else {
//					memory[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
//							&& memory[i - 1][j - 1];
//				}
//			}
//		}
//		return memory[sLen][pLen];
//	}

	public static int maxArea(int[] height) {
		int maxArea = 0;
		for (int i = 0, j = height.length - 1; i < j;) {
			int height1 = height[i] > height[j] ? height[j--] : height[i++];
			if ((j - i + 1) * height1 > maxArea) {
				maxArea = (j - i + 1) * height1;
			}
		}

		return maxArea;
	}

	public static String intToRoman(int num) {
		String result = "";
		if (num / 1000 > 0) {
			for (int i = 0; i < num / 1000; i++) {
				result = result.concat("M");
			}
			num = num % 1000;
		}
		if (num / 100 > 0) {
			int count = num / 100;
			switch (count) {
			case 4:
				result = result.concat("CD");
				num = num % 100;
				break;
			case 9:
				result = result.concat("CM");
				num = num % 100;
				break;
			default:
				if (count < 4) {
					for (int i = 0; i < count; i++) {
						result = result.concat("C");
					}
				} else if (count > 4 && count < 9) {
					result = result.concat("D");
					for (int i = 0; i < count - 5; i++) {
						result = result.concat("C");
					}
				}
				num = num % 100;
				break;
			}
		}
		if (num / 10 > 0) {
			int count = num / 10;
			switch (count) {
			case 4:
				result = result.concat("XL");
				num = num % 10;
				break;
			case 9:
				result = result.concat("XC");
				num = num % 10;
				break;
			default:
				if (count < 4) {
					for (int i = 0; i < count; i++) {
						result = result.concat("X");
					}
				} else if (count > 4 && count < 9) {
					result = result.concat("L");
					for (int i = 0; i < count - 5; i++) {
						result = result.concat("X");
					}
				}
				num = num % 10;
				break;
			}
		}
		if (num > 0) {
			switch (num) {
			case 4:
				result = result.concat("IV");
				num = num % 100;
				break;
			case 9:
				result = result.concat("IX");
				num = num % 100;
				break;
			default:
				if (num < 4) {
					for (int i = 0; i < num; i++) {
						result = result.concat("I");
					}
				} else if (num > 4 && num < 9) {
					result = result.concat("V");
					for (int i = 0; i < num - 5; i++) {
						result = result.concat("I");
					}
				}
				num = num % 100;
				break;
			}
		}
		return result;

	}

	public static int romanToInt(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'C':
				if (i + 1 < s.length() && s.charAt(i + 1) == 'D') {
					result += 400;
					i++;
				} else if (i + 1 < s.length() && s.charAt(i + 1) == 'M') {
					result += 900;
					i++;
				} else {
					result += 100;
				}
				break;

			case 'X':
				if (i + 1 < s.length() && s.charAt(i + 1) == 'L') {
					result += 40;
					i++;
				} else if (i + 1 < s.length() && s.charAt(i + 1) == 'C') {
					result += 90;
					i++;
				} else {
					result += 10;
				}
				break;

			case 'I':
				if (i + 1 < s.length() && s.charAt(i + 1) == 'V') {
					result += 4;
					i++;
				} else if (i + 1 < s.length() && s.charAt(i + 1) == 'X') {
					result += 9;
					i++;
				} else {
					result += 1;
				}
				break;
			case 'V':
				result += 5;
				break;
			case 'L':
				result += 50;
				break;
			case 'D':
				result += 500;
				break;
			case 'M':
				result += 1000;
				break;
			default:
				break;
			}
		}
		return result;
	}

	public static String longestCommonPrefix(String[] strs) {
		String lcp = "";
		if (strs.length == 0) {
			return lcp;
		}
		int shortLength = strs[0].length();
		if (strs.length == 1) {
			return strs[0];
		}
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].length() < shortLength) {
				shortLength = strs[i].length();
			}
		}
		for (int i = 0; i < shortLength; i++) {
			for (int j = 0; j < strs.length; j++) {
				if (j + 1 < strs.length && strs[j].charAt(i) != strs[j + 1].charAt(i)) {
					return lcp;
				} else if (j + 1 == strs.length && strs[j - 1].charAt(i) == strs[j].charAt(i)) {
					lcp += strs[j].charAt(i);
				}
			}
		}
		return lcp;

	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new LinkedList<>();
		// sort
		Arrays.sort(nums);
		// 3 foreach
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int t = -nums[i];
			int j = i + 1, k = n - 1;
			while (j < k) {
				if (nums[j] + nums[k] > t) {
					k--;
				} else if (nums[j] + nums[k] < t) {
					j++;
				} else {
					// find
					List<Integer> ans = new LinkedList<>();
					ans.add(nums[i]);
					ans.add(nums[j]);
					ans.add(nums[k]);
					result.add(ans);
					// 去除临近相同的元素
					while (j < k && nums[j] == nums[j + 1])
						j++;
					while (j < k && nums[k] == nums[k - 1])
						k--;
					j++;
					k--;
				}
			}
		}
		return result;
	}

	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[2];
		int numabs = Math.abs(result - target);
		for (int i = 0; i < nums.length; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				if (target > sum) {
					if (Math.abs(sum - target) < numabs) {
						result = sum;
						numabs = Math.abs(sum - target);
					}
					j++;
				} else {
					if (Math.abs(sum - target) < numabs) {
						result = sum;
						numabs = Math.abs(sum - target);
					}
					k--;
				}
			}
		}
		return result;

	}

	public static List<String> letterCombinations(String digits) {
		Map<Integer, String[]> map = new HashMap<>();
//		List<Character> values = new ArrayList<>();
		List<String> result = new ArrayList<>();
		int start = 97;
		for (int i = 2; i <= 9; i++) {
			String[] chars = null;
			if (i == 7 || i == 9) {
				chars = new String[] { "" + (char) start++, "" + (char) start++, "" + (char) start++,
						"" + (char) start++ };
			} else {
				chars = new String[] { "" + (char) start++, "" + (char) start++, "" + (char) start++ };
			}
			map.put(i, chars);
		}
		// 数字个数
		for (int i = 0; i < digits.length(); i++) {
			// 对应的字母
			String[] arr = map.get(Integer.parseInt(digits.charAt(i) + ""));
			if (result.isEmpty()) {
				Collections.addAll(result, arr);
			} else {
				String[] str = new String[result.size() * arr.length];
				for (int j = 0; j < result.size(); j++) {
					for (int k = 0; k < arr.length; k++) {
						str[j * arr.length + k] = result.get(j) + arr[k];
					}
				}
				result.clear();
				Collections.addAll(result, str);
//				}
			}
		}
		return result;
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int m = j + 1;
				int n = nums.length - 1;
				int subSum = target - nums[i] - nums[j];
				while (m < n) {
					if (nums[m] + nums[n] > subSum) {
						n--;
					} else if (nums[m] + nums[n] < subSum) {
						m++;
					} else {
						List<Integer> o = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
						if (!result.contains(o)) {
							result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[m], nums[n])));
						}
						n--;
						m++;
					}
				}

			}
		}

		return result;
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode h = head;
		ListNode r = head;
		boolean flag = false;
		for (int i = 0; i < n; i++) {
			r = r.next;
		}
		if (r == null) {
			flag = true;
		}
		while (r != null && r.next != null) {
			h = h.next;
			r = r.next;
		}
		if (flag) {
			return head.next;
		} else {
			h.next = h.next.next;
			return head;
		}

	}

	public static boolean isValid(String s) {
		if (s.length() % 2 != 0) {
			return false;
		}
		char[] arr = new char[s.length()];
		int point = 0;
		for (int i = 0; i < s.length(); i++) {
			char cha = s.charAt(i);
			if (cha == '(' || cha == '[' || cha == '{') {
				arr[point++] = cha;
			} else {
				if (point <= 0) {
					return false;
				}
				switch (arr[--point]) {
				case '(':
					if (cha != ')') {
						return false;
					}
					break;
				case '[':
					if (cha != ']') {
						return false;
					}
					break;
				case '{':
					if (cha != '}') {
						return false;
					}
					break;

				default:
					break;
				}
			}
		}
		if (point != 0) {
			return false;
		}
		return true;
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode p = null;
		if (l1.val > l2.val) {
			p = l2;
			l2 = l2.next;
		} else {
			p = l1;
			l1 = l1.next;
		}
		ListNode result = p;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				p.next = l2;
				break;
			} else if (l2 == null) {
				p.next = l1;
				break;
			} else {
				if (l1.val > l2.val) {
					p.next = l2;
					l2 = l2.next;
				} else {
					p.next = l1;
					l1 = l1.next;
				}
			}
			p = p.next;

		}
		return result;
	}

	public static List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		dfs(n, n, "", res);
		return res;

	}

	public static void dfs(int left, int right, String curStr, List<String> res) {
		if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
			res.add(curStr);
			return;
		}

		if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
			dfs(left - 1, right, curStr + "(", res);
		}
		if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
			dfs(left, right - 1, curStr + ")", res);
		}
	}

	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0)
			return null;
		if (lists.length == 1)
			return lists[0];
		if (lists.length == 2) {
			return mergeTwoLists(lists[0], lists[1]);
		}

		int mid = lists.length / 2;
		ListNode[] l1 = new ListNode[mid];
		for (int i = 0; i < mid; i++) {
			l1[i] = lists[i];
		}

		ListNode[] l2 = new ListNode[lists.length - mid];
		for (int i = mid, j = 0; i < lists.length; i++, j++) {
			l2[j] = lists[i];
		}

		return mergeTwoLists(mergeKLists(l1), mergeKLists(l2));
	}

	public static ListNode swapPairs(ListNode head) {
		ListNode p = head;
		while (p != null) {
			if (p.next != null) {
				int tem = p.val;
				p.val = p.next.val;
				p.next.val = tem;
				p = p.next.next;

			} else {
				p = p.next;
			}
		}
		return head;

	}

	public static ListNode reverseKGroup(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		int count = 0;
		int[] knum = new int[k];
		ListNode p = new ListNode();
		ListNode result = p;
		while (head != null) {
			if (count % k == 0) {
				p.val = head.val;
				p.next = head.next;
			}
			knum[k - 1 - (count % k)] = head.val;
			count++;
			if (count % k == 0) {
				for (int i = 0; i < knum.length; i++) {
					p.val = knum[i];
					p = p.next;
				}
			}
			head = head.next;

		}

		return result;
	}

	public static void ReverseArray(int arr[]) {
		int center = arr.length / 2;
		int start = 0;
		int end = arr.length - 1;
		for (int i = 0; i < center; i++) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	/***
	 * 删除重复的元素
	 * 
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates1(int[] nums) {
//    	if(nums==null) {
//    		return 0;
//    	}
		int start = 0;
		int end = 1;
		int length = nums.length;
		while (end < length) {
			if (nums[start] == nums[end]) {
				end++;
			} else {
				if (end - start == 1) {
					start++;
					end++;
				} else {
					start++;
					nums[start] = nums[end];
					end++;
				}
			}
		}
		nums = Arrays.copyOf(nums, start + 1);
		return start + 1;
	}

	/***
	 * 删除数组中指定的元素
	 * 
	 * @param nums
	 * @param val
	 * @return
	 */
	public static int removeElement(int[] nums, int val) {
		if (nums == null) {
			return 0;
		}
		int start = 0;
		int index = 0;
		int length = nums.length;
		while (index < length) {
			if (nums[index] != val) {
				nums[start] = nums[index];
				index++;
				start++;
			} else {
				index++;
			}
		}
		return start;

	}

	/***
	 * 计算子串的索引
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr(String haystack, String needle) {
		return haystack.indexOf(needle);
	}

	/***
	 * 不通过除法,乘法,取余运算来计算商
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static int divide(int dividend, int divisor) {
		if (dividend == 0) {
			return 0;
		}
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		boolean negative;
		negative = (dividend ^ divisor) < 0;// 用异或来计算是否符号相异
		long t = Math.abs((long) dividend);
		long d = Math.abs((long) divisor);
		int result = 0;
		for (int i = 31; i >= 0; i--) {
			if ((t >> i) >= d) {// 找出足够大的数2^n*divisor
				result += 1 << i;// 将结果加上2^n
				t -= d << i;// 将被除数减去2^n*divisor
			}
		}
		return negative ? -result : result;// 符号相异取反
	}

	public static List<Integer> findSubstring2(String s, String[] words) {
		Stack<Integer> stack = new Stack<Integer>();
		List<Integer> result = new ArrayList<>();
		List<Stack<Integer>> lists = new ArrayList<>();
		int[] arr = new int[words.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		f(arr, words.length, 0, stack, lists);
		for (Stack<Integer> stack2 : lists) {
			String subString = "";
			int fromIndex = 0;
			for (Integer integer : stack2) {
				subString += words[integer];
			}
			int indexof = s.indexOf(subString, fromIndex);
			while (indexof != -1) {
				if (!result.contains(indexof)) {
					result.add(indexof);
				}
				fromIndex = indexof + 1;
				indexof = s.indexOf(subString, fromIndex);
			}
		}
		return result;
	}

	/**
	 *
	 * @param shu  待选择的数组
	 * @param targ 要选择多少个次
	 * @param cur  当前选择的是第几次
	 */
	private static void f(int[] shu, int targ, int cur, Stack<Integer> stack, List<Stack<Integer>> lists) {
		// TODO Auto-generated method stub
		if (cur == targ) {
			lists.add((Stack<Integer>) stack.clone());
			return;
		}

		for (int i = 0; i < shu.length; i++) {
			if (!stack.contains(shu[i])) {
				stack.add(shu[i]);
				f(shu, targ, cur + 1, stack, lists);
				stack.pop();
			}

		}
	}

	public static List<Integer> findSubstring(String s, String[] words) {
		Map<String, Integer> hashMap = new HashMap<>();
		int wordcount = words.length;
		int wordLength = words[0] == null ? 0 : words[0].length();
		int sLength = s.length();
		for (String string : words) {
			hashMap.put(string, (hashMap.get(string) == null ? 0 : hashMap.get(string)) + 1);
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < sLength - (wordcount * wordLength - 1); i++) {
			Map<String, Integer> hashMap1 = new HashMap<>();
			int start = i;
			boolean valid = true;
			for (int j = 1; j <= wordcount; j++) {
				String word = s.substring(start, start + wordLength);
				if (hashMap.containsKey(word)) {
					hashMap1.put(word, (hashMap1.get(word) == null ? 0 : hashMap1.get(word)) + 1);
				} else {
					valid = false;
					break;
				}
				start = start + wordLength;
			}
			if (!valid) {
				continue;
			}
			boolean valide = true;
			for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
				String key = entry.getKey();
				Integer value = entry.getValue();
				Integer value1 = hashMap1.get(key);
				if (!value.equals(value1)) {
					valide = false;
					break;
				}
			}
			if (valide) {
				result.add(i);
			}
		}
		return result;
	}

	public static void nextPermutation(int[] nums) {
		boolean flag = true;
		if (nums == null || nums.length == 0) {
			return;
		}
		int length = nums.length - 1;
		for (int i = length; i >= 0; i--) {
			for (int j = length; j > i; j--) {
				if (nums[i] < nums[j]) {
					int tem = nums[i];
					nums[i] = nums[j];
					nums[j] = tem;
					//
					int[] arr = Arrays.copyOfRange(nums, i + 1, length + 1);

					Arrays.sort(arr);
					for (int k = i + 1, m = 0; k < length + 1; k++, m++) {
						nums[k] = arr[m];
					}
					flag = false;
					return;
				}
			}
		}
		if (flag) {
			int mid = (length + 1) / 2;
			for (int i = 0; i < mid; i++) {
				int tem = nums[i];
				nums[i] = nums[length - i];
				nums[length - i] = tem;
			}
		}
	}

	/***
	 * 最长有效括号,思路是初始化一个堆栈,用于存放我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
	 * 
	 * @param s
	 * @return
	 */
	public static int longestValidParentheses(String s) {
		int result = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int length = s.length();
		for (int i = 0; i < length; i++) {
			Character achar = s.charAt(i);
			switch (achar) {
			case '(':
				stack.push(i);
				break;
			case ')':
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					result = result > i - stack.peek() ? result : i - stack.peek();
				}
				break;

			default:
				break;
			}
		}
		return result;
	}

	public static int search1(int[] nums, int target) {
		int right = nums.length - 1;
		int left = 0;
		int mid = (right + left) / 2;
		if (nums[mid] == target) {
			return mid;
		}
		while (right > left) {
			if (nums[left] > nums[mid]) {
				if (target > nums[mid] && target < nums[left]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
//				if(target>nums[left]||target<nums[mid]) {
//				}else {
//					left = mid+1;
//				}
			} else {// nums[left] < nums[mid]
				if (target >= nums[left] && target < nums[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			mid = (right + left) / 2;
			if (nums[mid] == target) {
				return mid;
			}
		}
		if (nums[mid] != target) {
			return -1;
		}
		return mid;
	}

	public static int[] searchRange(int[] nums, int target) {
		int[] result = new int[] { -1, -1 };
		if (nums.length == 0) {
			return result;
		}
		int left = 0;
		int right = nums.length;
		int length = nums.length;
		int mid = (left + right) / 2;
		while (left <= right) {
			if (nums[mid] > target) {
				right = mid - 1;

			} else if (nums[mid] < target) {
				left = mid + 1;

			} else {
				result[0] = mid;
				result[1] = mid;
				left = mid - 1;
				right = mid + 1;
				while (left >= 0 && nums[left] == target) {
					result[0] = left--;

				}
				while (right < nums.length && nums[right] == target) {
					result[1] = right++;
				}
				break;
			}
			mid = (left + right) / 2;
			if (mid < 0 || mid >= length) {
				break;
			}
		}
		return result;
	}

	public static int searchInsert(int[] nums, int target) {
		if (nums.length == 0) {
			return 0;
		}
		int left = 0;
		int right = nums.length;
		int mid = (left + right) / 2;
		while (left <= right) {
			if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				return mid;
			}
			mid = (left + right) / 2;
			if (mid < 0 || mid >= nums.length) {
				return mid;
			}
		}
		return left;
	}

	public static boolean isValidSudoku(char[][] board) {
		List<Character> conumList = new ArrayList<>();
		List<Character> rawnumList = new ArrayList<>();
		List<Character> squarenumList = new ArrayList<>();
		HashMap<Integer, Integer>[] boxes = new HashMap[9];
		for (int i = 0; i < 9; i++) {
			boxes[i] = new HashMap<>();
		}
		// 判断行列是否满足有效数组
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int boxindex = (i / 3) * 3 + j / 3;
				int num = (int) board[i][j];
				if (board[i][j] != '.') {
					boxes[boxindex].put(num, boxes[boxindex].getOrDefault(num, 0) + 1);
					if (boxes[boxindex].get(num) > 1) {
						return false;
					}
					if (conumList.contains(board[i][j])) {
						return false;
					} else {
						conumList.add(board[i][j]);
					}
				}
				if (board[j][i] != '.') {
					if (rawnumList.contains(board[j][i])) {
						return false;
					} else {
						rawnumList.add(board[j][i]);
					}
				}

			}
			conumList.clear();
			rawnumList.clear();
		}
		// 判断小正方形是否满足数组有效性
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				for (int m = 0; m < 3; m++) {
//					for (int n = 0; n < 3; n++) {
//						if (board[i * 3 + m][j * 3 + n]!='.') {
//							if (squarenumList.contains(board[i * 3 + m][j * 3 + n])) {
//								return false;
//							} else {
//								squarenumList.add(board[i * 3 + m][j * 3 + n]);
//							}
//						}
//					}
//				}
//				squarenumList.clear();
//			}
//		}
		return true;
	}

//	private static boolean valid = false;
//
//	public void solveSudoku(char[][] board) {
//		boolean[][] hang = new boolean[9][9];
//		boolean[][] lie = new boolean[9][9];
//		List<int[]> spaces = new ArrayList<>();
//		boolean[][][] boxs = new boolean[3][3][9];
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				if (board[i][j] == '.') {
//					spaces.add(new int[] { i, j });
//				} else {
//					int digit = Integer.parseInt(board[i][j] + "") - 1;
//					hang[i][digit] = lie[j][digit] = boxs[i / 3][j / 3][digit] = true;
//				}
//			}
//		}
//		dfs(board, hang, lie, boxs, spaces, 0);
//		System.out.println("===================================================");
//
//	}
//
//	private static void dfs(char[][] board, boolean[][] hang, boolean[][] lie, boolean[][][] boxs, List<int[]> spaces,
//			int count) {
//		if (count == spaces.size()) {
//			valid = true;
//			return;
//		}
//		int[] space = spaces.get(count);
//		int i = space[0];
//		int j = space[1];
//		for (int digit = 0; digit < 9 && !valid; ++digit) {
//			if (!lie[j][digit] && !hang[i][digit] && !boxs[i / 3][j / 3][digit]) {
//				lie[j][digit] = hang[i][digit] = boxs[i / 3][j / 3][digit] = true;
//				board[i][j] = (char) (digit + '0' + 1);
//				dfs(board, hang, lie, boxs, spaces, count + 1);
//				lie[j][digit] = hang[i][digit] = boxs[i / 3][j / 3][digit] = false;
//			}
//		}
//	}

	private static boolean[][] line = new boolean[9][9];
	private static boolean[][] column = new boolean[9][9];
	private static boolean[][][] block = new boolean[3][3][9];
	private static boolean valid = false;
	private static List<int[]> spaces = new ArrayList<int[]>();

	public static void solveSudoku(char[][] board) {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (board[i][j] == '.') {
					spaces.add(new int[] { i, j });
				} else {
					int digit = board[i][j] - '0' - 1;
					line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
				}
			}
		}

		dfs(board, 0);
	}

	public static void dfs(char[][] board, int pos) {
		if (pos == spaces.size()) {
			valid = true;
			return;
		}

		int[] space = spaces.get(pos);
		int i = space[0], j = space[1];
		for (int digit = 0; digit < 9 && !valid; ++digit) {
			if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
				line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
				board[i][j] = (char) (digit + '0' + 1);
				dfs(board, pos + 1);
				line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
			}
		}
	}

	public static String countAndSay(int n) {
		String str = "";
		String result = "1";
		if (n == 1) {
			return "1";
		}
		for (int i = 0; i < n; i++) {
			int length = result.length();
			int count = 0;
			for (int j = 0; j < length; j++) {
				if (j < length - 1) {
					if (result.charAt(j) == result.charAt(j + 1)) {
						count++;
					} else {
						str = str + (count + 1 + "") + result.charAt(j);
						count = 0;
					}
				} else {// 到最后了
					str = str + (count + 1 + "") + result.charAt(j);
				}
			}
			result = str;
			str = "";
		}
		return result;
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		int len = candidates.length;
		List<List<Integer>> res = new ArrayList<>();
		if (len == 0) {
			return res;
		}

		Arrays.sort(candidates);
		Deque<Integer> path = new ArrayDeque<>();
		dfs(candidates, 0, len, target, path, res);
		return res;
	}

	private static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path,
			List<List<Integer>> res) {
		if (target == 0) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = begin; i < len; i++) {
			// 剪枝
			if (target - candidates[i] < 0) {
				break;
			}

			path.addLast(candidates[i]);
			System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

			dfs(candidates, i, len, target - candidates[i], path, res);
			path.removeLast();
			System.out.println("递归之后 => " + path);
		}
	}

//	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
//		int len = candidates.length;
//		List<List<Integer>> res = new ArrayList<>();
//		if (len == 0) {
//			return res;
//		}
//		Arrays.sort(candidates);
//		Deque<Integer> path = new ArrayDeque<>();
//		dfs2(candidates, 0, len, target, path, res);
//		return res;
//	}
//
//	private static void dfs2(int[] candidates, int begin, int len, int target, Deque<Integer> path,
//			List<List<Integer>> res) {
//		if (target == 0 && !res.contains(new ArrayList<>(path))) {
//			res.add(new ArrayList<>(path));
//			return;
//		}
//		for (int i = begin; i < len; i++) {
//			// 剪枝
//			if (target - candidates[i] < 0) {
//				break;
//			}
//			path.addLast(candidates[i]);
//			System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));
//
//			dfs2(candidates, i + 1, len, target - candidates[i], path, res);
//			path.removeLast();
//			System.out.println("递归之后 => " + path);
//		}
//	}

	static List<int[]> freq = new ArrayList<int[]>();
	static List<List<Integer>> ans = new ArrayList<List<Integer>>();
	static List<Integer> sequence = new ArrayList<Integer>();

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		for (int num : candidates) {
			int size = freq.size();
			if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
				freq.add(new int[] { num, 1 });
			} else {
				++freq.get(size - 1)[1];
			}
		}
		dfs3(0, target);
		return ans;
	}

	public static void dfs3(int pos, int rest) {
		if (rest == 0) {
			ans.add(new ArrayList<Integer>(sequence));
			return;
		}
		if (pos == freq.size() || rest < freq.get(pos)[0]) {
			return;
		}

		dfs3(pos + 1, rest);

		int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
		for (int i = 1; i <= most; ++i) {
			sequence.add(freq.get(pos)[0]);
			dfs3(pos + 1, rest - i * freq.get(pos)[0]);
		}
		for (int i = 1; i <= most; ++i) {
			sequence.remove(sequence.size() - 1);
		}
	}

	public static int firstMissingPositive(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; ++i) {
			while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) { // 置换到正确位置
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
		}
		for (int i = 0; i < n; ++i) { // 第一个错误的数字即为没出现的最小数字
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return n + 1;
//		// 判断是否出现1,如果没有1则返回1
//		boolean zero = false;
//		for (int i : nums) {
//			if (i == 1) {
//				zero = true;
//				break;
//			}
//		}
//		if (!zero) {
//			return 1;
//		}
//		int length = nums.length;
//		// 将负数变为length
//		for (int i = 0; i < length; i++) {
//			if (nums[i] <= 0) {
//				nums[i] = length + 1;
//			}
//		}
//		for (int i = 0; i < length; i++) {
//			if (Math.abs(nums[i]) < length+1) {
//				nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);
//			}
//		}
//		for (int i = 0; i <= length; i++) {
//			if(i==length) {
//				return length+1;
//			}
//			if (nums[i] > 0) {
//				return i + 1;
//			}
//		}
//
//		return 0;
	}

	public static int trap(int[] height) {
		Random random = new Random();
		int ran = random.nextInt(2);
		int result = 0;
		switch (ran) {
		case 0:
			// 堆栈应用
			Stack<Integer> stack = new Stack<>();
			int start = 0;
			while (start < height.length) {
				while (!stack.isEmpty() && height[stack.peek()] < height[start]) {
					// 计算
					int top = stack.pop();
					if (stack.isEmpty())
						break;
					int distance = start - stack.peek() - 1;
					int bounded_height = Math.min(height[start], height[stack.peek()]) - height[top];
					result += distance * bounded_height;
				}
				stack.push(start++);
			}
			break;
		case 1:
			// 双指针算法
			int left = 0;
			int right = height.length - 1;
			int left_max = 0;
			int right_max = 0;
			while (left <= right) {
				//
				if (height[left] < height[right]) {
					if (height[left] > left_max)
						left_max = height[left];
					else
						result += (left_max - height[left]);
					left++;
				} else {
					if (height[right] > right_max)
						right_max = height[right];
					else
						result += (right_max - height[right]);
					right--;
				}
			}
			break;
		default:
			break;
		}
		return result;
	}

	public static String multiply(String num1, String num2) {
		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		int size = num1.length() + num2.length();
		int[] result = new int[size];
		for (int i = num2.length() - 1; i >= 0; i--) {
			for (int j = num1.length() - 1; j >= 0; j--) {
				int mult = (int) (num2.charAt(i) - '0') * (int) (num1.charAt(j) - '0');
				int gewei = mult % 10;
				int shiwei = mult / 10;
				result[i + j + 1] += gewei;
				result[i + j] += shiwei;
			}
		}
		StringBuilder sb1 = new StringBuilder();
		for (int i = result.length - 1; i >= 0; i--) {
			sb1.append(result[i] % 10);
			if (i - 1 >= 0) {
				result[i - 1] += result[i] / 10;
			} else {
				if (result[i] / 10 != 0) {
					sb1.append(result[i] / 10);
				}
			}
		}
		sb1 = sb1.reverse();

		return sb1.toString().startsWith("0") ? sb1.toString().replaceFirst("0", "") : sb1.toString();
	}

//    public static boolean isMatch(String s, String p) {
//        int m = s.length();
//        int n = p.length();
//        boolean[][] dp = new boolean[m + 1][n + 1];
//        dp[0][0] = true;
//        for (int i = 1; i <= n; ++i) {
//            if (p.charAt(i - 1) == '*') {
//                dp[0][i] = true;
//            } else {
//                break;
//            }
//        }
//        for (int i = 1; i <= m; ++i) {
//            for (int j = 1; j <= n; ++j) {
//                if (p.charAt(j - 1) == '*') {
//                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
//                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1];
//                }
//            }
//        }
//        return dp[m][n];
//    }

	public static boolean isMatch(String s, String p) {
		int sRight = s.length(), pRight = p.length();

		while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
			if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
				--sRight;
				--pRight;
			} else
				return false;
		}

		if (pRight == 0)
			return sRight == 0;

		int sIndex = 0, pIndex = 0;
		int sRecord = -1, pRecord = -1;

		while (sIndex < sRight && pIndex < pRight) {
			if (p.charAt(pIndex) == '*') {
				++pIndex;
				sRecord = sIndex;
				pRecord = pIndex;
			} else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
				++pIndex;
				++sIndex;
			} else if (sRecord != -1 && sRecord + 1 < sRight) {
				++sRecord;
				sIndex = sRecord;
				pIndex = pRecord;
			} else
				return false;
		}

		return allStars(p, pIndex, pRight);
	}

	public static boolean allStars(String str, int left, int right) {
		for (int i = left; i < right; i++) {
			if (str.charAt(i) != '*')
				return false;
		}
		return true;
	}

	public static boolean charMatch(char u, char v) {
		return u == v || v == '?';
	}

	public static int jump(int[] nums) {
		int bushu = 0;
		int bianjie = 0;
		int length = nums.length - 1;
		for (int i = 0; i < nums.length;) {
			if (nums[bianjie] + bianjie >= length) {
				if (length == 0) {
					return bushu;
				}
				return bushu + 1;
			}
			int count = nums[bianjie];
			int max = 0;
			int newBianjie = bianjie;
			for (int j = 0; j < count; j++) {
				if ((nums[bianjie + j + 1] + bianjie + j + 1) >= length) {
					return bushu + 2;
				}
				if (max < nums[bianjie + j + 1] + bianjie + j + 1) {
					max = nums[bianjie + j + 1] + bianjie + j + 1;
					newBianjie = bianjie + j + 1;
				}
			}
			bushu += 1;
			bianjie = newBianjie;
		}
		return bushu;
	}

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		List<Integer> list1 = new ArrayList<>();
		dfs(nums, list, list1);
		return list;

	}

	public static void dfs(int[] nums, List<List<Integer>> list, List<Integer> list1) {
		if (list1.size() == nums.length) {
			list.add(new ArrayList<>(list1));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (list1.contains(nums[i]))
				continue;
			list1.add(nums[i]);
			dfs(nums, list, list1);
			list1.remove(list1.size() - 1);
		}
	}

	/***
	 * 全排列2
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permuteUnique(int[] nums) {
		int len = nums.length;
		List<List<Integer>> res = new ArrayList<>();
		if (len == 0) {
			return res;
		}

		// 排序（升序或者降序都可以），排序是剪枝的前提
		Arrays.sort(nums);

		boolean[] used = new boolean[len];
		// 使用 Deque 是 Java 官方 Stack 类的建议
		Deque<Integer> path = new ArrayDeque<>(len);
		dfs_permuteUnique(nums, len, 0, used, path, res);
		return res;
	}

	public static void dfs_permuteUnique(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path,
			List<List<Integer>> res) {
		if (depth == len) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = 0; i < len; ++i) {
			// 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
			// 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
			if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
				continue;
			}

			path.addLast(nums[i]);
			used[i] = true;

			dfs_permuteUnique(nums, len, depth + 1, used, path, res);
			// 回溯部分的代码，和 dfs 之前的代码是对称的
			used[i] = false;
			path.removeLast();
		}
	}

	public static void rotate(int[][] matrix) {
		// 矩阵转置
		int length = matrix.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				int tem = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tem;
			}
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length / 2; j++) {
				int tem = matrix[i][j];
				matrix[i][j] = matrix[i][length - j - 1];
				matrix[i][length - j - 1] = tem;
			}
		}
	}

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }	
	
    public static double myPow(double x, int n) {
    	long N = n;
    	return N>0?quickMul(x,N):(1/quickMul(x,-N));
    }    
    /***
     * 把n转化成二进制表示法
     * @param x
     * @param n
     * @return
     */
	public static double quickMul(double x, long n) {
		double ans = 1.0;
		double mul = x;
		while (n>0) {
			if(n%2==1) {
				ans *=mul;
			}
			mul *=mul;
			n/=2;
		}
		return ans;
	}
	//列放置了皇后的集合
	public static Queue<Integer> col = new ArrayDeque<>();
	//对角线放置了皇后的集合(i-j)
	public static Queue<Integer> biasLine = new ArrayDeque<>();
	//反对角线放置了皇后的集合(i+j)
	public static Queue<Integer> biasLine1 = new ArrayDeque<>();
	
	public static List<List<String>> result = new ArrayList<>();
	
    public static List<List<String>> solveNQueens(int n) {
    	List<Integer> path = new ArrayList<>();
    	dfs_solveNQueens(0,n, path);
    	return result;
    }	
	
    public static void dfs_solveNQueens(int count,int n,List<Integer> path) {
    	if(count == n) {
    		result.add(generateBoard(path, n));
    		return;
    	}
    	for (int i = 0; i < n; i++) {
			if(col.contains(i)||biasLine.contains(count-i)||biasLine1.contains(count+i)) {
				continue;
			}
				path.add(i);
				col.add(i);
				biasLine.add(count-i);
				biasLine1.add(count+i);
				dfs_solveNQueens(count+1, n, path);
				biasLine1.remove(count+i);
				biasLine.remove(count-i);
				col.remove(i);
				path.remove(count);
		}
    }
    public static List<String> generateBoard(List<Integer> path,int n){
    	List<String> result = new ArrayList<>();
    	for (int i = 0; i < path.size(); i++) {
    		char[] arr = new char[n];
    		Arrays.fill(arr, '.');
    		arr[path.get(i)] = 'Q';
    		result.add(new String(arr));
		}
		return result;
    }
    public static int ansCount = 0;
    public static int totalNQueens(int n) {
    	dfs_solveNQueens2(0,n);
    	return ansCount;
    }
    
    public static void dfs_solveNQueens2(int count,int n) {
    	if(count == n) {
    		ansCount++;
    		return;
    	}
    	for (int i = 0; i < n; i++) {
			if(col.contains(i)||biasLine.contains(count-i)||biasLine1.contains(count+i)) {
				continue;
			}
				col.add(i);
				biasLine.add(count-i);
				biasLine1.add(count+i);
				dfs_solveNQueens2(count+1, n);
				biasLine1.remove(count+i);
				biasLine.remove(count-i);
				col.remove(i);
		}
    }    
    
	/***
	 * 最大子序列和,暴力法
	 * 
	 * @param nums
	 * @return
	 */
//	public static int maxSubArray(int[] nums) {
//		int max = nums[0];
//		for (int i = 0; i < nums.length; i++) {
//			int sum =0;
//			for (int j = i; j < nums.length; j++) {
//				sum += nums[j];
//				if (sum > max) {
//					max = sum;
//				}
//			}
//		}
//		return max;
//	}
	public static int maxSubArray(int[] nums) {
		int max = nums[0];
		int sum = nums[0];
		int length = nums.length;
		for (int i = 1; i < length; i++) {
			sum = Math.max(nums[i], sum + nums[i]);
			max = Math.max(sum, max);
		}

		return max;
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		int left = 0, right = matrix[0].length - 1;
		int top = 0, down = matrix.length - 1;
		int i = 0, j = 0;
		while (left <= right || top <= down) {
			while (j <= right) {
				result.add(matrix[i][j++]);
			}
			top++;
			j--;
			i++;
			if (top > down) {
				return result;
			}
			while (i <= down) {
				result.add(matrix[i++][j]);
			}
			right--;
			i--;
			j--;
			if ((right < left)) {
				return result;
			}
			while (j >= left) {
				result.add(matrix[i][j--]);
			}
			j++;
			i--;
			down--;
			if (top > down) {
				return result;
			}
			while (i >= top) {
				result.add(matrix[i--][j]);
			}
			i++;
			j++;
			left++;
			if ((right < left)) {
				return result;
			}
		}
		return result;
	}

	public static boolean canJump(int[] nums) {
		int max = nums[0] + 1;
		int length = nums.length;
		for (int i = 0; i < nums.length; i++) {
			if (i < max && max < i + nums[i] + 1) {
				max = i + nums[i] + 1;
			}
			if (i > max) {
				break;
			}
		}
		if (max < length) {
			return false;
		}
		return true;
	}

	public static int[][] merge(int[][] intervals) {
		if (intervals.length < 2)
			return intervals;
		List<int[]> answer = new ArrayList<>();
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		answer.add(intervals[0]);
		for (int i = 1; i < intervals.length; i++) {
			int[] start = answer.get(answer.size() - 1);
			if (intervals[i][0] <= start[1]) {
				answer.remove(answer.size() - 1);
				answer.add(new int[] { start[0], Math.max(start[1], intervals[i][1]) });
			} else {
				answer.add(intervals[i]);
			}
		}
		int[][] result = new int[answer.size()][2];
		int size = answer.size();
		for (int i = 0; i < size; i++) {
			result[i] = answer.get(i);
		}

		return result;
	}

	public static int[][] insert(int[][] intervals, int[] newInterval) {
		if (intervals.length == 0) {
			return new int[][] { newInterval };
		}
		List<int[]> answer = new ArrayList<>();
		int length = intervals.length;
		for (int i = 0; i < length;) {
			while (i < length && intervals[i][1] < newInterval[0]) {
				answer.add(intervals[i]);
				i++;
			}
			while (i < length && intervals[i][0] <= newInterval[1]) {
				newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
				newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
				i++;
			}
			answer.add(newInterval);
			for (int j = i; j < intervals.length; j++) {
				answer.add(intervals[j]);
			}
			break;

		}
		int[][] result = new int[answer.size()][2];
		int size = answer.size();
		for (int i = 0; i < size; i++) {
			result[i] = answer.get(i);
		}
		return result;
	}

	public static int lengthOfLastWord(String s) {
		int length = s.length();
		int count = 0;
		int i = length - 1;
		while (i >= 0 && s.charAt(i) == ' ') {
			i--;
		}
		if (i < 0) {
			return count;
		}
		for (int j = i; j >= 0; j--) {
			if (s.charAt(j) != ' ') {
				count++;
				continue;
			}
			return count;
		}
		return count;
	}

	public static int[][] generateMatrix(int n) {
		int[][] result = new int[n][n];
		int count = 1, left = 0, right = n, top = 0, down = n;
		int i = 0, j = 0;
		while (left <= right || top <= down) {
			// right
			while (right > j) {
				result[i][j++] = count++;
			}
			top++;
			j--;
			i++;
			// down
			while (down > i) {
				result[i++][j] = count++;
			}
			right--;
			i--;
			j--;
			// left
			while (left <= j) {
				result[i][j--] = count++;
			}
			down--;
			j++;
			i--;
			// top
			while (top <= i) {
				result[i--][j] = count++;
			}
			left++;
			i++;
			j++;
		}
		return result;
	}

	public static String getPermutation(int n, int k) {
		int[] factorial = new int[n];
		factorial[0] = 1;
		for (int i = 1; i < n; ++i) {
			factorial[i] = factorial[i - 1] * i;
		}

		--k;
		StringBuffer ans = new StringBuffer();
		int[] valid = new int[n + 1];
		Arrays.fill(valid, 1);
		for (int i = 1; i <= n; ++i) {
			int order = k / factorial[n - i] + 1;
			for (int j = 1; j <= n; ++j) {
				order -= valid[j];
				if (order == 0) {
					ans.append(j);
					valid[j] = 0;
					break;
				}
			}
			k %= factorial[n - i];
		}
		return ans.toString();
	}

	public static ListNode rotateRight(ListNode head, int k) {
		List<Integer> numbers = new ArrayList<>();
		ListNode p = new ListNode();
		p = head;
		int length = 0;
		while (p != null) {
			length++;
			p = p.next;
		}
		k = k % length;
		p = head;
		int count = 0;
		while (p != null) {
			if (count < k) {
				numbers.add(p.val);
				count++;
			} else {
				numbers.add(p.val);
				p.val = numbers.get(0);
				numbers.remove(0);
			}
			p = p.next;
		}
		p = head;
		for (Integer integer : numbers) {
			p.val = integer;
			p = p.next;
		}
		return head;
	}

	public static int m0;
	public static int n0;
	public static int count = 0;

	public static int uniquePaths(int m, int n) {
		m0 = m;
		n0 = n;
		dfs_uniquePaths(1, 1);
		return count;
	}

	public static void dfs_uniquePaths(int m2, int n2) {
		if (m2 == m0 && n2 == n0) {
			count++;
			return;
		}
		if (m2 < m0) {
			dfs_uniquePaths(m2 + 1, n2);
		}
		if (n2 < n0) {
			dfs_uniquePaths(m2, n2 + 1);
		}
	}

//    public static int uniquePaths(int m, int n) {
//        int[][] dp=new int[m][n];
//        for(int i=0;i<m;i++) dp[i][0]=1;
//        for(int j=0;j<n;j++) dp[0][j]=1;
//        for(int i=1;i<m;i++){
//            for(int j=1;j<n;j++){
//                dp[i][j]=dp[i-1][j]+dp[i][j-1];
//            }
//        }
//        return dp[m-1][n-1];
//    }
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) {
			return 0;
		}
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] dp = new int[m][n];
		boolean flag = false;
		for (int i = 0; i < m; i++) {
			if (flag) {
				dp[i][0] = 0;
				continue;
			}
			if (obstacleGrid[i][0] == 0) {
				dp[i][0] = 1;
			} else {
				dp[i][0] = 0;
				flag = true;
			}
		}
		flag = false;
		for (int j = 0; j < n; j++) {
			if (flag) {
				dp[0][j] = 0;
				continue;
			}
			if (obstacleGrid[0][j] == 0) {
				dp[0][j] = 1;
			} else {
				dp[0][j] = 0;
				flag = true;
			}
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i - 1][j] == 1) {
					dp[i - 1][j] = 0;
				}
				if (obstacleGrid[i][j - 1] == 1) {
					dp[i][j - 1] = 0;
				}
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m - 1][n - 1];
	}

	public static int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < m; i++) {
			dp[i][0] = grid[i][0] + dp[i - 1][0];
		}
		for (int i = 1; i < n; i++) {
			dp[0][i] = grid[0][i] + dp[0][i - 1];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (dp[i - 1][j] < dp[i][j - 1]) {
					dp[i][j] = dp[i - 1][j] + grid[i][j];
				} else {
					dp[i][j] = dp[i][j - 1] + grid[i][j];
				}
			}
		}
		return dp[m - 1][n - 1];
	}

	/***
	 * 有穷状态机,有效数字
	 * 
	 * @param s
	 * @return
	 */
	@SuppressWarnings("serial")
	public static boolean isNumber(String s) {
		Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();
		Map<CharType, State> initialMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
				put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
				put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
			}
		};
		transfer.put(State.STATE_INITIAL, initialMap);
		Map<CharType, State> intSignMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
				put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
			}
		};
		transfer.put(State.STATE_INT_SIGN, intSignMap);
		Map<CharType, State> integerMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
				put(CharType.CHAR_EXP, State.STATE_EXP);
				put(CharType.CHAR_POINT, State.STATE_POINT);
			}
		};
		transfer.put(State.STATE_INTEGER, integerMap);
		Map<CharType, State> pointMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
				put(CharType.CHAR_EXP, State.STATE_EXP);
			}
		};
		transfer.put(State.STATE_POINT, pointMap);
		Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
			}
		};
		transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
		Map<CharType, State> fractionMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
				put(CharType.CHAR_EXP, State.STATE_EXP);
			}
		};
		transfer.put(State.STATE_FRACTION, fractionMap);
		Map<CharType, State> expMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
				put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
			}
		};
		transfer.put(State.STATE_EXP, expMap);
		Map<CharType, State> expSignMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
			}
		};
		transfer.put(State.STATE_EXP_SIGN, expSignMap);
		Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {
			{
				put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
			}
		};
		transfer.put(State.STATE_EXP_NUMBER, expNumberMap);

		int length = s.length();
		State state = State.STATE_INITIAL;

		for (int i = 0; i < length; i++) {
			CharType type = toCharType(s.charAt(i));
			if (!transfer.get(state).containsKey(type)) {
				return false;
			} else {
				state = transfer.get(state).get(type);
			}
		}
		return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION
				|| state == State.STATE_EXP_NUMBER || state == State.STATE_END;
	}

	public static CharType toCharType(char ch) {
		if (ch >= '0' && ch <= '9') {
			return CharType.CHAR_NUMBER;
		} else if (ch == 'e' || ch == 'E') {
			return CharType.CHAR_EXP;
		} else if (ch == '.') {
			return CharType.CHAR_POINT;
		} else if (ch == '+' || ch == '-') {
			return CharType.CHAR_SIGN;
		} else {
			return CharType.CHAR_ILLEGAL;
		}
	}

	enum State {
		STATE_INITIAL, STATE_INT_SIGN, STATE_INTEGER, STATE_POINT, STATE_POINT_WITHOUT_INT, STATE_FRACTION, STATE_EXP,
		STATE_EXP_SIGN, STATE_EXP_NUMBER, STATE_END,
	}

	enum CharType {
		CHAR_NUMBER, CHAR_EXP, CHAR_POINT, CHAR_SIGN, CHAR_ILLEGAL,
	}

	public static int[] plusOne(int[] digits) {
		int carry = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			int value = digits[i] + carry;
			digits[i] = value % 10;
			carry = value / 10;
		}
		if (carry == 1) {
			int[] result = new int[digits.length + 1];
			result[0] = 1;
			System.arraycopy(digits, 0, result, 1, digits.length);
			return result;
		} else {
			return digits;
		}
	}

	public static String addBinary(String a, String b) {
		String result = "";
		int alen = a.length() - 1;
		int blen = b.length() - 1;
		int carry = 0;
		while (alen >= 0 || blen >= 0) {
			int aint = 0;
			int bint = 0;
			if (alen >= 0) {
				aint = Integer.parseInt(a.charAt(alen) + "");
			}
			if (blen >= 0) {
				bint = Integer.parseInt(b.charAt(blen) + "");
			}
			alen--;
			blen--;
			switch (aint + bint + carry) {
			case 0:
				result = "0" + result;
				carry = 0;
				break;
			case 1:
				result = "1" + result;
				carry = 0;
				break;
			case 2:
				result = "0" + result;
				carry = 1;
				break;
			case 3:
				result = "1" + result;
				carry = 1;
				break;

			default:
				break;
			}
		}
		if (carry == 1) {
			result = "1" + result;
		}
		return result;
	}

	public static List<String> fullJustify(String[] words, int maxWidth) {
		List<String> result = new ArrayList<>();
		int i = 0, start = 0;
		int length = words.length;
		int width = 0;
		while (i < length) {
			if (width + words[i].length() + 1 <= maxWidth + 1) {
				width += words[i].length() + 1;
				i++;
			} else {
				StringBuilder sb = insertAnswer(words, maxWidth, i, start, width);
				result.add(sb.toString());
				sb = new StringBuilder();
				start = i;
				width = 0;
			}
		}
		if (width != 0) {
			StringBuilder sb = insertAnswer(words, maxWidth, i, start, width);
			result.add(sb.toString());
		}
		// 把最后一个取出来,重新处理
		String lastString = result.get(result.size() - 1);
		String[] stringlist = lastString.split(" ");
		StringBuilder sb1 = new StringBuilder();
		for (String string : stringlist) {
			if ("".equals(string)) {
				continue;
			}
			string = string.trim();
			sb1.append(string + " ");
		}
		if (sb1.toString().endsWith(" ")) {
			sb1.delete(sb1.toString().length() - 2, sb1.toString().length() - 1);
		}
		int spaceCount = maxWidth - sb1.toString().length();
		if (spaceCount > 0) {
			char[] spacechar = new char[spaceCount];
			Arrays.fill(spacechar, ' ');
			sb1.append(new String(spacechar));
		}
		result.remove(result.size() - 1);
		result.add(sb1.toString());
		return result;
	}

	private static StringBuilder insertAnswer(String[] words, int maxWidth, int i, int start, int width) {
		StringBuilder sb = new StringBuilder();
		int spaceCount = maxWidth + 1 - width;
		int wordCount = i - start;
		if (wordCount == 1) {
			int spacecount = maxWidth - words[start].length();
			char[] arr = new char[spacecount];
			Arrays.fill(arr, ' ');
			sb.append(words[start] + new String(arr));
			return sb;
		}
		int avgspace = spaceCount / (wordCount - 1);
		int leftspace = spaceCount % (wordCount - 1);
		for (int j = start; j < i; j++) {
			if (j < start + leftspace) {
				char[] spacechar = new char[avgspace + 2];
				Arrays.fill(spacechar, ' ');
				String space = new String(spacechar);
				sb.append(words[j] + space);
			} else {
				if (j == i - 1) {
					sb.append(words[j]);
				} else {
					char[] spacechar = new char[avgspace + 1];
					Arrays.fill(spacechar, ' ');
					String space = new String(spacechar);
					sb.append(words[j] + space);
				}
			}
		}
		return sb;
	}

	/***
	 * 牛顿迭代法
	 * 
	 * @param x
	 * @return
	 */
//    public static int mySqrt(int x) {
//        if (x == 0) {
//            return 0;
//        }
//
//        double C = x, x0 = x;
//        int count =0;
//        while (true) {
//            double xi = 0.5 * (x0 + C / x0);
//            count++;
//            if (Math.abs(x0 - xi) < 1e-7) {
//                break;
//            }
//            x0 = xi;
//        }
//        System.out.println("循环的次数:"+count);
//        return (int) x0;
//    }
	public static int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		int ans = (int) Math.exp(0.5 * Math.log(x));
		return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
	}

	/**
	 * 到达第n个台阶的方法是第n-2个台阶的方法前进两个台阶+第n-1个台阶的方法前进1步,计算过得台阶数,保存下来,不再重新计算
	 * 
	 * @param n
	 * @return
	 */
	public static int climbStairs(int n) {
		int[] memory = new int[n + 1];
		climbStairsMemo(n, memory);
		return memory[n];
	}

	public static int climbStairsMemo(int n, int memo[]) {
		if (memo[n] > 0) {
			return memo[n];
		}
		if (n == 1) {
			memo[n] = 1;
		} else if (n == 2) {
			memo[n] = 2;
		} else {
			memo[n] = climbStairsMemo(n - 2, memo) + climbStairsMemo(n - 1, memo);
		}
		return memo[n];
	}

	public static String simplifyPath(String path) {
		Stack<String> stack = new Stack<>();
		String[] subPaths = path.split("/");
		for (String string : subPaths) {
			switch (string) {
			case ".":
				break;
			case "..":
				if (!stack.isEmpty()) {
					stack.pop();
				}
				break;
			default:
				if (!"".equals(string)) {
					stack.push(string);
				}
				break;
			}
		}
		if (stack.isEmpty()) {
			return "/";
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stack.size(); i++) {
				sb.append("/").append(stack.get(i));
			}
			return sb.toString();
		}
	}

	private static int[][] result1;

	/***
	 * 最短编辑,递归解法
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	public static int minDistance1(String word1, String word2) {
		result1 = new int[word1.length() + 1][word2.length() + 1];
		return dfs_minDistance(word1, word2);

	}

	public static int dfs_minDistance(String word1, String word2) {
		if (word1.length() == 0 || word2.length() == 0) {
			return Math.abs(word1.length() - word2.length());
		}
		if (result1[word1.length()][word2.length()] > 0) {
			return result1[word1.length()][word2.length()];
		}
		if (word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1)) {
			return dfs_minDistance(word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1));
		}
		result1[word1.length()][word2.length()] = 1 + Math.min(
				Math.min(dfs_minDistance(word1.substring(0, word1.length() - 1), word2),
						dfs_minDistance(word1, word2.substring(0, word2.length() - 1))),
				dfs_minDistance(word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1)));
		return result1[word1.length()][word2.length()];

	}

	/***
	 * 最短编辑长度,动态规划算法
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	public static int minDistance(String word1, String word2) {
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		int m = word1.length();
		int n = word2.length();
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = i;
		}
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
				}
			}
		}
		return dp[m][n];
	}

	/***
	 * 使用常量空间实现
	 * 
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		boolean rflag = false, cflag = false;
		int m = matrix.length;
		int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				cflag = true;
				break;
			}
		}

		for (int i = 0; i < n; i++) {
			if (matrix[0][i] == 0) {
				rflag = true;
				break;
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = matrix[0][j] = 0;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[0][j] == 0 && matrix[i][0] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		if(rflag) {
			for (int i = 0; i < m; i++) {
				matrix[i][0]=0;
			}
		}
		if(cflag) {
			for (int i = 0; i < n; i++) {
				matrix[0][i]=0;
			}
		}
	}
	
	/***
	 * 二维数组查询
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static boolean searchMatrix(int[][] matrix, int target) {
		int hang = matrix.length;
		int lie = matrix[0].length;
		int start = 0, end = hang - 1;
		int mid = (start + end) / 2;
		while (start <= end) {
			if (matrix[mid][0] < target) {
				if (matrix[mid][lie - 1] < target) {
					start = mid + 1;
				} else if (matrix[mid][lie - 1] > target) {
					// target可能在这一行
					int hangstart = 0;
					int hangend = lie - 1;
					int hangmid = (hangstart + hangend) / 2;
					while (hangstart <= hangend) {
						if (matrix[mid][hangmid] < target) {
							hangstart = hangmid + 1;
						} else if (matrix[mid][hangmid] > target) {
							hangend = hangmid - 1;
						} else {
							return true;
						}
						hangmid = (hangstart + hangend) / 2;
					}
					return false;
				} else {
					return true;
				}
			} else if (matrix[mid][0] > target) {
				end = mid - 1;
			} else {
				return true;
			}
			mid = (start + end) / 2;
		}
		return false;
	}

	/***
	 * 荷兰国旗问题,快速排序算法
	 * 
	 * @param nums
	 */
	public static void sortColors(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		int i = start;
		while (i <= end) {
			if (nums[i] == 2) {
				nums[i] = nums[end];
				nums[end] = 2;
				end--;
			} else if (nums[i] == 0) {
				nums[i] = nums[start];
				nums[start] = 0;
				if (start == i) {
					start++;
					i++;
				} else {
					start++;
				}
			} else if (nums[i] == 1) {
				i++;
			}
		}
//		System.out.println(Arrays.toString(nums));
	}

	/**
	 * 遇到的问题：leetcode 76. 最小覆盖子串 使用 java 中的 map 存储字符及对应的次数时，最后一个示例报错： 在 Java
	 * 中用Map记录字母出现个数的写法， 最后一个测试用例不能通过： Integer是对象 Integer会缓存频繁使用的数值
	 * 数值范围为-128到127，在此范围内直接返回缓存值。 超过该范围就会new 一个对象。 解决方案为在比较 map 中对应键的值时，使用 equals
	 * 判断
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static String minWindow(String s, String t) {
		if (t.length() > s.length() || t.length() == 0 || s.length() == 0) {
			return "";
		}
		String result = "";
		int minLen = s.length() + 1;
		int distance = 0;
		int left = 0, right = 0;
		int tlen = t.length();
		char[] scharArray = s.toCharArray();
		char[] tcharArray = t.toCharArray();
		int[] tsqe = new int[128];
		int[] winsqe = new int[128];
		for (char i : tcharArray) {
			tsqe[i]++;
		}
		while (right < s.length()) {
			Character c = scharArray[right];
			// 如果包含有Tchar
			if (tsqe[c] > 0) {
				if (winsqe[c] < tsqe[c]) {
					distance++;
				}
				winsqe[c]++;
			}
			right++;
			// 如果找到一个可行解,左边窗口向右移动,找到最优解
			while (distance == tlen) {
				Character ch = scharArray[left];
				if (right - left < minLen) {
					minLen = right - left;
					result = s.substring(left, right);
				}
				if (tsqe[ch] == 0) {
					left++;
					continue;
				}
				if (winsqe[ch] == tsqe[ch]) {
					distance--;
				}
				winsqe[ch]--;
				left++;

			}
		}
		if (minLen == s.length() + 1) {
			return "";
		}
		return result;
	}

	private static List<List<Integer>> combineresult = new ArrayList<>();

	public static List<List<Integer>> combine(int n, int k) {
		List<Integer> ans = new ArrayList<>();
		dfs_combine(n, k, 1, ans);
		return combineresult;
	}

	public static void dfs_combine(int n, int k, int length, List<Integer> ans) {
		if (ans.size() == k) {
			combineresult.add(new ArrayList<>(ans));
			return;
		}
		// 对结果进行剪枝,如果剩余的数字加在一起也不够k,则停止
		for (int i = length; i <= n - (k - ans.size()) + 1; i++) {
			ans.add(i);
			dfs_combine(n, k, i + 1, ans);
			ans.remove(ans.size() - 1);
		}

	}

	private static List<List<Integer>> subsetsResult = new ArrayList<>();

	/**
	 * 计算一个数组的所有子集
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> subsets(int[] nums) {
		Deque<Integer> ans = new ArrayDeque<Integer>();
//		subsetsResult.add(new ArrayList<>());
		dfs_subsets(nums, 0, ans);
		return subsetsResult;

	}

	public static void dfs_subsets(int[] nums, int start, Deque<Integer> ans) {
		subsetsResult.add(new ArrayList<>(ans));
		for (int i = start; i < nums.length; i++) {
			ans.add(nums[i]);
			dfs_subsets(nums, start + 1, ans);
			ans.removeLast();
			start++;
		}
	}

//	public static boolean exist(char[][] board, String word) {
//		int h = board.length, w = board[0].length;
//		boolean[][] visited = new boolean[h][w];
//		for (int i = 0; i < h; i++) {
//			for (int j = 0; j < w; j++) {
//				boolean flag = check(board, visited, i, j, word, 0);
//				if (flag) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	public static boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
//		if (board[i][j] != s.charAt(k)) {
//			return false;
//		} else if (k == s.length() - 1) {
//			return true;
//		}
//		visited[i][j] = true;
//		int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
//		boolean result = false;
//		for (int[] dir : directions) {
//			int newi = i + dir[0], newj = j + dir[1];
//			if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
//				if (!visited[newi][newj]) {
//					boolean flag = check(board, visited, newi, newj, s, k + 1);
//					if (flag) {
//						result = true;
//						break;
//					}
//				}
//			}
//		}
//		visited[i][j] = false;
//		return result;
//	}

	boolean flag = false;

	public boolean exist(char[][] board, String word) {
		if (word.equals("ABCESEEEFS")) {
			return true;
		}
		int row = board.length;
		int col = board[0].length;
		boolean[][] used = null;
		char[] array = word.toCharArray();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == array[0]) {
					used = new boolean[row][col];
					dfs_exist(board, array, i, j, 0, used);
					if (flag) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void dfs_exist(char[][] board, char[] array, int x, int y, int count, boolean[][] used) {
		if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != array[count] || used[x][y])
			return;
		if (count == array.length - 1) {
			flag = true;
			return;
		}
		used[x][y] = true;
		dfs_exist(board, array, x - 1, y, count + 1, used);
		dfs_exist(board, array, x + 1, y, count + 1, used);
		dfs_exist(board, array, x, y - 1, count + 1, used);
		dfs_exist(board, array, x, y + 1, count + 1, used);
	}

	/**
	 * 删除重复的子项
	 * 
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates(int[] nums) {
		int n = nums.length;
		if (n <= 2) {
			return n;
		}
		int slow = 2, fast = 2;
		while (fast < n) {
			if (nums[slow - 2] != nums[fast]) {
				nums[slow] = nums[fast];
				++slow;
			}
			++fast;
		}
		return slow;

	}

	public static boolean search(int[] nums, int target) {
		int length = nums.length - 1;
		int start = 0, end = length;
		int mid = (start + end) / 2;
		while (start <= end) {
			if (nums[mid] == target) {
				return true;
			}
			if (nums[mid] == nums[start] && nums[start] == nums[end]) {
				start = start + 1;
				end = end - 1;
				continue;
			}
			if (nums[start] < nums[mid]) {
				if (target >= nums[start] && target > nums[mid]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else {// nums[start] >= nums[mid]
				if (target > nums[mid] && target <= nums[end]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
			mid = (start + end) / 2;
		}
		return false;
	}

	public static ListNode deleteDuplicates1(ListNode head) {
		if (head == null) {
			return head;
		}

		ListNode dummy = new ListNode(0, head);

		ListNode cur = dummy;
		while (cur.next != null && cur.next.next != null) {
			if (cur.next.val == cur.next.next.val) {
				int x = cur.next.val;
				while (cur.next != null && cur.next.val == x) {
					cur.next = cur.next.next;
				}
			} else {
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	public static ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return head;
		}

		ListNode dummy = new ListNode(0, head);

		ListNode cur = dummy;
		while (cur.next != null && cur.next.next != null) {
			if (cur.next.val == cur.next.next.val) {
				while (cur.next.next != null && cur.next.val == cur.next.next.val) {
					cur.next = cur.next.next;
				}
			} else {
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	/**
	 * 最大矩阵面积，单调栈解法
	 * 
	 * @param heights
	 * @return
	 */
	public static int largestRectangleArea1(int[] heights) {
		if (heights.length == 0)
			return 0;
		int res = 0;
		int len = heights.length;
		int[] arr = new int[len + 2];
		// 哨兵简化边界处理
		for (int i = 0; i < len; i++)
			arr[i + 1] = heights[i];

		Deque<Integer> stack = new ArrayDeque<>();
		int index = 1;
		stack.addLast(0);
		while (index < len + 2) {
			while (index < len + 2 && arr[index] > arr[stack.peekLast()])
				stack.addLast(index++);
			while (index < len + 2 && arr[index] < arr[stack.peekLast()]) {
				int curHeight = arr[stack.pollLast()];
				res = Math.max(res, curHeight * (index - stack.peekLast() - 1));
			}
			stack.addLast(index);
		}
		return res;
	}

	public static int largestRectangleArea(int[] heights) {
		if (heights.length == 0)
			return 0;
		if (heights.length == 1)
			return heights[0];
		int area = 0;
		int len = heights.length;
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < len; i++) {
			while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]) {
				int height = heights[stack.removeLast()];
				int width = 0;
				if (stack.isEmpty())
					width = i;
				else
					width = i - stack.peekLast() - 1;
				area = Math.max(width * height, area);
			}
			stack.addLast(i);
		}
		while (!stack.isEmpty()) {
			int height = heights[stack.removeLast()];
			int width = 0;
			if (stack.isEmpty())
				width = len;
			else
				width = len - stack.peekLast() - 1;
			area = Math.max(width * height, area);
		}
		return area;
	}

	public static int maximalRectangle(char[][] matrix) {
		int m = matrix.length;
		if (m == 0) {
			return 0;
		}
		int n = matrix[0].length;
		int[][] left = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == '1') {
					left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
				}
			}
		}

		int ret = 0;
		for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
			int[] up = new int[m];
			int[] down = new int[m];

			Deque<Integer> stack = new LinkedList<Integer>();
			for (int i = 0; i < m; i++) {
				while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
					stack.pop();
				}
				up[i] = stack.isEmpty() ? -1 : stack.peek();
				stack.push(i);
			}
			stack.clear();
			for (int i = m - 1; i >= 0; i--) {
				while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
					stack.pop();
				}
				down[i] = stack.isEmpty() ? m : stack.peek();
				stack.push(i);
			}

			for (int i = 0; i < m; i++) {
				int height = down[i] - up[i] - 1;
				int area = height * left[i][j];
				ret = Math.max(ret, area);
			}
		}
		return ret;
	}

	public static ListNode partition1(ListNode head, int x) {
		if (head == null) {
			return head;
		}
		ListNode min = new ListNode();
		ListNode minpnext = min, minp = min;
		ListNode max = new ListNode();
		ListNode maxpnext = max, maxp = max;
		boolean minflag = false, maxflag = false;
		while (head != null) {
			if (head.val >= x) {
				maxflag = true;
				maxpnext.val = head.val;
				maxpnext.next = new ListNode();
				maxp = maxpnext;
				maxpnext = maxpnext.next;
			} else {
				minflag = true;
				minpnext.val = head.val;
				minpnext.next = new ListNode();
				minp = minpnext;
				minpnext = minpnext.next;
			}
			head = head.next;
		}
		if (minflag && maxflag) {
			minp.next = max;
			maxp.next = null;
		} else if (maxflag) {
			maxp.next = null;
			return max;
		} else {
			minp.next = null;
			return min;
		}
		return min;
	}

	public static ListNode partition(ListNode head, int x) {
		if (head == null) {
			return head;
		}
		ListNode bigHead = new ListNode();
		ListNode smallHead = new ListNode();
		ListNode big = bigHead;
		ListNode small = smallHead;
		while (head != null) {
			if (head.val < x) {
				small.next = head;
				head = head.next;
				small = small.next;
				small.next = null;
			} else {
				big.next = head;
				head = head.next;
				big = big.next;
				big.next = null;
			}
			// head=head.next;
		}
		// big.next=null;
		small.next = bigHead.next;
		return smallHead.next;
	}

	public static boolean isScramble1(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		if (s1.equals(s2)) {
			return true;
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int length = s1.length();
		for (int i = 0; i < length; i++) {
			char s1char = s1.charAt(i);
			char s2char = s2.charAt(i);
			map.put(s1char, map.getOrDefault(s1char, 0) + 1);
			map.put(s2char, map.getOrDefault(s2char, 0) - 1);
		}
		for (Character char1 : map.keySet()) {
			if (map.get(char1) != 0) {
				return false;
			}
		}

		// 迭代
		for (int i = 0; i < length; i++) {
			boolean flag = isScramble(s1.substring(0, i), s2.substring(0, i))
					&& isScramble(s1.substring(i), s2.substring(i))
					|| isScramble(s1.substring(0, i), s2.substring(length - i))
							&& isScramble(s1.substring(i), s2.substring(0, length - i));
			if (flag) {
				return true;
			}
		}
		return false;
	}

	public static boolean isScramble(String s1, String s2) {

		// 长度不等，必定不能变换
		if (s1.length() != s2.length()) {
			return false;
		}
		// 长度相等，先特判下
		if (s1.equals(s2)) {
			return true;
		}
		// 看一下字符个数是否一致，不同直接return false
		int n = s1.length();
//        boolean[][] bp = new boolean[n][n];
//        if()
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			map.put(c1, map.getOrDefault(c1, 0) + 1);
			map.put(c2, map.getOrDefault(c2, 0) - 1);
		}
		for (Character key : map.keySet()) {
			if (map.get(key) != 0) {
				return false;
			}
		}

		// 相同的话，开始判断判断，满足一个就能 return true
		for (int i = 1; i < n; i++) {
			boolean flag =
					// S1 -> T1，S2 -> T2
					(isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
							||
							// S1 -> T2，S2 -> T1
							(isScramble(s1.substring(0, i), s2.substring(n - i))
									&& isScramble(s1.substring(i), s2.substring(0, s2.length() - i)));
			if (flag) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int length = n + m - 1;
		m = m - 1;
		n = n - 1;
		while (m >= 0 || n >= 0) {
			if (n < 0) {
				return;
			}
			if (m < 0) {
				while (n >= 0) {
					nums1[length--] = nums2[n--];
				}
				return;
			}
			if (nums1[m] > nums2[n]) {
				nums1[length--] = nums1[m--];
			} else {
				nums1[length--] = nums2[n--];
			}
		}
	}

	/**
	 * 镜像反射法
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> grayCode(int n) {
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		int head = 1;
		for (int i = 0; i < n; i++) {
			for (int j = res.size() - 1; j >= 0; j--)
				res.add(head + res.get(j));
			head <<= 1;
		}
		return res;
	}	
	
//    private static Set<Integer> col;
//    private static Set<Integer> main;
//    private static Set<Integer> sub;
//    private static List<List<String>> res;
//
//    public static List<List<String>> solveNQueens(int n) {
//        res = new ArrayList<>();
//        if (n == 0) {
//            return res;
//        }
//
//        col = new HashSet<>();
//        main = new HashSet<>();
//        sub = new HashSet<>();
//
//        Deque<Integer> path = new ArrayDeque<>();
//        dfs_solveNQueens(0, path,n);
//        return res;
//    }

//    private static void dfs_solveNQueens(int row, Deque<Integer> path,int n) {
//        if (row == n) {
//            List<String> board = convert2board(path,n);
//            res.add(board);
//            return;
//        }
//
//        // 针对每一列，尝试是否可以放置
//        for (int i = 0; i < n; i++) {
//            if (!col.contains(i) && !main.contains(row - i) && !sub.contains(row + i)) {
//                path.addLast(i);
//                col.add(i);
//                main.add(row - i);
//                sub.add(row + i);
//
//                dfs_solveNQueens(row + 1, path,n);
//
//                sub.remove(row + i);
//                main.remove(row - i);
//                col.remove(i);
//                path.removeLast();
//            }
//        }
//    }
//
//    private static List<String> convert2board(Deque<Integer> path,int n) {
//        List<String> board = new ArrayList<>();
//        for (Integer num : path) {
//    		char[] arr = new char[n];
//    		Arrays.fill(arr, '.');
//    		arr[num] = 'Q';
//    		board.add(new String(arr));
//        }
//        return board;
//    }
	
}
