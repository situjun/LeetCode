/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    
    /**
    
    //Key:just copy
    
    
    public void connect(TreeLinkNode root) {
        //Key:在current node的上一层，parent层操作
        helper(root.left,root.right);
    }
    public TreeLinkNode helper(TreeLinkNode left, TreeLinkNode right){
        
        helper(left.left,left.right).next = helper(right.left,right.right).next;
        
    }
    
    
    ****/
    
    public void connect(TreeLinkNode root) {
        TreeLinkNode level_start=root;
        while(level_start!=null){
            TreeLinkNode cur=level_start;
            while(cur!=null){
                if(cur.left!=null) cur.left.next=cur.right;
                if(cur.right!=null && cur.next!=null) cur.right.next=cur.next.left;
                
                cur=cur.next;
            }
            level_start=level_start.left;
        }
    }
    
    
}