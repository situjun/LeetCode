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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root,p,q);
    }
    public TreeNode helper(TreeNode node,TreeNode p,TreeNode q){
        if(node != null){
            if(node == p || node == q){
                return node;
            } else {
                TreeNode left = helper(node.left,p,q);
                TreeNode right = helper(node.right,p,q);
                return left != null && right != null?node:left==null?right:left;
            }
        }
        return null;
    }
}