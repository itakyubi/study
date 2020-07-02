package leetcode;

/**
 * leetcode.FindPeakElement
 * 2020-05-09 18:29
 *
 * @author wuao
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = (right + left) >> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
