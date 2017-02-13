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
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root,sum,0);
    }
    public boolean helper(TreeNode node,int target,int sum){
        if(node!=null){
            if(node.left == null && node.right == null){
               if(sum+node.val == target) return true;  
            } else {
                return helper(node.left,target,sum+node.val)||helper(node.right,target,sum+node.val)?true:false;
            }
        }
        return false;
    }
}