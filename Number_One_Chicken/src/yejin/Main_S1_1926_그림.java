package yejin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  6 5
	1 1 0 1 1
	0 1 1 0 0
	0 0 0 0 0
	1 0 1 1 1
	0 0 1 1 1
	0 0 1 1 1
	
	문제 : (가로, 세로로) 1로 연결된것이 한 그림, 넓이는 1의 개수

	출력 :  그림의 개수, 가장 넓은 그림의 넓이(그림이 하나도 없으면 0)
 */

public class Main_S1_1926_그림 {
	static int n,m; //세로, 가로
	static boolean map[][];
	static boolean isVisited[][];
	//방향 배열 (상, 하, 좌, 우)
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	static int cnt = 0;		//총 도화지 개수
	static int width = 0;		//총 가장 넓은 도화지 넓이
	static int eleWidth;	//각각의 넓이

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new boolean[n][m];
		isVisited = new boolean[n][m];
		
		//맵 받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = (st.nextToken().equals("1"));
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				eleWidth = 0;	//각각의 넓이
				//방문하지 않았으면 & map에서 참이면 (첫시작)
				if(!isVisited[i][j] && map[i][j]) {
					eleWidth++;
					cnt++;
					
					bfs(i, j);
					
					width = Math.max(width,eleWidth);
				}
			}
		}
		//출력
		System.out.println(cnt);
		System.out.println(width);
	}

	private static void bfs(int r, int c) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(new Node(r, c));
		isVisited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			//오른쪽, 아래 방향으로 움직임 시작
			for (int d = 0; d < 4; d++) {
				int nr = current.r+ dr[d];
				int nc = current.c+ dc[d];
				
				//경계검사 & 방문검사 & 1이면
				if(nr<n && nr>=0&& nc<m && nc>=0&& !isVisited[nr][nc] && map[nr][nc]) {
					eleWidth++;
					isVisited[nr][nc] = true;
					queue.offer(new Node(nr, nc));
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
