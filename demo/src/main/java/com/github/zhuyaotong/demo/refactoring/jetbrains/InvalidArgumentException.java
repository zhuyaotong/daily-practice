package com.github.zhuyaotong.demo.refactoring.jetbrains;

public class InvalidArgumentException extends Throwable {
    public InvalidArgumentException(String password) {
        super(password);
    }
}
