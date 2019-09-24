package com.wangp.design_pattern.pattern.createmodel;

import lombok.Data;

/**
 * 建造者（Builder）模式
 *
 * 将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，
 * 这样的设计模式被称为建造者模式。它是将一个复杂的对象分解为多个简单的对象，
 * 然后一步一步构建而成。它将变与不变相分离，即产品的组成部分是不变的，但每一部分是可以灵活选择的
 *
 * 该模式的主要优点如下：
 * 1.各个具体的建造者相互独立，有利于系统的扩展。
 * 2.客户端不必知道产品内部组成的细节，便于控制细节风险。
 *
 * 其缺点如下：
 * 1.产品的组成部分必须相同，这限制了其使用范围。
 * 2.如果产品的内部变化复杂，该模式会增加很多的建造者类。
 *
 *
 * 建造者（Builder）模式和工厂模式的关注点不同：建造者模式注重零部件的组装过程，
 * 而工厂方法模式更注重零部件的创建过程，但两者可以结合使用。
 * 建造者（Builder）模式由产品、抽象建造者、具体建造者、指挥者等 4 个要素构成
 *
 *
 * 建造者（Builder）模式创建的是复杂对象，其产品的各个部分经常面临着剧烈的变化，
 * 但将它们组合在一起的算法却相对稳定，所以它通常在以下场合使用。
 *
 * 创建的对象较复杂，由多个部件构成，各部件面临着复杂的变化，但构件间的建造顺序是稳定的。
 * 创建复杂对象的算法独立于该对象的组成部分以及它们的装配方式，即产品的构建过程和最终的表示是独立的。
 */
public class BuilderModel {
}

/**
 * 产品角色：
 * 它是包含多个组成部件的复杂对象，由具体建造者来创建其各个滅部件
 */
@Data
class ProductRole{
    private String partA;
    private String partB;
    private String partC;

    public void show(){
        System.out.println("产品建造完成..  ---> "+this.toString());
    }
}

/**
 * 抽象建造者:
 * 它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()。
 */
abstract class Builder{
    //创建对象
    protected ProductRole productRole = new ProductRole();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();
    //返回产品对象
    public ProductRole getResult(){
        return productRole;
    }
}

/**
 * 具体建造者：
 * 实现 Builder 接口，完成复杂产品的各个部件的具体创建方法。
 */
class ConcreteBuilder extends Builder{

    @Override
    public void buildPartA() {
        productRole.setPartA("建造partA...");
    }

    @Override
    public void buildPartB() {
        productRole.setPartB("建造partB...");
    }

    @Override
    public void buildPartC() {
        productRole.setPartC("建造partC...");
    }
}

/**
 * 指挥者：
 * 它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息。
 */
class Director{
    private Builder builder;
    public Director(Builder builder){
        this.builder = builder;
    }

    //产品组装和组装方法
    public ProductRole construct(){
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}