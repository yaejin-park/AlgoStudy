package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 0. 흰칸 == ROAD, 검정칸 == WALL 이라고 가정한다.
 * 1. 시작지점(0,0)부터 bfs
 * 2. 특정 위치에 도착할때마다 memo배열에 해당 위치까지 도달하는데 부순 벽의 수를 기록한다.
 * 3. 특정 위치를 탐색할 수 있는(queue에 넣을 수 있는) 조건은
 * 	3-1. 처음 방문하는 경우(memo[r][c] == 0)
 *  3-2. 이전에 해당 위치에 방문했을때 부순 벽의 수(memo[r][c]) 보다 적은 벽을 부수고 도착했을 경우
 * @author 노영석
 *
 */
public class Main_2665_미로만들기 {
	static class Point{
		int row;
		int col;
		int brokenWall;	//현재 좌표까지 오는데 깬 문의 수
		public Point(int row, int col, int brokenWall) {
			super();
			this.row = row;
			this.col = col;
			this.brokenWall = brokenWall;
		}
	}
	public static final char WALL = '0';
	public static final char ROAD = '1';
	static int N;
	static char map[][];
	static int memo[][];	// (r,c)까지 도착하는데 부순 벽의 수
	static int min = Integer.MAX_VALUE;
	static int dr[]= {-1,1,0,0};
	static int dc[]= {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		memo = new int[N][N];
		
		for(int i =0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		Queue<Point> q= new LinkedList<>();
		q.offer(new Point(0,0,1));
		memo[0][0] = 1;		//한번도 방문하지 않은 상태를 0이라고 하기 위해서 시작점을 1로 설정한다.
							//만약 시작점을 0으로 설정하면 중복 방문될 수 있다.
		while(!q.isEmpty()) {
			Point poll = q.poll();
			if(poll.row == N-1 && poll.col == N-1) {	//도착지점
				min = Integer.min(min, poll.brokenWall);	//최소값 판단
				continue;
			}
			int rr,cc;
			for(int i =0; i<4; i++) {
				rr = poll.row + dr[i];
				cc = poll.col + dc[i];
				
				//범위 밖
				if(rr < 0 || cc < 0 || cc >N-1 || rr >N-1) continue;
				
				int brokenWall = poll.brokenWall;
				if(map[rr][cc] == WALL) {	//현재 위치가 벽이면 이 벽을 부숴야 하므로 brokenWall 증가
					brokenWall++;
				}
				
				if(memo[rr][cc] == 0) {	//한번도 가보지 않은 곳
					if(brokenWall< min) {//도착 지점까지 가는데 깬 벽의 최소값보다 작을때만 queue에 추가한다.
						memo[rr][cc] = brokenWall;	//현재 위치까지 오는데 부순 벽 수 메모한다.
						q.offer(new Point(rr,cc,brokenWall));
					}
						
				}
				else {	//이미 방문했던 곳
					if(memo[rr][cc]>brokenWall) {	//이전에 방문했을때 보다 더 적은 벽을 깨고 현재 위치에 도착했을 때
						if(brokenWall< min) {//도착 지점까지 가는데 깬 벽의 최소값보다 작을때만 queue에 추가한다.
							q.offer(new Point(rr,cc,brokenWall));
							memo[rr][cc] = brokenWall;//현재 위치까지 오는데 부순 벽 수 메모한다.
						}
							
					}
				}
			}
		}
		
		
		
		System.out.println(min-1);	//1부터 시작했으므로 하나 빼서 출력한다.
		
	}

}
