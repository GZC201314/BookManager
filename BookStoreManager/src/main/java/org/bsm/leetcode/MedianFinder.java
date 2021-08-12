package org.bsm.leetcode;

import java.util.PriorityQueue;

/**
 * @author GZC-
 * @create 2021-08-12 23:26
 */
public class MedianFinder {
  // 大顶堆用于存储较小的一半元素
  private PriorityQueue<Integer> maxHeap;
  // 小顶堆用于存储较大的一半元素
  private PriorityQueue<Integer> minHeap;
  /** initialize your data structure here. */
  public MedianFinder() {
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if (maxHeap.isEmpty()) {
      maxHeap.add(num);
      return;
    }
    if (num <= maxHeap.peek()) {
      maxHeap.add(num);
    } else {
      minHeap.add(num);
    }

    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.add(maxHeap.remove());
    }
    if (maxHeap.size() < minHeap.size()) {
      maxHeap.add(minHeap.remove());
    }
  }

  public double findMedian() {
    if (maxHeap.size() - minHeap.size() == 1) {
      return maxHeap.peek();
    } else {
      return (minHeap.peek() - maxHeap.peek()) / 2.0 + maxHeap.peek();
    }
  }
}
