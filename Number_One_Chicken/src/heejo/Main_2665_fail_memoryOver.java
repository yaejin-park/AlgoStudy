package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 미로만들기
 * 백준 2665
 * 골드4
 * 
 * 목표 : 시작에서 끝지점으로 가는 길 중 방의 색을 몇개 바꿔야하는지 출력하기
 * 
 * 풀이방법 BFS
 * 1. 지도카운트 배열을 int 최대값으로 초기화
 * 2. 출발지(0,0)부터 상하좌우로 탐색
 * 3. 다음 목적지 카운트 비교해서 이동
 * 
 * 실패 : 메모리 초과
 * 
 * @author joy96
 *
 */
public class Main_2665_fail_memoryOver {
	static int N;
	static boolean[][] map;
	static int[][] count;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new boolean[N][N];
		count = new int[N][N];
		String str;
		for (int i = 0; i < N; i++) {
			str = in.readLine();
			for (int j = 0; j < N; j++) {
				if(str.charAt(j)==1) {
					map[i][j] = true;					
				}
				else {
					map[i][j] = false;
				}
				
				count[i][j] = Integer.MAX_VALUE;
			}
		}

		bfs();

		System.out.println(count[N - 1][N - 1]);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, 0));
		count[0][0] = 0;

		int nextX;
		int nextY;
		while (!queue.isEmpty()) {
			Node current = queue.poll();

			//상하좌우 순으로 이동
			for (int i = 0; i < 4; i++) {
				nextX = current.x + dr[i];
				nextY = current.y + dc[i];
				//변경 좌표가 배열 범위 안에 들어올 경우
				if ((nextX >= 0 && nextY >= 0 && nextX < N && nextY < N)) {
					//현재 횟수보다 이동하는 곳의 횟수가 더 크다면 이동
					if (count[nextX][nextY] > count[current.x][current.y]) {
						//이동하는 곳이 흰 방이라면 그대로 가자
						if (map[nextX][nextY]) {
							count[nextX][nextY] = count[current.x][current.y]; // 계속 간다
						//검은방이라면 카운트 1 증가
						}
						else {
							count[nextX][nextY] = count[current.x][current.y] + 1; // 카운트 1 증가
						}
						queue.offer(new Node(nextX, nextY));
					}
					//이동하는 곳의 횟수가 더 작다면 종료
				}
			}
		}
	}

	//노드 생성
	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
