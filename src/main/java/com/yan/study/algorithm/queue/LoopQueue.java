package com.yan.study.algorithm.queue;

import org.omg.CORBA.INTERNAL;

public class LoopQueue<E> implements Queue<E>{


    private E data[];
    private int front,tail;
    private int size;
    private int capacity;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }
    @Override
    public void enqueue(E e) {

        //如果已经满了
        if( (tail+1) % data.length == front){
            resize( getCapacity() * 2);
        }else {
            data[tail] = e;

        }

        size++;
    }

    private void resize(int newCapaticity) {
        E[] newData = (E []) new Object[newCapaticity +1];
        for (int i= 0; i<size; i++){
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;

    }


    @Override
    public E dequeue() {
        E ret = data[front];
        data[front] = null;
        front = (front +1) % data.length;
        size--;
        return ret;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCapacity() {
        return data.length-1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }
}
