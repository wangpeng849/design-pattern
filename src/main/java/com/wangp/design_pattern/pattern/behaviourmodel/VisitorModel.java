package com.wangp.design_pattern.pattern.behaviourmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者（Visitor）模式
 *
 *
 * 将作用于某种数据结构中的各元素的操作分离出来封装成独立的类，使其在不改变数据结构的前提下可以添加作用于这些元素的新的操作，
 * 为数据结构中的每个元素提供多种访问方式。它将对数据的操作与数据结构进行分离，是行为类模式中最复杂的一种模式。
 *
 * 访问者（Visitor）模式是一种对象行为型模式，其主要优点如下。
 * 1.扩展性好。能够在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。
 * 2.复用性好。可以通过访问者来定义整个对象结构通用的功能，从而提高系统的复用程度。
 * 3.灵活性好。访问者模式将数据结构与作用于结构上的操作解耦，使得操作集合可相对自由地演化而不影响系统的数据结构。
 * 4.符合单一职责原则。访问者模式把相关的行为封装在一起，构成一个访问者，使每一个访问者的功能都比较单一。
 *
 * 访问者（Visitor）模式的主要缺点如下。
 * 1.增加新的元素类很困难。在访问者模式中，每增加一个新的元素类，都要在每一个具体访问者类中增加相应的具体操作，这违背了“开闭原则”。
 * 2.破坏封装。访问者模式中具体元素对访问者公布细节，这破坏了对象的封装性。
 * 3.违反了依赖倒置原则。访问者模式依赖了具体类，而没有依赖抽象类。
 */
public class VisitorModel {
}

/**
 * 访问者模式包含以下主要角色。
 * 1.抽象访问者（Visitor）角色：定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作 visit() ，
 * 该操作中的参数类型标识了被访问的具体元素。
 *
 * 2.具体访问者（ConcreteVisitor）角色：实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么。
 *
 * 3.抽象元素（Element）角色：声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数。
 *
 * 4.具体元素（ConcreteElement）角色：实现抽象元素角色提供的 accept() 操作，其方法体通常都是 visitor.visit(this) ，
 * 另外具体元素中可能还包含本身业务逻辑的相关操作。
 *
 * 5.对象结构（Object Structure）角色：是一个包含元素角色的容器，提供让访问者对象遍历容器中的所有元素的方法，
 * 通常由 List、Set、Map 等聚合类实现。
 *
 *
 * 通常在以下情况可以考虑使用访问者（Visitor）模式。
 * 对象结构相对稳定，但其操作算法经常变化的程序。
 * 对象结构中的对象需要提供多种不同且不相关的操作，而且要避免让这些操作的变化影响对象的结构。
 * 对象结构包含很多类型的对象，希望对这些对象实施一些依赖于其具体类型的操作。
 */
interface Visitor{
    void visit(ConcreteElementA element);
    void visit(ConcreteElementB element);
}

class ConcreteVisitorA implements Visitor{

    @Override
    public void visit(ConcreteElementA element) {
        System.out.println("具体访问者A访问-->"+element.operationA());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.println("具体访问者A访问-->"+element.operationB());
    }
}

class ConcreteVisitorB implements Visitor{

    @Override
    public void visit(ConcreteElementA element) {
        System.out.println("具体访问者B访问-->"+element.operationA());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.println("具体访问者B访问-->"+element.operationB());
    }
}

interface Element{
    void accept(Visitor visitor);
}

class ConcreteElementA implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    String operationA(){
        return "OperationA starting";
    }
}

class ConcreteElementB implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    String operationB(){
        return "OperationB starting";
    }
}

class ObjectStructure{
    List<Element> elements = new ArrayList<>();

    void accept(Visitor visitor){
        java.util.Iterator i=elements.iterator();
        while(i.hasNext())
        {
            ((Element) i.next()).accept(visitor);
        }
    }

    void add(Element element){
        elements.add(element);
    }
    void remove(Element element){
        elements.remove(element);
    }
}

