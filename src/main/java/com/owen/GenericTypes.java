package com.owen;


import com.owen.model.Man;
import com.owen.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang_b on 2018/3/27.
 * <p>
 * 泛型
 */
public class GenericTypes {
    public static void main(String[] args) {
        typeCompare();


    }


    /*
    * 得到结论  泛型的类型是相同的
    * .net 里面泛型的 类型是不相同的
    * */
    private static void typeCompare() {
        /*
        * 注释的代码 编译不会通过 。  当涉及到泛型时， 尽管 man 是 Person 的子类型，但是 ArrayList<man> 不是 ArrayList<Person> 的子类型，泛型不支持协变
        * */
        // ArrayList<Person> flist = new ArrayList<Man>();

        Person[] person = new Man[10];//协变   .net 不支持

        /*
        * 有边界的通配符
        * */
        List<? extends Person> list = new ArrayList<Man>();//泛型通配符 可以实现这种类似协变

        // List<?> list 表示 list 是持有某种特定类型的 List，但是不知道具体是哪种类型。那么我们可以向其中添加对象吗？当然不可以，因为并不知道实际是哪种类型，所以不能添加任何类型，这是不安全的。而单独的 List list ，也就是没有传入泛型参数，表示这个 list 持有的元素的类型是 Object，因此可以添加任何类型的对象，只不过编译器会有警告信息。

        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("泛型的类型相同");
        }

    }
    /*
    * 限制 T 必须是从 person 继承的
    * */
    class Job<T extends Person> {
        private T worker;

        public void DoWork() {
            System.out.println(worker.getName() + "doing work");
        }

        public void GoToWork(T t) {

        }

        public T GoToWork() {

            return worker;
        }
    }


}
