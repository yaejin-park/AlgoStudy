package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 아이디어
 * - 1번글을 기준으로 잡고, 2번글을 하나씩 옮겨보면서 맞는 글자가 있는지 확인
 * - 글자가 맞으면 1번글도 다음 글자로, 2번글도 그 다음글자부터만 옮겨보도록 체크
 * - 1번글의 범위도 한칸씩 줄여보면서 체크해보자
 * 
 */
public class Main_9251_LCS_fail {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int length1 = str1.length();
		int length2 = str2.length();

		int maxCnt = 0;
		for (int a = 0; a < length1; a++) {
			int cnt = 0;
			int loc = 0;
			for (int i = a; i < length1; i++) {
				for (int j = loc; j < length2; j++) {
					if (str1.charAt(i) == str2.charAt(j)) {
						loc = j+1;
//						System.out.print(a +", ");
//						System.out.print(i +", ");
//						System.out.println(j);
						cnt++;
						break;
					}
				}
			}
			maxCnt = Math.max(cnt, maxCnt);
		}
//		System.out.println(cnt);
		System.out.println(maxCnt);
	}
}
