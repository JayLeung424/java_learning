// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.locks.Condition;
// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.LockSupport;
//
// public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {
//     private static final long serialVersionUID = 7373984972572414691L;
//
//     /**
//      * Creates a new {@code AbstractQueuedSynchronizer} instance
//      * with initial synchronization state of zero.
//      */
//     protected AbstractQueuedSynchronizer() {
//     }
//
//     /**
//      * Wait queue node class.
//      * <p>
//      * 不管是条件队列，还是CLH等待队列
//      * 都是基于Node类
//      * <p>
//      * AQS当中的同步等待队列也称CLH队列，CLH队列是Craig、Landin、Hagersten三人
//      * 发明的一种基于双向链表数据结构的队列，是FIFO先入先出线程等待队列，Java中的
//      * CLH队列是原CLH队列的一个变种,线程由原自旋机制改为阻塞机制。
//      */
//     static final class Node {
//         /**
//          * 标记节点未共享模式
//          */
//         static final Node SHARED = new Node();
//         /**
//          * 标记节点为独占模式
//          */
//         static final Node EXCLUSIVE = null;
//
//         /**
//          * 在同步队列中等待的线程等待超时或者被中断，需要从同步队列中取消等待
//          */
//         static final int CANCELLED = 1;
//         /**
//          * 后继节点的线程处于等待状态，而当前的节点如果释放了同步状态或者被取消，
//          * 将会通知后继节点，使后继节点的线程得以运行。
//          */
//         static final int SIGNAL = -1;
//         /**
//          * 节点在等待队列中，节点的线程等待在Condition上，当其他线程对Condition调用了signal()方法后，
//          * 该节点会从等待队列中转移到同步队列中，加入到同步状态的获取中
//          */
//         static final int CONDITION = -2;
//         /**
//          * 表示下一次共享式同步状态获取将会被无条件地传播下去
//          */
//         static final int PROPAGATE = -3;
//
//         /**
//          * 标记当前节点的信号量状态 (1,0,-1,-2,-3)5种状态
//          * 使用CAS更改状态，volatile保证线程可见性，高并发场景下，
//          * 即被一个线程修改后，状态会立马让其他线程可见。
//          */
//         volatile int waitStatus;
//
//         /**
//          * 前驱节点，当前节点加入到同步队列中被设置
//          */
//         volatile Node prev;
//
//         /**
//          * 后继节点
//          */
//         volatile Node next;
//
//         /**
//          * 节点同步状态的线程
//          */
//         volatile Thread thread;
//
//         /**
//          * 等待队列中的后继节点，如果当前节点是共享的，那么这个字段是一个SHARED常量，
//          * 也就是说节点类型(独占和共享)和等待队列中的后继节点共用同一个字段。
//          */
//         Node nextWaiter;
//
//         /**
//          * Returns true if node is waiting in shared mode.
//          */
//         final boolean isShared() {
//             return nextWaiter == SHARED;
//         }
//
//         /**
//          * 返回前驱节点
//          */
//         final Node predecessor() throws NullPointerException {
//             Node p = prev;
//             if (p == null) throw new NullPointerException();
//             else return p;
//         }
//
//         // 空节点，用于标记共享模式
//         Node() {    // Used to establish initial head or SHARED marker
//         }
//
//         // 用于同步队列CLH
//         Node(Thread thread, Node mode) {     // Used by addWaiter
//             this.nextWaiter = mode;
//             this.thread = thread;
//         }
//
//         // 用于条件队列
//         Node(Thread thread, int waitStatus) { // Used by Condition
//             this.waitStatus = waitStatus;
//             this.thread = thread;
//         }
//     }
//
//     /**
//      * 指向同步等待队列的头节点
//      */
//     private transient volatile Node head;
//
//     /**
//      * 指向同步等待队列的尾节点
//      */
//     private transient volatile Node tail;
//
//     /**
//      * 同步资源状态
//      */
//     private volatile int state;
//
//     /**
//      * @return current state value
//      */
//     protected final int getState() {
//         return state;
//     }
//
//     protected final void setState(int newState) {
//         state = newState;
//     }
//
//     /**
//      * Atomically sets synchronization state to the given updated
//      * value if the current state value equals the expected value.
//      * This operation has memory semantics of a {@code volatile} read
//      * and write.
//      *
//      * @param expect the expected value
//      * @param update the new value
//      * @return {@code true} if successful. False return indicates that the actual
//      * value was not equal to the expected value.
//      */
//     protected final boolean compareAndSetState(int expect, int update) {
//         // See below for intrinsics setup to support this
//         return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
//     }
//
//     // Queuing utilities
//
//     /**
//      * The number of nanoseconds for which it is faster to spin
//      * rather than to use timed park. A rough estimate suffices
//      * to improve responsiveness with very short timeouts.
//      */
//     static final long spinForTimeoutThreshold = 1000L;
//
//     /**
//      * 执行入队 = 将节点插入CLH同步队列，必要时进行初始化。
//      * 一旦线程进入到同步队列中去，就会严格按照FIFO的顺序获取锁，
//      * 前继节点没获取到锁（不包括取消节点），后面的节点不可能获取锁。
//      *
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
//      * 加锁失败后 线程进入队列
//      * 注意：该入队方法的返回值就是新创建的节点
//      *
//      * @param mode Node.EXCLUSIVE 独占模式
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
//     /**
//      * Sets head of queue to be node, thus dequeuing. Called only by
//      * acquire methods.  Also nulls out unused fields for sake of GC
//      * and to suppress unnecessary signals and traversals.
//      *
//      * @param node the node
//      */
//     private void setHead(Node node) {
//         head = node;
//         node.thread = null;
//         node.prev = null;
//     }
//
//     /**
//      * 唤醒节点
//      *
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
//                 if (t.waitStatus <= 0) s = t;
//         }
//         // 执行唤醒操作
//         if (s != null) LockSupport.unpark(s.thread);
//     }
//
//     /**
//      * 把当前结点设置为SIGNAL或者PROPAGATE
//      * 唤醒head.next(B节点)，B节点唤醒后可以竞争锁，成功后head->B，然后又会唤醒B.next，一直重复直到共享节点都唤醒
//      * head节点状态为SIGNAL，重置head.waitStatus->0，唤醒head节点线程，唤醒后线程去竞争共享锁
//      * head节点状态为0，将head.waitStatus->Node.PROPAGATE传播状态，表示需要将状态向后继节点传播
//      */
//     private void doReleaseShared() {
//         for (; ; ) {
//             Node h = head;
//             if (h != null && h != tail) {
//                 int ws = h.waitStatus;
//                 if (ws == Node.SIGNAL) {// head是SIGNAL状态
//                     /* head状态是SIGNAL，重置head节点waitStatus为0，E这里不直接设为Node.PROPAGAT,
//                      * 是因为unparkSuccessor(h)中，如果ws < 0会设置为0，所以ws先设置为0，再设置为PROPAGATE
//                      * 这里需要控制并发，因为入口有setHeadAndPropagate跟release两个，避免两次unpark
//                      */
//                     if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0)) continue; // 设置失败，重新循环
//                     /* head状态为SIGNAL，且成功设置为0之后,唤醒head.next节点线程
//                      * 此时head、head.next的线程都唤醒了，head.next会去竞争锁，成功后head会指向获取锁的节点，
//                      * 也就是head发生了变化。看最底下一行代码可知，head发生变化后会重新循环，继续唤醒head的下一个节点
//                      */
//                     unparkSuccessor(h);
//                     /*
//                      * 如果本身头节点的waitStatus是出于重置状态（waitStatus==0）的，将其设置为“传播”状态。
//                      * 意味着需要将状态向后一个节点传播
//                      */
//                 } else if (ws == 0 && !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
//                     continue;                // loop on failed CAS
//             }
//             if (h == head) // 如果head变了，重新循环
//                 break;
//         }
//     }
//
//     /**
//      * 把node节点设置成head节点，且Node.waitStatus->Node.PROPAGATE
//      */
//     private void setHeadAndPropagate(Node node, int propagate) {
//         Node h = head; // h用来保存旧的head节点
//         setHead(node);// head引用指向node节点
//         /* 这里意思有两种情况是需要执行唤醒操作
//          * 1.propagate > 0 表示调用方指明了后继节点需要被唤醒
//          * 2.头节点后面的节点需要被唤醒（waitStatus<0），不论是老的头结点还是新的头结点
//          */
//         if (propagate > 0 || h == null || h.waitStatus < 0 || (h = head) == null || h.waitStatus < 0) {
//             Node s = node.next;
//             if (s == null || s.isShared())// node是最后一个节点或者 node的后继节点是共享节点
//                 /* 如果head节点状态为SIGNAL，唤醒head节点线程，重置head.waitStatus->0
//                  * head节点状态为0(第一次添加时是0)，设置head.waitStatus->Node.PROPAGATE表示状态需要向后继节点传播
//                  */ doReleaseShared();
//         }
//     }
//
//     // Utilities for various versions of acquire
//
//     /**
//      * 终结掉正在尝试去获取锁的节点
//      *
//      * @param node the node
//      */
//     private void cancelAcquire(Node node) {
//         // Ignore if node doesn't exist
//         if (node == null) return;
//
//         node.thread = null;
//
//         // 剔除掉一件被cancel掉的节点
//         Node pred = node.prev;
//         while (pred.waitStatus > 0) node.prev = pred = pred.prev;
//
//         // predNext is the apparent node to unsplice. CASes below will
//         // fail if not, in which case, we lost race vs another cancel
//         // or signal, so no further action is necessary.
//         Node predNext = pred.next;
//
//         // Can use unconditional write instead of CAS here.
//         // After this atomic step, other Nodes can skip past us.
//         // Before, we are free of interference from other threads.
//         node.waitStatus = Node.CANCELLED;
//
//         // If we are the tail, remove ourselves.
//         if (node == tail && compareAndSetTail(node, pred)) {
//             compareAndSetNext(pred, predNext, null);
//         } else {
//             // If successor needs signal, try to set pred's next-link
//             // so it will get one. Otherwise wake it up to propagate.
//             int ws;
//             if (pred != head && ((ws = pred.waitStatus) == Node.SIGNAL || (ws <= 0 && compareAndSetWaitStatus(pred, ws, Node.SIGNAL))) && pred.thread != null) {
//                 Node next = node.next;
//                 if (next != null && next.waitStatus <= 0) compareAndSetNext(pred, predNext, next);
//             } else {
//                 unparkSuccessor(node);
//             }
//
//             node.next = node; // help GC
//         }
//     }
//
//     /**
//      * 获取资源失败后，检测并更新等待状态
//      *
//      * @param pred 它的前置节点
//      * @param node 当前线程的节点
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
//             // do while 的目的为了找到一个有效(等待状态)的前置节点
//             do {
//                 // 如果前一节点取消了，那就继续往前找到一个等待状态的节点，并排在它的后面
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
//     /**
//      * 中断当前线程
//      */
//     static void selfInterrupt() {
//         Thread.currentThread().interrupt();
//     }
//
//     /**
//      * 阻塞当前节点，返回当前Thread的中断状态
//      * LockSupport.park 底层实现逻辑调用系统内核功能 pthread_mutex_lock 阻塞线程
//      */
//     private final boolean parkAndCheckInterrupt() {
//         // 阻塞当前线程
//         LockSupport.park(this);
//         // 被唤醒之后，返回中断标记，即如果是正常唤醒则返回false，如果是由于中断醒来，就返回true
//         return Thread.interrupted();
//     }
//
//     /**
//      * 线程进入队列 进入阻塞状态
//      *
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
//                 if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) interrupted = true;
//             }
//         } finally {
//             // 8.如果退出了自旋for循环，说明发生了异常（包括中断和超时，当然这个方法不支持中断和超时），
//             // 最后会执行这里的逻辑，将取消当前节点，将当前节点的waitStatus设置为CANCELLED
//             if (failed) cancelAcquire(node);
//         }
//     }
//
//     /**
//      * 与acquireQueued逻辑相似，唯一区别节点还不在队列当中需要先进行入队操作
//      */
//     private void doAcquireInterruptibly(int arg) throws InterruptedException {
//         final Node node = addWaiter(Node.EXCLUSIVE);// 以独占模式放入队列尾部
//         boolean failed = true;
//         try {
//             for (; ; ) {
//                 final Node p = node.predecessor();
//                 if (p == head && tryAcquire(arg)) {
//                     setHead(node);
//                     p.next = null; // help GC
//                     failed = false;
//                     return;
//                 }
//                 if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) throw new InterruptedException();
//             }
//         } finally {
//             if (failed) cancelAcquire(node);
//         }
//     }
//
//     /**
//      * 独占模式定时获取
//      */
//     private boolean doAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
//         if (nanosTimeout <= 0L) return false;
//         final long deadline = System.nanoTime() + nanosTimeout;
//         final Node node = addWaiter(Node.EXCLUSIVE);// 加入队列
//         boolean failed = true;
//         try {
//             for (; ; ) {
//                 final Node p = node.predecessor();
//                 if (p == head && tryAcquire(arg)) {
//                     setHead(node);
//                     p.next = null; // help GC
//                     failed = false;
//                     return true;
//                 }
//                 nanosTimeout = deadline - System.nanoTime();
//                 if (nanosTimeout <= 0L) return false;// 超时直接返回获取失败
//                 if (shouldParkAfterFailedAcquire(p, node) && nanosTimeout > spinForTimeoutThreshold)
//                     // 阻塞指定时长，超时则线程自动被唤醒
//                     LockSupport.parkNanos(this, nanosTimeout);
//                 if (Thread.interrupted())// 当前线程中断状态
//                     throw new InterruptedException();
//             }
//         } finally {
//             if (failed) cancelAcquire(node);
//         }
//     }
//
//     /**
//      * 尝试获取共享锁
//      */
//     private void doAcquireShared(int arg) {
//         final Node node = addWaiter(Node.SHARED);// 入队
//         boolean failed = true;
//         try {
//             boolean interrupted = false;
//             for (; ; ) {
//                 final Node p = node.predecessor();// 前驱节点
//                 if (p == head) {
//                     int r = tryAcquireShared(arg); // 非公平锁实现，再尝试获取锁
//                     // state==0时tryAcquireShared会返回>=0(CountDownLatch中返回的是1)。
//                     // state为0说明共享次数已经到了，可以获取锁了
//                     if (r >= 0) {// r>0表示state==0,前继节点已经释放锁，锁的状态为可被获取
//                         // 这一步设置node为head节点设置node.waitStatus->Node.PROPAGATE，然后唤醒node.thread
//                         setHeadAndPropagate(node, r);
//                         p.next = null; // help GC
//                         if (interrupted) selfInterrupt();
//                         failed = false;
//                         return;
//                     }
//                 }
//                 // 前继节点非head节点，将前继节点状态设置为SIGNAL，通过park挂起node节点的线程
//                 if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) interrupted = true;
//             }
//         } finally {
//             if (failed) cancelAcquire(node);
//         }
//     }
//
//     /**
//      * Acquires in shared interruptible mode.
//      *
//      * @param arg the acquire argument
//      */
//     private void doAcquireSharedInterruptibly(int arg) throws InterruptedException {
//         final Node node = addWaiter(Node.SHARED);
//         boolean failed = true;
//         try {
//             for (; ; ) {
//                 final Node p = node.predecessor();
//                 if (p == head) {
//                     int r = tryAcquireShared(arg);
//                     if (r >= 0) {
//                         setHeadAndPropagate(node, r);
//                         p.next = null; // help GC
//                         failed = false;
//                         return;
//                     }
//                 }
//                 if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) throw new InterruptedException();
//             }
//         } finally {
//             if (failed) cancelAcquire(node);
//         }
//     }
//
//     /**
//      * Acquires in shared timed mode.
//      *
//      * @param arg          the acquire argument
//      * @param nanosTimeout max wait time
//      * @return {@code true} if acquired
//      */
//     private boolean doAcquireSharedNanos(int arg, long nanosTimeout) throws InterruptedException {
//         if (nanosTimeout <= 0L) return false;
//         final long deadline = System.nanoTime() + nanosTimeout;
//         final Node node = addWaiter(Node.SHARED);
//         boolean failed = true;
//         try {
//             for (; ; ) {
//                 final Node p = node.predecessor();
//                 if (p == head) {
//                     int r = tryAcquireShared(arg);
//                     if (r >= 0) {
//                         setHeadAndPropagate(node, r);
//                         p.next = null; // help GC
//                         failed = false;
//                         return true;
//                     }
//                 }
//                 nanosTimeout = deadline - System.nanoTime();
//                 if (nanosTimeout <= 0L) return false;
//                 if (shouldParkAfterFailedAcquire(p, node) && nanosTimeout > spinForTimeoutThreshold)
//                     LockSupport.parkNanos(this, nanosTimeout);
//                 if (Thread.interrupted()) throw new InterruptedException();
//             }
//         } finally {
//             if (failed) cancelAcquire(node);
//         }
//     }
//
//     // Main exported methods
//
//     /**
//      * 尝试获取独占锁，可指定锁的获取数量
//      */
//     protected boolean tryAcquire(int arg) {
//         throw new UnsupportedOperationException();
//     }
//
//     /**
//      * 尝试释放独占锁，在子类当中实现
//      */
//     protected boolean tryRelease(int arg) {
//         throw new UnsupportedOperationException();
//     }
//
//     /**
//      * 共享式：共享式地获取同步状态。对于独占式同步组件来讲，同一时刻只有一个线程能获取到同步状态，
//      * 其他线程都得去排队等待，其待重写的尝试获取同步状态的方法tryAcquire返回值为boolean，这很容易理解；
//      * 对于共享式同步组件来讲，同一时刻可以有多个线程同时获取到同步状态，这也是“共享”的意义所在。
//      * 本方法待被之类覆盖实现具体逻辑
//      * 1.当返回值大于0时，表示获取同步状态成功，同时还有剩余同步状态可供其他线程获取；
//      * <p>
//      * 　2.当返回值等于0时，表示获取同步状态成功，但没有可用同步状态了；
//      * <p>
//      * 　3.当返回值小于0时，表示获取同步状态失败。
//      */
//     protected int tryAcquireShared(int arg) {
//         throw new UnsupportedOperationException();
//     }
//
//     /**
//      * 释放共享锁，具体实现在子类当中实现
//      */
//     protected boolean tryReleaseShared(int arg) {
//         throw new UnsupportedOperationException();
//     }
//
//     /**
//      * 当前线程是否持有独占锁
//      */
//     protected boolean isHeldExclusively() {
//         throw new UnsupportedOperationException();
//     }
//
//     /**
//      * 获取独占锁
//      */
//     public final void acquire(int arg) {
//         // 尝试获取锁
//         if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))// 独占模式
//             selfInterrupt();
//     }
//
//     /**
//      *
//      */
//     public final void acquireInterruptibly(int arg) throws InterruptedException {
//         if (Thread.interrupted()) throw new InterruptedException();
//         if (!tryAcquire(arg)) doAcquireInterruptibly(arg);
//     }
//
//     /**
//      * 获取独占锁，设置最大等待时间
//      */
//     public final boolean tryAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
//         if (Thread.interrupted()) throw new InterruptedException();
//         return tryAcquire(arg) || doAcquireNanos(arg, nanosTimeout);
//     }
//
//     /**
//      * 释放独占模式持有的锁
//      */
//     public final boolean release(int arg) {
//         // tryRelease() 是 用户自定义的释放锁逻辑，如果成功，就判断等待队列中有没有需要被唤醒的节点
//         if (tryRelease(arg)) {
//             Node h = head;
//             // waitStatus为0表示没有需要被唤醒的节点
//             if (h != null && h.waitStatus != 0) unparkSuccessor(h);
//             return true;
//         }
//         return false;
//     }
//
//     /**
//      * 请求获取共享锁
//      */
//     public final void acquireShared(int arg) {
//         if (tryAcquireShared(arg) < 0)// 返回值小于0，获取同步状态失败，排队去；获取同步状态成功，直接返回去干自己的事儿。
//             doAcquireShared(arg);
//     }
//
//
//     /**
//      * Releases in shared mode.  Implemented by unblocking one or more
//      * threads if {@link #tryReleaseShared} returns true.
//      *
//      * @param arg the release argument.  This value is conveyed to
//      *            {@link #tryReleaseShared} but is otherwise uninterpreted
//      *            and can represent anything you like.
//      * @return the value returned from {@link #tryReleaseShared}
//      */
//     public final boolean releaseShared(int arg) {
//         if (tryReleaseShared(arg)) {
//             doReleaseShared();
//             return true;
//         }
//         return false;
//     }
//
//     // Queue inspection methods
//
//     public final boolean hasQueuedThreads() {
//         return head != tail;
//     }
//
//     public final boolean hasContended() {
//         return head != null;
//     }
//
//     public final Thread getFirstQueuedThread() {
//         // handle only fast path, else relay
//         return (head == tail) ? null : fullGetFirstQueuedThread();
//     }
//
//     /**
//      * Version of getFirstQueuedThread called when fastpath fails
//      */
//     private Thread fullGetFirstQueuedThread() {
//         Node h, s;
//         Thread st;
//         if (((h = head) != null && (s = h.next) != null && s.prev == head && (st = s.thread) != null) || ((h = head) != null && (s = h.next) != null && s.prev == head && (st = s.thread) != null))
//             return st;
//
//         Node t = tail;
//         Thread firstThread = null;
//         while (t != null && t != head) {
//             Thread tt = t.thread;
//             if (tt != null) firstThread = tt;
//             t = t.prev;
//         }
//         return firstThread;
//     }
//
//     /**
//      * 判断当前线程是否在队列当中
//      */
//     public final boolean isQueued(Thread thread) {
//         if (thread == null) throw new NullPointerException();
//         for (Node p = tail; p != null; p = p.prev)
//             if (p.thread == thread) return true;
//         return false;
//     }
//
//     final boolean apparentlyFirstQueuedIsExclusive() {
//         Node h, s;
//         return (h = head) != null && (s = h.next) != null && !s.isShared() && s.thread != null;
//     }
//
//     /**
//      * 用来判断线程需不需要排队，因为队列是FIFO的，所以需要判断队列中有没有相关线程的节点已经在排队了。
//      * 返回FALSE场景:
//      * 1.h != t返回false，那么短路与判断就会直接返回false  -- 当头节点和尾节点相等(都为null,或者指向同一个元素)时，才会返回false。
//      * 2.h != t 返回true，(s = h.next) == null 返回false以及s.thread != Thread.currentThread()返回false
//      * h != t返回true表示队列中至少有两个不同节点存在。 (s = h.next) == null返回false表示头节点是有后继节点的。 s.thread != Thread.currentThread()返回fasle表示着当前线程和后继节点的线程是相同的，
//      * 那就说明已经轮到这个线程相关的节点去尝试获取同步状态了，自然无需排队，直接返回fasle。
//      * 返回True场景:
//      * 1.h != t返回true，(s = h.next) == null返回true   h != t返回true表示队列中至少有两个不同节点存在。
//      * (s = h.next) == null返回true 说明头节点之后是没有后继节点的，这情况可能发生在如下情景：有另一个线程已经执行到初始化队列的操作了，介于compareAndSetHead(new Node())与tail = head之间
//      * 2.h != t返回true，(s = h.next) == null返回false，s.thread != Thread.currentThread()返回true。
//      * h != t返回true表示队列中至少有两个不同节点存在。
//      * (s = h.next) == null返回false表示首节点是有后继节点的。 s.thread != Thread.currentThread()返回true 表示后继节点的相关线程不是当前线程，所以首节点虽然有后继节点，但是后继节点相关的线程却不是当前线程，那当前线程自然得老老实实的去排队。
//      *
//      * @return 线程需要排队返回true，否则返回false
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
//     // Instrumentation and monitoring methods
//
//     /**
//      * 同步队列长度
//      */
//     public final int getQueueLength() {
//         int n = 0;
//         for (Node p = tail; p != null; p = p.prev) {
//             if (p.thread != null) ++n;
//         }
//         return n;
//     }
//
//     /**
//      * 获取队列等待thread集合
//      */
//     public final Collection<Thread> getQueuedThreads() {
//         ArrayList<Thread> list = new ArrayList<Thread>();
//         for (Node p = tail; p != null; p = p.prev) {
//             Thread t = p.thread;
//             if (t != null) list.add(t);
//         }
//         return list;
//     }
//
//     /**
//      * 获取独占模式等待thread线程集合
//      */
//     public final Collection<Thread> getExclusiveQueuedThreads() {
//         ArrayList<Thread> list = new ArrayList<Thread>();
//         for (Node p = tail; p != null; p = p.prev) {
//             if (!p.isShared()) {
//                 Thread t = p.thread;
//                 if (t != null) list.add(t);
//             }
//         }
//         return list;
//     }
//
//     /**
//      * 获取共享模式等待thread集合
//      */
//     public final Collection<Thread> getSharedQueuedThreads() {
//         ArrayList<Thread> list = new ArrayList<Thread>();
//         for (Node p = tail; p != null; p = p.prev) {
//             if (p.isShared()) {
//                 Thread t = p.thread;
//                 if (t != null) list.add(t);
//             }
//         }
//         return list;
//     }
//
//
//     // Internal support methods for Conditions
//
//     /**
//      * 判断节点是否在同步队列中
//      */
//     final boolean isOnSyncQueue(Node node) {
//         // 快速判断1：节点状态或者节点没有前置节点
//         // 注：同步队列是有头节点的，而条件队列没有
//         if (node.waitStatus == Node.CONDITION || node.prev == null) return false;
//         // 快速判断2：next字段只有同步队列才会使用，条件队列中使用的是nextWaiter字段
//         if (node.next != null) // If has successor, it must be on queue
//             return true;
//         // 上面如果无法判断则进入复杂判断
//         return findNodeFromTail(node);
//     }
//
//     private boolean findNodeFromTail(Node node) {
//         Node t = tail;
//         for (; ; ) {
//             if (t == node) return true;
//             if (t == null) return false;
//             t = t.prev;
//         }
//     }
//
//     /**
//      * 将节点从条件队列当中移动到同步队列当中，等待获取锁
//      */
//     final boolean transferForSignal(Node node) {
//         /*
//          * 修改节点信号量状态为0，失败直接返回false
//          */
//         if (!compareAndSetWaitStatus(node, Node.CONDITION, 0)) return false;
//
//         /*
//          * 加入同步队列尾部当中，返回前驱节点
//          */
//         Node p = enq(node);
//         int ws = p.waitStatus;
//         // 前驱节点不可用 或者 修改信号量状态失败
//         if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL)) LockSupport.unpark(node.thread); // 唤醒当前节点
//         return true;
//     }
//
//     final boolean transferAfterCancelledWait(Node node) {
//         if (compareAndSetWaitStatus(node, Node.CONDITION, 0)) {
//             enq(node);
//             return true;
//         }
//         /*
//          * If we lost out to a signal(), then we can't proceed
//          * until it finishes its enq().  Cancelling during an
//          * incomplete transfer is both rare and transient, so just
//          * spin.
//          */
//         while (!isOnSyncQueue(node)) Thread.yield();
//         return false;
//     }
//
//     /**
//      * 入参就是新创建的节点，即当前节点
//      */
//     final int fullyRelease(Node node) {
//         boolean failed = true;
//         try {
//             // 这里这个取值要注意，获取当前的state并释放，这从另一个角度说明必须是独占锁
//             // 可以考虑下这个逻辑放在共享锁下面会发生什么？
//             int savedState = getState();
//             if (release(savedState)) {
//                 failed = false;
//                 return savedState;
//             } else {
//                 // 如果这里释放失败，则抛出异常
//                 throw new IllegalMonitorStateException();
//             }
//         } finally {
//             /**
//              * 如果释放锁失败，则把节点取消，由这里就能看出来上面添加节点的逻辑中
//              * 只需要判断最后一个节点是否被取消就可以了
//              */
//             if (failed) node.waitStatus = Node.CANCELLED;
//         }
//     }
//
//     // Instrumentation methods for conditions
//
//     public final boolean hasWaiters(ConditionObject condition) {
//         if (!owns(condition)) throw new IllegalArgumentException("Not owner");
//         return condition.hasWaiters();
//     }
//
//     /**
//      * 获取条件队列长度
//      */
//     public final int getWaitQueueLength(ConditionObject condition) {
//         if (!owns(condition)) throw new IllegalArgumentException("Not owner");
//         return condition.getWaitQueueLength();
//     }
//
//     /**
//      * 获取条件队列当中所有等待的thread集合
//      */
//     public final Collection<Thread> getWaitingThreads(ConditionObject condition) {
//         if (!owns(condition)) throw new IllegalArgumentException("Not owner");
//         return condition.getWaitingThreads();
//     }
//
//     /**
//      * 条件对象，实现基于条件的具体行为
//      */
//     public class ConditionObject implements Condition, java.io.Serializable {
//         private static final long serialVersionUID = 1173984872572414699L;
//         /**
//          * First node of condition queue.
//          */
//         private transient Node firstWaiter;
//         /**
//          * Last node of condition queue.
//          */
//         private transient Node lastWaiter;
//
//         /**
//          * Creates a new {@code ConditionObject} instance.
//          */
//         public ConditionObject() {
//         }
//
//         // Internal methods
//
//         /**
//          * 1.与同步队列不同，条件队列头尾指针是firstWaiter跟lastWaiter
//          * 2.条件队列是在获取锁之后，也就是临界区进行操作，因此很多地方不用考虑并发
//          */
//         private Node addConditionWaiter() {
//             Node t = lastWaiter;
//             // 如果最后一个节点被取消，则删除队列中被取消的节点
//             // 至于为啥是最后一个节点后面会分析
//             if (t != null && t.waitStatus != Node.CONDITION) {
//                 // 删除所有被取消的节点
//                 unlinkCancelledWaiters();
//                 t = lastWaiter;
//             }
//             // 创建一个类型为CONDITION的节点并加入队列，由于在临界区，所以这里不用并发控制
//             Node node = new Node(Thread.currentThread(), Node.CONDITION);
//             if (t == null) firstWaiter = node;
//             else t.nextWaiter = node;
//             lastWaiter = node;
//             return node;
//         }
//
//         /**
//          * 发信号，通知遍历条件队列当中的节点转移到同步队列当中，准备排队获取锁
//          */
//         private void doSignal(Node first) {
//             do {
//                 if ((firstWaiter = first.nextWaiter) == null) lastWaiter = null;
//                 first.nextWaiter = null;
//             } while (!transferForSignal(first) && // 转移节点
//                     (first = firstWaiter) != null);
//         }
//
//         /**
//          * 通知所有节点移动到同步队列当中，并将节点从条件队列删除
//          */
//         private void doSignalAll(Node first) {
//             lastWaiter = firstWaiter = null;
//             do {
//                 Node next = first.nextWaiter;
//                 first.nextWaiter = null;
//                 transferForSignal(first);
//                 first = next;
//             } while (first != null);
//         }
//
//         /**
//          * 删除条件队列当中被取消的节点
//          */
//         private void unlinkCancelledWaiters() {
//             Node t = firstWaiter;
//             Node trail = null;
//             while (t != null) {
//                 Node next = t.nextWaiter;
//                 if (t.waitStatus != Node.CONDITION) {
//                     t.nextWaiter = null;
//                     if (trail == null) firstWaiter = next;
//                     else trail.nextWaiter = next;
//                     if (next == null) lastWaiter = trail;
//                 } else trail = t;
//                 t = next;
//             }
//         }
//
//         // public methods
//
//         /**
//          * 发新号，通知条件队列当中节点到同步队列当中去排队
//          */
//         public final void signal() {
//             if (!isHeldExclusively())// 节点不能已经持有独占锁
//                 throw new IllegalMonitorStateException();
//             Node first = firstWaiter;
//             if (first != null)
//             /**
//              * 发信号通知条件队列的节点准备到同步队列当中去排队
//              */ doSignal(first);
//         }
//
//         /**
//          * 唤醒所有条件队列的节点转移到同步队列当中
//          */
//         public final void signalAll() {
//             if (!isHeldExclusively()) throw new IllegalMonitorStateException();
//             Node first = firstWaiter;
//             if (first != null) doSignalAll(first);
//         }
//
//         /**
//          * Implements uninterruptible condition wait.
//          * <ol>
//          * <li> Save lock state returned by {@link #getState}.
//          * <li> Invoke {@link #release} with saved state as argument,
//          *      throwing IllegalMonitorStateException if it fails.
//          * <li> Block until signalled.
//          * <li> Reacquire by invoking specialized version of
//          *      {@link #acquire} with saved state as argument.
//          * </ol>
//          */
//         public final void awaitUninterruptibly() {
//             Node node = addConditionWaiter();
//             int savedState = fullyRelease(node);
//             boolean interrupted = false;
//             while (!isOnSyncQueue(node)) {
//                 LockSupport.park(this);
//                 if (Thread.interrupted()) interrupted = true;
//             }
//             if (acquireQueued(node, savedState) || interrupted) selfInterrupt();
//         }
//
//         /**
//          * 该模式表示在退出等待时重新中断
//          */
//         private static final int REINTERRUPT = 1;
//         /**
//          * 异常中断
//          */
//         private static final int THROW_IE = -1;
//
//         /**
//          * 这里的判断逻辑是：
//          * 1.如果现在不是中断的，即正常被signal唤醒则返回0
//          * 2.如果节点由中断加入同步队列则返回THROW_IE，由signal加入同步队列则返回REINTERRUPT
//          */
//         private int checkInterruptWhileWaiting(Node node) {
//             return Thread.interrupted() ? (transferAfterCancelledWait(node) ? THROW_IE : REINTERRUPT) : 0;
//         }
//
//         /**
//          * 根据中断时机选择抛出异常或者设置线程中断状态
//          */
//         private void reportInterruptAfterWait(int interruptMode) throws InterruptedException {
//             if (interruptMode == THROW_IE) throw new InterruptedException();
//             else if (interruptMode == REINTERRUPT) selfInterrupt();
//         }
//
//         /**
//          * 加入条件队列等待，条件队列入口
//          */
//         public final void await() throws InterruptedException {
//
//             // T2进来
//             // 如果当前线程被中断则直接抛出异常
//             if (Thread.interrupted()) throw new InterruptedException();
//             // 把当前节点加入条件队列
//             Node node = addConditionWaiter();
//             // 释放掉已经获取的独占锁资源
//             int savedState = fullyRelease(node);// T2释放锁
//             int interruptMode = 0;
//             // 如果不在同步队列中则不断挂起
//             while (!isOnSyncQueue(node)) {
//                 LockSupport.park(this);// T1被阻塞
//                 // 这里被唤醒可能是正常的signal操作也可能是中断
//                 if ((interruptMode = checkInterruptWhileWaiting(node)) != 0) break;
//             }
//             /**
//              * 走到这里说明节点已经条件满足被加入到了同步队列中或者中断了
//              * 这个方法很熟悉吧？就跟独占锁调用同样的获取锁方法，从这里可以看出条件队列只能用于独占锁
//              * 在处理中断之前首先要做的是从同步队列中成功获取锁资源
//              */
//             if (acquireQueued(node, savedState) && interruptMode != THROW_IE) interruptMode = REINTERRUPT;
//             // 走到这里说明已经成功获取到了独占锁，接下来就做些收尾工作
//             // 删除条件队列中被取消的节点
//             if (node.nextWaiter != null) // clean up if cancelled
//                 unlinkCancelledWaiters();
//             // 根据不同模式处理中断
//             if (interruptMode != 0) reportInterruptAfterWait(interruptMode);
//         }
//
//
//         /**
//          * Implements timed condition wait.
//          * <ol>
//          * <li> If current thread is interrupted, throw InterruptedException.
//          * <li> Save lock state returned by {@link #getState}.
//          * <li> Invoke {@link #release} with saved state as argument,
//          *      throwing IllegalMonitorStateException if it fails.
//          * <li> Block until signalled, interrupted, or timed out.
//          * <li> Reacquire by invoking specialized version of
//          *      {@link #acquire} with saved state as argument.
//          * <li> If interrupted while blocked in step 4, throw InterruptedException.
//          * <li> If timed out while blocked in step 4, return false, else true.
//          * </ol>
//          */
//         public final boolean await(long time, TimeUnit unit) throws InterruptedException {
//             long nanosTimeout = unit.toNanos(time);
//             if (Thread.interrupted()) throw new InterruptedException();
//             Node node = addConditionWaiter();
//             int savedState = fullyRelease(node);
//             final long deadline = System.nanoTime() + nanosTimeout;
//             boolean timedout = false;
//             int interruptMode = 0;
//             while (!isOnSyncQueue(node)) {
//                 if (nanosTimeout <= 0L) {
//                     timedout = transferAfterCancelledWait(node);
//                     break;
//                 }
//                 if (nanosTimeout >= spinForTimeoutThreshold) LockSupport.parkNanos(this, nanosTimeout);
//                 if ((interruptMode = checkInterruptWhileWaiting(node)) != 0) break;
//                 nanosTimeout = deadline - System.nanoTime();
//             }
//             if (acquireQueued(node, savedState) && interruptMode != THROW_IE) interruptMode = REINTERRUPT;
//             if (node.nextWaiter != null) unlinkCancelledWaiters();
//             if (interruptMode != 0) reportInterruptAfterWait(interruptMode);
//             return !timedout;
//         }
//
//
//         final boolean isOwnedBy(AbstractQueuedSynchronizer sync) {
//             return sync == AbstractQueuedSynchronizer.this;
//         }
//
//         /**
//          * Queries whether any threads are waiting on this condition.
//          * Implements {@link AbstractQueuedSynchronizer#hasWaiters(ConditionObject)}.
//          *
//          * @return {@code true} if there are any waiting threads
//          * @throws IllegalMonitorStateException if {@link #isHeldExclusively}
//          *                                      returns {@code false}
//          */
//         protected final boolean hasWaiters() {
//             if (!isHeldExclusively()) throw new IllegalMonitorStateException();
//             for (Node w = firstWaiter; w != null; w = w.nextWaiter) {
//                 if (w.waitStatus == Node.CONDITION) return true;
//             }
//             return false;
//         }
//
//         /**
//          * Returns an estimate of the number of threads waiting on
//          * this condition.
//          * Implements {@link AbstractQueuedSynchronizer#getWaitQueueLength(ConditionObject)}.
//          *
//          * @return the estimated number of waiting threads
//          * @throws IllegalMonitorStateException if {@link #isHeldExclusively}
//          *                                      returns {@code false}
//          */
//         protected final int getWaitQueueLength() {
//             if (!isHeldExclusively()) throw new IllegalMonitorStateException();
//             int n = 0;
//             for (Node w = firstWaiter; w != null; w = w.nextWaiter) {
//                 if (w.waitStatus == Node.CONDITION) ++n;
//             }
//             return n;
//         }
//
//         /**
//          * 得到同步队列当中所有在等待的Thread集合
//          */
//         protected final Collection<Thread> getWaitingThreads() {
//             if (!isHeldExclusively()) throw new IllegalMonitorStateException();
//             ArrayList<Thread> list = new ArrayList<Thread>();
//             for (Node w = firstWaiter; w != null; w = w.nextWaiter) {
//                 if (w.waitStatus == Node.CONDITION) {
//                     Thread t = w.thread;
//                     if (t != null) list.add(t);
//                 }
//             }
//             return list;
//         }
//     }
//
//     /**
//      * Setup to support compareAndSet. We need to natively implement
//      * this here: For the sake of permitting future enhancements, we
//      * cannot explicitly subclass AtomicInteger, which would be
//      * efficient and useful otherwise. So, as the lesser of evils, we
//      * natively implement using hotspot intrinsics API. And while we
//      * are at it, we do the same for other CASable fields (which could
//      * otherwise be done with atomic field updaters).
//      * unsafe魔法类，直接绕过虚拟机内存管理机制，修改内存
//      */
//     private static final Unsafe unsafe = Unsafe.getUnsafe();
//     // 偏移量
//     private static final long stateOffset;
//     private static final long headOffset;
//     private static final long tailOffset;
//     private static final long waitStatusOffset;
//     private static final long nextOffset;
//
//     static {
//         try {
//             // 状态偏移量
//             stateOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("state"));
//             // head指针偏移量，head指向CLH队列的头部
//             headOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("head"));
//             tailOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
//             waitStatusOffset = unsafe.objectFieldOffset(Node.class.getDeclaredField("waitStatus"));
//             nextOffset = unsafe.objectFieldOffset(Node.class.getDeclaredField("next"));
//
//         } catch (Exception ex) {
//             throw new Error(ex);
//         }
//     }
//
//     /**
//      * CAS 修改头部节点指向. 并发入队时使用.
//      */
//     private final boolean compareAndSetHead(Node update) {
//         return unsafe.compareAndSwapObject(this, headOffset, null, update);
//     }
//
//     /**
//      * CAS 修改尾部节点指向. 并发入队时使用.
//      */
//     private final boolean compareAndSetTail(Node expect, Node update) {
//         return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
//     }
//
//     /**
//      * CAS 修改信号量状态.
//      */
//     private static final boolean compareAndSetWaitStatus(Node node, int expect, int update) {
//         return unsafe.compareAndSwapInt(node, waitStatusOffset, expect, update);
//     }
//
//     /**
//      * 修改节点的后继指针.
//      */
//     private static final boolean compareAndSetNext(Node node, Node expect, Node update) {
//         return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
//     }
// }
//
// /**
//  * AQS框架具体实现-独占锁实现ReentrantLock
//  */
// public class ReentrantLock implements Lock, java.io.Serializable {
//     private static final long serialVersionUID = 7373984872572414699L;
//     /**
//      * 内部调用AQS的动作，都基于该成员属性实现
//      */
//     private final Sync sync;
//
//     /**
//      * ReentrantLock锁同步操作的基础类,继承自AQS框架.
//      * 该类有两个继承类，1、NonfairSync 非公平锁，2、FairSync公平锁
//      */
//     abstract static class Sync extends AbstractQueuedSynchronizer {
//         private static final long serialVersionUID = -5179523762034025860L;
//
//         /**
//          * 加锁的具体行为由子类实现
//          */
//         abstract void lock();
//
//         /**
//          * 尝试获取非公平锁 争夺资源 尝试占用锁
//          */
//         final boolean nonfairTryAcquire(int acquires) {
//             // 获取到当前线程 acquires = 1
//             final Thread current = Thread.currentThread();
//             // 获取当前线程的state
//             int c = getState();
//             /**
//              * 不需要判断同步队列（CLH）中是否有排队等待线程
//              * 判断state状态是否为0，为0说明当前线程没有占用锁，
//              */
//             if (c == 0) {
//                 // unsafe操作，cas修改state状态
//                 if (compareAndSetState(0, acquires)) {
//                     // 独占状态锁持有者指向当前线程
//                     setExclusiveOwnerThread(current);
//                     return true;
//                 }
//             }
//             /**
//              * state状态不为0，说明当前线程已经占用了锁,判断锁持有者是否是当前线程，
//              * 如果是当前线程持有 则state+1
//              */
//             else if (current == getExclusiveOwnerThread()) {
//                 // 如果是持有锁的线程，可以重入  (重入次数+1)
//                 int nextc = c + acquires;
//                 if (nextc < 0) // overflow
//                     throw new Error("Maximum lock count exceeded");
//                 // 这个地方不需要CAS 是因为判断条件 c == 0 保证了只有一个线程可以进入
//                 setState(nextc);
//                 return true;
//             }
//             // 加锁失败
//             return false;
//         }
//
//         /**
//          * 释放锁
//          */
//         protected final boolean tryRelease(int releases) {
//             int c = getState() - releases;
//             if (Thread.currentThread() != getExclusiveOwnerThread()) throw new IllegalMonitorStateException();
//             boolean free = false;
//             if (c == 0) {
//                 free = true;
//                 setExclusiveOwnerThread(null);
//             }
//             setState(c);
//             return free;
//         }
//
//         /**
//          * 判断持有独占锁的线程是否是当前线程
//          */
//         protected final boolean isHeldExclusively() {
//             return getExclusiveOwnerThread() == Thread.currentThread();
//         }
//
//         // 返回条件对象
//         final ConditionObject newCondition() {
//             return new ConditionObject();
//         }
//
//
//         final Thread getOwner() {
//             return getState() == 0 ? null : getExclusiveOwnerThread();
//         }
//
//         final int getHoldCount() {
//             return isHeldExclusively() ? getState() : 0;
//         }
//
//         final boolean isLocked() {
//             return getState() != 0;
//         }
//
//         /**
//          * Reconstitutes the instance from a stream (that is, deserializes it).
//          */
//         private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
//             s.defaultReadObject();
//             setState(0); // reset to unlocked state
//         }
//     }
//
//     /**
//      * 非公平锁
//      */
//     static final class NonfairSync extends Sync {
//         private static final long serialVersionUID = 7316153563782823691L;
//
//         /**
//          * 加锁行为
//          */
//         final void lock() {
//             /**
//              * 第一步：直接尝试加锁
//              * 与公平锁实现的加锁行为一个最大的区别在于，此处不会去判断同步队列(CLH队列)中
//              * 是否有排队等待加锁的节点，上来直接加锁（判断state是否为0,CAS修改state为1）
//              * ，并将独占锁持有者 exclusiveOwnerThread 属性指向当前线程
//              * 如果当前有人占用锁，再尝试去加一次锁
//              */
//             if (compareAndSetState(0, 1)) setExclusiveOwnerThread(Thread.currentThread());
//             else
//                 // AQS定义的方法,加锁
//                 acquire(1);
//         }
//
//         /**
//          * 父类AbstractQueuedSynchronizer.acquire()中调用本方法
//          */
//         protected final boolean tryAcquire(int acquires) {
//             return nonfairTryAcquire(acquires);
//         }
//     }
//
//     /**
//      * 公平锁
//      */
//     static final class FairSync extends Sync {
//         private static final long serialVersionUID = -3000897897090466540L;
//
//         final void lock() {
//             acquire(1);
//         }
//
//         /**
//          * 重写aqs中的方法逻辑
//          * 尝试加锁，被AQS的acquire()方法调用
//          */
//         protected final boolean tryAcquire(int acquires) {
//             final Thread current = Thread.currentThread();
//             int c = getState();
//             if (c == 0) {
//                 /**
//                  * 与非公平锁中的区别，需要先判断队列当中是否有等待的节点
//                  * 如果没有则可以尝试CAS获取锁
//                  */
//                 if (!hasQueuedPredecessors() && compareAndSetState(0, acquires)) {
//                     // 独占线程指向当前线程
//                     setExclusiveOwnerThread(current);
//                     return true;
//                 }
//             } else if (current == getExclusiveOwnerThread()) {
//                 int nextc = c + acquires;
//                 if (nextc < 0) throw new Error("Maximum lock count exceeded");
//                 setState(nextc);
//                 return true;
//             }
//             return false;
//         }
//     }
//
//     /**
//      * 默认构造函数，创建非公平锁对象
//      */
//     public ReentrantLock() {
//         sync = new NonfairSync();
//     }
//
//     /**
//      * 根据要求创建公平锁或非公平锁
//      */
//     public ReentrantLock(boolean fair) {
//         sync = fair ? new FairSync() : new NonfairSync();
//     }
//
//     /**
//      * 加锁
//      */
//     public void lock() {
//         sync.lock();
//     }
//
//     /**
//      * 尝试获去取锁，获取失败被阻塞，线程被中断直接抛出异常
//      */
//     public void lockInterruptibly() throws InterruptedException {
//         sync.acquireInterruptibly(1);
//     }
//
//     /**
//      * 尝试加锁
//      */
//     public boolean tryLock() {
//         return sync.nonfairTryAcquire(1);
//     }
//
//     /**
//      * 指定等待时间内尝试加锁
//      */
//     public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
//         return sync.tryAcquireNanos(1, unit.toNanos(timeout));
//     }
//
//     /**
//      * 尝试去释放锁
//      */
//     public void unlock() {
//         sync.release(1);
//     }
//
//     /**
//      * 返回条件对象
//      */
//     public Condition newCondition() {
//         return sync.newCondition();
//     }
//
//     /**
//      * 返回当前线程持有的state状态数量
//      */
//     public int getHoldCount() {
//         return sync.getHoldCount();
//     }
//
//     /**
//      * 查询当前线程是否持有锁
//      */
//     public boolean isHeldByCurrentThread() {
//         return sync.isHeldExclusively();
//     }
//
//     /**
//      * 状态表示是否被Thread加锁持有
//      */
//     public boolean isLocked() {
//         return sync.isLocked();
//     }
//
//     /**
//      * 是否公平锁？是返回true 否则返回 false
//      */
//     public final boolean isFair() {
//         return sync instanceof FairSync;
//     }
//
//     /**
//      * 获取持有锁的当前线程
//      */
//     protected Thread getOwner() {
//         return sync.getOwner();
//     }
//
//     /**
//      * 判断队列当中是否有在等待获取锁的Thread节点
//      */
//     public final boolean hasQueuedThreads() {
//         return sync.hasQueuedThreads();
//     }
//
//     /**
//      * 当前线程是否在同步队列中等待
//      */
//     public final boolean hasQueuedThread(Thread thread) {
//         return sync.isQueued(thread);
//     }
//
//     /**
//      * 获取同步队列长度
//      */
//     public final int getQueueLength() {
//         return sync.getQueueLength();
//     }
//
//     /**
//      * 返回Thread集合，排队中的所有节点Thread会被返回
//      */
//     protected Collection<Thread> getQueuedThreads() {
//         return sync.getQueuedThreads();
//     }
//
//     /**
//      * 条件队列当中是否有正在等待的节点
//      */
//     public boolean hasWaiters(Condition condition) {
//         if (condition == null) throw new NullPointerException();
//         if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
//             throw new IllegalArgumentException("not owner");
//         return sync.hasWaiters((AbstractQueuedSynchronizer.ConditionObject) condition);
//     }
//
// }