package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 아이디어 (DFS)
 * 
 * - 한 점에서 시작해 탐방하기
 * - 탐방 방향은 현재 온 방향을 제외한 나머지 세 방향으로 탐색
 * - 이를 쭉 반복하다가 탐방온 곳으로 다시 돌아온다면 종료
 * - 아니라면 모든 점 반복
 */

public class Main_16929_twoDots {
	static int N;
	static int M;
	static char gameArray[][];
	static boolean selectedArray[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str1.nextToken());
		M = Integer.parseInt(str1.nextToken());
		gameArray = new char[N][M];
		selectedArray = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str2 = br.readLine();
			for (int j = 0; j < M; j++) {
				gameArray[i][j] = str2.charAt(j);
			}
		}
		
		//DFS를 위해 각 점을 하나씩 계산해본다.
		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//이미 사이클 찾았다면 그냥 종료하기
				if(isCycle) {
					break outer;
				}
				//해당 점이 이미 다른 점에서부터 출발해서 탐색이 된 점이라면 패스하자
				if (selectedArray[i][j]) {
					continue;
				}
				else {
					selectedArray[i][j] = true;	//해당 점은 탐색을 했다.
					dfs(-1, i, j);	//탐색 시작 (-1의 의미는 출발지이기 때문에 정해져있는 방향이 없다는 것을 뜻한다)
				}
			}
		}
		if(isCycle) {
			System.out.println("Yes");
		}
		else {
			System.out.println("No");
		}
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static boolean isCycle;

	public static void dfs(int direction, int x, int y) {
		// 오던 방향이 아닌 모든 방향으로 탐색
		for (int i = 0; i < 4; i++) {
			// 사이클이 이미 완성되었다면 모두 종료
			if (isCycle) {
				return;
			}
			// 현재 방향이 정해져 있는데 방금 왔던 방향이었다면 pass (0<->1, 2<->3이므로 기존 방향+현재방향%4가 1인 경우가 오던 방향이다)
			if ((direction!=-1)&&((direction +i)%4==1)) {
				continue;
			}
			int nextX = x + dr[i];
			int nextY = y + dc[i];
			// 배열 범위가 아니라면 패스
			if ((nextX < 0) || (nextX >= N) || (nextY < 0) || (nextY >= M)) {
				continue;
			}
			// 배열 범위이지만, 해당 위치가 현재 컬러와 다르다면 패스
			else if (gameArray[nextX][nextY] != gameArray[x][y]) {
				continue;
			}
			// 색깔도 같고 해당 위치를 이미 탐색했었다면 사이클 찾은 것이니 마무리하기
			else if (selectedArray[nextX][nextY]) {
				isCycle = true;
				return;
			}
			// 어떠한 경우도 아니라면, 일단 탐색을 계속한다.
			else {
				selectedArray[nextX][nextY] = true;
				dfs(i, nextX, nextY);
			}
		}
	}
}