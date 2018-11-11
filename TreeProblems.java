
package treeproblems;

import java.util.ArrayDeque;

public class TreeProblems {

    public static void main(String[] args) {
        TreeNode root = randomBinaryTree(20);
        
        System.out.println( "Height: " + root.height() );
        System.out.println( "Length of longest path: " + root.longestPath() );
        root.printBinary();
        
    }
    
    // Return root of a random tree with n nodes
    public static TreeNode randomBinaryTree( int n ) {
        int nodes = 1;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        ArrayDeque<TreeNode> backup = new ArrayDeque<>();
        TreeNode root = new TreeNode();
        queue.add(root);
        
        while ( nodes < n ) {
            TreeNode node;
            if( queue.isEmpty() )
                node = backup.remove();
            else
                node = queue.remove();
            if( Math.random() < 0.5 ) {
                queue.add( new TreeNode(node) );
                nodes++;
            }
            if( node.getChildren().size() < 2 )
                backup.add( node );
        }
        
        return root;
    }
    
}
