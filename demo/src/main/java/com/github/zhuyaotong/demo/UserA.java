package com.github.zhuyaotong.demo;

// 普通不变模式
public class UserA {
    private String name;
    private int age;
    private AddressA addr;

    public UserA(String name, int age, AddressA addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }
    // 只有getter方法，无setter方法...
}

class AddressA {
    private String province;
    private String city;

    public AddressA(String province, String city) {
        this.province = province;
        this.city = city;
    }
    // 有getter方法，也有setter方法...
}

// 深度不变模式
class UserB {
    private String name;
    private int age;
    private AddressB addr;

    public UserB(String name, int age, AddressB addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }
    // 只有getter方法，无setter方法...
}

class AddressB {
    private String province;
    private String city;

    public AddressB(String province, String city) {
        this.province = province;
        this.city = city;
    }
    // 有getter方法，无setter方法...
}