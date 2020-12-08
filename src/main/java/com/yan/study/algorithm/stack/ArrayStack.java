package com.yan.study.algorithm.stack;

import com.yan.study.algorithm.array.Array;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 基于之前的动态数组实现自己的栈
 */
public class ArrayStack<E> implements Stack<E>{

    private Array<E> array;
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }
    public ArrayStack(){
        array = new Array<>();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int capacity(){
        return array.getCapacity();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("stack: [");
        for (int i= 0;i<array.getSize();i++){
            res.append(array.get(i));
            if(i !=array.getSize()-1){
                res.append(",");

            }
        }

        res.append("]top");
        return res.toString();
    }
}
