package com.github.zhuyaotong.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

  public static void main(String[] args) {

    // 创建目标对象StudentDao
    IStudentDao stuDAO = new StudentDao();

    // 创建MyInvocationHandler对象
    InvocationHandler handler = new MyInvocationHandler(stuDAO);

    // 使用Proxy.newProxyInstance动态的创建代理对象stuProxy
    IStudentDao stuProxy =
        (IStudentDao)
            Proxy.newProxyInstance(
                stuDAO.getClass().getClassLoader(), stuDAO.getClass().getInterfaces(), handler);

    // 动用代理对象的方法
    stuProxy.save();

    stuProxy.hello();
  }
}

class MyInvocationHandler implements InvocationHandler {

  private Object obj;

  public MyInvocationHandler(Object obj) {
    this.obj = obj;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    System.out.println("开始事务");
    Object result = method.invoke(obj, args);
    System.out.println("结束事务");

    return result;
  }
}
// 抽象接口
interface IStudentDao {

  void save();

  void hello();
}

// 目标对象
class StudentDao implements IStudentDao {

  public void save() {
    System.out.println("保存成功");
  }

  public void hello() {
    System.out.println("你好！");
  }
}
