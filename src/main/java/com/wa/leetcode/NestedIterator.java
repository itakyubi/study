package com.wa.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * NestedIterator
 * 2020-11-20 17:02
 *
 * @author wuao
 */
public class NestedIterator implements Iterator<Integer> {

    private LinkedList<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new LinkedList<>(nestedList);
    }

    @Override
    public boolean hasNext() {
        while (!list.isEmpty() && !list.get(0).isInteger()) {
            List<NestedInteger> first = list.remove(0).getList();
            for (int i = first.size() - 1; i >= 0; i--) {
                list.addFirst(first.get(i));
            }
        }
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        return list.remove(0).getInteger();
    }

    private interface NestedInteger {
        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();
    }
}


