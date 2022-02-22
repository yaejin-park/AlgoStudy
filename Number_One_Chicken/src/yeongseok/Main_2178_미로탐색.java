package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2178_미로탐색 {
	static int R;
	static int C;
	static char[][] MAP;
	//상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static class Point{
		int row;
		int col;
		int cnt;
		public Point(int row, int col, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		MAP = new char[R][C];
		
		//지도 만들기
		for(int i = 0 ; i< R; i++) {
			MAP[i] = br.readLine().toCharArray();
		}
		
		Queue<Point> queue = new LinkedList<>();		
		queue.offer(new Point(0,0,1));
		MAP[0][0] = '0';
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			//목적지 도달하면
			if(p.row == R-1 && p.col == C-1) {
				System.out.println(p.cnt);
				return;
			}
			
			int rr, cc;
			for(int i = 0 ; i <4; i++) {
				rr = p.row + dr[i];
				cc = p.col + dc[i];
				
				//범위밖이면
				if(rr < 0 || cc < 0 || rr > R-1 || cc > C-1) continue;
				
				//이미 방문한적이 있다면 or 벽이면
				if(MAP[rr][cc] == '0') continue;
				
				//방문표시
				MAP[rr][cc] = '0';
				
				queue.offer(new Point(rr,cc,p.cnt+1));
			}
			
			
		}
		
	}

}
