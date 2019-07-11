package com.hexi.kotlindemo.test.genericsJava;

/**
 * Created by hexi on 2017/7/12.
 */

public class TestSource {
    public void demo(Source<String> strs) {
        Source<? extends Object> objects = strs;
    }
}
