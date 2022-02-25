package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B2_3052_나머지 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] left = new boolean[42];	//0~41의 나머지 
		int answer = 0;
		
		for (int i = 0; i < 10; i++) {
			int result = Integer.parseInt(br.readLine())%42;
			if(!left[result]) {
				answer++;
				left[result] = true;
			}
		}
		System.out.println(answer);
	}

}
