import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class LeftView {
    // BFS Approach
    public List<Integer> leftviewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result.add(node.value);  // Add first node of each level
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

    // DFS Approach
    private List<Integer> resultdfs = new ArrayList<>();
    private int maxdepth = -1;

    public List<Integer> leftviewDFS(TreeNode root) {
        dfs(root, 0);
        return resultdfs;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth > maxdepth) {
            resultdfs.add(root.value); // Corrected from 'depth' to 'root.value'
            maxdepth = depth;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

    public static void main(String[] args) {
        LeftView leftview = new LeftView();
        TreeNode root1 = new TreeNode(1, 
                new TreeNode(2, new TreeNode(3), new TreeNode(4)), 
                new TreeNode(5));

        System.out.println("BFS Left View: " + leftview.leftviewBFS(root1)); // Expected: [1,2,3]
        System.out.println("DFS Left View: " + leftview.leftviewDFS(root1)); // Expected: [1,2,3]
    }
}
