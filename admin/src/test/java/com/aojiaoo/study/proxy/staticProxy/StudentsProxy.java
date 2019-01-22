package com.aojiaoo.study.proxy.staticProxy;

/**
 * 学生代理类，也实现了Person接口，保存一个学生实体，这样既可以代理学生产生行为
 *
 * @author Gonjan
 */
public class StudentsProxy implements Person {
    //被代理的学生
    private Student stu = null;

    public StudentsProxy(Person stu) {
        // 只代理学生对象
        System.out.println("为" + stu.getClass() + "创建代理对象" + StudentsProxy.class.getName());
        if (stu.getClass() == Student.class) {
            this.stu = (Student) stu;
        }
    }

    //代理上交班费，调用被代理学生的上交班费行为
    public void giveMoney() {
        System.out.println("运行前");
        stu.giveMoney();
        System.out.println("运行完成");
    }
}