package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_10026_적록색약 {
	static class Point{
		int row,  col;
		char color;
		public Point(int row, int col, char color) {
			super();
			this.row = row;
			this.col = col;
			this.color = color;
		}
	}
	static int N;
	static char map[][];
	static boolean visitied[][];
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visitied = new boolean[N][N];
		
		for(int i =0; i<N; i++) {
			String readLine = br.readLine();
			map[i] = readLine.toCharArray();
		}
		
		int cnt=0;
		//적록색약이 아닌 사람
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(visitied[i][j]) continue;
				cnt++;
				Queue<Point> q= new LinkedList<>();
				q.offer(new Point(i,j,map[i][j]));
				visitied[i][j] = true;
				
				while(!q.isEmpty()) {
					Point poll = q.poll();
					
					int rr,cc;
					for(int k = 0; k<4; k++) {
						rr = poll.row + dr[k];
						cc = poll.col + dc[k];
						
						if(rr < 0 || cc < 0 || rr > N-1 || cc > N-1) continue;
						
						if(visitied[rr][cc]) continue;
						
						if(map[rr][cc] != poll.color) continue;
						
						visitied[rr][cc] = true;
						q.offer(new Point(rr,cc,map[rr][cc]));
					}
				}
				
			}
		}
		System.out.print(cnt + " ");
		
		
		//적록색약인 사람
		cnt=0;
		visitied = new boolean[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(visitied[i][j]) continue;
				cnt++;
				Queue<Point> q= new LinkedList<>();
				q.offer(new Point(i,j,map[i][j]));
				visitied[i][j] = true;
				
				while(!q.isEmpty()) {
					Point poll = q.poll();
					
					int rr,cc;
					for(int k = 0; k<4; k++) {
						rr = poll.row + dr[k];
						cc = poll.col + dc[k];
						
						if(rr < 0 || cc < 0 || rr > N-1 || cc > N-1) continue;
						
						if(visitied[rr][cc]) continue;
						
						if(poll.color == 'R' || poll.color == 'G') {
							if(map[rr][cc] == 'B') continue;
						}else {
							if(map[rr][cc] != 'B') continue;
						}
						
						visitied[rr][cc] = true;
						q.offer(new Point(rr,cc,map[rr][cc]));
					}
				}
				
			}
		}
		System.out.println(cnt);
	}

}
