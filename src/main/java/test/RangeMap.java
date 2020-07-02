package test;

import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeMap;

/**
 * RangeMap
 * 2020-07-02 17:41
 *
 * @author wuao
 */
public class RangeMap {

    public static void main(String[] args) {
        com.google.common.collect.RangeMap<Integer, Integer> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), 1);
        System.out.println(rangeMap);

        rangeMap.put(Range.open(3, 6), 2);
        System.out.println(rangeMap);

        rangeMap.put(Range.openClosed(10, 20), 3);
        System.out.println(rangeMap);

        rangeMap.put(Range.closed(20, 20), 4);
        rangeMap.remove(Range.closed(5, 11));
        System.out.println(rangeMap);
    }
}
