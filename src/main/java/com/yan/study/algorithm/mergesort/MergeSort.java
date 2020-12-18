package com.yan.study.algorithm.mergesort;

import java.util.Arrays;

/**
 * 归并排序算法
 */
public class MergeSort {

    private MergeSort(){

    }

    public static <E extends Comparable<E>> void  sort(E arr[]){
        sort(arr,0,arr.length-1);
    }

    private  static  <E extends Comparable<E>> void sort(E arr[],int l,int r){
        if( l >= r){
          return;//这里可以使用插入排序优化
        }


//        int mid = ( l +r )/2;
        int mid = l+(r-l)/2;//防止数组越界
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        //小小的优化
        //如果mid的值小于mid+1就不用合并了否则合并
        if (arr[mid].compareTo(arr[mid+1]) > 0){
            merge(arr,l,mid,r);
        }

    }

    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static <E extends Comparable<E>> void merge(E arr[],int l,int mid, int r){
        E[] temp = Arrays.copyOfRange(arr,l,r+1);
        int i = l;
        int j = mid+1;
        for( int k = l; k<=r; k++ ){
            if(i>mid){
                arr[k] = temp[j-l];j++;
            }else if(j > r){
                arr[k] = temp[i-l];i++;
            }else if(temp[i-l].compareTo(temp[j-l]) <= 0){
                //arr[i] and arr[j] 谁的值小
                arr[k] = temp[i-l]; i++;
            }else{
                arr[k] = temp[j-l]; j++;

            }
        }

    }
}
