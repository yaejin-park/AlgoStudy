package yejin.Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 *  문제설명
 *  1. 우, 하로 만 이동
 *  2. 1,1에서 시작해서 N,N에서 탈출
 *  3. 이동하려는 칸보다 현재칸 값이 작으면 비용 1씩증가해서 이동하려는 칸보다 현재칸 값이 커졌을 때 이동 가능
 *  
 *  map : 입력 값 담는 배열
 *  visited : 현재까지 사용한 최소비용 담는 배열(최대값으로 초기화)
 *  
 *  현재 map < 이동 map 이면,  현재 map > 이동 map될때까지 비용 증가
 *  근데 이미 이동 map에 방문했다면, 이동비용이 더 커지면 갈 필요 없음.
 * 
 * @author yaejin
 *
 */

public class Main_G5_배열탈출 {
	static int n;
	static int map[][];
	static int visited[][];
	//우, 하
	static int dr[] = {0, 1};
	static int dc[] = {1, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		//방문체크겸, 최소 금액 저장 배열
		visited = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		//맵 받기
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(0,0);
		
		//출력
		System.out.println(visited[n-1][n-1]);
	}

	private static void bfs(int r, int c) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(r, c));
		visited[r][c] = 0;
		
		while(!queue.isEmpty()) { 
			Node cur = queue.poll();
 
			for (int i = 0; i < 2; i++) {
				int cr = cur.r + dr[i];
				int cc = cur.c + dc[i];
				
				//경계검사
				if(cr>=0 && cr<n && cc>=0 && cc<n) {
					//현재칸의 비용 임시로 담기
					int tempVal = visited[cur.r][cur.c]; 
					
					
					int curMap = map[cur.r][cur.c];	//현재칸 값
					int moveMap = map[cr][cc];		//방문할 칸 값
					//현재칸 값이 이동할칸 값보다 커질때까지 값 증가	
					while(curMap <= moveMap) {
						curMap++;	//현재칸 값 증가
						tempVal++;	//비용 증가
						//이미 방문 && 과거 비용보다 더 커지면 그만두기
						if(moveMap != Integer.MAX_VALUE && tempVal > visited[cr][cc]) {
							break;
						}
					}
					//한바탕 비용을 증가하고나서 그 비용이 visited의 비용보다 작으면 업데이트하기
					if(tempVal < visited[cr][cc]) {
						visited[cr][cc] = tempVal;
						queue.add(new Node(cr, cc));
					}
					
				}
			}
		}
	}
	
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
