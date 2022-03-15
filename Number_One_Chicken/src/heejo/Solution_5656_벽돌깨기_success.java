package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 5656 벽돌깨기
 * 
 * 아이디어
 * - 벽돌을 깨트릴 줄과 순서를 정하고
 * - 순차적으로 벽돌깨기를 실행
 * - 1라운드씩 끝날 때마다 벽돌 재정렬
 * - 라운드가 다 끝나면 벽돌 남은 개수를 확인 후 비교, 갱신
 * 
 * 오답노트
 * 1. 벽돌을 깨는 것이 확산되는 과정을 DFS가 아닌 BFS(Queue)를 이용해보자.. (DFS 너무 오래걸리고 OverFlow발생;)
 * 2. 이미 체크가 된 벽돌들은 queue에 넣지 않는다. (무한루프)
 * 3. 정해둔 줄에 벽돌이 없다면 그냥 넘겨
 * 
 * @author joy96
 *
 */
public class Solution_5656_벽돌깨기 {
	static int map_origin[][];
	static int map_temp[][];
	static boolean map_selected_origin[][];
	static boolean map_selected_temp[][];
	static int N;
	static int W;
	static int H;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer str = new StringTokenizer(in.readLine());
			N = Integer.parseInt(str.nextToken());
			W = Integer.parseInt(str.nextToken());
			H = Integer.parseInt(str.nextToken());
			map_origin = new int[H][W];
			map_selected_origin = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				str = new StringTokenizer(in.readLine());
				for (int j = 0; j < W; j++) {
					map_origin[i][j] = Integer.parseInt(str.nextToken());
				}
			}
			minCount = Integer.MAX_VALUE;
			chooseColumn = new int[N];
			isSelected = new boolean[N];
			// 실행. 모든 경우의 수를 해보고 난 후 가장 적은 최소값을 찾아보자
			chooseThreeColumn(0);
			
			System.out.println("#" + test_case + " " + minCount);
		}
	}

	static int chooseColumn[];
	static boolean isSelected[];
	static int minCount;
	static int count;

	public static void chooseThreeColumn(int cnt) {
		//깨트릴 줄과 순서를 정했다면
		if (cnt == N) {
			//백트래킹: 이론적으로 가능한 최소값이 떠버리면 종료해버리기
			if(minCount==0) {
				return;
			}
			//벽돌깨기 실행
			breakBrick();
			return;
		}
		//중복순열 : 벽돌을 깨트릴 줄을 순서대로 만들자
		else {
			for (int i = 0; i < W; i++) {
				chooseColumn[cnt] = i;
				chooseThreeColumn(cnt + 1);
			}
		}
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void breakBrick() {
		// 원본 map을 복사
		map_temp = new int[H][W];
		for (int a = 0; a < H; a++) {
			for (int b = 0; b < W; b++) {
				map_temp[a][b] = map_origin[a][b];
			}
		}

		// 해당 column 순서대로 breakBrick 실행
		for (int i = 0; i < N; i++) {
			//해당 column에서 가장 먼저 만나는 벽돌을 찾기
			for (int j = 0; j < H; j++) {
				//벽돌을 찾았다면
				if (map_temp[j][chooseColumn[i]] != 0) {
					//맵 체크용 배열 복사
					map_selected_temp = new boolean[H][W];
					for (int a = 0; a < H; a++) {
						for (int b = 0; b < W; b++) {
							map_selected_temp[a][b] = map_selected_origin[a][b];
						}
					}
					//큐 생성
					Queue<int[]> queue = new LinkedList<int[]>();
					queue.offer(new int[] { j, chooseColumn[i] });	// 현재 위치 큐에 넣기

					//큐가 비게 될 때까지 (깨트릴 수 있는 벽돌을 전부 확인할 때까지) 반복 
					while (!queue.isEmpty()) {
						int[] temp = queue.poll();	//큐에 쌓인 벽돌 하나 뽑아오기
						int value = map_temp[temp[0]][temp[1]] - 1;	//뽑아온 벽의 값 확인
						map_selected_temp[temp[0]][temp[1]] = true;	//부서지는 벽이라고 체크
						
						// 뽑아온 벽돌을 기준으로 상하좌우로 확인하기
						for (int k = 0; k < 4; k++) {
							int nextX = temp[0];
							int nextY = temp[1];
							//상하좌우 방향을 정하면(k) value값만큼의 거리(l) 내에 있는 벽돌들을 확인하기
							for (int l = 1; l <= value; l++) {
								nextX += dr[k];
								nextY += dc[k];
								// 다음 위치가 map 범위 내에 있다면
								if ((nextX < H) && (nextX >= 0) && (nextY < W) && (nextY >= 0)) {
									//그 위치에 벽돌이 있다면
									if (map_temp[nextX][nextY] > 0) {
										//그 벽돌은 아직 확인을 안했다면(큐에 적재가 안됐다면)
										if (!map_selected_temp[nextX][nextY]) {
											map_selected_temp[nextX][nextY] = true;	//벽돌 확인 체크하고
											queue.offer(new int[] { nextX, nextY });	//큐에 넣는다
										}
									}
								}
								// 범위를 벗어나면
								else {
									// 해당 방향으로는 더이상 탐색할 필요 없음
									break;
								}
							}
						}
					}
					//한바탕 소동이 끝나고 벽돌들을 다시 정렬하기
					arrangeBrick(map_temp);
					
					//바로 다음 column 순서로 넘어가기 위해 break
					break;
				}
			}
		}
		
		// 벽돌깨기가 전부 종료되면 남아있는 벽돌 개수 확인
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map_temp[i][j] > 0) {
					count++;
				}
			}
		}
		// 기존 최소값과 비교
		minCount = Math.min(minCount, count);
		return;
	}

	//벽돌 정렬
	public static void arrangeBrick(int map[][]) {
		//앞에서 진행한 벽돌깨기에서 체크된 벽돌들을 전부 0으로 바꾸고
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (map_selected_temp[j][i]) {
					map[j][i] = 0;
				}
			}
		}

		//0으로 된 자리들은 전부 밀어버린다. (벽돌들을 아래로 붙이기)
		for (int i = 0; i < W; i++) {
			int temp_row = -1;	//0이 있는 자리를 저장하는 용도의 변수
			// 아래에서부터 위로 한단계씩 확인
			for (int j = H - 1; j >= 0; j--) {
				// 해당 자리가 0이라면
				if (map[j][i] == 0) {
					// 이미 저장된 0자리가 없다면 기록
					if (temp_row == -1) {
						temp_row = j;
					}
					// 이미 저장된 0 자리가 있다면 패스
				}
				// 해당 자리가 0이 아니라면
				else {
					// 저장된 0자리가 있다면 거기로 값 이동
					if (temp_row != -1) {
						map[temp_row][i] = map[j][i];
						map[j][i] = 0;
						j = temp_row;
						temp_row = -1;
					}
					// 저장된 0자리가 없다면 패스
				}
			}
		}
	}
}
