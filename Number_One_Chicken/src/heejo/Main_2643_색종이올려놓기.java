package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - DP를 이용
 * - 현재 선택된 색종이를 품을 수 있는 색종이들 중 가장 큰 개수를 가진 색종이 + 1로 하기 
 */
public class Main_2643_색종이올려놓기 {
	static int N;
	static int array[][];
	static int calc[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][2];
		calc = new int[N];
		for(int i =0; i<N; i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			array[i][0] = Integer.parseInt(str.nextToken());
			array[i][1] = Integer.parseInt(str.nextToken());
		}
		
		//실행: 모든 색종이 탐방
		for(int i =0; i<N; i++) {
			colorPaper(i, array[i][0], array[i][1]);
		}
		
		//결과 출력: 최대값
		int max = 0;
		for(int i =0; i<N; i++) {
			max = Math.max(max, calc[i]);
		}
		System.out.println(max);
	}
	
	//값: 현재 색종이를 품을 수 있는 최대 색종이 수
	public static int colorPaper(int i, int x, int y) {
		//현재 색종이에 값이 저장되어있는가?
		if(calc[i]>0) {
			return calc[i];
		}
		//저장되지 않았다면
		else {			
			//현재 색종이보다 큰 색종이들이 가지고 있는 값들중에 최대값 + 1 저장하기
			int max = 0;
			for(int j = 0; j<N; j++) {
				if(j==i) {
					continue;
				}
				//x가 y보다 크다면
				if((array[j][0]>=x)&&(array[j][1]>=y)) {
					max = Math.max(colorPaper(j, array[j][0], array[j][1]), max);
				}
				//y가 x보다 크다면(90도 회전)
				else if((array[j][0]>=y)&&(array[j][1]>=x)) {
					max = Math.max(colorPaper(j, array[j][1], array[j][0]), max);
				}
			}
			calc[i] = max+1;	//최대값+자기자신(1)
			return calc[i];
		}
	}
	
}
