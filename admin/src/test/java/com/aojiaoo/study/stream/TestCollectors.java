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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestCollectors {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void before() {

    }

    /**
     * collect
     * 对元素进行收集
     */
    @Test
    public void TestCollect() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "tom"));
        persons.add(new Person("3", "jan"));
        persons.add(new Person("5", "jack"));
        persons.add(new Person("2", "kate"));
        persons.add(new Person("4", "pure"));

        List<Person> personList1 = persons.stream()
                .sorted(Comparator.comparing(Person::getId))
                .collect(java.util.stream.Collectors.toList());

        List<Person> personList2 = persons.stream()
                .sorted(Comparator.comparing(Person::getId))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        logger.debug("personList1 is: {}", personList1);
        logger.debug("personList2 is: {}", personList2);
    }

    /**
     * Collectors.toMap
     * 对元素进行收集
     */
    @Test
    public void TestCollectsToMap() {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "tom"));
        persons.add(new Person("3", "jan"));
        persons.add(new Person("5", "jack"));
        persons.add(new Person("2", "kate"));
        persons.add(new Person("4", "kate"));

//        Map<String, Person> personIdNameMap1 = persons.stream()
//                .collect(Collectors.toMap(x -> {
//                }));

        Map<String, Person> personIdNameMap2 = persons.stream()
                .collect(Collectors.toMap(Person::getId, Function.identity()));


//        logger.debug("personIdNameMap1 is: {}", personIdNameMap1);
        logger.debug("personIdNameMap2 is: {}", personIdNameMap2);
    }


    /**
     * Collectors.groupingBy
     * 分组
     */
    @Test
    public void TestGroupingBy() {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "tom", 15));
        persons.add(new Person("2", "jan", 18));
        persons.add(new Person("3", "jack", 18));
        persons.add(new Person("4", "kate1", 15));
        persons.add(new Person("5", "kate2", 12));

        Map<Integer, List<Person>> personAgeMap1 = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(personAgeMap1);


        Map<Integer, Map<String, Person>> personAgeMap2 = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge,
                        Collectors.toMap(Person::getId, Function.identity())));
        System.out.println(personAgeMap2);


    }


    @After
    public void after() {

    }
}
