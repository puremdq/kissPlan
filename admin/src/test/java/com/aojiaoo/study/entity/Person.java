package com.aojiaoo.study.entity;

import lombok.Data;

@Data
public class Person {
    private String id;
    private String name;
    private Integer age;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String id, String name,int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
