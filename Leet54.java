public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix.length == 0) return list;
        int x1 = 0,y1 = 0,x2 = matrix.length-1,y2 = matrix[0].length-1;
        int index1 = 0,index2 = 0;
        
        //Key point:下面的判断错误
        //Conrer case:[[2,3]] 如果是下面的判断就会出错
        // while(list.size()< matrix[0].length*matrix.length){
            
            
        //考虑成一个不断内部压缩的状态，然后每次都从左上角循环一遍
        while(x1<=x2 && y1<=y2){    
            for(int i = y1;i<=y2;i++) list.add(matrix[x1][i]);
            x1++;
            for(int i = x1;i<=x2;i++) list.add(matrix[i][y2]);
            y2--;
            //Key point:向左和向上都需要考虑下x1,x2关系和y1,y2关系
            //防止奇数层matrix的影响
            if(x1<=x2) for(int i = y2;i>=y1;i--) list.add(matrix[x2][i]);
            x2--;
            if(y1<=y2) for(int i = x2;i>=x1;i--) list.add(matrix[i][y1]);
            y1++;
        }
        
        return list;
    }
}