package yejin.Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 나 왜 DP 손도 못대겠찌..?ㅎㅎ
 * 구글링했어요 구글링했는데도 이해하는데 좀 걸렸음ㅎㅎ
 * 
 */
public class Main_G5_2225_합분해 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long dp[][] = new long[K+1][N+1];	//K는 1부터K까지 , N는 0부터 N까지
		//점화식
//		dp[K][N] = dp[K][N-1] + dp[K-1][N];
		
		//초기값 세팅
		//K가 1일떄
		for (int i = 0; i < N+1; i++) {
			dp[1][i] = 1;
		}
		//N이 0일때
		for (int i = 0; i < K+1; i++) {
			dp[i][0] = 1;
		}
		
		for (int i = 2; i < K+1; i++) {
			for (int j = 1; j < N+1; j++) {
				dp[i][j] = (dp[i][j-1]%1000000000 + dp[i-1][j]%1000000000)%1000000000;
			}
		}
		
		System.out.println(dp[K][N]);
	}

}
