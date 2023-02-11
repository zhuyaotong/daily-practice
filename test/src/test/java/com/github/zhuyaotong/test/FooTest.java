package com.github.zhuyaotong.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FooTest {
    private FooDependency fooDependency;

    @Before
    public void setUp() {
        fooDependency = new FooDependency();
    }

    // Don't do this... the test is too brittle
//    @Test
//    public void foo_three() {
//        final Logger logger = mock(Logger.class);
//        Foo foo = new Foo(logger);
//        assertEquals(Foo.DEFAULT_VALUE, foo.bar(3));
//        verify(logger).debug("Returning default value for: 3");
//    }

    @Test
    public void foo_seven() throws Exception {
        assertEquals(-1, new Foo().foo(7));
    }

    // Do this instead
//    @Test(expected = IOException.class)
//    public void foo_nine() throws Exception {
//        new Foo().foo(9);
//    }
}
