package org.bsm.leetcode;

import java.util.*;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {}
 * ListNode(int val) { this.val = val; } ListNode(int val, ListNode next) { this.val = val;
 * this.next = next; } }
 */
class SolutionHead {
  /**
   * 213. 打家劫舍 II
   *
   * <p>你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
   *
   * <p>这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
   *
   * <p>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
   *
   * <p>给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
   */
  public static int rob(int[] nums) {
    int length = nums.length;
    if (length == 1) {
      return nums[0];
    }
    if (length == 2) {
      return Math.max(nums[0], nums[1]);
    }
    return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
  }

  private static int robRange(int[] nums, int start, int end) {
    int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
    for (int i = start + 2; i <= end; i++) {
      int temp = second;
      second = Math.max(first + nums[i], second);
      first = temp;
    }
    return second;
  }

  /**
   * 214. 最短回文串
   *
   * <p>给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
   */
  public static String shortestPalindrome(String s) {
    int length = s.length();
    if (isPalindrome(s)) {
      return s;
    }
    for (int i = length - 1; i >= 0; i--) {
      if (isPalindrome(s.substring(0, i))) {
        return new StringBuilder(s.substring(i)).reverse() + s;
      }
    }
    return new StringBuilder(s).reverse() + s;
  }

  /** 校验回文数 */
  public static boolean isPalindrome(String s) {
    // 用StringBuilder的reverse方法将字符串反转
    StringBuilder sb = new StringBuilder(s);
    String afterReverse = sb.reverse().toString();
    // 判断反转后的字符串与原字符串是否相等，可用compareTo，equals，
    int isequal = afterReverse.compareTo(s); // 若相等则输出0
    return isequal == 0;
  }

  /**
   * 215. 数组中的第K个最大元素
   *
   * <p>在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
   */
  public int findKthLargest(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
  }

  /**
   * 216. 组合总和 III
   *
   * <p>找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
   */
  public static List<List<Integer>> result = new ArrayList<>();

  public static List<List<Integer>> combinationSum3(int k, int n) {
    List<Integer> ans = new ArrayList<>();
    dfs_combinationSum3(k, n, 1, ans);
    return result;
  }

  public static void dfs_combinationSum3(int k, int n, int start, List<Integer> ans) {
    if (k == 1) {
      if (n >= start && n <= 9) {
        ans.add(n);
        result.add(new ArrayList<>(ans));
        ans.remove(ans.size() - 1);
      }
    } else {
      for (int i = start; i <= 9; i++) {
        ans.add(i);
        dfs_combinationSum3(k - 1, n - i, i + 1, ans);
        ans.remove(ans.size() - 1);
      }
    }
  }

  /**
   * 217. 存在重复元素
   *
   * <p>给定一个整数数组，判断是否存在重复元素。
   */
  public static boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (!set.add(num)) {
        return true;
      }
    }
    return false;
  }
  /**
   * 217. 存在重复元素
   *
   * <p>给定一个整数数组，判断是否存在重复元素。
   */
  public static boolean containsDuplicate2(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i - 1] == nums[i]) {
        return true;
      }
    }
    return false;
  }

  /**
   * 218. 天际线问题
   *
   * <p>城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。
   *
   * <p>给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
   */
  public static List<List<Integer>> getSkyline(int[][] buildings) {
    PriorityQueue<int[]> pq =
        new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
    for (int[] building : buildings) {
      pq.offer(new int[] {building[0], -building[2]});
      pq.offer(new int[] {building[1], building[2]});
    }

    List<List<Integer>> res = new ArrayList<>();

    TreeMap<Integer, Integer> heights = new TreeMap<>((a, b) -> b - a);
    heights.put(0, 1);
    int left, height = 0;
    while (!pq.isEmpty()) {
      int[] arr = pq.poll();
      if (arr[1] < 0) {
        heights.put(-arr[1], heights.getOrDefault(-arr[1], 0) + 1);
      } else {
        heights.put(arr[1], heights.get(arr[1]) - 1);
        if (heights.get(arr[1]) == 0) heights.remove(arr[1]);
      }
      int maxHeight = heights.keySet().iterator().next();
      if (maxHeight != height) {
        left = arr[0];
        height = maxHeight;
        res.add(Arrays.asList(left, height));
      }
    }

    return res;
  }

  /**
   * 219. 存在重复元素 II
   *
   * <p>给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，使得nums [i] = nums [j]，
   *
   * <p>并且 i 和 j的差的 绝对值 至多为 k。
   */
  public static boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        if (i - map.get(nums[i]) <= k) {
          return true;
        } else {
          map.put(nums[i], i);
        }
      } else {
        map.put(nums[i], i);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(shortestPalindrome(""));
  }
}
