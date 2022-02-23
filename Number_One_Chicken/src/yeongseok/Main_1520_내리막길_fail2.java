package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. 10억개의 경로는 2초내에 탐색이 불가하다.
 * 2. 백트레킹 + 메모이제이션을 이용해서 최대한 탐색을 줄여야함
 * 3. dfs를 사용해서 목적지에 도착할때까지 탐색한다.
 * 4. 탐색이 끝나고 재귀 함수가 하나씩 return 될때 현재 위치에서 목적지까지 가는 경로의 수를 return한다.
 * 5. 재귀함수에서 반환한 값을 MAP에 음수로 표기한다.
 * 6. 다른 재귀함수가 지도를 탐색할 때 음수값을 만나면 탐색을 더이상 하지 않고 사방에서 얻은 음수 값을 더해서 자기 위치에 표기한다.
 * 7. (0,0)위치의 음수값을 절댓값을 취해서 반환한다.
 * @author 노영석
 */
public class Main_1520_내리막길_fail2 {
	static int R,C;
	static int [][] MAP;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split;
		
		split = br.readLine().split(" ");
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		
		MAP = new int[R][C];
		
		//지도 만들기
		for(int i = 0; i<R; i++) {
			split = br.readLine().split(" ");
			for(int j = 0 ;j<C; j++) {
				MAP[i][j] = Integer.parseInt(split[j]);
			}
		}
		System.out.println(Math.abs(dfs(0,0)));
	}
	private static int dfs(int r, int c) {
		//목적지까지 도달하면
		if(r == R-1 && c == C-1) return -1;
		
		
		int cnt = 0;
		int rr, cc;
		for(int i = 0 ; i< 4; i++) {
			rr= r+ dr[i];
			cc= c+ dc[i];
			//범위밖이면
			if(rr < 0 || cc < 0 || rr > R-1 || cc > C-1) continue;
			
			//이미 왔던길이면
			if(MAP[rr][cc] <0) {
				
				 cnt += MAP[rr][cc]; continue;
			}
			//이전 길보다 높이가 높으면
			if(MAP[rr][cc] >= MAP[r][c]) continue;
			
			//갈수있는 길이면
			cnt += dfs(rr,cc);
		}
		
		//cnt == 0일때 문제가 발생한다. ******************
		/*if(cnt == 0) {
			
		}*/
		
		return MAP[r][c] = cnt;
	}
}
