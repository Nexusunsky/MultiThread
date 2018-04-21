##ImmutableMode

    Immutable模式中存在着确保实例不会改变的类，在访问这些实例时并不需要执行耗时
    的互斥处理，能够巧妙的利用该模式，定能提高程序的性能；
    Immutable角色是一个类，在这个角色中，字段的值不可以修改，也不存在修改实例对象
    的方法，意味着Immutable的对象创建完成，在该对象的生命周期内，
    其状态便永远不会发生改变。

##拓展思路：

    1，Immutable模式的使用场景；
        a,对象创建后，状态不再发生变化，对象的状态是由其字段的值决定的。
        b,对象是共享的，且被频繁访问时，Immutable模式的优点是不需要
        使用synchronized关键字，能够在不失去安全性和生存性的前提下提高性能。

    2，考虑成对的mutable类和Immutable类；拆分场景中变化的对象和不会变化的对象
    来表明具体类的语义性；例如：java.lang.String和java.lang.StringBuffer类

    3，确保不可变性；
       不可变性是一个很微妙的性质，对代码的细微变动，都有可能引起对象不可变性的变化
       经常容易出现的问题：
            将字段保持的对象直接最为getter方法返回；
            将构造方法的参数传入的字段直接赋值给字段；

##final

    Java中的fianl在不同的场景之下，有多种不同的用途；
    1，final类
    如果类的声明中加上final，则表示该类无法扩展继承；
    2，final方法
    如果在实例方法的声明中加上final，则表示该方法不会被子类的方法重写。
    如果在静态方法的声明中加上final，则表示该方法不会被子类的方法隐藏。
    3，final字段
    final在创建对象时，只能被赋值一次。
    4，final局部变量和方法参数
    局部变量最多可以被赋值一次；
    方法参数不能再被赋值；

##集合类与多线程

    1，非线程安全的java.util.ArrayList类
        用于提供可调整大小的数组，是非线程安全的，当多个线程并发执行读写时，是不安全的；

    2，利用Collections.synchronizedList方法所进行的方法同步
        java.util.ArrayList是线程非安全的，但是使用Collections.synchronizedList
        方法进行同步后，保存到了变量list中

    3，利用copy-on-write的java.util.concurrent.CopyOnWriteArrayList类
    java.util.concurrent.CopyOnWriteArrayList是线程安全的，与使用Collections.synchronizedList
    不同，CopyOnWriteArrayList是使用copy-on-write技术来避免读写冲突。

