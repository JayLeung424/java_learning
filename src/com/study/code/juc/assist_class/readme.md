# JUC 三大辅助类


## CountDownLatch: 减少计数
CountDownLatch 类可以设置一个计数器，然后通过countDown 方法来进行 减 1 的操作，使用 await 方法等待计数器不大于 0，然后继续执行 await 方法 之后的语句。

- CountDownLatch 主要有两个方法，当一个或多个线程调用 await 方法时，这 些线程会阻塞
- 其它线程调用 countDown 方法会将计数器减 1(调用 countDown 方法的线程 不会阻塞)
- 当计数器的值变为 0 时，因 await 方法阻塞的线程会被唤醒，继续执行
## CyclicBarrier: 循环栅栏
CyclicBarrier 看英文单词可以看出大概就是循环阻塞的意思，在使用中 CyclicBarrier 的构造方法第一个参数是目标障碍数，每次执行 CyclicBarrier 一 次障碍数会加一，如果达到了目标障碍数，才会执行 cyclicBarrier.await()之后 的语句。可以将 CyclicBarrier 理解为加 1 操作
## Semaphore: 信号灯
Semaphore 的构造方法中传入的第一个参数是最大信号量（可以看成最大线 程池），每个信号量初始化为一个最多只能分发一个许可证。使用 acquire 方 法获得许可证，release 方法释放许可