package org.bsm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 *
 * <p>请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * @author GZC-
 * @create 2021-07-09 16:12
 */
public class MyStack {
  /** Initialize your data structure here. */
  Queue<Integer> queue1;

  Queue<Integer> queue2;

  public MyStack() {
    queue1 = new ArrayDeque<>();
    queue2 = new ArrayDeque<>();
  }

  /** Push element x onto stack. */
  public void push(int x) {
    queue2.offer(x);
    while (!queue1.isEmpty()) {
      queue2.offer(queue1.poll());
    }
    Queue<Integer> temp = queue1;
    queue1 = queue2;
    queue2 = temp;
  }

  /** Removes the element on top of the stack and returns that element. */
  public int pop() {
    return queue1.poll();
  }

  /** Get the top element. */
  public int top() {
    return queue1.peek();
  }

  /** Returns whether the stack is empty. */
  public boolean empty() {
    return queue1.isEmpty();
  }
}
