package bintree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinTree {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        Node root = createBinaryTree(list);
        System.out.println("先序遍历");
        preOrderTraversal(root);
        System.out.println("\n中序遍历");
        inOrderTraversal(root);
        System.out.println("\n后序遍历");
        postOrderTraversal(root);

        System.out.println("\n先序遍历1");
        preOrderTraversal1(root);
        System.out.println("\n中序遍历1");
        inOrderTraversal1(root);
        System.out.println("\n后序遍历1");
        postOrderTraversal1(root);

    }

    /**
     * 构建二叉树
     *
     * @param list 输入序列
     * @return root节点
     */
    public static Node createBinaryTree(LinkedList<Integer> list) {
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(list.removeFirst());
        queue.add(root);

        while (!list.isEmpty()) {
            Node l = new Node(list.removeFirst());
            queue.add(l);
            Node r = null;
            if (!list.isEmpty()) {
                r = new Node(list.removeFirst());
                queue.add(r);
            }
            Node prev = queue.poll();
            prev.left = l;
            prev.right = r;
        }
        return root;
    }

    public static void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    //先序遍历迭代方式
    public static void preOrderTraversal1(Node root) {
        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            if (root != null) {
                System.out.print(root + " ");
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop().right;
            }
        }
    }

    public static void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root + " ");
        inOrderTraversal(root.right);
    }

    //中序遍历：与先序遍历的区别在于，在加入的时候不输出，而是在栈中弹出的时候再输出
    public static void inOrderTraversal1(Node root) {
        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root + " ");
                root = root.right;
            }
        }
    }

    public static void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root + " ");
    }

    //存在一种情况：从栈中弹出一个节点a，存在右子节点，这时候，这个右子节点如果没有被访问过
    //则将节点a，重新入栈开始处理其右子节点。
    //如果右子节点处理完成之后，弹出a，则输出a
    //因此需要记录，上一个输出的节点
    public static void postOrderTraversal1(Node root) {
        Stack<Node> stack = new Stack<>();
        Node last = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                //右子节点需要被处理，但是还没有被处理，那么需要先处理他
                if (root.right != null && root.right != last) {
                    stack.push(root);
                    root = root.right;
                } else {
                    //右子节点处理完
                    System.out.print(root + " ");
                    last = root;
                    root = null;
                }
            }
        }
    }

    static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
