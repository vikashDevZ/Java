package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {

    static int idx = -1;

    public static Node createTree(int[] n) {
        idx++;

        if (n[idx] == -1)
            return null;
        Node head = new Node(n[idx]);

        head.left = createTree(n);
        head.right = createTree(n);

        return head;
    }
    // preorder
    // postorder
    // inorder

    public static void inOrder(Node root) {
        if (root == null) {
            System.out.println("");
            return;
        }

        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    public static void postOrder(Node root) {
        if (root == null) {
            System.out.println("");
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("");
            return;
        }

        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void leveOrder(Node root) {
        Queue<Node> st = new LinkedList<>();

        st.add(root);
        st.add(null);

        while (!st.isEmpty()) {
            Node curr = st.poll();
            if (curr != null) {
                System.out.print(curr.data);
                if (curr.left != null) {
                    st.add(curr.left);
                }
                if (curr.right != null) {
                    st.add(curr.right);
                }
            } else {
                System.out.println("");
                if (st.isEmpty()) {
                    break;
                } else {
                    st.add(null);
                }
            }
        }
    }

    public static int height(Node root) {
        if (root == null)
            return 0;

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int diam(Node root) {
        if (root == null)
            return 0;

        int diam1 = diam(root.left);
        int diam2 = diam(root.right);
        int diam3 = height(root.left) + height(root.right) + 1;

        return Math.max(diam1, Math.max(diam2, diam3));
    }

    public static int findMax(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;
        return Math.max(root.data, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static void addlevel(Map<Integer, Integer> mp, Node root, int lvl) {
        if (root == null)
            return;

        if (!mp.containsKey(lvl)) {
            mp.put(lvl, root.data);
        }
        if (root.left != null) {
            addlevel(mp, root.left, lvl + 1);
        }
        if (root.right != null) {
            addlevel(mp, root.right, lvl + 1);
        }

    }

    public static void leftView(Node root) {
        // or rightView iykyk
        Map<Integer, Integer> map = new HashMap<>();

        addlevel(map, root, 0);

        for (int value : map.values()) {
            System.out.println(value);
        }
    }

    public static void addlvelForBottm(Node root, Map<Integer, Integer> map, int dist) {

        // if (!map.containsKey(dist)) {
        map.put(dist, root.data);
        // }

        if (root.left != null) {
            addlvelForBottm(root.left, map, dist - 1);
        }
        if (root.right != null) {
            addlvelForBottm(root.right, map, dist + 1);
        }

    }

    public static void bottomView(Node root) {

        Map<Integer, Integer> map = new HashMap<>();

        addlvelForBottm(root, map, 0);

        for (int value : map.values()) {
            System.out.println(value);
        }

    }

    public static Node findCommonAnsister(Node root, int d1, int d2) {
        if (root == null)
            return null;

        if (root.data == d1 || root.data == d2) {
            return root;
        }
        Node left = findCommonAnsister(root.left, d1, d2);
        Node right = findCommonAnsister(root.right, d1, d2);

        if (left == null)
            return right;
        if (right == null)
            return left;

        return root;
    }

    static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static ListNode findMedian(ListNode root) {
        ListNode slow = root;
        ListNode fast = root;
        ListNode prev = root;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (prev != null) {
            prev.next = null;
        }

        return slow;

    }

    public static Node treeToDoubleLL(ListNode head) {
        if (head == null)
            return null;

        ListNode mid = findMedian(head);
        Node root = new Node(mid.data);

        if (head == mid)
            return root;

        root.left = treeToDoubleLL(head);
        root.right = treeToDoubleLL(mid.next);
        return root;
    }

    public static boolean isBinaryTreeComplete(Node root) {
        if (root == null)
            return true;

        Queue<Node> st = new LinkedList<>();
        st.add(root);

        while (!st.isEmpty()) {
            Node curr = st.poll();
            if (curr == null) {
                while (!st.isEmpty()) {
                    Node newCurr = st.poll();
                    if (newCurr != null)
                        return false;
                }
                return true;
            } else {
                st.add(curr.left);
                st.add(curr.right);
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // int arr[] = { 1, 2, -1, 3, -1, -1, 4, 5, -1, -1, 6, -1, -1 };
        int arr[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, -1 };

        Node root = createTree(arr);
        // preOrder(root);
        // postOrder(root);
        // inOrder(root);
        // leveOrder(root);
        // System.out.println(height(root));
        // System.out.println(diam(root));
        // System.out.println(findMax(root));
        // leftView(root);
        // bottomView(root);
        // System.out.println(findCommonAnsister(root, 5, 6).data);

        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        // System.out.println(treeToDoubleLL(head).data);
        System.out.println(isBinaryTreeComplete(root));
    }
}
