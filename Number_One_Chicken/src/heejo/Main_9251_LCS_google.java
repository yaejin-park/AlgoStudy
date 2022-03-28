package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 오답노트
 * - 반례) VREGDFELK, VLSKD
 *  - 정답은 3인데 출력은2
 * 	- str2의 VD가 선택이 먼저 되면서, VLK를 찾지 못하는 문제 발생
 * - 모르겠어서 결국 구글링...
 * 
 */
public class Main_9251_LCS_google {
	static String str1;
	static String str2;
	static int array[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		int length1 = str1.length();
		int length2 = str2.length();
		array = new int[length1][length2];
		int maxCnt = 0;

		//세로 첫째줄 채우기 (str2의 첫번째 문자를 고정) 
		int found = 0; // 이미 해당 문자를 사용했다면 1로 변경
		for (int i = 0; i < length1; i++) {
			//이미 str2의 첫번째 문자와 일치하는 문자를 찾았었다면
			if (found == 1) {
				//이후 모든 배열들 전부 1로 처리
				array[i][0] = 1;
			}
			//아직 str2의 첫번째 문자와 일치하는 문자를 찾지 못했다면
			else {
				//일치하는 문자를 찾았다면
				if (str1.charAt(i) == str2.charAt(0)) {
					found = 1;	//found변수 1로 변경
					array[i][0] = 1;	//해당 배열부터 1로 처리
				}
			}
		}
		//가로 첫째줄 채우기 (str1의 첫번째 문자를 고정)
		found = 0;
		for (int j = 0; j < length2; j++) {
			//이미 str1의 첫번째 문자와 일치하는 문자를 찾았었다면
			if (found == 1) {
				//이후 모든 배열들 전부 1로 처리
				array[0][j] = 1;
			}
			//아직 str1의 첫번째 문자와 일치하는 문자를 찾지 못했다면
			else {
				//일치하는 문자를 찾았다면
				if (str2.charAt(j) == str1.charAt(0)) {
					found = 1;			//found변수 1로 변경
					array[0][j] = 1;	//해당 배열부터 1로 처리
				}
			}
		}
		//결과 출력
		System.out.println(LCS(length1-1,length2-1));
	}
	
	static int LCS(int x, int y) {
		//해당 위치가 첫번째 세로줄 or 첫번째 가로줄이라면
		if((x==0)||(y==0)) {
			//저장해둔 값 출력
			return array[x][y];
		}
		//str1과 str2의 끝 문자가 서로 같다면
		if(str1.charAt(x)==str2.charAt(y)) {
			//그 끝 문자들 이전까지의 결과값에 1을 더해서 입력
			array[x][y] = LCS(x-1,y-1)+1;
		}
		//끝 문자가 서로 다르다면
		else {
			//직전 세로 결과or가로 결과 중 큰 값 출력 
			array[x][y] = Math.max(LCS(x-1,y), LCS(x,y-1));
		}
		
		//입력된 해당 위치 값을 출력
		return array[x][y];
	}
}
