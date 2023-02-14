package com.github.zhuyaotong.p3c;

public class IfReturn {
    public void findBoyfriend(Man man) {
        if (man.isUgly()) {
            System.out.println("本姑娘是外貌协会的资深会员");
            return;
        }
        if (man.isPoor()) {
            System.out.println("贫贱夫妻百事哀");
            return;
        }
        if (man.isBadTemper()) {
            System.out.println("银河有多远，你就给我滚多远");
            return;
        }
        System.out.println("可以先交往一段时间看看");
    }

    public static void main(String[] args) {
        new IfReturn().findBoyfriend(new Man());
    }
}

class Man {
    public boolean isUgly() {
        return false;
    }

    public boolean isPoor() {
        return false;
    }

    public boolean isBadTemper() {
        return false;
    }
}
