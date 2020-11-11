package com.wa.leetcode;

/**
 * VerifyPreorderSerializationOfABinaryTree
 * 2020-11-11 16:46
 *
 * @author wuao
 */
public class VerifyPreorderSerializationOfABinaryTree {

    public static void main(String[] args) {
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    public static boolean isValidSerialization(String preorder) {
        int count = 1;

        for (String node : preorder.split(",")) {
            count--;
            if (count < 0)
                return false;
            if (!node.equals("#"))
                count += 2;
        }

        return count == 0;
    }

    public static boolean isValidSerialization2(String preorder) {
        int count = 1;

        for (int i = 0; i < preorder.length(); i++) {
            if (preorder.charAt(i) == ',') {
                count--;
                if (count < 0)
                    return false;
                if (preorder.charAt(i - 1) != '#')
                    count += 2;
            }
        }

        count = preorder.charAt(preorder.length() - 1) == '#' ? count - 1 : count + 1;

        return count == 0;
    }
}
