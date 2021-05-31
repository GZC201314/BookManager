package org.bsm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155. 最小栈
 *
 * <p>设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * <p>push(x) —— 将元素 x 推入栈中。 pop()—— 删除栈顶的元素。 top()—— 获取栈顶元素。 getMin() —— 检索栈中的最小元素。
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
