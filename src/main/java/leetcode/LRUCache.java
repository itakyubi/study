package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * leetcode.LRUCache
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-03 19:49
 */
public class LRUCache {
    private int capacity;
    private LRULinkedHashMap<Integer, Integer> linkedHashMap = new LRULinkedHashMap<>();

    private class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            if (size() > capacity) {
                return true;
            } else {
                return false;
            }
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = linkedHashMap.get(key);
        if (value == null) {
            return -1;
        }
        linkedHashMap.remove(key);
        linkedHashMap.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (linkedHashMap.containsKey(key)) {
            linkedHashMap.remove(key);
        }
        linkedHashMap.put(key, value);
    }
}
