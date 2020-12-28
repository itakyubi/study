package com.wa.source.code.singleton;

/**
 * Hungry
 * 2020-12-28 14:07
 *
 * @author wuao
 */
public class Hungry {
    private static Hungry hungry = new Hungry();

    private Hungry() {
        // 由于 hungry 变量在类加载过程中就已经创建，因此可以直接使用其进行非空判断
        if (hungry != null) {
            throw new IllegalStateException();
        }
    }

    public static Hungry getInstance() {
        return hungry;
    }
}
