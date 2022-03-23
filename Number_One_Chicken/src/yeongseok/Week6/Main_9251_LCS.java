package yeongseok.Week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * String s1, s2가 있을때 단순하게 생각하면 s1의 부분집합을 s2에서 찾으면된다. -> 시간복잡도 == 2^1000 * 1000 ==> ㅋㅋ
 * 시간 복잡도를 줄여야함...
 * how? 뒤에서부터?, 정렬?, 아! 작은 단위로 나눠서 비교하자
 * 작은 단위로 나누면, 
 * 		1. 작은 단위의 비교에서 나온 결과를 테이블에 저장해야함. -> 테이블 설계 (테이블의 값이 무엇을 의미하는지 정하는 것이 중요하다)
 * 		2. 1에서의 결과를 더 큰 단위의 비교에서 활용해야 한다.	-> 점화식 찾기
 * 		-> DP
 * DP 문제는 점화식을 찾는게 관건이다. 
 * 점화식을 찾는 방법은 테이블 값 간의 관계를 직접 찾아봐야 한다.
 * dp[i][j] == 1)s1[i].equals(s2[j]) => dp[i][j]  = dp[i-1][j-1] + 1;
 * 			   2)not s1[i].equals(s2[j]) => dp[i][j]  = max(dp[i][j-1],dp[i-1][j]) 
 * 
 * DP를 사용하면, 1000번만 연산하면 된다. 
 * 하지만 단순하게 생각했을때(부분집합 구해서 연산)와 비교했을 때 어느 부분의 연산이 줄어들어서 연산 횟수가 감소되는지 정확하게 모르겠다..
 * @author 노영석
 */
public class Main_9251_LCS {
	static String s1;
	static String s2;
	static int dp[][];
	static int max;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		
		dp = new int[s1.length()+1][s2.length()+1];
		
		for(int i = 1; i < s1.length()+1; i++) {
			for(int j = 1; j <s2.length()+1; j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
					max = Integer.max(max,dp[i][j]);
				}else {
					dp[i][j] = Integer.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		
		System.out.println(max);
	}

}










