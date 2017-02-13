public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        //Key:
        //基本的递归的思想，从前往后遍历，对于每一个运算符将其左右子字符串分成两部分，然后计算两边的各种不同的结果来，然后和在一起，这中间不存是不存在加括号的相同发的情况的。
        return helper(input);
    }
    public List<Integer> helper(String input){
        //Key:这里默认了都是合法的运算，所以不会出现运算符出现在最后。也就不用判断input == ""了
        List<Integer> list = new ArrayList<>();
        if(input == null || input.equals("")) return list;
        for(int i = 0;i<=input.length()-1;i++){
            if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
                String a = input.substring(0,i);
                String b = input.substring(i+1);
                List<Integer> left = helper(a);
                List<Integer>  right = helper(b);
                //Key:List<Integer> for each时可以用int
                for(int l:left){
                    for(int r:right){
                        if(input.charAt(i) == '+') list.add(l+r);
                        if(input.charAt(i) == '-') list.add(l-r);
                        if(input.charAt(i) == '*') list.add(l*r);
                    }
                }
            }
        }
        if(list.size() == 0) list.add(Integer.parseInt(input));
        return list;
    }
}