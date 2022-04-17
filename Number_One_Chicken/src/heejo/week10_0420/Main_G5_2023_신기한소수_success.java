package heejo.week10_0420;

import java.util.Scanner;

/*
 * 아이디어
 * - 그렇다면 역으로 작은 숫자에서 시작해보자
 * - 일의자리가 소수라면, 그 뒤에 숫자를 한개 붙여서 소수인지 판별하고, 맞다면 또 붙이고...
 * - 그렇다면 가지치기가 많이 줄어들 것으로 예상
 * 
 * 	ex) 3의자리 중 소수 찾는 과정
 *  - 1~9중 소수 찾기 => 2,3,5,7
 *  - 찾은 숫자들 중 차례로 뒤에 숫자 더 붙여서 소수 찾기. 2 => 20~29중 소수 찾기. 23,29
 *  - 23 => 230~239중 소수 찾기, 29 => 290~299중 소수 찾기
 */
public class Main_G5_2023_신기한소수_success {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		calc(0, 0);	//숫자, 자리수
	}
	
	public static void calc(int num, int length) {
		//숫자의 자리수가 N이 됐다면 소수찾기 성공이니 출력하고 종료
		if(length==N) {
			System.out.println(num);
			return;
		}
		
		//0부터 9까지 숫자를 뒤에 붙여보기
		nextNum : for (int i = 0; i<=9; i++) {
			int tempNum = num*10 + i;	//숫자 뒤에 붙이기
			
			//극초반에 유효한 if문. 혹시 0이나 1이라면 소수가 아니니까 패스
			if(tempNum<2) {
				continue;
			}
			
			int sqrtNum = (int) Math.sqrt(tempNum);	//소수 판별을 위해 제곱근 구하기
			//나누는 숫자는 2부터 제곱근까지
			for(int j=2;j<=sqrtNum;j++) {
				if(tempNum%j==0) {
					//소수가 아니라면 return
					continue nextNum;
				}
			}
			//소수가 맞다면 다음 calc를 실행
			calc(tempNum, length+1);
		}
	}
}
