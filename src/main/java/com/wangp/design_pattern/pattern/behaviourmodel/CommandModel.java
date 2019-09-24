package com.wangp.design_pattern.pattern.behaviourmodel;

/**
 * 命令模式
 *
 * 将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开。
 * 这样两者之间通过命令对象进行沟通，这样方便将命令对象进行储存、传递、调用、增加与管理。
 *
 * 命令模式的主要优点如下。
 * 1.降低系统的耦合度。命令模式能将调用操作的对象与实现该操作的对象解耦。
 * 2.增加或删除命令非常方便。采用命令模式增加与删除命令不会影响其他类，它满足“开闭原则”，对扩展比较灵活。
 * 3.可以实现宏命令。命令模式可以与组合模式结合，将多个命令装配成一个组合命令，即宏命令。
 * 4.方便实现 Undo 和 Redo 操作。命令模式可以与后面介绍的备忘录模式结合，实现命令的撤销与恢复。
 *
 * 其缺点是：可能产生大量具体命令类。因为计对每一个具体操作都需要设计一个具体命令类，这将增加系统的复杂性。
 *
 *
 * 命令模式通常适用于以下场景。
 * 1.当系统需要将请求调用者与请求接收者解耦时，命令模式使得调用者和接收者不直接交互。
 * 2。当系统需要随机请求命令或经常增加或删除命令时，命令模式比较方便实现这些功能。
 * 3.当系统需要执行一组操作时，命令模式可以定义宏命令来实现该功能。
 * 4.当系统需要支持命令的撤销（Undo）操作和恢复（Redo）操作时，可以将命令对象存储起来，采用备忘录模式来实现。
 */
public class CommandModel {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        waiter.chooseChangFen();
    }
}

/**
 * 命令模式包含以下主要角色。
 * 抽象命令类（Command）角色：声明执行命令的接口，拥有执行命令的抽象方法 execute()。
 * 具体命令角色（Concrete    Command）角色：是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作。
 * 实现者/接收者（Receiver）角色：执行命令功能的相关操作，是具体命令对象业务的真正实现者。
 * 调用者/请求者（Invoker）角色：是请求的发送者，它通常拥有很多的命令对象，并通过访问命令对象来执行相关请求，它不直接访问接收者。
 */
interface Command{
    void execute();
}

class ConcreteCommandA implements Command {
    ReceiverA receiverA;

    ConcreteCommandA(){
        this.receiverA =  new ReceiverA();
    }
    @Override
    public void execute() {
        System.out.println("ConcreteCommandA");
        receiverA.action();
    }
}

class ConcreteCommandB implements Command {

    ReceiverB receiverB;

    ConcreteCommandB(){
        this.receiverB =  new ReceiverB();
    }
    @Override
    public void execute() {
        System.out.println("ConcreteCommandB");
        receiverB.action();
    }
}

class ReceiverA{
    public void action(){
        System.out.println("ReceiverA 被调用");
    }
}

class ReceiverB{
    public void action(){
        System.out.println("ReceiverB 被调用");
    }
}

class Invoker{
    Command command;
    Invoker(Command command){
        this.command = command;
    }

    void call(){
        command.execute();
    }
}

/**
 *客户去餐馆可选择的早餐有肠粉、河粉和馄饨等，客户可向服务员选择以上早餐中的若干种，
 * 服务员将客户的请求交给相关的厨师去做。这里的点早餐相当于“命令”，服务员相当于“调用者”，厨师相当于“接收者”，
 * 所以用命令模式实现比较合适。
 *
 * 首先，定义一个早餐类（Breakfast），它是抽象命令类，有抽象方法 cooking()，说明要做什么；
 * 再定义其子类肠粉类（ChangFen）、馄饨类（HunTun）和河粉类（HeFen），它们是具体命令类，
 * 实现早餐类的 cooking() 方法，但它们不会具体做，而是交给具体的厨师去做；
 * 具体厨师类有肠粉厨师（ChangFenChef）、馄蚀厨师（HunTunChef）和河粉厨师（HeFenChef），
 * 他们是命令的接收者，由于本实例要显示厨师做菜的效果图（点此下载要显示的效果图）；
 * 最后，定义服务员类（Waiter），它接收客户的做菜请求，并发出做菜的命令。客户类是通过服务员类来点菜的
 */
interface Breakfast{
    void cooking();
}

class ChangFen implements Breakfast{

    ChangFenChef changFenChef;

    ChangFen(){
        changFenChef = new ChangFenChef();
    }

    @Override
    public void cooking() {
        changFenChef.cooking();
        System.out.println("肠粉一碗");
    }
}

class HuTun implements Breakfast{
    HuTunChef huTunChef;

    HuTun(){
        this.huTunChef = new HuTunChef();
    }

    @Override
    public void cooking() {
        huTunChef.cooking();
        System.out.println("混沌一碗");
    }
}

class ChangFenChef{
    void cooking(){
        System.out.println("肠粉师傅已就位！");
    }
}

class HuTunChef{
    void cooking(){
        System.out.println("混沌师傅已就位！");
    }
}

class Waiter{
    Breakfast breakfast = new ChangFen();
    void chooseChangFen(){
        breakfast.cooking();
    }
}