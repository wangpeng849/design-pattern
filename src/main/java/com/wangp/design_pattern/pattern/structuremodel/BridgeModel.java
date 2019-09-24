package com.wangp.design_pattern.pattern.structuremodel;

import lombok.Data;

/**
 * 桥接模式
 *
 * 桥接（Bridge）模式的定义如下：将抽象与实现分离，使它们可以独立变化。
 * 它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度。
 *
 * 桥接（Bridge）模式的优点是：
 * 1.由于抽象与实现分离，所以扩展能力强；
 * 2.其实现细节对客户透明。
 *
 * 缺点是：由于聚合关系建立在抽象层，要求开发者针对抽象化进行设计与编程，这增加了系统的理解与设计难度。
 *
 * 模式的结构
 * 桥接（Bridge）模式包含以下主要角色。
 * 抽象化（Abstraction）角色：定义抽象类，并包含一个对实现化对象的引用。
 * 扩展抽象化（Refined    Abstraction）角色：是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法。
 * 实现化（Implementor）角色：定义实现化角色的接口，供扩展抽象化角色调用。
 * 具体实现化（Concrete Implementor）角色：给出实现化角色接口的具体实现。
 */
public class BridgeModel {
    public static void main(String[] args) {
        Color color = new Yellow();
        Bag bag = new HandBag(color);
        System.out.println(bag.getName()+","+bag.color.getColor());
    }
}

/**
 * Implementor
 * 实现化角色
 */
interface Implementor{
    public void OperationImpl();
}

/**
 * 具体实现化（Concrete Implementor）
 */
class ConcreteImplementor implements Implementor{

    @Override
    public void OperationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}

/**
 *    抽象化角色
 */
abstract class Abstraction{
    Implementor implementor;
    Abstraction(Implementor implementor){
        this.implementor = implementor;
    }

    abstract void Operation();
}

/**
 *  扩展化角色
 */
class RefinedAbstraction extends Abstraction{

    RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    void Operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问" );
        implementor.OperationImpl();
    }
}


/**
 * 用桥接（Bridge）模式模拟女士皮包的选购。
 *
 * 分析：女士皮包有很多种，可以按用途分、按皮质分、按品牌分、按颜色分、按大小分等，
 * 存在多个维度的变化，所以采用桥接模式来实现女士皮包的选购比较合适。
 *
 * 本实例按用途分可选钱包（Wallet）和挎包（HandBag），
 * 按颜色分可选黄色（Yellow）和红色（Red）。可以按两个维度定义为颜色类和包类。
 *
 * 颜色类（Color）是一个维度，定义为实现化角色，它有两个具体实现化角色：
 * 黄色和红色，通过 getColor() 方法可以选择颜色；包类（Bag）是另一个维度，定义为抽象化角色，
 * 它有两个扩展抽象化角色：挎包和钱包，它包含了颜色类对象，通过 getName() 方法可以选择相关颜色的挎包和钱包。
 */
interface Color{
    String getColor();
}

class Yellow implements Color{

    @Override
    public String getColor() {
        return "yellow";
    }
}

class Red implements Color{

    @Override
    public String getColor() {
        return "red";
    }
}

abstract class Bag{
    Color color;
    Bag(Color color){
        this.color = color;
    }
    abstract String getName();

    public void setColor(Color color){
        this.color = color;
    }
}

@Data
class HandBag extends Bag {

    HandBag(Color color) {
        super(color);
    }

    @Override
    public String getName() {
        return "handbag";
    }
}

class Wallet extends Bag {

    Wallet(Color color) {
        super(color);
    }

    @Override
    public String getName() {
        return "wallet";
    }
}