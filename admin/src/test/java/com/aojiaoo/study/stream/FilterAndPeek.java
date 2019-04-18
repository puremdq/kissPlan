package com.aojiaoo.study.stream;

import com.aojiaoo.study.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class FilterAndPeek {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void before() {

    }

    /**
     * filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。 （入参 Predicate）
     */
    @Test
    public void TestFilter() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "tom"));
        persons.add(new Person("2", "jan"));
        persons.add(new Person("3", "jack"));

        List<Person> result1 = persons.stream().filter(x -> x.getId().compareTo("2") <= 0).collect(Collectors.toList());
        logger.debug("test steam Map end the result1 is {}", result1);
    }

    /**
     * peek 方法我们可以拿到元素，然后做一些其他事情。（入参 Consumer）
     */
    @Test
    public void TestPeek() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "tom"));
        persons.add(new Person("2", "jan"));
        persons.add(new Person("3", "jack"));

        Set<String> idSet = new HashSet<>();

//        List<Person> result1 = persons.stream().filter(x -> x.getId().compareTo("2") <= 0).peek(x -> {
//            idSet.add(x.getId());
//        }).collect(Collectors.toList());

        List<Person> result2 = persons.stream().filter(x -> x.getId().compareTo("2") <= 0).peek(x -> x.setName("ha")).collect(Collectors.toList());


        logger.debug("test steam method {} end the idSet is {}", "peek", idSet);
        logger.debug("test steam method {} end the result is {}", "peek", result2);
    }

    @After
    public void after() {

    }
}
