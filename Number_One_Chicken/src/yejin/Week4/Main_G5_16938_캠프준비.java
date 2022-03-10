package yejin.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_16938_캠프준비 {
	static int N, L, R, X;
	static boolean isSelected[];
	static int question[];
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//문제N개
		L = Integer.parseInt(st.nextToken());	//문제난이도합 L보다 크거나 같고,
		R = Integer.parseInt(st.nextToken());	//R보다 작거나 같아야함
		X = Integer.parseInt(st.nextToken());	//가장 어려운 문제와 가장 쉬운 문제 난이도 차 X보다 크거나 같아야함
		
		isSelected = new boolean[N];
		//고를 문제는 2개이상
		
		//가지고 있는 문제 난이도
		question = new int[N];
		st = new StringTokenizer(br.readLine());
		br.close();
		for (int i = 0; i < N; i++) {
			question[i] = Integer.parseInt(st.nextToken());
		}
		
		//부분집합으로 문제 고르기
		subset(0);
		
		System.out.println(answer);
	}

	private static void subset(int cnt) {
		if(cnt == N) {
			int sum = 0;
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					sum += question[i];
					max = Math.max(max, question[i]);
					min = Math.min(min, question[i]);
				}
			}
			if(sum >= L && sum <= R && max-min >=X) {
				answer++;
			}
			return;
		}
		
		isSelected[cnt] = true;
		subset(cnt+1);
		
		isSelected[cnt] = false;
		subset(cnt+1);
	}
}