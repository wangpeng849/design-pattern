package com.wangp.design_pattern.pattern.createmodel;

/**
 * 工厂方法（FactoryMethod）模式
 *
 * 定义一个创建产品对象的工厂接口，将产品对象的实际创建工作推迟到具体子工厂类当中。
 * 这满足创建型模式中所要求的“创建与使用相分离”的特点
 *
 *
 * “简单工厂模式”，它不属于 GoF 的 23 种经典设计模式
 *
 * 模式的结构
 * 工厂方法模式的主要角色如下。
 * 1.抽象工厂（Abstract Factory）：提供了创建产品的接口，调用者通过它访问具体工厂的工厂方法 newProduct() 来创建产品。
 * 2.具体工厂（ConcreteFactory）：主要是实现抽象工厂中的抽象方法，完成具体产品的创建。
 * 3.抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能。
 * 4.具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应。
 *
 *
 * 工厂方法模式通常适用于以下场景。
 * 1.客户只知道创建产品的工厂名，而不知道具体的产品名。如 TCL 电视工厂、海信电视工厂等。
 * 2.创建对象的任务由多个具体子工厂中的某一个完成，而抽象工厂只提供创建产品的接口。
 * 3.客户不关心创建产品的细节，只关心产品的品牌。
 */
public class FactoryMethod {
}

/**
 * 抽象工厂
 */
interface AbstractFactory{
    Product newProduct();
}

/**
 * 具体工厂
 */
class ConcreteFactory1 implements  AbstractFactory{

    @Override
    public Product newProduct() {
        System.out.println("具体工厂1生成-->具体产品1...");
        return new ConcreteProduct1();
    }
}

class ConcreteFactory2 implements  AbstractFactory{

    @Override
    public Product newProduct() {
        System.out.println("具体工厂2生成-->具体产品2...");
        return new ConcreteProduct2();
    }
}

/**
 *  抽血产品
 */
interface Product{
    void show();
}

/**
 * 具体产品
 */
class ConcreteProduct1 implements Product{

    @Override
    public void show() {
        System.out.println("产品1展示....");
    }
}

class ConcreteProduct2 implements Product{

    @Override
    public void show() {
        System.out.println("产品2展示....");
    }
}