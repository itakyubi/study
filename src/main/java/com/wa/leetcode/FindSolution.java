package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FindSolution
 * https://leetcode.cn/problems/find-positive-integer-solution-for-a-given-equation/
 * 1237. 找出给定方程的正整数解
 * 2023/2/18 2:48 下午
 *
 * @author wuao
 */
public class FindSolution {

    /*
    给你一个函数f(x, y)和一个目标结果z，函数公式未知，请你计算方程f(x,y) == z所有可能的正整数 数对x 和 y。满足条件的结果数对可以按任意顺序返回。
    尽管函数的具体式子未知，但它是单调递增函数，也就是说：
    f(x, y) < f(x + 1, y)
    f(x, y) < f(x, y + 1)
    函数接口定义如下：
    interface CustomFunction {
        public:
        // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
        int f(int x, int y);
    };
    你的解决方案将按如下规则进行评判：
    判题程序有一个由 CustomFunction 的 9 种实现组成的列表，以及一种为特定的 z 生成所有有效数对的答案的方法。
    判题程序接受两个输入：function_id（决定使用哪种实现测试你的代码）以及目标结果 z 。
    判题程序将会调用你实现的 findSolution 并将你的结果与答案进行比较。
    如果你的结果与答案相符，那么解决方案将被视作正确答案，即 Accepted 。
            

    示例 1：
        输入：function_id = 1, z = 5
        输出：[[1,4],[2,3],[3,2],[4,1]]
        解释：function_id = 1 暗含的函数式子为 f(x, y) = x + y
        以下 x 和 y 满足 f(x, y) 等于 5：
        x=1, y=4 -> f(1, 4) = 1 + 4 = 5
        x=2, y=3 -> f(2, 3) = 2 + 3 = 5
        x=3, y=2 -> f(3, 2) = 3 + 2 = 5
        x=4, y=1 -> f(4, 1) = 4 + 1 = 5
    示例 2：
        输入：function_id = 2, z = 5
        输出：[[1,5],[5,1]]
        解释：function_id = 2 暗含的函数式子为 f(x, y) = x * y
        以下 x 和 y 满足 f(x, y) 等于 5：
        x=1, y=5 -> f(1, 5) = 1 * 5 = 5
        x=5, y=1 -> f(5, 1) = 5 * 1 = 5

    提示：
        1 <= function_id <= 9
        1 <= z <= 100
        题目保证f(x, y) == z的解处于1 <= x, y <= 1000的范围内。
        在 1 <= x, y <= 1000的前提下，题目保证f(x, y)是一个32 位有符号整数。
    */

    interface CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        public int f(int x, int y);
    }

    // 暴力
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= 1000; j++) {
                if (customfunction.f(i, j) == z) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    // 二分
    public List<List<Integer>> findSolution2(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            // 固定i后用二分法找j
            int jLeft = 1, jRight = 1000;
            while (jLeft <= jRight) {
                int jMid = (jLeft + jRight) / 2;
                int tmpZ = customfunction.f(i, jMid);
                if (tmpZ < z) {
                    jLeft = jMid + 1;
                } else if (tmpZ > z) {
                    jRight = jMid - 1;
                } else {
                    res.add(Arrays.asList(i, jMid));
                    break;
                }
            }
        }
        return res;
    }

    // 双指针
    public List<List<Integer>> findSolution3(CustomFunction customfunction, int z) {
        // 利用函数的单调性，假设f(x1,y1)=f(x2,y2)=z，x1<x2，则必然有y1>y2
        // 也就是说，y的取值范围会随着x的增大而减小
        // 每次y的上限都是上一次y的取值
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1, j = 1000; i <= 1000 && j >= 1; i++) {
            while (j >= 1 && customfunction.f(i, j) > z) {
                j--;
            }
            if (j >= 1 && customfunction.f(i, j) == z) {
                res.add(Arrays.asList(i, j));
            }
        }
        return res;
    }
}