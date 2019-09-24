package com.wangp.design_pattern.pattern.createmodel;

/**
 * 单例（Singleton）模式的定义：指一个类只有一个实例，且该类能自行创建这个实例的一种模式。
 * 例如，Windows 中只能打开一个任务管理器，这样可以避免因打开多个任务管理器窗口而造成内存资源的浪费，
 * 或出现各个窗口显示内容的不一致等错误。
 *
 *
 *
 * 单例模式有 3 个特点：
 * 1.单例类只有一个实例对象；
 * 2.该单例对象必须由单例类自行创建；
 * 3.单例类对外提供一个访问该单例的全局访问点；
 *
 *
 * 单例模式是设计模式中最简单的模式之一。
 * 通常，普通类的构造函数是公有的，外部类可以通过“new 构造函数()”来生成多个实例。
 * 但是，如果将类的构造函数设为私有的，外部类就无法调用该构造函数，也就无法生成多个实例。
 * 这时该类自身必须定义一个静态私有实例，并向外提供一个静态的公有函数用于创建或获取该静态私有实例。
 */


public class Singleton {

    private static volatile Singleton instance = null;

    private Singleton() {
        //构造函数私有化
    }

    /**
     * 加锁
     * @return
     */
    public synchronized static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
            return instance;
        }
        return instance;
    }


}

