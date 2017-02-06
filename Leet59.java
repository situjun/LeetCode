public class Solution {
    public int[][] generateMatrix(int n) {
        
        int[][] matrix = new int[n][n];
        int x1 = 0,x2 = n-1,y1 = 0,y2 = n-1;
        int count = 1,sum = n*n;
        while(x1<=x2 && y1 <= y2){
            for(int i = y1;i<=y2;i++) matrix[x1][i] = count++;
            x1++;
            for(int i = x1;i<=x2;i++) matrix[i][y2] = count++;
            y2--;
            if(y1<=y2) for(int i = y2;i>=y1;i--) matrix[x2][i] = count++;
            x2--;
            if(x1<=x2) for(int i = x2;i>=x1;i--) matrix[i][y1] = count++;
            y1++;
        }
        return matrix;
    }
}