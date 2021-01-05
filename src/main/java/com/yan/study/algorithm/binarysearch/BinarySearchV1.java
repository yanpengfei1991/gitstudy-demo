package com.yan.study.algorithm.binarysearch;

//二分搜索法第一个版本
public class BinarySearchV1 {

    private BinarySearchV1(){

    }

    //二分查找法的递归写法
    public static <E extends Comparable<E>> int binarySearchR(E []arr,E target){

       return binarySearchR(arr,target,0,arr.length-1);
    }

    private static <E extends Comparable<E>> int binarySearchR(E[] arr, E target, int l, int r) {

            if(l > r){
                return -1;
            }

            int mid = l+(r-l)/2;

            if(target.compareTo(arr[mid]) == 0){
                return mid;
            }

            if(target.compareTo(arr[mid]) > 0){
                l = mid+1;
                return binarySearchR(arr,target,l,r);
            }
                r = mid-1;
            return  binarySearchR(arr,target,l,r);

    }
    //< target 最大值索引
    public static <E extends Comparable<E>> int lower(E []arr,E target) {
            int l  = -1;
            int r = arr.length-1;
            while (l < r ){
                // l和r相邻时出现死循环，搜索空间没有变化
//                int mid = l+(r-l)/2;
                int mid = l+(r-l+1)/2;
                if(arr[mid].compareTo(target) < 0){
                    l = mid;
                }else{
                    r = mid -1;
                }
            }
         return -1;
    }
    //二分查找法的非递归写法
    public static <E extends Comparable<E>> int binarySearch(E []arr,E target){

        int l  = 0;
        int r = arr.length-1;

        while(l<=r){
            int mid = l+(r-l)/2;

            if(target.compareTo(arr[mid]) == 0){
                return mid;
            }

            if(target.compareTo(arr[mid]) > 0){
                l = mid+1;
            }else{
                r = mid-1;
            }

        }

        return -1;
    }
}
