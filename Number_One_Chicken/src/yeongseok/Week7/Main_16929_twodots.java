package yeongseok.Week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * bfs를 돌리는데, 색깔이 같고, 이미 예전에 방문했던 곳에 도달하면 cycle이 생긴다.
 * 단, 상하좌우중 현재 위치에 도달시킨 방향을 갈수 없다.
 * 그래서 Point 클래스에 from 속성에 자신이 어느 뱡향에서 왔는지를 저장한다.
 * @author 노영석
 */
public class Main_16929_twodots {
	static class Point{
		int from;
		int r,c;
		public Point(int from, int r, int c) {
			this.from = from;
			this.r = r;
			this.c = c;
		}
	}
	static final int TOP=0, DOWN=1, LEFT=2, RIGHT=3, DEFAULT=4;
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	static int R,C;
	static char map[][];
	static boolean visitied[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visitied = new boolean[R][C];
		
		//지도 만들기
		for(int i = 0; i<R; i++) {
			String readLine = br.readLine();
			map[i] = readLine.toCharArray();
		}
		
		//BFS 탐색
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(!visitied[i][j]) {
					if(bfs(i,j)) return;
				}
			}
		}
		System.out.println("No");
	}
	
	private static boolean bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(DEFAULT,i,j));
		visitied[i][j] = true;
		char color = map[i][j];
		
		
		while(!q.isEmpty()) {
			Point poll = q.poll();
			int rr,cc;
			for(int n =0; n<4; n++) {
				if(n == poll.from) continue; //자신을 현재 위치에 도달시킨 방향이면 고려하지 않는다.
				
				rr = poll.r + dr[n];
				cc = poll.c + dc[n];
				
				if(rr>R-1 || cc > C-1 || rr<0 || cc <0) continue;
				
				if(map[rr][cc] != color) continue;
				
				if(visitied[rr][cc]) {
					System.out.println("Yes");
					return true;
				}
				
				visitied[rr][cc] = true;
				int from; //어느 방향으로부터 왔는지 결정한다.
				if(n == 0) {
					from = DOWN;
				}else if(n==1) {
					from = TOP;
				}else if(n==2) {
					from = RIGHT;
				}else {
					from = LEFT;
				}
				q.offer(new Point(from,rr,cc));
			}
		}
		return false;
	}

}
