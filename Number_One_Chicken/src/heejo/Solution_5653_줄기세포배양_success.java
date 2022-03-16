package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - 큐를 이용해볼까?
 * - 큐를 두개 이용
 * - 큐1은 기존에 있는 비활성or활성 세포들, 큐2는 새롭게 생성된 세포들을 다루자.
 * 
 * 실행과정 (T시간만큼 반복)
 * - 큐1 한 사이클 실행.
 * 	- 큐1에서는 비활성or활성 세포들을 관리
 * 	- 번식이 된다면 해당 세포들의 내용(위치와 수치)을 큐2로 넘기자.
 * 	- 세포가 죽게 된다면 큐1에 다시 넣지 말고 끝내자
 * - 큐2 한 사이클 실행
 *  - 큐2에서는 새롭게 생기는 세포들을 관리
 *  - 새롭게 생기는 세포의 자리에 기존 수치가 있다면 해당 수치를 더 큰값으로 만들기 (큐1에서 수치가 다른 큐는 버리는 기능을 넣으면 된다.)
 * 
 * 오답노트
 * - 큐1에 너무 많은 큐가 생성이 되면서 실행 오류가 만들어진다. 큐1의 세포 번식 부분을 손보자.
 * 	- [변경 후] 큐1은 비활성 세포들만을 관리 (활성 세포 관리X)
 *  - [변경 후] 번식을 하고 새로운 세포들을 큐2에 보내고 난 후에는 절대 큐1에 다시 넣지 않고 무조건 종료.
 *  - [변경 후] 종료하기 전에 현재 위치의 활성세포가 T시간이후에도 죽지 않는지 확인, 죽으면 지도에 -1처리(추후 개수 셀 때 반영x). 아니라면 1처리(추후 개수에 반영o).
 */

public class Solution_5653_줄기세포배양_success {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(str.nextToken());
			int M = Integer.parseInt(str.nextToken());
			int K = Integer.parseInt(str.nextToken());
			int rangeN = (2 * K) + N;
			int rangeM = (2 * K) + M;
			int map[][] = new int[rangeN][rangeM];
			// 큐1. 현재 비활성 상태에 있는 세포들을 저장 (가로, 세로, 원래상태시간, 남은상태시간)
			Queue<int[]> queue1 = new LinkedList<int[]>();
			// 큐2. 활성 상태로 새로 생기는 번식을 저장 (가로, 세로, 원래상태시간)
			Queue<int[]> queue2 = new LinkedList<int[]>();
			for (int i = 0; i < N; i++) {
				str = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i + K][j + K] = Integer.parseInt(str.nextToken());
					// 해당 위치에 세포가 존재하다면
					if (map[i + K][j + K] > 0) {
						// 큐1에 활성 상태 세포 저장 (가로위치, 세로위치, 생명력 수치, 활성상태시간+비활성상태시간)
						queue1.offer(new int[] { i + K, j + K, map[i + K][j + K], 2 * map[i + K][j + K] });
					}

				}
			}
			int dr[] = { -1, 1, 0, 0 };
			int dc[] = { 0, 0, -1, 1 };

			// 줄기세포 배양 실행
			for (int i = 1; i <= K; i++) {
				// 큐1 1회전 돌리기
				int queue1Size = queue1.size();
				for (int j = 0; j < queue1Size; j++) {
					// 큐1에서 세포 하나 추출
					int tempQueue[] = queue1.poll(); // 가로, 세로, 원래시간, 남은시간
					
					// 해당 위치의 세포의 생명력 수치가 지도map에 나온 수치와 다르다면 버리기
					if (tempQueue[2] != map[tempQueue[0]][tempQueue[1]]) {
						continue;
					}
					// 남은 비활성+활성 시간을 1 감소
					tempQueue[3]--;

					// 현재가 활성이 시작되고 첫 시간이었다면
					if (tempQueue[3] == tempQueue[2] - 1) {
						// 상하좌우 판별해보고 넣을 수 있다면 큐2에 넣기
						for (int k = 0; k < 4; k++) {
							// map 해당 위치가 0이라면 아직 세포가 없다는 것이므로, 큐2에 삽입
							if (map[tempQueue[0] + dr[k]][tempQueue[1] + dc[k]] == 0) {
								queue2.offer(new int[] { tempQueue[0] + dr[k], tempQueue[1] + dc[k], tempQueue[2] });
							}
						}
						// 활성이 시작되고 번식이 된 후라면, 큐1에 다시 집어넣을 이유는 없기에, 아래 결정으로 넘어간다.
						// 결정1. 남은 활성 시간이 남은 실행 시간보다 크다면, 종료 후에도 활성 세포로 남아있는다.
						if (tempQueue[3] > (K - i)) {
							map[tempQueue[0]][tempQueue[1]] = 1;
						}
						// 결정2. 그렇지 않다면, 종료 후에는 죽은 세포가 된다.
						else {
							map[tempQueue[0]][tempQueue[1]] = -1;
						}
					}
					else {
						// 아직 활성단계로 진입하지 않은 비활성 세포들은 큐1에 재삽입
						queue1.offer(tempQueue);
					}
				}
				// 큐2 1회전 돌리기
				int queue2Size = queue2.size();
				for (int j = 0; j < queue2Size; j++) {
					// 큐2에서 세포 하나 추출
					int tempQueue[] = queue2.poll(); // 가로, 세로, 생명력 수치(원래시간)
					// 해당 위치 map이 0보다 크다면 생명력 수치 비교
					if (map[tempQueue[0]][tempQueue[1]] > 0) {
						// 현재 추출한 세포의 생명력이 더 크다면 지도map에 반영 (큐1 회전시 생명력 더 작은 애들 쳐낼 수 있다)
						map[tempQueue[0]][tempQueue[1]] = Math.max(map[tempQueue[0]][tempQueue[1]], tempQueue[2]);
					}
					// 해당 위치 map에 세포가 없다면 세포 생성
					else {
						map[tempQueue[0]][tempQueue[1]] = tempQueue[2];
					}
					// 큐2를 큐1에 넣기
					queue1.offer(new int[] { tempQueue[0], tempQueue[1], tempQueue[2], 2 * tempQueue[2] });
				}
			}
			//실행 후 비활성or활성 세포의 개수를 센다.
			int count = 0;
			for (int i = 0; i < rangeN; i++) {
				for (int j = 0; j < rangeM; j++) {
					if (map[i][j] > 0) {
						count++;
					}
				}
			}
			//결과 출력
			System.out.println("#" + test_case + " " + count);
		}
	}
}
