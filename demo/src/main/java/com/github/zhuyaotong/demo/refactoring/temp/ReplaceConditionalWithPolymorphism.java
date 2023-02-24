package com.github.zhuyaotong.demo.refactoring.temp;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceConditionalWithPolymorphism {

}

//class Product {
//    String priceCode;
//    int discount;
//
//    Product(String priceCode) {
//        setDiscount(priceCode);
//    }
//
//    public int getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(String priceCode) {
//        switch (priceCode) {
//            case "CODE1":
////                discount = // some logic;
//            case "CODE2":
////                discount = // some other logic;
//            case "CODE3":
////                discount = // some other logic;
//        }
//    }
//}

//class Product {
//    String priceCode;
//    DiscountStrategy discountStrategy;
//
//    Product(String priceCode) {
//        setDiscount(priceCode);
//    }
//
//    public int getDiscount() {
//        return discountStrategy.getDiscount();
//    }
//
//    public void setDiscount(String priceCode) {
//        if (priceCode.equals("CODE1")) {
//            discountStrategy = new DiscountStrategy1();
//        } else if (priceCode.equals("CODE2")) {
//            discountStrategy = new DiscountStrategy2();
//        }
//        // ...
//    }
//}
//
interface DiscountStrategy {
    int getDiscount();
}
//
//class DiscountStrategy1 implements DiscountStrategy {
//    public int getDiscount() {
//        // calculate & return discount;
//    }
//}
//
//class DiscountStrategy2 implements DiscountStrategy {
//    public int getDiscount() {
//        // calculate & return discount;
//    }
//}
//
//class DiscountStrategy3 implements DiscountStrategy {
//    public int getDiscount() {
//        // calculate & return discount;
//    }
//}

class DiscountFactory {
    private static final Map<String, DiscountStrategy> strategies = new HashMap<>();
    private static final DiscountStrategy DEFAULT_STRATEGY = () -> 0;

    static {
        strategies.put("code1", () -> 10);
        strategies.put("code2", () -> 20);
    }

    public DiscountStrategy getDiscountStrategy(String priceCode) {
        if (!strategies.containsKey(priceCode)) {
            return DEFAULT_STRATEGY;
        }
        return strategies.get(priceCode);
    }
}

class Product {
    private DiscountStrategy discountStrategy;

    Product(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public int getDiscount() {
        return discountStrategy.getDiscount();
    }

    public static void main(String[] args) {
        DiscountFactory factory = new DiscountFactory();
        Product product = new Product(factory.getDiscountStrategy("code1"));
        System.out.println(product.getDiscount());
    }
}

interface BillingStrategy {
    double getActPrice(double rawPrice);
}

// Normal billing strategy (unchanged price)
class NormalStrategy implements BillingStrategy {
    @Override
    public double getActPrice(double rawPrice) {
        return rawPrice;
    }
}

// Strategy for Happy hour (50% discount)
class HappyHourStrategy implements BillingStrategy {
    @Override
    public double getActPrice(double rawPrice) {
        return rawPrice * 0.5;
    }
}

@Data
class OrderItem {
    public String Name;
    public double Price;
    public int Quantity;
    public BillingStrategy strategy;

    public OrderItem(String name, double price, int quantity, BillingStrategy strategy) {
        this.Name = name;
        this.Price = price;
        this.Quantity = quantity;
        this.strategy = strategy;
    }
}

class Order {
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private BillingStrategy strategy = new NormalStrategy();

    public void Add(String name, double price, int quantity, BillingStrategy strategy) {
        orderItems.add(new OrderItem(name, price, quantity, strategy));
    }

    // Payment of bill
    public void PayBill() {
        double sum = 0;
        for (OrderItem item : orderItems) {

            double actPrice = item.strategy.getActPrice(item.getPrice() * item.getQuantity());
            sum += actPrice;

            System.out.printf("%s -- %f(%d) - %f",
                    item.getName(), item.getPrice(), item.getQuantity(), actPrice);
        }
        System.out.println("Total due: " + sum);
    }
}
