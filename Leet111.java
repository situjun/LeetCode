/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:×öÂé·³ÁË....
    int result = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        helper(root,1);
        return result;
    }
    public void helper(TreeNode node,int min){
        if(node == null) return;
        if(node.left == null && node.right == null){
            result = Math.min(result,min);
        } else {
            helper(node.left,min+1);
            helper(node.right,min+1);
        }
        
    }
}