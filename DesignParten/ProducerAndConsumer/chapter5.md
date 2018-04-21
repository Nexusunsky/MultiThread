##ProducerAndConsumer

    生产者安全地将数据交给消费者，在生产者和消费之间需要一个桥梁角色，
    该角色用于消除线程间处理速度的差异。


   角色分析：

    1，Data角色由Producer角色生产，供Consumer角色使用；
    2，Channel角色保管从Producer生产的Data，还响应Consumer角色的请求。
    为了确保安全性，channel角色会对Producer角色和Consumer角色的访问执行互斥处理。
    Channel角色位于Producer和Consumer之间，承担用于传递Data的中转站，通信的任务。
    3，传递Data的顺序由Channel角色控制，有三种顺序来进行选择：
        队列---先接收的先传递
        栈---后接收的先传递
        优先队列---'优先'的先传递
    4，存在中间角色的意义
        Channel实现线程的协调运行
        总结线程处理的操作：
            线程的协调运行要考虑：放在中间的对象
            线程的互斥处理要考虑：应该保护的对象


##理解线程的InterruptedException

    InterruptedException 表示该方法包含如下两层含义：
        "耗时的方法"
        "可以取消的方法"

   包含有InterruptedException的方法为wait，sleep，join三个方法；

    耗时：
        线程执行wait方法后，进入对象的等待队列，等待notify和notifyAll。
        线程执行sleep后，会暂停执行
        线程执行join后，会等待指定线程终止

    可以取消：
       取消"wait在等待notify"的处理
       取消"在sleep指定时间内停止执行"的处理
       取消"join方法等待其他线程终止"的处理

    在wait的方法执行时，需要注意，线程进入等待队列时，已经释放了锁，
    当正在wait的线程被调用interrupt方法时（即线程被取消执行），该线程会在重新获取
    锁之后抛出InterruptedException异常，而在获取锁之前，线程不会抛出异常；

    说明：notify和interrupt，interrupted方法比较：
        1，执行notify方法时线程必须要获取对象的锁
        2，interrupt是线程对象的方法，而并不需要获取对象的锁。
        3，interrupt方法只是改变了线程对象的中断状态，而sleep，wait，join会检查线程内部的中断状态，从而抛出异常；
        4，Thread.interrupted方法-检查并清除中断状态；