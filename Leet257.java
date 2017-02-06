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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        helper(list,root,"");
        return list;
    }
    public void helper(List<String> list,TreeNode node,String path){
        if(node != null){
            if(node.left == null && node.right == null){
                list.add(path+node.val);   
            } else {
                helper(list,node.left,path+node.val+"->");
                helper(list,node.right,path+node.val+"->");
            }
        }
    }
    
}


//写乱了
/***
public List<String> binaryTreePaths(TreeNode root) {
        //Key point:similar to Permutations
        //因为是一直向下的，不用回头。用Permutations方法反而麻烦了.....
        List<String> list = new ArrayList();
        List<Integer> item = new ArrayList();
        //Key point:记得区分出right和left，否则会重复加一遍path
        helper(list,item,root);
        return list;
    }
    public void helper(List<String> list,List<Integer> item,TreeNode node){
        //Key point:写法有问题，如果left为null，但right不为null，那么也会加上。不符题意
        
        /**
        if(node == null){
            if(right == 0){
                String tmp = "";
                for(int i = 0;i <= item.size()-1;i++){
                    if(i != 0) tmp += "->"+item.get(i);
                    else tmp += item.get(i)+"";
                }
                list.add(tmp);
            }
        //Key point:像这种递归没有return的，还是加上else吧
        } else {
            item.add(node.val);
            helper(list,item,node.left,0);
            helper(list,item,node.right,1);
            item.remove(item.size()-1);
        }

        
        if(node != null){
            if(node.right == null && node.left == null){
                String tmp = "";
                for(int i = 0;i <= item.size()-1;i++){
                    if(i != 0) tmp += "->"+item.get(i);
                    else tmp += item.get(i)+"";
                }
                //Key point:因为叶节点未经过递归，所以要手动加一下
                //list.add(tmp);
                if(item.size() == 0) list.add(tmp+node.val);
                else list.add(tmp+"->"+node.val);
            } else {
                item.add(node.val);
                helper(list,item,node.left);
                helper(list,item,node.right);
                item.remove(item.size()-1);
            }
        }
    }

***/