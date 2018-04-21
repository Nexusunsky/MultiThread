##简介
    
###并发简史
    
    1，早期的操作系统不包含操作系统，从头到尾都只运行一个应用程序，而这个应用程序能够访问计算机中的所有资源。
    2，操作系统的出现使得计算机每次能够运行多个程序，不同的程序都在单独的进程中运行。
    3，操作系统为各个独立执行的进程分配各种资源，包括内存，文件句柄以及安全证书等；
    4，不同的进程之间通过一种粗粒度的通信机制来交换数据，包括：套接字，信号处理器，共享内存，信号量以及文件等；
    
###线程的优势
    
    1，发挥多处理器的强大能力
    2，建模的简单性（从线程的粒度控制程序的执行逻辑，调度机制的细节，交替执行的操作，异步I/O以及资源等待等问题）
    3，异步事件的简化处理
    4，响应更灵敏的用户界面
        
###线程的风险
    
    1，安全性问题，关注"永远不发生糟糕的事情"，多个线程的操作执行顺序是不可预测的；
    2，生存性问题，关注"某件正确的事情最终会发生"，不可预测的无限循环；
    3，性能问题，线程调度器在线程转换时所引起频繁地出现上下文切换；
    
###线程的广泛性
    
    1，框架代码中对线程的使用以及程序代码中对线程的使用，所引起的线程问题；
    2，远程方法调用（RMI）远程对象必须注意两个线程安全性问题：正确地协同多个对象中共享的状态，以及对线程对象本身状态的访问。