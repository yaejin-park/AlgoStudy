package yejin.Week3;

import java.util.Scanner;

public class Main_S5_9655_돌게임 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		//상근이 먼저 시작
		//1,3개만 가져갈 수 있으므로 상근이는 홀수 돌번째만, 창영이는 무조건 홀수에 홀수를 가져가므로 짝수번째만 가져가게 됨
		if(N%2==1) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
		
	}

}
