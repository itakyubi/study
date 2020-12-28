package com.wa.source.code.singleton;

/**
 * StaticInnerClass
 * 2020-12-28 13:38
 *
 * @author wuao
 */
public class StaticInnerClass {

    private StaticInnerClass() {
        // 加上这个判断就可以防止反射攻击
        // 因为在调用 InnerClass.staticInnerClass 变量进行判断时，就会去执行23行的类加载
        // 导致 InnerClass.staticInnerClass 被初始化，从而判断该变量为非空
        if (InnerClass.staticInnerClass != null) {
            throw new IllegalStateException();
        }
    }

    public static StaticInnerClass getInstance() {
        return InnerClass.staticInnerClass;
    }

    private static class InnerClass {
        private static StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}
