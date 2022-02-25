package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 너무 어렵게 생각해서 못풀다가 구글링해서 품
 * 둘러싸인 0으로 풀까?도 생각했지만, 설마 그거겠어 라는 생각에 계속 다른방법 찾다가 더 어려워진 케이스
 * @author yaejin
 *
 */
public class Main_B1_2563_색종이 {
	//상하좌우
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		
		int map[][] = new int[102][102];
		
		//색종이 개수만큼 반복
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			//색종이면 1로 채우기(테두리 체크를 위한 0 자리 남겨두고 +1)
			for (int j = r+1; j < r+11; j++) {
				for (int j2 = c+1; j2 < c+11; j2++) {
					map[j][j2] = 1;
				}
			}
		}
		
		//탐색하며 1이나오면 4방 체크
		for (int j = 0; j < 102; j++) {
			for (int j2 = 0; j2 < 102; j2++) {
				//1이나오면
				if(map[j][j2] == 1) {
					int oneCnt = 0;
					//4방 체크
					for (int k = 0; k < 4; k++) {
						int nr = j+dr[k]; 
						int nc = j2+dc[k]; 
						
						//테두리를 0으로 둘러싸고 있기 때문에 경계체크 안해줘도됨.
						if(map[nr][nc] ==1) {
							oneCnt++;
						}
					}
					
					if(oneCnt ==2) {
						answer+=2;
					} else if(oneCnt == 3) {
						answer+=1;
					}
				}
			}
		}
		
		System.out.println(answer);
	}

}
