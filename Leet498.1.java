public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int x1 = 0,y1 = 0,x2 = matrix.length-1,y2 = matrix[0].length-1;
        int index1 = 0,index2 = 0,count = 0,sum = (x2+1)*(y2+1),tag = 0;
        int[] result = new int[sum];
        while(count<=sum-1){
            //Corner 考虑，分别考虑正好抵达四条边和位于对角线上如何处理....
            while(count<=sum-1 && index1>=0 && index2<=y2) result[count++] = matrix[index1--][index2++];
            if(index2>y2 && index1<0){
                index1 = 1;
                index2 = y2;
            } else if(index1<0){
                index1 =0;
            } else if(index2>y2){
                index2 = y2;
                index1+=2;
            }
            while(count<=sum-1 && index1<=x2 && index2 >=0) result[count++] = matrix[index1++][index2--];
            if(index1>x2 && index2 <0){
                index1=x2;
                index2=1;
            } else if(index1>x2){
                index1 = x2;
                index2 += 2;
            } else if(index2<0){
                index2 = 0;
            }
            //for(int i:result) System.out.print(i+",");
            //System.out.print("\n");
        }
        return result;
    }
}