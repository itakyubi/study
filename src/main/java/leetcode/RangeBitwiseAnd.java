package leetcode;

/**
 * RangeBitwiseAnd
 * 2020-07-02 19:00
 *
 * @author wuao
 */
public class RangeBitwiseAnd {

    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }

    public int rangeBitwiseAnd2(int m, int n) {
        while (m < n)
            n = n & (n - 1);

        // another method : return n;
        return m & n;
    }
}
