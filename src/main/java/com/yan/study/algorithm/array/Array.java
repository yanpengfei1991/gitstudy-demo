package com.yan.study.algorithm.array;

import javafx.scene.control.TableView;

/**
 * 基于Java数组封装属于自己的数组类
 */
public class Array<E>{
    private E data[];
    private int size;//数组中实际有多少个元素,初始指向的位置是第一个没有元素的索引

    /**
     * 构造函数传入容量为capacity的构造Array
     * @param capacity
     */
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }
    //无参构造函数初始化10个容量
    public Array(){
        this(10);
    }
    //获取数组中元素的个数
    public int getSize(){
        return  size;
    }
    //获取数组的容量
    public  int getCapacity(){
        return data.length;
    }
    // 数组是否为空
    public boolean isEmpty(){

        return  size == 0;
    }
    //[0,1,2,3]因为数组的结构在尾部添加时最方便的
    public void addLast( E e){
//        if( size == data.length ){
//            throw new IllegalArgumentException("AddLast failed ,array is full");
//        }
//        data[size] = e;
//        size++;

        add(size,e);
    }

    //向指定的index位置插入元素
    public void add(int index, E e){
        if( size == data.length ){
            //扩容
            resize(2* data.length);
        }
        for(int i = size-1;i>=index;i--){
            //前面的元素赋值给后一个位置，逐步后移
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }



    public void addFirst(E e){
        add(0,e);
    }

    public E get(int index){
        return data[index];
    }

    void set(int index,E e){
        data[index] = e;
    }
    //是否存在某个元素e
    public boolean contains(E e){
        for (int i =0;i<size;i++){
            if(data[i].equals(e) ){
                return true;
            }
        }

        return  false;
    }
    //查找某个元素e的索引
    public int find(E e){
        for (int i =0;i<size;i++){
            if(data[i].equals(e)){
                return i;
            }
        }

        return  -1;
    }
    //数组中删除指定索引位置的元素
    public E remove(int index){
        if(index <0 || index>=size){
            throw new IllegalArgumentException("index remove failed ,index is fault");
        }
        E ret = data[index];
        //我自己的思路
//        for (int i =index;i<size;i++){
//            data[i] = data[i+1];
//        }
//
        for (int i = index+1;i<size;i++){
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;
//        if(size == data.length/2){
//            resize(data.length/2);
//        }
        //防止复杂度震荡
        if(size == data.length/4){
            resize(data.length/2);
        }
        return ret;

    }
//从数组中删除元素e
    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }
    public E removeLast(){
        return  remove(size-1);

    }
    public E  removeFirst(){
        return  remove(0);
    }

    public E getLast(){
        return get(size-1);

    }

    public E getFirst(){
        return get(0);
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(String.format("array:size = %d,capacity:= %d\n",size,data.length));
        result.append("[");
        for(int i = 0; i<size;i++){
            result.append(data[i]);
            if(i != size-1){
                result.append(" ,");

            }
        }
        result.append("]");

        return result.toString();
    }

    private void resize(int newCapacity) {
        E []newData = (E[])new Object[newCapacity];

        for (int i=0; i<size; i++){

            newData[i] = data[i];
        }
        data = newData;
    }
}
