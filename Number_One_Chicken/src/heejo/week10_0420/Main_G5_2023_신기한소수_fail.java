package group_study;

import java.util.Scanner;

/*
 * 아이디어
 * - N의자리에 해당하는 숫자들을 전부 탐색
 * - 소수 판별하는 과정에서 제곱근까지만 나눠보면 시간 save가 되지 않을까
 * 	ex) 100이 소수인지 판별하기 위해선 2부터 100의 제곱근인 10까지만 나눠본다
 * 
 * 결과
 * - 바로 실패
 * - 8자리 탐색에 시간이 너무 걸린다. 90000000 * 소수탐색
 */
public class Main_G5_2023_신기한소수_fail {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = (int) Math.pow(10, N - 1); i < Math.pow(10, N); i++) {
			check(i, N);
//			System.out.println("check : " + i);
		}
	}

	public static void check(int num, int N) {
		int tempNum = num;
		for (int i = 1; i < N; i++) {
			int sqrtNum = (int) Math.sqrt(tempNum);
			for (int j=2; j<=sqrtNum; j++) {
				//나머지가 없다면 소수가 아니다.
				if(tempNum%j==0) {
					return;
				}
			}
			tempNum /= 10;
		}
		//일의자리인 경우
		if((tempNum==2)||(tempNum==3)||(tempNum==5)||(tempNum==7)) {
			System.out.println(num);
		}
	}
}
