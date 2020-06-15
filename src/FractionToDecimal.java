import java.util.HashMap;
import java.util.Map;

/**
 * FractionToDecimal
 * 2020-06-15 19:21
 *
 * @author wuao
 */
public class FractionToDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        if (numerator < 0 ^ denominator < 0)
            sb.append("-");

        long num = Math.abs(Long.valueOf(numerator));
        long div = Math.abs(Long.valueOf(denominator));
        sb.append(num / div);

        long remainder = num % div;
        if (remainder == 0)
            return sb.toString();
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                break;
            }
            map.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / div);
            remainder %= denominator;
        }
        return sb.toString();
    }
}
