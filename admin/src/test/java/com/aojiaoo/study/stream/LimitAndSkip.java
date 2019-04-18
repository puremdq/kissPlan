package com.aojiaoo.study.stream;

import com.aojiaoo.study.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LimitAndSkip {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void before() {

    }

    /**
     * limit 返回 Stream 的前面 n 个元素，skip 则是扔掉前 n 个元素
     */
    @Test
    public void TestLimitAndSkip() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "tom"));
        persons.add(new Person("2", "jan"));
        persons.add(new Person("3", "jack"));
        persons.add(new Person("4", "kate"));
        persons.add(new Person("5", "pure"));

        int page = 2;
        int pageSize = 3;
        //limit (1,2) (page-1)*pageSize
        List<Person> result1 = persons.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        logger.debug("{}", result1);
    }


    @After
    public void after() {

    }
}
