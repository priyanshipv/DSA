package dsa.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class LevelOrder {

    // Definition for a binary tree node
    private static class TreeNode {
        int val; // Value of the node
        TreeNode left; // Left child of the node
        TreeNode right; // Right child of the node

        // Default constructor
        TreeNode() {}

        // Constructor with value parameter
        TreeNode(int val) {
            this.val = val;
        }

        // Constructor with value, left child, and right child parameters
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Method to perform level order traversal of a binary tree
    public List<List<Integer>> levelOrder(TreeNode root) {
        // List to store the level of the tree
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        // If the root is null, return the empty list
        if (root == null) return ans;

        // Queue to store the nodes of the tree
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // Add the root node to the queue
        queue.add(root);
        // Variable to keep track of the current level
        int level = 0;

        // While the queue is not empty
        while (!queue.isEmpty()) {
            // Add a new list for the current level
            ans.add(new ArrayList<Integer>());

            // Get the number of nodes at the current level
            int level_length = queue.size();
            // Iterate over all nodes at the current level
            for (int i = 0; i < level_length; i++) {
                // Remove the node from the queue
                TreeNode node = queue.remove();
                // Add the node's value to the current level list
                ans.get(level).add(node.val);

                // If the left child is not null, add it to the queue
                if (node.left != null) queue.add(node.left);
                // If the right child is not null, add it to the queue
                if (node.right != null) queue.add(node.right);
            }
            // Move to the next level
            level++;
        }
        // Return the list of ans
        return ans;
    }
}