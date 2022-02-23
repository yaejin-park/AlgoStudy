package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 제목 : 벽 부수고 이동하기
 * 사이트 : 백준
 * 난이도 : 골드4
 * 번호 : 2206
 * 
 * 목표 : 1,1에서 N,M까지 가장 짧은 거리를 출력하라. (벽 1개 부술 수 있다)
 * 
 * 풀이 방법
 * 0. 초기 설정
 * 1. 1,1부터 상하좌우로 이동 가능한지 확인 후 이동
 * 2. 이동시 자리에 이동 카운트를 사용한다.
 * 3. N,M에 도착하면 종료
 * 4. 1~4의 과정에서 지도의 벽 한개씩을 부수며 작동시켜보기
 * 5. 4의 결과들 중 최소값 출력
 */

public class Main_G4_2206_박희조 {
	static int N, M; // N row와 M column
	static int min; // 거리의 최소값
	static int map[][]; // 지도
	static boolean visited[][]; // 방문여부

	public static void main(String[] args) throws Exception {
		// 0. 초기설정
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		// 0-1. 변수설정
		N = Integer.parseInt(str1.nextToken()); // row
		M = Integer.parseInt(str1.nextToken()); // column
		min = N * M; // 최대값은 지도의 총 거리인 N*M이다.
		map = new int[N + 1][M + 1]; // (1,1)에서 (N,M)으로 가는 지도이기 때문에 +1을 한다.
		visited = new boolean[N + 1][M + 1];
		// 0-2. 지도 입력
		for (int i = 1; i <= N; i++) {
			String str2 = in.readLine();
			for (int j = 1; j <= M; j++) {
				//이동이 가능하다면
				if (str2.charAt(j - 1) == '0') {
					map[i][j] = 1;
				}
			}
		}

		// 1~4.실행 및 출력
		int min = bfs(1,1);	//벽 부수는 것 없이 실행
		int temp_result = 0;
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				//원상복구
				visited[i][j] = false;
				if(map[i][j]!=0) {
					map[i][j]=1;
				}
				if(map[i][j]==0) {
					map[i][j]=1;
					temp_result = bfs(1,1);
					if(min==-1) {
						min = temp_result;
					}
					else if((temp_result!=-1)&&(temp_result<min)) {
						min = temp_result;
					}
					map[i][j] = 0;
				}
			}
		}
		System.out.println(min);

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean isBroken;
	public static int bfs(int X, int Y) {
		Queue<int[]> queue = new LinkedList<int[]>();
		//초기 설정
		queue.offer(new int[] { X, Y});
		visited[X][Y] = true;
		
		//BFS
		while (!queue.isEmpty()) {
			int temp[] = queue.poll();
			int currentX = temp[0];
			int currentY = temp[1];
			//3. N,M위치면 즉시 종료
			if((currentX==N)&&(currentY==M)) {
				return map[N][M];
			}
			//1. 상하좌우 이동여부 확인
			for (int i = 0; i < 4; i++) {
				int nextX = currentX + dr[i];
				int nextY = currentY + dc[i];
				//범위 밖으로 벗어나면 패스
				if (nextX <= 0 || nextY <= 0 || nextX > N || nextY > M) {
					continue;	
				}
				//혹시 방문했거나, 이동할 수 없는 map이면 패스
				if (visited[nextX][nextY])
					continue;
				if (visited[nextX][nextY] || map[nextX][nextY] == 0)
					continue;
				queue.offer(new int[] { nextX, nextY});	//큐에 다음 좌표 삽입
		        map[nextX][nextY] = map[currentX][currentY] + 1;	//2. 다음 map 위치에 현재 위치값 +1 (추적)
				visited[nextX][nextY] = true;	//해당 좌표 사용한다는 체크
			}
		}
		//오류 발생
		return -1;
	}
}
