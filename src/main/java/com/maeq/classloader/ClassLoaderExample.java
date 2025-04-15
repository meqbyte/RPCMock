package com.maeq.classloader;

public class ClassLoaderExample {
    public static void main(String[] args) {
        // 获取当前类的类加载器
        ClassLoader classLoader = ClassLoaderExample.class.getClassLoader();

        // 输出当前类的类加载器
        System.out.println("ClassLoader of this class: " + classLoader);

        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("System ClassLoader: " + systemClassLoader);
    }
}

