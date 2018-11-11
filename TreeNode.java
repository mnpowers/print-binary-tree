
package treeproblems;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    
    private TreeNode parent;
    private List<TreeNode> children;
    
    public TreeNode() {
        children = new ArrayList<>();
    }
    
    public TreeNode( TreeNode parent ) {
        this.parent = parent;
        parent.children.add(this);
        children = new ArrayList<>();
    }
    
    // Return the number of "generations" in tree above current node
    public int depth() {
        if ( parent == null )
            return 0;
        return 1 + parent.depth();
    }
    
    // Return number of "generations" in tree below current node
    public int height() {   
        int maxChildHeight = -1;
        
        for( TreeNode child : children )
            maxChildHeight = Math.max( maxChildHeight, child.height() );
        
        return 1 + maxChildHeight;
    }
    
    // Returns length of longest path in tree
    public int longestPath() {
        int maxHeight = -1;
        int secondMaxHeight = -1;
        int maxPathLength = 0;
        
        for( TreeNode child : children ) {
            int height = child.height();
            if( height > maxHeight ) {
                secondMaxHeight = maxHeight;
                maxHeight = height;
            } else if ( height > secondMaxHeight )
                secondMaxHeight = height;
            
            maxPathLength = Math.max( maxPathLength, child.longestPath() );
        }
        
        return Math.max( maxPathLength, maxHeight + secondMaxHeight + 2 );
    }
    
    // Checks if tree is binary
    public boolean isBinary() {
        if( children.size() > 2 )
            return false;
        for( TreeNode child : children ) {
            if( !child.isBinary() )
                return false;
        }
        return true;
    }
    
    // Prints tree if it is binary. Throws exception if not binary
    public void printBinary() {
        try {
            BinaryTreePrinter.print(this);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public TreeNode getParent() {
        return parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
    
    
}
