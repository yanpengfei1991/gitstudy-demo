package com.yan.study.algorithm.bubblesort;

//冒泡排序的实现
public class BubbleSort {

    private BubbleSort(){};
    //[5，3，1，6，7，9]
    public static <E extends Comparable<E>> void bubbleSort(E[]data){
        //外循环 n 次
        for(int i = 0;i  <data.length-1;i++){
            // 用于记录数组是否已经是有序的了,避免有序数组重复循环
            boolean isSorted = true;
            //对每一个元素扫描和后面的元素比对，最大值放到最后i，也就是每次较少比对i个元素
            for(int j = 0;j<data.length-1-i;j++){

                if(data[j].compareTo(data[j+1])>0){
                    swap(data,j,j+1);
                }
            }

            // 如果内存循环结束, isSorted扔为true, 则表示数组已经排序完成
            if (isSorted) {
                break;
            }
        }
    }

    //优化版本
    public static <E extends Comparable<E>> void bubbleSort2(E[]data){
        //外循环 n 次
        for(int i = 0;i  <data.length-1;){
            // 用于记录数组是否已经是有序的了,避免有序数组重复循环
            int lastSwapIndex = 0;
            //对每一个元素扫描和后面的元素比对，最大值放到最后i，也就是每次较少比对i个元素
            for(int j = 0;j<data.length-1-i;j++){

                if(data[j].compareTo(data[j+1])>0){
                    swap(data,j,j+1);
                    lastSwapIndex = j+1;//记录最后交换的位置
                }
            }

            // 如果内存循环结束, 则表示数组已经排序完成
//            if (lastSwapIndex == 0) {
//                break;
//            }
            i = data.length - lastSwapIndex;//还剩多少没有排好序，需要循环
        }
    }

    private static  <E extends Comparable<E>> void swap(E[] data, int j, int i) {

        E temp = data[j];
        data[j] = data[i];
        data[i] = temp;
    }
}
