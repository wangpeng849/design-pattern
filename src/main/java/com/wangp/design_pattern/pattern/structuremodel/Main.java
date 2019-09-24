package com.wangp.design_pattern.pattern.structuremodel;

/**
 * @author Failing
 * 测试
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("-----------------------代理模式------------------------------");
        Subject proxy = new Proxy();
        proxy.show();
        System.out.println("-----------------------适配器模式------------------------------");
        Target target = new ClassAdapter();
        target.request();
        target = new ObjectAdapter(new Adaptee());
        target.request();
        System.out.println("-----------------------桥接模式------------------------------");
        Implementor implementor = new ConcreteImplementor();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.Operation();
        System.out.println("-----------------------装饰模式------------------------------");
        Compoent compoent = new ConcreteComponet();
        Decorator decorator = new ConcreteDecorator(compoent);
        decorator.operation();
        System.out.println("-----------------------外观模式------------------------------");
        Facade facade = new Facade();
        facade.method();
        System.out.println("-----------------------享元模式------------------------------");
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        Flyweight f1 = flyweightFactory.getFlyweight("AAA");
        Flyweight f2 = flyweightFactory.getFlyweight("AAA");
        Flyweight f3 = flyweightFactory.getFlyweight("AAA");
        Flyweight f4 = flyweightFactory.getFlyweight("BBB");
        Flyweight f5 = flyweightFactory.getFlyweight("BBB");
        f1.operation(new UnshareConcreteFlyweight("第一次调用AAA"));
        f2.operation(new UnshareConcreteFlyweight("第二次调用AAA"));
        f3.operation(new UnshareConcreteFlyweight("第三次调用AAA"));
        f4.operation(new UnshareConcreteFlyweight("第一次调用BBB"));
        f5.operation(new UnshareConcreteFlyweight("第二次调用BBB"));
        System.out.println("-----------------------组合模式------------------------------");
        Component com1 = new Compositee();
        Component com2 = new Compositee();
        Component leaf1 = new Leaf("ABC");
        Component leaf2 = new Leaf("DEF");
        Component leaf3 = new Leaf("GHI");
        com1.add(leaf1);
        com1.add(com2);
        com2.add(leaf2);
        com2.add(leaf3);
        com1.operation();
    }
}
