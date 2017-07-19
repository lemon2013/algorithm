package com.pty.dtgh;

public class MatrixChain {
	public static void getMatrixChanOrder(int[] p) {
		int n = p.length;
		long[][] dp = new long[n][n];
		int[][] s = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = 0;
		}
		for (int l = 2; l < n; l++) {
			for (int i = 1; i <= n - l; i++) {
				int j = i + l - 1;
				dp[i][j] = Long.MAX_VALUE;
				for (int k = i; k < j; k++) {
					long temp = dp[i][j];
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
					if(temp!=dp[i][j]){
						s[i][j]=k;
					}
				}
			}
		}
		System.out.println(dp[1][n-1]+" "+s[1][n-1]);
		printOptimal(s,1,n-1);
	}
	public static void printOptimal(int[][] s,int i,int j){
		if(i==j){
			System.out.print("A"+i);
		}else{
			System.out.print("(");
			printOptimal(s,i,s[i][j]);
			printOptimal(s,s[i][j]+1,j);
			System.out.print(")");
		}
	}
	public static void main(String[] args) {
//		int[] p = { 30, 35, 15, 5, 10, 20, 25 };
		int[] p = { 5, 10, 3, 12, 5, 50, 6 };
		getMatrixChanOrder(p);
	}
}
