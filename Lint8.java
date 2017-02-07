public class Solution {
    /**
     * @param str: an array of char
     * @param offset: an integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        //Corner case:"cppjavapy", 25
        //需要处理offset大于length
        //Corner case:"", 10
        if(str.length != 0){
            int index = str.length-offset%str.length;
            StringBuilder sb = new StringBuilder();
            for(int i = index;i<=str.length-1;i++) sb.append(str[i]);
            for(int i = 0;i<=index-1;i++) sb.append(str[i]);
            String tmp = sb.toString();
            for(int i = 0;i<=str.length-1;i++) str[i] = tmp.charAt(i);
        } 
        
    }
}