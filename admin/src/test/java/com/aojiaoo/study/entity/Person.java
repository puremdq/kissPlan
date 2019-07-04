package com.aojiaoo.study.entity;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class Person {
    private String id;
    private String name;
    private Integer age;
    private int ingAge;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(String id, String name, Integer age, int ingAge) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ingAge = ingAge;
    }

    public Person() {
    }
    public static Object getFieldValue(Object object, Field field) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) throws Exception {

        Person person = new Person(null, "null", 22, 22);



        String a="dsds";

        Field[] fields = Person.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println();
            System.out.println(field.getType().getName() + " " + field.getName() + " " +
                    field.getType().isPrimitive() + " " + getFieldValue(a,field));
        }

    }
}
