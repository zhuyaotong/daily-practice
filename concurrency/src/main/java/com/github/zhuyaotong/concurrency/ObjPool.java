package com.github.zhuyaotong.concurrency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Function;

class ObjPool<T, R> {

  final List<T> pool;

  // 用信号量实现限流器
  final Semaphore sem;

  // 构造函数
  ObjPool(int size, T t) {

    pool = new Vector<T>() {};

    for (int i = 0; i < size; i++) {
      pool.add(t);
    }

    sem = new Semaphore(size);
  }

  // 利用对象池的对象，调用func
  R exec(Function<T, R> func) throws InterruptedException {

    T t = null;
    sem.acquire();

    try {
      t = pool.remove(0);
      return func.apply(t);

    } finally {
      pool.add(t);
      sem.release();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    // 创建对象池
    ObjPool<Long, String> pool = new ObjPool<Long, String>(10, 2L);

    ObjPool<String, String> pool2 = new ObjPool<String, String>(10, "你好");

    // 通过对象池获取t，之后执行
    pool.exec(
        t -> {
          System.out.println(t);
          return t.toString();
        });

    pool2.exec(
        t -> {
          System.out.println(t);
          return t;
        });
  }
}

class Cache<K, V> {

  final Map<K, V> m = new HashMap<>();
  final ReadWriteLock rwl = new ReentrantReadWriteLock();

  // 读锁
  final Lock r = rwl.readLock();
  // 写锁
  final Lock w = rwl.writeLock();

  // 读缓存
  V get(K key) {
    r.lock();

    try {
      return m.get(key);
    } finally {
      r.unlock();
    }
  }

  // 写缓存
  V put(K key, V value) {
    w.lock();

    try {
      return m.put(key, value);
    } finally {
      w.unlock();
    }
  }
}

class SL {
  private double x, y;
  final StampedLock sl = new StampedLock();

  // 存在问题的方法
  void moveIfAtOrigin(double newX, double newY) {

    long stamp = sl.readLock();

    try {
      while (x == 0.0 && y == 0.0) {

        long ws = sl.tryConvertToWriteLock(stamp);
        stamp = ws;

        if (ws != 0L) {
          x = newX;
          y = newY;

          break;

        } else {
          sl.unlockRead(stamp);
          stamp = sl.writeLock();
        }
      }

    } finally {
      sl.unlock(stamp);
    }
  }
}

// T1Task需要执行的任务：
// 洗水壶、烧开水、泡茶
class T1Task implements Callable<String> {

  FutureTask<String> ft2;

  // T1任务需要T2任务的FutureTask
  T1Task(FutureTask<String> ft2) {
    this.ft2 = ft2;
  }

  @Override
  public String call() throws Exception {

    System.out.println("T1:洗水壶...");
    TimeUnit.SECONDS.sleep(1);

    System.out.println("T1:烧开水...");
    TimeUnit.SECONDS.sleep(15);

    // 获取T2线程的茶叶
    String tf = ft2.get();
    System.out.println("T1:拿到茶叶:" + tf);

    System.out.println("T1:泡茶...");

    return "上茶:" + tf;
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // 创建任务T2的FutureTask
    FutureTask<String> ft2 = new FutureTask<>(new T2Task());

    // 创建任务T1的FutureTask
    FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

    // 线程T1执行任务ft1
    Thread T1 = new Thread(ft1);
    T1.start();

    // 线程T2执行任务ft2
    Thread T2 = new Thread(ft2);
    T2.start();

    // 等待线程T1执行结果
    System.out.println(ft1.get());
  }
}

// T2Task需要执行的任务:
// 洗茶壶、洗茶杯、拿茶叶
class T2Task implements Callable<String> {

  @Override
  public String call() throws Exception {

    System.out.println("T2:洗茶壶...");
    TimeUnit.SECONDS.sleep(1);

    System.out.println("T2:洗茶杯...");
    TimeUnit.SECONDS.sleep(2);

    System.out.println("T2:拿茶叶...");
    TimeUnit.SECONDS.sleep(1);

    return "龙井";
  }
}

// 递归任务
class Fibonacci extends RecursiveTask<Integer> {

  final int n;

  Fibonacci(int n) {
    this.n = n;
  }

  protected Integer compute() {

    if (n <= 1) return n;

    Fibonacci f1 = new Fibonacci(n - 1);

    // 创建子任务
    f1.fork();

    Fibonacci f2 = new Fibonacci(n - 2);

    // 等待子任务结果，并合并结果
    return f2.compute() + f1.join();
  }

  protected Integer compute2(int n) {

    if (n <= 1) return n;

    return compute2(n - 1) + compute2(n - 2);
  }

  public static void main(String[] args) {

    // 创建分治任务线程池
    ForkJoinPool fjp = new ForkJoinPool(4);

    // 创建分治任务
    Fibonacci fib = new Fibonacci(30);

    // 启动分治任务
    Integer result = fjp.invoke(fib);

    // 输出结果
    System.out.println(result);

    System.out.println(fib.compute2(30));
  }
}

// MR模拟类
class MR extends RecursiveTask<Map<String, Long>> {

  private String[] fc;
  private int start, end;

  // 构造函数
  MR(String[] fc, int fr, int to) {
    this.fc = fc;
    this.start = fr;
    this.end = to;
  }

  @Override
  protected Map<String, Long> compute() {

    if (end - start == 1) {

      return calc(fc[start]);

    } else {

      int mid = (start + end) / 2;
      MR mr1 = new MR(fc, start, mid);

      mr1.fork();
      MR mr2 = new MR(fc, mid, end);

      // 计算子任务，并返回合并的结果
      return merge(mr2.compute(), mr1.join());
    }
  }

  // 合并结果
  private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {

    Map<String, Long> result = new HashMap<>();
    result.putAll(r1);

    // 合并结果
    r2.forEach(
        (k, v) -> {
          Long c = result.get(k);

          if (c != null) result.put(k, c + v);
          else result.put(k, v);
        });

    return result;
  }

  // 统计单词数量
  private Map<String, Long> calc(String line) {

    Map<String, Long> result = new HashMap<>();

    // 分割单词
    String[] words = line.split("\\s+");

    // 统计单词数量
    for (String w : words) {

      Long v = result.get(w);

      if (v != null) result.put(w, v + 1);
      else result.put(w, 1L);
    }

    return result;
  }

  public static void main(String[] args) {

    String[] fc = {"hello world", "hello me", "hello fork", "hello join", "fork join in world"};

    // 创建ForkJoin线程池
    ForkJoinPool fjp = new ForkJoinPool(3);

    // 创建任务
    MR mr = new MR(fc, 0, fc.length);

    // 启动任务
    Map<String, Long> result = fjp.invoke(mr);

    // 输出结果
    result.forEach((k, v) -> System.out.println(k + ":" + v));
  }
}
