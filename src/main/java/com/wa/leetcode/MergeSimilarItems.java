package com.wa.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MergeSimilarItems
 * https://leetcode.cn/problems/merge-similar-items/
 * 2363. 合并相似的物品
 * 2023/2/28 8:57 上午
 *
 * @author wuao
 */
public class MergeSimilarItems {

    /*
    给你两个二维整数数组items1 和items2，表示两个物品集合。每个数组items有以下特质：
    items[i] = [valuei, weighti] 其中valuei表示第i件物品的价值，weighti表示第 i件物品的 重量。
    items中每件物品的价值都是 唯一的。
    请你返回一个二维数组ret，其中ret[i] = [valuei, weighti]，weighti是所有价值为valuei物品的重量之和。
    注意：ret应该按价值 升序排序后返回。

    示例 1：
        输入：items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
        输出：[[1,6],[3,9],[4,5]]
        解释：
        value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 5 ，总重量为 1 + 5 = 6 。
        value = 3 的物品再 items1 中 weight = 8 ，在 items2 中 weight = 1 ，总重量为 8 + 1 = 9 。
        value = 4 的物品在 items1 中 weight = 5 ，总重量为 5 。
        所以，我们返回 [[1,6],[3,9],[4,5]] 。
    示例 2：
        输入：items1 = [[1,1],[3,2],[2,3]], items2 = [[2,1],[3,2],[1,3]]
        输出：[[1,4],[2,4],[3,4]]
        解释：
        value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 3 ，总重量为 1 + 3 = 4 。
        value = 2 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 1 ，总重量为 3 + 1 = 4 。
        value = 3 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
        所以，我们返回 [[1,4],[2,4],[3,4]] 。
    示例 3：
        输入：items1 = [[1,3],[2,2]], items2 = [[7,1],[2,2],[1,4]]
        输出：[[1,7],[2,4],[7,1]]
        解释：
        value = 1 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 4 ，总重量为 3 + 4 = 7 。
        value = 2 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
        value = 7 的物品在 items2 中 weight = 1 ，总重量为 1 。
        所以，我们返回 [[1,7],[2,4],[7,1]] 。
            
    提示：
        1 <= items1.length, items2.length <= 1000
        items1[i].length == items2[i].length == 2
        1 <= valuei, weighti <= 1000
        items1中每个 valuei都是 唯一的。
        items2中每个 valuei都是 唯一的。
    */

    public static void main(String[] args) {
        int[][] items1 = new int[][]{{1, 1}, {4, 5}, {3, 8}}, items2 = new int[][]{{3, 1}, {1, 5}};
        int[][] items12 = new int[][]{{1, 1}, {3, 2}, {2, 3}}, items22 = new int[][]{{2, 1}, {3, 2}, {1, 3}};
        int[][] items13 = new int[][]{{1, 3}, {2, 2}}, items23 = new int[][]{{7, 1}, {2, 2}, {1, 4}};

        System.out.println(mergeSimilarItems(items1, items2));
        System.out.println(mergeSimilarItems(items12, items22));
        System.out.println(mergeSimilarItems(items13, items23));
    }

    private static List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] item : items1) {
            map.put(item[0], map.getOrDefault(item[0], 0) + item[1]);
        }
        for (int[] item : items2) {
            map.put(item[0], map.getOrDefault(item[0], 0) + item[1]);
        }

        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> Arrays.asList(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
