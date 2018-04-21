##SingleThreadedExecution

    :同一时间，仅允许一个线程执行处理；而将同一时间仅允许一个线程执行的代码范围称为临界区，
    站在执行处理的线程而言，我们需要确定临界区（临界阈）;


1，划分SingleThreadedExecution模型：
    SharedResource（共享资源）
    可以被多个线程访问的类，包含两类方法：
    1，safe Method:多个线程同时调用，也不会发生问题；
    2，unsafe Method：多个线程同时调用，实例状态可能发生分歧；

2，使用场景以及特点分析：
    1，Synchronized方法由于会操作对象的monitor，
    那么会增加程序调用的耗时，会稍微降低程序性能；
    2，当ShareResource对象的实例存在由被多个线程同时读写的可能性时，
    我们就需要考虑使用SingleThreadedExecution；
    3，当SharedResource角色的状态可能变化时就需要考虑SingleThreadedExecution，
    否则当创建实例后，实例的状态在其生命周期过程中便永远不会发生变化，也不需要考虑；

3，死锁与生存性问题
    死锁是指两个线程分别持有锁并相互等待对方释放锁的现象,
    发生死锁的情况：
        1，存在有多个SharedResources角色；
        2，线程在持有其中之一的SharedResources时，还在试图获取其他的SharedResources对象；
        3，获取SharedResources对象的顺序并不固定；

4，可复用性和继承反常
    子类继承SharedResources时可能，改写了父类的safeMethod方法，和safe的属性；

5，临界区的大小和性能
    1，进入synchronized方法时，获取对象锁，该操作花费时间。
    2，线程冲突引起的等待，

6，原子操作：
    如果某个线程正在执行synchronized方法，其他线程就无法进入；
    那么从多线程的角度看，这个便是"不可分割的操作"，也就是我们称为的原子操作；

7，java编程规范中定了一些原子操作：
    1，char，int等基本类型的赋值和引用操作都是原子的，
    2，对于对象等引用类型的赋值和引用操作也是原子的；
    3，对于long，double的赋值和引用都是非原子的操作，那么对于它们的操作需要注意线程安全的问题；