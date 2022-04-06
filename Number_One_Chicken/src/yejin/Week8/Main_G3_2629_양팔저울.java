package yejin.Week8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1. 추 조합 +,- 해서 해당하면 Y 아니면 N (시간복잡도..)
 * 2. 알고리즘 분류 -> 배낭문제..
 * 
 * 구글링
 * 1. 추 하나 그대로
 * 2. 현재 추 + 새로운 추
 * 3. 현재추 : 반대편 새로운 추
 * 
 * 
 * 0< 추 무게 < 500 * 30 = 15,000
 * 0< 구슬 무게 <40,000
 * 
 * @author yaejin
 *
 */
public class Main_G3_2629_양팔저울 {
	static int C,  B;
	static int chu[], ball[];
	static boolean dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//추 입력
		C = Integer.parseInt(br.readLine());
		dp = new boolean[C+1][40001];	//찾는 구슬의 무게 40,000이하, 조합이 되었는지 true, false 저장
		
		//추 무게 저장
		chu = new int[C];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		
		dp(0,0);
		
		//구슬 입력
		B = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B; i++) {
			int ball= Integer.parseInt(st.nextToken());
			
			if(dp[C][ball]) sb.append("Y ");
			else sb.append("N ");
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dp(int cnt, int num) {
		if(dp[cnt][num] == true) return;
		dp[cnt][num] = true;
		
		//추를 다 뽑으면
		if(cnt==C) return;
		
		//추 하나 그대로
		dp(cnt+1, num + chu[cnt]);
		dp(cnt+1, num);
		dp(cnt+1, Math.abs(num-chu[cnt]));
	}

}
