package com.github.zhuyaotong.concurrency;

// 以下代码来源于【参考1】
class VolatileExample {
    int x = 0;
    volatile boolean v = false;
    private static int var = 0;

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if (v == true) {
            // 这里x会是多少呢？
        }

        synchronized (this) { //此处自动加锁
            // x是共享变量,初始值=10
            if (this.x < 12) {
                this.x = 12;
            }
        } //此处自动解锁
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileExample ve = new VolatileExample();
        System.out.println("x = " + ve.x);
        ve.writer();
        System.out.println("x = " + ve.x);
        ve.reader();
        System.out.println("x = " + ve.x);


        Thread B = new Thread(() -> {
            // 主线程调用B.start()之前
            // 所有对共享变量的修改，此处皆可见
            // 此例中，var==77
        });
        // 此处对共享变量var修改
        var = 77;
        // 主线程启动子线程
        B.start();


        Thread c = new Thread(() -> {
            // 此处对共享变量var修改
            var = 66;
        });
        // 例如此处对共享变量修改，
        // 则这个修改结果对线程c可见
        // 主线程启动子线程
        c.start();
        c.join();
        // 子线程所有对共享变量的修改
        // 在主线程调用c.join()之后皆可见
        // 此例中，var==66
    }
}

class X {
    // 修饰非静态方法
    synchronized void foo() {
        // 临界区
    }

    // 修饰静态方法
    synchronized static void bar() {
        // 临界区
    }

    // 修饰代码块
    Object obj = new Object();

    void baz() {
        synchronized (obj) {
            // 临界区
        }
    }
}

class Account {
    // 锁：保护账户余额
    private final Object balLock = new Object();
    // 账户余额
    private Integer balance;
    // 锁：保护账户密码
    private final Object pwLock = new Object();
    // 账户密码
    private String password;

    // 取款
    void withdraw(Integer amt) {
        synchronized (balLock) {
            if (this.balance > amt) {
                this.balance -= amt;
            }
        }
    }

    // 查看余额
    Integer getBalance() {
        synchronized (balLock) {
            return balance;
        }
    }

    // 更改密码
    void updatePassword(String pw) {
        synchronized (pwLock) {
            this.password = pw;
        }
    }

    // 查看密码
    String getPassword() {
        synchronized (pwLock) {
            return password;
        }
    }
}

class AccountB {
    private Object lock;
    private int balance;

    private AccountB() {
    }

    // 创建Account时传入同一个lock对象
    public AccountB(Object lock) {
        this.lock = lock;
    }

    // 转账
    void transfer(AccountB target, int amt) {
        // 此处检查所有对象共享的锁
        synchronized (lock) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}

class AccountC {
    private int balance;

    // 转账
    void transfer(AccountC target, int amt) {
        synchronized (AccountC.class) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
