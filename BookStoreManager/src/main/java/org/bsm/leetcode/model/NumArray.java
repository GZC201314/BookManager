package org.bsm.leetcode.model;

/**
 * @author GZC-
 * @create 2021-08-17 21:44
 */
public class NumArray {
  int[] sumArray;

  public NumArray(int[] nums) {
    sumArray = new int[nums.length + 1];
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      sumArray[i + 1] = sum;
    }
  }

  public int sumRange(int left, int right) {
    return sumArray[right] - sumArray[left];
  }
}
