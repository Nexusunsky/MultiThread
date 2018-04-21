##Active Object模式
    
    所谓Active指的是"主动的"，即"有自己特有的线程"的对象模式；
    并且，ActiveObject 中的主动对象同时还具有从外部接收和处理异步消息并根据需要返回处理结构的特征。
    ActiveObject中的主动对象会通过自己特有的线程在合适的时机处理从外部接收到的异步消息。
    ActiveObject综合了 Producer-Consumer模式，Thread-PerMessage模式，Future模式等；
    
    
##Active Object角色分析
   
    ActiveObject(主动对象)
    定义了主动对象向Client角色提供的接口（API）
    
    Proxy（代理人）(Concurrent)
    实现 ActiveObject 接口，方法调用处于多线程并发环境之下
    1，直接和 Client 角色交互；
    2，将接口功能的调用转换为 MethodRequest 角色；
    3，传递给 Scheduler 角色来调度执行（这个传递的操作执行在Client）
    
    Scheduler
    1，负责将Proxy传递过来的MethodRequest角色传递给ActivationQueue，以及从中取出相应的MethodRequest来处理；
    2，负责实现线程调度的功能逻辑来响应MethodRequest；
    
    MethodRequest
    来自与Client角色的请求对应的角色；
    定义了负责实际执行完成功能的Servant（ActiveObject）角色；
    以及设置Future返回值和执行（execute）接口；
    
    Servant(sequential)
    负责实际处理请求，所有的方法只能执行在单线程中。
    调用Servant的是Scheduler角色，Scheduler从ActivationQueue角色取出一个MethodRequest对象并执行；
    Proxy角色会将请求转换为MethodRequest，而实际由Servant来实际执行该请求；
   
    ActivationQueue(主动队列)
    调用putRequest()是Client角色；
    而调用takeRequest()是Scheduler负责，而实现了Producer-Consumer模式；
    
    VirtualResult（虚拟结果）
    Client角色需要的异步结果： T : getResult（）
    
    FutureResult(期货结果)
    Client在获取处理结果时实际调用的角色；
    
    RealResult（真实结果）
    表示处理结果，由Servant角色创建，然后设置给FutureResult
    
##Active Object模式总结
    
      1，ActiveObject角色的特征：
            代码：
                a，定义了（API）
                b，接收异步消息：Proxy将方法调用转换为MethodRequest后保存在ActivationQueue中
                c，Client角色和Scheduler维护的线程属于不用线程；
                d，实际处理的执行为Servant运行在单线程环境中；
                e，需要返回值使用Future模式实现；
            功能：
                a，可以接受外部的异步请求
                b，能够自由调度请求
                c，单线程执行实际处理
                d，返回执行结果
                e，拥有独立线程
            
      2，并发性
            Proxy角色即使被多个线程调度也没有问题；
            Servatn角色只能执行在单线程环境中；
      
      3，从Producer-Consumer模式分析：
            Client对应Producer角色，生产MethodRequest
            Scheduler角色对应Consumer角色，消费MethodRequest
            ActivationQueue角色则是Channel角色，维护MethodRequest的队列
      
      4，扩展接口的核心就是在ActiveObject接口中新增Api