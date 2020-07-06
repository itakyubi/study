package com.wa.model;

/**
 * TireNode
 * 2020-07-06 16:43
 *
 * @author wuao
 */
public class TireNode {

    private TireNode[] links;
    private final int R = 26;
    private boolean isEnd;

    public TireNode() {
        links = new TireNode[R];
    }

    public boolean containsKey(char c) {
        return links[c - 'a'] != null;
    }

    public TireNode get(char c) {
        return links[c - 'a'];
    }

    public void put(char c, TireNode tireNode) {
        links[c - 'a'] = tireNode;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd() {
        isEnd = true;
    }
}
