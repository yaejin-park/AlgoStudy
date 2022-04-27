package heejo.week11_0427;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 아이디어
 * - BFS. 큐를 사용하자
 * - 모든 곳마다 탐색을 하면서 결과를 갱신해보기 (50x50이라 해볼만할듯)
 */
public class Main_G5_2589_보물섬 {
	static int result;
	static int M;
	static int N;
	static char island[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		island = new char[M][N];
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				island[i][j] = str.charAt(j);
				if (island[i][j] == 'L') {
					result = 1;	//혹시 하나의 육지라도 있다면 이는 최소 1부터 시작함을 의미
				}
			}
		}
		//좌표를 한군데씩 돌아가면서 실행
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// 육지면서 아직 확인 안한 곳이라면
				if (island[i][j] == 'L') {
					// 실행
					exe(i, j);
				}
			}
		}
		//결과 출력
		System.out.println(result);
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void exe(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		boolean map_temp[][] = new boolean[M][N];
		int length = 0;
		
		map_temp[a][b] = true;
		queue.offer(new int[] {a, b, length});
		while (!queue.isEmpty()) {
			int temp[] = queue.poll();
			length = temp[2];
			for (int k = 0; k < 4; k++) {
				int nextI = temp[0] + dr[k];
				int nextJ = temp[1] + dc[k];
				// 범위 내 들어오고
				if (nextI >= 0 && nextI < M && nextJ >= 0 && nextJ < N) {
					// 육지면서 아직 확인 안한 곳이라면
					if (island[nextI][nextJ] == 'L' && map_temp[nextI][nextJ] == false) {
						map_temp[nextI][nextJ] = true;
						queue.offer(new int[] { nextI, nextJ, temp[2] + 1 });
					}
				}
			}
		}
		//갱신
		result = Math.max(length, result);
		return;
	}
}
