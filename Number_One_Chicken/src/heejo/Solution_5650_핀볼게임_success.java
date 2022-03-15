package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 5650 핀볼게임
 * 
 * 필요한 변수
 *  - 방향을 나타내는 direction변수 : 1, 2, 3, 4 순서대로 상 하 좌 우를 의미
 *  - 시작점을 나타내는 startX startY 변수
 * 
 * 풀이 방법
 *  - 출발지와 방향을 정하고, 게임 실행
 *  - 모든 게임들 중 가장 많이 벽을 부딪힌 횟수를 출력
 *   
 * @author joy96
 *
 */
public class Solution_5650_핀볼게임_success {
	static int map[][];
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			wormHole = new int[5][4];
			for (int i = 0; i < 5; i++) {
				Arrays.fill(wormHole[i], -1);
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer str = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str.nextToken());
					if (map[i][j] >= 6) {
						if (wormHole[map[i][j] - 6][0] == -1) {
							wormHole[map[i][j] - 6][0] = i;
							wormHole[map[i][j] - 6][1] = j;
						} else {
							wormHole[map[i][j] - 6][2] = i;
							wormHole[map[i][j] - 6][3] = j;
						}
					}
				}
			}
			// 게임은 N*N*4판
			countMax = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//선택한 자리가 블랙홀이거나, 웜홀이거나, 블록이라면 패스
					if (map[i][j] != 0) {
						continue;
					}
					for (int k = 1; k <= 4; k++) {
						//1판
						count = 0;	//벽에 부딪힌 횟수
						ori_startX = i;	//최초의 출발점
						ori_startY = j;	//최초의 출발점
						passed = false;	//웜홀을 만나면 방금 이 웜홀으로 통과했는지 체크
						isStart = false;//현재 위치가 출발지인데, 이게 게임 시작인지 아님 게임 종료인지 구분
						game(i, j, k);
					}
				}
			}
			System.out.println("#" + test_case + " " + countMax);
		}
	}

	static int wormHole[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int count;
	static int countMax;
	static int ori_startX;
	static int ori_startY;
	static boolean passed;
	static boolean isStart;

	public static void game(int startX, int startY, int direction) {
		// 블랙홀을 만났거나, 출발지에 다시 도착한다면 끝
		if (((ori_startX == startX) && (ori_startY == startY)) || (direction == -1)) {
			// 지금이 게임 시작의 첫 부분이 아닌지?
			if (isStart) {
				countMax = Math.max(countMax, count);
				return;
			}
		}
		isStart = true;	//게임은 시작됐다
		
		int nextX, nextY;	//핀볼이 다음으로 이동할 위치
		// 혹시 지금 있는 자리가 웜홀이라면
		if ((map[startX][startY] >= 6)) {
			//방금 통과해서 도착한 웜홀이 아니라면
			if (!passed) {
				// nextX와 nextY는 웜홀 반대편으로 변경
				if ((wormHole[map[startX][startY] - 6][0] == startX)&&(wormHole[map[startX][startY] - 6][1] == startY)) {
					nextX = wormHole[map[startX][startY] - 6][2];
					nextY = wormHole[map[startX][startY] - 6][3];
				} else {
					nextX = wormHole[map[startX][startY] - 6][0];
					nextY = wormHole[map[startX][startY] - 6][1];
				}
				passed = true;	//방금 웜홀을 통과했다.
				game(nextX, nextY, direction);
				return;
			} else {
				// 방금 통과한 웜홀이라면 기존 방식으로 방향으로 이동
				passed = false;
			}
		}
		//방향에 따라 nextX와 nextY 설정
		nextX = startX + dr[direction - 1];
		nextY = startY + dc[direction - 1];
		
		// 혹시 방금이 벽을 만난 것이었다면
		if ((nextX < 0) || (nextX >= N) || (nextY < 0) || (nextY >= N)) {
			//방향을 반대로 뒤집고
			if (direction / 3 == 0) {
				direction = 3 - direction;
			} else {
				direction = 7 - direction;
			}
			//nextX와 nextY는 startX,Y로 재변경
			nextX = startX;
			nextY = startY;
			count++;
		}
		game(nextX, nextY, decideDirection(direction, map[nextX][nextY]));

	}

	public static int decideDirection(int originalDirection, int block) {
		//웜홀을 만났다면, 방향은 바뀌지 않는다.
		if (block >= 6) {
			return originalDirection;
		}
		//아무 블록이 없다면
		if (block == 0) {
			return originalDirection;
		}
		//블랙홀을 만난다면
		else if (block == -1) {
			// 종료
			return -1;
		}
		
		
		count++;	//아래의 경우는 벽, 블록에 부딪혀 방향이 바뀌는 경우들이기 때문에 count 1 증가
		
		//방향을 바꾸자
		switch (originalDirection) {
		case 1: // 상
			if ((block == 1) || (block == 4) || (block == 5)) {
				// 평평한 면을 만나면, 방향을 반대로 뒤집어라 (하)
				return 2;
			} else if (block == 2) {
				// 상->우로 변경
				return 4;
			} else if (block == 3) {
				// 상->좌로 변경
				return 3;
			}
			break;
		case 2: // 하
			if ((block == 2) || (block == 3) || (block == 5)) {
				// 평평한 면을 만나면, 방향을 반대로 뒤집어라 (상)
				return 1;
			} else if (block == 1) {
				// 하->우로 변경
				return 4;
			} else if (block == 4) {
				// 하->좌로 변경
				return 3;
			}
			break;
		case 3: // 좌
			if ((block == 3) || (block == 4) || (block == 5)) {
				// 평평한 면을 만나면, 방향을 반대로 뒤집어라 (우)
				return 4;
			} else if (block == 1) {
				// 좌->상로 변경
				return 1;
			} else if (block == 2) {
				// 좌->하로 변경
				return 2;
			}
			break;
		case 4: // 우
			if ((block == 1) || (block == 2) || (block == 5)) {
				// 평평한 면을 만나면, 방향을 반대로 뒤집어라 (좌)
				return 3;
			} else if (block == 3) {
				// 우->하로 변경
				return 2;
			} else if (block == 4) {
				// 우->상로 변경
				return 1;
			}
			break;
		}
		return 0;
	}
}
