class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeHeight {

    // Recursive height calculation
    public static int heightRecursive(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(heightRecursive(root.left), heightRecursive(root.right));
    }

    // Iterative BFS height calculation
    public static int heightIterative(TreeNode root) {
        if (root == null) return 0;
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        int height = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return height;
    }

    public static void main(String[] args) {
        // Test Case 1: Empty Tree
        TreeNode root = null;
        printTestResult("Empty Tree", 0, root);

        // Test Case 2: Single Node
        root = new TreeNode(1);
        printTestResult("Single Node", 1, root);

        // Test Case 3: Left-Skewed Tree (1-2-3-4)
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        printTestResult("Left-Skewed Tree", 4, root);

        // Test Case 4: Right-Skewed Tree (1-2-3-4)
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        printTestResult("Right-Skewed Tree", 4, root);

        // Test Case 5: Balanced Tree (height = 3)
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        printTestResult("Balanced Tree", 3, root);

        // Test Case 6: Complex Unbalanced Tree
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        printTestResult("Complex Unbalanced Tree", 4, root);
    }

    private static void printTestResult(String testName, int expected, TreeNode root) {
        int recursiveResult = heightRecursive(root);
        int iterativeResult = heightIterative(root);
        
        System.out.println("Test: " + testName);
        System.out.println("Recursive Result: " + recursiveResult);
        System.out.println("Iterative Result: " + iterativeResult);
        System.out.println("Status: " + 
            (recursiveResult == expected && iterativeResult == expected ? "PASS" : "FAIL"));
        System.out.println("--------------------------");
    }
}