###WorkerThread模式

   ##角色分析：

        1，Client创建表示工作内容的Request角色，并将其传递给Channel角色，
        2，Channel接收来自Client角色的Request，并将其传给Worker。
        3，Worker中Chanel取出Request，并调用Request中的工作，
        持续的进行Channel中的Request。
        4，Request表示工作，保存了进行工作所必须的信息。


   ##模式分析

        1，提高吞吐量：将方法的调用和执行相分离，让不同的线程执行不同的部分，
        提高线程的响应速度，但同时需要注意创建和启动线程的开销；
        2，容量控制：控制WorkerThread的数量，匹配合适的工作开销，并且Worker的数量
        不一定要在创建时指定，可以动态的改变。
            场景：
                1，设定初始Worker数量；
                2，当工作增加时就增加Worker角色；
                3，但是，工作增加的太多会导致内存开销过大，因此达到极限值就不再
                增加Worker角色，需要逐渐减少Worker角色；
        3，调用与执行的分离：
        Client 角色负责发送Request请求，将工作内容封装到Request角色当中，
        然后传递给Channel角色。（可以视为方法的调用）；
        Worker角色负责进行工作，从Channel中取出Request对象，
        来执行实际的处理。（可视为方法的实际执行）；
        而ThreadPerMessage模式和WorkerThread模式中，方法的调用和方法的执行
        是特意被分离的，

   ##事件分发线程

        1，什么是事件分发线程：
        在进行相应的操作时，就会产生事件（Request）的对象，然后将这些事件对象保存
        到事件队列(Channel)中，二事件分发线程就是Worker线程，将这些事件从Channel
        中取出来，进行相应的处理。
        2，事件分发线程只有一个：
        事件分发线程在系统中是唯一的，这样我们不需要在事件分发线程要执行的方法中，
        实现工人线程间的互斥处理。

