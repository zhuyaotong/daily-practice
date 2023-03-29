package com.github.zhuyaotong.demo.reflect;

import java.lang.reflect.*;

public class ObtainingInformationAboutConstructors {
    public ObtainingInformationAboutConstructors() {
    }

    protected ObtainingInformationAboutConstructors(int i, double d) {
    }

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("com.github.zhuyaotong.demo.reflect.ObtainingInformationAboutConstructors");
            Constructor ctorlist[] = cls.getDeclaredConstructors();

            for (int i = 0; i < ctorlist.length; i++) {
                Constructor ct = ctorlist[i];
                System.out.println("name  = " + ct.getName());
                System.out.println("decl class = " + ct.getDeclaringClass());

                Class pvec[] = ct.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);

                Class evec[] = ct.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);

                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
