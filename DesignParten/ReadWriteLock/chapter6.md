##Read-Write Lock

    ReadWrite模式的本质是将对象状态的的变化分开考虑，
    对象的状态在线程执行写入操作时才会发生变化，而在读操作时对象状态并不会变化；
    已知：线程在获取对象的锁时，会降低程序的性能，而如果将对公共数据的读和写操作，
    分开考虑，便可以减少由于获取锁而造成的性能开销；


   ReadWriteLock模式中的角色分析：

    Reader：对SharedResource执行read操作
    Writer：对SharedResource执行write操作
    SharedResource表示的是Reader和Writer角色共享的资源
    ReadWriteLock表示读写锁

   ReadWriteLock模式特点分析：

    ReadWriteLock模式利用了读取操作的线程之间不会冲突的特性。
    由于读取操作不会修改SharedResource角色的状态，那么多个Reader无须互斥处理。
    但是需要满足如下两个场景：
        1，适合读取操作繁重的操作
        2，适合读取频率比写入频率高时

    注意区分物理锁和逻辑锁的概念；

   Before/After模式结构：

     before();
     try{
        execute();
     } finally {
        after();
     }

    保证execute执行后一定会进行的操作；
    一般before和after是一对互为反向操作的操作对，
    因此，如果before操作无论是否出错都一定会执行after，那么就会出现问题，


   ReadWriteLock

    中preferWriting和waitingWriters字段的意义：
    当ReaderThread线程的个数多余WriterThread时就会出现writerThread执行效率的问题，
    因为ReadThread并未进行互斥处理，那么随着不断执行read操作就会导致writeThread
    更难执行，而preferWriting和waitingWriters两个字段就是为了保证writethread
    能够获得更高的执行机会，避免出现write无法执行的问题。

