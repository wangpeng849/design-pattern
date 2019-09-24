package com.wangp.design_pattern.pattern.structuremodel;

/**
 * 装饰（Decorator）模式
 *
 * 指在不改变现有对象结构的情况下，动态地给该对象增加一些职责（即增加其额外功能）的模式，它属于对象结构型模式。
 *
 * 装饰（Decorator）模式的主要优点有：
 * 采用装饰模式扩展对象的功能比采用继承方式更加灵活。
 * 可以设计出多个不同的具体装饰类，创造出多个不同行为的组合。
 *
 * 其主要缺点是：装饰模式增加了许多子类，如果过度使用会使程序变得很复杂。
 *
 *
 *
 * 前面讲解了关于装饰模式的结构与特点，下面介绍其适用的应用场景，装饰模式通常在以下几种情况使用。
 * 当需要给一个现有类添加附加职责，而又不能采用生成子类的方法进行扩充时。例如，该类被隐藏或者该类是终极类或者采用继承方式会产生大量的子类。
 * 当需要通过对现有的一组基本功能进行排列组合而产生非常多的功能时，采用继承关系很难实现，而采用装饰模式却很好实现。
 * 当对象的功能要求可以动态地添加，也可以再动态地撤销时。
 *
 * 装饰模式在 Java 语言中的最著名的应用莫过于 Java I/O 标准库的设计了。
 * 例如，InputStream 的子类 FilterInputStream，OutputStream 的子类 FilterOutputStream，
 * Reader 的子类 BufferedReader 以及 FilterReader，还有 Writer 的子类 BufferedWriter、
 * FilterWriter 以及 PrintWriter 等，它们都是抽象装饰类。
 */
public class DecoratorModel {
    public static void main(String[] args) {
        Morrign m1 = new Succubus(new Origninal());
        Morrign m2 = new Girl(new Origninal());
        m1.display();
        m2.display();
    }
}

/**
 *装饰模式主要包含以下角色。
 *抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
 *具体构件（Concrete    Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
 *抽象装饰（Decorator）角色：继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
 *具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。
 */

interface  Compoent{
    void operation();
}

class ConcreteComponet implements Compoent{

    ConcreteComponet(){
        System.out.println("创建具体构建角色");
    }

    @Override
    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}

class Decorator implements Compoent{

    Compoent compoent;

    Decorator(Compoent compoent){
        this.compoent = compoent;
    }

    @Override
    public void operation() {
        compoent.operation();
    }
}

class ConcreteDecorator extends   Decorator{

    @Override
    public void operation(){
        compoent.operation();
        addFunction();
    }

    private void addFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }

    ConcreteDecorator(Compoent compoent) {
        super(compoent);
    }
}


/**
 *用装饰模式实现游戏角色“莫莉卡·安斯兰”的变身。
 *分析：在《恶魔战士》中，游戏角色“莫莉卡·安斯兰”的原身是一个可爱少女，
 * 但当她变身时，会变成头顶及背部延伸出蝙蝠状飞翼的女妖，当然她还可以变为穿着漂亮外衣的少女。
 * 这些都可用装饰模式来实现，在本实例中的“莫莉卡”原身有 setImage(String t) 方法决定其显示方式，
 * 而其 变身“蝙蝠状女妖”和“着装少女”可以用 setChanger() 方法来改变其外观，原身与变身后的效果用 display() 方法来显示。
 */
 interface Morrign{
     void display();
}

class Origninal implements Morrign{

     Origninal(){

     }

    @Override
    public void display() {
        System.out.println("原型");
    }
}

class Changer implements Morrign{

     Morrign morrign;

     Changer(Morrign morrign){
        this.morrign = morrign;
     }

    @Override
    public void display() {
         morrign.display();
    }
}

class Succubus extends Changer{

    Succubus(Morrign morrign) {
        super(morrign);
    }

    @Override
    public void display(){
        morrign.display();
        setChanger();
    }

    private void setChanger() {
        System.out.println("女巫--Morrign");
    }
}

class Girl extends Changer{

    Girl(Morrign morrign) {
        super(morrign);
    }

    @Override
    public void display(){
        morrign.display();
        setChanger();
    }

    private void setChanger() {
        System.out.println("女孩--Morrign");
    }
}