package com.wa.demo.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ConsistencyHashWithVirtualNode
 * 2023/3/15 10:34 上午
 *
 * @author wuao
 */
public class ConsistencyHashWithVirtualNode {

    private static SortedMap<Integer, String> serverMap = new TreeMap<>();
    private static int vmNode = 5; // 每个服务器虚拟节点个数

    public static void main(String[] args) {
        // 服务器列表
        String[] servers = new String[]{"192.168.0.0:111", "192.168.0.1:111",
                "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
        // 初始化hash环
        for (String server : servers) {
            // 添加真实节点
            int hash = getHash(server);
            serverMap.put(hash, server);

            // 添加虚拟节点
            for (int i = 0; i < vmNode; i++) {
                String vmNode = server + "vm" + i;
                int vmHash = getHash(vmNode);
                serverMap.put(vmHash, vmNode);
            }
        }

        // 计算数据所属服务器
        String[] data = new String[]{"dataA", "dataB", "dataC", "dataAA", "dataBB", "dataCC"};
        for (String s : data) {
            System.out.printf("数据%s被缓存到节点%s%n", s, getServerNode(s));
        }

        // 输出结果的数据倾斜程度相比于不使用虚拟节点要好
        /*数据dataA被缓存到节点192.168.0.0:111vm4
        数据dataB被缓存到节点192.168.0.3:111
        数据dataC被缓存到节点192.168.0.1:111vm3
        数据dataAA被缓存到节点192.168.0.1:111vm4
        数据dataBB被缓存到节点192.168.0.4:111vm1
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
