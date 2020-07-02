package leetcode;

import java.util.Arrays;

/**
 * leetcode.LargestNumber
 * 2020-06-19 16:20
 *
 * @author wuao
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        String[] numStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStr, (a, b) -> {
            String s1 = a + b;
            String s2 = b + a;
            return s2.compareTo(s1);
        });

        if (numStr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : numStr) {
            sb.append(s);
        }
        return sb.toString();
    }
}
