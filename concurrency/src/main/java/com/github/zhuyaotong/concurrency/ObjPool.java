package com.github.zhuyaotong.concurrency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Semaphore;
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
