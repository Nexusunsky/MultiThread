##ThreadedPerMessage

    ThreadPerMessage角色分析：
    Client角色，请求服务对象；
    Host角色，响应请求服务对象；
    Handler角色，实际处理请求的对象；

     ThreadPerMessag模式分析：
     1，本模式可以提高与Client角色对应的Host角色的响应时间，降低延迟时间，
     但同时需要权衡：handle操作的耗时和线程启动的耗时；
     2，适用于单个操作的请求，就是操作之间没有时序的依赖；
     3，适用于无返回值的请求；
     4，应用于服务器模型；
     5，通知型函数调用；


##ThreadPerMessage模式拓展：

    1，常用--基于Thread，和Runnable类型实现的线程调用；

    2，ThreadFactory，线程创建抽象化了的接口，参数中Runnable指定了线程执行的操作内容；
    而将线程创建的细节从Host中分离处理单独实现；

    3，Executors类提供了很多静态方法来获取ThreadFactory，
    例如：Executors.defaultThreadFactory();

    4，Executor可以直接隐藏掉线程创建的操作，在Executor.execute()方法中将线程
    操作的内容指定好，而将操作的具体执行细节分离单独实现。

    5，ExecutorService接口将可以反复execute()的服务进行了抽象化，
    该类型指明的是一种"可复用线程型ExecutorService对象"，通过线程池的概念
    维护了后台线程，来持续的执行相关的线程操作，减少线程启动的开销并利用
    Before/After模式中提供了shotdown()方法，并在shoydown()方法执行后，
    新的请求将不会在执行；

    6，扩展ExecutorService类型的子类型还有一种ScheduledExecutorService，
    实现对线程执行的时间调度可以实现定时执行一些线程操作，
    schedule(Runnable r,long delay ,TimeUnit unit）

