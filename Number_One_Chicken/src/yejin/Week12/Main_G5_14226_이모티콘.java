package yejin.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_G5_14226_이모티콘 {
	//스크린 / 카피
	static boolean dp[][] = new boolean[2000][2000];
	static int S;
	
	static class Emoji{
		int screen;
		int copy;
		int time;
		
		public Emoji(int screen, int copy, int time) {
			super();
			this.screen = screen;
			this.copy = copy;
			this.time = time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = Integer.parseInt(br.readLine());
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Emoji> queue = new LinkedList<>();
		queue.offer(new Emoji(1, 0, 0));
		dp[1][0] = true;
		
		while(!queue.isEmpty()) {
			Emoji cur = queue.poll();
			int screen = cur.screen;
			int copy = cur.copy;
			int time = cur.time;
			
			if(screen == S) {
				return time;
			}
			
			//1.모두 복사
			if(!dp[screen][screen]) {
				dp[screen][screen] = true;
				queue.offer(new Emoji(screen, screen, time+1));
			}
			//2.붙여넣기
			if(screen+copy<2000 && copy !=0 &&!dp[screen+copy][copy]) {
				dp[screen+copy][copy] = true;
				queue.offer(new Emoji(screen+copy, copy, time+1));
			}
			if( screen > 0 &&!dp[screen-1][copy]) {
				dp[screen-1][copy] = true;
				queue.offer(new Emoji(screen-1, copy, time+1));
			}
		}
		
		return -1;
	}

}
