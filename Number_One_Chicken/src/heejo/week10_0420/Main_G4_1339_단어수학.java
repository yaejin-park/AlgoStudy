package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 아이디어
 * - 합이 최대가 되려면 => 가장 큰 자리의 숫자부터 9,8,7..로 채워나가자
 * - 같은 자리에 여러 알파벳이 온다면, 우선순위를 정해보자
 * - 맨 위 자리부터 아래자리순으로 알파벳들을 비교해서 중복되는 알파벳을 선택
 * ex) ABC BDA
 * 	- 가장 큰 수 자리에 'A'와 'B'가 있다. 둘 중에 9는 뭐가 되는게 좋을까?
 *  - 'B'는 십의자리에 또 들어가있고 'A'는 일의자리에 들어가 있다.
 *  - 합이 최대가 되기 위해서는 B가 더 영향력이 있으니, B에 9를 넣자.
 *  
 *   
 * 아이디어2
 * - 간단하게 생각해서, 비트를 이용해서 해결해보기
 * - 알파벳마다 자리수에 1씩 증가
 * 	ex) CACB라는 숫자가 있다면
 * 	- 'B'는 알파벳 배열의 B칸에 1 증가
 *  - 'C'는 알파벳 배열의 C칸에 10 증가
 *  - 'A'는 알파벳 배열의 A칸에 100 증가
 *  - 'C'는 알파벳 배열의 C칸에 1000 증가
 * - 이런식으로 모든 숫자들을 스캔하면 알파벳 배열에 해당 알파벳이 몇번째 숫자칸에 몇번 나왔는지 확인이 가능 (C에는 1010이 들어가있다) 
 * - 알파벳 배열을 내림차순해서 맨 앞의 알파벳부터 9, 8, 7등의 숫자를 곱한 후 더하면 결과가 나온다.
 */
public class Main_G4_1339_단어수학 { 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int alpha[] = new int[26];
		//문자열 스캔
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<str.length(); j++) {
				char alphabet = str.charAt(str.length()-j-1);	//입력받은 문자열의 1의자리 알파벳부터 탐방
				alpha[alphabet-'A'] += Math.pow(10, j);	//해당 알파벳의 배열 위치에 숫자 더하기 (1의자리라면 1, 100의 자리라면 100)
			}
		}
		Arrays.sort(alpha);	//정렬(오름차순으로 함)
		
		int sum = 0;
		for(int i = 0; i<10; i++) {
			sum += alpha[25-i] * (9-i);	//맨 마지막 알파벳부터 10개를 9,8,7 차례로 곱해서 더하기
		}
		
		//결과 출력
		System.out.println(sum);
	}
	
}
