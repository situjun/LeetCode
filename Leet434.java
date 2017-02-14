public class Solution {
    public int countSegments(String s) {
        if(s == null || s.equals("")) return 0;
        s = s.trim();
        int count = 0;
        boolean tag = false;
        for(int i =0;i<=s.length()-1;i++){
            if(!tag && s.charAt(i) != ' '){
                count++;
                tag =true;
            } else if(s.charAt(i) == ' '){
                tag = false;
            }
        }
        return count;
    }
}