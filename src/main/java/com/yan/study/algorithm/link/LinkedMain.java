package com.yan.study.algorithm.link;

public class LinkedMain {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0;i<10;i++){
            linkedList.addFirst(i);
        }
        System.out.println(linkedList);
        linkedList.addFirst(10);
        linkedList.remove(0);
        System.out.println(linkedList);
    }
}
