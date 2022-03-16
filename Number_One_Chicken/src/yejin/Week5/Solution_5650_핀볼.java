package yejin.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5650_핀볼 {
	static int N;
	static int map[][];
	static Worm worm[][]; 	//웜홀 6-10의 좌표
	
	//델타배열 (상좌하우)
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,-1,0,1};
	
	static int start[] = new int[2];
	static int maxScore; //답(최대 점수)
	static int tempScore;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			//변수 초기화
			maxScore = Integer.MIN_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			worm = new Worm[11][2];
			
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					int cur = Integer.parseInt(st.nextToken());
					map[j][j2] = cur;
					
					//웜홀 좌표 저장
					if(cur >=6 && cur <=10) {
						if(worm[cur][0] == null) {
							worm[cur][0] = new Worm(j, j2);
						} else {
							worm[cur][1] = new Worm(j, j2);
						}
					}
				}
			}
			
			//시작점 찾기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					//0이거나
					if(map[r][c] ==0) {
						//시작좌표 저장
						start[0] = r;
						start[1] = c;
						//방향지정
						for (int d = 0; d < 4; d++) {
							tempScore = 0;
							dfs(r,c,d);
							maxScore = Math.max(maxScore, tempScore);
						}
					}
				}
			}
			sb.append("#"+(i+1)+" "+maxScore+"\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int r, int c, int d) {
		//해당 방향으로 전진
		int cr = r + dr[d];
		int cc = c + dc[d];
		System.out.println(cr+","+cc);
		
		//벽
		if(cr == -1 || cr == N || cc == -1 || cc == N ) {
			tempScore++; //점수 +1
			//방향바꾸기
			d = changeDirect(d, -2);
			//첫시작점으로 돌아옴?
			if(cr+dr[d] == start[0] || cc+dc[d] == start[1]) {
				tempScore--;
				return;
			}
		} else {
			//블록 (방향 바꾸고 이동)
			int meet = map[cr][cc];
			System.out.println("meet"+meet);
			if (meet == 0) {
				
			}else if(meet >=1 && meet==5) {	//블럭
				tempScore++;
				d = changeDirect(d, meet);
				cr += dr[d];
				cc += dr[d];
			}else if(meet >=6 && meet <=10) {//웜홀
				if(cr == worm[meet][0].row && cc == worm[meet][0].col) {
					//위치이동
					cr = worm[meet][1].row;
					cc = worm[meet][1].col;
				} else {
					cr = worm[meet][0].row;
					cc = worm[meet][0].col;
				}
			}else if(meet == -1 || (start[0] == cr && start[1] == cc)) { //블랙홀, 시작위치 -> 종료
				return;
			}
		}
		dfs(cr,cc,d);
	}
	
	//방향 전환
	private static int changeDirect(int d, int meet) {
		switch (d) {
		case 0://상
			if(meet==2) {
				return 3;
			} else if(meet == 3) {
				return 1;
			} else {
				return d+2>3?d-2:d+2;
			}
		case 2://하
			if(meet==1) {
				return 3;
			} else if(meet == 4) {
				return 1;
			} else {
				return d+2>3?d-2:d+2;
			}
		case 1://좌
			if(meet==1) {
				return 0;
			} else if(meet == 2) {
				return 2;
			} else {
				return d+2>3?d-2:d+2;
			}
		case 3://우
			if(meet==4) {
				return 0;
			} else if(meet == 3) {
				return 2;
			} else {
				return d+2>3?d-2:d+2;
			}
		}
		return -1;
	}

	static class Worm{
		int row;
		int col;
		
		public Worm(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}

}
