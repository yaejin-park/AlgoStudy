package yeongseok.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 동서남북 + 상하 만 추가된 bfs 탐색문제
 * @author 노영석
 *
 */
public class Main_6593_상범빌딩 {
	static class Point{
		int r,c,level,time;
		public Point(int level, int r, int c,  int time) {
			this.r = r;
			this.c = c;
			this.level = level;
			this.time = time;
		}
	}
	//동서남북상하
	static int dl[] = {-1,1,0,0,0,0};
	static int dr[] = {0,0,-1,1,0,0};
	static int dc[] = {0,0,0,0,-1,1};
	static int L,R,C;
	static boolean map[][][];
	static boolean visitied[][][];
	static Point start,end;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L == 0 && R == 0 && C == 0) break;
			map = new boolean[L][R][C];
			visitied = new boolean[L][R][C];
			
			//create map 
			//if map[l][r][c] == S or E or . => true, else false;
			for(int l = 0; l<L; l++) {
				for(int r =0; r<R; r++) {
					String readLine = br.readLine();
					for(int c = 0; c<C; c++) {
						switch(readLine.charAt(c)) {
						case 'S':
							start = new Point(l,r,c,0);
							map[l][r][c] = true;
							break;
						case 'E':
							end = new Point(l,r,c,0);
							map[l][r][c] = true;
							break;
						case '.':
							map[l][r][c] = true;
							break;
						case '#':
							map[l][r][c] = false;	
							break;
						}
					}
				}
				//층간 공백 입력받기
				br.readLine();
			}
			
			
			if(!BFS()) {
				sb.append("Trapped!\n");	//빌딩을 빠져나오지 못한경우
			}
		}
		System.out.println(sb.toString());
		
	}
	/**
	 * end point에 도착하면 return true; else return false;
	 * @return
	 */
	private static boolean BFS() {
		//BFS
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		visitied[start.level][start.r][start.c] = true;
		while(!q.isEmpty()) {
			Point poll = q.poll();
			int nr,nc,nl;
			for(int i =0; i<6; i++) {
				nl = poll.level+dl[i];
				nr = poll.r+dr[i];
				nc = poll.c+dc[i];
				
				if(nl >L-1 || nr > R-1 || nc > C-1 || nl <0 || nr <0 || nc <0) continue;
				
				if(!map[nl][nr][nc]) continue; 	//벽이면
				
				if(visitied[nl][nr][nc]) continue;
				
				if(nl == end.level && nr == end.r && nc == end.c) {	//end point 이면
					sb.append("Escaped in "+(poll.time+1)+" minute(s).\n");
					return true;
				}
				visitied[nl][nr][nc] = true;
				q.offer(new Point(nl,nr,nc,poll.time+1));
			}
		}		
		return false;
	}

}
