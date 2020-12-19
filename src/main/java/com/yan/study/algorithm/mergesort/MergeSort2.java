package com.yan.study.algorithm.mergesort;


import java.util.Arrays;

/**
 * 归并排序算法
 */
public class MergeSort2 {

    private MergeSort2(){

    }

    public static <E extends Comparable<E>> void  sort(E arr[]){
        sort(arr,0,arr.length-1);
    }

    private  static  <E extends Comparable<E>> void sort(E arr[],int l,int r){
        if( l >= r){
            return;
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

    //自底向上归并排序
    public static  <E extends Comparable<E>> void sortBU(E[] arr){
        E[]temp = Arrays.copyOf(arr,arr.length);
        int n = arr.length;
        //遍历合并的区间长度
        for( int sz = 1; sz<n; sz+=sz ){
            //遍历合并两个区间的起始位置,合并[i,i+sz-1],[i+sz,i+sz+sz-1]
            for(int i =0;i+sz<n; i+=sz+sz){
                merge(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1),temp);
            }
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
