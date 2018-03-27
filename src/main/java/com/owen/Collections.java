package com.owen;


import com.owen.model.Person;

import java.util.*;

/*
* java中各种集合的操作
*
* 参考文章 https://www.cnblogs.com/leeplogs/p/5891861.html]
*
* 和net 比较起来 就是  遍历的时候  需要先拿到迭代器 iterator  还有就是 种类比较多
* */
public class Collections {

    public static void main(String[] args) {
        operationMap();
        operationList();
    }

    /*
    * map 和 .net 里面的dictionary 类似
    * */
    private static void operationMap() {
        Map map = new HashMap<String, Person>();
        Person p = new Person("bob");
        p.setAge("18");
        map.put("bob", p);
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.println("键" + e.getKey() + "的值为" + e.getValue());
        }
    }

    private static void operationList() {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("bob"));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Person p = (Person) it.next();
            System.out.println(p);
        }
    }
}
