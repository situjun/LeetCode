class ZeroOne{
	public static void main(String[] args){
		int[] V = {6,3,5,4,6};
		int[] N = {2,2,6,5,4};
		int limit = 10;
		int max = 0;
		int[][] arr = new int[N.length+1][limit+1];
		//matrix size is N*(Package volume)
		for(int i =0;i<=N.length;i++){
			arr[i][0] = 0 ;
		}
		for(int i=0;i<=limit;i++){
			arr[0][i] = 0;
		}
		for(int i =1;i<=N.length;i++){
			for(int j = 1;j<=limit;j++){
				if(j-N[i-1] >=0){
					arr[i][j] = Math.max(arr[i-1][j],arr[i-1][j-N[i-1]]+V[i-1]);
				} else {
					arr[i][j] = arr[i-1][j];
				}
			}
		}
		System.out.println("The max value is"+arr[N.length][limit]);
		System.out.println("Entire maxtrix is:");
		for(int i=1;i<=N.length;i++){
			for(int j =1;j<=limit;j++){
				System.out.print(arr[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
}