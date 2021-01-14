package com.yan.study.algorithm.maxheap;
import java.util.ArrayList;
import java.util.Collection;

//最大堆的实现
public class MaxHeap<E extends Comparable<E>> {

    // 使用动态数组保存堆中元素
    private ArrayList<E> data;

    public MaxHeap(int capacity){
        data = new ArrayList<>(capacity);
    }

    public MaxHeap(){
        data = new ArrayList<>();
    }

    //返回堆中元素的个数
    public int getSize(){
        return data.size();
    }
    //返回堆是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }
    //	 * 返回给定下标为index的节点的父节点下标值
    public int parent(int index){

        if(index == 0){

            throw  new IllegalArgumentException("索引0没有父节点");
        }

        return (index-1)/2;

    }
    //	 * 返回给定下标为index的节点的左子树下标值

    public int leftChild(int index){

        return 2 * index +1;

    }
    //返回给定下标为index的节点的右子树下标值
    public int rightChild(int index){
        return 2 * index +2;

    }
    //添加操作
    public void add(E e){
        data.add(e);//把这个元素添加到队尾
        siftUp(data.size()-1);//也就是最好一个元素上浮
    }

    //节点上浮
    private void siftUp(int index) {
        // 不是根节点, 且当前节点大于父节点时, 和父节点交换位置
        while(index!=0 && data.get(index).compareTo(data.get(parent(index))) > 0){

            swap(index, parent(index));

        }

    }
    //交换data位置
    private void swap(int first, int second) {

        E temp = data.get(first);
        data.set(first, data.get(second));
        data.set(second, temp);
    }
    //寻找最大元素
    public E findMax(){
        if (data.isEmpty()) {
            throw new IllegalArgumentException("当前堆为空, 无最大值");
        }
        return data.get(0);
    }
    //取出最大元素
    public E extractMax(){

        E ret = findMax();
        // 将最大值和最后一个叶子节点交换位置, 即用最后一个叶子节点替换了根节点
        swap(0, data.size() -1);
        // 删除最后一个叶子节点, 即删除了最大值
        data.remove(data.size() - 1);
        // 现在的根节点下沉
        siftDown(0);
        return ret;
    }


    //节点下沉
    private void siftDown(int index) {

        while (leftChild(index) < data.size()) {
            // 用于和index节点进行比较的节点, 先默认为左孩子节点
            int swapIndex = leftChild(index);
            /*
             * swapIndex + 1 < data.size表示右孩子也不为空
             * 右孩子节点值大于左孩子时, 使用右子树与当前节点进行比较
             */
            if (swapIndex + 1 < data.size() && data.get(swapIndex + 1).compareTo(data.get(swapIndex)) > 0) {
                // 右孩子下标进行替换, 这里也可以直接写成swapIndex ++;
                swapIndex = rightChild(index);
            }
            //此时swapIndex是上面对比后的最大值
            if (data.get(swapIndex).compareTo(data.get(index)) > 0) {
                swap(index, swapIndex);
                index = swapIndex;
            } else {
                return;
            }

        }
    }

	 // 替换根节点
    public E replace(E e){
        E ret = findMax();
        // 替换根节点
        data.set(0, e);
        // 根节点下沉
        siftDown(0);
        return ret;
    }

    /**
     * Heapify方式将集合中的元素添加到最大堆中
     * @param collection
     * 首先将数组当成一个完全二叉树
     *
     * 找到最后一个非叶子节点, 方法是根据最后一个元素计算其父节点
     *
     * 从最后一个非叶子节点之前的节点都执行Sift Down操作
     */
    public MaxHeap(Collection<E> collection){
        data = new ArrayList<>(collection);
        for(int i = parent(data.size() -1); i >= 0; i--){
            siftDown(i);
        }
    }

}
