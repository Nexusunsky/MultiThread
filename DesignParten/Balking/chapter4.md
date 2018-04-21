##Balking（停止并返回）

    在调用时不适合执行操作，或者没必要执行这个操作，就停止处理，直接返回；


 Balking模式同样拥有GuardedObject，以及相应需要守护的状态；

 拓展思路：

    使用Balking的场景
    1，并不需要执行时（条件型执行）
    2，不需要等待守护条件成立时
    3，守护条件仅在第一次成立时

Balk结果的表示：

    1，忽略balk，无返回值的调用；
    2，通过返回值来表示Balk；
    3，异常表示Balk；


Synchonized 中没有超时，也不能中断；

    1，想要使用synchronized获取锁但处于阻塞状态
       在这种状态下的线程执行interrupt，也不会抛出InterruptedException
       线程必须在获取锁并进入synchronized之后，调用了关注中断状态的方法；
       如wait，sleep，join等，或者使用isInterrupted方法或interrupted方法
       检查中断状态；

    2，执行wait并进入等待队列
