package yejin.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 총 경기 30개
 * (30-무승부)/2 가 각각 승,패의 합
 * 무승부는 최소 2개이상의 국가에서 나야한다.
 * 승 합 = 패 합 / 승+패+무승부 = 30 / 무승부 합 짝수 & 무승부 합/2 < 각 국가 무승부합(X)
 * 
 * -> 한 나라와 여러번 경기해도 괜찮다고 생각
 * -> 여러나라와 각각 한번씩 경기로 다시 생각하자..
 * 
 */
public class Main_G5_6987_월드컵_실패 {
	static int play = 30;
	static int[] win = new int[6];
	static int[] same = new int[6];
	static int[] lose = new int[6];
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//입력받기
			for (int j = 0; j < 6; j++) {
				win[j] = Integer.parseInt(st.nextToken());
				same[j] = Integer.parseInt(st.nextToken());
				lose[j] = Integer.parseInt(st.nextToken());
				System.out.println(win[i]+","+same[j]+","+lose[j]);
			}
			
			sb.append(check()?1+" ":0+" ");
		}
		System.out.println(sb);
	}


	private static boolean check() {
		int sameCnt = 0;
		int sameSum = 0;
		int winSum = 0;
		int loseSum = 0;
		
		for (int j = 0; j < 6; j++) {
			winSum += win[j];
			sameSum += same[j];
			loseSum += lose[j];
			
			//각 나라의 승무패 합이 5가 아니면 끝
			if(win[j]+same[j]+lose[j] !=5) return false;
			
			if(same[j]!=0) sameCnt++;
		}
		
		//무승부가 있는데 무승부 국가 개수는 1개라면 X
		if(sameSum != 0 && sameCnt<2) return false;
		
		//무승부 합은 짝수여야함
		if(sameSum %2 ==1) return false;
		
		//이긴횟수 = 진횟수 같아야함
		if(winSum != loseSum) return false;
		
		if(sameSum >0) {
			//무승부합/2보다 각 무승부 수가 크면 안됨
			for (int i = 0; i < 6; i++) {
				if(same[i] > sameSum/2) return false;
			}
		}
		
//		//합
//		if(sameSum + winSum + loseSum != 30) return false;
		
		return true;
	}

}
