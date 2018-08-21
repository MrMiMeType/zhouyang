package com.atguigu.concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSafeDaemon {
    private static List<String> list = new CopyOnWriteArrayList<String>();
    private static Set<String> set = new CopyOnWriteArraySet<String>();
    public static void main(String[] args) {
        //testArrayListNotSafe();

        //testHashSetNotSafe();

        testHashMapNotSafe();
    }
    //ArrayList是线程不安全的
    public static void testArrayListNotSafe(){
        for (int i = 1; i <= 10 ; i++) {
            new Thread(() -> {
                list.add("hahah");
                //在这儿打印最终会打印出长度为10的集合，如果放在main方法中打印的话main方法会争抢资源，可能出现打印0-10个元素的情况！
                //CopyOnWriteArrayList还是有线程问题，但是能避免抛出ConcurrentModifiCationException
                System.out.println(list.toString());
            },String.valueOf(i)).start();
        }
    }
    //HashSet是线程不安全的
    public static void testHashSetNotSafe(){
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                set.add("山哥大土匪！");
                System.out.println(set.toString());
            }, String.valueOf(i)).start();
        }
    }

    public static void testHashMapNotSafe(){
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30 ; i++) {
            new Thread(() -> {
                map.put("张建春","帅！");
                System.out.println(Thread.currentThread().getName()+":"+map.toString());
            },String.valueOf(i)).start();
        }
    }
}
