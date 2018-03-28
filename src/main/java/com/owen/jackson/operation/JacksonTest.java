package com.owen.jackson.operation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.owen.model.CommonRS;
import com.owen.model.Person;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by huang_b on 2018/3/28.
 *https://blog.csdn.net/sdyy321/article/details/40298081
 * @JsonValue 枚举是很有用
 * @JsonIgnore 忽略属性
 * @JsonProperty("name") 别名
 *
 *
 * 循环引用 天生不支持 http://www.weizhixi.com/user/index/article/id/64.html
 */
public class JacksonTest {
    public static void main(String[] args) throws IOException {
        Person person = new Person();
        person.setAge("18");
        person.setBirthDay(new Date());
        person.setName("bob");

        String json = toJson(person);
        person = toBean(json, Person.class);
        System.out.println(person.toString());
        List<Person> list = new ArrayList<Person>();

        for (int i = 0; i < 5; i++) {
            person.setName("bob" + i);
            list.add(person);
        }
        json = toJson(list);
        list = toListBean(json, Person.class);

        CommonRS<Person> commonRS = new CommonRS<Person>();
        commonRS.setData(person);

        json = toJson(commonRS);
        CommonRS<Person> commonRS1 = toGenericBean(json, commonRS.getClass(), person.getClass());
    }

    private static String toJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT,false);//格式化输出
        String json = objectMapper.writeValueAsString(o);
        System.out.println(json);
        return json;
    }

    private static <T> T toBean(String json, Class<T> tClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //当json中出现了实体中没有的 属性时 ，能匹配到的 继续
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        T t = objectMapper.readValue(json, tClass);
        t.toString();
        return t;
    }

    private static <T> List<T> toListBean(String json, Class<T> tClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        JavaType t = objectMapper.getTypeFactory().constructParametricType(
                List.class, tClass);
        List<T> objList = objectMapper.readValue(json, t);
        return objList;
    }

    private static <T> T toGenericBean(String json, Class<T> beanClass, Class<?> genericClass) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            JavaType bean = objectMapper.getTypeFactory().constructParametrizedType(beanClass, beanClass, genericClass);
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(bean));
            //return read.readValue(json, bean);

            //return read.readValue(json,new TypeReference<CommonRS<OrderDetailRS>>(){});
        } catch (IOException e) {
            throw new RuntimeException("JSON转对象出错：" + json, e);
        }

    }

}
