package org.bsm.leetcode;

import org.bsm.leetcode.model.ListNode;
import org.bsm.leetcode.model.Node;
import org.bsm.leetcode.model.TreeNode;

import java.util.*;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {}
 * ListNode(int val) { this.val = val; } ListNode(int val, ListNode next) { this.val = val;
 * this.next = next; } }
 */
class Solution {

  @org.jetbrains.annotations.Contract("null -> null")
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

  public static void dfs_subsetsWithDup(
      int[] nums, int length, List<Integer> ans, Set<List<Integer>> result) {
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

  private static final int SEG_COUNT = 4;
  private static List<String> ans = new ArrayList<String>();
  private static int[] segments;

  public static List<String> restoreIpAddresses(String s) {
    segments = new int[SEG_COUNT];
    dfs_findLadders(s, 0, 0);
    return ans;
  }

  public static void dfs_findLadders(String s, int segId, int segStart) {
    // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
    if (segId == SEG_COUNT) {
      if (segStart == s.length()) {
        StringBuffer ipAddr = new StringBuffer();
        for (int i = 0; i < SEG_COUNT; ++i) {
          ipAddr.append(segments[i]);
          if (i != SEG_COUNT - 1) {
            ipAddr.append('.');
          }
        }
        ans.add(ipAddr.toString());
      }
      return;
    }

    // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
    if (segStart == s.length()) {
      return;
    }

    // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
    if (s.charAt(segStart) == '0') {
      segments[segId] = 0;
      dfs_findLadders(s, segId + 1, segStart + 1);
    }

    // 一般情况，枚举每一种可能性并递归
    int addr = 0;
    for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
      addr = addr * 10 + (s.charAt(segEnd) - '0');
      if (addr > 0 && addr <= 0xFF) {
        segments[segId] = addr;
        dfs_findLadders(s, segId + 1, segEnd + 1);
      } else {
        break;
      }
    }
  }

  public static List<Integer> result = new ArrayList<>();

  public static List<Integer> inorderTraversal(TreeNode root) {
    dfs_inorderTraversal(root);
    return result;
  }

  public static void dfs_inorderTraversal(TreeNode treenode) {
    if (treenode == null) {
      return;
    }
    dfs_inorderTraversal(treenode.left);
    s.add(treenode.val);
    dfs_inorderTraversal(treenode.right);
  }

  /**
   * 不同的二叉搜索树
   *
   * @param n
   * @return
   */
  public static List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      List<TreeNode> treeNode = new ArrayList<>();
      treeNode.add(null);
      return treeNode;
    }
    return generateTrees(1, n);
  }

  public static List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> ans = new ArrayList<>();
    if (end < start) {
      ans.add(null);
      return ans;
    }
    for (int i = start; i <= end; i++) {
      List<TreeNode> leftNodes = generateTrees(start, i - 1);
      List<TreeNode> rightNodes = generateTrees(i + 1, end);
      for (TreeNode left : leftNodes) {
        for (TreeNode right : rightNodes) {
          TreeNode root = new TreeNode(i);
          root.left = left;
          root.right = right;
          ans.add(root);
        }
      }
    }
    return ans;
  }

  public static int numTrees(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i < n + 1; i++) for (int j = 1; j < i + 1; j++) dp[i] += dp[j - 1] * dp[i - j];

    return dp[n];
  }

  //	public static boolean isInterleave(String s1, String s2, String s3) {
  //		return dfs_isInterleave(s1, s2, s3, true);
  //	}
  //
  //	public static boolean dfs_isInterleave(String s1, String s2, String s3, boolean isleft) {
  //		if (s1.length() + s2.length() != s3.length()) {
  //			return false;
  //		}
  //		if (isleft) {
  //			if (s1.equals(s3)) {
  //				return true;
  //			}
  //			for (int i = 0; i < s1.length(); i++) {
  //				if (s3.startsWith(s1.substring(0, i + 1))) {
  //					dfs_isInterleave(s1.substring(i + 1), s2, s3.substring(i + 1), false);
  //				} else {
  //					return false;
  //				}
  //			}
  //			return false;
  //		} else {
  //			if (s2.equals(s3)) {
  //				return true;
  //			}
  //			for (int i = 0; i < s2.length(); i++) {
  //				if (s3.startsWith(s2.substring(0, i + 1))) {
  //					dfs_isInterleave(s1, s2.substring(i + 1), s3.substring(i + 1), true);
  //				} else {
  //					return false;
  //				}
  //			}
  //			return false;
  //		}
  //	}
  public static boolean isInterleave(String s1, String s2, String s3) {
    int n = s1.length(), m = s2.length(), t = s3.length();

    if (n + m != t) {
      return false;
    }

    boolean[][] f = new boolean[n + 1][m + 1];

    f[0][0] = true;
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        int p = i + j - 1;
        if (i > 0) {
          f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
        }
        if (j > 0) {
          f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
        }
      }
    }

    return f[n][m];
  }

  /**
   * 判断搜索二叉树,中序遍历序列有序
   *
   * @param root
   * @return
   */
  private static long leftmin = Long.MIN_VALUE;

  public static boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (!isValidBST(root.left)) {
      return false;
    }
    if (root.val <= leftmin) {
      return false;
    }
    leftmin = root.val;
    System.out.println(leftmin);
    return isValidBST(root.right);
  }

  public void recoverTree(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
    TreeNode x = null, y = null, prep = null;
    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.addLast(root);
        root = root.left;
      }
      root = stack.pop();
      if (prep != null && root.val < prep.val) {
        y = root;
        if (x == null) {
          x = prep;
        } else {
          break;
        }
      }
      prep = root;
      root = root.right;
    }
    int tem = x.val;
    x.val = y.val;
    y.val = tem;
  }

  /**
   * 相同的图判断
   *
   * @param p
   * @param q
   * @return
   */
  public static boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    while (p != null && q != null) {
      if (p.val != q.val) {
        return false;
      }
      return (isSameTree(p.left, q.left)) && isSameTree(p.right, q.right);
    }
    return false;
  }

  public static boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }

    return isSymmetric(root.left, root.right);

    //		while (root.left != null && root.right != null) {
    //			if (root.left.val != root.right.val) {
    //				return false;
    //			}
    //
    //		}
  }

  public static boolean isSymmetric(TreeNode leftnode, TreeNode rightNode) {
    if (leftnode == null && rightNode == null) {
      return true;
    }
    if (leftnode.val != rightNode.val) {
      return false;
    }
    return (isSymmetric(leftnode.left, rightNode.right)
        && isSymmetric(leftnode.right, rightNode.left));
  }

  /**
   * 二叉树层次遍历
   *
   * @param root
   * @return
   */
  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> reLists = new ArrayList<>();
    if (root == null) {
      return reLists;
    }
    Queue<TreeNode> queue1 = new LinkedList<>();
    Queue<TreeNode> queue2 = new LinkedList<>();
    queue1.add(root);
    List<Integer> ans = new ArrayList<>();
    ans.add(root.val);
    reLists.add(ans);
    while (!queue1.isEmpty() || !queue2.isEmpty()) {
      List<Integer> ans1 = new ArrayList<>();
      while (!queue1.isEmpty()) {
        TreeNode node = queue1.poll();
        if (node.left != null) {
          ans1.add(node.left.val);
          queue2.add(node.left);
        }
        if (node.right != null) {
          ans1.add(node.right.val);
          queue2.add(node.right);
        }
      }
      if (ans1.size() != 0) {
        reLists.add(ans1);
      }
      List<Integer> ans2 = new ArrayList<>();
      while (!queue2.isEmpty()) {
        TreeNode node = queue2.poll();
        if (node.left != null) {
          ans2.add(node.left.val);
          queue1.add(node.left);
        }
        if (node.right != null) {
          ans2.add(node.right.val);
          queue1.add(node.right);
        }
      }
      if (ans2.size() != 0) {
        reLists.add(ans2);
      }
    }
    return reLists;
  }

  public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> reLists = new ArrayList<>();
    if (root == null) {
      return reLists;
    }

    Deque<TreeNode> queue1 = new LinkedList<>();
    Deque<TreeNode> queue2 = new LinkedList<>();
    queue1.add(root);
    while (!queue1.isEmpty() || !queue2.isEmpty()) {
      List<Integer> ans1 = new ArrayList<>();
      while (!queue1.isEmpty()) {
        TreeNode node = queue1.pollLast();
        ans1.add(node.val);
        if (node.left != null) {
          queue2.add(node.left);
        }
        if (node.right != null) {
          queue2.add(node.right);
        }
      }
      if (ans1.size() != 0) {
        reLists.add(ans1);
      }
      List<Integer> ans2 = new ArrayList<>();
      while (!queue2.isEmpty()) {
        TreeNode node = queue2.pollLast();
        ans2.add(node.val);
        if (node.left != null) {
          queue1.add(node.left);
        }
        if (node.right != null) {
          queue1.add(node.right);
        }
      }
      if (ans2.size() != 0) {
        reLists.add(ans2);
      }
    }
    return reLists;
  }

  public static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  /**
   * 根据中序和先序序列构造二叉树
   *
   * @param preorder
   * @param inorder
   * @return
   */
  private static Map<Integer, Integer> map = new HashMap<>();

  private static int post_idx;

  public static TreeNode buildTree1(int[] preorder, int[] inorder) {
    int prelen = preorder.length;
    int inlen = inorder.length;
    if (prelen != inlen) {
      return null;
    }
    // 计算每个数字对应的index
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }
    return buildTree1(preorder, inorder, 0, prelen - 1, 0, prelen - 1);
  }

  public static TreeNode buildTree1(
      int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }
    // 前序遍历第一个节点是根节点
    int root_val = preorder[preStart];
    TreeNode rootNode = new TreeNode(root_val);
    int pIndex = map.get(root_val);
    rootNode.left =
        buildTree1(
            preorder, inorder, preStart + 1, pIndex - inStart + preStart, inStart, pIndex - 1);
    rootNode.right =
        buildTree1(preorder, inorder, pIndex - inStart + preStart + 1, preEnd, pIndex + 1, inEnd);
    return rootNode;
  }

  /**
   * [9,3,15,20,7] [9,15,7,20,3]
   *
   * @param inorder
   * @param postorder
   * @return
   */
  public static TreeNode buildTree(int[] inorder, int[] postorder) {
    int postlen = postorder.length;
    int inlen = inorder.length;
    if (postlen != inlen) {
      return null;
    }
    // 计算每个数字对应的index
    for (int i = 0; i < inlen; i++) {
      map.put(inorder[i], i);
    }
    post_idx = postlen - 1;
    return buildTree(inorder, postorder, 0, postlen - 1);
  }

  public static TreeNode buildTree(int[] inorder, int[] postorder, int start, int end) {
    if (start > end) {
      return null;
    }
    int root_val = postorder[post_idx];
    TreeNode rootNode = new TreeNode(root_val);
    int pIndex = map.get(root_val);
    post_idx--;
    rootNode.right = buildTree(inorder, postorder, pIndex + 1, end);
    rootNode.left = buildTree(inorder, postorder, start, pIndex - 1);
    return rootNode;
  }

  //    int post_idx;
  //    static Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();
  //
  //    public static TreeNode helper(int[] inorder,int[] postorder,int in_left, int in_right) {
  //        // 如果这里没有节点构造二叉树了，就结束
  //        if (in_left > in_right) {
  //            return null;
  //        }
  //
  //        // 选择 post_idx 位置的元素作为当前子树根节点
  //        int root_val = postorder[post_idx];
  //        TreeNode root = new TreeNode(root_val);
  //
  //        // 根据 root 所在位置分成左右两棵子树
  //        int index = idx_map.get(root_val);
  //
  //        // 下标减一
  //        post_idx--;
  //        // 构造右子树
  //        root.right = helper(inorder,postorder,index + 1, in_right);
  //        // 构造左子树
  //        root.left = helper(inorder,postorder,in_left, index - 1);
  //        return root;
  //    }
  //
  //    public static TreeNode buildTree(int[] inorder, int[] postorder) {
  //
  //        // 从后序遍历的最后一个元素开始
  //        post_idx = postorder.length - 1;
  //
  //        // 建立（元素，下标）键值对的哈希表
  //        int idx = 0;
  //        for (Integer val : inorder) {
  //            idx_map.put(val, idx++);
  //        }
  //
  //        return helper(inorder,postorder,0, inorder.length - 1);
  //    }

  public static List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> reLists = new ArrayList<>();
    Deque<List<Integer>> stack = new ArrayDeque<>();
    if (root == null) {
      return reLists;
    }
    Queue<TreeNode> queue1 = new LinkedList<>();
    Queue<TreeNode> queue2 = new LinkedList<>();
    queue1.add(root);
    List<Integer> ans = new ArrayList<>();
    ans.add(root.val);
    stack.add(ans);
    while (!queue1.isEmpty() || !queue2.isEmpty()) {
      List<Integer> ans1 = new ArrayList<>();
      while (!queue1.isEmpty()) {
        TreeNode node = queue1.poll();
        if (node.left != null) {
          ans1.add(node.left.val);
          queue2.add(node.left);
        }
        if (node.right != null) {
          ans1.add(node.right.val);
          queue2.add(node.right);
        }
      }
      if (ans1.size() != 0) {
        stack.addFirst(ans1);
      }
      List<Integer> ans2 = new ArrayList<>();
      while (!queue2.isEmpty()) {
        TreeNode node = queue2.poll();
        if (node.left != null) {
          ans2.add(node.left.val);
          queue1.add(node.left);
        }
        if (node.right != null) {
          ans2.add(node.right.val);
          queue1.add(node.right);
        }
      }
      if (ans2.size() != 0) {
        stack.addFirst(ans2);
      }
    }
    reLists.addAll(stack);
    return reLists;
  }

  public static TreeNode sortedArrayToBST(int[] nums) {
    int length = nums.length;
    TreeNode root = new TreeNode();
    if (length == 0) {
      return root;
    }
    int mid = length / 2;
    root.val = nums[mid];
    root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
    root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, length));
    return root;
  }

  public static TreeNode sortedListToBST(ListNode head) {
    ArrayList<Integer> list = new ArrayList<>();
    if (head == null) {
      return null;
    }
    while (head != null) {
      list.add(head.val);
      head = head.next;
    }
    return sortedArrayToBST(list.stream().mapToInt(Integer::valueOf).toArray());
  }

  public static boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (Math.abs(deepTree(root.left) - deepTree(root.right)) > 1) {
      return false;
    }
    return isBalanced(root.left) && isBalanced(root.right);
  }

  public static int deepTree(TreeNode node) {
    if (node == null) {
      return 0;
    }
    if (node.left == null && node.right == null) {
      return 1;
    }
    return Math.max(deepTree(node.left), deepTree(node.right)) + 1;
  }

  /**
   * 最小深度
   *
   * @param root
   * @return
   */
  public static int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
  }

  public static boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null) {
      return targetSum == root.val;
    }
    return hasPathSum(root.left, targetSum - root.val)
        || hasPathSum(root.right, targetSum - root.val);
  }

  public static void dfs_pathSum(TreeNode node, int targetSum) {
    if (node == null) {
      return;
    }
    if (node.left == null && node.right == null) {
      if (targetSum == node.val) {
        s.add(node.val);
        pathSumResult.add(new ArrayList<>(s));
        s.remove(s.size() - 1);
      }
    }
    System.out.printf("当前List 里边存在的数据：" + s + " 要添加的数据------>" + node.val + "\n");
    s.add(node.val);
    targetSum = targetSum - node.val;
    if (targetSum > 0) {
      dfs_pathSum(node.left, targetSum);
      dfs_pathSum(node.right, targetSum);
    }
    s.remove(s.size() - 1);
  }

  /**
   * @param root
   * @param targetSum
   * @return
   */
  private static List<List<Integer>> pathSumResult = new ArrayList<>();

  private static List<Integer> s = new ArrayList<>();

  public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {

    dfs_pathSum(root, targetSum);
    return pathSumResult;
  }

  /**
   * 114. 二叉树展开为链表(算法思想，把右子树，加到左子树的最右边，然后把整个左子树放到根节点的右节点上，每运算一次)
   *
   * @param root
   */
  public static void flatten(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
      TreeNode pre = curr.left;
      if (pre != null) {
        while (pre.right != null) {
          pre = pre.right;
        }
        pre.right = curr.right;
        curr.right = curr.left;
        curr.left = null;
      }
      curr = curr.right;
    }
  }

  /**
   * 115. 不同的子序列(动态规划算法) dp[i][j] 代表 T 前 i 字符串可以由 S j 字符串组成最多个数.
   *
   * <p>转移方程
   *
   * <p>当 S[j] == T[i] , dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
   *
   * <p>当 S[j] != T[i] , dp[i][j] = dp[i][j-1]
   *
   * @param s
   * @param t
   * @return 不同的子序列的个数
   */
  public static int numDistinct(String s, String t) {
    if (t.length() > s.length()) {
      return 0;
    }
    int m = s.length();
    int n = t.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= m; i++) {
      dp[0][i] = 1;
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s.charAt(j - 1) == t.charAt(i - 1)) {
          dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
        } else {
          dp[i][j] = dp[i][j - 1];
        }
      }
    }
    return dp[n][m];
  }

  /**
   * 116. 填充每个节点的下一个右侧节点指针 队列方法
   *
   * @param root
   * @return
   */
  //    public static Node connect_deque(Node root) {
  //        if (root == null) {
  //            return null;
  //        }
  //        Deque<Node> deque = new ArrayDeque<>();
  //        deque.add(root);
  //        int count = 0;
  //        while (!deque.isEmpty()){
  //            for (int i = 0; i < Math.pow(2, count); i++) {
  //                Node node = deque.pollFirst();
  //                if (i != Math.pow(2, count) - 1) {
  //                    node.next = deque.peekFirst();
  //                }
  //                if(node.left!=null){
  //                    deque.addLast(node.left);
  //                    deque.addLast(node.right);
  //                }
  //            }
  //            count++;
  //        }
  //        return root;
  //    }
  public static Node connect1(Node root) {
    if (root == null) {
      return null;
    }
    if (root.left == null) {
      return root;
    }
    root.left.next = root.right;
    Node left = root.left;
    Node right = root.right;
    if (root.next != null) {
      right.next = root.next.left;
    }
    connect1(root.left);
    connect1(root.right);
    return root;
  }

  /**
   * 117. 填充每个节点的下一个右侧节点指针,不是完全二叉树 II
   *
   * @param root
   * @return
   */
  public static Node connect(Node root) {
    List<List<Integer>> reLists = new ArrayList<>();
    if (root == null) {
      return null;
    }
    Queue<Node> queue1 = new LinkedList<>();
    Queue<Node> queue2 = new LinkedList<>();
    queue1.add(root);
    while (!queue1.isEmpty() || !queue2.isEmpty()) {
      while (!queue1.isEmpty()) {
        Node node = queue1.poll();
        if (queue1.peek() != null) {
          node.next = queue1.peek();
        }
        if (node.left != null) {
          queue2.add(node.left);
        }
        if (node.right != null) {
          queue2.add(node.right);
        }
      }
      while (!queue2.isEmpty()) {
        Node node = queue2.poll();
        if (queue2.peek() != null) {
          node.next = queue1.peek();
        }
        if (node.left != null) {
          queue1.add(node.left);
        }
        if (node.right != null) {
          queue1.add(node.right);
        }
      }
    }
    return root;
  }

  /**
   * 118. 杨辉三角
   *
   * @param numRows
   * @return
   */
  public static List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>();
    if (numRows == 0) {
      return result;
    }
    List<Integer> row1 = new ArrayList<>();
    row1.add(1);
    result.add(row1);
    for (int i = 1; i < numRows; i++) {
      List<Integer> row = new ArrayList<>();
      row.add(1);
      List<Integer> rowlast = result.get(result.size() - 1);
      for (int j = 1; j < rowlast.size(); j++) {
        row.add(rowlast.get(j - 1) + rowlast.get(j));
      }
      row.add(1);
      result.add(row);
    }
    return result;
  }

  /**
   * 119. 杨辉三角 II 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
   *
   * @param rowIndex
   * @return
   */
  public static List<Integer> getRow(int rowIndex) {
    List<Integer> row1 = new ArrayList<>();
    row1.add(1);
    for (int i = 1; i < rowIndex; i++) {
      List<Integer> row = new ArrayList<>();
      row.add(1);
      for (int j = 1; j < row1.size(); j++) {
        row.add(row1.get(j - 1) + row1.get(j));
      }
      row.add(1);
      row1 = new ArrayList<>(row);
    }
    return row1;
  }

  /**
   * 120. 三角形最小路径和 给定一个三角形 triangle ，找出自顶向下的最小路径和。
   *
   * @param triangle 三角形 triangle
   * @return 最小路径和
   */
  public static int minimumTotal(List<List<Integer>> triangle) {
    if (triangle == null) {
      return 0;
    }
    for (int i = 1; i < triangle.size(); i++) {
      for (int j = 0; j < triangle.get(i).size(); j++) {
        if (j == 0) {
          triangle.get(i).set(j, triangle.get(i - 1).get(j) + triangle.get(i).get(j));
        } else if (j == triangle.get(i - 1).size()) {
          triangle.get(i).set(j, triangle.get(i - 1).get(j - 1) + triangle.get(i).get(j));
        } else {
          int min = Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j));
          triangle.get(i).set(j, min + triangle.get(i).get(j));
        }
      }
    }
    int min = triangle.get(triangle.size() - 1).get(0);
    for (int i = 1; i < triangle.get(triangle.size() - 1).size(); i++) {
      if (min > triangle.get(triangle.size() - 1).get(i)) {
        min = triangle.get(triangle.size() - 1).get(i);
      }
    }
    return min;
  }

  /**
   * 121. 买卖股票的最佳时机 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
   *
   * <p>你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
   *
   * <p>返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
   *
   * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   *
   * @param prices 价格数组
   * @return
   */
  public static int maxProfit1(int[] prices) {
    int minprice = Integer.MAX_VALUE;
    int maxprofit = 0;
    for (int i = 0; i < prices.length; i++) {
      if (prices[i] < minprice) {
        minprice = prices[i];
      } else if (prices[i] - minprice > maxprofit) {
        maxprofit = prices[i] - minprice;
      }
    }
    return maxprofit;
  }

  /**
   * 122. 买卖股票的最佳时机 II
   *
   * @param prices 价格数组
   * @return
   */
  public static int maxProfit2(int[] prices) {
    int max = 0;
    int length = prices.length;
    for (int i = 1; i < length; i++) {
      if (prices[i] > prices[i - 1]) {
        max += (prices[i] - prices[i - 1]);
      }
    }
    return max;
  }

  /**
   * 123. 买卖股票的最佳时机 III 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
   *
   * <p>设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
   *
   * <p>注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 状态转移方程 buy1 = max{buy1,-prices[i]} sell1 =
   * max{sell1,prices[i]+buy1} buy2 = max{buy2,sell1-prices[i]} sell2 = max{sell2,buy2+prices[i]}
   *
   * @param prices 价格数组
   * @return 最大利润
   */
  public static int maxProfit(int[] prices) {
    int n = prices.length;
    int buy1 = -prices[0], sell1 = 0;
    int buy2 = -prices[0], sell2 = 0;
    for (int i = 1; i < n; ++i) {
      buy1 = Math.max(buy1, -prices[i]);
      sell1 = Math.max(sell1, buy1 + prices[i]);
      buy2 = Math.max(buy2, sell1 - prices[i]);
      sell2 = Math.max(sell2, buy2 + prices[i]);
    }
    return sell2;
  }

  /**
   * 124. 二叉树中的最大路径和 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个
   * 节点，且不一定经过根节点。 路径和 是路径中各节点值的总和。 给你一个二叉树的根节点 root，返回其 最大路径和 。
   *
   * @param root
   * @return
   */
  private static int max = Integer.MIN_VALUE;

  public static int maxPathSum(TreeNode root) {
    maxSum(root);
    return max;
  }

  public static int maxSum(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int leftNum = Math.max(maxSum(node.left), 0);
    int rightNum = Math.max(maxSum(node.right), 0);
    if (max < (node.val + leftNum + rightNum)) {
      max = (node.val + leftNum + rightNum);
    }
    return node.val + Math.max(leftNum, rightNum);
  }

  /**
   * 125. 验证回文串 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 说明：本题中，我们将空字符串定义为有效的回文串。
   *
   * @param s
   * @return
   */
  public static boolean isPalindrome(String s) {
    int end = s.length() - 1;
    int start = 0;
    while (start < end) {
      if (Character.isLetterOrDigit(s.charAt(start)) && Character.isLetterOrDigit(s.charAt(end))) {
        if (Character.toUpperCase(s.charAt(start)) != Character.toUpperCase(s.charAt(end))) {
          return false;
        } else {
          start++;
          end--;
        }
      } else if (!Character.isLetterOrDigit(s.charAt(start))) {
        start++;
      } else if (!Character.isLetterOrDigit(s.charAt(end))) {
        end--;
      }
    }
    return true;
  }

  /**
   * 127. 单词接龙 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
   *
   * <p>序列中第一个单词是 beginWord 。 序列中最后一个单词是 endWord 。 每次转换只能改变一个字母。 转换过程中的中间单词必须是字典wordList 中的单词。
   * 给你两个单词 beginWord和 endWord 和一个字典 wordList ，找到从beginWord 到endWord 的 最短转换序列 中的 单词数目
   * 。如果不存在这样的转换序列，返回 0。
   *
   * @param beginWord
   * @param endWord
   * @param wordList
   * @return
   */
  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (wordList.size() == 0 || !wordList.contains(endWord)) {
      return 0;
    }
    // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
    Set<String> wordSet = new HashSet<>(wordList);
    wordSet.remove(beginWord);
    // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
    Queue<String> queue = new ArrayDeque<>();
    queue.offer(beginWord);
    Queue<String> visited = new ArrayDeque<>();
    visited.offer(beginWord);
    // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
    int step = 1;
    int wordlen = beginWord.length();
    while (!queue.isEmpty()) {
      int wordSize = queue.size();
      // 循环每一个节点，构造部图构
      for (int i = 0; i < wordSize; i++) {
        String word = queue.poll();
        for (int j = 0; j < wordlen; j++) {
          char oldchar = word.charAt(j);
          for (char k = 'a'; k <= 'z'; k++) {
            if (k == oldchar) {
              continue;
            }
            // 对于有重复的字符要特殊处理
            //                        String newWord = word.replace(oldchar, k);
            char[] chars = word.toCharArray();
            chars[j] = k;
            String newWord = new String(chars);
            if (newWord.equals(endWord)) {
              return step + 1;
            }
            if (wordSet.contains(newWord)) {
              if (!visited.contains(newWord)) {
                visited.add(newWord);
                queue.add(newWord);
              }
            }
          }
        }
      }
      step++;
    }
    return 0;
  }

  /**
   * 129. 求根节点到叶节点数字之和 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。 每条从根节点到叶节点的路径都代表一个数字：
   *
   * <p>例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。 计算从根节点到叶节点生成的 所有数字之和 。
   *
   * <p>叶节点 是指没有子节点的节点。
   *
   * @param root
   * @return
   */
  public static int sumNumbers(TreeNode root) {
    return dfs_sumNumbers(root, 0);
  }

  public static int dfs_sumNumbers(TreeNode root, int levelSum) {
    if (root == null) {
      return 0;
    }
    int sum = levelSum * 10 + root.val;
    if (root.left == null && root.right == null) {
      return sum;
    }
    return dfs_sumNumbers(root.left, sum) + dfs_sumNumbers(root.right, sum);
  }

  /**
   * 126. 单词接龙 II
   *
   * <p>按字典wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord
   * ->s1->s2->...->sk这样的单词序列，并满足： 每对相邻的单词之间仅有单个字母不同。
   * 转换过程中的每个单词si（1<=i<=k）必须是字典wordList中的单词。注意beginWord不必是字典wordList中的单词。 sk==endWord
   * 给你两个单词beginWord和endWord，以及一个字典wordList。 请你找出并返回所有从beginWord到endWord的最短转换序列，
   * 如果不存在这样的转换序列，返回一个空列表。 每个序列都应该以单词列表[beginWord,s1,s2,...,sk]的形式返回。
   *
   * @param beginWord
   * @param endWord
   * @param wordList
   * @return
   */
  public static List<List<String>> findLadders(
      String beginWord, String endWord, List<String> wordList) {
    List<List<String>> res = new ArrayList<>();
    // 因为需要快速判断扩展出的单词是否在 wordList 里，因此需要将 wordList 存入哈希表，这里命名为「字典」
    Set<String> dict = new HashSet<>(wordList);
    // 特殊用例判断
    if (!dict.contains(endWord)) {
      return res;
    }

    dict.remove(beginWord);

    // 第 1 步：广度优先遍历建图
    // 记录扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先遍历的第几层
    Map<String, Integer> steps = new HashMap<>();
    steps.put(beginWord, 0);
    // 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，这些单词可以变换到 key ，它们是一对多关系
    Map<String, List<String>> from = new HashMap<>();
    int step = 1;
    boolean found = false;
    int wordLen = beginWord.length();
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String currWord = queue.poll();
        char[] charArray = currWord.toCharArray();
        // 将每一位替换成 26 个小写英文字母
        for (int j = 0; j < wordLen; j++) {
          char origin = charArray[j];
          for (char c = 'a'; c <= 'z'; c++) {
            charArray[j] = c;
            String nextWord = String.valueOf(charArray);
            if (steps.containsKey(nextWord) && step == steps.get(nextWord)) {
              from.get(nextWord).add(currWord);
            }
            if (!dict.contains(nextWord)) {
              continue;
            }
            // 如果从一个单词扩展出来的单词以前遍历过，距离一定更远，为了避免搜索到已经遍历到，且距离更远的单词，需要将它从 dict 中删除
            dict.remove(nextWord);
            // 这一层扩展出的单词进入队列
            queue.offer(nextWord);

            // 记录 nextWord 从 currWord 而来
            from.putIfAbsent(nextWord, new ArrayList<>());
            from.get(nextWord).add(currWord);
            // 记录 nextWord 的 step
            steps.put(nextWord, step);
            if (nextWord.equals(endWord)) {
              found = true;
            }
          }
          charArray[j] = origin;
        }
      }
      step++;
      if (found) {
        break;
      }
    }

    // 第 2 步：深度优先遍历找到所有解，从 endWord 恢复到 beginWord ，所以每次尝试操作 path 列表的头部
    if (found) {
      Deque<String> path = new ArrayDeque<>();
      path.add(endWord);
      dfs_findLadders(from, path, beginWord, endWord, res);
    }
    return res;
  }

  public static void dfs_findLadders(
      Map<String, List<String>> from,
      Deque<String> path,
      String beginWord,
      String cur,
      List<List<String>> res) {
    if (cur.equals(beginWord)) {
      res.add(new ArrayList<>(path));
      return;
    }
    for (String precursor : from.get(cur)) {
      path.addFirst(precursor);
      dfs_findLadders(from, path, beginWord, precursor, res);
      path.removeFirst();
    }
  }

  /**
   * 128. 最长连续序列 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
   *
   * @param nums
   * @return
   */
  public static int longestConsecutive(int[] nums) {
    Set<Integer> set_nums = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      set_nums.add(nums[i]);
    }
    int longestConsecutive = 0;

    for (int integer : set_nums) {
      int currlongestCons = 0;
      int currNum = 0;
      if (!set_nums.contains(integer - 1)) {
        currlongestCons++;
        currNum = integer;
        while (set_nums.contains(currNum + 1)) {
          currlongestCons++;
          currNum++;
        }
        if (longestConsecutive < currlongestCons) {
          longestConsecutive = currlongestCons;
        }
      }
    }
    return longestConsecutive;
  }

  /**
   * 130. 被围绕的区域 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
   *
   * @param board 围棋矩阵
   */
  public static void solve(char[][] board) {
    if (board == null) {
      return;
    }
    int gao = board.length;
    int chang = board[0].length;
    // 从矩阵的边缘向内部扩散寻找没被包围的“O”
    for (int i = 0; i < gao; i++) {
      dfs_solve(board, i, 0);
      dfs_solve(board, i, chang - 1);
    }
    for (int i = 0; i < chang; i++) {
      dfs_solve(board, 0, i);
      dfs_solve(board, gao - 1, i);
    }
    for (int i = 0; i < gao; i++) {
      for (int j = 0; j < chang; j++) {
        if (board[i][j] == '#') {
          board[i][j] = 'O';
        } else if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }
      }
    }
  }

  public static void dfs_solve(char[][] board, int x, int y) {
    int chang = board[0].length;
    int gao = board.length;
    if (x < 0 || x >= gao || y < 0 || y >= chang || board[x][y] != 'O') {
      return;
    }
    board[x][y] = '#';
    dfs_solve(board, x + 1, y);
    dfs_solve(board, x - 1, y);
    dfs_solve(board, x, y + 1);
    dfs_solve(board, x, y - 1);
  }

  /**
   * 131. 分割回文串 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
   *
   * <p>回文串 是正着读和反着读都一样的字符串。
   *
   * @param s
   * @return
   */
  private static List<List<String>> partition_result = new ArrayList<>();
  // 存储s[i][j] 是否是回文串，防止重复计算
  private static boolean[][] checkStr;

  public static List<List<String>> partition(String s) {
    int n = s.length();

    checkStr = new boolean[n][n];
    for (int i = 0; i < n; ++i) {
      Arrays.fill(checkStr[i], true);
    }
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i + 1; j < n; j++) {
        checkStr[i][j] = s.charAt(i) == s.charAt(j) && checkStr[i + 1][j - 1];
      }
    }
    List<String> res = new ArrayList<>();
    dfs_partition(s, res, 0);
    return partition_result;
  }

  public static void dfs_partition(String s, List<String> res, int i) {
    int n = s.length();
    if (i == n) {
      partition_result.add(new ArrayList<>(res));
      return;
    }
    for (int j = i; j < n; j++) {
      if (checkStr[i][j]) {
        res.add(s.substring(i, j + 1));
        dfs_partition(s, res, j - 1);
        res.remove(res.size() - 1);
      }
    }
  }

  /**
   * 132. 分割回文串 II 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
   *
   * <p>返回符合要求的 最少分割次数 。
   *
   * @param s
   * @return
   */
  private static int[] f;

  public static int minCut(String s) {
    int n = s.length();
    f = new int[n];
    f[0] = 0;
    char[] chararr = s.toCharArray();
    for (int i = 1; i < n; i++) {
      // 寻找之前最小的分割
      int min = Integer.MAX_VALUE;
      for (int j = 0; j <= i; j++) {
        if (checkHWS(chararr, j, i)) {
          if (j == 0) {
            min = 0;
          } else if (f[j - 1] + 1 < min) {
            min = f[j - 1] + 1;
          }
        }
      }
      f[i] = min;
    }
    return f[n - 1];
  }

  public static boolean checkHWS(char[] chararr, int left, int right) {
    while (left < right) {
      if (chararr[left] != chararr[right]) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  /**
   * 133. 克隆图 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
   *
   * @param node
   * @return
   */
  private static Map<Node, Node> visited = new HashMap<>();

  public static Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }
    if (visited.containsKey(node)) {
      return visited.get(node);
    }
    Node cloneNode = new Node(node.val, new ArrayList<>());
    visited.put(node, cloneNode);
    for (Node neighbor : node.neighbors) {
      cloneNode.neighbors.add(cloneGraph(neighbor));
    }
    return cloneNode;
  }

  /**
   * 134. 加油站
   *
   * <p>在一条环路上有个加油站，其中第i个加油站有汽油gas[i]升。
   *
   * <p>你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
   *
   * <p>如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
   *
   * @param gas
   * @param cost
   * @return
   */
  public static int canCompleteCircuit(int[] gas, int[] cost) {
    int size = gas.length;
    int result = -1;
    int gasNum = 0;
    for (int i = 0; i < size; i++) {
      if (gasNum + gas[i] < cost[i]) {
        continue;
      }
      gasNum += (gas[i] - cost[i]);
      int newgasNum = gasNum;
      boolean isOK = true;
      for (int j = i + 1; j < size + i + 1; j++) {
        if (newgasNum + gas[j % size] < cost[j % size]) {
          isOK = false;
          break;
        }
        newgasNum += (gas[j % size] - cost[j % size]);
      }
      gasNum = 0;
      if (isOK) {
        return i;
      }
    }
    return result;
  }

  /**
   * 135. 分发糖果 老师想给孩子们分发糖果，有N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。 你需要按照以下要求，帮助老师给这些孩子分发糖果： 每个孩子至少分配到 1
   * 个糖果。 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。 那么这样下来，老师至少需要准备多少颗糖果呢？
   *
   * <p>算法：左规则 右规则 选取对应位置的最大值
   *
   * @param ratings
   * @return
   */
  public static int candy(int[] ratings) {
    int length = ratings.length;
    int[] dp = new int[length];
    dp[0] = 1;
    for (int i = 1; i < length; i++) {
      if (ratings[i - 1] < ratings[i]) {
        dp[i] = dp[i - 1] + 1;
      } else {
        dp[i] = 1;
      }
    }
    int result = 0;
    int right = 1;
    for (int i = length - 2; i >= 0; i--) {
      if (ratings[i + 1] < ratings[i]) {
        right++;
      } else {
        right = 1;
      }
      result += Math.max(dp[i], right);
    }
    return result;
  }

  /**
   * 136. 只出现一次的数字
   *
   * <p>给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
   *
   * @param nums
   * @return
   */
  public static int singleNumber2(int[] nums) {
    Set<Integer> integerSet = new HashSet<>();
    int n = nums.length;
    for (int num : nums) {
      if (!integerSet.contains(num)) {
        integerSet.add(num);
      } else {
        integerSet.remove(num);
      }
    }
    int result = 0;
    for (Integer integer : integerSet) {
      result = (int) integer;
    }
    return result;
  }

  /**
   * 136. 只出现一次的数字
   *
   * <p>给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。 使用异或算法保证使用常数的村塾空间
   *
   * @param nums
   * @return
   */
  public static int singleNumber1(int[] nums) {
    int result = 0;
    for (int num : nums) {
      result ^= num;
    }
    return result;
  }

  /**
   * 137. 只出现一次的数字 II
   *
   * <p>给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
   *
   * @param nums
   * @return
   */
  public static int singleNumber(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (Integer key : map.keySet()) {
      if (map.get(key) == 1) {
        return key;
      }
    }
    return 0;
  }

  /**
   * 138. 复制带随机指针的链表
   *
   * <p>构造已知链表的深拷贝
   *
   * @param head
   * @return
   */
  private static Map<Node, Node> mapNode = new HashMap<>();

  public static Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }
    if (mapNode.containsKey(head)) {
      return mapNode.get(head);
    }
    Node node = new Node(head.val);
    mapNode.put(head, node);
    node.next = copyRandomList(head.next);
    node.random = copyRandomList(head.random);
    return node;
  }

  /**
   * 139. 单词拆分
   *
   * <p>给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
   *
   * <p>动态规划
   *
   * @param s
   * @param wordDict
   * @return
   */
  public static boolean wordBreak1(String s, List<String> wordDict) {
    if (wordDict.contains(s)) {
      return true;
    }
    Set<String> wordDictSet = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[s.length()];
  }

  /**
   * 139. 单词拆分
   *
   * <p>给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
   *
   * <p>递归算法，有问题，有些测试用例会出现超时
   *
   * @param s
   * @param wordDict
   * @return
   */
  public static boolean wordBreak2(String s, List<String> wordDict) {
    if (s.length() == 0) return true;
    if (s.length() >= 151) return false;
    Set<String> wordDictSet = new HashSet<>(wordDict);
    return wordBreak(s, wordDictSet);
  }

  public static boolean wordBreak(String s, Set<String> wordDictSet) {
    if (wordDictSet.contains(s)) {
      return true;
    }
    for (String word : wordDictSet) {
      if (s.startsWith(word) && wordBreak(s.substring(word.length()), wordDictSet)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 140. 单词拆分 II
   *
   * <p>给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子， 使得句子中所有的单词都在词典中。返回所有这些可能的句子。
   *
   * @param s
   * @param wordDict
   * @return
   */
  public static List<String> wordBreak(String s, List<String> wordDict) {
    Map<Integer, List<List<String>>> map = new HashMap<>();
    Set<String> wordDictSet = new HashSet<>(wordDict);
    List<List<String>> wordLists = dfs_wordBreak(s, s.length(), wordDictSet, 0, map);
    List<String> result = new ArrayList<>();
    for (List<String> words : wordLists) {
      result.add(String.join(" ", words));
    }
    return result;
  }

  public static List<List<String>> dfs_wordBreak(
      String s,
      int length,
      Set<String> wordDictSet,
      int index,
      Map<Integer, List<List<String>>> map) {
    if (!map.containsKey(index)) {
      List<List<String>> wordLists = new LinkedList<>();
      if (length == index) {
        wordLists.add(new LinkedList<String>());
      }
      for (int i = index + 1; i <= length; i++) {
        String word = s.substring(index, i);
        if (wordDictSet.contains(word)) {
          List<List<String>> nextWordLists = dfs_wordBreak(s, length, wordDictSet, i, map);
          for (List<String> words : nextWordLists) {
            LinkedList<String> wordList = new LinkedList<>(words);
            // 在开头插入元素，使用LinkedList 速度会变快
            wordList.offerFirst(word);
            wordLists.add(wordList);
          }
        }
      }
      map.put(index, wordLists);
    }
    return map.get(index);
  }

  /**
   * 141. 环形链表
   *
   * <p>
   *
   * <p>快慢指针，如果存在环，快指针在未来的某个时刻一定可以追上慢指针
   *
   * @param head
   * @return
   */
  public static boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    return true;
  }

  /**
   * 142. 环形链表 II 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0
   * 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
   *
   * @param head
   * @return
   */
  public static ListNode detectCycle1(ListNode head) {
    Set<ListNode> set = new HashSet<>();
    if (head == null || head.next == null) {
      return null;
    }
    while (head != null) {
      if (!set.add(head)) {
        return head;
      }
      head = head.next;
    }
    return null;
  }

  /**
   * 142. 环形链表 II 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0
   * 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
   *
   * <p>相遇后，再从头开始前进，另一个指针从相遇位置开始前进，再次相遇的位置就是换的开始节点
   *
   * @param head
   * @return
   */
  public ListNode detectCycle(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode slow = head, fast = head;
    while (fast != null) {
      slow = slow.next;
      if (fast.next != null) {
        fast = fast.next.next;
      } else {
        return null;
      }
      if (fast == slow) {
        ListNode ptr = head;
        while (ptr != slow) {
          ptr = ptr.next;
          slow = slow.next;
        }
        return ptr;
      }
    }
    return null;
  }

  /**
   * 143. 重排链表
   *
   * <p>给定一个单链表L：L0→L1→…→Ln-1→Ln ， 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
   *
   * <p>你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
   *
   * @param head
   */
  public static void reorderList(ListNode head) {
    Deque<ListNode> stack = new ArrayDeque<>();
    while (head != null) {
      stack.addLast(head);
      head = head.next;
    }
    ListNode newHead = stack.peekFirst();
    ListNode tail = newHead;
    while (!stack.isEmpty()) {
      ListNode start = stack.pollFirst();
      ListNode end = stack.pollLast();
      if (start != tail) {
        tail.next = start;
      }
      start.next = end;
      tail = end;
      if (tail != null) {
        tail.next = null;
      }
    }
  }

  /**
   * 144. 二叉树的前序遍历
   *
   * <p>给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
   *
   * @param root
   * @return
   */
  public static List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfs_preorderTraversal(root, result);
    return result;
  }

  public static void dfs_preorderTraversal(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    result.add(root.val);
    dfs_preorderTraversal(root.left, result);
    dfs_preorderTraversal(root.right, result);
  }

  /**
   * 145. 二叉树的后序遍历
   *
   * <p>给定一个二叉树，返回它的 后序 遍历。
   *
   * @param root
   * @return
   */
  public static List<Integer> postorderTraversal1(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    dfs_postorderTraversal(root, result);
    return result;
  }

  public static void dfs_postorderTraversal(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    dfs_postorderTraversal(root.left, result);
    dfs_postorderTraversal(root.right, result);
    result.add(root.val);
  }

  public static List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode prev = null;
    while (root != null || !stack.isEmpty()) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      if (root.right == null || root.right == prev) {
        result.add(root.val);
        prev = root;
        root = null;
      } else {
        stack.push(root);
        root = root.right;
      }
    }
    return result;
  }

  /**
   * 147. 对链表进行插入排序
   *
   * @param head
   * @return
   */
  public static ListNode insertionSortList(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode XNNode = new ListNode();
    XNNode.next = new ListNode(head.val);
    head = head.next;
    while (head != null) {
      ListNode p = XNNode.next;
      ListNode q = p;
      // 判断当前元素是不是比第一个元素小
      if (p.val > head.val) {
        XNNode.next = new ListNode(head.val);
        XNNode.next.next = p;
        head = head.next;
        continue;
      }
      while (p != null) {
        if (p.next != null && p.next.val > head.val) {
          p.next = new ListNode(head.val, p.next);
          break;
        }
        q = p;
        p = p.next;
      }
      if (p == null) {
        q.next = new ListNode(head.val);
      }
      head = head.next;
    }
    return XNNode.next;
  }

  /**
   * 148. 排序链表 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 O(nlog(n))
   *
   * @param head
   * @return
   */
  public static ListNode sortList(ListNode head) {
    return sortSubList(head, null);
  }

  public static ListNode sortSubList(ListNode head, ListNode tail) {
    if (head == null) {
      return null;
    }
    if (head.next == tail) {
      head.next = null;
      return head;
    }

    ListNode slow = head, fast = head;
    while (fast != tail) {
      slow = slow.next;
      fast = fast.next;
      if (fast != tail) {
        fast = fast.next;
      }
    }

    ListNode mid = slow;
    ListNode leftList = sortSubList(head, mid);
    ListNode rightList = sortSubList(mid, tail);

    ListNode XNNode = new ListNode();
    ListNode p = XNNode, lp = leftList, rp = rightList;
    while (lp != null && rp != null) {
      if (lp.val < rp.val) {
        p.next = lp;
        lp = lp.next;
      } else {
        p.next = rp;
        rp = rp.next;
      }
      p = p.next;
    }
    if (lp != null) {
      p.next = lp;
    }
    if (rp != null) {
      p.next = rp;
    }
    return XNNode.next;
  }

  /**
   * 149. 直线上最多的点数
   *
   * <p>给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
   *
   * @param points 坐标集
   * @return 统一直线的个数
   */
  public static int maxPoints(int[][] points) {
    int n = points.length;
    if (n < 3) {
      return n;
    }
    int res = 0;
    for (int i = 0; i < n - 1; i++) {
      // 不能使用double作为key，会存在精度问题
      Map<String, Integer> slope = new HashMap<>();
      int repeat = 0;
      int tmp_max = 0;
      for (int j = i + 1; j < n; j++) {
        int dy = points[i][1] - points[j][1];
        int dx = points[i][0] - points[j][0];
        if (dy == 0 && dx == 0) {
          repeat++;
          continue;
        }
        int g = gcd(dy, dx);
        if (g != 0) {
          dy /= g;
          dx /= g;
        }
        String tmp = String.valueOf(dy) + "/" + String.valueOf(dx);
        slope.put(tmp, slope.getOrDefault(tmp, 0) + 1);
        tmp_max = Math.max(tmp_max, slope.get(tmp));
      }
      res = Math.max(res, repeat + tmp_max + 1);
    }
    return res;
  }

  public static int gcd(int dy, int dx) {
    if (dx == 0) return dy;
    else return gcd(dx, dy % dx);
  }

  /**
   * 150. 逆波兰表达式求值(后缀表达式)
   *
   * <p>有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。 || "-".equals(tokens[i]) ||
   *
   * @param tokens 字符串数组
   * @return
   */
  public static int evalRPN(String[] tokens) {
    Deque<Integer> stack = new ArrayDeque<Integer>();
    int length = tokens.length;
    for (int i = 0; i < length; i++) {
      if ("+-*/".contains(tokens[i])) {
        int firstNum = stack.poll();
        int secondNum = stack.poll();
        int result = 0;
        switch (tokens[i]) {
          case "+":
            result = secondNum + firstNum;
            break;
          case "-":
            result = secondNum - firstNum;
            break;
          case "*":
            result = secondNum * firstNum;
            break;
          case "/":
            result = secondNum / firstNum;
            break;
        }
        stack.push(result);

      } else {
        stack.push(Integer.parseInt(tokens[i]));
      }
    }
    return stack.poll();
  }

  /**
   * 151. 翻转字符串里的单词
   *
   * <p>单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
   *
   * @param s
   * @return
   */
  public static String reverseWords(String s) {
    if ("".equals(s)) {
      return "";
    }
    String[] words = s.split(" ");
    StringBuilder sb = new StringBuilder();
    Stack<String> stack = new Stack<>();
    for (String word : words) {
      word = word.trim();
      if (!"".equals(word)) {
        stack.push(word);
      }
    }
    while (!stack.isEmpty()) {
      sb.append(stack.pop()).append(" ");
    }
    return sb.toString().trim();
  }

  /**
   * 152. 乘积最大子数组
   *
   * <p>给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
   *
   * <p>动态规划
   *
   * @param nums
   * @return
   */
  public static int maxProduct(int[] nums) {
    if (nums == null) {
      return 0;
    }
    int length = nums.length;
    int[] dpMax = new int[length];
    int[] dpMin = new int[length];
    dpMax[0] = nums[0];
    int max = dpMax[0];
    for (int i = 1; i < length; i++) {
      dpMax[i] = Math.max(dpMax[i - 1] * nums[i], Math.max(nums[i], dpMin[i - 1] * nums[i]));
      if (max < dpMax[i]) {
        max = dpMax[i];
      }

      dpMin[i] = Math.min(dpMax[i - 1] * nums[i], Math.min(nums[i], dpMin[i - 1] * nums[i]));
    }
    return max;
  }

  /**
   * 153. 寻找旋转排序数组中的最小值
   *
   * <p>提示数组中不存在相同的数字
   *
   * @param nums 旋转排序数组
   * @return 最小值
   */
  public static int findMin1(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = (right + left) / 2;
      if (nums[left] > nums[mid]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return nums[left];
  }

  /**
   * 154. 寻找旋转排序数组中的最小值 II
   *
   * <p>给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
   *
   * <p>算法思路，对于中间元素和右端元素分三种情况分类讨论 1. nums[right] > nums[mid] 最小值在mid元素的左端 2. nums[right] < nums[mid]
   * 最小值在mid 元素的右端 3. nums[right] = nums[mid] 此时不能判断最小值所在的空间，我们把右端的元素，向左移动一位 *
   *
   * @param nums
   * @return
   */
  public static int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = (right + left) / 2;
      if (nums[right] > nums[mid]) {
        right = mid;
      } else if (nums[right] < nums[mid]) {
        left = mid + 1;
      } else {
        right = right - 1;
      }
    }
    return nums[left];
  }

  /**
   * 160. 相交链表
   *
   * <p>编写一个程序，找到两个单链表相交的起始节点。
   *
   * @param headA
   * @param headB
   * @return
   */
  public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
    Set<ListNode> nodeSet = new HashSet<>();
    while (headA != null || headB != null) {
      if (headA != null && !nodeSet.add(headA)) {
        return headA;
      }
      if (headB != null && !nodeSet.add(headB)) {
        return headB;
      }
      if (headA != null) headA = headA.next;
      if (headB != null) headB = headB.next;
    }
    return null;
  }

  /**
   * 进行两次遍历，在两个遍历相等的时候就是两个链表的交点
   *
   * @param headA
   * @param headB
   * @return
   */
  public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode nodea = headA, nodeb = headB;
    while (nodea != nodeb) {
      if (nodea == null) nodea = headB;
      else nodea = nodea.next;
      if (nodeb == null) nodeb = headA;
      else nodeb = nodeb.next;
    }
    return nodeb;
  }

  /**
   * 162. 寻找峰值
   *
   * <p>峰值元素是指其值大于左右相邻值的元素。
   *
   * <p>给你一个输入数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
   *
   * @param nums
   * @return
   */
  public static int findPeakElement(int[] nums) {
    int length = nums.length - 1;
    for (int i = 1; i < length; i++) {
      if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
        return i;
      }
    }
    if (length == 0) {
      return 0;
    }
    if (nums[length] > nums[length - 1]) {
      return length;
    }
    return 0;
  }

  /**
   * 164. 最大间距
   *
   * <p>给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
   *
   * <p>如果数组元素个数小于 2，则返回 0。
   *
   * @param nums
   * @return
   */
  public static int maximumGap1(int[] nums) {
    int length = nums.length;
    if (length < 2) {
      return 0;
    }
    int minVal = Arrays.stream(nums).min().getAsInt();
    int maxVal = Arrays.stream(nums).max().getAsInt();
    int d = Math.max(1, (maxVal - minVal) / (length - 1));
    int bucketNum = (maxVal - minVal) / d + 1;
    // 保存每个桶中的最大值和最小值
    int[][] bucket = new int[bucketNum][2];
    for (int i = 0; i < bucketNum; i++) {
      Arrays.fill(bucket[i], -1);
    }
    for (int num : nums) {
      // 计算对应的桶索引
      int idx = (num - minVal) / d;
      // 计算对应桶的最大值和最小值
      if (bucket[idx][0] == -1) {
        bucket[idx][0] = num;
        bucket[idx][1] = num;
      } else {
        bucket[idx][0] = Math.min(bucket[idx][0], num);
        bucket[idx][1] = Math.max(bucket[idx][1], num);
      }
    }
    int result = 0;
    int minIndex = -1;
    for (int i = 0; i < bucketNum; i++) {
      if (bucket[i][0] == -1) {
        continue;
      }
      if (minIndex != -1) {
        result = Math.max(result, bucket[i][0] - bucket[minIndex][1]);
      }
      minIndex = i;
    }
    return result;
  }
  /**
   * 164. 最大间距
   *
   * <p>给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
   *
   * <p>如果数组元素个数小于 2，则返回 0。
   *
   * <p>使用系统自带的排序算法的效果更好
   *
   * @param nums
   * @return
   */
  public int maximumGap(int[] nums) {
    int length = nums.length;
    if (length < 2) {
      return 0;
    }
    Arrays.sort(nums);
    int max = Integer.MIN_VALUE;
    for (int i = 1; i < length; i++) {
      if (nums[i] - nums[i - 1] > max) {
        max = nums[i] - nums[i - 1];
      }
    }
    return max;
  }

  /**
   * 165. 比较版本号
   *
   * <p>输入：version1 = "1.01", version2 = "1.001"
   *
   * <p>输出：0
   *
   * <p>解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
   *
   * @param version1
   * @param version2
   * @return
   */
  public static int compareVersion(String version1, String version2) {
    String[] v1arr = version1.split("\\.");
    String[] v2arr = version2.split("\\.");
    int size = Math.max(v1arr.length, v2arr.length);
    for (int i = 0; i < size; i++) {
      String subVersion1 = "0";
      String subVersion2 = "0";
      if (i < v1arr.length) {
        subVersion1 = v1arr[i];
      }
      if (i < v2arr.length) {
        subVersion2 = v2arr[i];
      }

      if (Integer.parseInt(subVersion1) > Integer.parseInt(subVersion2)) {
        return 1;
      } else if (Integer.parseInt(subVersion1) < Integer.parseInt(subVersion2)) {
        return -1;
      }
    }
    return 0;
  }

  private static void deleteBeforeZero(String subVersion1) {
    for (int j = 0; j < subVersion1.length(); j++) {
      if (subVersion1.charAt(j) == '0') {
        continue;
      }
      subVersion1 = subVersion1.substring(j);
      break;
    }
    subVersion1 = "0";
  }

  /**
   * 166. 分数到小数
   *
   * <p>给定两个整数，分别表示分数的分子numerator 和分母 denominator，以 字符串形式返回小数 。
   *
   * <p>如果小数部分为循环小数，则将循环的部分括在括号内。
   *
   * <p>如果存在多个答案，只需返回 任意一个 。
   *
   * <p>对于所有给定的输入，保证 答案字符串的长度小于 104 。
   *
   * @param numerator
   * @param denominator
   * @return
   */
  public static String fractionToDecimal(int numerator, int denominator) {
    Set<Long> setNum = new HashSet<>();
    Map<Long, Integer> map = new HashMap<>();
    StringBuilder result = new StringBuilder();
    List<Character> charArr = new ArrayList<>();
    long denominatorD = denominator;
    long numeratorD = numerator;
    if (numeratorD * denominatorD < 0) {
      result.append("-");
    }

    result.append((Math.abs(numeratorD) / Math.abs(denominatorD)));
    if ((double) numerator % denominatorD != 0) {
      result.append(".");
    }
    long num = numeratorD % denominatorD;
    if (num < 0) {
      num = -num;
    }
    if (denominatorD < 0) {
      denominatorD = -denominatorD;
    }
    int index = 0;
    while (num != 0) {
      num = num * 10;
      if (setNum.add(num)) {
        map.put(num, index++);
        charArr.add((char) (num / denominatorD + '0'));
        num = num % denominatorD;
      } else {
        int startIndex = map.get(num);
        charArr.add(startIndex, '(');
        charArr.add(')');
        break;
      }
    }
    for (Character character : charArr) {
      result.append(character);
    }
    return result.toString();
  }

  /**
   * 167. 两数之和 II - 输入有序数组
   *
   * <p>输入：numbers = [2,7,11,15], target = 9
   *
   * <p>输出：[1,2]
   *
   * <p>解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
   */
  public static int[] twoSum1(int[] numbers, int target) {
    int[] result = new int[2];
    int length = numbers.length;
    for (int i = 0; i < length; i++) {
      if (i < length - 2 && numbers[i] + numbers[i + 1] > target) {
        break;
      }
      for (int j = i + 1; j < length; j++) {
        if (numbers[i] + numbers[j] == target) {
          result[0] = i + 1;
          result[1] = j + 1;
          break;
        }
      }
    }
    return result;
  }

  public static int[] twoSum2(int[] numbers, int target) {
    int[] result = new int[2];
    Map<Integer, Integer> mapNumber = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
      mapNumber.put(numbers[i], i + 1);
    }
    for (int i = 0; i < numbers.length; i++) {
      int num = target - numbers[i];
      if (mapNumber.containsKey(num)) {
        int j = mapNumber.get(num);
        if (j != i + 1) {
          result[0] = i + 1;
          result[1] = j;
          break;
        }
      }
    }
    return result;
  }

  public static int[] twoSum(int[] numbers, int target) {
    int start = 0, end = numbers.length - 1;
    while (start < end) {
      if (numbers[start] + numbers[end] > target) {
        end--;
      } else if (numbers[start] + numbers[end] < target) {
        start++;
      } else {
        return new int[] {start + 1, end + 1};
      }
    }
    return null;
  }

  /**
   * 168. Excel表列名称
   *
   * <p>给定一个正整数，返回它在 Excel 表中相对应的列名称。
   *
   * <p>输入: 1
   *
   * <p>输出: "A"
   */
  public static String convertToTitle(int columnNumber) {
    String result = "";
    char[] charArr =
        new char[] {
          'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
          'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
    while (columnNumber != 0) {
      int num = columnNumber % 26;
      if (num == 0) {
        num = 26;
        columnNumber--;
      }
      result = charArr[num - 1] + result;
      columnNumber = columnNumber / 26;
    }
    return result;
  }

  /**
   * 169. 多数元素
   *
   * <p>给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
   *
   * <p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。
   */
  public static int majorityElement(int[] nums) {
    Deque<Integer> stack = new ArrayDeque<Integer>();
    stack.add(nums[0]);
    int length = nums.length;
    for (int i = 1; i < length; i++) {
      if (!stack.isEmpty()) {
        if (stack.peek() == nums[i]) {
          stack.add(nums[i]);
        } else {
          stack.pop();
        }
      } else {
        stack.push(nums[i]);
      }
    }
    return stack.pop();
  }

  /**
   * 171. Excel表列序号
   *
   * <p>给定一个Excel表格中的列名称，返回其相应的列序号。
   *
   * <p>输入: "A"
   *
   * <p>输出: 1
   */
  public static int titleToNumber(String columnTitle) {
    int length = columnTitle.length() - 1;
    int result = 0;
    for (int i = length, index = 0; i >= 0; i--, index++) {
      char ch = columnTitle.charAt(i);
      int num = (ch + 0) - 64;
      result += num * Math.pow(26, index);
    }
    return result;
  }

  /**
   * 172. 阶乘后的零
   *
   * <p>给定一个整数 n，返回 n! 结果尾数中零的数量。
   */
  public static int trailingZeroes(int n) {
    int zeroCount = 0;
    long currentMultiple = 5;
    while (n > 0) {
      n /= 5;
      zeroCount += n;
    }
    return zeroCount;
  }

  /**
   * 174. 地下城游戏
   *
   * @param dungeon
   * @return
   */
  public int calculateMinimumHP(int[][] dungeon) {
    int m = dungeon.length;
    int n = dungeon[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = dungeon[0][0];
    for (int i = 1; i < n; i++) {
      dp[0][i] = dp[0][i - 1] + dungeon[0][i];
    }
    for (int i = 1; i < m; i++) {
      dp[i][0] = dp[i - 1][0] + dungeon[i][0];
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + dungeon[i][j];
      }
    }
    return dp[m - 1][n - 1];
  }

  public static void main(String[] args) {
    TreeNode tree =
        new TreeNode(
            1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3, new TreeNode(6), new TreeNode(7)));
    Node node =
        new Node(
            1,
            new Node(2, new Node(4), new Node(5), null),
            new Node(3, new Node(6), new Node(7), null),
            null);
    TreeNode tree1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    TreeNode tree2 = new TreeNode(2, new TreeNode(1), new TreeNode(1));
    ListNode five = new ListNode(2, new ListNode(5, new ListNode(9)));
    ListNode four = new ListNode(-3, five);
    ListNode l1 = new ListNode(-10, four);
    TreeNode l2 =
        new TreeNode(4, new TreeNode(9, new TreeNode(5), new TreeNode(1)), new TreeNode(0));
    ListNode l3 = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
    //
    ListNode ListNode1 = new ListNode(1);
    ListNode ListNode2 = new ListNode(3);
    ListNode ListNode3 = new ListNode(5);
    ListNode ListNode4 = new ListNode(7);
    ListNode ListNode5 = new ListNode(9);
    ListNode ListNode6 = new ListNode(11);
    ListNode ListNode7 = new ListNode(13);
    ListNode ListNode8 = new ListNode(15);
    ListNode ListNode9 = new ListNode(17);
    ListNode ListNode10 = new ListNode(19);
    ListNode ListNode11 = new ListNode(21);
    ListNode ListNode12 = new ListNode(23);
    ListNode ListNode13 = new ListNode(25);
    ListNode ListNode14 = new ListNode(27);
    ListNode ListNode15 = new ListNode(29);
    ListNode ListNode16 = new ListNode(30);
    ListNode ListNode17 = new ListNode(31);
    ListNode ListNode18 = new ListNode(32);
    ListNode1.next = ListNode2;
    ListNode2.next = ListNode3;
    ListNode3.next = ListNode4;
    ListNode4.next = ListNode5;
    ListNode5.next = ListNode6;
    ListNode6.next = ListNode7;
    ListNode7.next = ListNode8;
    ListNode8.next = ListNode9;
    ListNode9.next = ListNode10;
    ListNode10.next = ListNode11;
    ListNode11.next = ListNode12;
    ListNode12.next = ListNode13;
    ListNode13.next = ListNode14;
    ListNode14.next = ListNode15;
    ListNode15.next = ListNode16;
    ListNode16.next = ListNode17;
    ListNode17.next = ListNode18;

    ListNode f = new ListNode(5);
    //		ListNode l2 = new ListNode(1, three);
    //		[[1,2],[3,5],[6,7],[8,10],[12,16]]
    int[] preorder = new int[] {4, 1, 2, 1, 2};
    int[] inorder = new int[] {4, 4, 1, 5, 1};
    int[] postorder = new int[] {9, 15, 7, 20, 3};
    List<List<Integer>> triangle = new ArrayList<>();
    triangle.add(new ArrayList<>(Collections.singletonList(2)));
    triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
    triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
    triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
    String[] strArr =
        new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

    //    List<String> strlist = new ArrayList<>(Arrays.asList("apple", "pen"));
    long start = new Date().getTime();
    int[] intArr = new int[] {5, 25, 75};
    long end = new Date().getTime();
    System.out.println("程序运行时间: " + (end - start));
  }
}
