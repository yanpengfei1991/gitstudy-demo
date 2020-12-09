package com.yan.study.algorithm.link;

import com.yan.study.algorithm.queue.Queue;

/**
 * 用链表实现队列
 */
public class LinkedQueue<E> implements Queue<E>{



    private class Node{
        public E e;    //存储当前的元素
        public Node next;  //存储指向的下一个元素

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node() {
            this(null,null);
        }

        public Node(E e){
            this(e,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head,tail;
    private int size;
    public LinkedQueue(){
        head = null;
        tail = null;
        size = 0;
    }
    //从链表尾部入
    @Override
    public void enqueue(E e) {
        //如果tail是空，其实就是第一个
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }else{
           tail.next =  new Node(e);
           tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("can not dequeue from empty Queue");
        }
        //从头部取
        Node ret = head;
       head =  head.next;
        ret.next = null;
        //如果只有一个元素
        if(head == null){
            tail =null;

        }
        size--;
        return ret.e;
    }

    @Override
    public E getFront() {
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0==size;
    }
    @Override
    public String toString(){
        StringBuilder res =  new StringBuilder();
        res.append("Queue:front");
        Node cur = head.next;
        while(cur!=null){
            res.append(cur+"->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
