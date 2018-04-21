##GuardedSuspensionMode

    GuardedSuspension模式：如果执行现在的处理会造成问题，就让执行处理的线程进行
    等待；通过让线程等待的方式来让保证对象的状态的安全性。

    GuardedSuspension模式角色：
    1，GuardedObject(被守护对象)，持有需要被守护的方法的对象，当线程执行
    guardedMethod时，条件满足则直接执行，否则线程阻塞等待条件满足后执行；
    2，GuardedObject(被守护对象)，还持有其他改变状态（特指改变守护条件）的方法
    (stateChangingMethod)
    3，guardedMethod方法通过while条件和wait()方法实现，stateChangingMethod
    则通过notify/notifyAll实现。

    思路扩展：
    1，GuardedSuspension模式是建立在SingleThreadedExecution模式的基础之上，
    基于条件的线程等待，类似于"附加条件的SingleThreadedExecution模式"
    2，GuardedSuspension模式一定是基于多线程的，因为在单线程条件下，如果只有一个
    线程改变GuardedObject的状态，就会一直阻塞线程，而无法满足条件，单线程模式下，
    仅需要if语句来判断条件是否满足，所以GuardedSuspension模式类似于多线程的if。
    3，wait和notify的目的是为了实现对守护条件的线程阻塞或者线程恢复，
    无论GuardedObject的状态被改变多少次。
    4，wait和notify的责任（可复用性），我们需要将对GuardedObject的状态变化
    以及守护条件的wait和notify操作封装起来，方便外界的复用。
    5，GuardedSuspension模式总结：
        特征：条件检查；条件检查循环；等待；唤醒；
        * GuardedObject中涉及有条件状态变化的方法要具备原子性；
    6，核心思想：确保在多线程环境下，确保GuardedObject只在自身状态正确时（守护条件）
    才完成制定操作，否则线程等待，直到状态正确时执行。