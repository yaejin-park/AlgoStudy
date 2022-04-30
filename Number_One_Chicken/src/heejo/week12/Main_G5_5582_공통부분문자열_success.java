package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * - DP를 이용
 * - 기존의 내용들을 바탕으로 같으면 +1을, 다르면 아무 입력도 하지 않기
 */
public class Main_G5_5582_공통부분문자열_success {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		int A_length = A.length();
		int B_length = B.length();
		int array[][] = new int[A_length+1][B_length+1];
		int max = 0;
		for (int i = 1; i <= A_length; i++) {
			for (int j = 1; j <= B_length; j++) {
				char a = A.charAt(i-1);
				char b = B.charAt(j-1);
				if(a==b) {
					array[i][j] = array[i-1][j-1] + 1;
					max = Math.max(array[i][j], max);
				}
			}
		}
		System.out.println(max);
//		System.out.print("  ");
//		for(int i =1; i<=B_length; i++) {
//			System.out.print(B.charAt(i-1) + " ");
//		}
//		System.out.println();
//		for(int i =1; i<=A_length; i++) {
//			System.out.print(A.charAt(i-1) + " ");
//			for(int j =1; j<=B_length; j++) {
//				System.out.print(array[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
}
