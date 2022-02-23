package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 내리막 길
 * 백준 골드4
 * 1520
 * 
 * 목표 : 0,0부터 M-1,N-1까지 내려가는 경로의 개수를 구하라
 * 
 * 풀이 방법(DFS)
 * 0. 기본 설정
 * 1. 하좌우 방향으로 한칸씩 내려가며 길을 찾는다.		================== comment : 위쪽 방향으로 가는것도 고려해야 합니다.
 * 2. 길이 완성될 때마다 카운트에 1을 증가한다.
 * 3. 결과 출력
 * 
 * 시간초과를 해결할 방법은 무엇이 있을까요...
 */
public class hejoo_Main_1520_내리막길_실패 {
	static int M, N;	//지도 세로와 가로
	static int map[][];	//지도 배열
	static int H;	//최종 경로 수 
	static boolean isSelected[][];	//해당 지도 위치가 사용되었는지 체크
	static int[] dr = {1, 0, 0};	//x이동에 사용되는 배열 (하 좌 우)
	static int[] dc = {0, -1, 1};	//y이동에 사용되는 배열 (하 좌 우)
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//0. 기본 설정
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		M = Integer.parseInt(str1.nextToken());
		N = Integer.parseInt(str1.nextToken());
		map = new int[M][N];
		isSelected = new boolean[M][N];
		for(int i=0;i<M; i++) {
			StringTokenizer str2 = new StringTokenizer(in.readLine());
			for (int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(str2.nextToken());
			}
		}
		
		//출발 위치를 true로 두고, DFS 시작
		isSelected[0][0]=true;
		dfs(0, 0);
		
		//3. 결과 출력
		System.out.println(H);
	}
	
	
	public static void dfs(int x, int y) {
		//해당 위치에 도착하면 H를 1 증가하고 종료
		if((x==(M-1))&&(y==(N-1))) {
			H++;
			return;
		}
		
		//맨 마지막 줄에 있는데, 오른쪽으로는 이동이 불가능한 경우 종료
		if(x==(M-1)&&((isSelected[x][y+1]==true)||(map[x][y+1]>=map[x][y]))) {
			return;
		}
		
		//일반적인 경우
		else {
			//하, 좌, 우 방향으로 탐색
			for(int i = 0; i<3; i++) {
				int nextX = x + dr[i];
				int nextY = y + dc[i];
				
				//배열 밖으로 벗어나면 탐색 안함
				if(nextX<0 || nextX>=M || nextY<0 || nextY>=N) {
					continue;
				}
				
				//해당 다음 위치가 아직 이동한 곳이 아니고, 값도 현재 위치보다 작을 경우 이동한다.
				if((!isSelected[nextX][nextY])&&(map[nextX][nextY]<map[x][y])) {
					isSelected[nextX][nextY]=true;	//해당 칸으로 이동한다는 표시
					dfs(nextX,nextY);
					isSelected[nextX][nextY]=false;	//해당 칸 이동한 표시 지우기
				}
			}
		}
	}
}
