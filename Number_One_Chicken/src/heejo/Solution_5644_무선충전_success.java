package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 무선충전 5644
 * 
 * 아이디어
 *  - 사람은 두명
 *  - BC(이하 기지국)은 최대 8개이므로, 해당 위치에 기지국이 있는지 표기하는 것이 관건 2진법을 이용하여 1번 기지국은 1의자리, 2번 기지국은 2의자리 등을 표기하는것으로 하자
 *  	ex) 해당 위치의 값이 5(101)이면 해당 위치는 3번과 1번 기지국이 있다는 것을 의미
 *  - 기지국이 여러개인 경우에는 => 서로 겹치는지 확인해보자
 *   - 겹치지 않다면, 각각의 기지국들 중 가장 센파워를 취하자
 *   - 겹친다면
 *    - 겹치는 기지국이 둘 다에게 최대 파워가 아니라면, 그냥 센 파워를 취하자
 *    - 겹치는 기지국이 최소 한명에게 최대 파워라면, 겹치는 기지국을 제외한 후 나머지 한명의 최대파워 + 겹치는 기지국 파워
 * 
 * 오답노트
 * 1. 당연히 map을 받는 x좌표가 row, y좌표가 column인줄 알았다........
 * 2. 기지국이 중복되는 기준은 기지국의 파워 값이 아닌 기지국의 번호이다.........
 * 
 * @author joy96
 *
 */
public class Solution_5644_무선충전_success {
	static int map[][];
	static int N = 10;
	static int M; // 총 이동 시간
	static int A; // 기지국의 개수
	static int arrayA[]; // A의 이동경로
	static int arrayB[]; // B의 이동경로
	static int BCInfor[][]; // 기지국에 대한 정보. (순서대로 x좌표, y좌표, 거리, 파워)
	static int totalSum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			M = Integer.parseInt(str.nextToken());
			A = Integer.parseInt(str.nextToken());
			map = new int[N][N];
			arrayA = new int[M];
			arrayB = new int[M];
			str = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arrayA[i] = Integer.parseInt(str.nextToken());
			}
			str = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arrayB[i] = Integer.parseInt(str.nextToken());
			}
			BCInfor = new int[A][4];
			for (int i = 0; i < A; i++) {
				str = new StringTokenizer(br.readLine());
				BCInfor[i][1] = Integer.parseInt(str.nextToken()) - 1; // x좌표
				BCInfor[i][0] = Integer.parseInt(str.nextToken()) - 1; // y좌표
				BCInfor[i][2] = Integer.parseInt(str.nextToken()); // 거리
				BCInfor[i][3] = Integer.parseInt(str.nextToken()); // 파워
			}

			int map_r[] = { 1, 1, -1, -1 };
			int map_c[] = { 1, -1, -1, 1 };
			// BCInfor를 바탕으로 map에 표시하기 (4중 for문......)
			for (int i = 0; i < A; i++) {
				int val = (int) Math.pow(2, i);
				int x, y;
				for (int j = 0; j <= BCInfor[i][2]; j++) {
					if (j == 0) {
						x = BCInfor[i][0];
						y = BCInfor[i][1];
						map[x][y] += val;
						continue;
					}
					for (int k = 0; k < 4; k++) {
						for (int l = 0; l < j; l++) {
							if (k % 2 == 1) {
								x = BCInfor[i][0] + ((j - l) * map_r[k]);
								y = BCInfor[i][1] + (l * map_c[k]);
							} else {
								x = BCInfor[i][0] + (l * map_c[k]);
								y = BCInfor[i][1] + ((j - l) * map_r[k]);
							}
							if ((x >= 0) && (x < N) && (y >= 0) && (y < N)) {
								map[x][y] += val;
							}
						}
					}
				}
			}
			
			//게임에서 사용될 x,y좌표들
			int A_X = 0; // A의 x좌표
			int A_Y = 0; // A의 y좌표
			int B_X = N - 1; // B의 x좌표
			int B_Y = N - 1; // B의 y좌표

			// 게임 실행
			totalSum = 0;
			calc(map[A_X][A_Y], map[B_X][B_Y]); // 시작 위치에서부터 충전이 가능하다고 함
			for (int i = 0; i < M; i++) {
				// 이동
				A_X += dr[arrayA[i]];
				A_Y += dc[arrayA[i]];
				B_X += dr[arrayB[i]];
				B_Y += dc[arrayB[i]];
				calc(map[A_X][A_Y], map[B_X][B_Y]);
			}
			System.out.println("#" + test_case + " " + totalSum);
		}
	}

	// 계산
	static void calc(int mapValueA, int mapValueB) {
		// 기지국 몇개인지 확인
		boolean arrayA[] = new boolean[A];
		boolean arrayB[] = new boolean[A];
		if ((mapValueA == 0) && (mapValueB == 0)) {
			return;
		}
		int countA = 0;
		int countB = 0;
		int maxApower = 0;
		int maxBpower = 0;
		int maxApowerIndex = -1;
		int maxBpowerIndex = -1;
		for (int i = 0; i < A; i++) {
			//A가 서있는 곳에 해당 i 기지국이 있다면
			if (((mapValueA / (int) Math.pow(2, i)) % 2) == 1) {
				arrayA[i] = true;	//A가 있는 곳에 i번째 기지국이 있음
				countA++;	//A의 기지국 개수 1 증가
				
				//파워 최대값이 발견되면, 최대값과 해당 번호 갱신
				if(maxApower<BCInfor[i][3]) {
					maxApower=BCInfor[i][3];
					maxApowerIndex = i;
				}
			}
			//B가 서있는 곳에 해당 i 기지국이 있다면
			if (((mapValueB / (int) Math.pow(2, i)) % 2) == 1) {
				arrayB[i] = true;	//B가 있는 곳에 i번째 기지국이 있음
				countB++;	//B의 기지국 개수 1 증가
				
				//파워 최대값이 발견되면, 최대값과 해당 번호 갱신
				if(maxBpower<BCInfor[i][3]) {
					maxBpower=BCInfor[i][3];
					maxBpowerIndex = i;
				}
			}
		}

		// 두 사람이 서있는 곳에 기지국이 각각 한개씩이라면
		if ((countA == 1) && (countB == 1)) {
			// 같은 기지국이라면 나눠서 합기
			if (mapValueA == mapValueB) {
				totalSum += maxApower;
			}
			// 다른 기지국이라면 각각 합하기
			else {
				totalSum += maxApower;
				totalSum += maxBpower;
			}
		}
		// 기지국이 여러개인 경우에는
		else {
			// 각자의 최대파워 번호가 겹치지 않다면, 각각의 기지국들 중 가장 센 파워를 취하자
			if ((maxApowerIndex != maxBpowerIndex)) {
				totalSum += maxApower;
				totalSum += maxBpower;
			}
			// 최대파워 번호가 겹친다면, 해당 번호를 제외하고 가장 센 파워를 찾아보자
			else {
				int secondApower = 0;
				int secondBpower = 0;
				for (int i = 0; i < A; i++) {
					if (arrayA[i]) {
						if ((i!=maxApowerIndex)) {
							secondApower = Math.max(secondApower, BCInfor[i][3]);
						}
					}
					if (arrayB[i]) {
						if ((i!=maxBpowerIndex)) {
							secondBpower = Math.max(secondBpower, BCInfor[i][3]);
						}
					}
				}
				// 둘의 두번째 파워들 중에 더 큰 값 + 최대파워
				totalSum += Math.max(secondApower, secondBpower);
				totalSum += maxApower;
			}
		}
	}

	// 정지, 상, 우, 하, 좌
	static int dr[] = { 0, -1, 0, 1, 0 };
	static int dc[] = { 0, 0, 1, 0, -1 };
}
