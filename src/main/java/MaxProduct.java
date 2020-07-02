/**
 * MaxProduct
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-07 11:05
 */
public class MaxProduct {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }
            min = Math.min(nums[i], min * nums[i]);
            max = Math.max(nums[i], max * nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
