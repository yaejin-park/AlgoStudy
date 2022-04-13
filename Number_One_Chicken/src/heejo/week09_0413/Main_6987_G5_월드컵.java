package heejo.week09_0413;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 오답노트
 * - 너무 어렵게 생각했었다.... (3^15라서 시간초과될줄ㅎ)
 * 
 * 아이디어
 * - 모든 대진표의 경우의 수를 따져본다.
 * - 이때, 완전히 엉뚱한 조사는 방지하기 위해 입력표보다 큰 결과를 받지는 못하게 한다.
 *   (예를 들어, A팀이 1승만 했는데 2승이 되는 경우의 수는 계산하지 않는다) => 이부분으로 인해 시간이 절약되는걸지도
 * - 모든 라운드가 끝났을 때, 입력표와 출력표가 완전히 일치하면 true, 아니라면 false
 */

public class Main_6987_G5_월드컵 {
	static int input[][];
	static int output[][];
	static boolean finish;
	static final int TOTAL_ROUND = 15;
	//라운드 별 배틀하는 팀 대진표. (팀 숫자가 유동적이라면 조합으로 만들기)
	static int battle[][] = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 2, 3 }, { 2, 4 }, { 2, 5 },
			{ 2, 6 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 4, 5 }, { 4, 6 }, { 5, 6 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 4; test_case++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			input = new int[7][3];
			output = new int[7][3];
			for (int i = 1; i <= 6; i++) {
				input[i][0] = Integer.parseInt(str.nextToken());
				input[i][1] = Integer.parseInt(str.nextToken());
				input[i][2] = Integer.parseInt(str.nextToken());
			}
			finish = false;
			
			//게임 실행
			game(0);
			
			//결과 출력
			if(finish) {
				System.out.print(1 + " ");
			}
			else {
				System.out.print(0 + " ");
			}
		}
	}

	public static void game(int round) {
		if(finish) {
			return;
		}
		//15라운드까지 다 끝났을 때
		if (round == TOTAL_ROUND) {
			//입력표와 직접 계산한표가 다르다면 false
			for(int i = 1; i<=6; i++) {
				for(int j =0; j<3; j++) {
					if(output[i][j]!=input[i][j]) {
						return;
					}
				}
			}
			//전부 일치하면 true
			finish = true;
			return;
		} else {
			for (int j = 0; j < 3; j++) {
				if ((output[battle[round][0]][j] < input[battle[round][0]][j])
						&&(output[battle[round][1]][2-j] < input[battle[round][1]][2-j])){
					output[battle[round][0]][j] += 1;
					output[battle[round][1]][2 - j] += 1;
					game(round + 1);
					output[battle[round][0]][j] -= 1;
					output[battle[round][1]][2 - j] -= 1;
				}
			}
		}
	}
}
