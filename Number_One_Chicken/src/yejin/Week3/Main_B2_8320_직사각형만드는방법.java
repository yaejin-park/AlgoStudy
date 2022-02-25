package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B2_8320_직사각형만드는방법 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		br.close();
		
		int i = 1;
		int cnt = 0;
		
		while(true) {
			
			if(i>n || i*i>n) {
				break;
			}
			
			//나눌 수 있는 약수여야 직사각형 만들 수 있음
			for (int j = i, end = n/i; j <= end; j++) {
				cnt++;
			}
			i++;
		}
		System.out.println(cnt);
	}
}
