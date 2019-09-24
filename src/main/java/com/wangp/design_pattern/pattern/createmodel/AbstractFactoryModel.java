package com.wangp.design_pattern.pattern.createmodel;

/**
 * 抽象工厂（AbstractFactory）模式
 *
 * 一种为访问类提供一个创建一组相关或相互依赖对象的接口，
 * 且访问类无须指定所要产品的具体类就能得到同族的不同等级的产品的模式结构。
 *
 *
 * 抽象工厂模式是工厂方法模式的升级版本，工厂方法模式只生产一个等级的产品，而抽象工厂模式可生产多个等级的产品。
 *
 * 使用抽象工厂模式一般要满足以下条件。
 * 1.系统中有多个产品族，每个具体工厂创建同一族但属于不同等级结构的产品。
 * 2.系统一次只可能消费其中某一族产品，即同族的产品一起使用。
 *
 * 抽象工厂模式除了具有工厂方法模式的优点外，其他主要优点如下。
 * 1.可以在类的内部对产品族中相关联的多等级产品共同管理，而不必专门引入多个新的类来进行管理。
 * 2.当增加一个新的产品族时不需要修改原代码，满足开闭原则。
 *
 * 其缺点是：当产品族中需要增加一个新的产品时，所有的工厂类都需要进行修改。
 *
 *
 *
 * 抽象工厂模式通常适用于以下场景：
 * 1.当需要创建的对象是一系列相互关联或相互依赖的产品族时，如电器工厂中的电视机、洗衣机、空调等。
 * 2.系统中有多个产品族，但每次只使用其中的某一族产品。如有人只喜欢穿某一个品牌的衣服和鞋。
 * 3.系统中提供了产品的类库，且所有产品的接口相同，客户端不依赖产品实例的创建细节和内部结构。
 */
public class AbstractFactoryModel {
}

/**
 * 抽象工厂
 */
interface AbstractFatory{
    Product1 newProduct1();
    Product2 newProduct2();
}

/**
 * 实例工厂
 */
class ObjectFactory implements AbstractFatory{

    @Override
    public Product1 newProduct1() {
        System.out.println("具体工厂 ---> 生产product1");
        return new Product1();
    }

    @Override
    public Product2 newProduct2() {
        System.out.println("具体工厂 ---> 生产product2");
        return new Product2();
    }
}

/**
 * 产品
 */
class Product1{
    Product1(){
        System.out.println("产品1生产...");
    }
}

class Product2{
    Product2(){
        System.out.println("产品2生产...");
    }
}
