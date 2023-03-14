package com.github.zhuyaotong.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 以下代码来源于【参考1】
//class VolatileExample {
//    int x = 0;
//    volatile boolean v = false;
//    private static int var = 0;
//
//    public void writer() {
//        x = 42;
//        v = true;
//    }
//
//    public void reader() {
//        if (v == true) {
//            // 这里x会是多少呢？
//        }
//
//        synchronized (this) { //此处自动加锁
//            // x是共享变量,初始值=10
//            if (this.x < 12) {
//                this.x = 12;
//            }
//        } //此处自动解锁
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        VolatileExample ve = new VolatileExample();
//        System.out.println("x = " + ve.x);
//        ve.writer();
//        System.out.println("x = " + ve.x);
//        ve.reader();
//        System.out.println("x = " + ve.x);
//
//
//        Thread B = new Thread(() -> {
//            // 主线程调用B.start()之前
//            // 所有对共享变量的修改，此处皆可见
//            // 此例中，var==77
//        });
//        // 此处对共享变量var修改
//        var = 77;
//        // 主线程启动子线程
//        B.start();
//
//
//        Thread c = new Thread(() -> {
//            // 此处对共享变量var修改
//            var = 66;
//        });
//        // 例如此处对共享变量修改，
//        // 则这个修改结果对线程c可见
//        // 主线程启动子线程
//        c.start();
//        c.join();
//        // 子线程所有对共享变量的修改
//        // 在主线程调用c.join()之后皆可见
//        // 此例中，var==66
//    }
//}
//
//class X {
//    // 修饰非静态方法
//    synchronized void foo() {
//        // 临界区
//    }
//
//    // 修饰静态方法
//    synchronized static void bar() {
//        // 临界区
//    }
//
//    // 修饰代码块
//    Object obj = new Object();
//
//    void baz() {
//        synchronized (obj) {
//            // 临界区
//        }
//    }
//}
//
//class Account {
//    // 锁：保护账户余额
//    private final Object balLock = new Object();
//    // 账户余额
//    private Integer balance;
//    // 锁：保护账户密码
//    private final Object pwLock = new Object();
//    // 账户密码
//    private String password;
//
//    // 取款
//    void withdraw(Integer amt) {
//        synchronized (balLock) {
//            if (this.balance > amt) {
//                this.balance -= amt;
//            }
//        }
//    }
//
//    // 查看余额
//    Integer getBalance() {
//        synchronized (balLock) {
//            return balance;
//        }
//    }
//
//    // 更改密码
//    void updatePassword(String pw) {
//        synchronized (pwLock) {
//            this.password = pw;
//        }
//    }
//
//    // 查看密码
//    String getPassword() {
//        synchronized (pwLock) {
//            return password;
//        }
//    }
//}
//
//class AccountB {
//    private Object lock;
//    private int balance;
//
//    private AccountB() {
//    }
//
//    // 创建Account时传入同一个lock对象
//    public AccountB(Object lock) {
//        this.lock = lock;
//    }
//
//    // 转账
//    void transfer(AccountB target, int amt) {
//        // 此处检查所有对象共享的锁
//        synchronized (lock) {
//            if (this.balance > amt) {
//                this.balance -= amt;
//                target.balance += amt;
//            }
//        }
//    }
//}
//
//class AccountC {
//    private int balance;
//
//    // 转账
//    void transfer(AccountC target, int amt) {
//        synchronized (AccountC.class) {
//            if (this.balance > amt) {
//                this.balance -= amt;
//                target.balance += amt;
//            }
//        }
//    }
//}
//
//class Account {
//    private int balance;
//    // 转账
//    void transfer(Account target, int amt){
//        // 锁定转出账户
//        synchronized(this) {
//            // 锁定转入账户
//            synchronized(target) {
//                if (this.balance > amt) {
//                    this.balance -= amt;
//                    target.balance += amt;
//                }
//            }
//        }
//    }
//}
//
//
//class Account {
//    private int balance;
//    // 转账
//    void transfer(Account target, int amt){
//        // 锁定转出账户
//        synchronized(this){     ①
//            // 锁定转入账户
//            synchronized(target){ ②
//                if (this.balance > amt) {
//                    this.balance -= amt;
//                    target.balance += amt;
//                }
//            }
//        }
//    }
//}
//
//
//class Allocator {
//    private List<Object> als =
//            new ArrayList<>();
//    // 一次性申请所有资源
//    synchronized boolean apply(
//            Object from, Object to){
//        if(als.contains(from) ||
//                als.contains(to)){
//            return false;
//        } else {
//            als.add(from);
//            als.add(to);
//        }
//        return true;
//    }
//    // 归还资源
//    synchronized void free(
//            Object from, Object to){
//        als.remove(from);
//        als.remove(to);
//    }
//}
//
//class Account {
//    // actr应该为单例
//    private Allocator actr;
//    private int balance;
//    // 转账
//    void transfer(Account target, int amt){
//        // 一次性申请转出账户和转入账户，直到成功
//        while(!actr.apply(this, target));
//        try{
//            // 锁定转出账户
//            synchronized(this){
//                // 锁定转入账户
//                synchronized(target){
//                    if (this.balance > amt){
//                        this.balance -= amt;
//                        target.balance += amt;
//                    }
//                }
//            }
//        } finally {
//            actr.free(this, target);
//        }
//    }
//}
//
//
//class Account {
//    private int id;
//    private int balance;
//    // 转账
//    void transfer(Account target, int amt){
//        Account left = this;        ①
//        Account right = target;    ②
//        if (this.id > target.id) { ③
//            left = target;           ④
//            right = this;            ⑤
//        }                          ⑥
//        // 锁定序号小的账户
//        synchronized(left){
//            // 锁定序号大的账户
//            synchronized(right){
//                if (this.balance > amt){
//                    this.balance -= amt;
//                    target.balance += amt;
//                }
//            }
//        }
//    }
//}
//
//
//class Allocator {
//    private List<Object> als;
//    // 一次性申请所有资源
//    synchronized void apply(
//            Object from, Object to){
//        // 经典写法
//        while(als.contains(from) ||
//                als.contains(to)){
//            try{
//                wait();
//            }catch(Exception e){
//            }
//        }
//        als.add(from);
//        als.add(to);
//    }
//    // 归还资源
//    synchronized void free(
//            Object from, Object to){
//        als.remove(from);
//        als.remove(to);
//        notifyAll();
//    }
//}
//
//
//public class BlockedQueue<T>{
//    final Lock lock =
//            new ReentrantLock();
//    // 条件变量：队列不满
//    final Condition notFull =
//            lock.newCondition();
//    // 条件变量：队列不空
//    final Condition notEmpty =
//            lock.newCondition();
//
//    // 入队
//    void enq(T x) {
//        lock.lock();
//        try {
//            while (队列已满){
//                // 等待队列不满
//                notFull.await();
//            }
//            // 省略入队操作...
//            //入队后,通知可出队
//            notEmpty.signal();
//        }finally {
//            lock.unlock();
//        }
//    }
//    // 出队
//    void deq(){
//        lock.lock();
//        try {
//            while (队列已空){
//                // 等待队列不空
//                notEmpty.await();
//            }
//            // 省略出队操作...
//            //出队后，通知可入队
//            notFull.signal();
//        }finally {
//            lock.unlock();
//        }
//    }
//}
//
//
//// 自定义线程对象
//class MyThread extends Thread {
//    public void run() {
//        // 线程需要执行的代码
//    ......
//    }
//}
//    // 创建线程对象
//    MyThread myThread = new MyThread();
//
//
//// 实现Runnable接口
//class Runner implements Runnable {
//    @Override
//    public void run() {
//        // 线程需要执行的代码
//    ......
//    }
//}
//    // 创建线程对象
//    Thread thread = new Thread(new Runner());
//
//    MyThread myThread = new MyThread();
//    // 从NEW状态转换到RUNNABLE状态
//    myThread.start();
//
//
//
//public class Counter {
//    private long value;
//    synchronized long get(){
//        return value;
//    }
//    synchronized long addOne(){
//        return ++value;
//    }
//}
//
//
//public class SafeWM {
//    // 库存上限
//    private final AtomicLong upper =
//            new AtomicLong(0);
//    // 库存下限
//    private final AtomicLong lower =
//            new AtomicLong(0);
//    // 设置库存上限
//    void setUpper(long v){
//        upper.set(v);
//    }
//    // 设置库存下限
//    void setLower(long v){
//        lower.set(v);
//    }
//    // 省略其他业务代码
//}
//
//
//public class SafeWM {
//    // 库存上限
//    private final AtomicLong upper =
//            new AtomicLong(0);
//    // 库存下限
//    private final AtomicLong lower =
//            new AtomicLong(0);
//    // 设置库存上限
//    void setUpper(long v){
//        // 检查参数合法性
//        if (v < lower.get()) {
//            throw new IllegalArgumentException();
//        }
//        upper.set(v);
//    }
//    // 设置库存下限
//    void setLower(long v){
//        // 检查参数合法性
//        if (v > upper.get()) {
//            throw new IllegalArgumentException();
//        }
//        lower.set(v);
//    }
//    // 省略其他业务代码
//}
//
//
//class SafeCalc {
//    long value = 0L;
//    long get() {
//        synchronized (new Object()) {
//            return value;
//        }
//    }
//    void addOne() {
//        synchronized (new Object()) {
//            value += 1;
//        }
//    }
//}
//
//
//class Account {
//    // 账户余额
//    private Integer balance;
//    // 账户密码
//    private String password;
//    // 取款
//    void withdraw(Integer amt) {
//        synchronized(balance) {
//            if (this.balance > amt){
//                this.balance -= amt;
//            }
//        }
//    }
//    // 更改密码
//    void updatePassword(String pw){
//        synchronized(password) {
//            this.password = pw;
//        }
//    }
//}
//
//
//    // 普通对象锁
//    private final Object
//            lock = new Object();
//    // 静态对象锁
//    private static final Object
//            lock = new Object();
//
//
//
//
//class SafeVector{
//    private Vector v;
//    // 所有公共方法增加同步控制
//    synchronized
//    void addIfNotExist(Object o){
//        if(!v.contains(o)) {
//            v.add(o);
//        }
//    }
//}

//class Hello {
//    public static void main(String[] args) {
//
//        Thread th = Thread.currentThread();
//        while(true) {
//            if(th.isInterrupted()) {
//                break;
//            }
//            // 省略业务代码无数
//            try {
//                Thread.sleep(100);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//
//
//        try {
//            Thread.sleep(100);
//        }catch(InterruptedException e){
//            // 重新设置中断标志位
//            th.interrupt();
//        }
//    }
//}
//
//
//class SampleLock {
//    volatile int state;
//    // 加锁
//    lock() {
//        // 省略代码无数
//        state = 1;
//    }
//    // 解锁
//    unlock() {
//        // 省略代码无数
//        state = 0;
//    }
//}
//
//
//class X {
//    private final Lock rtl =
//            new ReentrantLock();
//    int value;
//    public int get() {
//        // 获取锁
//        rtl.lock();         ②
//        try {
//            return value;
//        } finally {
//            // 保证锁能释放
//            rtl.unlock();
//        }
//    }
//    public void addOne() {
//        // 获取锁
//        rtl.lock();
//        try {
//            value = 1 + get(); ①
//        } finally {
//            // 保证锁能释放
//            rtl.unlock();
//        }
//    }
//}
//
//
//public class BlockedQueue<T>{
//    final Lock lock =
//            new ReentrantLock();
//    // 条件变量：队列不满
//    final Condition notFull =
//            lock.newCondition();
//    // 条件变量：队列不空
//    final Condition notEmpty =
//            lock.newCondition();
//
//    // 入队
//    void enq(T x) {
//        lock.lock();
//        try {
//            while (队列已满){
//                // 等待队列不满
//                notFull.await();
//            }
//            // 省略入队操作...
//            //入队后,通知可出队
//            notEmpty.signal();
//        }finally {
//            lock.unlock();
//        }
//    }
//    // 出队
//    void deq(){
//        lock.lock();
//        try {
//            while (队列已空){
//                // 等待队列不空
//                notEmpty.await();
//            }
//            // 省略出队操作...
//            //出队后，通知可入队
//            notFull.signal();
//        }finally {
//            lock.unlock();
//        }
//    }
//}
