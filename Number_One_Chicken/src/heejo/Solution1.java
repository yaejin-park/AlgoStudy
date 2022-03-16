import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 낚시터 자리잡기
 * 
 * 출입구에 따른 입장 방식은 총 6가지
 * 
 * @author joy96
 *
 */
class Solution1 {

	// 순열[6][3]
	static int permutation[][] = {{1, 2, 3}, {1, 3, 2}, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 }, { 3, 2, 1 }};
	static int minLength;
	static int myPlace[];
	static int exit_locate_people[][];
	static int N;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			exit_locate_people = new int[3][2];
			for (int i = 0; i < 3; i++) {
				StringTokenizer str = new StringTokenizer(br.readLine());
				exit_locate_people[i][0] = Integer.parseInt(str.nextToken()) - 1; // 출입구i의 위치
				exit_locate_people[i][1] = Integer.parseInt(str.nextToken()); // 출입구i의 사람들
			}
			minLength = Integer.MAX_VALUE;
			
			// 6가지 경우의 수를 전부 계산해보기
			for (int i = 0; i < 6; i++) {
				myPlace = new int[N]; // 낚시꾼들의 자리
				fill(0, permutation[i]);	//계산하기
			}
			
			System.out.println("#" + test_case + " " + minLength);	//결과값 출력
		}
	}

	public static void fill(int cnt, int turn[]) {
		//모든 낚시꾼들이 들어갔다면
		if (cnt == 3) {
			//거리를 계산하기
			int totalDistance = 0;
			for(int i =0; i<N; i++) {
				if(myPlace[i]>0) {
					//해당 위치의 낚시꾼과 그 낚시꾼이 들어온 게이트와 거리 계산하여 더하기
					totalDistance += Math.abs(exit_locate_people[myPlace[i]-1][0]-i)+1;
				}
			}
			//최소값 비교하기
			minLength = Math.min(minLength, totalDistance);
			return;
		}
		else {
			int gateWhere = exit_locate_people[turn[cnt] - 1][0];	//현재 게이트가 어디에 위치해 있는지
			int gatePeople = exit_locate_people[turn[cnt] - 1][1];	//현재 게이트에 사람이 몇명 기다리는지

			int restPeople = gatePeople;	//현재 게이트에 남은 사람이 몇명인지
			int i = 0;
			while (true) {
				if (i > N) {
					//[이 에러가 뜬다면] 틀린것이다.
					return;
				}
				//숫자들이 성공적으로 채워졌다면 다음 게이트로 넘어가자.
				if (restPeople == 0) {
					fill(cnt + 1, turn);
					return;
				}
				// 만약 개수가 1개 남았는데, 대칭으로 채워질 수 있는 상황이라면
				if ((restPeople == 1)) {
					if ((gateWhere + i < N) && (myPlace[gateWhere + i] == 0) && (gateWhere - i >= 0) && (myPlace[gateWhere - i] == 0)) {
						int tempPlace[] = new int[N];
						for (int j = 0; j < N; j++) {
							tempPlace[j] = myPlace[j];
						}
						//1. 오른쪽을 채워서 다음 게이트로 넘어가고
						myPlace[gateWhere + i] = turn[cnt];
						fill(cnt + 1, turn);
						
						//초기화한 다음에
						for (int j = 0; j < N; j++) {
							myPlace[j] = tempPlace[j];
						}
						
						//2. 왼쪽을 채워서 다음 게이트로 넘어간다.
						myPlace[gateWhere - i] = turn[cnt];
						fill(cnt + 1, turn);
						return;
					}
				}
				//오른쪽에 채울 수 있는 상황이라면 채운다
				if ((gateWhere + i < N) && (myPlace[gateWhere + i] == 0)) {
					myPlace[gateWhere + i] = turn[cnt];
					restPeople--;
				}
				
				//왼쪽에 채울 수 있는 상황이라면 채운다
				if ((gateWhere - i >= 0) && (myPlace[gateWhere - i] == 0)) {
					myPlace[gateWhere - i] = turn[cnt];
					restPeople--;
				}
				//i 증가
				i++;
			}
		}
	}
}