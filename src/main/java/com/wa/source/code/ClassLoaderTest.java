package com.wa.source.code;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassLoaderTest
 * 2020-11-30 09:55
 *
 * @author wuao
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = classLoader.loadClass("com.wa.source.code.ClassLoaderTest").newInstance();

        // obj 确实是类 com.wa.source.code.ClassLoaderTest 实例化出的对象
        System.out.println(obj.getClass());
        // 但是由于类加载器不同，一个是系统应用程序类加载器，一个是自定义类加载器
        // 导致虽然是同一个Class文件加载的，但依然是两个独立的类，类型检查时自然是false
        System.out.println(obj instanceof ClassLoaderTest);
    }
}
