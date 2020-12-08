package com.yan.study.algorithm.queue;

public interface Queue<E> {

    void enqueue(E e);
    E dequeue();
    int getSize();
    int getCapacity();
    boolean isEmpty();


}
