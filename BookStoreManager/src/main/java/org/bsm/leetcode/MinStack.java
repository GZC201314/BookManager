package org.bsm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 146. LRU 缓存机制
 *
 * @author GZC-
 * @create 2021-05-26 10:48
 */
public class MinStack {

  /** initialize your data structure here. */
  public int min = Integer.MAX_VALUE;

  Deque<Integer> stack = null;
  Deque<Integer> minStack = new ArrayDeque<>();

  public MinStack() {
    stack = new ArrayDeque<>();
  }

  public void push(int val) {
    if (minStack.isEmpty()) {
      minStack.push(val);
    } else if (minStack.peek() >= val) {
      stack.push(val);
    } else {
      minStack.push(minStack.peek());
    }
  }

  public void pop() {
    stack.pop();
    minStack.pop();
  }

  public int top() {
    if (stack != null) {
      return stack.peek();
    }
    return 0;
  }

  public int getMin() {
    return min;
  }
}
