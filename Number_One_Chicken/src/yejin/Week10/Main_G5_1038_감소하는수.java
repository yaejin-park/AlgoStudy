package yejin.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_G5_1038_감소하는수 {

	//자리수와  해당 자리수의 숫자를 설정후 해당 자리수의 숫자로 시작할때 감소하는 수 개수가 몇개 나오는지 저장
	//맨 마지막 감소하는 수는 9876543210 -> 10자리수까지가능
	//해당 숫자로 시작한후 바로 그 뒤에 이어지는 숫자가 0~9일때
	static int decre[][] = new int[10][10]; 
	static int N;
	static boolean visited[] = new boolean[10];
	static long su = 0;
	static int cnt = -1;
	//자리수 : 0~9까지 존재
	//해당 자리 시작수 : 자리수~
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 10; i++) {
			decre[0][i] = 1;
			cnt++;
			if(cnt==N) {
				System.out.println(i);
				break;
			}
		}
		
		if(N >= 10) {//자리수 1개 이상
			loop:
			for (int i = 1; i < 10; i++) {			//자리수 10자리까지
				for (int j = i; j < 10; j++) {		//첫자리 값
					for (int j2 = i-1; j2 < j; j2++) {//두번째 자리 값
						decre[i][j] += decre[i-1][j2];
						cnt += decre[i-1][j2];
//						System.out.printf("%d : %d자리에 %d수, 두번째자리는 %d%n",cnt,i,j,j2);
						
						if(cnt>=N) {										//찾으려는 값보다 커지면	
							visited[j] = true;
							su = j*((long)Math.pow(10, i));					//첫번째 자리 수로 su 초기화
							cnt -= decre[i-1][j2];							//다시 빼고 차근차근 수 찾기
							find(i-1,j2);									//두번째 자리부터 재귀
							break loop;
						}
					}
				}
			}
		}
		
		//범위 넘은 수
		if(cnt < N) {
			System.out.println(-1);
		}
	}
	
	static boolean isFound = false;
	private static void find(int i, int j) {	//i:(0부터시작하는 자리) , j : 해당자리에 들어갈 수
		//발견하면 리턴시키기
		if(isFound) return;
		
		//
		su += j*(Math.pow(10, i));
		
		//마지막 자리까지 선택하면
		if(i == 0) {
			cnt++;	//갯수 증가
			
			if(cnt == N) {	//cnt개수까지 맞으면 끝!
				isFound = true;
				System.out.println(su);
				return;
			}
			return;
		}
		
		//순열(내림차순)
		for (int k = 0; k < j; k++) {	//j수보다 작은수만 넣을 수 있다.
			if(!visited[k]) {
				visited[k] = true;
				find(i-1,k);
				su -= k*(Math.pow(10, i-1));	//su도 초기화
				visited[k] = false;				//방문 초기화
			}
		}
	}
}
