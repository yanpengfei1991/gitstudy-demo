package com.yan.study.algorithm.stack;

public class StackMain {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack<Integer>();

        for (int i=0;i<5;i++){
            stack.push(i);
        }
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);

    }
}
