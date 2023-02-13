package com.github.zhuyaotong.p3c;

class LazyInitDemo {
    private volatile Helper helper = null;

    public Helper getHelper() {
        if (helper == null) {
            synchronized (this) {
                if (helper == null) {
                    helper = new Helper();
                }
            }
        }
        return helper;
    }
    // other methods and fields...
}

class Helper {
}
