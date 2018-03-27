package com.owen.model;

import java.net.SocketPermission;
import java.util.Date;

/**
 * Created by huang_b on 2018/3/27.
 */
public class Person {
    private String name;
    private Date birthDay;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(String name) {

    }

    protected Person(String name, String age) {

    }

    private Person(String name, String age, Date birthDay) {
        this.age = age;
        this.name = name;
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

    public void eat(String foodName) {
        System.out.println(name + "  eating  " + foodName);
    }
}
