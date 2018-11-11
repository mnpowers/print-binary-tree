
package treeproblems;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePrinter {
    
    // Print a binary tree. Trows exception if tree not binary
    public static void print( TreeNode root ) throws IllegalArgumentException {
        if( !root.isBinary() ) 
            throw new IllegalArgumentException( "Tree is not binary" );
        List<StringBuilder> s = binaryToList(root);
        for( StringBuilder build : s )
            System.out.println(build);
        
    }
    
    /*
    Return a list whose entries are the lines that will be printed
    in method print( node ).
    */
    private static List<StringBuilder> binaryToList( TreeNode node ) {
        List<StringBuilder> s = new ArrayList<>();
        int h = node.height();
        List<TreeNode> children = node.getChildren();
        
        if( h == 0 ) {                                      // Base case, tree has a single node
            s.add( new StringBuilder("o") );
            return s;
        }
        
        List<StringBuilder> a = binaryToList( children.get(0) ); // Recursively create list for left child
        List<StringBuilder> lBranch = createLeftBranch(a);       // Create list for left side of picture
        
        List<StringBuilder> rBranch = new ArrayList<>();         // List for right side of picture,
        if( children.size() == 2 ) {                             // empty if no right child
            rBranch = createRightBranch( binaryToList( children.get(1) ) );
        }
        
        int lSize = lBranch.size();
        int lWidth = lBranch.get(0).length();
        int rSize = rBranch.size();
        int rWidth = rBranch.isEmpty() ? 0 : rBranch.get(0).length();
        for( int i = 0; i < Math.max( lSize, rSize ); i++ ) {    // Combine left and right sides into full picture
            StringBuilder build = new StringBuilder();
            if( i < lSize )
                build.append( lBranch.get(i) );
            else
                build.append( spaces(lWidth) );
            if( i == 0 )
                build.append('o');
            else
                build.append(' ');
            if( i < rSize )
                build.append( rBranch.get(i) );
            else
                build.append( spaces(rWidth) );
            s.add(build);
        }
        return s;    
    }
    
    /* 
    Create list for left side of printed picture, using list representing picture 
    of tree rooted at left child. Top part of picture contains edge leading from
    root to child.
    */
    private static List<StringBuilder> createLeftBranch( List<StringBuilder> list ) {
        List<StringBuilder> branch = new ArrayList<>();
        int length = list.get(0).length();
        int root = findNode( list.get(0) );

        if( root == length - 1 ) {            // Special case where node is in top right corner of picture
            branch.add( spaces(length + 1) );
            StringBuilder build = spaces(length);
            build.append('/');
            branch.add(build);
            for( StringBuilder line : list ) {
                line.append(' ');
                branch.add(line);
            }
        } else {
            for( int i = length; i > root; i-- ) {
                StringBuilder build = new StringBuilder();
                for( int j = 0; j < length; j++ ) {
                    if( j == i )
                        build.append('/');
                    else
                        build.append(' ');
                }
                branch.add(build);
            }
            branch.addAll(list);
        }
        return branch;
    }
    
    /* 
    Create list for right side of printed picture, using list representing picture 
    of tree rooted at right child. Top part of picture contains edge leading from
    root to child.
    */
    private static List<StringBuilder> createRightBranch( List<StringBuilder> list ) {
        List<StringBuilder> branch = new ArrayList<>();
        int length = list.get(0).length();
        int rootLoc = findNode( list.get(0) );

        if( rootLoc == 0 ) {                  // Special case where node is in top left corner of picture
            branch.add( spaces(length + 1) );
            StringBuilder build = new StringBuilder("\\");
            build.append( spaces(length) );
            branch.add(build);
            for( StringBuilder line : list ) {
                StringBuilder builder = new StringBuilder(" ");
                builder.append(line);
                branch.add(builder);
            }
        } else {
            for( int i = -1; i < rootLoc; i++ ) {
                StringBuilder build = new StringBuilder();
                for( int j = 0; j < length; j++ ) {
                    if( j == i )
                        build.append('\\');
                    else
                        build.append(' ');
                }
                branch.add(build);
            }
            branch.addAll(list);
        }
        return branch;
    }

    // Return StringBuilder consisting of n whitespaces
    private static StringBuilder spaces( int n ) {
        StringBuilder b = new StringBuilder();
        for( int i = 0; i < n; i++ )
            b.append(" ");
        return b;
    }
    
    // Return index of first character in b equal to 'o'
    private static int findNode( StringBuilder b ) {
        for( int i = 0; i < b.length(); i++ ) {
            if( b.charAt(i) == 'o' )
                return i;
        }
        return -1;
    }

}
