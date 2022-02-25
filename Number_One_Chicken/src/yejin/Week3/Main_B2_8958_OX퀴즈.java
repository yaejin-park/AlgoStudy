package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B2_8958_OX퀴즈 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			
			int start = 1;
			int sum = 0;
			for (int j = 0, end = str.length(); j < end; j++) {
				if(str.charAt(j)=='O') {
					sum += start++;
				} else {
					start =1;
				}
			}
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	}

}
