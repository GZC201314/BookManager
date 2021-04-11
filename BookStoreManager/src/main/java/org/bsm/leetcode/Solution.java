package org.bsm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.bsm.leetcode.model.ListNode;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
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
		for (int i = digits.length - 1; i >= 0;i--) {
			int value = digits[i]+carry;
			digits[i]= value%10;
			carry = value/10;
		}
		if(carry==1) {
			int[] result = new int[digits.length+1];
			result[0] =1;
			System.arraycopy(digits, 0, result, 1, digits.length);
			return result;
		}else {
			return digits;
		}
	}

    public static String addBinary(String a, String b) {
    	String result = "";
    	int alen = a.length()-1;
    	int blen = b.length()-1;
    	int carry =0;
    	while (alen>=0||blen>=0) {
    		int aint = 0;
    		int bint = 0;
    		if(alen>=0) {
    			aint = Integer.parseInt(a.charAt(alen)+"");
    		}
    		if(blen>=0) {
    			bint = Integer.parseInt(b.charAt(blen)+"");
    		}
    		alen--;
    		blen--;
    		switch (aint+bint+carry) {
			case 0:
				result="0"+result;
				carry=0;
				break;
			case 1:
				result="1"+result;
				carry=0;
				break;
			case 2:
				result="0"+result;
				carry=1;
				break;
			case 3:
				result="1"+result;
				carry=1;
				break;

			default:
				break;
			}
		}
    	if(carry==1) {
    		result="1"+result;
    	}
    	return result;
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
//		[[1,2],[3,5],[6,7],[8,10],[12,16]]
//		ListNode[] nodeArr = new ListNode[] { l1, l2, l3 };
//		int[][] arr = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
//		int[][] arr = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
//		int[][] arr = new int[][] { { 1, 5 } };
//		int[] arr = new int[] {1,2,3};
//		String[] strArr = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};

//		char[][] suduku = { { '.', '.', '9', '7', '4', '8', '.', '.', '.' },
//				{ '7', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '2', '.', '1', '.', '9', '.', '.', '.' },
//				{ '.', '.', '7', '.', '.', '.', '2', '4', '.' }, { '.', '6', '4', '.', '1', '.', '5', '9', '.' },
//				{ '.', '9', '8', '.', '.', '.', '3', '.', '.' }, { '.', '.', '.', '8', '.', '3', '.', '2', '.' },
//				{ '.', '.', '.', '.', '.', '.', '.', '.', '6' }, { '.', '.', '.', '2', '7', '5', '9', '.', '.' } };
//		nextPermutation(arr);
		long start = new Date().getTime();
		System.out.println(addBinary("11","1"));
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
