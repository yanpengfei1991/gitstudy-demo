package com.yan.study.algorithm.quicksort;


import java.util.Random;

//为快速排序添加随机化，对于partition函数并不每次都取最左边当做界值
public class QuickSortV2 {

    public static <E extends Comparable<E>> void quickSort( E arr[]){

        sort(arr,0,arr.length-1);


    }

    private static  <E extends Comparable<E>> void sort(E [] arr,int l,int r){

        if(l>=r){
            return;
        }

        int p = partition(arr, l, r);
        sort(arr,l,p-1);
        sort(arr,p,r);
    }

    //第一个最基础的partition,循环不变量arr[l+1,j] < v      arr[j+1,i-1] >= v
    private static <E extends Comparable<E>> int partition( E[]arr,int l, int r){
        //对于一个随机的数组，这是一个O(n)的复杂度 而对于一个有序数组，则成为了O(n)²
//        改进生成一个l,r之间的一个随机值
        int p = l+ (new Random()).nextInt(r-l+1);
        swap(arr,l,p);
//        --------------
        int j = l;
        for(int i = l+1; i<=r; i++){
            //如果小于0，则i这个元素应在<v的区间，所以<v的区间++j扩充，然后交换位置
            if( arr[i].compareTo(arr[l]) < 0){
                j++;
                swap(arr,i,j);
            }
        }
        swap(arr,l,j);//循环结束后，将第一个界值和中间位置j交换
        return j;
    }

    private static <E extends Comparable<E>> void swap(E[]arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;


    }
}
