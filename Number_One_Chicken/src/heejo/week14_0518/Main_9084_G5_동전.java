package week14_0518;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - 1원~M원까지..
 * - 동전 하나씩 선택해서 정해보기
 */
public class Main_9084_G5_동전 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int coin[] = new int[N];
			for (int i = 0; i < N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int DP[] = new int[M + 1];
			// DP실행
			for (int selectedMoney = 0; selectedMoney < N; selectedMoney++) {
				for (int sumMoney = 1; sumMoney <= M; sumMoney++) {
					//현재 금액 - 선택된 동전. 
					if(sumMoney-coin[selectedMoney]>0) {
						DP[sumMoney] += DP[sumMoney-coin[selectedMoney]];
					}
					//선택된 동전으로만 현재 금액을 만들 수 있는지 (아마 sumMoney-coin[selectedMoney]==0인 경우에만 해당)
					else if(sumMoney%coin[selectedMoney]==0) {
						DP[sumMoney] += 1;
					}
//					System.out.println("["+selectedMoney+"]["+sumMoney+"] : " + Arrays.toString(DP));
				}
			}
			System.out.println(DP[M]);
		}
	}
}
