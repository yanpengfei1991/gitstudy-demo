package com.yan.study.algorithm.array;



public class ArrayMain {
    public static void main(String[] args) {
      Array<Integer> arr =  new Array<Integer>();
     for (int i=0;i<10;i++){
         arr.addLast(i);
     }
        System.out.println(arr);
        arr.addLast(11);
        System.out.println(arr);

        arr.remove(0);
        arr.remove(1);

        System.out.println(arr);

    }

}
