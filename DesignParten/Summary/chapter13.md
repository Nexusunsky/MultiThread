##多线程编程的模式语言总结
    
    模式：针对某个语境下反复出现的问题的解决方案；
    语境：问题的状况和背景，也称上下文（Context）；
    约束力：解决问题的条件，无法跨越的障碍；    
    模式语言：模式的集合，将相互关联，相互补充的各种模式集中在一起，然后通俗易懂地描述它们之间的关系；
    
        如果将模式看作针对一个问题的一种解决方案，那么模式语言就是针对某个领域中的问题集的解决方案集合；
    只要解决方案能很好地描述出技巧，心得，要领，提示等就是模式语言；
          
###SingleThreadedExecution模式
    
    语境：多个线程共享对象
    问题：如果每个线程随意的改变对象状态，对象会失去安全性
    解决方案：
        1，严格地规定对象的不稳定状态的范围（临界区）。
        2，施加保护，确保临界区只能被一个线程执行；
    实现：Java使用synchronized 关键字实现临界区。
    相关模式：
        1，当对象的状态不改变是就使用Immutable模式，可以提高吞吐量
        2，当可以分离对对象状态读，写操作的线程期望提高吞吐量时，使用Read-WriteLock模式
        
###Immutable模式

      语境：虽然多个线程共享对象，但是对象的状态并不会发生变化。
      问题：使用SingleThreadedExecution模式，吞吐量会下降。
      解决方案：
          认真考察对象的状态，将状态不会变化的对象变为不可变类；         
      实现：
          Java可以使用private来隐藏字段，使用final来确保字段无法改变；  
      相关模式：
          多个线程间执行互斥处理时，使用SingleThreaedExecution模式
          当改变对象状态的线程少于读取对象状态的线程时，可以使用Read_WriteLock模式
                
###GuardedSuspension模式        

     语境：多个线程共享对象时。
     问题：如果多个线程随意地访问对象，对象会失去安全性。
     解决方案：
        如果对象的状态不正确，就让线程等待对象恢复至正确的状态。
        1，用"守护条件"表示对象的"正确状态"
        2，在执行可能会导致对象失去安全性的处理之前，检查是否满足守护条件。
        3，如果不满足守护条件，则让线程等待，直至守护条件满足为止。
     实现：
        1，Java中使用while语句来检查守护条件，
        2，调用wait方法让线程等待。
        3，调用notify，notifyAll来发送守护条件变化的通知。
        4，检查和改变守护条件则可以使用SingleThreadExecution模式实现。
     相关模式： 
        GuardedSuspension模式的检查和改变条件的部分可以使用SingleThreadExecution模式
        
###Balking模式
     
     语境：多个线程共享对象时。
     问题：如果多个线程随意地访问对象，对象会失去安全性，但是如果要等待安全的时机，响应性会下降；
     解决方案：
        当对象状态不正确时就中断处理。
        1，用"守护条件"表示对象的"正确状态"。
        2，在执行可能导致对象失去安全性的处理之前，检查是否满足守护条件
        3，只有满足守护条件时才让程序继续执行，不满足守护条件则中端执行，立即返回。       
     实现：
        Java使用if语句来检查守护条件，可以使用return语句从方法中返回，或是通过throw语句抛出异常来进行中断。
        而检查和改变守护条件则可以使用SingleThreadExecution模式实现；
     相关模式：
        当要让线程等待至满足守护条件时，可以使用GuardedSuspension模式。
        Balking模式的检查和改变守护条件的部分可以使用SingleThreadExecution模式。
        
###Producer_Consumer模式
    
      语境：想从某个线程（Producer）向其他线程（Consumer）传递数据时；
      问题：
        如果Producer角色和Consumer角色的处理速度不一致，那么处理快的角色会被处理慢的角色拖后腿，
        从而导致吞吐量下降。另外，当Producer写的时候，Consumer读取数据，又会导致失去安全性；
      
      解决方案：
        1，在Producer角色和Consumer角色之间准备一个中转站--Channel角色。
        2，让Channel角色持有多个数据，同时在Channel中进行线程互斥，确保不失去线程数据的安全性。
      
      实现：
      相关模式：
        1，Channel角色安全传递数据的部分可以使用GuardedSuspension模式
        2，Future模式中使用返回值的时候可以使用Producer——Consumer模式
        3，WorkerThread模式传递请求的时候可以使用Producer-Consumer模式
        
###Read-WriteLock模式
      
       语境： 
            当多个线程共享对象时，且存在读取（Reader）对象状态的线程和改变（Writer）对象状态的线程时；
        
       问题：
            如果不进行线程的互斥处理将会失去安全性，但是使用SingleThreadedExecution模式，吞吐量会下降；
       
       解决方案：
            1，将"控制Reader的锁"与"控制Writer的锁"分开，引入一个提供两种锁的ReadWriteLock角色；
            2，涉及ReadWriteLock角色时，注意Reader角色之间即使发生冲突也不会有影响，因此无需进行互斥处理，
            但是Writer角色之间需要执行互斥的处理，确保同一时间只有一个Writer角色来操场共享对象的状态；
     
       实现：
            Java使用finally语句块来防止忘记释放锁。
            
       相关模式：     
            Read-Write Lock模式的ReadWriteLock角色实现互斥处理的部分可以使用GuardedSuspension模式。
            当Writer角色完全不存在时，可以使用Immutable模式；

###Thread-Per-Message模式
        
    语境：当Client角色要调用Host角色的方法时；
   
    问题：在方法的处理结束前，程序的控制权无法从Host角色中返回，如果方法的执行时间需要很长时间，响应性会下降。
        
    解决方案：
        1，在Host角色中启动一个新线程；
        2，将方法需要的实际的执行处理交给这个新启动的线程负责
        3，让Client角色不需要等待Host中方法的执行完成而可以继续向前执行，保证不改变Clinet角色的前提下，提高响应性；
            
    实现：
        Java可以使用线程池实现线程的复用；
        
    相关模式： 
        当想要节省线程启动所花费的时间时，可以使用WorkerThread模式。
        当想要将处理的结果返回给Client角色时，可以使用Future模式。
                                         
###Worker-Thread模式
        
     语境：当Client角色要调用Host角色的方法时；
    
     问题：
        1，如果方法的处理需要花费很长时间，响应性会下降。
        2，如果为了提高响应性而启动一个新的线程并让它负责处理，那么线程的启动会降低吞吐量。
        3，如果要发出许多请求时，那么过多的线程会增加资源的消耗。
        
     解决方案：
        1，启动执行处理的线程Worker
        2，将代表请求的对象传递给工人线程Worker，保证线程的复用。
    
     实现：
     相关模式：
        将代表请求的对象传递给工人线程时可以使用Producer——Conumer模式；                     
            
###Future模式
          
      语境：
        当一个线程Client向其他线程委托了处理，而Client也需要获取到处理的结果时。
      
      问题：如果委托处理时等待执行结果，响应性会下降。
     
      解决方案：
        1，设计一个与处理结果相同的接口（Api）Future角色
        2，在处理开始就返回Future对象，稍后在待处理结束后将真实的结果设置给Future对象中。
        3，Client角色就可以通过Future角色在自己觉得合适的时机获取（等待）处理的结果。
      实现：
      相关模式：                     
        在Client角色等待处理结果时使用GuardedSuspension模式
        当想在Thread-Per-Message模式中获取处理结果时可以使用Future模式
        当想在Worker-Thread模式中获取处理结果时可以使用Future模式

###Two-Phase-Termination模式

     语境：当想要终止正在运行的线程时。
     
     问题：如果因为外部的原因紧急终止了线程，就会失去安全性。
     
     解决方案：
        1，让即将被处理的线程自己去判断开始终止处理的时间点；
        2，提供给外界来向该线程发出"终止处理"的请求，该方法仅仅是设置"终止请求已经到来"这个闭锁。
        3，线程会在可以安全地开始终止检查之前检查该闭锁。
        4，如果"终止请求已经到来"则线程开始执行终止处理。
     
     实现：    
        1，Java不仅要设置"终止请求已经到来"，还要使用interrupt来中断wait，sleep，join等方法
        2，由于中断wait，sleep，join等方法时会抛出InterruptedException异常会清除中断状态，因此使用isInterrupted方法检查终止请求到来时需要格外注意。
        3，当想要实现即使发生异常也能进行终止处理时，可以使用finally语句块
     
     相关模式            
        当想要执行终止处理时禁止其他处理，可以使用Balking模式
        当想确保一定会执行终止处理时，使用Before／After模式。
        
###Thread-Specific-Storage模式
    
      语境：使得单线程环境设计的对象（TSObject）运行于多线程环境时。
      
      问题：
            TSObjetc对象的复用是困难的，让其运行于多线程环境，那么稍不注意便会失去安全性和生存性。
            甚至有时候根本就不能修改，TSObject对象的Api。
      
      解决方案：
           1，创建每个线程所特有的存储空间，让存储空间与线程一一对应并进行管理。
           2，编译一个与TSObject对象有相同接口的API的TSObjectProxy角色。
           3，为了管理Client角色和TSObject角色之间的对应表，我们需要编写一个TSObjtectCollection角色；
           4，TSObjectProxy角色使用TSObjtectCollection角色来获取与当前线程对应的TSObject对象，并将职责委托给TSObject角色。
           Client通过TSObjectProxy来交互；将多线程互斥的处理全部隐藏在TSObjectCollection角色内部。而一个TSObject对应一个线程；
           5，这样的处理会隐式引入上下文到程序当中，导致代码变得不够清晰；
            
      实现：
        Java使用java.lang.ThreadLocal类扮演了TSObjectCollcetion角色。
      
      相关模式：         
        当想要对多个线程进行互斥处理可以使用SingleThreadedExecution模式。
        
###ActiveObject模式
    
      语境：
        假设现在有处理请求的线程（Client）和包含了处理内容的对象（Servant），并且Servant只能工作在单线程环境中；
        
      问题：
        虽然多个线程都需要调用Servant来实现功能，但Servant无法实现线程安全，获取代价过高；
        我们需要即使在Servant的工作十分耗时，但是也不降低Client的响应性；
        处理的请求顺序和执行顺序并不一定相同。
        处理的结果需要返回给Client角色。
        
      解决方案：
        我们需要构建一个系统：
            可以接收异步消息，并且与Client工作在不同线程的主动对象系统；
        1，引入Scheduler角色的线程。由这个调度器来调度Servant工作在单线程中，设计WorkerThred模式。
        这样可以不修改Servatn来适应多线程的调用环境这么复杂的改动；
        2，将Client角色的请求实现为对Proxy角色的方法调用，而Proxy角色将每一个请求MethodRequest转换为一个对象，
        使用Producer-Consumer模式将其传递给Scheduler角色。保证了Client的响应性不受Servatn处理的耗时影响；
        3，Scheduler从Producer-Consumer模式中的Channel角色RequestQueue中获取到请求然后执行；
        4，使用Future模式将执行结果返回给Client角色。
        
      实现：
      相关模式：          
        实现Scheduler角色的部分使用WorkerThread模式；
        在将请求从Proxy角色传递给Scheduler角色的部分可以使用Producer-Consumer模式；
        在将执行结果返回给Client角色的部分可以使用Future模式；  