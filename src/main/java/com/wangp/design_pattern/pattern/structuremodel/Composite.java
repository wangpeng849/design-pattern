package com.wangp.design_pattern.pattern.structuremodel;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * <p>
 * 有时又叫作部分-整体模式，它是一种将对象组合成树状的层次结构的模式，
 * 用来表示“部分-整体”的关系，使用户对单个对象和组合对象具有一致的访问性。
 * <p>
 * 组合模式的主要优点有：
 * 1.组合模式使得客户端代码可以一致地处理单个对象和组合对象，无须关心自己处理的是单个对象，还是组合对象，这简化了客户端代码；
 * 2.更容易在组合体内加入新的对象，客户端不会因为加入了新的对象而更改源代码，满足“开闭原则”；
 * <p>
 * 其主要缺点是：
 * 1.设计较复杂，客户端需要花更多时间理清类之间的层次关系；
 * 2.不容易限制容器中的构件；
 * 3.不容易用继承的方法来增加构件的新功能；
 */
public class Composite {
    public static void main(String[] args) {
        //最后“大袋子”中的内容有：{1 双李宁牌运动鞋（单价 198 元）、白色小袋子{2 包韶关香菇（单价 68 元）、
        // 3 包韶关红茶（单价 180 元）}、中袋子{1 个景德镇瓷器（单价 380 元）、
        // 红色小袋子{2 包婺源特产（单价 7.9 元）、1 张婺源地图（单价 9.9 元）}}}
        Bags bigBag = new Bags("大袋子");
        Bags whiteBag = new Bags("白色袋子");
        Bags middleBag = new Bags("中袋子");
        Bags redBag = new Bags("红色袋子");
        Goods shoes = new Goods("李宁运动鞋",1,198);
        Goods mushroom = new Goods("香菇",2,68);
        Goods blacktea = new Goods("红茶",3,180);
        Goods glasses = new Goods("陶瓷",1,380);
        Goods specil = new Goods("特产",2,7.9f);
        Goods map = new Goods("地图",1,9.9f);
        bigBag.add(shoes);
        bigBag.add(whiteBag);
        bigBag.add(middleBag);
        whiteBag.add(mushroom);
        whiteBag.add(blacktea);
        middleBag.add(glasses);
        middleBag.add(redBag);
        redBag.add(specil);
        redBag.add(map);
        System.out.println("选购的商品有：");
        bigBag.show();
        System.out.println("\n总价为："+  bigBag.calculation());

    }
}

/**
 * 组合模式包含以下主要角色。
 * 抽象构件（Component）角色：它的主要作用是为树叶构件和树枝构件声明公共接口，并实现它们的默认行为。
 * 在透明式的组合模式中抽象构件还声明访问和管理子类的接口；在安全式的组合模式中不声明访问和管理子类的接口，管理工作由树枝构件完成。
 * <p>
 * 树叶构件（Leaf）角色：是组合中的叶节点对象，它没有子节点，用于实现抽象构件角色中 声明的公共接口。
 * <p>
 * 树枝构件（Composite）角色：是组合中的分支节点对象，它有子节点。它实现了抽象构件角色中声明的接口，
 * 它的主要作用是存储和管理子部件，通常包含 Add()、Remove()、GetChild() 等方法。
 * <p>
 * <p>
 * 组合模式分为透明式的组合模式和安全式的组合模式。
 * <p>
 * (1) 透明方式：在该方式中，由于抽象构件声明了所有子类中的全部方法，所以客户端无须区别树叶对象和树枝对象，对客户端来说是透明的。
 * 但其缺点是：树叶构件本来没有 Add()、Remove() 及 GetChild() 方法，却要实现它们（空实现或抛异常），这样会带来一些安全性问题
 * (2) 安全方式：在该方式中，将管理子构件的方法移到树枝构件中，抽象构件和树叶构件没有对子对象的管理方法，这样就避免了上一种方式的安全性问题，
 * 但由于叶子和分支有不同的接口，客户端在调用时要知道树叶对象和树枝对象的存在，所以失去了透明性。
 */

//抽象构件
interface Component {
    public void add(Component c);

    public void remove(Component c);

    public Component getChild(int i);

    public void operation();
}

//树叶构件
class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void add(Component c) {
    }

    @Override
    public void remove(Component c) {
    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println("树叶" + name + "：被访问！");
    }
}

//树枝构件
class Compositee implements Component {
    private ArrayList<Component> children = new ArrayList<Component>();

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public Component getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        for (Object obj : children) {
            ((Component) obj).operation();
        }
    }
}

/**
 * 假如李先生到韶关“天街e角”生活用品店购物，
 * 用 1 个红色小袋子装了 2 包婺源特产（单价 7.9 元）、1 张婺源地图（单价 9.9 元）；
 * 用 1 个白色小袋子装了 2 包韶关香藉（单价 68 元）和 3 包韶关红茶（单价 180 元）；
 * 用 1 个中袋子装了前面的红色小袋子和 1 个景德镇瓷器（单价 380 元）；
 * 用 1 个大袋子装了前面的中袋子、白色小袋子和 1 双李宁牌运动鞋（单价 198 元）。
 * <p>
 * 最后“大袋子”中的内容有：
 * {1 双李宁牌运动鞋（单价 198 元）、白色小袋子{2 包韶关香菇（单价 68 元）、
 * 3 包韶关红茶（单价 180 元）}、中袋子{1 个景德镇瓷器（单价 380 元）、
 * 红色小袋子{2 包婺源特产（单价 7.9 元）、1 张婺源地图（单价 9.9 元）}}}，
 * 现在要求编程显示李先生放在大袋子中的所有商品信息并计算要支付的总价。
 */

interface Articles {
    float calculation();

    void show();
}

//物品
class Goods implements Articles {
    private String name;
    private int quantity;
    private float price;

    public Goods(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public float calculation() {
        return quantity * price;
    }

    @Override
    public void show() {
        System.out.println(this.name+"(数量：" + this.quantity + ",单价：" + this.price+")");
    }
}

//树枝构件：袋子
class Bags implements Articles {
    private String name;
    private List<Articles> bags = new ArrayList<>();

    public Bags(String name) {
        this.name = name;
    }

    public void add(Articles articles) {
        bags.add(articles);
    }

    public void remove(Articles articles) {
        bags.remove(articles);
    }

    public Articles getChild(int index) {
        return bags == null ? null : bags.get(index);
    }

    @Override
    public float calculation() {
        float sum = 0;
        for (Articles articles : bags) {
            sum += articles.calculation();
        }
        return sum;
    }

    @Override
    public void show() {
        for (Articles obj : bags) {
            obj.show();
        }
    }
}