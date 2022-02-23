package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. map을 (0,0)부터 (r-1,c-1)까지 순회한다.
 * 2. 1을 만나면 그 좌표부터 bfs를 돌려서 그림의 크기를 구한다.
 * 		- 그림 cnt++;
 * 		- bfs돌면서 색칠되어 있는 부분을 0으로 덮어쓰기한다.
 * 3. 그림이 하나도 없는 경우 최대 사이즈는 0이다.!!
 * @param args
 */
public class Main_1926_그림 {
	static int R,C;
	static int MAP[][];
	static int cnt=0;
	static int max = 0;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static class Point{
		int row;
		int col;
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split;
		split = br.readLine().split(" ");
		
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		MAP = new int[R][C];
		//map 생성하기
		for(int i = 0; i<R; i++) {
			split = br.readLine().split(" ");
			for(int j = 0 ;j<C; j++) {
				MAP[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		//map을 (0,0)부터 (r-1,c-1)까지 순회한다.
		for(int i = 0; i<R; i++) {
			for(int j = 0 ;j<C; j++) {
				if(MAP[i][j] == 0) continue;
				
				int size = bfs(i,j);
				if(size > max) max = size;
				cnt++;
			}
		}
		
		 System.out.println(cnt);
		 System.out.println(max);
	}
	private static int bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(i,j));
		MAP[i][j] = 0;
		int size=1;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int rr,cc;
			for(int k = 0 ; k < 4; k++) {
				rr = p.row + dr[k];
				cc = p.col + dc[k];
				
				if(!(rr > -1 && cc > -1 && rr < R && cc < C)) continue;
				
				if(MAP[rr][cc] == 0) continue;
				
				MAP[rr][cc] = 0;
				queue.offer(new Point(rr,cc));
				size++;
			}
		}
		return size;
	}

}
