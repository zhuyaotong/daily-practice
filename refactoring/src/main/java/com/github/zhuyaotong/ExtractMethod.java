package com.github.zhuyaotong;

public class ExtractMethod {

    public ExtractMethod(String name) {
        this.name = name;
    }

    private String name;

//    void printOwing() {
//        printBanner();
//
//        // Print details.
//        System.out.println("name: " + name);
//        System.out.println("amount: " + getOutstanding());
//    }

    void printOwing() {
        printBanner();
        printDetails(getOutstanding());
    }

    void printDetails(double outstanding) {
        System.out.println("name: " + name);
        System.out.println("amount: " + outstanding);
    }

    private double getOutstanding() {
        return 100.0000;
    }

    private void printBanner() {
        System.out.println("banner...");
    }

    public static void main(String[] args) {
        new ExtractMethod("Extract Method").printOwing();
    }

}
