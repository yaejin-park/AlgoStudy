package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 아이디어
 * - 하나씩 세기
 * - 이중for문
 * 
 * 시간 초과로 X
 */
public class Main_G5_5582_공통부분문자열_fail {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		int A_length = A.length();
		int B_length = B.length();
		int max_length = 0;
		for(int i =0; i<A_length; i++) {
			int temp_i = i;
			int temp_j = -1;
			int count = 0;
			for(int j =0; j<B_length; j++) {
				if(temp_i==i) {
					temp_j = j;
				}
				System.out.print("MATCH : " + A.charAt(temp_i) +"," + B.charAt(j));
				if(A.charAt(temp_i)==B.charAt(j)) {
					count++;
					temp_i++;
					System.out.println(" : SAME!!, count : " + count);
					if(temp_i==A_length) {
						break;						
					}
				}
				else {
					System.out.println(" : DIFF!!, count : " + count);
					max_length = Math.max(max_length, count);
					temp_i = i;
					count = 0;
					if(temp_j != j) {
						j = temp_j;
					}
				}
			}
			max_length = Math.max(max_length, count);
		}
		System.out.println(max_length);
	}
}
