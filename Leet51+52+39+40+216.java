public class Solution {
    
    //key point:虽然不算快，但因为模板化，记起来比较容易
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        boolean[] used = new boolean[n];
        helper(list,item,n,used);
        //for(List<String> i:list) System.out.println(i);
        return list;
    }
    public void helper(List<List<String>> list,List<Integer> item,int n,boolean[] used){
        if(item.size() == n){
            List<String> sol = new ArrayList<>();
            for(Integer q:item){
                char[] arr = new char[n];
                Arrays.fill(arr,'.');
                arr[q] = 'Q';
                sol.add(new String(arr));
            }
            
            /**
            
            for(Integer q:item){
                String tmp = "";
                for(int j = 0;j<=n-1;j++){
                    if(q == j)tmp = tmp+"Q";
                    else tmp = tmp+".";
                }
                sol.add(tmp);
            }
            
            **/
            
            list.add(sol);
        } else {
            for(int i = 0;i<=n-1;i++){
                int iSize = item.size();
                //Key point: <= 1 包含位于正下方和对角线
                //Important:Corner case:5  不仅是不能相邻位置出现在对角线上，隔着多行也不能出现在对角线上
                //PS:对角线非常不好判断
                //if(used[i] || iSize>=1 && Math.abs(item.get(iSize-1)-i) == 1) continue;
                boolean tag = false;
                if(used[i]) continue;
                for(int j = 0;j<=iSize-1;j++){
                    if(Math.abs(iSize-j) == Math.abs(i - item.get(j))) tag = true;
                }
                if(tag) continue;
                item.add(i);
                used[i] = true;
                helper(list,item,n,used);
                used[i] = false;
                item.remove(item.size()-1);
            }
        }
    }
}

public class Solution {
    public int totalNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        boolean[] used = new boolean[n];
        helper(list,item,n,used);
        //for(List<String> i:list) System.out.println(i);
        //Key:偷个懒，直接转成Set Set<List<String>> set = new HashSet<>(list);
        //注意转换方法
        Set<List<String>> set = new HashSet<>(list);
        
        return set.size();
    }
    public void helper(List<List<String>> list,List<Integer> item,int n,boolean[] used){
        if(item.size() == n){
            List<String> sol = new ArrayList<>();
            for(Integer q:item){
                char[] arr = new char[n];
                Arrays.fill(arr,'.');
                arr[q] = 'Q';
                sol.add(new String(arr));
            }
            
            /**
            
            for(Integer q:item){
                String tmp = "";
                for(int j = 0;j<=n-1;j++){
                    if(q == j)tmp = tmp+"Q";
                    else tmp = tmp+".";
                }
                sol.add(tmp);
            }
            
            **/
            
            list.add(sol);
        } else {
            for(int i = 0;i<=n-1;i++){
                int iSize = item.size();
                //Key point: <= 1 包含位于正下方和对角线
                //Important:Corner case:5  不仅是不能相邻位置出现在对角线上，隔着多行也不能出现在对角线上
                //PS:对角线非常不好判断
                //if(used[i] || iSize>=1 && Math.abs(item.get(iSize-1)-i) == 1) continue;
                boolean tag = false;
                if(used[i]) continue;
                for(int j = 0;j<=iSize-1;j++){
                    if(Math.abs(iSize-j) == Math.abs(i - item.get(j))) tag = true;
                }
                if(tag) continue;
                item.add(i);
                used[i] = true;
                helper(list,item,n,used);
                used[i] = false;
                item.remove(item.size()-1);
            }
        }
    }
}

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        //Key:排序关键
        Arrays.sort(candidates);
        helper(list,item,candidates,target,0);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] candidates,int target,int sum){
        if(sum == target){
            list.add(new ArrayList<>(item));
        } else if(sum<target){
            for(int i = 0;i<=candidates.length-1;i++){
                //Key:也是只存在非递增顺序的list，
                //即[[2,2,3],[2,3,2],[3,2,2],[7]] 是错误的.所以加一个下面的判断
                //Key:这是通用方法，但是复杂度的确太高了.....
                if(item.size() >0 && candidates[i] < item.get(item.size()-1)) continue;
                //Key:如果用下面这个判断降复杂度，那么必须配合排序 Arrays.sort(candidates);
                //否则处理：[6,7,2,3] 7 这种无序就会出错
                if(sum+candidates[i]>target) break;
                item.add(candidates[i]);
                helper(list,item,candidates,target,sum+candidates[i]);
                item.remove(item.size()-1);
            }
        }
    }
}

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //Key:懒方法，套模板，加个set筛选
        Set<List<Integer>> list = new HashSet<>();
        List<Integer> item = new ArrayList<>();
        //Key:排序关键
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        helper(list,item,candidates,target,0,used,0);
        List<List<Integer>> result = new ArrayList<>(list);
        return result;
    }
    public void helper(Set<List<Integer>> list,List<Integer> item,int[] candidates,int target,int sum,boolean[] used,int start){
        if(sum == target){
            list.add(new ArrayList<>(item));
        } else if(sum<target){
            for(int i = start;i<=candidates.length-1;i++){
                //Key:也是只存在非递增顺序的list，
                //即[[2,2,3],[2,3,2],[3,2,2],[7]] 是错误的.所以加一个下面的判断
                //Key:这是通用方法，但是复杂度的确太高了.....
                
                if(item.size() >0 && candidates[i] < item.get(item.size()-1) || used[i]) continue;
                //Key:如果用下面这个判断降复杂度，那么必须配合排序 Arrays.sort(candidates);
                //否则处理：[6,7,2,3] 7 这种无序就会出错
                if(sum+candidates[i]>target) break;
                item.add(candidates[i]);
                used[i] = true;
                helper(list,item,candidates,target,sum+candidates[i],used,i+1);
                used[i] = false;
                item.remove(item.size()-1);
            }
        }
    }
}

public class Solution {
    //combinationSum3 指的是该题型第三道题.....
    public List<List<Integer>> combinationSum3(int k, int n) {
        //Key:similar to permutations
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        helper(list,item,0,n,k);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int sum,int n,int k){
        if(item.size() == k && sum == n){
            list.add(new ArrayList<>(item));
        } else {
            int i = item.size() == 0?1:item.get(item.size()-1)+1;
            for(;i<=9;i++){
                //Key:判断条件要仔细想一下
                if((sum+i) > n || item.size()>=k) break;
                item.add(i);
                helper(list,item,sum+i,n,k);
                item.remove(item.size()-1);
            }
        }
    }
}