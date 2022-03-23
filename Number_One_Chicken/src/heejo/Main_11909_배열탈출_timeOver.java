package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - DFS 이용
 * - 가능한 모든 경로를 탐색하여 각 경로의 버튼 클릭 횟수 파악해두기
 * - 경로를 지날 때마다 기존 경로들 중 버튼을 최소값으로 누른 횟수보다 크다면 종료, 작다면 갱신 후 이동
 * - 목적지까지 가능한 최소값 출력
 */

public class Main_11909_배열탈출_timeOver {
	static int n;	//배열 크기
	static int array[][];	//배열 데이터 저장
	static int min;	//전체 최소값
	static int array_calc[][];	//배열 데이터 (해당 위치로 이동하기 위해 버튼 누르는 최소값)
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		array = new int[n][n];
		array_calc = new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				array[i][j] = Integer.parseInt(str.nextToken());
				array_calc[i][j] = Integer.MAX_VALUE;	//초기값은 최대값으로
			}
		}
		min = Integer.MAX_VALUE;
		calc(0,0,0);
		//만약 최소값이 안나온다면 0으로 처리
		if(min==Integer.MAX_VALUE) {
			min = 0;
		}
		//결과 출력
		System.out.println(min);
	}
	public static void calc(int x, int y, int click) {
		//현재 위치까지 온 클릭 횟수가 기존 최소값보다 크다면 break 종료
		if(click>array_calc[x][y]) {
			return;
		}
		//목적지에 도착했다면 종료
		if((x==n-1)&&(y==n-1)) {
			min = Math.min(min, click);
			return;
		}
		//위의 내용에 해당사항 없다면 최소값이므로 반영 
		array_calc[x][y] = click;
		
		int nextX;
		int nextY;
		//조건1
		if((x>=0)&&(x<n-1)&&(y>=0)&&(y<n-1)) {
			//다음 위치1
			nextX = x;
			nextY = y+1;
			if(array[x][y]>array[nextX][nextY]) {
				calc(nextX, nextY, click);
			}
			else {
				calc(nextX, nextY, click+(array[nextX][nextY]-array[x][y]+1));
			}
			
			//다음 위치2
			nextX = x+1;
			nextY = y;
			if(array[x][y]>array[nextX][nextY]) {
				calc(nextX, nextY, click);
			}
			else {
				calc(nextX, nextY, click+(array[nextX][nextY]-array[x][y]+1));
			}
		}
		//조건2
		else if((x==n-1)&&(y<n-1)&&(y>=0)) {
			//다음 위치
			nextX = x;
			nextY = y+1;
			if(array[x][y]>array[nextX][nextY]) {
				calc(nextX, nextY, click);
			}
			else {
				calc(nextX, nextY, click+(array[nextX][nextY]-array[x][y]+1));
			}
		}
		//조건3
		else if((y==n-1)&&(x>=0)&&(x<n-1)) {
			nextX = x+1;
			nextY = y;
			if(array[x][y]>array[nextX][nextY]) {
				calc(nextX, nextY, click);
			}
			else {
				calc(nextX, nextY, click+(array[nextX][nextY]-array[x][y]+1));
			}
		}
	}
}
