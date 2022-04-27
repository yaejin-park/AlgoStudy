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

public class Main_G4_2573_빙산 {
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
					iceList.add(new Node(i, j));	//빙산 좌표 저장
				}
			}
		}
			
		int time = 0;		//분리되기까지 걸리는 시간
		while(ice >0) {
			time++;	//시간 증가
			//맵 복사
			tempMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempMap[i][j] = map[i][j];
				}
			}
			
			//빙산하나씩 꺼내서
			for (int j = 0, end = iceList.size(); j < end; j++) {
				Node cur = iceList.get(j);
				
				//4방 체크
				for (int i = 0; i < 4; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					
//					if(nr<0 || nc<0 || nr>=N || nc>=M) continue;	//경계체크
					
					if(map[nr][nc] == 0) {						//바다면
						tempMap[cur.r][cur.c]--;				//녹이기
						
						if(tempMap[cur.r][cur.c] == 0) {		//녹인 곳이 0이되면
							iceList.remove(j);
							end--;
							j--;
							ice--;
							break;
						}
					}
				}
			}
			
			//얼음 다 녹았으면
			if(ice == 0) {	//분리안됐으므로 0 출력
				System.out.println(0);
				break;
			}
			
			//맵 복사
			for (int i = 1; i < N-1; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = tempMap[i][j];
				}
			}
			
			//하나로 이어져있는지 체크
			if(!isOne(ice)) {	//떨어졌으면
				System.out.println(time);
				break;
			}
		}
	}
	
	public static boolean isOne(int ice) {
		visited = new boolean[N][M];
		Queue<Node> queue = new LinkedList<>();
		visited[iceList.get(0).r][iceList.get(0).c] = true;
		ice--;
		queue.offer(iceList.get(0));
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
//				if(nr<0 || nc<0 || nr>=N || nc>=M) continue;	//경계검사
				
				if(visited[nr][nc] || map[nr][nc] ==0) continue;//방문검사&빙산아니면
				visited[nr][nc] = true;	//방문체크
				ice--;					//안체크된 빙산수 -1
				
				if(ice==0) {	//빙산 다 체크됐으면
					return true;//하나로 다 이어졌으므로 true리턴
				}
				
				queue.offer(new Node(nr, nc));
			}
		}
		
		return false;			//빙산 시작점에서 다 돌았는데 아직 체크안된 빙산있으면 분리됐으므로 false 리턴
	}
}
