package com.owen;

import com.owen.model.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by huang_b on 2018/3/27.
 * <p>
 * 例子 来源 https://blog.csdn.net/sinat_38259539/article/details/71799078
 * 反射
 */
public class Reflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Person> clazz = GetClassThreeMethod();

        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }
        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用 构造方法
        Object obj = con.newInstance();

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(String.class, String.class, Date.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance("Bob", "18", new Date());
        System.out.println(obj);
        ((Person) obj).eat("apple");
    }

    /*
    *  三种获取 反射对象  就是对象的对象
    *  在运行时  同一个类 只会产生 一个 反射对象
    * */
    private static Class GetClassThreeMethod() throws ClassNotFoundException {
        //第一种方式获取Class对象
        Person stu1 = new Person("bob");//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = Person.class;
        System.out.println(stuClass == stuClass2);//判断第一种

        Class stuClass3 = Class.forName("com.owen.model.Person");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
        System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
        return stuClass3;
    }
}
