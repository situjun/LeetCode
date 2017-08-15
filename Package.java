//01 package
//  ********* Item weight and value *************
//  Order: 1  2  3  4  5
//  Value: 6  3  5  4  6
// Weight: 2  2  6  5  4
//F[i][j] 是前i个物品放入容积j中，而不是第i个物品放入j中。（如F[3][5]是前三个物品放入（Order:1&2&3）,而不是仅仅第3个物品。）
public class Package{
	public static void main(String []args){
		int[] val = {6,3,5,4,6};
		int[] weight = {2,2,6,5,4};
		int N,V;
		N = val.length;
		V = 10;
		int[][] F = new int[N+1][V+1];
		for(int i=0;i<=V;i++){
			F[0][i] = 0;
		}
		for(int i=0;i<=N;i++){
			F[i][0] = 0;
		}
		for(int i=1;i<=N;i++){
			for(int j=1;j<=V;j++){
				if(weight[i-1]>j){
					F[i][j] = F[i-1][j];
				} else {
					F[i][j] = Math.max(F[i-1][j],F[i-1][j-weight[i-1]]+val[i-1]);
				}
			}
		}
		for(int i=0;i<=N;i++){
			for(int j=0;j<=V;j++){
				System.out.print(F[i][j]+",");
			}
			System.out.print("\n");
		}
	}
	public void Package(){
		
	}
}