package com.github.zhuyaotong.thinkinginjava;

public class PrimitiveDateTypes {
    public static void main(String[] args) {
        char c = 'x';
        Character ch = new Character(c);
        ch = new Character('x');
        ch = 'x';
        c = new Character('y');
    }
}

class Scope {
    public static void main(String[] args) {
        {
            int x = 666;
            {
//                int x = 888;
                int q = 96;

                int r = x;
            }
//            q = x;
        }
    }
}

class Static {
    static int i = 47;

    public static void main(String[] args) {
        Static s1 = new Static();
        Static s2 = new Static();
        System.out.println(s1.i == s2.i && s2.i == Static.i);
    }
}

class InstanceOf {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(((Object) "你好吗".length()) instanceof Integer);

        System.out.println(Class.forName("java.lang.String").isInstance(0.05d));
        System.out.println(Class.forName("java.lang.String").isInstance("0.05d"));

        System.out.println(Hello.class instanceof Object);
        System.out.println(new Hello() instanceof Object);

        System.out.println(new Hi() instanceof Hello);
        System.out.println(new Hello() instanceof Hello);
        System.out.println(new Hello() instanceof Hi);

        System.out.println(Class.forName("com.github.zhuyaotong.thinkinginjava.Hello").isInstance(Hello.class));
        System.out.println(Class.forName("com.github.zhuyaotong.thinkinginjava.Hello").isInstance(new Hello()));
        System.out.println(Class.forName("com.github.zhuyaotong.thinkinginjava.Hello").isInstance(new Hi()));
        System.out.println(Class.forName("com.github.zhuyaotong.thinkinginjava.Hi").isInstance(new Hi()));
        System.out.println(Class.forName("com.github.zhuyaotong.thinkinginjava.Hi").isInstance(new Hello()));
        System.out.println(Class.forName("java.lang.Object").isInstance(new Hello()));

        System.out.println(OneInterface.class instanceof Object);
        System.out.println(new OneInterfaceImpl() instanceof OneInterface);
    }
}

class Hello {

}

class Hi extends Hello {

}

interface OneInterface {

}

class OneInterfaceImpl implements OneInterface {
}
