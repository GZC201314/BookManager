package org.bsm.leetcode;

import java.util.HashMap;

/**
 * @author GZC-
 * @create 2021-06-27 19:03
 */
public class TrieNode {
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  String word = null;

  public TrieNode() {}
}
