package org.bsm.leetcode;

import org.bsm.leetcode.model.ListNode;
import org.bsm.leetcode.model.TreeNode;

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
   *
   * <p>执行用时： 27 ms , 在所有 Java 提交中击败了 23.73% 的用户
   *
   * <p>内存消耗： 47.2 MB , 在所有 Java 提交中击败了 9.14% 的用户
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

  /**
   * 220. 存在重复元素 III
   *
   * <p>给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，
   *
   * <p>同时又满足 abs(i- j) <= k 。
   *
   * <p>如果存在则返回 true，不存在返回 false。
   */
  public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    int n = nums.length;
    Map<Long, Long> map = new HashMap<>();
    long w = (long) t + 1;
    for (int i = 0; i < n; i++) {
      long id;
      if (nums[i] >= 0) {
        id = nums[i] / w;
      } else {

        id = (nums[i] + 1) / w - 1;
      }
      if (map.containsKey(id)) {
        return true;
      }
      if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
        return true;
      }
      if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
        return true;
      }
      map.put(id, (long) nums[i]);
      if (i >= k) {
        if (nums[i - k] >= 0) {
          id = nums[i - k] / w;
        } else {

          id = (nums[i - k] + 1) / w - 1;
        }
        map.remove(id);
      }
    }
    return false;
  }

  /**
   * 221. 最大正方形
   *
   * <p>在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
   *
   * <p>动态规划算法 转移方程：
   *
   * <p>if matrix(i,j) == 0 则 dp(i,j)=0
   *
   * <p>if matrix(i,j) == 1 则 dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
   */
  public static int maximalSquare(char[][] matrix) {
    if (matrix.length == 0) {
      return 0;
    }
    int max = 0;
    int hang = matrix.length, lie = matrix[0].length;
    int[][] dp = new int[hang + 1][lie + 1];
    for (int i = 0; i < lie; i++) {
      dp[0][i] = (int) matrix[0][i] - 48;
      if (dp[0][i] > max) {
        max = dp[0][i];
      }
    }
    for (int i = 1; i < hang; i++) {
      dp[i][0] = (int) matrix[i][0] - 48;
      if (dp[i][0] > max) {
        max = dp[i][0];
      }
    }
    for (int i = 1; i < hang; i++) {
      for (int j = 1; j < lie; j++) {
        if (matrix[i][j] == '0') {
          dp[i][j] = 0;
        } else {
          dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
          if (dp[i][j] > max) {
            max = dp[i][j];
          }
        }
      }
    }
    return max * max;
  }

  /**
   * 222. 完全二叉树的节点个数
   *
   * <p>给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
   */
  public static int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  public int countNodes1(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int level = 0;
    TreeNode node = root;
    while (node.left != null) {
      level++;
      node = node.left;
    }
    int low = 1 << level, high = (1 << (level + 1)) - 1;
    while (low < high) {
      int mid = (high - low + 1) / 2 + low;
      if (exists(root, level, mid)) {
        low = mid;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }

  public boolean exists(TreeNode root, int level, int k) {
    int bits = 1 << (level - 1);
    TreeNode node = root;
    while (node != null && bits > 0) {
      if ((bits & k) == 0) {
        node = node.left;
      } else {
        node = node.right;
      }
      bits >>= 1;
    }
    return node != null;
  }

  public static int computeArea(
      int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
    // 计算重叠矩形的面积
    int x1 = Math.max(ax1, bx1);
    int x2 = Math.min(ax2, bx2);
    int y1 = Math.max(by1, ay1);
    int y2 = Math.min(ay2, by2);
    // 如果存在重叠的矩形
    int subArea = 0;
    if (x1 < x2 && y1 < y2) {
      subArea = (x2 - x1) * (y2 - y1);
    }
    int mainArea = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
    return mainArea - subArea;
  }

  /**
   * 224. 基本计算器
   *
   * <p>给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
   */
  public static int calculate1(String s) {
    s = s.replaceAll(" ", "");
    Deque<Integer> ops = new ArrayDeque<>();
    ops.push(1);
    int result = 0;
    int n = s.length();
    int i = 0;
    int sign = 1;
    while (i < n) {
      switch (s.charAt(i)) {
        case '+':
          sign = ops.peek();
          i++;
          break;
        case '-':
          sign = -ops.peek();
          i++;
          break;
        case '(':
          ops.push(sign);
          i++;
          break;
        case ')':
          ops.pop();
          i++;
          break;
        default:
          long num = 0;
          while (i < n && Character.isDigit(s.charAt(i))) {
            num = num * 10 + s.charAt(i) - '0';
            i++;
          }
          result += sign * num;
      }
    }

    return result;
  }

  /**
   * 226. 翻转二叉树
   *
   * <p>翻转一棵二叉树。
   */
  public static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode leftNode = invertTree(root.right);
    TreeNode rightNode = invertTree(root.left);
    root.left = leftNode;
    root.right = rightNode;
    return root;
  }

  /**
   * 227. 基本计算器 II
   *
   * <p>给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
   */
  public static int calculate(String s) {
    //    s = s.replaceAll(" ", "");
    //    Deque<Character> flag = new ArrayDeque<>();
    //    flag.push('#');
    //    Deque<Integer> number = new ArrayDeque<>();
    //    char[] charArr = s.toCharArray();
    //    for (int i = 0; i < charArr.length; i++) {
    //      StringBuilder sb = new StringBuilder();
    //      while (i < charArr.length && Character.isDigit(charArr[i])) {
    //        sb.append(charArr[i++]);
    //      }
    //      if (!"".equals(sb.toString())) {
    //        number.push(Integer.parseInt(sb.toString()));
    //      }
    //      char[] flags = {'+', '-', '*', '/'};
    //      if ("+-*/".contains(charArr[i] + "")) {}
    //    }
    Deque<Integer> stack = new LinkedList<Integer>();
    s = s.replaceAll(" ", "");
    char preSign = '+';
    int num = 0;
    int n = s.length();
    for (int i = 0; i < n; ++i) {
      if (Character.isDigit(s.charAt(i))) {
        num = num * 10 + s.charAt(i) - '0';
      }
      if (!Character.isDigit(s.charAt(i)) || i == n - 1) {
        switch (preSign) {
          case '+':
            stack.push(num);
            break;
          case '-':
            stack.push(-num);
            break;
          case '*':
            stack.push(stack.pop() * num);
            break;
          default:
            stack.push(stack.pop() / num);
        }
        preSign = s.charAt(i);
        num = 0;
      }
    }
    int ans = 0;
    while (!stack.isEmpty()) {
      ans += stack.pop();
    }
    return ans;
  }

  /**
   * 228. 汇总区间
   *
   * <p>给定一个无重复元素的有序整数数组 nums 。
   */
  public static List<String> summaryRanges(int[] nums) {
    List<String> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }
    int nextNum = nums[0];
    int first = nums[0];
    int end = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] - nextNum == 1) {
        end++;
        nextNum = nums[i];
      } else {
        if (first == end) {
          result.add(first + "");
        } else {
          result.add(first + "->" + end);
        }
        first = end = nextNum = nums[i];
      }
    }
    if (first == end) {
      result.add(first + "");
    } else {
      result.add(first + "->" + end);
    }
    return result;
  }

  /**
   * 229. 求众数 II
   *
   * <p>给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
   */
  public static List<Integer> majorityElement(int[] nums) {
    List<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }
    int n = nums.length;
    if (n == 1) {
      result.add(nums[0]);
      return result;
    }
    int cand1 = nums[0], cand2 = nums[0];
    int count1 = 0, count2 = 0;
    for (int num : nums) {
      if (cand1 == num) {
        count1++;
        continue;
      }
      if (cand2 == num) {
        count2++;
        continue;
      }
      if (count1 == 0) {
        cand1 = num;
        count1 = 1;
        continue;
      }
      if (count2 == 0) {
        cand2 = num;
        count2 = 1;
        continue;
      }
      count1--;
      count2--;
    }
    count1 = 0;
    count2 = 0;
    for (int num : nums) {
      if (num == cand1) {
        count1++;
      } else if (num == cand2) {
        count2++;
      }
    }
    if (count1 > n / 3) {
      result.add(cand1);
    }
    if (count2 > n / 3) {
      result.add(cand2);
    }
    return result;
  }

  public static List<Integer> listResult = new ArrayList<>();

  /**
   * 230. 二叉搜索树中第K小的元素
   *
   * <p>给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
   */
  public static int kthSmallest(TreeNode root, int k) {
    zxpl(root);
    return listResult.get(k - 1);
  }

  public static void zxpl(TreeNode root) {
    if (root == null) {
      return;
    }
    zxpl(root.left);
    listResult.add(root.val);
    zxpl(root.right);
  }

  /**
   * 231. 2 的幂
   *
   * <p>给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
   *
   * <p>如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
   */
  public static boolean isPowerOfTwo(int n) {
    if (n == 0) {
      return false;
    }
    while (n != 0) {
      if (n == 1) {
        return true;
      }
      if (n % 2 != 0) {
        return false;
      }
      n = n / 2;
    }
    return true;
  }

  /**
   * 233. 数字 1 的个数
   *
   * <p>给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
   */
  public static int countDigitOne(int n) {
    int countr = 0;
    for (int i = 1; i <= n; i *= 10) {
      int divider = i * 10;
      countr += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0), i);
    }
    return countr;
  }

  /**
   * 234. 回文链表
   *
   * <p>请判断一个链表是否为回文链表。
   *
   * <p>进阶： 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
   */
  public boolean isPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }

    // 找到前半部分链表的尾节点并反转后半部分链表
    ListNode firstHalfEnd = endOfFirstHalf(head);
    ListNode secondHalfStart = reverseList(firstHalfEnd.next);

    // 判断是否回文
    ListNode p1 = head;
    ListNode p2 = secondHalfStart;
    boolean result = true;
    while (result && p2 != null) {
      if (p1.val != p2.val) {
        result = false;
      }
      p1 = p1.next;
      p2 = p2.next;
    }

    // 还原链表并返回结果
    firstHalfEnd.next = reverseList(secondHalfStart);
    return result;
  }

  /** 转换列表 */
  private ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode nextTemp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nextTemp;
    }
    return prev;
  }

  /** 寻找中间节点 */
  private ListNode endOfFirstHalf(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }

  public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode ancestor = root;
    while (true) {
      if (p.val > ancestor.val && q.val > ancestor.val) {
        ancestor = ancestor.right;
      } else if (p.val < ancestor.val && q.val < ancestor.val) {
        ancestor = ancestor.left;
      } else {
        break;
      }
    }
    return ancestor;
  }

  /**
   * 236. 二叉树的最近公共祖先
   *
   * <p>给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
   */
  public static TreeNode lowestCommonAncestorResult;

  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    dfs_lowestCommonAncestor(root, p, q);
    return lowestCommonAncestorResult;
  }

  public static boolean dfs_lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return false;
    }
    boolean rson = dfs_lowestCommonAncestor(root.right, p, q);
    boolean lson = dfs_lowestCommonAncestor(root.left, p, q);
    if ((rson && lson) || ((root.val == p.val || root.val == q.val) && (rson || lson))) {
      lowestCommonAncestorResult = root;
    }
    return rson || lson || (root.val == p.val || root.val == q.val);
  }

  /**
   * 237 删除链表中的节点
   *
   * <p>请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
   */
  public static void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

  /**
   * 238. 除自身以外数组的乘积
   *
   * <p>给你一个长度为n的整数数组nums，其中n > 1，返回输出数组output，其中 output[i]等于nums中除nums[i]之外其余各元素的乘积。
   */
  public static int[] productExceptSelf(int[] nums) {
    int length = nums.length;
    int[] result = new int[length];
    result[0] = 1;
    // 计算左边的元素的乘积
    for (int i = 1; i < length; i++) {
      result[i] = nums[i - 1] * result[i - 1];
    }

    int R = 1;
    for (int i = length - 1; i >= 0; i--) {
      result[i] = result[i] * R;
      R *= nums[i];
    }
    return result;
  }

  /**
   * 239. 滑动窗口最大值
   *
   * <p>给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
   *
   * @param nums
   * @param k
   * @return
   */
  public static int[] maxSlidingWindow(int[] nums, int k) {
    //    int start = 0, end = k;
    //    int length = nums.length;
    //    int[] result = new int[length - k + 1];
    //    int max = nums[0];
    //    for (int i = start; i < end; i++) {
    //      if (nums[i] > max) {
    //        max = nums[i];
    //      }
    //    }
    //    int index = 0;
    //    result[index++] = max;
    //    while (end < length) {
    //      // 如果删除的数字不是max
    //      if (nums[start] != max) {
    //        // 判断新加入的数字是否比max大
    //        if (max < nums[end]) {
    //          result[index++] = nums[end];
    //          max = nums[end];
    //        } else {
    //          result[index++] = max;
    //        }
    //      } else { // 删除的是最大值
    //        // 新加入的数字比max大
    //        if (max < nums[end]) {
    //          result[index++] = nums[end];
    //          max = nums[end];
    //        } else { // 新加入的数字比max小。找出窗口中的最大值
    //          max = nums[start + 1];
    //          for (int i = start + 1; i <= end; i++) {
    //            if (nums[i] > max) {
    //              max = nums[i];
    //            }
    //          }
    //          result[index++] = max;
    //        }
    //      }
    //      start++;
    //      end++;
    //    }
    //    return result;
    if (nums == null || nums.length < 2) return nums;
    // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
    LinkedList<Integer> queue = new LinkedList<Integer>();
    // 结果数组
    int[] result = new int[nums.length - k + 1];
    // 遍历nums数组
    for (int i = 0; i < nums.length; i++) {
      // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
      while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
        queue.pollLast();
      }
      // 添加当前值对应的数组下标
      queue.addLast(i);
      // 判断当前队列中队首的值是否有效
      if (queue.peek() <= i - k) {
        queue.poll();
      }
      // 当窗口长度为k时 保存当前窗口中最大值
      if (i + 1 >= k) {
        result[i + 1 - k] = nums[queue.peek()];
      }
    }
    return result;
  }

  /**
   * 240. 搜索二维矩阵2
   *
   * <p>编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
   *
   * <p>每行的元素从左到右升序排列。每列的元素从上到下升序排列。
   */
  public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }

    return searchRec(matrix, target, 0, 0, matrix[0].length - 1, matrix.length - 1);
  }

  private static boolean searchRec(
      int[][] matrix, int target, int left, int up, int right, int down) {
    // this submatrix has no height or no width.
    if (left > right || up > down) {
      return false;
      // `target` is already larger than the largest element or smaller
      // than the smallest element in this submatrix.
    } else if (target < matrix[up][left] || target > matrix[down][right]) {
      return false;
    }

    int mid = left + (right - left) / 2;

    // Locate `row` such that matrix[row-1][mid] < target < matrix[row][mid]
    int row = up;
    while (row <= down && matrix[row][mid] <= target) {
      if (matrix[row][mid] == target) {
        return true;
      }
      row++;
    }

    return searchRec(matrix, target, left, row, mid - 1, down)
        || searchRec(matrix, target, mid + 1, up, right, row - 1);
  }

  public static List<Integer> diffWaysToCompute(String expression) {
    if (expression.matches("[0-9]+")) {
      List<Integer> arr = new ArrayList<>();
      arr.add(Integer.parseInt(expression));
      return arr;
    }
    List<Integer> result = new ArrayList<>();
    List<Integer> lResult = new ArrayList<>();
    List<Integer> rResult = new ArrayList<>();
    int length = expression.length();
    for (int i = 0; i < length; ++i) {
      if ("+-*".contains(expression.charAt(i) + "")) {
        lResult = diffWaysToCompute(expression.substring(0, i));
        rResult = diffWaysToCompute(expression.substring(i + 1));
        for (Integer value : lResult) {
          for (Integer integer : rResult) {
            switch (expression.charAt(i)) {
              case '+':
                result.add(value + integer);
                break;
              case '-':
                result.add(value - integer);
                break;
              case '*':
                result.add(value * integer);
                break;
            }
          }
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    char[][] matrix =
        new char[][] {
          {'1', '1', '1', '1', '0'},
          {'1', '1', '1', '1', '0'},
          {'1', '1', '1', '1', '1'},
          {'1', '1', '1', '1', '1'},
          {'0', '0', '1', '1', '1'}
        };
    int[] arr = {7, 2, 4};
    int[][] intArr = {
      {1, 4, 7, 11, 15},
      {2, 5, 8, 12, 19},
      {3, 6, 9, 16, 22},
      {10, 13, 14, 17, 24},
      {18, 21, 23, 26, 30}
    };
    System.out.println((diffWaysToCompute("2-1-1")));
  }
}
