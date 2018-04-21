##Java内存模型
    
    Java内存模型定义了java线程在访问内存时会发生什么。
    
    与java内存模式交互时的指南：
        1，使用synchronized或volatile来保护在多个线程之间共享的对象；
        2，将常量字段设置为final
        3，不要从构造函数中泄露this
        
###重排序        
    
    重排序：Reorder，指编译器和Java虚拟机通过改变程序的处理顺序来优化程序，冲排序被广泛用于提高程序性能，
    但是无法被我们感知，判读十分或者何时进行了重排序；    
    
    存在数据竞争：对一个对象的字段，有写数据的线程，读书的线程，但是我们没有使用synchronized关键字和volatile关键字修饰该字段来正确地同步线程时，
    我们称这种没有同步的状态为"存在数据竞争"；

###可见性
    
    可见性：visibility，多线程环境下，如果没有使用synchronized关键字和volatile关键字正确地进行同步，线程A写入字段中的值可能并不会立即对
    线程B可见，而我们必须非常清楚地知道在什么情况下一个线程的写值对其他线程是可见的。
    
####共享内存与操作
    
    1，共享内存是所有线程共享的存储空间，也被称为堆内存（heap）；
    2，对象会被全部保存在共享内存中，同时对象中的字段也存在于共享内存中；
    3，数组的元素也被保存在共享内存中；
    4，new关键字产生的对象被非配在共享内存的存储空间。
    5，局部变量不会被保存在共享内存中。通常，局部变量的参数，方法的形式参数，catch语句块中编写的异常处理器的参数等
    都不会保存在共享内存中，所以其他线程不会访问到它们；
    
    在Java内存模型中，只有多个线程访问的共享内存才会发生问题。
    
    1，----非同步操作：非volatile字段的读写，通过缓存来执行  
        a，normal read操作
        b，normal write操作
    
    2，----同步操作：同步操作具有防止重排序，控制可见性的效果。             
        c，volatile read操作
        d，volatile write操作
    
    3，----同步操作：使用synchronized 字段进行互斥处理的操作。    
        e，lock 操作
        f，unlock 操作
    
####缓存
    
    缓存（cache）：CPU的缓存，寄存器（register）以及Java虚拟机临时保存的变量等统称为缓存。
   
   
###Java线程安全
   
    在Java内存模型中，进行synchronized和可见性控制是及其消耗性能的，因此我们需要有条件的进行控制；
    我们需要研究这样的条件和控制的方法：synchronized，volatile，final 关键字；

####synchronized    
    
    synchronized具有"线程互斥的处理"和"同步处理"两种功能。
    
###线程的互斥处理
    
    1，如果程序中有synchronized关键字，线程就会进行lock／unlock操作。
        synchronized开始时获取锁（lock）
        synchronized结束时释放锁（unlock）
        
    2，当线程在wait内部等待时，会释放锁，但是线程会被等待到锁的等待队列中，
       当等待在锁的等待队列中收到notify的通知时，还需要重新获取到锁，才能继续运行。
    
    3，线程互斥的实现：
       一个对象只对应一个监视锁，当有线程获取到该锁时，其他线程就会进入到该锁的等待队列中，从而实现线程互斥。
       
    4，synchronized关键字（lock／unlock操作）并不仅仅进行线程的互斥处理。
       Java内存模型确保了某个线程在进行unlock操作前进行的所有写入操作对同一个对象的监视锁操作的线程都是可见的。
        
        细节：
            进行unlock操作后，写入缓存中的内容会被强制写入共享内存中
            进行lock操作后，缓存中的内容会失效，然后共享内存中的最新内容会被强制重新读取到缓存中
                  
####volatile
    
    volatile具有"同步处理"和"对long和double的原子操作"这两种功能
    
###同步处理
    
    某个线程对volatile字段进行的写操作的结果对其他线程立即可见。
    并且，volatile字段禁止读取和写入前后发生重排序。
    Volatile关键字修饰的字段多被用作判断对象是否变为特定状态的标志。

    同synchronized一样，在volatile中也存在release和acquire一样的概念，
        
        acquire                                               release
        volatile read                                         volatile write 
        synchronized lock                                     synchronized unlock
        线程的启动                                             线程的启动的第一个操作
        检测线程的终止（join，isAlive）                         线程终止前的最后一个操作
        检测中断（isInterrupted，Thread.interrupted等）         中断 
        线程的第一个操作                                        向字段写入默认值
        
        
#####volatile字段的赋值语句的位置很重要
    
    由于release和acquire的原则指导下，我们可以知道：
    当要确定volatile字段的值是否发生变化时，必须先确保非volatile的其他字段的值已经被更新；
                            
#####volatile字段不会进行线程的互斥处理
    
    volatile字段可以实现禁止重排序和实现多线程环境下的可见性
    但是他不能实现线程的互斥处理，即访问volatile字段的线程不会进入监视锁的等待队列
    
#####访问volatile字段会产生性能开销
    
    向volatile字段写入的值如果对线程B可见，那么之前写入的所有值都是可见的；
    因此向volatile字段读取和写入数据的性能开销就增大很多，可以认为volatile字段和synchronized的处理耗时几乎相同；
    
#####对long和double的原子操作
    
    Java规范没有指定long和double的原子操作，但是可以使用volatile实现
    
#####使用synchronized字段和volatile来保护多个线程之间共享的字段

###final关键字
    
####final字段于构建线程安全的对象

    使用final关键字声明的字段（final字段）只能被初始化一次，且只能在"字段声明时或是构造函数中"进行；
    final字段初始化结束后，任何时候，它的值对其他线程都是可见的；
        1，final字段初始化后的值对所有的线程都是可见的；
        2，在final字段可以追溯到的所以范围内都可以看到正确的值；
        
#####java.util.concurrent.ConcurrentHashMap使用final和volatile字段实现了无阻塞的Map。
    
####指南：不要从构造函数中泄漏this
        
    多个线程在创建共享对象时，那么在该对象的构造函数中暴露出this的引用时，可能导致线程不安全的问题，
    因为this所持有的当前对象中的字段还没有被初始化完成，进行的对象的初始化工作（特别是final字段的值）
    可能还没进行完成，就被其他线程通过这个静态字段访问正在创建的对象。同样地，向静态字段中保存的数组和集合
    中保存this也是非常危险的；
        
####Double-Checked-Locking模式的危险性
    
    
####InitializationOnDemandHolder模式
    
    利用静态内部类的方式来初始化来创建唯一的对象，
    基于Java规范中，类的初始化是线程安全的来创建唯一的对象；
    还使用了嵌套类的延迟初始化，避免内存浪费；    
    
        
    
    
    
    
    
    
    
    
    
    
    
    
    
       
    
    