public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        helper(list,item,n,k,1);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int n,int k,int start){
        if(item.size() == k){
            list.add(new ArrayList<>(item));
        } else {
            for(int i = start;i<=n;i++){
                item.add(i);
                helper(list,item,n,k,i+1);
                item.remove(item.size()-1);
            }
        }
    }
}