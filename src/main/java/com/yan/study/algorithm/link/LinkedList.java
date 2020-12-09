package com.yan.study.algorithm.link;

/**
 * 实现自己的链表
 * @param <E>
 */
public class LinkedList<E> {

    private class Node{
       public E e;    //存储当前的元素
       public  Node next;  //存储指向的下一个元素

       public Node(E e,Node next){
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
    //一开始的设计，链表只需要一个头即可无限连接下去
//    private Node head;
    private Node dummyHead;//然后进化成关键在于找到要添加的结点的前一个结点，所以给头结点设置了一个前面的虚拟结点
    int size;

    public LinkedList(){
//        head = null;
        dummyHead = new Node(null,null);
        size = 0;
    }

    //获取链表中的个数
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return  size == 0;
    }
    //在链表头添加元素
    public void addFirst(E e){
//        //自己构建一个node
//       Node node = new Node(e);
//       node.next = head;
//       //将head指向这个node,也就是让这个node成为头结点
//        node = head ; //这是我自己开始写的错误，没有理解引用
//        head = node;


        //实际以上步骤可以合为一个
//        head = new Node(e,head);
//        size++;
        add(0,e);
    }

    //在链表index(0-based)位置添加,关键在于找到要添加的结点的前一个结点
    public void add(int index,E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("index is fault");
        }


            //关键在于索引和node如何关联上？

//           Node node =  new Node<>(e);
//            node.next = prev.next;
//            prev.next = node;//等同于如下一行
            Node prev =dummyHead;
            for (int i=0;i<index;i++){
                prev = prev.next;

            }
            prev.next = new Node(e,prev.next);
            size++;

    }

    public void addLast(E e){
        add(size,e);
    }

    //在链表index(0-based)位置查找
    public E get(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("index is fault");
        }

        Node cur = dummyHead.next;
        for(int i=0;i<index;i++){
            cur = cur.next;
        }
        return  cur.e;
    }

    //获得链表的第一个元素
    public E getFirest(){
        return get(0);
    }
    //获取最后一个
    public E getLast(){
        return get(size-1);
    }
    //更新index位置的元素
    public void set(int index,E e){

        Node cur = dummyHead.next;
        for(int i=0;i<index;i++){
            cur = cur.next;
        }
        cur.e=e;
    }

    //查找链表中是否有元素e
    public boolean contains(E e){

        Node cur = dummyHead.next;
//        for(int i=0;i<size;i++){
//            cur = cur.next;
//            if(cur.e.equals(e)){
//                return true;
//            }
//        }
//        return false;

        while(cur!=null) {
            if (cur.e.equals(e)) {
                return true;
            }
        }
        return  false;
    }

//删除指定索引位置的元素
    public E remove(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("index is fault");
        }
       Node prev = dummyHead;
       for(int i=0;i<index;i++){
           prev = prev.next;

       }
       Node delNode = prev.next ;
       prev.next = delNode.next;
       delNode.next =null;
       size--;
       return delNode.e;
    }

    //从链表中删除第一个元素
    public E removeFirst(){
        return remove(0);

    }

    //从链表中删除最后一个元素
    public E removeLast(){
        return remove(size-1);
    }
    @Override
    public String toString(){
       StringBuilder res =  new StringBuilder();
       res.append("");
       Node cur = dummyHead.next;
       while(cur!=null){
           res.append(cur+"->");
           cur = cur.next;
       }
        res.append("NULL");
        return res.toString();
    }
}
