package com.yan.study.algorithm.queue;

/**
 *  循环队列的过程和之前实现的动态数组是一样的，关键在于如何维护front和tail两个索引的指向
 *  在循环中需要注意取模的计算
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E>{


    private E[] data;
    private int front,tail;
    private int size;
    private int capacity;
    //循环队列初始front,tail都是为0,front = tail 为空，tail指向下一个存放的位置
    public LoopQueue(int capacity){
        data = (E[])new Object[capacity+1];//因为front = tail+1%c为满，所以浪费了一个空间
        front = 0;
        tail = 0;
    }

    public LoopQueue(){
        this(10);
    }
    @Override
    public void enqueue(E e) {
        //因为front = tail+1%c为满,余数的取值范围为0到除数之间（不包括除数）的整数如24÷8=3，其中8是除数
        if((tail+1)%data.length == front){
            resize(getCapacity() * 2);
        }
        data[tail] =e;
        tail = (tail+1)%data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity+1];
        //和下面toStirng两种遍历方式
        for (int i =0;i<size;i++){
            newData[i] = data[(front+1)%data.length];
        }

        data = newData;
        front = 0;
        tail = size;

    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("cannot dequeue from empty queue");
        }
        E ret = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;
        if(size == getCapacity() /4 && getCapacity()/2!=0){
            resize(getCapacity()/2);//缩容
        }
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("can not getFront from empty queue");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity(){
        return data.length-1;
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("QUEUE : front[");
        for (int i =front;i!=tail;i=(i+1)%data.length){
            res.append(data[i]);
            if((i+1) %data.length!=tail){
                res.append(", ");
            }
        }
        res.append("]tail");
        return res.toString();
    }
}
