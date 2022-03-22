package yejin.Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//구글링을 통해 LCS 개념 배움
public class Main_G5_9251_LCS {
	static String A, B;
	static int same[][];
	static int Alen, Blen;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		A = br.readLine();
		B = br.readLine();
		Alen = A.length();
		Blen = B.length();
		
		//비교 길이 저장 (첫번째줄에 0으로 채운 배열)
		same = new int[Alen+1][Blen+1];
		
		for (int i = 1; i < Alen+1; i++) {
			for (int j = 1; j < Blen+1; j++) {
				//첫줄에는 0칸으로채우고 char 0은 1인덱스에 넣으므로 i-1, j-1
				
				//서로 알파벳 같으면, 대각선 왼쪽 위 값 +1
				//서로 알파벳 다르면, 위, 왼쪽 값 비교해서 큰값 가져오기
				if(A.charAt(i-1) == B.charAt(j-1)) {
					same[i][j] = same[i-1][j-1]+1;
				} else {
					same[i][j] = Math.max(same[i-1][j], same[i][j-1]);
				}
			}
		}
		
		//출력
		System.out.println(same[Alen][Blen]);
	}
}
