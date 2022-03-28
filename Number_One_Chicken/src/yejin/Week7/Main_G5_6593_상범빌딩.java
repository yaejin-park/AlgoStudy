package yejin.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 동서남북상하로 이동
 * 
 * 
 * 입력)
 * L(빌딩 층 수) R(행) C(열) -> 다 1이상 30이하
 * R개행 L번 주어짐 C개의 문자
 * 
 * # : 금으로 막혀있어 지나갈 수 없는 칸
 * . : 비어있는 칸
 * S : 시작지점
 * E : 탈출 출구
 * 
 * 입력 끝은 LRC가 모두 0으로 표시
 * 
 * 풀이) 
 * 최단시간 -> bfs
 * 
 * @param args
 */
public class Main_G5_6593_상범빌딩 {
	static int L,R,C;
	static char map[][][];
	static int dp[][][];	//bfs로 돌면서 최단시간 저장
	static Node start, end;
	static StringBuilder sb = new StringBuilder();
	
	//상하좌우 아래 위
	static int df[] = {0,0,0,0,1,-1};
	static int dr[] = {-1,1,0,0,0,0};
	static int dc[] = {0,0,-1,1,0,0};
	
	static class Node{
		int floor;
		int row;
		int col;
		int weight;
		
		public Node(int floor, int row, int col, int weight) {
			super();
			this.floor = floor;
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			dp = new int[L][R][C];	//최단시간 dp
			
			//0 0 0 나오면 입력 받기 끝
			if(L==0 && R==0 && C==0) {
				break;
			}
			
			//맵받기
			map = new char[L][R][C];
			for (int i = 0; i < L; i++) {		//층
				for (int j = 0; j < R; j++) {	//행
					String str = br.readLine();
					for (int c = 0; c < C; c++) {//열
						char cur = str.charAt(c);
						map[i][j][c] = cur;
						dp[i][j][c] = Integer.MAX_VALUE;	//dp값 미리 최대값으로 저장해두기

						//시작점, 탈출구 Node 미리 저장해두기
						if(cur=='S') {
							start = new Node(i, j, c, 0);
						} else if(cur =='E') {
							end = new Node(i, j, c, Integer.MAX_VALUE);
						}
					}
				}
				//층마다 빈줄 건너뛰기
				br.readLine();
			}
			
			//bfs
			bfs(start);
			//출력(탈출구의 weight 시간 출력 ! 최대값으로 초기화해놓은게 그대로면 Trapped 출력, 아니면 해당 dp에 저장된 weight 출력)
			sb.append(dp[end.floor][end.row][end.col] ==Integer.MAX_VALUE? "Trapped!\n": "Escaped in "+dp[end.floor][end.row][end.col]+" minute(s).\n");
		}
		System.out.println(sb);
	}


	private static void bfs(Node starts) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(start.floor, start.row, start.col, start.weight));
		//시작점 dp weight 0 으로 넣기
		dp[starts.floor][starts.row][starts.col] = 0;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for (int i = 0; i < 6; i++) {
				int cf = cur.floor + df[i];
				int cr = cur.row + dr[i];
				int cc = cur.col + dc[i];
				
				//경계검사 & #이 아니면  & dp보다 현재weight보다 더 크면 넣기
				if(lineTest(cf,cr,cc) && map[cf][cr][cc] != '#' && dp[cf][cr][cc] > cur.weight+1) {
					dp[cf][cr][cc] = cur.weight+1;
					queue.add(new Node(cf, cr, cc, cur.weight+1));
				}
			}
		}
		
	}
	
	private static boolean lineTest(int cf, int cr, int cc) {
		if(cf > -1 && cf < L && cr > -1 && cr < R && cc > -1 && cc < C) {
			return true;
		} else {
			return false;
		}
	}
}
