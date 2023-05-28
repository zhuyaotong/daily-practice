package com.github.zhuyaotong.demo;

public class TypeParameter {}

/**
 * Generic version of the Car class.
 *
 * @param <T> the type of the value
 */
class Car<T> {

  // T stands for "Type"
  private T t;

  public void set(T t) {
    this.t = t;
  }

  public T get() {
    return t;
  }
}

// class Util {
//  // Generic static method
//  public static <K, V, Z, Y> boolean compare(Pair<K, V> p1, Pair<Z, Y> p2) {
//    return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
//  }
// }

class Pair<K, V> {

  private K key;
  private V value;

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }
}

// class MyClass<T> {
//  private T myMethod(T a) {
//    return a;
//  }
// }

class Util<K, V, Z, Y> {
  // Generic static method
  //  public static boolean compare(Pair<K, V> p1, Pair<Z, Y> p2) {
  //    return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
  //  }
}

class MyClass<T> {

  // Type declaration <T> already done at class level
  private T myMethod(T a) {
    return a;
  }

  // <T> is overriding the T declared at Class level;
  // So There is no ClassCastException though a is not the type of T declared at MyClass<T>.
  //  private <T> T myMethod1(Object a) {
  //    return (T) a;
  //  }

  // Runtime ClassCastException will be thrown if a is not the type T (MyClass<T>).
  private T myMethod1(Object a) {
    return (T) a;
  }

  // No ClassCastException
  // MyClass<String> obj= new MyClass<String>();
  // obj.myMethod2(Integer.valueOf("1"));
  // Since type T is redefined at this method level.
  private <T> T myMethod2(T a) {
    return a;
  }

  // No ClassCastException for the below
  // MyClass<String> o = new MyClass<String>();
  // o.myMethod3(Integer.valueOf("1").getClass())
  // Since <T> is undefined within this method;
  // And MyClass<T> don't have impact here
  //  private <T> T myMethod3(Class a) {
  //    return (T) a;
  //  }

  // ClassCastException for o.myMethod3(Integer.valueOf("1").getClass())
  // Should be o.myMethod3(String.valueOf("1").getClass())
  private T myMethod3(Class a) {
    return (T) a;
  }

  // Class<T> a :: a is Class object of type T
  // <T> is overriding of class level type declaration;
  private <T> Class<T> myMethod4(Class<T> a) {
    return a;
  }
}
