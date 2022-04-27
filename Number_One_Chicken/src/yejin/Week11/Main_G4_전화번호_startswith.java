package yejin.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main_G4_전화번호_startswith {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int n =Integer.parseInt(br.readLine());	//전화번호수(100000)
			
			String num[] = new String[n];
			for (int j = 0; j < n; j++) {
				num[j] = br.readLine();
			}
			Arrays.sort(num);

			boolean isNo = false;
			
			for (int j = 1; j < n; j++) {
				if(num[j].startsWith(num[j-1])) {
					isNo = true;
					break;
				}
			}
			
			if(isNo) {
				sb.append("NO");
			} else {
				sb.append("YES");
			}
			sb.append(System.lineSeparator());
			
		}
		System.out.println(sb);
	}

}