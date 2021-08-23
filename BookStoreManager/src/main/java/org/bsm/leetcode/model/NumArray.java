package org.bsm.leetcode.model;

/**
 * 307. 区域和检索 - 数组可修改
 *
 * <p>给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。
 *
 * <p>使用线段树算法
 */
public class NumArray {
  int[] tree;
  int n;

  void update(int pos, int val) {
    pos += n;
    tree[pos] = val;
    while (pos > 0) {
      int left = pos;
      int right = pos;
      if (pos % 2 == 0) {
        right = pos + 1;
      } else {
        left = pos - 1;
      }
      // parent is updated after child is updated
      tree[pos / 2] = tree[left] + tree[right];
      pos /= 2;
    }
  }

  public int sumRange(int l, int r) {
    // get leaf with value 'l'
    l += n;
    // get leaf with value 'r'
    r += n;
    int sum = 0;
    while (l <= r) {
      if ((l % 2) == 1) {
        sum += tree[l];
        l++;
      }
      if ((r % 2) == 0) {
        sum += tree[r];
        r--;
      }
      l /= 2;
      r /= 2;
    }
    return sum;
  }

  public NumArray(int[] nums) {
    if (nums.length > 0) {
      n = nums.length;
      tree = new int[n * 2];
      buildTree(nums);
    }
  }

  private void buildTree(int[] nums) {
    for (int i = n, j = 0; i < 2 * n; i++, j++) {
      tree[i] = nums[j];
    }
    for (int i = n - 1; i > 0; --i) {
      tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
  }
}
