package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 추의 무게 조합 방법
 * 1. 추 하나 그대로의 무게
 * 2. 현재 추의 무게에 새로운 추 더하기 (구슬  <-> 추+새로운 추) 
 * 3. 현재 추의 무게에 반대편에 새로운 추 더해서 총 무게 빼기 (구슬+새로운 추 <-> 추)
 */

public class Main_2629_양팔저울_google {
	static int N;
	static int[] weight;
	static boolean[][] result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer str = new StringTokenizer(br.readLine());
		weight = new int[N];
		result = new boolean[N+1][40001];	//구슬의 무게가 40000이하이기 때문에 그 이상은 불필요
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(str.nextToken());
		}

		dp(0,0);
		
		int T = Integer.parseInt(br.readLine());
		str = new StringTokenizer(br.readLine());
		for(int i=0; i<T; i++) {
			int w = Integer.parseInt(str.nextToken());

			if(result[N][w]) {
				System.out.print("Y ");
			}else {
				System.out.print("N ");
			}
		}
	}

	static void dp(int cnt, int num) {
		if(result[cnt][num]) {
			return;
		}
		
		result[cnt][num] = true;

		if(cnt == N) {
			return;
		}

		dp(cnt+1, num+ weight[cnt]);
		dp(cnt+1, num);
		dp(cnt+1, Math.abs(num- weight[cnt]));

	}
}
