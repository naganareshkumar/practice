// Define and intialize null value for tree with left and right nodes 

import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

public class Node{
    Node left; 
    Node right;
    int value;
    public Node(int value){
        this.value =value;
        this.left = null;
        this.right = null;
    }
    public Node(int value, Node left, Node right){
        this.value= value;
        this.left=left;
        this.right = right;
    }
}
class BinaryTree{
    public static int heightRecursive(Node node){
        if(node ==null){
            return 0;
        }
        return 1+Math.max(heightRecursive(node.left),heightRecursive(node.right));
    }
    public static int heightIterative(Node root){
        if(root == null){
            return 0;
        }
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        int height   = 0;
        while(!que.isEmpty()){
            int level = que.size();
            height++;
            for(int i =0; i<level;i++){
                Node node1 = que.poll();
                if(node1.left!=null){
                    que.add(node1.left);
                }
                if(node1.right!=null){
                    que.add(node1.right);
                }
            }
        }
        return height;
    }
}


class BinaryTreeHeightTest {

    @Test
    public void testEmptyTree() {
        Node root = null;
        assertEquals(0, BinaryTree.heightRecursive(root));
        assertEquals(0, BinaryTree.heightIterative(root));
    }

    @Test
    public void testSingleNodeTree() {
        Node root = new Node(1);
        assertEquals(1, BinaryTree.heightRecursive(root));
        assertEquals(1, BinaryTree.heightIterative(root));
    }

    @Test
    public void testLeftSkewedTree() {
        // Structure: 1 -> 2 -> 3 -> 4 (height = 4)
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.left = new Node(4);

        assertEquals(4, BinaryTree.heightRecursive(root));
        assertEquals(4, BinaryTree.heightIterative(root));
    }

    @Test
    public void testRightSkewedTree() {
        // Structure: 1 -> 2 -> 3 -> 4 (height = 4)
        Node root = new Node(1);
        root.right = new Node(2);
        root.right.right = new Node(3);
        root.right.right.right = new Node(4);

        assertEquals(4, BinaryTree.heightRecursive(root));
        assertEquals(4, BinaryTree.heightIterative(root));
    }

    @Test
    public void testBalancedTree() {
        // Structure:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4  5  6  7 (height = 3)
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        assertEquals(3, BinaryTree.heightRecursive(root));
        assertEquals(3, BinaryTree.heightIterative(root));
    }

    @Test
    public void testComplexUnbalancedTree() {
        // Structure:
        //        1
        //      /   \
        //     2     5
        //    /       \
        //   3         6
        //  /
        // 4 (left height = 4, right height = 2 → total height = 4)
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.left = new Node(4);
        root.right = new Node(5);
        root.right.right = new Node(6);

        assertEquals(4, BinaryTree.heightRecursive(root));
        assertEquals(4, BinaryTree.heightIterative(root));
    }
}