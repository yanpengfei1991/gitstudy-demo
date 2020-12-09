package com.yan.study.algorithm.link;

import com.yan.study.algorithm.stack.Stack;

/**
 * 用链表实现栈
 */
public class LinkedStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;
    public LinkedStack(){
        linkedList = new LinkedList<>();
    }

    public void push(E e){
        linkedList.addFirst(e);
    }

    public E pop(){
      return   linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return   linkedList.getFirst();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack:top");
        res.append(linkedList);

        return res.toString();
    }
}
