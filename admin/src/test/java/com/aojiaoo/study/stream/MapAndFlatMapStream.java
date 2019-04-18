package com.aojiaoo.study.stream;

import com.aojiaoo.study.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapAndFlatMapStream {

    private Logger logger = LoggerFactory.getLogger(MapAndFlatMapStream.class);

    @Before
    public void before() {

    }

    /**
     * map/flatMap
     * 对流中的每个元素执行一个函数，使得元素转换成另一种类型输出。
     * <p>
     * map 一对一 （入参 Function<T, R>）
     */
    @Test
    public void TestMap() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "tom"));
        persons.add(new Person("2", "jan"));
        persons.add(new Person("3", "jack"));

        List<String> result1 = persons.stream().map(Person::getId)
                .collect(Collectors.toList());


        List<Map<String, Object>> result2 = persons.stream().map(x -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", x.getName());
            map.put("id", x.getId());
            return map;
        }).collect(Collectors.toList());

        logger.debug("test steam Map end the result1 is {}", result1);
        logger.debug("test steam Map end the result2 is {}", result2);
    }


    /**
     * map/flatMap
     * 对流中的每个元素执行一个函数，使得元素转换成另一种类型输出。
     * <p>
     * flatMap 一对多 （入参 Function<T, ? extends Stream>）
     */
    @Test
    public void TestFlatMap() {

        List<List<Person>> persons = new ArrayList<>();
        List<Person> persons1 = new ArrayList<>();
        persons1.add(new Person("1", "tom"));
        persons1.add(new Person("2", "jan"));
        List<Person> persons2 = new ArrayList<>();
        persons2.add(new Person("3", "jack"));
        persons2.add(new Person("4", "lily"));
        persons.add(persons1);
        persons.add(persons2);

        List<Person> result1 = persons.stream().flatMap(x -> x.stream())
                .collect(Collectors.toList());

        List<Map<String, Object>> result2 = persons.stream().flatMap(x -> x.stream())
                .map(x -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", x.getId());
                    map.put("name", x.getName());
                    return map;
                }).collect(Collectors.toList());

        logger.debug("test steam Map end the result1 is {}", result1);
        logger.debug("test steam Map end the result2 is {}", result2);
    }


    @After
    public void after() {

    }
}
