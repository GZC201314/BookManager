package org.bsm.leetcode;

import java.util.*;

import org.bsm.leetcode.model.ListNode;
import org.bsm.leetcode.model.TreeNode;

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

    private static final int SEG_COUNT = 4;
    private static List<String> ans = new ArrayList<String>();
    private static int[] segments;

    public static List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public static void dfs(String s, int segId, int segStart) {
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
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
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
        List<TreeNode> treeResult = generateTrees(1, n);
        return treeResult;

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

        for (int i = 2; i < n + 1; i++)
            for (int j = 1; j < i + 1; j++)
                dp[i] += dp[j - 1] * dp[i - j];

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
        return (isSymmetric(leftnode.left, rightNode.right) && isSymmetric(leftnode.right, rightNode.left));
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

    public static TreeNode buildTree1(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        // 前序遍历第一个节点是根节点
        int root_val = preorder[preStart];
        TreeNode rootNode = new TreeNode(root_val);
        int pIndex = map.get(root_val);
        rootNode.left = buildTree1(preorder, inorder, preStart + 1, pIndex - inStart + preStart, inStart, pIndex - 1);
        rootNode.right = buildTree1(preorder, inorder, pIndex - inStart + preStart + 1, preEnd, pIndex + 1, inEnd);
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
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
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
     * @param root
     */
    public static void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr !=null){
            TreeNode pre = curr.left;
            if(pre !=null){
                while (pre.right!=null){
                    pre = pre.right;
                }
                pre.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr  = curr.right;
        }
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(5, null, new TreeNode(6)));
        TreeNode tree1 = new TreeNode(3, new TreeNode(2), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        TreeNode tree2 = new TreeNode(2, new TreeNode(1), new TreeNode(1));
        ListNode five = new ListNode(0, new ListNode(5, new ListNode(9)));
        ListNode four = new ListNode(-3, five);
        ListNode l1 = new ListNode(-10, four);
//
//		ListNode f = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode f = new ListNode(5);
//		ListNode l2 = new ListNode(1, three);
//		[[1,2],[3,5],[6,7],[8,10],[12,16]]
//		ListNode[] nodeArr = new ListNode[] { l1, l2, l3 };
//		int[][] arr = new int[][] { { 1, 3, 5, 7 } };
//		char[][] arr = new char[][] {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//		int[][] arr = new int[][] { { 1, 5 } };
        int[] preorder = new int[]{-10, -3, 0, 5, 9};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
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
        flatten(tree);
//        System.out.println();
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
