##Future 模式
    
    Future指定的是期货，在未来交付的物品；那么需要等一段时间后获取，
    那么，与其一直等待结果,不如先拿到一张交货单，等待未来物品制作完成然后通知交付物品。
    
####Future模式中角色分析
    1，Clinet：向Host角色发出请求，并立即收到请求结果--VirtualData，此时对应于Future角色。
    
    2，Server：创建新的线程，并在新的线程中创建RealData角色。同时将Future角色返回给Client角色，
    新线程在创建了RealData角色后，会立即将RealData设置到Future中；
    
    3，VirtualData：保持Future和RealData的类型一直性；
    
    4，RealData：真实需要的数据，该对象的创建是耗时的；
    
    5，Future：是ReadlData的提货单，由Server角色传递给Client；
            
####Future模式的特点
    
    1，保留了Thread-Per-Message模式中"提高程序响应"的优点；
    
    2，异步方法调用的返回值，一般Java方法调用都是同步的，只有等待该方法执行完毕才能继续向前执行；
    Thread-Per-Message模式通过在方法中创建一个新的线程，模拟实现异步（Asynchronous）调用；好处是不阻塞调用方的方法执行；
    
    3，"准备返回值"和"使用返回值"的分离
    Future模式实现将"准备返回值"和"使用返回值"的分离，将伴随方法调用的一连串处理如同"慢动作"一样分解，
    把各个处理（启动，执行，准备返回值，使用返回值）规划到各个线程中去执行。
    
    4，变种：
        a，不阻塞主线程的Future角色
        b，会发生变化的Future角色
        
    5，多线程相关：
        a，IData接口中没有涉及多线程的相关操作；
        b，Server类中使用Thread-Per-Message模式启动了新的线程，涉及由多线程操作；
        c，FutureData中的setReadData()和getContent()方法均涉及多线程的相关处理；
        
    6，特殊的Immutable：
        Server中的request方法虽然不是synchronized方法，却是多线程安全的，
        由于Server没有被会被多个线程并发访问的字段（即不带有任何状态），即使多线程访问也是安全的；
        openCall模式是当Server带有状态时，仅仅守护状态变化的一种模式；    