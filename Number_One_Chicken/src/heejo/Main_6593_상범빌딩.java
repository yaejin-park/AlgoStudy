package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - 삼중배열..
 * - BFS 방식으로 풀자
 * - 계산의 편의를 위해 건물 데이터를 받을 때, chat에서 int로 바꾸기
 * - BFS로 탈출하면서, 현재 자리에 올 수 있었던 가장 짧은 시간 기록하기
 */

public class Main_6593_상범빌딩 {
	static int L;	//층
	static int R;	//행
	static int C;	//열
	static int map[][][];	//상범빌딩 데이터

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//계속 반복
		while (true) {
			StringTokenizer str1 = new StringTokenizer(br.readLine());
			L = Integer.parseInt(str1.nextToken());
			R = Integer.parseInt(str1.nextToken());
			C = Integer.parseInt(str1.nextToken());
			map = new int[L][R][C];
			
			// 전부 0이라면 종료
			if ((L == 0) && (R == 0) && (C == 0)) {
				break;
			}

			int start[] = {0, 0, 0};	//시작 위치
			int end[] = {0, 0, 0};	//도착 위치
			String str2;
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					str2 = br.readLine();
					for (int k = 0; k < C; k++) {
						switch (str2.charAt(k)) {
						case 'S':
							//시작위치 저장
							start[0] = i;
							start[1] = j;
							start[2] = k;
							break;
						case '.':
							//갈 수 있는 위치에 0값
							map[i][j][k] = 0;
							break;
						case '#':
							//장애물 위치에 -1값
							map[i][j][k] = -1;
							break;
						case 'E':
							//도착위치 저장
							end[0] = i;
							end[1] = j;
							end[2] = k;						
							break;
						}
					}
				}
				str2 = br.readLine();	//각 층 사이 빈줄 입력받기
			}
			int totalTime = Integer.MAX_VALUE;
			
			//순서대로 상, 하, 좌, 우, 아래, 위
			int dr[] = {-1, 1, 0, 0, 0, 0};
			int dc[] = {0, 0, -1, 1, 0, 0};
			int dz[] = {0, 0, 0, 0, 1, -1};
			
			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[] {start[0], start[1], start[2], 1});	//출발 위치 넣어주기
			
			//실행(BFS)
			while(!queue.isEmpty()) {
				int temp[] = queue.poll();	//큐에서 데이터 하나 빼와서
				
				//현재 위치가 도착위치라면 totalTime 변수에 최소값 갱신
				if((temp[0]==end[0])&&(temp[1]==end[1])&&(temp[2]==end[2])) {
					totalTime = Math.min(totalTime, temp[3]);
				}
				//아직 도착하지 않았다면 탈출 이동하기
				else {
					//상,하,좌,우,아래,위
					for(int i =0; i<6; i++) {
						int nextZ = temp[0]+dz[i];	//다음 층
						int nextX = temp[1]+dr[i];	//다음 row
						int nextY = temp[2]+dc[i];	//다음 column
						int cnt = temp[3];	//현재까지 온 이동시간
						
						//다음 위치가 건물 범위 안에 속하고
						if((nextZ>=0)&&(nextZ<L)&&(nextX>=0)&&(nextX<R)&&(nextY>=0)&&(nextY<C)) {
							//그 값이 벽이 아니고
							if((map[nextZ][nextX][nextY]>=0)) {
								//이미 한번 확인했던 경로라면
								if(map[nextZ][nextX][nextY]>0) {
									//기존의 값과 현재 값 비교
									if(map[nextZ][nextX][nextY]>cnt) {										
										//갱신된다면 큐에 다시 넣기
										map[nextZ][nextX][nextY]=cnt;
										queue.offer(new int[] {nextZ, nextX, nextY, cnt+1});	
									}
								}
								//처음 만난 경로라면
								else {
									//현재 위치에 이동 시간 입력하고 큐에 다시 넣기
									map[nextZ][nextX][nextY] = cnt;
									queue.offer(new int[] {nextZ, nextX, nextY, cnt+1});
								}
							}
						}
					}
				}
			}
			
			//결과 출력
			if(totalTime==Integer.MAX_VALUE) {
				System.out.println("Trapped!");
			}
			else {
				//앞에서 계산의 편의를 위해 시간을 0초가 아닌 1초부터 시작했기 때문에, 1을 빼준다.
				System.out.println("Escaped in "+(totalTime-1)+" minute(s).");
			}
		}
	}
}
