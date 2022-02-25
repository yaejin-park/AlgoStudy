package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B1_2999_박예진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String msg = br.readLine();
		
		int cnt = msg.length();
		
		int R = 0;
		int C = 0;
		
		for (int i = 1; i <= cnt; i++) {
			if(cnt%i ==0) {
				//i와 cnt/i 중에서 작은 값을 R에 넣기
				int tempR = Math.min(i, cnt/i);
				
				//R은 그동안 저장한 R중에서 가장 큰값
				R = Math.max(R, tempR);
			}
		}
		
		C = cnt/R;
		
		//행렬 만들기
		char decode [][] = new char[R][C];
		
		int index = 0;
		//행렬 저장
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				decode[j][i] = msg.charAt(index++);
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(decode[i][j]);
			}
		}
		System.out.println(sb);
	}
}
