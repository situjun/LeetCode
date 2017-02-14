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
    public boolean isSymmetric(TreeNode root) {
        //Key:这道题思路和一般的tree思路不太一样。有些绕...
        //这里如果判断current node本身的话，不太好判断。所以向上一层，判断他们的parent那层
        //递归是按照层级判断的
        if(root == null) return true;
        return helper(root.left,root.right);
    }
    public boolean helper(TreeNode left,TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return helper(left.left,right.right) && helper(left.right,right.left);
    }
}