package cn.ne.aopauth.utils;


import cn.ne.aopauth.utils.link.Link;
import cn.ne.aopauth.utils.link.LinkImple;
import cn.ne.aopauth.utils.link.testcase.*;

import java.util.Arrays;

public class LinkDemo {
    public static void main(String[] args) {
//        linkTest();
        linkCase();
    }

    private static void linkTest(){
        Link<String> link = new LinkImple<>();
        System.out.println("[保存前]是否空链表 >>> " + link.isEmpty());
        System.out.println("[保存前]元素个数 >>> " + link.size());
        link.add("abc");
        link.add("123");
        link.add("22DD");
        System.out.println("[保存后]元素个数 >>> " + link.size());
        System.out.println("[保存后]是否空链表 >>> " + link.isEmpty());

        System.out.println("链表转为数组 >>> " + Arrays.toString(link.toArray()));

        System.out.println("下标1的数据 >>> " + link.get(1));

//        System.out.println("更新前 >>> " + link.get(1)+",更新后 >>> " + link.set(1,"12345"));

        System.out.println("是否存在 >>> " + link.contains("abc"));
        System.out.println("是否存在 >>> " + link.contains("fuck"));

        System.out.println("删除了 >>> " + link.remove("abc")+",剩余 >>> "+ link.size());

        link.clear();
        System.out.println("清空数据。剩余 >>> " + link.size());
    }

    private static void linkCase(){
        Student student = new Student();
        student.buy(new MathBook("布尔传奇",67.8,"布尔"));
        student.buy(new MathBook("线性代数",37.8,"xx数学家"));
        student.buy(new ProgramBook("Java",99.8,"Lee"));
        student.buy(new ProgramBook("Python",67.8,"Lee"));
        student.buy(new ProgramBook("Go",99.8,"Lee"));
        student.buy(new BigdataBook("Flink实时分析",129.8,"Leo"));
        student.buy(new BigdataBook("Spark",159.6,"unknow"));
        Object[] objects1 = student.getBooks().toArray();
        print(objects1,student);
        System.out.println("=========================================================");


        student.give(new ProgramBook("Go",99.8,"Lee"));
        Object[] objects2 = student.getBooks().toArray();
        print(objects2,student);
        System.out.println("=========================================================");


        Object[] objects3 = student.search("Lee").toArray();
        print(objects3, student);
    }


    private static void print(Object[] temp,Student student) {
        for (Object object : temp) {
            System.out.println(object + " ");
        }
    }

}
