package yejin.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
/*
 * 1. 실패
 * 알파벳 조합 0~9 (총 10개)
 * 나온 알파벳 개수 세서 개수만큼 9~(9-알파벳개수)만큼 범위 잡고 알파벳 조합 짜기
 * 나온 조합으로 가장 큰 합 출력
 * ---------------------------------------------
 * 2. 성공
 * 알파벳 값을 저장하는 26칸의 배열 생성
 * 알파벳 자리수에 맞게 단어 자리가 1의 자리면 1, 10의 자리면 10, 100의 자리면 100 값을 더해주기
 * 
 * 그 알파벳 배열을 내림차순 한 후, 큰 수부터 차례대로 9~0 매칭
 * 
 */
public class Main_G4_1339_단어수학 {
//	static Alpha alpha[] = new Alpha[26];
	static Integer alpha[] = new Integer[26];
	static int N, alphaCnt;
	static int max = 0;
	static boolean[] su = new boolean[10];	//1~9 수 뽑혔는지 여부 체크
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//배열 만들기 A(0)~Z(25)까지 26개 공간
		for (int i = 0; i < 26; i++) {
			alpha[i] = new Integer(0);
		}
		
		N = Integer.parseInt(br.readLine());
	
		//단어 개수만큼 순회
		for (int i = 0; i < N; i++) {
			String str =br.readLine(); 
			//한 단어 길이만큼 순회
			for (int j = 0, end=str.length(); j < end; j++) {
				char cur = str.charAt(j);	//해당자리 알파벳
				//알파벳 개수 세기
				if(alpha[cur-'A'] == 0) {//아직 나온적없으면
					alphaCnt++;				//알파벳 개수 ++
				}
				//해당 알파벳 인덱스에 해당 자리수 더해주기 (ex. 10의 자리면 10, 1의자리면 1, 100의 자리면 100)
				alpha[cur-'A'] += (int)Math.pow(10, (end-1)-j);
			}
		}
		
		//내림차순
		Arrays.sort(alpha, Collections.reverseOrder());
		
		//합구하기
		for (int i = 0; i < alphaCnt; i++) {
			//9부터 큰 수 대로 (큰 값의 알파벳에 큰 수 매칭)
			for (int j = 9; j > -1; j--) {
				if(!su[j]) {			//매칭안된 수면
					max += alpha[i]*j;	//매칭하면서 바로 더해주기	
					su[j] = true;		//매칭된 수 방문체크
					break;
				}
			}
		}
		System.out.println(max);
	}


}
