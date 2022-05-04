package yejin.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_G5_14226_이모티콘2 {
	//스크린 / 카피
	static boolean dp[][] = new boolean[2000][2000];
	static int S;
	static int minTime = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = Integer.parseInt(br.readLine());
		
		dp[1][0] = true;
		dfs(1, 0, 0);
		
		System.out.println(minTime);
	}

	private static void dfs(int screen, int copy, int time) {
		//화면에 S개 이모티콘 만들어지면 리턴
		if(screen == S) {
			minTime = Math.min(minTime, time);
			return;
		}
		
		if(time > minTime) return;
		
		//1.화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
		if(!dp[screen][screen]) {
			dp[screen][screen] = true;
			dfs(screen, screen, time+1);
		}
		//2.클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
		if(copy!= 0 && screen+copy < 2000 && !dp[screen+copy][copy] ) {
			dp[screen+copy][copy] = true;
			dfs(screen+copy, copy, time+1);
		}
		//3.화면에 있는 이모티콘 중 하나를 삭제한다.
		if(screen >0 && !dp[screen-1][copy] ) {
			dp[screen-1][copy]= true;
			dfs(screen-1, copy, time+1);
		}
	}

}
