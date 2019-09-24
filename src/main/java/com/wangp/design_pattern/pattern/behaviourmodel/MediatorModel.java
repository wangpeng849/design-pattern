package com.wangp.design_pattern.pattern.behaviourmodel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 中介者（Mediator）模式
 *
 * 定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。
 * 中介者模式又叫调停模式，它是迪米特法则的典型应用。
 *
 *
 * 中介者模式是一种对象行为型模式，其主要优点如下。
 * 1.降低了对象之间的耦合性，使得对象易于独立地被复用。
 * 2.将对象间的一对多关联转变为一对一的关联，提高系统的灵活性，使得系统易于维护和扩展。
 *
 * 其主要缺点是：当同事类太多时，中介者的职责将很大，它会变得复杂而庞大，以至于系统难以维护。
 *
 */
public class MediatorModel {
    public static void main(String[] args) {
        Medium md=new EstateMedium();    //房产中介
        Customer member1,member2;
        member1=new Seller("张三(卖方)");
        member2=new Buyer("李四(买方)");
        md.register(member1); //客户注册
        md.register(member2);
    }
}

/***
 * 中介者模式包含以下主要角色。
 * 1.抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
 * 2.具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色。
 * 3.抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
 * 4.具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
 */
interface Mediator{
    void register(Colleague colleague);
    void relay(Colleague colleague);
}

class ConcreteMediator implements Mediator{

    List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        if(!colleagues.contains(colleague)){
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void relay(Colleague colleague) {
        for(Colleague ob:colleagues)
        {
            if(!ob.equals(colleague))
            {
                ob.receive();
            }
        }
    }
}

abstract class Colleague{
    Mediator mediator;
    public void setMediator(Mediator mediator){
        this.mediator=mediator;
    }
    abstract void receive();
    abstract void send();
}

class ConcreateColleague1 extends Colleague{

    @Override
    void receive() {
        System.out.println("具体同事类1收到请求。");
    }

    @Override
    void send() {
        System.out.println("具体同事类1发出请求。");
        //请中介者转发
        mediator.relay(this);
    }
}

class ConcreateColleague2 extends Colleague{

    @Override
    void receive() {
        System.out.println("具体同事类2收到请求。");
    }

    @Override
    void send() {
        System.out.println("具体同事类2发出请求。");
        //请中介者转发
        mediator.relay(this);
    }
}

/***
 * 用中介者模式编写一个“韶关房地产交流平台”程序。
 *
 * 说明：韶关房地产交流平台是“房地产中介公司”提供给“卖方客户”与“买方客户”进行信息交流的平台，比较适合用中介者模式来实现。
 *
 * 首先，定义一个中介公司（Medium）接口，它是抽象中介者，它包含了客户注册方法 register(Customer member) 和
 * 信息转发方法 relay(String from,String ad)；再定义一个韶关房地产中介（EstateMedium）公司，它是具体中介者类，
 * 它包含了保存客户信息的 List 对象，并实现了中介公司中的抽象方法。
 *
 * 然后，定义一个客户（Qistomer）类，它是抽象同事类，其中包含了中介者的对象，和发送信息的 send(String ad) 方法与
 * 接收信息的 receive(String from，Stringad) 方法的接口，由于本程序是窗体程序，所以本类继承 JPmme 类，
 * 并实现动作事件的处理方法 actionPerformed(ActionEvent e)。
 *
 * 最后，定义卖方（Seller）类和买方（Buyer）类，它们是具体同事类，是客户（Customer）类的子类，
 * 它们实现了父类中的抽象方法，通过中介者类进行信息交流，
 */
//抽象中介者：中介公司
interface Medium
{
    void register(Customer member); //客户注册
    void relay(String from, String ad); //转发
}

//具体中介者：房地产中介
class EstateMedium implements Medium
{
    private List<Customer> members=new ArrayList<Customer>();
    @Override
    public void register(Customer member)
    {
        if(!members.contains(member))
        {
            members.add(member);
            member.setMedium(this);
        }
    }
    @Override
    public void relay(String from, String ad)
    {
        for(Customer ob:members)
        {
            String name=ob.getName();
            if(!name.equals(from))
            {
                ((Customer)ob).receive(from,ad);
            }
        }
    }
}

//抽象同事类：客户
abstract class Customer extends JFrame implements ActionListener
{
    private static final long serialVersionUID=-7219939540794786080L;
    protected Medium medium;
    protected String name;
    JTextField SentText;
    JTextArea ReceiveArea;
    public Customer(String name)
    {
        super(name);
        this.name=name;
    }
    void ClientWindow(int x,int y)
    {
        Container cp;
        JScrollPane sp;
        JPanel p1,p2;
        cp=this.getContentPane();
        SentText=new JTextField(18);
        ReceiveArea=new JTextArea(10,18);
        ReceiveArea.setEditable(false);
        p1=new JPanel();
        p1.setBorder(BorderFactory.createTitledBorder("接收内容："));
        p1.add(ReceiveArea);
        sp=new JScrollPane(p1);
        cp.add(sp,BorderLayout.NORTH);
        p2=new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder("发送内容："));
        p2.add(SentText);
        cp.add(p2,BorderLayout.SOUTH);
        SentText.addActionListener(this);
        this.setLocation(x,y);
        this.setSize(250, 330);
        this.setResizable(false); //窗口大小不可调整
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String tempInfo=SentText.getText().trim();
        SentText.setText("");
        this.send(tempInfo);
    }
    @Override
    public String getName()
    {    return name;   }
    public void setMedium(Medium medium)
    {      this.medium=medium;  }
    public abstract void send(String ad);
    public abstract void receive(String from,String ad);
}

//具体同事类：卖方
class Seller extends Customer
{
    private static final long serialVersionUID=-1443076716629516027L;
    public Seller(String name)
    {
        super(name);
        ClientWindow(50,100);
    }
    @Override
    public void send(String ad)
    {
        ReceiveArea.append("我(卖方)说: "+ad+"\n");
        //使滚动条滚动到最底端
        ReceiveArea.setCaretPosition(ReceiveArea.getText().length());
        medium.relay(name,ad);
    }
    @Override
    public void receive(String from, String ad)
    {
        ReceiveArea.append(from +"说: "+ad+"\n");
        //使滚动条滚动到最底端
        ReceiveArea.setCaretPosition(ReceiveArea.getText().length());
    }
}

//具体同事类：买方
class Buyer extends Customer
{
    private static final long serialVersionUID = -474879276076308825L;
    public Buyer(String name)
    {
        super(name);
        ClientWindow(350,100);
    }
    @Override
    public void send(String ad)
    {
        ReceiveArea.append("我(买方)说: "+ad+"\n");
        //使滚动条滚动到最底端
        ReceiveArea.setCaretPosition(ReceiveArea.getText().length());
        medium.relay(name,ad);
    }
    @Override
    public void receive(String from, String ad)
    {
        ReceiveArea.append(from +"说: "+ad+"\n");
        //使滚动条滚动到最底端
        ReceiveArea.setCaretPosition(ReceiveArea.getText().length());
    }
}