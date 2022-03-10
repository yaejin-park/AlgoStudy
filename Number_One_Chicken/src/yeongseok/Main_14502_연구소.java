package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_연구소 {
	static class Point{
		int row,col;
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static List<Point> list = new ArrayList<>();	// 벽을 세울수 있는 빈칸의 좌표들
	static int N,M;
	static final int SPACE=0,WALL=1,VIRUS=2;
	static int MAP[][];
	static Point[] res = new Point[3];	//벽을 세울 좌표 3개
	static int max = 0;	//안전 영역 최대 개수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		MAP = new int[N][M];
		
		//지도 입력받기
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				if(MAP[i][j] == SPACE) {
					list.add(new Point(i,j));
				}
			}
		}
		
		combination(0,0);
		System.out.println(max);
	}
	private static void combination(int resIndex, int targetIndex) {
		if(resIndex == 3) {	// 벽 설치할 3개의 위치를 선택했다면
			//벽 설치
			for(int i = 0; i<3; i++) {
				MAP[res[i].row][res[i].col] = WALL;
			}
			//바이러스 퍼트리기
			int cntSafeSpace = cntSafeSpace();
			//최대값 갱신
			max = Integer.max(cntSafeSpace, max);
			
			//벽 해제
			for(int i = 0; i<3; i++) {
				MAP[res[i].row][res[i].col] = SPACE;
			}
			return;
		}
		for(int i = targetIndex; i < list.size(); i++) {
			res[resIndex] = list.get(i);
			combination(resIndex+1, i+1);
		}
	}
	
	/**
	 * 바이러스가 퍼지고 난뒤 안전 영역의 개수를 센다
	 * @return
	 */
	private static int cntSafeSpace() {
		Queue<Point> q = new LinkedList<>();
		int dr[] = {-1,1,0,0};
		int dc[] = {0,0,-1,1};
		int cnt=0;
		//원래 빈 영역이였던 좌표들
		List<Point> wasSpace = new ArrayList<>();
		//바이러스 찾기
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(MAP[i][j] == VIRUS) {
					q.offer(new Point(i,j));
				}
				
			}
		}
		
		//bfs 돌면서 바이러스 감염시키기
		while(!q.isEmpty()) {
			Point poll = q.poll();
			
			int rr,cc;
			for(int i = 0; i<4; i++) {
				rr = poll.row + dr[i];
				cc = poll.col + dc[i];
				
				//범위초과
				if(rr<0 || cc <0 || rr>N-1 || cc>M-1 ) continue;
			
				if(MAP[rr][cc] != SPACE) continue;
					
				wasSpace.add(new Point(rr,cc));
				MAP[rr][cc] = VIRUS;
				q.offer(new Point(rr,cc));
			}
		}
		
		//빈 영역 찾기
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(MAP[i][j] == SPACE) {
					cnt++;
				}
				
			}
		}
		
		//원래 빈 영역이였던 좌표를 빈 영역으로 다시 바꾼다
		for(Point p : wasSpace) {
			MAP[p.row][p.col] = SPACE;
		}
		
		return cnt;
	}

}
