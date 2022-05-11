package yejin.Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 두 동전 중 하나만 떨어트리기
 */

public class Main_G4_16197_두동전 {
	static int N,M;
	
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static Loc coin[] = new Loc[2];
	static int answer = -1;
	static char map[][];
	static boolean visited[][][][];
	
	static class Loc{
		int r;
		int c;
		int cnt;
		
		public Loc(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int idx = 0;
		
		visited = new boolean[N][M][N][M];
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char cur = str.charAt(j);
				map[i][j] = cur;
				if(cur =='o') {
					coin[idx++] = new Loc(i, j, 0);
				}
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Loc> queue1 = new LinkedList<>();
		Queue<Loc> queue2 = new LinkedList<>();
		queue1.add(coin[0]);
		queue2.add(coin[1]);
		visited[coin[0].r][coin[0].c][coin[1].r][coin[1].c] = true;
		
		while(!queue1.isEmpty() || !queue2.isEmpty()) {
			Loc coin1 = queue1.poll();
			Loc coin2 = queue2.poll();
			
			if(coin1.cnt >=10) {
				return -1;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr1 = coin1.r + dr[i];
				int nc1 = coin1.c + dc[i];
				int nr2 = coin2.r + dr[i];
				int nc2 = coin2.c + dc[i];
				
				//둘다 떨어지면
				if((nr1< 0 || nr1>=N || nc1 <0 || nc1>=M) && (nr2<0 || nr2>=N || nc2 <0 || nc2>=M)) continue;
				
				//하나만 떨어지면 
				if(((nr1< 0 || nr1>=N || nc1 <0 || nc1>=M) && (nr2>=0 && nr2 <N && nc2 >=0 && nc2 < M))||
						(nr2< 0 || nr2>=N || nc2 <0 || nc2>=M) && (nr1>=0 && nr1 <N && nc1 >=0 && nc1 < M)) {
					return coin1.cnt+1;
				}
				
				//둘 다 안이면
				//둘 중에 하나라도 벽이면 continue
				if(map[nr1][nc1] =='#') {
					nr1 = coin1.r;
					nc1 = coin1.c;
				}
				if(map[nr2][nc2] =='#') {
					nr2 = coin2.r;
					nc2 = coin2.c;
				}
				
				if(visited[nr1][nc1][nr2][nc2]) continue;
				visited[nr1][nc1][nr2][nc2] = true;
				
				queue1.add(new Loc(nr1, nc1, coin1.cnt+1));
				queue2.add(new Loc(nr2, nc2, coin2.cnt+1));
			}
		}
		return -1;
	}

}
