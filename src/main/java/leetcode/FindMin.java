package leetcode;

/**
 * leetcode.FindMin
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-16 02:35
 */
public class FindMin {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int left = 0, right = nums.length - 1, mid;
        if (nums[0] < nums[right]) {
            return nums[0];
        }
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }
}
