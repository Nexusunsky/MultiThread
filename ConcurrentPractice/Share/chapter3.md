##对象的共享和发布

    1，并发程序的关键在于：访问共享的可变状态时需要进行正确的管理；
    2，同步代码块和同步方法可以确保以原子的方式执行操作；
    3，synchronized关键字不仅能用于实现原子性或确定"临界区"，还能实现内存可见性；
    
###一，可见性

    在没有同步的情况下，编译器，处理器以及运行时等都可能对操作的执行顺序进行意想不到的调整。
    在缺乏足够同步的多线程程序中，要想对内存操作的执行顺序进行判断，几乎无法得出正确的结论。
    
    1，失效数据：
    缺乏同步的程序中可能产生错误结果的一种情况，其他线程对于数据访问操作的更新停留在缓冲中，不能直接同步到内存中
    让所有线程对该数据的最新情况保持可见性；
    
    2，非原子的64位操作：
    非volatile类性的64位数值变量（double，long）在Java规范中的问题。
    Java内存模型要求：
    变量的读取操作和写入操作都必须是原子操作，但对于非volatile类型的long和double变量，JVM允许将64位的读操作或写操作分解为两个32位操作。
    例如：
     当读取一个非volatile类型的long变量时，如果对该变量的读操作和写操作在不同的线程中执行，那么很可能会读取到某个指的高32位和另一个值的
     低32位。因此，即使不考虑失效数据问题，在多线程使用共享且可变的long和double等类型变量也是不安全的，除非使用关键字volatile来声明，
     或使用锁机制保护起来。
     
     3，加锁与可见性
     访问某个共享且可变的变量时要求所有线程在同一个锁上同步，就是为了确保某个线程写入该变量的值对于其他线程来说都是可见的。
     否则，持有非正确锁的线程读取到的某个变量就很可能是一个失效值；
     
####加锁的含义不仅仅局限于互斥行为，还包括内存可见性。为了确保所有线程都能看到共享变量的最新值，所有执行读操作或者写操作的线程都必须在同一个锁上同步。

    4，Volatile变量
       Volatile变量是一种削弱的，比synchronized关键字更轻量级的同步机制，即Volatile变量，用来确保将变量的更新操作通知到其他线程。
       编译器和运行时都会注意到这个变量是共享的；因此并不会将变量上的操作与其他内存操作一起重排序。     
     
       仅当volatile变量能简化代码的实现以及同步策略的验证时，才应该使用；
       volatile变量存在有局限性，volatile变量通常用作
            1，某个操作完成
            2，发生中断
            3，状态的标志
            
       volatile变量的正确使用方式包括：
            1，确保他们自身状态的可见性；
            2，确保它们所引用对象的状态的可见性；
            3，以及标志一些重要的程序生命周期事件的发生（初始化，终止）；
            
       volatile的使用场景：
            1，对变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值
            2，该变量不会与其他状态变量一起纳入不变性条件
            3，在访问变量时不需要加锁
   
###二，发布和逸出
    
    "发布"一个对象的意思是指，是对象能够在当前域之外的代码中使用。
     1，当发布某个对象时，可能会间接的发布其他对象；
     2，外部（Alien）方法指：行为并不由C来规定的方法，包括其他类中的方法，以及本类中可以被覆写的方法；
     3，发布一个类的内部类对象时，会找出类对象和类内部状态的逸出；
     
     安全的对象的构造过程
     当从对象的构造函数中发布对象时，只是发布了一个尚未构造完成的对象，
     如果this引用在构造过程中逸出，那么对象就被认为时不正确构造；
     
     
###三，线程封闭
    
    1，Ad-hoc线程封闭：指维护线程封闭性的职责完全由程序实现来承担，很脆弱。
    当决定要使用线程封闭技术时，通常是因为要将某个子系统实现为一个单线程子系统。
    而单线程子系统提供的简便性要要胜过Ad-hoc线程封闭技术。
        
    2，栈封闭：只有通过局部变量才能访问对象
    局部变量的固有属性之一：封闭在执行线程中，它们都是位于执行线程的栈中，其他线程无法访问这个栈。
    在线程内部（Within-Thread）上下文中，使用线程非安全的对象时，那么该对象仍然是线程安全的；
    
    3，ThreadLocal
    ThreadLocal是更规范的用法，它使线程中的域变量与保存值的对象关联起来。
    1，ThreadLoacl对象通常是用于防止对可变单例对象或全局变量进行共享。
    2，当某个频繁执行的操作需要临时对象（例如一个缓冲区），而同时希望避免每次执行时都重新分配该临时对象，适用。
    
    
###四，不变性
    
    不可变对象一定是线程安全的；
    
    当满足一下条件，对象才是不可变的：
        1，对象创建以后其状态就不能修改
        2，对象所有域都是final类型
        3，对象是正确创建的（在对象的创建期间，this引用没有逸出）
    
    1，Final域
    
     1，final类型的域是不能修改的（但如果final域所引用的对象是可变的，那么这些被引用的对象是可以修改的）；
     2，Java内存模型中，final域还有特殊的语义，fianl域能够确保初始化过程的安全性，从而可以不受限制的访问不可变对象；
     3，正如"除非需要更高的可见性，否则应将所有的域都声明为私有域"是一个良好的编程习惯一样，
     "除非需要某个域是可变的，否则将其声明为final域"也是一个良好的习惯。
     
     4，对于在访问和更性多个相关变量时出现的竞争问题，可以通过将这些变量全部保存在一个不可变对象中来消除；
     通过使用包含多个状态变量的容器对象来维持不变性条件，并使用一个volatile类型的引用来确保可见性；
     
###五，安全发布

    1，不正确的发布，正确的对象被破坏
       没有使用同步来确保对象对其他线程可见，因此将对象称为"未被正确发布"
                    
    2，不可变对象与初始化安全性
       1，由于不可变对象是一种非常重要的对象，因此Java内存模型为不可变对象的共享提供一种初始化安全性保证；
       2，即使某个对象的引用对其他线程是可见的，也并不意味着对象状态对于使用该对象的线程来说一定是可见的；
       3，为了维持初试安全性的保证，必须满足所有需求：状态不可修改，所有域都是final类型，以及正确的构造过程；
       
####任何线程都可以在不需要额外同步的情况下安全地访问不可变对象，即使在发布这些对象是没有使用同步

    
    3，安全发布的常用模式；        
    可变对象必须通过安全的方式来发布：发布和使用该对象的线程时都必须使用同步。
    
    确保使用对象的线程能够看到该对象处于已发布的状态：
        安全地发布一个对象，对象的引用以及对象的状态必须同时对其他线程可见。
        正确构造的对象可以使用如下方式发布：
        1，静态初始化函数中初始化一个对象引用；
        2，将对象的引用保存到volatile类型的域或者AtmoicReferance对象中；
        3，将对现象的引用保存到某个正确构造对象的final类型域中；
        4，将对象的引用保存到一个由锁保存的域中。
        5，静态初始化器由JVM在类的初始化解读哪执行，这是JVM内部实现的同步机制，累的加载过程都是线程安全的；
        因此通过这种方式初始化的任何对象都可以被安全地发布
        
    4，事实不可变对象
        如果对象发布后不会被修改，
        那么所有的安全发布机制都能够确保：当对象的引用对所有访问该对象的线程可见时，对象发布时的状态对于所有线程也将是可见的；    
        
####在没有额外的同步的情况下，任何线程都可以安全地使用被安全发布的事实不可变对象


    5，可变对象
       对象在构造后可以修改，那么安全发布只能确保"安全发布当时"状态的可见性。
       要安全地共享可变对象，这些对象就必须被安全地发布，并且必须是线程安全的或者某个锁保护起来。
       
       对象发布需求取决于它的可变形：
            1，不可变对象可以通过任何机制发布
            2，事实不可变对象必须通过安全方式发布
            3，可变对象必须通过安全方式来发布，并且必须是线程安全或是某个锁保护起来
       
    6，安全地共享对象
        并发编程中使用共享对象时，实用的策略：
            1，线程封闭：线程封闭的对象只能由一个线程拥有，对象被封闭在该线程中，并且只能有这个线程修改
            2，只读共享：没有额外同步的情况下，共享的只读对象可以有多个线程并发访问，
            但任何时候线程都不能修改，共享的只读对象包括不可变对象和事实不可变对象；
            3，线程安全共享，线程安全的对象只在其内部实现同步，
            因此多个线程共享可以通过对象的共有接口访问来访问而不需要进一步的同步；
            4，保护对象，被保护的对象只能通过持有特定的锁来访问，
            保护对象包括封装在其他线程安全对象中的对象，以及已发布的并且由某个特定锁保护的对象；   
    
    
    
         
     
     
     
     
     
     
     
             
     
            