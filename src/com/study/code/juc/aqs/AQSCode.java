// package com.study.code.juc.aqs;
//
// import sun.misc.Unsafe;
//
// import java.util.concurrent.locks.LockSupport;
// import java.util.concurrent.locks.ReentrantLock;
//
// /**
//  * @ClassName: AQSCode
//  * @Description:
//  * @Author: jay
//  * @Date: 2022/11/24 17:39
//  **/
// public class AQSCode {
//     private final ReentrantLock lock = new ReentrantLock(true);
//     private static final Unsafe unsafe = Unsafe.getUnsafe();
//     private static final long stateOffset = 0;
//
//
//
//     protected final boolean compareAndSetState(int expect, int update) {
//         // 判断当前线程是否是持有锁的线程 期望是0(未占用)，更新是1(占用)
//         return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
//     }
//
//     /**
//      * 争夺资源 尝试占用锁
//      * @param acquires
//      * @return
//      */
//     protected final boolean tryAcquire(int acquires) {
//         // 获取到当前线程
//         final Thread current = Thread.currentThread();
//         // 获取当前线程的state
//         int c = getState();
//         if (c == 0) {
//             // 如果当前线程的state是0，说明当前线程没有占用锁，可以尝试获取锁
//             if (!hasQueuedPredecessors() &&
//                     compareAndSetState(0, acquires)) {
//                 setExclusiveOwnerThread(current);
//                 return true;
//             }
//         }
//         // 如果当前线程已经占用了锁，判断当前线程是否是持有锁的线程
//         else if (current == getExclusiveOwnerThread()) {
//             // 如果是持有锁的线程，可以重入  (重入次数+1)
//             int nextc = c + acquires;
//             if (nextc < 0) // overflow
//                 throw new Error("Maximum lock count exceeded");
//             setState(nextc);
//             return true;
//         }
//         return false;
//     }
//
//
//     /**
//      * 用来判断线程需不需要排队，因为队列是FIFO的，所以需要判断队列中有没有相关线程的节点已经在排队了。
//      * 返回FALSE场景:
//      *      1.h != t返回false，那么短路与判断就会直接返回false  -- 当头节点和尾节点相等(都为null,或者指向同一个元素)时，才会返回false。
//      *      2.h != t 返回true，(s = h.next) == null 返回false以及s.thread != Thread.currentThread()返回false
//      *             h != t返回true表示队列中至少有两个不同节点存在。 (s = h.next) == null返回false表示头节点是有后继节点的。 s.thread != Thread.currentThread()返回fasle表示着当前线程和后继节点的线程是相同的，
//      *             那就说明已经轮到这个线程相关的节点去尝试获取同步状态了，自然无需排队，直接返回fasle。
//      * 返回True场景:
//      *      1.h != t返回true，(s = h.next) == null返回true   h != t返回true表示队列中至少有两个不同节点存在。
//      *          (s = h.next) == null返回true 说明头节点之后是没有后继节点的，这情况可能发生在如下情景：有另一个线程已经执行到初始化队列的操作了，介于compareAndSetHead(new Node())与tail = head之间
//      *      2.h != t返回true，(s = h.next) == null返回false，s.thread != Thread.currentThread()返回true。
//      *          h != t返回true表示队列中至少有两个不同节点存在。
//      *          (s = h.next) == null返回false表示首节点是有后继节点的。 s.thread != Thread.currentThread()返回true 表示后继节点的相关线程不是当前线程，所以首节点虽然有后继节点，但是后继节点相关的线程却不是当前线程，那当前线程自然得老老实实的去排队。
//      *
//      * @return  线程需要排队返回true，否则返回false
//      */
//     public final boolean hasQueuedPredecessors() {
//         // 读取头节点
//         Node t = tail;
//         // 读取尾节点
//         Node h = head;
//         // s是首节点h的后继节点
//         Node s;
//         return h != t && ((s = h.next) == null || s.thread != Thread.currentThread());
//     }
//
//
//
//     /**
//      * 加锁失败后 线程进入队列
//      * 注意：该入队方法的返回值就是新创建的节点
//      * @param mode  Node.EXCLUSIVE 独占模式
//      * @return
//      */
//     private Node addWaiter(Node mode) {
//         // 基于当前线程，节点类型（Node.EXCLUSIVE）创建新的节点
//         // 由于这里是独占模式，因此节点类型就是Node.EXCLUSIVE
//         Node node = new Node(Thread.currentThread(), mode);
//         // 这里为了提搞性能，首先执行一次快速入队操作，即直接尝试将新节点加入队尾
//         Node pred = tail;
//         // 尝试快速入队
//         if (pred != null) { // 队列已经初始化
//             node.prev = pred;
//             // 这里根据CAS的逻辑，即使并发操作也只能有一个线程成功并返回，其余的都要执行后面的入队操作。即enq()方法
//             if (compareAndSetTail(pred, node)) {
//                 pred.next = node;
//                 return node; // 快速入队成功后，就直接返回了
//             }
//         }
//         // 快速入队失败，也就是说队列都还每初始化，就使用enq
//         enq(node);
//         return node;
//     }
//
//
//     /**
//      * 执行入队 = 将节点插入队列，必要时进行初始化。
//      * 一旦线程进入到同步队列中去，就会严格按照FIFO的顺序获取锁，
//      * 前继节点没获取到锁（不包括取消节点），后面的节点不可能获取锁。
//      * @param node
//      * @return
//      */
//     private Node enq(final Node node) {
//         for (; ; ) {
//             Node t = tail;
//             // 如果队列为空，用一个空节点充当队列头
//             if (t == null) { // Must initialize
//                 // 同样是CAS，只有一个线程可以初始化头结点成功，其余的都要重复执行循环体
//                 if (compareAndSetHead(new Node())) // 哨兵节点 也是虚拟节点，目的是为了站位
//                     tail = head;// 尾部指针也指向队列头
//             } else {
//                 // 队列已经初始化，入队
//                 // 新创建的节点指向队列尾节点，毫无疑问并发情况下这里会有多个新创建的节点指向队列尾节点
//                 node.prev = t;
//                 // 基于这一步的CAS，不管前一步有多少新节点都指向了尾节点，这一步只有一个能真正入队成功，其他的都必须重新执行循环体
//                 if (compareAndSetTail(t, node)) {
//                     t.next = node;
//                     // 该循环体唯一退出的操作，就是入队成功（否则就要无限重试）
//                     return t;// 打断循环
//                 }
//             }
//         }
//     }
//
//     /**
//      * 线程进入队列 进入阻塞状态
//      * @param node 当前节点
//      * @param arg  1
//      * @return
//      */
//     final boolean acquireQueued(final Node node, int arg) {
//         // 1.锁资源获取失败标记
//         boolean failed = true;
//         try {
//             // 2.获取锁的过程中线程是否被中断的标记
//             boolean interrupted = false;
//             // 3.自旋获取锁
//             for (; ; ) {
//                 // 4.拿到node的上一个节点  predecessor = node.prev
//                 final Node p = node.predecessor();
//                 // 5.如果前继节点是头(head)节点，说明可以尝试获取资源。排队成功后，尝试获取锁
//                 if (p == head && tryAcquire(arg)) {
//                     // 6.获取锁成功，将当前节点设置为头节点，注意，这里已经获取锁了，不存在并发，所以不用CAS设置头节点。
//                     setHead(node);
//                     p.next = null; // help GC  设置为null 等待gc回收
//                     failed = false;
//                     return interrupted;
//                 }
//                 // 7.如果获取锁失败，就调用shouldParkAfterFailedAcquire()方法修改前继节点的WaitStatus，
//                 // 并判断前继节点的waitStatus是否为SIGNAL，如果是，就调用parkAndCheckInterrupt阻塞当前线程
//                 if (shouldParkAfterFailedAcquire(p, node)
//                         && parkAndCheckInterrupt())
//                     interrupted = true;
//             }
//         } finally {
//             // 8.如果退出了自旋for循环，说明发生了异常（包括中断和超时，当然这个方法不支持中断和超时），
//             // 最后会执行这里的逻辑，将取消当前节点，将当前节点的waitStatus设置为CANCELLED
//             if (failed) cancelAcquire(node);
//         }
//     }
//
//
//
//
//     /**
//      * 获取资源失败后，检测并更新等待状态
//      * @param pred  它的前置节点
//      * @param node  当前线程的节点
//      * @return
//      */
//     private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
//         // 获取前置节点的waitStatus
//         int ws = pred.waitStatus;
//         if (ws == Node.SIGNAL)
//             // 如果前置节点的waitStatus是Node.SIGNAL则返回true，
//             // 然后会执行parkAndCheckInterrupt()方法进行挂起
//             return true;
//         if (ws > 0) {
//             // 由waitStatus的几个取值可以判断这里表示前置节点被取消
//             do {
//                 // 如果前节点取消了，那就往前找到一个等待状态的接待你，并排在它的后面
//                 node.prev = pred = pred.prev;
//             } while (pred.waitStatus > 0);
//             // 这里我们由当前节点的前置节点开始，一直向前找最近的一个没有被取消的节点
//             // 注，由于头结点head是通过new Node()创建，它的waitStatus为0,因此这里不会出现空指针问题，也就是说最多就是找到头节点上面的循环就退出了
//             pred.next = node;
//         } else {
//             // 根据waitStatus的取值限定，这里waitStatus的值只能是0或者PROPAGATE，
//             // 那么我们把前置节点的waitStatus设为Node.SIGNAL然后重新进入该方法进行判断
//             compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
//         }
//         return false;
//     }
//
//
//     /**
//      * 阻塞当前线程，返回中断状态
//      * @return
//      */
//     private final boolean parkAndCheckInterrupt() {
//         // 阻塞当前线程
//         LockSupport.park(this);
//         // 被唤醒之后，返回中断标记，即如果是正常唤醒则返回false，如果是由于中断醒来，就返回true
//         return Thread.interrupted();
//     }
//
//     /**
//      * 锁的释放
//      * @param arg
//      * @return
//      */
//     public final boolean release(int arg) {
//         // tryRelease() 是 用户自定义的释放锁逻辑，如果成功，就判断等待队列中有没有需要被唤醒的节点
//         if (tryRelease(arg)) {
//             Node h = head;
//             // waitStatus为0表示没有需要被唤醒的节点
//             if (h != null && h.waitStatus != 0)
//                 unparkSuccessor(h);
//             return true;
//         }
//         return false;
//     }
//
//     /**
//      * 用户自定义的释放锁逻辑，如果成功，就判断等待队列中有没有需要被唤醒的节点
//      * @param releases
//      * @return
//      */
//     protected final boolean tryRelease(int releases) {
//         // 获取当前线程状态 1  - 传过来的1
//         int c = getState() - releases;
//         // 判断当前线程是否是持有锁的线程
//         if (Thread.currentThread() != getExclusiveOwnerThread())
//             throw new IllegalMonitorStateException();
//         boolean free = false;
//         if (c == 0) {
//             // 如果当前线程是持有锁的线程，那么就把持有锁的线程置为null
//             free = true;
//             setExclusiveOwnerThread(null);
//         }
//         // 设置当前线程的状态
//         setState(c);
//         return free;
//     }
//
//     /**
//      * 唤醒节点
//      * @param node
//      */
//     private void unparkSuccessor(Node node) {
//         int ws = node.waitStatus;
//         if (ws < 0)
//             // 把标记为设置为0，表示唤醒操作已经开始进行，提高并发环境下性能
//             compareAndSetWaitStatus(node, ws, 0);
//
//         Node s = node.next;
//         // 如果当前节点的后继节点为null，或者已经被取消
//         if (s == null || s.waitStatus > 0) {
//             s = null;
//             // 注意这个循环没有break，也就是说它是从后往前找，一直找到离当前节点最近的一个等待唤醒的节点
//             for (Node t = tail; t != null && t != node; t = t.prev)
//                 if (t.waitStatus <= 0)
//                     s = t;
//         }
//         // 执行唤醒操作
//         if (s != null)
//             LockSupport.unpark(s.thread);
//     }
//
// }
