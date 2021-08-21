package stackandqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Transformation {
    public static void main(String[] args) {
        TwoQueuesStack stack = new TwoQueuesStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("========");
        TwoStacksQueue queue = new TwoStacksQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}

class TwoQueuesStack {
    private Queue<Integer> queue;
    private Queue<Integer> helper;

    public TwoQueuesStack() {
        queue = new LinkedList<>();
        helper = new LinkedList<>();
    }

    public void push(int i) {
        queue.add(i);
    }

    public int pop() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        while (queue.size() > 1) {
            helper.add(queue.poll());
        }
        int res = queue.poll();
        swap();
        return res;
    }

    public int peek() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        while (queue.size() > 1) {
            helper.add(queue.poll());
        }
        int res = queue.poll();
        helper.add(res);
        swap();
        return res;
    }

    private void swap() {
        Queue<Integer> tmp = queue;
        queue = helper;
        helper = tmp;
    }

}

class TwoStacksQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStacksQueue() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    public void push(int pushInt) {
        stackPush.push(pushInt);
    }

    public int poll() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }
}