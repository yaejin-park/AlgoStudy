package yejin.Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * Paper 클래스
 * A > B 로 되게 저장.
 * A 내림차순 -> B 내림차순
 * 
 * Paper를 LIS DP
 * 
 * 
 */
import java.util.StringTokenizer;

public class Main_G4_2643_색종이올려놓기 {
	static class Paper implements Comparable<Paper>{
		int A;
		int B;
		public Paper(int a, int b) {
			super();
			A = a;
			B = b;
		}
		@Override
		public int compareTo(Paper o) {
			//A 큰 순 -> B 큰 순
			if(this.A == o.A) return  o.B-this.B;
			else	return o.A-this.A;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());;	//색종이 장수
		
		Paper paper[] = new Paper[N];
		int LIS[] = new int[N];
		
		//입력받기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			//더 큰수를 A에 넣기
			if(A > B) {
				paper[i] = new Paper(A, B);
			} else {
				paper[i] = new Paper(B, A);
			}
		}
		//정렬
		Arrays.sort(paper);
		
		//DP+최장 수열
		int max = 0;
		for (int i = 0; i < N; i++) {		//현재 수
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {	//비교할 수(첫번째 수~현재수)
				//현재수가 비교할수보다 같거나 작으면 && 최장수열 크기가 작으면
				if(paper[i].A <= paper[j].A && paper[i].B <= paper[j].B
					&& LIS[i] < LIS[j]+1) {
					LIS[i] = LIS[j]+1;
				}
			}
			max = Math.max(max, LIS[i]);
		}
		System.out.println(max);
	}

}
