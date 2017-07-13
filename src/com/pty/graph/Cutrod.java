package com.pty.dtgh;

import java.math.BigInteger;

public class Cutrod {
	public static long getCutRod(long[] p, int n) {
		if (n == 0)
			return 0;
		long q = Long.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			q = Math.max(q, p[i] + getCutRod(p, n - i));
		}
		return q;
	}

	public static String getCutRodDP(long[] p, int n) {
		long[] dp = new long[n + 1];
		int[] length = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			long q = Long.MIN_VALUE;
			for (int j = 1; j <= i; j++) {
				long temp = q;
				q = j < p.length ? Math.max(q, p[j] + dp[i - j]) : Math.max(q, dp[j] + dp[i - j]);
				if (temp < q) {
					length[i] = j;
				}
			}
			dp[i] = q;
		}
		int temp = n;
		StringBuffer str = new StringBuffer("r" + n + "=" + dp[n] + "," + "切割方案" + n + "=");
		while (temp > 0) {
			str.append(length[temp]);
			str.append("+");
			temp = temp - length[temp];
		}
		str.delete(str.length() - 1, str.length());
		return str.toString();
	}

	public static BigInteger getFibonacci(int n) {
		BigInteger[] dp = new BigInteger[n + 1];
		dp[0] = new BigInteger("0");
		if (n <= 2) {
			return new BigInteger("1");
		}
		dp[1] = new BigInteger("1");
		dp[2] = new BigInteger("1");
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1].add(dp[i - 2]);
		}
		return dp[n];
	}

	public static void main(String[] args) {

		long[] p = new long[11];
		p[0] = 0;
		p[1] = 1;
		p[2] = 5;
		p[3] = 8;
		p[4] = 9;
		p[5] = 10;
		p[6] = 17;
		p[7] = 17;
		p[8] = 20;
		p[9] = 24;
		p[10] = 30;
		Long time5 = System.nanoTime();
		System.out.println(getCutRodDP(p, 17));
		Long time6 = System.nanoTime();
		double t4 = (time6 - time5) / 1000000;
		System.out.println("耗时：" + t4 + "ms");
		// Long time7 = System.nanoTime();
		// System.out.println(getFibonacci(7000).toString());
		// Long time8 = System.nanoTime();
		// double t5 = (time8 - time7) / 1000000;
		// System.out.println("耗时："+t5+"ms");
	}
}
