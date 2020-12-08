package com.yan.study.gitstudydemo.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyTestLambada {
    public static void main(String[] args) {
        //匿名内部类
        test(new MyInterface<String, List>() {
            @Override
            public List stragety(String s, List list) {
                list.add(s);
                return list;
            }
        });
    }

    public static  void test(MyInterface<String, List> inter){
        List<String> result = inter.stragety("yan",new ArrayList());
        System.out.println("99");

    }
}

@FunctionalInterface
interface MyInterface<T,R>{

    R stragety(T t,R r);
}