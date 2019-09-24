package com.wangp.design_pattern.pattern.behaviourmodel;

/**
 * 状态（State）模式
 * <p>
 * 对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，允许状态对象在其内部状态发生改变时改变其行为。
 * <p>
 * 状态模式是一种对象行为型模式，其主要优点如下。
 * 1.状态模式将与特定状态相关的行为局部化到一个状态中，并且将不同状态的行为分割开来，满足“单一职责原则”。
 * 2.减少对象间的相互依赖。将不同的状态引入独立的对象中会使得状态转换变得更加明确，且减少对象间的相互依赖。
 * 3.有利于程序的扩展。通过定义新的子类很容易地增加新的状态和转换。
 * <p>
 * 状态模式的主要缺点如下。
 * 1.状态模式的使用必然会增加系统的类与对象的个数。
 * 2.状态模式的结构与实现都较为复杂，如果使用不当会导致程序结构和代码的混乱。
 */
public class StateModel {
    public static void main(String[] args) {
        ScoreContext account = new ScoreContext();
        System.out.println("学生状态为：");
        account.add(30);
        account.add(40);
        account.add(25);
        account.add(-15);
        account.add(-25);
    }
}

/**
 * 状态模式包含以下主要角色。
 * 环境（Context）角色：也称为上下文，它定义了客户感兴趣的接口，维护一个当前状态，并将与状态相关的操作委托给当前状态对象来处理。
 * 抽象状态（State）角色：定义一个接口，用以封装环境对象中的特定状态所对应的行为。
 * 具体状态（Concrete    State）角色：实现抽象状态所对应的行为。
 */
class StateContext {
    State state;

    public StateContext() {
        state = new ConcreteStateA();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    void Handle() {
        state.Handle(this);
    }
}

interface State {
    void Handle(StateContext stateContext);
}

class ConcreteStateA implements State {

    @Override
    public void Handle(StateContext stateContext) {
        System.out.println("当前状态是 A.");
        stateContext.setState(new ConcreteStateB());
    }
}

class ConcreteStateB implements State {

    @Override
    public void Handle(StateContext stateContext) {
        System.out.println("当前状态是 B.");
        stateContext.setState(new ConcreteStateA());
    }
}

/***
 *用“状态模式”设计一个学生成绩的状态转换程序。
 *
 * 分析：本实例包含了“不及格”“中等”和“优秀” 3 种状态，
 * 当学生的分数小于 60 分时为“不及格”状态，当分数大于等于 60 分且小于 90 分时为“中等”状态，
 * 当分数大于等于 90 分时为“优秀”状态，我们用状态模式来实现这个程序。
 *
 * 首先，定义一个抽象状态类（AbstractState），其中包含了环境属性、状态名属性和当前分数属性，以及加减分方法 addScore(intx)
 * 和检查当前状态的抽象方法 checkState()；然后，定义“不及格”状态类 LowState、“中等”状态类 MiddleState 和“优秀”状态类 HighState，
 * 它们是具体状态类，实现 checkState() 方法，负责检査自己的状态，并根据情况转换；
 * 最后，定义环境类（ScoreContext），其中包含了当前状态对象和加减分的方法 add(int score)，客户类通过该方法来改变成绩状态。
 */

abstract class AbstractState {
    protected String stateName;
    protected ScoreContext hj;
    protected int score;

    abstract void checkState();

    void addSocre(int score) {
        this.score+=score;
        System.out.println("加上"+score+"当前分数："+this.score);
        checkState();
        System.out.println("分，\t当前状态："+hj.getState().stateName);
    }
}

class LowState extends AbstractState {

    public LowState(ScoreContext sc) {
        hj = sc;
        stateName = "不及格";
        score = 0;
    }

    public LowState(AbstractState state) {
        hj = state.hj;
        stateName = "不及格";
        score = state.score;
    }

    @Override
    void checkState() {
        if (score >= 90) {
            hj.setState(new HighState(this));
        } else if (score >= 60) {
            hj.setState(new MiddleState(this));
        }
    }

}

class MiddleState extends AbstractState {

    public MiddleState(AbstractState state) {
        hj = state.hj;
        stateName = "中等";
        score = state.score;
    }

    @Override
    public void checkState() {
        if (score < 60) {
            hj.setState(new LowState(this));
        } else if (score >= 90) {
            hj.setState(new HighState(this));
        }
    }
}

class HighState extends AbstractState {

    public HighState(AbstractState state) {
        hj = state.hj;
        stateName = "优秀";
        score = state.score;
    }

    @Override
    public void checkState() {
        if (score < 60) {
            hj.setState(new LowState(this));
        } else if (score < 90) {
            hj.setState(new MiddleState(this));
        }
    }
}

class ScoreContext {
    private AbstractState state;

    public AbstractState getState() {
        return state;
    }

    public void setState(AbstractState state) {
        this.state = state;
    }

    ScoreContext() {
        state = new LowState(this);
    }
    void add(int score){
        state.addSocre(score);
    }
}
