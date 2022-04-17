package heejo.week10_0420;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 아이디어
 * - 현재 자리수는 직전의 수보다 커야한다.
 * - 어차피 10자리가 넘어가면 감소하는 수는 없다. (ex) 9876543210
 * - 그렇다면 1의자리부터 10의자리까지 범위 내에서 감소하는 수를 찾자
 * 
 * - 각 자리마다 queue[]가 준비되어 있다.
 * - i번째 자리의 감소하는 수를 찾기 위해서는 i-1번째 자리의 queue를 탐색
 * - 탐색할 때, 가장 큰 자리의 수만 비교한다. ex) 321에서 '3'만 확인하기
 * - 현재 i번째 자리에 j라는 숫자를 추가하려 하는데, 방금 전의 '3'보다 j가 크면 이를 합친 j321을 i번째 큐에 넣는다. 아니라면 패스
 * - 큐에 새롭게 생성될 때마다 idx를 1씩 증가
 * - idx가 문제에서 요구한 N일 경우 종료하고 출력
 * - idx가 다 끝났는데도 N보다 작다면, -1을 출력 
 * 
 * 
 * 오답노트
 * - 1022에서 -1가 안나오고 987654321이 나온다.
 * - 계산과정에서 int 범위 초과하는 문제 발생
 * - 출력 타입을 int에서 long으로 수정
 */

public class Main_G5_1038_감소하는수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long result = calc(N);

		System.out.println(result);

	}

	public static long calc(int N) {
		Queue<Integer> queue[] = new Queue[10]; // 큐 10개 만들기
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			queue[i] = new LinkedList<>(); // 큐 초기화
		}
		// 0~9까지는 일의자리에서 감소하는 수가 되므로 queue[0]에 직접 넣기
		for (int i = 0; i < 10; i++) {
			if (idx == N) {
				return i;
			}
			queue[0].offer(i);
			idx++;
		}

		for (int i = 1; i < 10; i++) { // queue[i]에 새로 넣으려고 한다.
			for (int j = 0; j < 10; j++) { // 새로 넣는 맨 앞자리 숫자는 j
				int length = queue[i - 1].size(); // 비교하는 곳은 queue[i-1]
				for (int k = 0; k < length; k++) {
					int temp = queue[i - 1].poll(); // queue[i-1]에서 숫자 하나 뽑기
					if (j > temp / Math.pow(10, i - 1)) { // 그 숫자의 최고 자리의 수보다 j가 더 크다면
						if (idx == N) { // 혹시 idx가 일치하면
							return (j * (long) Math.pow(10, i) + temp); // 리턴하고 종료
						}
						queue[i].offer((int) (j * Math.pow(10, i) + temp)); // 숫자를 합성하여 queue[i]에 새롭게 추가
						idx++;
					}
					queue[i - 1].offer(temp); // 다시 원래 자리 큐에도 넣어준다.
				}
			}
		}

		return -1; // 그럼에도 idx가 N보다 작다면 -1리턴하고 종료
	}
}
