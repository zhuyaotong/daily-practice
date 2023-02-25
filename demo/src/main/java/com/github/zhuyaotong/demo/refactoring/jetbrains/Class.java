package com.github.zhuyaotong.demo.refactoring.jetbrains;

// File Class.java
//public class Class {
//    public int varInt;
//    private double varDouble;
//    public static final int CONSTANT = 0;
//
//    public void publicMethod() {
//        ...
//    }
//
//    public void hiddenMethod() {
//        ...
//    }
//
//    public void setVarDouble(double var) {
//        this.varDouble = var;
//    }
//
//    public double getVarDouble() {
//        return varDouble;
//    }
//}

// File Class.java
//public class Class extends SuperClass {
//    public int varInt;
//
//    public void publicMethod() {
////    ...
//    }
//
//    public void hiddenMethod() {
//        //  ...
//    }
//}
//
//// NEW file SuperClass.java
//abstract class SuperClass {
//    private double varDouble;
//    public static final int CONSTANT = 0;
//
//    public abstract void publicMethod();
//
//    public void setVarDouble(double var) {
//        this.varDouble = var;
//    }
//
//    public double getVarDouble() {
//        return varDouble;
//    }
//}

// File Class.java
//public class Class extends SuperClass {
//    public int varInt;
//
//    public void openMethod() {
////        ...
//    }
//}
//
//// File SuperClass.java
//abstract class SuperClass {
//    public static final int CONSTANT = 0;
//
//    public abstract void openMethod();
//
//    public void secretMethod() {
////        ...
//    }
//}

// File Class.java
//public class Class {
//    public int varInt;
//    private final MySuperClass superClass = new MySuperClass();
//
//    public SuperClass getSuperClass() {
//        return superClass;
//    }
//
//    public void openMethod() {
//        superClass.openMethod(); // Replace inheritance with delegation
//    }
//
//    private class MySuperClass extends SuperClass {
//        public void openMethod() {
////            ...
//        }
//    }
//}
//
//// File SuperClass.java UNCHANGED
//abstract class SuperClass {
//    public static final int CONSTANT = 0;
//
//    public abstract void openMethod();
//
//    public void secretMethod() {
////        ...
//    }
//}

// File Class.java
//public class Class extends SuperClass {
//    public void publicMethod() {
//
//    }
//
//    public void hiddenMethod() {
//    }
//}


// File SuperClass.java
//abstract class SuperClass {
//    public abstract void publicMethod();
//}

// Pull Members Up Example
// File Class.java
//public class Class extends SuperClass {
//    public void publicMethod() {
//
//    }
//}

// File SuperClass.java
//abstract class SuperClass {
//    public abstract void publicMethod();
//
//    public void hiddenMethod() {
//
//    }
//}

// Pull Members Down Example
// File Class.java
public class Class extends SuperClass {
    public void publicMethod() {

    }

    public void hiddenMethod() {

    }
}

// File SuperClass.java
abstract class SuperClass {
    public abstract void publicMethod();

}

class Fooo {
    public static final int MAX_PASSWORD_SIZE = 7;

    public void setPassword(String password) throws InvalidArgumentException {
        if (password.length() > MAX_PASSWORD_SIZE) {
            throw new InvalidArgumentException("password");
        }
    }
}

class SplitTemporaryVariable {
    public static void main(String[] args) {
//        double temp = 2 * (height + width);
//        System.out.println(temp);
//        temp = height * width;
//        System.out.println(temp);

//        final double perimeter = 2 * (height + width);
//        System.out.println(perimeter);
//        final double area = height * width;
//        System.out.println(area);
    }

//    int discount(int inputVal, int quantity) {
//        if (quantity > 50) {
//            inputVal -= 2;
//        }
//        // ...
//    }
//
//    int discount(int inputVal, int quantity) {
//        int result = inputVal;
//        if (quantity > 50) {
//            result -= 2;
//        }
//        // ...
//    }

}

class ReplaceTempWithQuery {
//    double calculateTotal() {
//        double basePrice = quantity * itemPrice;
//        if (basePrice > 1000) {
//            return basePrice * 0.95;
//        } else {
//            return basePrice * 0.98;
//        }
//    }

//    double calculateTotal() {
//        if (basePrice() > 1000) {
//            return basePrice() * 0.95;
//        } else {
//            return basePrice() * 0.98;
//        }
//    }
//
//    double basePrice() {
//        return quantity * itemPrice;
//    }
}

class PizzaDelivery {
    // ...
//    int getRating() {
//        return moreThanFiveLateDeliveries() ? 2 : 1;
//    }

    // Inline method
//    int getRating() {
//        return numberOfLateDeliveries > 5 ? 2 : 1;
//    }

//    boolean moreThanFiveLateDeliveries() {
//        return numberOfLateDeliveries > 5;
//    }


}

class ConsolidateConditionalExpression {
//    double disabilityAmount() {
//        if (seniority < 2) {
//            return 0;
//        }
//        if (monthsDisabled > 12) {
//            return 0;
//        }
//        if (isPartTime) {
//            return 0;
//        }
//        // Compute the disability amount.
//        // ...
//    }

//    double disabilityAmount() {
//        if (isNotEligibleForDisability()) {
//            return 0;
//        }
//        // Compute the disability amount.
//        // ...
//    }
}

class ReplaceNestedConditionalWithGuardClauses {
//    public double getPayAmount() {
//        double result;
//        if (isDead) {
//            result = deadAmount();
//        } else {
//            if (isSeparated) {
//                result = separatedAmount();
//            } else {
//                if (isRetired) {
//                    result = retiredAmount();
//                } else {
//                    result = normalPayAmount();
//                }
//            }
//        }
//        return result;
//    }

//    public double getPayAmount() {
//        if (isDead) {
//            return deadAmount();
//        }
//        if (isSeparated) {
//            return separatedAmount();
//        }
//        if (isRetired) {
//            return retiredAmount();
//        }
//        return normalPayAmount();
//    }
}

class ReplaceConditionalWithPolymorphism {
//    class Bird {
//        // ...
//        double getSpeed() {
//            switch (type) {
//                case EUROPEAN:
//                    return getBaseSpeed();
//                case AFRICAN:
//                    return getBaseSpeed() - getLoadFactor() * numberOfCoconuts;
//                case NORWEGIAN_BLUE:
//                    return (isNailed) ? 0 : getBaseSpeed(voltage);
//            }
//            throw new RuntimeException("Should be unreachable");
//        }
//    }

//    abstract class Bird {
//        // ...
//        abstract double getSpeed();
//    }
//
//    class European extends Bird {
//        double getSpeed() {
//            return getBaseSpeed();
//        }
//    }
//
//    class African extends Bird {
//        double getSpeed() {
//            return getBaseSpeed() - getLoadFactor() * numberOfCoconuts;
//        }
//    }
//
//    class NorwegianBlue extends Bird {
//        double getSpeed() {
//            return (isNailed) ? 0 : getBaseSpeed(voltage);
//        }
//    }

// Somewhere in client code
//    speed =bird.getSpeed();
}

class DecomposeConditional {
    public static void main(String[] args) {
//        if (date.before(SUMMER_START) || date.after(SUMMER_END)) {
//            charge = quantity * winterRate + winterServiceCharge;
//        } else {
//            charge = quantity * summerRate;
//        }
//
//        if (isSummer(date)) {
//            charge = summerCharge(quantity);
//        } else {
//            charge = winterCharge(quantity);
//        }
    }
}
