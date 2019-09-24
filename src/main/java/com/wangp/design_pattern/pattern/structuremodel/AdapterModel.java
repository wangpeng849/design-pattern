package com.wangp.design_pattern.pattern.structuremodel;

/**
 * 适配器模式
 *
 * 将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
 * 适配器模式分为类结构型模式和对象结构型模式两种，
 * 前者类之间的耦合度比后者高，且要求程序员了解现有组件库中的相关组件的内部结构，所以应用相对较少些
 *
 * 该模式的主要优点如下。
 * 1.客户端通过适配器可以透明地调用目标接口。
 * 2.复用了现存的类，程序员不需要修改原有代码而重用现有的适配者类。
 * 3.将目标类和适配者类解耦，解决了目标类和适配者类接口不一致的问题。
 *
 * 其缺点是：对类适配器来说，更换适配器的实现过程比较复杂。
 *
 *
 * 适配器模式（Adapter）包含以下主要角色。
 * 1.目标（Target）接口：当前系统业务所期待的接口，它可以是抽象类或接口。
 * 2.适配者（Adaptee）类：它是被访问和适配的现存组件库中的组件接口。
 * 3.适配器（Adapter）类：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者。
 */
public class AdapterModel {


    public static void main(String[] args) {
        System.out.println("适配器测试...");
        Motor motor =  new EAdapter();
        motor.driver();
    }
}

/**
 * 目标（Target）接口：
 * 当前系统业务所期待的接口，它可以是抽象类或接口。
 */
interface Target{
    public void request();
}

/**
 * 适配者（Adaptee）类：
 * 它是被访问和适配的现存组件库中的组件接口。
 */
class Adaptee  {

    public void speclificRequest() {
        System.out.println("适配器业务代码应用.");
    }

}

class ClassAdapter extends Adaptee implements Target{

    @Override
    public void request() {
        System.out.println("适配器启动...");
        speclificRequest();
    }
}

class ObjectAdapter implements Target{

    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        System.out.println("适配器启动...");
        adaptee.speclificRequest();
    }
}
/**
 * 理解适配器 小案例：
 * 新能源汽车的发动机有电能发动机（Electric Motor）和光能发动机（Optical Motor）等，
 * 各种发动机的驱动方法不同，例如，电能发动机的驱动方法 electricDrive() 是用电能驱动，
 * 而光能发动机的驱动方法 opticalDrive() 是用光能驱动，它们是适配器模式中被访问的适配者。
 *
 * 客户端希望用统一的发动机驱动方法 drive() 访问这两种发动机，所以必须定义一个统一的目标接口 Motor，
 * 然后再定义电能适配器（Electric Adapter）和光能适配器（Optical Adapter）去适配这两种发动机。
 */

/**
 * 发动机
 */
interface Motor{
    void driver();
}

/**
 * 电能适配者
 */
class EMotor   {
    public void electricDrive() {
        System.out.println("电能发动机驱动汽车！");
    }
}

/**
 * 光能适配者
 */
class OMotor  {

    public void opticalDrive() {
        System.out.println("光能发动机驱动汽车！");
    }
}

//电能适配器
class EAdapter implements Motor{
    private EMotor emotor;
    public EAdapter(){
        emotor=new EMotor();
    }
    @Override
    public void driver()
    {
        emotor.electricDrive();
    }
}

//光能适配器
class OAdapter implements Motor {
    private OMotor omotor;

    public OAdapter() {
        omotor = new OMotor();
    }

    @Override
    public void driver() {
        omotor.opticalDrive();
    }
}