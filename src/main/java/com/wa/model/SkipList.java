package com.wa.model;

import org.apache.poi.ss.formula.functions.T;

/**
 * SkipList
 * 2021-10-29 17:23
 *
 * @author wuao
 */
public class SkipList {
    SkipNode headNode;
    int level;
    final int MAX_LEVEL = 32;

    public T search(T value) {
        SkipNode<T> skipNode = new SkipNode<>();
        return skipNode.value;
    }

    public void insert(T value) {

    }

    public void delete(T value) {
    }
}
