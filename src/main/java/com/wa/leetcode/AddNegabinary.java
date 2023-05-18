package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AddNegabinary
 * https://leetcode.cn/problems/adding-two-negabinary-numbers/
 * 1073. 负二进制数相加
 * 2023/5/18 9:08 AM
 *
 * @author wuao
 */
public class AddNegabinary {

    /*
    给出基数为 -2的两个数arr1 和arr2，返回两数相加的结果。
    数字以数组形式给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，arr= [1,1,0,1]表示数字(-2)^3+ (-2)^2 + (-2)^0 = -3。数组形式中的数字 arr 也同样不含前导零：即arr == [0]或arr[0] == 1。
    返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。

    示例 1：
        输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
        输出：[1,0,0,0,0]
        解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
    示例 2：
        输入：arr1 = [0], arr2 = [0]
        输出：[0]
    示例 3：
        输入：arr1 = [0], arr2 = [1]
        输出：[1]
            
    提示：
        1 <= arr1.length,arr2.length <= 1000
        arr1[i]和arr2[i]都是0或1
        arr1和arr2都没有前导0
    */

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1, 1, 1}, arr2 = {1, 0, 1};
        int[] arr12 = {0}, arr22 = {0};
        int[] arr13 = {0}, arr23 = {1};
        int[] arr14 = {1}, arr24 = {1, 1};
        int[] arr15 = {1, 0, 1}, arr25 = {1, 0, 1};

        /*System.out.println(Arrays.toString(addNegabinary(arr1, arr2)));
        System.out.println(Arrays.toString(addNegabinary(arr12, arr22)));
        System.out.println(Arrays.toString(addNegabinary(arr13, arr23)));
        System.out.println(Arrays.toString(addNegabinary(arr14, arr24)));*/
        System.out.println(Arrays.toString(addNegabinary(arr15, arr25)));
    }

    // a+b+carry=x
    // x == 0 -> carry = 0
    // x == 1 -> carry = 0
    // x >= 2 -> x = x -2 && carry = -1
    // x == -1 -> x = 1 && carry = 1（当前位不够向高位借一个，但是由于符号相反所以carry从-1变1）
    private static int[] addNegabinary(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        int i = arr1.length - 1, j = arr2.length - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = 0;
            if (i >= 0) {
                x += arr1[i];
            }
            if (j >= 0) {
                x += arr2[j];
            }
            x += carry;

            if (x == 0 || x == 1) {
                carry = 0;
            } else if (x >= 2) {
                x -= 2;
                carry = -1;
            } else {
                x = 1;
                carry = 1;
            }
            list.add(x);
            i--;
            j--;
        }

        while (list.size() > 1 && list.get(list.size() - 1) == 0) {
            list.remove(list.size() - 1);
        }

        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(list.size() - 1 - k);
        }
        return res;
    }
}
