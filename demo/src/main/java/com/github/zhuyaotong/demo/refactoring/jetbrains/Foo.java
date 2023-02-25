package com.github.zhuyaotong.demo.refactoring.jetbrains;

// Before refactoring
//class Foo {
//    public Foo() {
//        // instance initialization
//    }
//}

import java.io.File;

// After refactoring
class Foo {
    private Foo() {
        // instance initialization
    }

    public static Foo createFoo() {
        return new Foo();
    }
}

//class Shape
//{
//    public void Draw()
//    {
//        try { /*draw*/ }
//        catch (Exception e) { LogError(e); }
//    }
//    public static void LogError(Exception e)
//    {
////        File.WriteAllText(@"c:\Errors\Exception.txt", e.ToString());
//    }
//}

class Shape {
    public void Draw() {
        try { /*draw*/ } catch (Exception e) {
            Logger.LogError(e); // Extract Class
        }
    }
}

class Logger {
    public static void LogError(Exception e) {
//        File.WriteAllText(@"c:\Errors\Exception.txt", e.ToString());
    }
}