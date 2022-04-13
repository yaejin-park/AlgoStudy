package yejin.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int W,H;
	static int map[][];
	static boolean visited[][];
	static long max = 0;
	
	//상하좌우, 대위(좌우)4~5 , 대하(좌우)6~7
	static int dr[] = {-1,1,0,0,-1,-1,1,1};
	static int dc[] = {0,0,-1,1,-1,1,-1,1};
	
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H+1][W+1];	//1부터 시작하게
			for (int i = 1; i < H+1 ; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j =1 ; j < W+1; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 0;
			for (int i = 1; i < H+1; i++) {
				for (int j = 1; j < W+1; j++) {
					visited = new boolean[H+1][W+1];
					visited[i][j] = true;
					dfs(i,j,1,map[i][j]);
					
					visited = new boolean[H+1][W+1];
					visited[i][j] = true;
					around(i,j,1,map[i][j]);
				}
			}
			
			sb.append("#"+t+" "+max*max+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int r, int c, int cnt, int sum) {
		if(cnt==4) {
			max = Math.max(max, sum);
			//최대값 비교
			return;
		}
		
		for (int i = 0; i < 8; i++) {
			//짝수열이면서 대각선 위는 건너뛰기
			if(c%2==0 && (i==4||i==5)) continue;
			if(c%2==1 && (i==6||i==7)) continue;
			
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			//경계검사
			if(nr<1 || nc<1 || nr>H || nc>W) continue;
			
			if(visited[nr][nc]) continue;
			visited[nr][nc] = true;
			dfs(nr,nc,cnt+1,sum+map[nr][nc]);
			visited[nr][nc] = false;
		}
	}
	
	private static void around(int r, int c, int cnt, int sum) {
		if(cnt==4) {
			max = Math.max(max, sum);
			//최대값 비교
			return;
		}
		
		for (int i = 0; i < 8; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			//경계검사
			if(nr<1 || nc<1 || nr>H || nc>W) continue;
			
			if(visited[nr][nc]) continue;
			visited[nr][nc] = true;
			around(r,c,cnt+1,sum+map[nr][nc]);
			visited[nr][nc] = false;
		}
	}
	
	
}
