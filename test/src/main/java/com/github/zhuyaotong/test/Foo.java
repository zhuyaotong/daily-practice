package com.github.zhuyaotong.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

final class Foo {
    private static final Logger logger = LoggerFactory.getLogger(Foo.class);
    static int DEFAULT_VALUE = 100;
    // Members and other methods removed for brevity.

    public int bar(final int i) {
        if (shouldReturnDefaultValue(i)) {
            logger.debug("Returning default value for: " + i);
            return DEFAULT_VALUE;
        } else {
            logger.debug("Returning calculated value for: " + i);
            return calculateValue(i);
        }
    }

    private int calculateValue(int i) {
        return 999;
    }

    private boolean shouldReturnDefaultValue(int i) {
        return true;
    }

    int foo(int i) throws IOException {
//        throw new IOException("故意的");
        return -1;
    }
}
