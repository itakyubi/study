package com.wa.source.code.singleton;

/**
 * DoubleLock
 * 2020-12-28 13:56
 *
 * @author wuao
 */
public class DoubleLock {
    private static volatile DoubleLock doubleLock;

    private DoubleLock() {
        // 先反射后获取，不能防
        // 先获取后反射，可以防
        if (doubleLock != null) {
            throw new IllegalStateException();
        }
    }

    public static DoubleLock getInstance() {
        if (doubleLock == null) {
            synchronized (DoubleLock.class) {
                if (doubleLock == null) {
                    doubleLock = new DoubleLock();
                }
            }
        }
        return doubleLock;
    }
}
