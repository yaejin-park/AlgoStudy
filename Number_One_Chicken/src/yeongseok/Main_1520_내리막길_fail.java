package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1520_내리막길_fail {
	static class Road{
		int row;
		int col;
		public Road(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int R,C;
	static int [][] MAP;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int cnt = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split;
		
		split = br.readLine().split(" ");
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		
		MAP = new int[R][C];
		
		for(int i = 0; i<R; i++) {
			split = br.readLine().split(" ");
			for(int j = 0 ;j<C; j++) {
				MAP[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		Queue<Road> queue = new LinkedList<>();
		queue.offer(new Road(0,0));
		
		while(!queue.isEmpty()) {
			Road road = queue.poll();
			int rr, cc;
			for(int i = 0; i<4; i++) {
				rr = road.row + dr[i];
				cc = road.col + dc[i];
				
				if(rr < 0 || cc <0 || rr >R-1 || cc > C-1) continue;
				
				if(!(MAP[road.row][road.col] > MAP[rr][cc])) continue;
				
				if(rr == R-1 && cc == C-1) {
					cnt++; continue;
				}
				
				queue.offer(new Road(rr,cc));
			}
		}
		System.out.println(cnt);
	}
}
