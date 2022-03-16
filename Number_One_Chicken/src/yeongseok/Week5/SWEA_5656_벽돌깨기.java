package yeongseok.Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 단순 구현...
 * @author 노영석
 *
 */
public class SWEA_5656_벽돌깨기 {
	static class Point{
		int row; int col; int val;//map[row][col]에 있던 값

		public Point(int row, int col, int val) {
			super();
			this.row = row;
			this.col = col;
			this.val = val;
		}
	}
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	static int T,N,C,R;
	static int [][] map;
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t < T+1; t++) {
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			map = new int[R][C];
			
			//지도 입력받기
			for(int i = 0 ;i<R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			recursive(N , map);
			
			//결과 출력
			if(min == Integer.MAX_VALUE)
				System.out.println("#" + t + " " + 0);
			else
				System.out.println("#" + t + " " + min);
		}
	}
	
	//remainBall이 0이 되기 전까지 공을 하나 떨어뜨린다.
	private static void recursive(int remainBall, int[][] map) {
		//더이상 떨어뜨릴 공이 없으면
		if(remainBall == 0) {
			int cnt=0;
			//count remain bricks
			for(int r = 0; r<R; r++) {
				for(int c = 0; c<C; c++) {
					if(map[r][c] != 0) cnt++;
				}
			}
			//compare with max value
			min = Integer.min(min, cnt);
			return;
			
		}else {
			//break bricks
			for(int i = 0; i<C; i++) {	//0~C-1 위치에 모두 공을 한번씩 떨어뜨려본다.
				
				//deep copy
				int [][] copy = new int[R][];
				for(int j = 0; j<R; j++) {
					copy[j] = Arrays.copyOf(map[j], C);
				}
				
				
				int row = 0;
				//깰수 있는 brick을 위에서부터 찾는다.
				while(row < R && copy[row][i] == 0) {
					row++;
				}
				if(row == R) continue;	//깰수있는 벽이 없는 경우
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row,i,copy[row][i]));
				copy[row][i] = 0;
				
				//하나의 벽이 부숴짐으로써 파생적으로 부숴지는 벽 모두 부수기
				while(!q.isEmpty()) {
					Point poll = q.poll();
					
					if(poll.val == 1) continue;
					
					int rr, cc;
					for(int dir = 0; dir<4; dir++) {	//상하좌우
						rr = poll.row;
						cc = poll.col;
						for(int k =1; k< poll.val; k++) {	//폭파 범위만큼 순회

							rr += dr[dir];
							cc += dc[dir];
							
							if(rr <0 || cc < 0 || rr > R-1 || cc > C-1) break;
							
							if(copy[rr][cc] == 0) continue;
							
							q.offer(new Point(rr,cc,copy[rr][cc]));
							copy[rr][cc] = 0;
							
						}
					}
					
				}

				// refactor map
				//사이사이에 껴있는 0들을 제거해준다.
				
				for(int c = 0; c< C; c++) {	//모든 colum들에 대해서 순회
					ArrayList<Integer> list = new ArrayList<>();
					for(int r = 0; r<R; r++) {	//특정 column에 있는 값들을 list로 이전시킴
						list.add(copy[r][c]);
					}
					int zeroCnt=0;	
					for(int index = list.size()-1; index >-1; index--) {	//사이사이에 있는 0을 제거한다.
						if(list.get(index) == 0) {
							zeroCnt++;
							list.remove(index);
						}
					}
					for(int k = 0 ; k < zeroCnt; k++) {	//제거한 0 개수만큼 리스트 앞에 0을 추가한다.
						list.add(0,0);
					}

					for(int r = 0; r<R; r++) {	// 기존 지도에 0을 제거한 값들로 갱신한다.
						copy[r][c] = list.get(r);
					}
				}
				
				recursive(remainBall-1,copy);
			}
			
		}
	}

}
