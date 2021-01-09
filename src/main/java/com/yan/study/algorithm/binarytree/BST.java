package com.yan.study.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//递归实现二分搜索树的实现binary search tree
public class BST <E extends Comparable<E>>{

    private int size;
    private Node root;

    public BST() {
        this.root = null ;
        this.size = 0 ;
    }

    public boolean isEmpty() {
        return size == 0 ;
    }

    public int getSize(){
        return size;
    }
    private class Node{
        private Node left,right;
        private E e;


        public Node(E e){
            this.e = e;
            left = null;
            right = null;

        }

    }

    //添加元素
    public void add(E e){
        root = add(root,e);
    }
    /**
     * 向以node节点为根节点的树上添加元素E
     * 递归方法
     * @param node
     * @param e
     * @return 返回插入新节点后的二分搜索树的根节点
     */
    private Node add(Node node, E e) {

        if(node == null){
            size++;
            return new Node(e);
        }
        //如果添加的元素小于节点元素则向左递归
        if(node.e.compareTo(e) > 0){

            node.left = add(node.left, e) ;

        } else if (node.e.compareTo(e) < 0) {

            node.right = add(node.right, e) ;
        }
        return node ;

    }

    //查找操作
    public boolean contains(E e){
        return contains(root,e);
    }

    /**
     * 判断以node为根节点的二分搜索树中是否包含元素e
     * 递归方法
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {

        if(node == null){
            return false;
        }

        // 递归终结条件
        if (node.e.compareTo(e) == 0) { // node.e == e
            return true;
            // 根据二分搜索树特性, e小于当前节点的值时, 向左递归
        } else if (node.e.compareTo(e) > 0) { // node.e > e
            return contains(node.left, e) ;
            // 根据二分搜索树特性, e大于当前节点的值时, 向右递归
        } else { // node.e < e
            return contains(node.right, e) ;
        }
    }

    //遍历操作，前序是最自然最常用的一种
    //前序：当前节点，左右，中序：左，当前节点，右 后序：左，右，中
    public void preOrder(){
        preOrder(root);
    }

    /**
     *
     * 前序遍历的递归方法, 深度优先
     * 前序遍历是指,先访问当前节点, 然后再访问左右子节点
     * @param node
     */
    private void preOrder(Node node) {

        if(node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //非递归的前序遍历
    public void preOrderNR(){

        Stack<Node> stack = new Stack<>();
        //从根节点开始，先将root压入栈
        stack.push(root);
        while( !stack.empty() ){
            //前序先是当前节点
            Node cur = stack.pop();
            System.out.println(cur.e);
            // 由于栈是后入先出, 前序遍历是先左孩子, 再右孩子, 所以这里需要先将右孩子压入栈

            if(cur.left != null){
                stack.push(cur.right);
            }

            if(cur.right != null){
                stack.push(cur.left);
            }
        }

    }
    //广度层序遍历,按层从左到右进行遍历,更快找到问题的解
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        /*
		 * 遍历过程:
		 * 1. 首先根节点入队
		 * 2. 每次出队时, 都将当前节点的左右孩子先后入队
		 * 3. 如果队列为空的话, 则表示层序遍历结束
         */
        while( !queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.e);
            if( cur.left != null){
                queue.add(cur.left);
            }
            if( cur.right != null){
                queue.add(cur.right);

            }

        }
    }
    //查找最大元素
    public E maxmun(){
        if (size == 0) {
            throw new IllegalArgumentException("BSTree is empty");
        }
        return maxmun(root).e;

    }

    //以node为根元素查找树中的最大节点
    private Node maxmun(Node node) {
        if(node.right == null){
            return node;
        }
        return  maxmun(node.right);
    }

    //删除最大节点
    public E removeMax(){
        E ret = maxmun();
        //完成删除动作
        root = removeMax(root);
        return ret;

    }
    /**
     * 删除以node为根节点的树的最大值
     * @param node
     * @return 返回删除后的新的二分搜索树的根
     */
    private Node removeMax(Node node) {
        if( node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    //查找最小元素
    public E minimum(){
        if (size == 0) {
            throw new IllegalArgumentException("BSTree is empty");
        }
        return minimum(root).e;
    }

    //以node为根元素查找树中的最小节点
    private Node minimum(Node node) {

        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //删除最小元素
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);

        return ret;
    }

    /**
     * 删除以node为根节点的树的最小值
     * @param node
     * @return 返回删除后的新的二分搜索树的根
     */

    private Node removeMin(Node node) {
        // 递归终止条件
        if(node.left == null){
            //这里自己逻辑困住了，首先找个引用把当前要删除的最小node的右节点用一个引用保存住
            Node rightNode = node.right;
            node.right = null;//然后，把这个要删除的最小node节点的右节点指向null完成删除
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除指定元素e所在的节点
     * @param e
     */
    public void remove(E e){

        root = remove(root, e);

    }

    /**
     * 递归删除以node为根节点中的二分搜索树中的节点
     * @param node
     * @param e
     * @return 返回删除后的新二分搜索树的根节点
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        //寻找e所在的node位置
        if(node.e.compareTo(e)>0){
            node.left =remove(node.left, e);
            return node;

        } else if (node.e.compareTo(e) < 0) {
             node.right = remove(node.right, e);
             return node;

        }else{
            // e == node.e
            // 待删除节点左子树为空的情况,逻辑和删除最小值逻辑相似
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况

            // 查找待删除节点的后继节点
            // 用后继节点替换当前待删除节点

            // 查找后继节点, 从待删除节点的右子树,查找最小值
            Node successor = minimum(node.right);
            /*
             *  需要注意的是, 这里removeMin中进行了size--操作,
             *  但是实际上最小的元素变成了successor, 并没有删除
             *  所以按照逻辑的话, 这里应该有一个size++
             *  但是后面删除了元素之后,需要再进行一次size--, 所以这里就不对size进行操作了
             */
            successor.right = removeMin(node.right);
            successor.left = node.left;
            // 后继节点完成替换, 删除当前节点
            node.left = node.right = null;
            return successor;
        }

    }

}
