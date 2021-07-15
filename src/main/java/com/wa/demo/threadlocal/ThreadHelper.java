package com.wa.demo.threadlocal;

/**
 * ThreadHelper
 * 2021-07-15 10:44
 *
 * @author wuao
 */
public class ThreadHelper {

    private static ThreadLocal<String> userId = new ThreadLocal<>();

    public static String getUserId() {
        return userId.get();
    }

    public static void setUserId(String userId) {
        ThreadHelper.userId.set(userId);
    }
}
