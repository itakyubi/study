import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * FindRepeatedDnaSequences
 * 2020-06-29 18:41
 *
 * @author wuao
 */
public class FindRepeatedDnaSequences {

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("CCCCCCCCCCC"));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        int len = 10;
        HashSet<String> set = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        for (int i = 0; i < s.length() - len + 1; i++) {
            String tmp = s.substring(i, i + len);
            if (set.contains(tmp)) {
                res.add(tmp);
            }
            set.add(tmp);
        }
        return new ArrayList<>(res);
    }
}
