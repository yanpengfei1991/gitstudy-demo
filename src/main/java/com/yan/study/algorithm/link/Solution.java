package com.yan.study.algorithm.link;

public class Solution {
    public static void main(String[] args) {

        LinkedQueue<Integer> link = new LinkedQueue<>();
        for(int i = 0;i<10;i++){

            link.enqueue(i);
        }
        System.out.println(link);
        link.dequeue();
        System.out.println(link);
    }
}
