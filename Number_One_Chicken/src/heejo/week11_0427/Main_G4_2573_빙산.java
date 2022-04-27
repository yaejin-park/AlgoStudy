package heejo.week11_0427;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - 큐를 사용하자
 * - 1년이 지나는 과정에 빙산이 분리가 됐는지, 또 빙산을 녹이는 내용이 들어가야 한다.
 * - [큐사용1] 빙산 분리 체크. 이때는 임시로 큐를 만들어서 체크하기
 * - [큐사용2] 빙산 녹이기. 이때는 아까부터 사용된 하나의 큐에서 체크하기
 * 
 * 1년주기
 * - 빙산이 현재 0인지 체크 => 그렇다면 0 출력
 * - 빙산이 지금 분리된 상태인지 체크 => 그렇다면 year 출력
 * - 둘 다 아니라면 빙산을 녹이기
 */
public class Main_G4_2573_빙산 {
	public static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][M];
		Queue<Node> queue = new LinkedList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>0) {
					queue.offer(new Node(i,j));
				}
			}
		}
		int result = game(N, M, map, queue);
		System.out.println(result);
	}
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static int game(int N, int M, int map[][], Queue<Node> queue) {
		int year = 0;
		while(true) {
			int iceberg = queue.size();
			
			//빙산이 분리가 안된채 결국 아무것도 없다면 0 출력
			if(iceberg==0) {
				return 0;
			}
			
			//빙산이 분리됐는지 확인
			Queue<Node> temp_queue = new LinkedList<>();
			boolean map_check[][] = new boolean[N][M];
			Node temp_node = queue.peek();
			temp_queue.offer(temp_node);
			map_check[temp_node.x][temp_node.y] = true;
			int count = 0;
			while(!temp_queue.isEmpty()) {
				temp_node = temp_queue.poll();
				count++;
				//상하좌우 탐방
				for(int k = 0; k<4; k++) {
					int nextI = temp_node.x + dr[k];
					int nextJ = temp_node.y + dc[k];
					//확인할 곳이 배열 범위에 속하면서
					if((nextI>=0)&&(nextI<N)&&(nextJ>=0)&&(nextJ<M)) {
						//아직 탐방 안했다면
						if((map[nextI][nextJ]!=0) &&(map_check[nextI][nextJ]==false)) {
							map_check[nextI][nextJ]=true;
							temp_queue.offer(new Node(nextI, nextJ));
						}
					}
				}
			}
			
			//큐의 맨 앞 부분에 연결된 빙하 개수가 전체 빙하 개수와 다르다면, 이는 분리가 된 것이다.
			if(count!=iceberg) {
				return year;
			}
			
			//분리 안됐다면 빙산을 녹인다.
			boolean map_temp[][] = new boolean[N][M];
			for(int i =0; i<iceberg; i++) {
				Node node = queue.poll();
				//0이라면 패스
				if(map[node.x][node.y]==0) {
					continue;
				}
				//상하좌우확인
				//바다랑 얼마나 겹치는지 확인
				int sea = 0;
				for(int k = 0; k<4; k++) {
					int nextI = node.x + dr[k];
					int nextJ = node.y + dc[k];
					//확인할 곳이 배열 범위에 속하면서
					if((nextI>=0)&&(nextI<N)&&(nextJ>=0)&&(nextJ<M)) {
						//해당 위치가 바다이면서
						//이번 stage에 0으로 바뀌지 않았다면(map_temp==false)
						if((map[nextI][nextJ]==0)&&(map_temp[nextI][nextJ]==false)) {
							sea++;
						}
					}
				}
				//해당 부분만큼 감소시킨다
				map[node.x][node.y] -= sea;
				if(map[node.x][node.y]<=0) {
					map_temp[node.x][node.y] = true;
					map[node.x][node.y] = 0;
				}
				else {
					queue.offer(node);
				}
			}
			//1년 증가
			year++;
		}
	}
}
