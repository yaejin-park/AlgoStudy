package week13_0511;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * - 4가지 조건을 다 만족해야 한다는 것을 생각
 * - 
 */
public class Main_G5_19942_다이어트 {
	static int N;
	static int array[][];
	static int cond[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][5];
		cond = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			cond[i] = Integer.parseInt(st.nextToken()); // 최소 단백질, 지방, 탄수화물, 비타민
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = new int[5];
		result[4] = Integer.MAX_VALUE;
		choose = new boolean[N];
		queue = new LinkedList<>();
		dfs(0, new int[] { 0, 0, 0, 0, 0 });

		if (result[4] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result[4]);
			while (!queue.isEmpty()) {
				System.out.print(queue.poll() + " ");
			}
		}
	}

	static int result[];
	static boolean choose[];
	static Queue<Integer> queue;

	public static void dfs(int cnt, int calc[]) {
		if (cnt == N) {
			return;
		} else {
			choose[cnt] = true;
			int calc2[] = new int[5];
			// 먼저 계산
			for (int i = 0; i < 5; i++) {
				calc2[i] = calc[i];
				calc[i] += array[cnt][i];
			}
			// 조건에 부합했다면 그대로 계산. 그 이후는 계산X ex) A~E중 AB에서 이미 최소값을 넘겼으니, AB에 C,D,E가 들어가는 경우는 계산 안한다.
			if (cond[0] <= calc[0] && cond[1] <= calc[1] && cond[2] <= calc[2] && cond[3] <= calc[3]) {
				if (calc[4] < result[4]) {
					queue.clear();
					for (int i = 0; i < 5; i++) {
						result[i] = calc[i];
					}
					for (int i = 0; i < N; i++) {
						if (choose[i]) {
							queue.offer(i + 1);
						}
					}
				}
			}
			// 아직 부족하다면 추가해보기
			else {
				dfs(cnt + 1, calc);
			}
			// 해당 음식을 빼고 계산해보기
			choose[cnt] = false;
			dfs(cnt + 1, calc2);
		}
	}
}
