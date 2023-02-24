package com.github.zhuyaotong.demo.refactoring.temp;

public class Foo {
    Bar bar;

    public Bar getbar() {
        return bar;
    }
}

class Bar {
    private Foo impValue1;

    public Bar(Foo impValue) {
        impValue1 = impValue;
    }

    public Foo getImpValue() {
        return impValue1;
    }
}

class Client {
    Foo a;
    Foo impValue = a.getbar().getImpValue();
}

//class Order {
//    // constructor etc...
//    private String customerName;
//
//    public String getCustomerName() {
//        return customerName;
//    }
//}

//class Customer {
//    // constructor etc...
//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
//
//class Order {
//    // constructor etc...
//    private Customer customer;
//
//    public String getCustomerName() {
//        return customer.getName();
//    }
//}
