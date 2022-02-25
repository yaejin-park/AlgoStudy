package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2_2798_박예진 {
	static int N,M;
	static int arr[];
	static int answer;
	static int gap = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());	//M을 넘지 않으면서 M에 가깝게
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//조합
		Combination(0, 0, 0);
		
		System.out.println(answer);
	}

	private static void Combination(int cnt,int start, int sum) {
		if(sum > M) return;
		
		if(cnt == 3) {
			//M과 가까우면
			if(M-sum < gap) {
				//합을 갱신
				gap = M-sum;
				answer = sum;
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			Combination(cnt+1, i+1, sum+arr[i]);
		}
	}
}
