package week14_0518;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - 내 자리에 올 수 있는 3가지 경우 중 가장 큰 수가 되는 경우를 찾기
 * - [i][j] 자리에 [i-1][j], [i][j-1], [i-1][j-1]중 하나가 올 수 있다.
 * - 배열 범위를 N+1, M+1로 해서 i-1을 해도 오류가 안나게끔 설정
 */

public class Main_11048_S1_이동하기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int array[][] = new int[N+1][M+1];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i<=N; i++) {
			for(int j =1; j<=M; j++) {
				array[i][j] += Math.max(array[i-1][j-1], Math.max(array[i-1][j], array[i][j-1]));
			}
		}
		
		System.out.println(array[N][M]);
	}
}
