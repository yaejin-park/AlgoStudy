package yejin.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  0 : 빈칸(3개이상), 1 : 벽, 2 : 바이러스 ( 2 <=바이러스 개수<=10)
 * 	벽 3개 막아서 안전영역 크기 최댓값 구하기
 *  바이러스 상하좌우로 퍼져나감
 */
public class Main_G5_14502_연구소 {
	static int N, M;
	static int virus[][] = new int[10][2]; //바이러스의 좌표
	
	//상하좌우 델타배열
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static int [][]map;	//맵 배열
	
	static int safe, tempSafe, virusIdx, maxSafe; //0 개수, 임시 0개수, 바이러스 인덱스(바이러스 좌표 배열활용하기위한), 최대 0의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	//맵초기화
		
		safe = 0;
		virusIdx = 0;
		
		//맵 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int cur = Integer.parseInt(st.nextToken());
				map[i][j] = cur;
				if(cur ==0) {		//0(빈방)개수 저장
					safe++;
				} else if(cur==2) {	//바이러스 개수 저장
					virus[virusIdx][0] = i;
					virus[virusIdx][1] = j;
					virusIdx++;		//바이러스 좌표를 저장하기 위한 인덱스
				}
			}
		}
		
		//벽 좌표 조합(3개)
		combi(0);
		
		System.out.println(maxSafe);
	}

	//벽 3개 조합
	private static void combi(int cnt) {
		//벽 3개 고르면
		if(cnt==3) {
			int [][] tempMap = new int [N][M];
			//!!!깊은 배열복사로 해야지, 그냥 tempMap = map하면 얕은복사가되어서 map이 바뀌어버림..!!
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			
			tempSafe = safe-3;	//임의 빈칸저장할 곳,빈칸 개수 설정(벽3개설치빼기)
			
			//dfs로 바이러스 퍼지기
			for (int i = 0; i < virusIdx; i++) {
				//바이러스 시작점
				dfs(virus[i][0], virus[i][1], tempMap);
				//빈칸이 0개 남으면 그만두기(백트래킹)
				if(tempSafe==0) {
					break;
				}
			}
			//가장 안전했을 때값과 비교해서 넣기
			maxSafe = Math.max(maxSafe, tempSafe);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {	//빈칸이면
					map[i][j] =1;		//벽 세우고
					combi(cnt+1);		//조합 호출
					map[i][j] =0;		//다시 벽 리셋
				}
			}
		}
	}

	//바이러스 퍼지게
	private static void dfs(int r, int c, int tempMap[][]) {
		//백트래킹(안전한 방이 0개되면 리턴)
		if(tempSafe ==0) {
			return;
		}	
		//델타배열로 상하좌우 검색
		for (int i = 0; i < 4; i++) {
			int cr = r+dr[i];
			int cc = c+dc[i];
			//경계검사 & 임시 맵에서 0이면 들어가서 감염시키기
			if(cr>=0 && cr<N && cc>=0 && cc<M && tempMap[cr][cc]==0) {
				tempMap[cr][cc]=2;
				tempSafe--;				//임시 0개수 감소
				dfs(cr,cc, tempMap);	//시작점 좌표와 맵 보내기
			}
		}
	}

}
