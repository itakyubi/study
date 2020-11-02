package com.wa.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * RemoveDuplicateLetters
 * 2020-11-02 17:47
 *
 * @author wuao
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }

    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        HashSet<Character> seen = new HashSet<>();
        HashMap<Character, Integer> lastIndex = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.empty() && c < stack.peek() && lastIndex.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                stack.push(c);
                seen.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
