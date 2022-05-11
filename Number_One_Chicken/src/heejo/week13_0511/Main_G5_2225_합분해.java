package week13_0511;

import java.util.Scanner;

/*
 * 순서 상관있다면, 중복순열?
 */
public class Main_G5_2225_합분해 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 만들어야 하는 합
		int K = sc.nextInt();	//사용되는 정수의 개수 개수
		int[][] DP = new int[K+1][N+1]; // k번 더해서 n이 되는 경우의 수

		for (int i = 1; i <= K; i++) {
			DP[i][0] = 1;
		}
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				DP[i][j] = (DP[i][j - 1] + DP[i - 1][j]) % 1000000000;
			}
		}
		System.out.println(DP[K][N]);

	}
}
