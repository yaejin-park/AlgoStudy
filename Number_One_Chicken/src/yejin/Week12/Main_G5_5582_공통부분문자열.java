package yejin.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * LCS랑 다르다 
 * @author yaejin
 *
 */
public class Main_G5_5582_공통부분문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A = br.readLine();
		String B = br.readLine();
		
		int dp[][] = new int[A.length()+1][B.length()+1];
		
		int max = 0;
		
		for (int i = 1; i < A.length()+1; i++) {		//1
			for (int j = 1; j < B.length()+1; j++) {	//2
				if(A.charAt(i-1) == B.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] +1;
					max = Math.max(max, dp[i][j]);
				} 
			}
		}
		
		System.out.println(max);
	}

}
