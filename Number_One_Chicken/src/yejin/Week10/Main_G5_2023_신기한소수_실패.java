package yejin.Week10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_G5_2023_신기한소수_실패 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int area = (int)Math.pow(10, N);	//1의 자리면 10까지 , 2자리면 100까지~~
		boolean []arr = new boolean[area];//true는 소수아님 false는 소수
		
		arr[1] = true;
		for (int i = 2; i < area; i++) {
			if(arr[i]) continue;
			for (int j = 2; j < (area)/i; j++) {
				//N자리
				arr[i*j] = true;
			}
		}
		
		//N자리수부터 N+1자리수 까지
		for (int i = area/10; i < area; i++) {
			if(!arr[i]) {
				boolean possible = true;
				for (int j = 1; j < N; j++) {
					if(arr[i/((int)Math.pow(10, j))]) {
						possible = false;
						break;
					}
				}
				if(possible) {
					sb.append(i).append(System.lineSeparator());
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
