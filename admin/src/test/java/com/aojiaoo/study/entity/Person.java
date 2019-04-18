package com.aojiaoo.study.entity;

import lombok.Data;

@Data
public class Person {
    private String id;
    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }
}
