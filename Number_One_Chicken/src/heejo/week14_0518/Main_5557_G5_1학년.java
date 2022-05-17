package week14_0518;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5557_G5_1학년 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num[] = new int[N];
		for(int i =0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		long dp[][] = new long[N][21];	//0~20까지.	2^63을 위해 long으로 변환
		dp[0][num[0]] = 1;				//시작부분 넣고 시작
		for(int i = 1; i<N-1; i++) {
			for(int j = 0; j<21; j++) {
				if(dp[i-1][j]>0) {
					if(j+num[i]<=20) {
						dp[i][j+num[i]] += dp[i-1][j];
					}
					if(j-num[i]>=0) {
						dp[i][j-num[i]] += dp[i-1][j];
					}
				}
			}
		}
		System.out.println(dp[N-2][num[N-1]]);
	}
}
