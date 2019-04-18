package com.aojiaoo.study.stream;

import com.aojiaoo.study.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedAndForEach {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void before() {

    }

    /**
     * limit 返回 Stream 的前面 n 个元素，skip 则是扔掉前 n 个元素
     */
    @Test
    public void TestSortedAndForEach() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "tom"));
        persons.add(new Person("3", "jan"));
        persons.add(new Person("5", "jack"));
        persons.add(new Person("2", "kate"));
        persons.add(new Person("4", "pure"));

        persons.stream().sorted(Comparator.comparing(Person::getId)).forEach(x -> logger.debug("current x is:{}", x));
    }


    @After
    public void after() {

    }
}
