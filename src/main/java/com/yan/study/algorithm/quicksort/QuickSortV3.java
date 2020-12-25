package com.yan.study.algorithm.quicksort;


import javax.jnlp.ExtendedService;
import java.util.Random;

//双路和三路快速排序
public class QuickSortV3 {

    public static <E extends Comparable<E>> void quickSort( E arr[]){
        Random random = new Random();

        sort(arr,0,arr.length-1,random);


    }

    private static  <E extends Comparable<E>> void sort(E [] arr,int l,int r,Random random){

        if(l>=r){
            return;
        }

        int p = partition(arr, l, r,random);
        sort(arr,l,p-1,random);
        sort(arr,p,r,random);
    }

    //三路排序的循环不变量，防止都是相等的元素时重复处理
    private static  <E extends Comparable<E>> void sort3Way(E [] arr,int l,int r,Random random){

        if(l>=r){
            return;
        }

        int p = l+ random.nextInt(r-l+1);
        swap(arr,l,p);
        //循环不变量arr[l+1,lt] < v ; arr[lt+1,i] = v ; arr[gt,r] > v
        int lt =l;
        int i = l+1;
        int gt = r+1;
        while (i < gt){
            if(arr[i].compareTo(arr[l]) < 0){
                lt++;
                swap(arr,i,lt);
                i++;
            }else if(arr[i].compareTo(arr[l]) > 0){
                gt--;
                swap(arr,i,gt);//注意这里不用i--,因为刚交换过来的gt还需要比较
            }else{
                i++;
            }
        }

        swap(arr,l,lt);
        //swap以后，arr[l,lt-1]<v;arr[lt,gt-1]=v;arr[gt,r]>v
        sort3Way(arr,l,lt-1,random);
        sort3Way(arr,gt,r,random);
    }

//    双路排序的循环不变量，arr[l+1,i-1]<=v  ;    arr[j+1,r]>=v
    private  static <E extends Comparable<E>> int partition2Way(E[]arr,int l,int r,Random random){

        int p = l+ random.nextInt(r-l+1);
        swap(arr,l,p);
        int i =l+1;
        int j = r;
        while (true){
            while (i<=j && arr[i].compareTo( arr[l] ) < 0){
                i++;
            }
            while (j>=i && arr[i].compareTo( arr[l] ) > 0){
                j--;
            }

            if(i>=j){
                break;
            }
            swap(arr,i,j);
            i++;
            j--;
        }
        swap(arr,l,j);
        return j;
    }
    //第一个最基础的partition,循环不变量arr[l+1,j] < v      arr[j+1,i-1] >= v
    private static <E extends Comparable<E>> int partition( E[]arr,int l, int r, Random random){
        //对于一个随机的数组，这是一个O(n)的复杂度 而对于一个有序数组，则成为了O(n)²
//        改进生成一个l,r之间的一个随机值
        int p = l+ random.nextInt(r-l+1);
        swap(arr,l,p);
        //对于相等的数组，则成为了O(n)²，改近为双路排序的循环不变量，arr[l+1,i-1]<=v  ;    arr[j+1,r]>=v

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
