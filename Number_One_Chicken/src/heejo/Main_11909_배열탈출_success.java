package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 오답노트(시간초과)
 * - DFS는 아닌거같다 (혹은 중단하는 브레이크 과정을 잘못 했거나)
 * - 어차피 탐색은 오른쪽,아래 방향으로만 일어난다.
 * - 첫번째줄 탐색은 한방향밖에 없음
 * - 그냥 이중for문으로 한줄한줄 읽어보자
 * - 방식은 이전과 마찬가지로 다른 배열에 버튼 누른 횟수 저장해가면서 최소값 비교하기
 */

public class Main_11909_배열탈출_success {
	static int n;	//배열 크기
	static int array[][];	//배열 데이터 저장
//	static int min;	//전체 최소값
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
		array_calc[0][0] = 0;	//시작 부분은 버튼을 누르지 않기에 0으로 처리
		
		//이중for문으로 배열 탐색 (방향은 오른쪽, 아래 순으로)
		for(int i =0 ;i<n;i++) {
			for(int j = 0; j<n; j++) {
				int nextX;
				int nextY;
				//조건1
				if((i>=0)&&(i<n-1)&&(j>=0)&&(j<n-1)) {
					//다음 위치1
					nextX = i;
					nextY = j+1;
					if(array[i][j]>array[nextX][nextY]) {
						array_calc[nextX][nextY] = Math.min(array_calc[i][j], array_calc[nextX][nextY]);
					}
					else {
						array_calc[nextX][nextY] = Math.min(array_calc[i][j] + (array[nextX][nextY]-array[i][j]+1),array_calc[nextX][nextY]);
					}
					
					//다음 위치2
					nextX = i+1;
					nextY = j;
					if(array[i][j]>array[nextX][nextY]) {
						array_calc[nextX][nextY] = Math.min(array_calc[i][j], array_calc[nextX][nextY]);
					}
					else {
						array_calc[nextX][nextY] = Math.min(array_calc[i][j] + (array[nextX][nextY]-array[i][j]+1),array_calc[nextX][nextY]);
					}
				}
				//조건2
				else if((i==n-1)&&(j<n-1)&&(j>=0)) {
					//다음 위치
					nextX = i;
					nextY = j+1;
					if(array[i][j]>array[nextX][nextY]) {
						array_calc[nextX][nextY] = Math.min(array_calc[i][j], array_calc[nextX][nextY]);
					}
					else {
						array_calc[nextX][nextY] = Math.min(array_calc[i][j] + (array[nextX][nextY]-array[i][j]+1),array_calc[nextX][nextY]);
					}
				}
				//조건3
				else if((j==n-1)&&(i>=0)&&(i<n-1)) {
					nextX = i+1;
					nextY = j;
					if(array[i][j]>array[nextX][nextY]) {
						array_calc[nextX][nextY] = Math.min(array_calc[i][j], array_calc[nextX][nextY]);
					}
					else {
						array_calc[nextX][nextY] = Math.min(array_calc[i][j] + (array[nextX][nextY]-array[i][j]+1),array_calc[nextX][nextY]);
					}
				}
			}
		}
		//결과 출력
		System.out.println(array_calc[n-1][n-1]);
	}
}
