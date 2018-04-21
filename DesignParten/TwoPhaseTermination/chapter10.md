##Two-Phase-Termination模式
    
    Two-Phase-Termination模式指的是分两阶段中止，先执行完终止处理再终止线程的模式。
    模式要点：
        1，安全地终止线程（安全性）；
        2，必定会进行终止处理（生存性）；
        3，发出终止请求后尽快进行终止处理（响应性）；
    
    
##角色分析
    
    TerminationRequester负责向Terminator发出终止请求
    Terminator角色负责接收终止请求，并实际执行终止处理 
       
##捕获程序的整体的终止时

    1，未捕获的异常处理器
    Thread.setDefaultUncaughtExceptionHandler设置未捕获的异常处理器（UncaughtExceptionHandler）
    设置该处理器后程序将不会输出调用堆栈而是直接终止；
    
    2，退出钩子
    Shutdown Hook是在Java虚拟机退出时启动的线程。
    Java虚拟机退出：System.exit()调用；或者进程当中全部的非守护线程终止时；
    使用退出钩子便可以来编写程序安全终止时的终止处理。    
    
    
##优雅地终止线程

    Graceful。
    1，安全地终止（安全性）：不破坏对象的安全洗的位置，程序才开始终止处理；   
    2，必定会进行终止处理（生存性）：确保抛出异常后程序也会执行终止处理，Before／After模式；
    3，发出终止请求后尽快进入终止处理（响应性）：收到终止请求后，尽快进入终止处理，会终止sleep，wait等线程阻塞的状态；
    
##中断状态和InterruptedException异常之间的相互转换    
    
    1，实现"如果线程处于中断状态就抛出InterruptedException"，可以提高线程对中断的响应性；
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    Thread.interrupted 检查的是Thread.currentThread()的中断状态，
    但是这个静态方法会清除掉Thread.currentThread()的中断状态
    
    2，如果想在不清除中断状态的前提下检查当前线程的中断状态，
    可以使用Thread.currentThread().isInterrupted()
    
    3，记录线程的中断状态：不因为线程抛出InterruptedException而忽略该状态；
     try {
         Thread.sleep(3000);
     } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
     }
   
##java.util.concurrent工具
    
    1,java.util.concurrent.CountDownLatch
        Thread.join()方法可以实现让当前线程去等待线程对象"线程终止"这个一次性的操作，
        但是无法实现"等待指定次数的某种操作发生"
     java.util.concurrent.CountDownLatch实现了"等待指定次数的countDown方法被调用的功能"
     
      
    2,java.util.concurrent.CyclicBarrier可以周期性地创建出屏障。在屏障解除之前，
    碰到屏障的线程不能继续向下执行。而屏障的解除条件是达到屏障处的线程个数达到了构造函数指定的个数。
    当指定个数的线程达到屏障处后，屏障就会被解除，实现了各线程的同步；
    
    
    
    
    
    
    
    
    
    
           