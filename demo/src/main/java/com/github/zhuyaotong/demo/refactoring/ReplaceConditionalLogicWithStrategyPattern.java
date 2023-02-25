package com.github.zhuyaotong.demo.refactoring;

public class ReplaceConditionalLogicWithStrategyPattern {
}

class IfElseDemo {

    private InsuranceStrategy strategy;

    public double calculateInsurance(double income) {
        if (income <= 10000) {
            strategy = new InsuranceStrategyLow();
            return strategy.calculateInsurance(income);
        } else if (income <= 30000) {
            strategy = new InsuranceStrategyMedium();
            return strategy.calculateInsurance(income);
        } else if (income <= 60000) {
            strategy = new InsuranceStrategyHigh();
            return strategy.calculateInsurance(income);
        } else {
            strategy = new InsuranceStrategyVeryHigh();
            return strategy.calculateInsurance(income);
        }
    }

    public static void main(String[] args) {
        System.out.println(new IfElseDemo().calculateInsurance(5000));
    }
}

abstract class InsuranceStrategy {

    public double calculateInsurance(double income) {
        return (income - getAdjustment()) * getWeight() + getConstant();
    }

    public abstract int getConstant();

    public abstract double getWeight();

    public abstract int getAdjustment();
}


class InsuranceStrategyVeryHigh extends InsuranceStrategy {

    public int getConstant() {
        return 105600;
    }

    public double getWeight() {
        return 0.02;
    }

    public int getAdjustment() {
        return 60000;
    }
}

class InsuranceStrategyHigh extends InsuranceStrategy {

    public int getConstant() {
        return 76500;
    }

    public double getWeight() {
        return 0.1;
    }

    public int getAdjustment() {
        return 30000;
    }
}

class InsuranceStrategyMedium extends InsuranceStrategy {
    @Override
    public int getConstant() {
        return 35600;
    }

    @Override
    public double getWeight() {
        return 0.2;
    }

    @Override
    public int getAdjustment() {
        return 10000;
    }
}

class InsuranceStrategyLow extends InsuranceStrategy {
    @Override
    public int getConstant() {
        return 0;
    }

    @Override
    public double getWeight() {
        return 0.365;
    }

    @Override
    public int getAdjustment() {
        return 0;
    }
}
