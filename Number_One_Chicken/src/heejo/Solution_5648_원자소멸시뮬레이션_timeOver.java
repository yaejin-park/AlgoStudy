package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - 원자들의 이동에는 1회전을 돌려야한다. (이 때, 거리는 1이 아닌 0.5)
 * - 1회전 이후 충돌하는 원자들이 있는지 확인하고 에너지를 방출하고 원자를 없앤다.
 * - 이 회전을 맵에 남은 원자가 없을 때까지 반복한다.
 * - 원자가 맵 밖으로 이동하면 에너지를 방출하지 않고 없앤다.
 * 
 * 문제점
 * - map[4001][4001][2]사이즈가 너무 커서 선언 및 초기화 단계에서 시간이 많이 걸린다.
 */
public class Solution_5648_원자소멸시뮬레이션_timeOver {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 원자의 수
			int range = 4001;
			// 큐1. 원자의 위치와 싸이클
			Queue<int[]> queue = new LinkedList<int[]>();
			// 큐2. 겹치는 원자의 위치
			Queue<int[]> queue2 = new LinkedList<int[]>();
			int map[][][] = new int[range][range][2];
			// 원자 데이터 입력받아 초기설정하기
			for (int i = 0; i < N; i++) {
				StringTokenizer str = new StringTokenizer(br.readLine());
				int atomX = (Integer.parseInt(str.nextToken()) + 1000) * 2;
				int atomY = (Integer.parseInt(str.nextToken()) + 1000) * 2;
				int atomDirection = Integer.parseInt(str.nextToken());
				int atomEnergy = Integer.parseInt(str.nextToken());
				// 큐1에 원자 데이터 넣기
				queue.offer(new int[] { atomY, atomX, atomDirection, atomEnergy });
				// 맵 map[][][0]에 에너지 추가로 넣기
				map[atomY][atomX][0] += atomEnergy;
				// 맵 map[][][1]값 1 증가
				map[atomY][atomX][1]++;
			}
			int totalEnergy = 0;
			int dr[] = { 1, -1, 0, 0 };
			int dc[] = { 0, 0, -1, 1 };
			int restAtomCount = N; // 남은 원자 수
			while (restAtomCount > 0) {
				// 한 싸이클
				int round = queue.size();
				for (int i = 0; i < round; i++) {
					// 원자 이동
					// 큐1에서 원자를 꺼내온다.
					if (queue.isEmpty()) {
						System.out.println("큐에 아무것도 없음 에러!!");
						break;
					}
					int temp[] = queue.poll();
					if (map[temp[0]][temp[1]][1] == 0) {
						// 이 큐는 없었던 걸로..
						restAtomCount--;
						continue;
					}
					map[temp[0]][temp[1]][0] -= temp[3];
					map[temp[0]][temp[1]][1]--;
					// 원자를 이동시킨다.
					temp[0] += dr[temp[2]];
					temp[1] += dc[temp[2]];

					// 만약 지도 범위를 벗어났다면 그냥 없앤다.
					if ((temp[0] < 0) || (temp[0] >= range) || (temp[1] < 0) || (temp[1] >= range)) {
						restAtomCount--;
					} else {
						map[temp[0]][temp[1]][0] += temp[3];
						map[temp[0]][temp[1]][1]++;
						// 이동한 위치에 원자가 겹친다면
						if (map[temp[0]][temp[1]][1] > 1) {
							if (map[temp[0]][temp[1]][1] == 2) {
								// 큐2에 해당 위치를 넣는다. (한번만 넣는다)
								queue2.offer(new int[] { temp[0], temp[1] });
							}
						}
						// 이동한 위치에 원자가 겹치지 않는다면
						else {
							// 다시 큐1에 넣는다.
							queue.offer(temp);
						}
					}
				}
				// 한 싸이클 끝나면 충돌한 공들을 처리하자
				// 큐2 크기만큼 반복
				int size = queue2.size();
				for (int i = 0; i < size; i++) {
					// 큐2에 값을 빼온다.
					int temp[] = queue2.poll();
					// 해당 위치의 map[][][0] value를 totalEnergy에 추가
					totalEnergy += map[temp[0]][temp[1]][0];
					// 해당 위치의 map[][][0]를 0으로 변경
					map[temp[0]][temp[1]][0] = 0;
					// 해당 위치의 map[][][1] count를 restAtomCount에 빼기
					restAtomCount -= (map[temp[0]][temp[1]][1] - 1);
					// 해당 위치의 map[][][1]를 0으로 변경
					map[temp[0]][temp[1]][1] = 0;
				}
			}
			System.out.println("#" + test_case + " " + totalEnergy);

		}
	}
}
