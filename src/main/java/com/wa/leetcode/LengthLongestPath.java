package com.wa.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * LengthLongestPath
 * 2022-04-20 08:58
 *
 * @author wuao
 */
public class LengthLongestPath {

    public static void main(String[] args) {
        String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String input2 = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        String input3 = "a";
        String input4 = "file1.txt\nfile2.txt\nlongfile.txt";


        System.out.println(lengthLongestPath(input));
        System.out.println(lengthLongestPath(input2));
        System.out.println(lengthLongestPath(input3));
        System.out.println(lengthLongestPath(input4));
    }


    private static int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, max = 0;
        while (i < input.length()) {
            int level = 1;
            while (i < input.length() && input.charAt(i) == '\t') {
                i++;
                level++;
            }

            boolean isFile = false;
            int len = 0;
            while (i < input.length() && input.charAt(i) != '\n') {
                if (input.charAt(i) == '.') {
                    isFile = true;
                }
                len++;
                i++;
            }

            i++; // 跳过换行符

            while (stack.size() >= level) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                len += stack.peek() + 1;
            }
            if (isFile) {
                max = Math.max(max, len);
            } else {
                stack.push(len);
            }
        }

        return max;
    }

    // 错误
    private static int lengthLongestPath2(String input) {
        Deque<String> deque = new LinkedList<>();
        int i = 0, max = 0, maxLevel = 0;
        while (i < input.length()) {
            int level = 0;
            while (input.charAt(i) == '\n' || input.charAt(i) == '\t') {
                i++;
                level++;
            }
            if (level == 0) {
                level = 1;
            }

            StringBuilder sb = new StringBuilder();
            while (i < input.length() && input.charAt(i) != '\n') {
                sb.append(input.charAt(i));
                i++;
            }

            String s = sb.toString();
            if (level > deque.size()) {
                deque.addLast(s);
                maxLevel = Math.max(maxLevel, level);
            } else if (level == deque.size()) {
                if (deque.getLast().length() < s.length()) {
                    deque.removeLast();
                    deque.addLast(s);
                }
            } else {
                if (deque.size() >= maxLevel && deque.getLast().contains(".")) {
                    int sum = 0;
                    Deque<String> tmp = new LinkedList<>();
                    while (!deque.isEmpty()) {
                        sum += deque.getLast().length() + 1;
                        if (deque.size() < level) {
                            tmp.addFirst(deque.getLast());
                        }
                        deque.removeLast();
                    }
                    max = Math.max(max, sum - 1);
                    deque = tmp;
                    deque.addLast(s);
                }
            }
        }

        if (deque.size() >= maxLevel && deque.getLast().contains(".")) {
            int sum = 0;
            while (!deque.isEmpty()) {
                sum += deque.getLast().length() + 1;
                deque.removeLast();
            }
            max = Math.max(max, sum - 1);
        }

        return max;
    }
}
