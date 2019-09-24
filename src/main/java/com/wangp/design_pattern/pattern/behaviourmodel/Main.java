package com.wangp.design_pattern.pattern.behaviourmodel;

/**
 * 测试
 *
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("-----------------------模板方法模式-----------------------");
        AbstractClass abstractClass = new ConcreteClass();
        abstractClass.TemplateMethod();
        System.out.println("-----------------------策略模式-----------------------");
        Strategy strategy = new Context(new Context(new ConcreteStategy1()));
        Context context = new Context(strategy);
        context.strategyMethod();
        System.out.println("-----------------------命令模式-----------------------");
        Command command = new ConcreteCommandA();
        Invoker invoker = new Invoker(command);
        invoker.call();
        System.out.println("-----------------------责任链模式-----------------------");
        Handler handler1 = new ConcreteHandler1();
        Handler handler2=  new ConcreteHandler2();
        handler1.setNext(handler2);
        handler1.handleRequest("two");
        System.out.println("-----------------------状态模式-----------------------");
        StateContext stateContext = new StateContext();
        stateContext.Handle();
        stateContext.Handle();
        stateContext.Handle();
        stateContext.Handle();
        System.out.println("-----------------------观察者模式-----------------------");
        Subject subject = new ConcreteSubject();
        subject.observers.add(new ConcreteObserver1());
        subject.observers.add(new ConcreteObserver2());
        subject.notifyObserver();
        System.out.println("-----------------------中介者模式-----------------------");
        Mediator mediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreateColleague1();
        Colleague colleague2 = new ConcreateColleague2();
        mediator.register(colleague1);
        mediator.register(colleague2);
        colleague1.send();
        System.out.println("*****************");
        colleague2.send();
        System.out.println("-----------------------迭代器模式-----------------------");
        Aggregate ag=new ConcreteAggregate();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");
        System.out.print("聚合的内容有：");
        Iterator it=ag.getIterator();
        while(it.hasNext())
        {
            Object ob=it.next();
            System.out.print(ob.toString()+"\t");
        }
        Object ob=it.first();
        System.out.println("\nFirst："+ob.toString());
        System.out.println("-----------------------访问者模式-----------------------");
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor = new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("********************************");
        visitor = new ConcreteVisitorB();
        os.accept(visitor);
        System.out.println("-----------------------备忘录模式-----------------------");
        Originnator originnator = new Originnator();
        Caretaker caretaker = new Caretaker();
        originnator.setState("S0");
        System.out.println("初始状态："+originnator.getState());
        caretaker.setMemento(originnator.createMemento());
        originnator.setState("S1");
        System.out.println("新的状态："+originnator.getState());
        originnator.restoreMemento(caretaker.getMemento());
        System.out.println("恢复状态："+originnator.getState());
        System.out.println("-----------------------解释器模式-----------------------");

    }
}
