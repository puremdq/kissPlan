package com.aojiaoo.study.mybatis;

public class SelectMethodProvider {
    public String select(Object params) {

        System.out.println(params);
        return "select * from a_test limit 1";
    }
}