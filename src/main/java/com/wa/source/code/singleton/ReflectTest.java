package com.wa.source.code.singleton;

import java.lang.reflect.Constructor;

/**
 * ReflectTest
 * 2020-12-28 13:40
 *
 * @author wuao
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception {
        hungryReflect();
    }

    /**
     * 内部静态类单例的反射攻击
     */
    public static void staticInnerClassReflect() throws Exception {
        Class objClass = StaticInnerClass.class;

        // 获取类的构造器
        Constructor constructor = objClass.getDeclaredConstructor();
        // 把构造器私有权限放开
        constructor.setAccessible(true);
        // 正常获取实例
        StaticInnerClass staticInnerClass = StaticInnerClass.getInstance();
        // 反射创建实例
        StaticInnerClass staticInnerClass1 = (StaticInnerClass) constructor.newInstance();

        System.out.println(staticInnerClass);
        System.out.println(staticInnerClass1);
        System.out.println(staticInnerClass == staticInnerClass1);
    }

    /**
     * 双重锁单例的反射攻击
     */
    public static void doubleLockReflect() throws Exception {
        Class objClass = DoubleLock.class;

        // 获取类的构造器
        Constructor constructor = objClass.getDeclaredConstructor();
        // 把构造器私有权限放开
        constructor.setAccessible(true);
        // 反射创建实例
        DoubleLock doubleLock1 = (DoubleLock) constructor.newInstance();
        // 正常获取实例
        DoubleLock doubleLock = DoubleLock.getInstance();

        System.out.println(doubleLock);
        System.out.println(doubleLock1);
        System.out.println(doubleLock == doubleLock1);
    }

    /**
     * 饿汉单例的反射攻击
     */
    public static void hungryReflect() throws Exception {
        Class objClass = Hungry.class;

        // 获取类的构造器
        Constructor constructor = objClass.getDeclaredConstructor();
        // 把构造器私有权限放开
        constructor.setAccessible(true);
        // 正常获取实例
        Hungry hungry = Hungry.getInstance();
        // 反射创建实例
        Hungry hungry1 = (Hungry) constructor.newInstance();

        System.out.println(hungry);
        System.out.println(hungry1);
        System.out.println(hungry == hungry1);
    }
}
