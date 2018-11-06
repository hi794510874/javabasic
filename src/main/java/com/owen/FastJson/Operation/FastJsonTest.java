package com.owen.FastJson.Operation;

import com.alibaba.fastjson.JSON;
import com.owen.model.CommonRS;
import com.owen.model.Head;
import com.owen.model.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huang_b on 2018/3/28.
 */
public class FastJsonTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge("18");
        person.setBirthDay(new Date());
        person.setName("bob");
        String json = JSON.toJSONStringWithDateFormat(person, "yyy-MM-dd'T'HH:mm:ss.sssX");
        System.out.println(json);
        person = JSON.parseObject(json, person.getClass());
        System.out.println(person);

        List<Person> list = new ArrayList<Person>();

        for (int i = 0; i < 5; i++) {
            person.setName("bob" + i);
            list.add(person);
        }
        json = JSON.toJSONStringWithDateFormat(list, "yyy-MM-dd'T'HH:mm:ss.sssX");
        System.out.println(json);
        //json="[{\"name\":\"bob4\",\"birthDay\":\"2018-03-28 16:49:48.0048\",\"age\":\"18\"},{\"name\":\"bob4\",\"birthDay\":\"2018-03-28 16:49:48.0048\",\"age\":\"18\"},{\"name\":\"bob4\",\"birthDay\":\"2018-03-28 16:49:48.0048\",\"age\":\"18\"},{\"name\":\"bob4\",\"birthDay\":\"2018-03-28 16:49:48.0048\",\"age\":\"18\"},{\"name\":\"bob4\",\"birthDay\":\"2018-03-28 16:49:48.0048\",\"age\":\"18\"}]";
        list = JSON.parseObject(json, List.class);

        CommonRS<Person> commonRS = new CommonRS<Person>();
        commonRS.setHead(new Head());
        commonRS.setData(person);

        json = JSON.toJSONStringWithDateFormat(commonRS, "yyy-MM-dd'T'HH:mm:ss.sssX");
        System.out.println(json);

        commonRS = JSON.parseObject(json, commonRS.getClass());

        Father father = new Father();
        father.setName("father");
        List<Son> sonList = new ArrayList<Son>();
        for (int i = 0; i < 3; i++) {
            Son son = new Son();
            son.setName("dd" + i);
            son.setFather(father);
            sonList.add(son);
        }
        father.setSons(sonList);
        json = JSON.toJSONString(father);
        System.out.println(json);

        father = JSON.parseObject(json, father.getClass());

        json = JSON.toJSONString(sonList);
        System.out.println(json);
        sonList=JSON.parseObject(json,List.class);
    }


    static class Father {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Son> getSons() {
            return sons;
        }

        public void setSons(List<Son> sons) {
            this.sons = sons;
        }

        private String name;
        private List<Son> sons;


    }

    static class Son extends Father {
        private Father father;
        private String gender;

        public Father getFather() {
            return father;
        }

        public void setFather(Father father) {
            this.father = father;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
