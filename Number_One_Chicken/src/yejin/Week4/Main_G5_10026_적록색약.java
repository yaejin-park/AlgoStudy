package yejin.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_G5_10026_적록색약 {
	//델타 배열
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	static int N;
	static char map[][];
	static boolean visited[][];
	
	static int no = 0;	//적록색약 아닌 사람
	static int yes = 0;	//적록색약인 사람

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char [N][N];
		visited = new boolean [N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		br.close();
		
		//일반
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//방문안했으면
				if(!visited[i][j]) {
					no++;		//1증가
					no(i,j);	//해당 좌표를 시작점으로해서 덩어리 찾기
				}
			}
		}
		
		//방문 배열 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		
		//적록색약
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//방문안했으면
				if(!visited[i][j]) {
					yes++;		//1증가
					yes(i,j);	//해당 좌표를 시작점으로해서 덩어리 찾기
				}
			}
		}
		System.out.println(no+" "+yes);
	}
	
	//일반 사람이 보는 갯수 구하는 함수
	private static void no(int r, int c) {
		char color = map[r][c];
		
		for (int i = 0; i < 4; i++) {
			int cr = r+dr[i];
			int cc = c+dc[i];
			
			//경계검사 + 방문 안했으면 + 두 색이 같으면
			if(cr>=0 && cr<N && cc>=0 && cc<N && !visited[cr][cc] && color==map[cr][cc]) {
				visited[cr][cc] = true;	//방문체크
				no(cr,cc);
			}
		}
	}
	
	//적록색약인 사람이 보는 갯수 구하는 함수
	private static void yes(int r, int c) {
		char color = map[r][c];
		
		for (int i = 0; i < 4; i++) {
			int cr = r+dr[i];
			int cc = c+dc[i];
			
			//경계검사 + 방문 안했으면
			if(cr>=0 && cr<N && cc>=0 && cc<N && !visited[cr][cc]) {
				//색이 같거나 두 색이 R과 G이면
				if(color==map[cr][cc] || (color =='R' && map[cr][cc]=='G') || (color =='G' && map[cr][cc]=='R')) {
					visited[cr][cc] = true;	//방문체크
					yes(cr,cc);
				}
			}
		}
	}

}
