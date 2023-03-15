package com.wa.demo.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ConsistencyHash
 * 2023/3/15 10:15 上午
 *
 * @author wuao
 */
public class ConsistencyHash {

    private static SortedMap<Integer, String> serverMap = new TreeMap<>();

    public static void main(String[] args) {
        // 服务器列表
        String[] servers = new String[]{"192.168.0.0:111", "192.168.0.1:111",
                "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
        // 初始化hash环
        for (String server : servers) {
            int hash = getHash(server);
            serverMap.put(hash, server);
        }

        // 计算数据所属服务器
        String[] data = new String[]{"dataA", "dataB", "dataC", "dataAA", "dataBB", "dataCC"};
        for (String s : data) {
            System.out.printf("数据%s被缓存到节点%s%n", s, getServerNode(s));
        }

        // 输出结果明显存在数据倾斜
        /*数据dataA被缓存到节点192.168.0.1:111
        数据dataB被缓存到节点192.168.0.3:111
        数据dataC被缓存到节点192.168.0.3:111
        数据dataAA被缓存到节点192.168.0.3:111
        数据dataBB被缓存到节点192.168.0.0:111
        数据dataCC被缓存到节点192.168.0.4:111*/
    }


    private static String getServerNode(String data) {
        int hash = getHash(data);
        // 找到比该hash值大的元素
        SortedMap<Integer, String> subMap = serverMap.tailMap(hash);
        if (subMap.isEmpty()) {
            // 如果没有比该hash值大的，则属于第一个node
            return serverMap.get(serverMap.firstKey());
        } else {
            // 第一个Key就是顺时针过去离node最近的那个结点
            return subMap.get(subMap.firstKey());
        }
    }

    // 使用FNV1_32_HASH算法计算服务器的Hash值
    private static int getHash(String s) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < s.length(); i++)
            hash = (hash ^ s.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

}
