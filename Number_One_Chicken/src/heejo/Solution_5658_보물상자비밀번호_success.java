package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 모의 SW 역량 테스트
 * 5658. 보물상자 비밀번호
 * 
 * 풀이방법
 * 1. 숫자를 구역별로 끊어서 만든다. (4개의 숫자가 만들어짐)
 * 2. 숫자가 기존에 만들어진 것(배열)과 중복되는지 확인하고 중복 안된다면 배열에 추가한다.
 * 3. 시계방향 회전을 다 끝내면, 배열을 정렬한다.
 * 4. 정렬된 배열에서 K번째로 큰 수를 찾아 출력한다.
 * 
 * 
 * 아이디어
 * - 16진수의 숫자들을 한글자씩 큐에 넣기
 * - 만들어지는 숫자는 N/4개가 모여서 만들어진다. => 하나의 숫자를 만들기 위해 반복되는 횟수(for문)
 * - 어차피 N/4번 회전하면 그 이상은 전부 중복된다. => 시계방향으로 이동해보는 횟수 (for문) 
 * @author joy96
 *
 */
public class Solution_5658_보물상자비밀번호_success {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer str1 = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(str1.nextToken()); // 숫자 개수
			int K = Integer.parseInt(str1.nextToken()); // 몇번째로 큰 수일까
			int count = N / 4;
			int loc = 0;
			String str2 = in.readLine();
			int array[] = new int[N];
			Queue<Character> queue = new LinkedList<Character>();
			//16진수의 숫자들을 queue에 넣는다.
			for (int i = 0; i < N; i++) {
				queue.offer(str2.charAt(i));
			}
			
			// 글자를 시계방향으로 옮긴다.
			for (int i = 0; i < count; i++) {
				// 구역을 나눠, 숫자로 만든다.
				for (int j = 0; j < 4; j++) {
					int realNum = 0; // 구역의 진짜 숫자 합
					int numLoc = count - 1; // n의 자리
					for (int k = 0; k < count; k++) {
						// 숫자로 만든다.
						char numBefore = queue.poll();
						int numAfter;
						if ((numBefore >= 'A') && (numBefore <= 'F')) {
							numAfter = (numBefore - 'A') + 10;
						} else {
							numAfter = (numBefore - '0');
						}
						realNum += numAfter * Math.pow(16, numLoc--);
						queue.offer(numBefore);
					}
					// 숫자를 배열에 집어넣는다. (배열에 같은 숫자가 없을 때만)
					if (!isNumberInArray(realNum, array, N)) {
						array[loc++] = realNum;
					}
				}
				// 큐를 한칸 옮긴다.
				queue.offer(queue.poll());
			}

			// 배열을 정렬하여, k번째 숫자를 출력한다.
			Arrays.sort(array);		//배열은 오름차순이다.
			System.out.println("#" + test_case + " " + array[(N - K)]);	//N-K의 위치에 있는 값을 출력한다.
		}
	}

	//해당 숫자(num)가 배열 안에 존재하는지 확인하는 함수. 존재하면 true를 출력한다.
	static boolean isNumberInArray(int num, int arr[], int length) {
		for (int i = 0; i < length; i++) {
			if (num == arr[i]) {
				return true;
			}
		}
		return false;
	}
}
