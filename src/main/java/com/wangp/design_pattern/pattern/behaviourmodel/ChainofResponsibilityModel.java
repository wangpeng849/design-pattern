package com.wangp.design_pattern.pattern.behaviourmodel;

/**
 * 责任链模式
 * <p>
 * 为了避免请求发送者与多个请求处理者耦合在一起，将所有请求的处理者通过前一对象记住其下一个对象的引用而连成一条链；
 * 当有请求发生时，可将请求沿着这条链传递，直到有对象处理它为止。
 * <p>
 * 在责任链模式中，客户只需要将请求发送到责任链上即可，无须关心请求的处理细节和请求的传递过程，
 * 所以责任链将请求的发送者和请求的处理者解耦了。
 * <p>
 * 责任链模式是一种对象行为型模式，其主要优点如下。
 * 1.降低了对象之间的耦合度。该模式使得一个对象无须知道到底是哪一个对象处理其请求以及链的结构，发送者和接收者也无须拥有对方的明确信息。
 * 2.增强了系统的可扩展性。可以根据需要增加新的请求处理类，满足开闭原则。
 * 3.增强了给对象指派职责的灵活性。当工作流程发生变化，可以动态地改变链内的成员或者调动它们的次序，也可动态地新增或者删除责任。
 * 4.责任链简化了对象之间的连接。每个对象只需保持一个指向其后继者的引用，不需保持其他所有处理者的引用，
 * 这避免了使用众多的 if 或者 if···else 语句。
 * 5.责任分担。每个类只需要处理自己该处理的工作，不该处理的传递给下一个对象完成，明确各类的责任范围，符合类的单一职责原则。
 * <p>
 * 其主要缺点如下。
 * 1.不能保证每个请求一定被处理。由于一个请求没有明确的接收者，所以不能保证它一定会被处理，该请求可能一直传到链的末端都得不到处理。
 * 2.对比较长的职责链，请求的处理可能涉及多个处理对象，系统性能将受到一定影响。
 * 3.职责链建立的合理性要靠客户端来保证，增加了客户端的复杂性，可能会由于职责链的错误设置而导致系统出错，如可能会造成循环调用。
 *
 *
 * 应用场景
 * 1.多个对象可以处理一个请求，哪个对象处理该请求由运行时刻自动确定。
 * 2.可动态指定一组对象处理请求，或添加新的处理者。
 * 3.在不明确指定请求处理者的情况下，向多个处理者中的一个提交请求。
 */
public class ChainofResponsibilityModel {
    public static void main(String[] args) {
        Leader t1 = new ClassAdviser();
        Leader t2 = new DepartmentHead();
        Leader t3 = new Dean();
        t1.setNext(t2);
        t2.setNext(t3);

        t1.handlerRequest(19);
    }
}

/***
 * 模式的结构
 * 职责链模式主要包含以下角色。
 * 抽象处理者（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和一个后继连接。
 * 具体处理者（Concrete Handler）角色：实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者。
 * 客户类（Client）角色：创建处理链，并向链头的具体处理者对象提交请求，它不关心处理细节和请求的传递过程。
 */
abstract class Handler {
    Handler next;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    abstract void handleRequest(String request);
}

class ConcreteHandler1 extends Handler {

    @Override
    void handleRequest(String request) {
        if (request.equals("one")) {
            System.out.println("具体处理者1111负责处理该请求！");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}

class ConcreteHandler2 extends Handler {

    @Override
    void handleRequest(String request) {
        if (request.equals("two")) {
            System.out.println("具体处理者2222负责处理该请求！");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}

/***
 *
 *假如规定学生请假小于或等于 2 天，班主任可以批准；小于或等于 7 天，系主任可以批准；小于或等于 10 天，院长可以批准；
 * 其他情况不予批准；这个实例适合使用职责链模式实现。
 * 首先，定义一个领导类（Leader），它是抽象处理者，
 * 包含了一个指向下一位领导的指针 next 和一个处理假条的抽象处理方法 handleRequest(int LeaveDays)；
 * 然后，定义班主任类（ClassAdviser）、系主任类（DepartmentHead）和院长类（Dean），
 * 它们是抽象处理者的子类，是具体处理者，必须根据自己的权力去实现父类的 handleRequest(int LeaveDays) 方法，
 * 如果无权处理就将假条交给下一位具体处理者，直到最后；客户类负责创建处理链，并将假条交给链头的具体处理者（班主任）
 */

abstract class Leader {
    private Leader next;
    public abstract void handlerRequest(int days);

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }
}

class ClassAdviser extends Leader {

    @Override
    public void handlerRequest(int days) {
        if (days <= 2) {
            System.out.println("ClassAdviser say :Yes,you can!");
            return;
        }
        if (super.getNext() != null) {
            super.getNext().handlerRequest(days);
        }
    }
}

class DepartmentHead extends Leader {

    @Override
    public void handlerRequest(int days) {
        if (days <= 7) {
            System.out.println("DepartmentHead say : Yes,you can!");
            return;

        }
        if (super.getNext() != null) {
            super.getNext().handlerRequest(days);
        }
    }
}

class Dean extends Leader {

    @Override
    public void handlerRequest(int days) {
        if (days <= 10) {
            System.out.println("Dean say: Yes,you can!");
            return;
        }
        System.out.println("不能请假！");
        return;
    }
}