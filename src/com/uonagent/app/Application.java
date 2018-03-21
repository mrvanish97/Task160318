package com.uonagent.app;

import com.uonagent.MyArrayList.MyArrayList;
import com.uonagent.MyHashMap.MyHashMap;

import java.util.HashMap;
import java.util.LinkedList;

public class Application {
  public static void main(String... args) {
    MyArrayList<Integer> list = new MyArrayList<>(2);
    for (int i = 0; i < 5; ++i) {
      list.add(i + 1);
    }
    list.remove(1);
    System.out.println(    list.indexOf(5));
    System.out.println(    list.get(2));
    MyHashMap<String, Integer> map = new MyHashMap<>(5);
    map.put("Вася", 143);
    map.put("Не Вася", 2399);
    System.out.println(map.put("Вася", 2335));
    System.out.println(map.containsValue(143));
    System.out.println(map.containsValue(2335));
    System.out.println(map.remove("Не Вася"));
    System.out.println(map.containsKey("Не Вася"));
  }
}
