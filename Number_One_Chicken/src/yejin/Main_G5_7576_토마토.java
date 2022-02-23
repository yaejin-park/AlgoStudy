package yejin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//다 익지 못하는 상황은
//-1로 막혀있는 상황

public class Main_G5_7576_토마토 {

	static int M, N; // 가로, 세로
	static int[][] map;
	static int day;

	static int reapCount = 0; // 익은 토마토 갯수
	static int goalCount = 0; // 다 익어야하는 토마토 갯수
	static Queue<Tomato> queue = new LinkedList<Tomato>();

	// 상하좌우
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st1 = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st1.nextToken());
		N = Integer.parseInt(st1.nextToken());

		goalCount = M * N;
		map = new int[N][M];

		// 전체 확인
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int status = Integer.parseInt(st.nextToken()); // 토마토상태
				map[i][j] = status; // 맵에 넣기
				if (status == 1) {
					reapCount++;
					queue.offer(new Tomato(i, j, 0));
				} else if(status == -1) {
					goalCount--;
				}
			}
		}
		
		// 전체 검사에서
		// 하나도 익은 토마토가 없으면 불가능
		if (reapCount == 0) {
			System.out.println(-1);

			// 이미 다 익었으면
		} else if (reapCount == goalCount) {
			System.out.println(0);
		} else { // 전체검사에서 나올 결과가 없으면
			int end = queue.size();
			for (int i = 0; i < end; i++) {
				bfs();
			}

			if (reapCount == goalCount) {
				System.out.println(day);
			} else {
				System.out.println(-1);
			}
		}

	}

	private static void bfs() {
		// 큐가 차있는 동안 계속 반복
		while (!queue.isEmpty()) {
			Tomato t = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];

				// 경계검사 통과 & 안익은 토마토면,
				if (nr < N && nr >= 0 && nc < M && nc >= 0 && map[nr][nc] == 0) {
					map[nr][nc] = 1;
					reapCount++; // 익은 토마토갯수 증가
					queue.offer(new Tomato(nr, nc, t.day + 1));
					// 익은 토마토로 표시
					map[t.r][t.c] = 1;
				}
			}
			// 며칠 걸렸는지 넣기
			day = Math.max(day, t.day);
		}
	}
}

//토마토 노드 클래스
class Tomato {
	int r;
	int c;
	int day;

	public Tomato(int r, int c, int day) {
		super();
		this.r = r;
		this.c = c;
		this.day = day;
	}
}
