##Thread-Specific-Storage模式
    
    即使只有一个入口，也会在内部为给个线程分配特有存储空间，
    java.lang.ThreadLocal的对象可以看作是储物间，是管理多个对象的集合
    
##Thread-Specific-Storage模式角色分析
    
    1，Client (委托者)
    将处理委托给TSObjectProxy角色（TS：ThreadSpecific）
    
    2，TSObjectProxy（线程特有对象的代理人）
    内部维护了一个TSObjectCollection角色来获取与线程对应的TSObject。
    一般是java.lang.ThreadLocal
    
    3，TSObject（线程对特有对象）
    TSObject保存者单个线程特有的信息。
    
##线程特有相关的知识点

    1，局部变量与java.lang.ThreadLocal
        线程本身是有自己特定的存储空间，即用于保存方法的局部变量的栈。
        方法中定义的变量一定是线程特有的，其他线程无法访问。但是这些变量在方法调用结束以后，方法占用的内存会出栈然后消失
    而java.lang.ThreadLocal则与方法调用无关，它是一个用于为线程分配特有的存储空间的类。
    
    2，保存线程特有的信息的位置
        a，线程外（thread-external）
        使用线程外部的对象（数据结构）来保存线程特有的信息，便不需要修改既有的线程类来适用于任何线程，如此会导致线程类的代码难以理解；
        
        b，线程内（thread-internal）
        在线程内部保存线程特有的信息，这样会让线程对象和具体信息产生耦合，导致线程类不可复用。
        
    3，线程特有意味着线程对象专属
        线程特有指：各个线程各自专有，一个线程对应于一份数据对象，不存在被多个线程共享的数据就不会发生多个线程访问情况；
    
    4，吞吐量的影响
        1，不改结构来实现程序
        2，没有显示地执行互斥处理，因此编程出差的可能性降低
        Thread-Specific-Storage模式看中的是可复用性，但是在TSObjectCollection中可能存在隐藏的互斥处理，在获取线程特有的TSObject时
        性能开销很大；
    
    5，上下文的危险性
        Thread.currentThread()这种上下文形式的调用很危险，虽然使得程序结构变得简单，但是全局信息很难控制信息的变化和使用
        与全局变量的危险性一致；    
        
    6，线程与信息的关系类型
        a，基于任务
        关注的焦点是任务本身而不关心实际的任务执行者；
        基于任务的方式不在线程中保存信息（上下文，状态等）。这些信息不保存在线程对象中，
        而是保存在线程之间交互的对象（数据以及执行请求的接口）中。而这些线程间交互的对象一般被称之为任务消息，请求或是命令。
        
        b，基于角色    
        关注的焦点是任务的执行者，将任务相关的信息（上下文，状态等）保存在线程对象中，我们将这样的线程对象称为角色。    

##线程特有总结

    Thread-Specific-Storage模式
    1，找出TSObject角色并抽象出功能接口ITS；
    2，定义TSObjectProxy角色并实现ITS接口；
    3，定义TSObjectCollection角色来维护"Client角色和TSObject之间的对应关系"；
    4，在TSObjectProxy使用TSObjectCollection角色来获取与当前线程对应的TSObject代理实现ITS的功能接口；
    不过，使用本模式以后，Thread.currentThread上下文就会被引入加大了代码的理解困难程度；
        