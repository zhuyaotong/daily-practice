package com.github.zhuyaotong.designpatterns.oop.composite;


public abstract class AbstractBird {
    //...省略其他属性和方法...
    public void fly() {
        //...
    }
}

//class Ostrich extends AbstractBird { //鸵鸟
//    //...省略其他属性和方法...
//    public void fly() {
//        throw new UnSupportedMethodException("I can't fly.'");
//    }
//}

class UnSupportedMethodException extends RuntimeException {
    public UnSupportedMethodException(String message) {
        super(message);
    }
}
