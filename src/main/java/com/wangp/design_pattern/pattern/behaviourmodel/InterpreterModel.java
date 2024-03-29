package com.wangp.design_pattern.pattern.behaviourmodel;

/**
 * 解释器（Interpreter）模式
 * <p>
 * 给分析对象定义一个语言，并定义该语言的文法表示，再设计一个解析器来解释语言中的句子。也就是说，用编译语言的方式来分析应用中的实例。
 * 这种模式实现了文法表达式处理的接口，该接口解释一个特定的上下文。
 * <p>
 * 这里提到的文法和句子的概念同编译原理中的描述相同，“文法”指语言的语法规则，而“句子”是语言集中的元素。
 * 例如，汉语中的句子有很多，“我是中国人”是其中的一个句子，可以用一棵语法树来直观地描述语言中的句子。
 * <p>
 * 解释器模式是一种类行为型模式，其主要优点如下。
 * 扩展性好。由于在解释器模式中使用类来表示语言的文法规则，因此可以通过继承等机制来改变或扩展文法。
 * 容易实现。在语法树中的每个表达式节点类都是相似的，所以实现其文法较为容易。
 * <p>
 * 解释器模式的主要缺点如下。
 * 执行效率较低。解释器模式中通常使用大量的循环和递归调用，当要解释的句子较复杂时，其运行速度很慢，且代码的调试过程也比较麻烦。
 * 会引起类膨胀。解释器模式中的每条规则至少需要定义一个类，当包含的文法规则很多时，类的个数将急剧增加，导致系统难以管理与维护。
 * 可应用的场景比较少。在软件开发中，需要定义语言文法的应用实例非常少，所以这种模式很少被使用到。
 */
public class InterpreterModel {
}

//抽象表达式类
interface AbstractExpression {
    public Object interpret(String info);    //解释方法
}

//终结符表达式类
class TerminalExpression implements AbstractExpression {
    @Override
    public Object interpret(String info) {
        //对终结符表达式的处理
        return null;
    }
}

//非终结符表达式类
class NonterminalExpression implements AbstractExpression {
    private AbstractExpression exp1;
    private AbstractExpression exp2;

    @Override
    public Object interpret(String info) {
        //非对终结符表达式的处理
        return null;
    }
}

//环境类
class InterpreterContext {
    private AbstractExpression exp;

    public InterpreterContext() {
        //数据初始化
    }

    public void operation(String info) {
        //调用相关表达式类的解释方法
    }
}

