package com.uonagent.app;

import com.uonagent.MyArrayList.MyArrayList;

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
  }
}
