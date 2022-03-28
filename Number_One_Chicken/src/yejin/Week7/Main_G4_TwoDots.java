package yejin.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 목표) 사이클 존재 여부 찾기
 * 사이클 조건
 * 1.점 4개 이상
 * 2.같은 색
 * 3.마지막과 첫번째 점 인접
 * 점 색 :  알파벳 대문자 한글자
 * 사이클 존재 -> Yes(중간에 system.exit(0)/ 아니면 No 출력
 * 
 * 입력)
 * N, M 게임판 크기 (2이상 50이하)
 * N개줄 게임판 상태
 * 
 * 아이디어)
 * 1. 점에 인접된 점이 1개뿐이면 다시 돌아오지 못하는 점(X)
 * 2. 알파벳 배열 두고, 갯수 4개 미만이면 시작하지 않기.
 * 
 * @author yaejin
 *
 */
public class Main_G4_TwoDots {
	static int N,M;
	static char map[][];
	static String answer = "No";
	static int alpha[] = new int[26];
	static int line[][];	//잇는 선을 저장
	
	//상하좌우
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//게임판 입력 받기
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char cur = str.charAt(j);
				map[i][j] = cur;
				alpha[cur-'A']++;	//알파벳 개수 저장
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//이어진 선을 표시하는 맵
				line = new int[N][M];
				
				//시작점 알파벳
				char cur = map[i][j];
				//시작점 알파벳이 4개이상 존재하면 탐색 시작
				if(alpha[cur-'A']>=4) {
					line[i][j] = 2;	//시작점에는 2넣기
					dfs(i,j,1, map[i][j]);
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void dfs(int row, int col, int cnt, char color) {
		//4방검사
		for (int i = 0; i < 4; i++) {
			int cr = row + dr[i];
			int cc = col + dc[i];
			
			//경계검사 & 같은 색 &선에 포함된 점이 아니면(시작점 제외)
			if(cr>-1 && cr <N && cc>-1 && cc < M && map[cr][cc] == color && line[cr][cc] !=1 ) {
				//4개 이상의 점을 연결했고 시작점에 도착하면(사이클 만족)
				if(cnt >= 4 && line[cr][cc] == 2) { 
					answer = "Yes";
					System.out.println(answer);
					System.exit(0);	//이렇게 종료? 괜찮나? 아니면, 더 좋은 종료방법..
				} else if(line[cr][cc]!=2) {	//시작점 아니면
					line[cr][cc] = 1;			//line에 1넣기
				} else if(line[cr][cc]==2) {	//시작점이면
					continue;					//건너뛰기
				}
				
				dfs(cr,cc,cnt+1, color);
			}
		}
	}

}
