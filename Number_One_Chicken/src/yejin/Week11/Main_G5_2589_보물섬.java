package yejin.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_2589_보물섬 {
	static int R, C;
	static char map[][];
	static boolean visited[][];
	
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static int max = 0;
	
	static class Node{
		int r;
		int c;
		int time;
		public Node(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='L') {
					bfs(i,j);
				}
			}
		}
		System.out.println(max);
	}

	private static void bfs(int r, int c) {
		visited = new boolean[R][C];
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(r, c, 0));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			max = Math.max(max, cur.time);
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				
				if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
				
				if(visited[nr][nc] || map[nr][nc] =='W') continue;	//방문했거나 바다면
				visited[nr][nc] = true;
				queue.offer(new Node(nr, nc, cur.time+1));
			}
		}
	}
}
