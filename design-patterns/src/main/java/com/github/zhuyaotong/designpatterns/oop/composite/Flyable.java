package com.github.zhuyaotong.designpatterns.oop.composite;

//public interface Flyable {
//    void fly();
//}
//
interface Tweetable {
    void tweet();
}

interface EggLayable {
    void layEgg();
}
//
//class Ostrich implements Tweetable, EggLayable { //鸵鸟
//    //... 省略其他属性和方法...
//    @Override
//    public void tweet() {
//        //...
//    }
//
//    @Override
//    public void layEgg() {
//        //...
//    }
//}
//
//class Sparrow implements Flyable, Tweetable, EggLayable { //麻雀
//    //... 省略其他属性和方法...
//    @Override
//    public void fly() {
//        //...
//    }
//
//    @Override
//    public void tweet() {
//        //...
//    }
//
//    @Override
//    public void layEgg() {
//        //...
//    }
//}

public interface Flyable {
    void fly();
}

class FlyAbility implements Flyable {
    @Override
    public void fly() { //...
    }
}

class TweetAbility implements Tweetable {
    @Override
    public void tweet() {
        //...
    }
}

class EggLayAbility implements EggLayable {
    @Override
    public void layEgg() {
        //...
    }
}

class Ostrich implements Tweetable, EggLayable { //鸵鸟
    private TweetAbility tweetAbility = new TweetAbility(); //组合
    private EggLayAbility eggLayAbility = new EggLayAbility(); //组合

    //... 省略其他属性和方法...
    @Override
    public void tweet() {
        tweetAbility.tweet(); // 委托
    }

    @Override
    public void layEgg() {
        eggLayAbility.layEgg(); // 委托
    }
}