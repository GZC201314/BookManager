package org.bsm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bsm.leetcode.model.ListNode;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
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

	public static boolean isMatch(String s, String p) {
		int sLen = s.length(), pLen = p.length();
		boolean[][] memory = new boolean[sLen + 1][pLen + 1];
		memory[0][0] = true;
		for (int i = 0; i <= sLen; i++) {
			for (int j = 1; j <= pLen; j++) {
				if (p.charAt(j - 1) == '*') {
					memory[i][j] = memory[i][j - 2] || (i > 0
							&& (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && memory[i - 1][j]);
				} else {
					memory[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
							&& memory[i - 1][j - 1];
				}
			}
		}
		return memory[sLen][pLen];
	}

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
	public static int removeDuplicates(int[] nums) {
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

	public static int search(int[] nums, int target) {
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

	public static void main(String[] args) {
//		ListNode five = new ListNode(3, new ListNode(4, new ListNode(5)));
//		ListNode four = new ListNode(2, five);
//		ListNode l1 = new ListNode(1, four);
//
//		ListNode f = new ListNode(4);
//		ListNode three = new ListNode(3, f);
//		ListNode l2 = new ListNode(1, three);
//		ListNode l3 = new ListNode(2, new ListNode(6));
//
//		ListNode[] nodeArr = new ListNode[] { l1, l2, l3 };
		int[] arr = new int[] { 2, 3, 6, 7 };
//		char[][] suduku = { { '.', '.', '9', '7', '4', '8', '.', '.', '.' },
//				{ '7', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '2', '.', '1', '.', '9', '.', '.', '.' },
//				{ '.', '.', '7', '.', '.', '.', '2', '4', '.' }, { '.', '6', '4', '.', '1', '.', '5', '9', '.' },
//				{ '.', '9', '8', '.', '.', '.', '3', '.', '.' }, { '.', '.', '.', '8', '.', '3', '.', '2', '.' },
//				{ '.', '.', '.', '.', '.', '.', '.', '.', '6' }, { '.', '.', '.', '2', '7', '5', '9', '.', '.' } };
//		nextPermutation(arr);
//		System.out.println();
//		solveSudoku(suduku);
		System.out.println(combinationSum(arr, 7));

//		ListNode result = reverseKGroup(l1, 2);
//		while (result != null) {
//			System.out.println(result.val);
//			result = result.next;
//		}
	}
}
