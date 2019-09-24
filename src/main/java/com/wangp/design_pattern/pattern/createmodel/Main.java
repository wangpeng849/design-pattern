package com.wangp.design_pattern.pattern.createmodel;


/**
 * 测试使用
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         *  单例模式
         */
        //Singleton singleton = new Singleton();   报错
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println("singleton1==singleton2  -->"+(singleton1 == singleton2));
        System.out.println("---------------------单例模式-----------------------");


        /**
         * 原型模式
         */
        RealizeType obj1 = new RealizeType();
        RealizeType obj2 = (RealizeType) obj1.clone();
        System.out.println("obj1.str==" +obj1.str + ",obj2.str==" +obj2.str);
        System.out.println("obj1==obj2  -->"+(obj1==obj2));
        System.out.println("---------------------原型模式-------------------------");

        /**
         * 工厂方法模式
         */
        //抽象工厂创建工厂
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractFactory factory2 = new ConcreteFactory2();
        //工厂创建产品
        Product product1 = factory1.newProduct();
        Product product2 = factory2.newProduct();
        //产品实现功能
        product1.show();
        product2.show();
        //单独生产product1
        System.out.println("-----------------------------------------\n单独生产产品1");
        Product product = new ConcreteFactory1().newProduct();
        product.show();
        System.out.println("---------------------工厂方法模式-------------------------");



        /**
         * 抽象工厂模式
         */
        AbstractFatory factory = new ObjectFactory();
        factory.newProduct1();
        factory.newProduct2();
        System.out.println("---------------------抽象工厂模式-------------------------");


        /**
         * 建造者模式
         */
        Director director = new Director(new ConcreteBuilder());
        ProductRole productRole = director.construct();
        productRole.show();
        System.out.println("---------------------建造者模式-------------------------");
    }

}
