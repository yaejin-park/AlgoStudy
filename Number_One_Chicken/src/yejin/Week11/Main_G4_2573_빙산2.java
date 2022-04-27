package yejin.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아이디어
 * 1. 빙산 하나인지 -> bfs로 찾기 (빙산이면 방문체크, 다 방문체크되면 끝)
 * 2. 
 * 
 */

public class Main_G4_2573_빙산2 {
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static int N, M;
	static int ice;
	static int map[][], tempMap[][];
	static boolean visited[][];
	static ArrayList<Node> iceList;
	
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		iceList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int cur = Integer.parseInt(st.nextToken());
				map[i][j] = cur;
				if(cur != 0) {	//
					ice++;
					iceList.add(new Node(i, j));
				}
			}
		}
		
		int time = 0;
		int tempIce = ice;
		while(tempIce >0) {
			time++;
			tempIce = ice;
			//맵 복사
			tempMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempMap[i][j] = map[i][j];
				}
			}
			
			//빙산하나씩 꺼내서
			for (int j = 0, end = iceList.size(); j < end; j++) {
				Node ice = iceList.get(j);
				
				//4방 체크
				for (int i = 0; i < 4; i++) {
					int nr = ice.r + dr[i];
					int nc = ice.c + dc[i];
					
					if(nr<0 || nc<0 || nr>=N || nc>=M) continue;	//경계체크
					
					if(map[nr][nc] == 0) {
						tempMap[ice.r][ice.c]--;				//바다면 녹이기
						if(tempMap[ice.r][ice.c] == 0) {		//빙산 다 녹으면
							iceList.remove(j);
							end--;
							j--;
							break;
						}
					}
				}
			}
			//맵 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = tempMap[i][j];
				}
			}
			if(tempIce == 0) {
				System.out.println(0);
				break;
			}
			ice = tempIce;
			
			//하나로 이어져있는지 체크
			if(!isOne()) {
				System.out.println(time);
				break;
			}
		}
	}
	
	public static boolean isOne() {
		int size = iceList.size();
		if(size ==0 ) return true;
		
		visited = new boolean[N][M];
		Queue<Node> queue = new LinkedList<>();
		visited[iceList.get(0).r][iceList.get(0).c] = true;
		size--;
		queue.offer(iceList.get(0));
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
				
				if(visited[nr][nc] || map[nr][nc] ==0) continue;
				visited[nr][nc] = true;
				size--;
				if(size==0) {
					return true;
				}
				
				queue.offer(new Node(nr, nc));
			}
		}
		
		return false;
	}
}
