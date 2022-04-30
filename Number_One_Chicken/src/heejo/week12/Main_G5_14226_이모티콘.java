package week12;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 아이디어
 * - 3가지 경우를 계산해야 한다.
 * - BFS로 queue를 이용하기
 * - 메모이제이션?을 하자. 2차원배열[화면][클립보드]. 완전히 같은 경우에는 큐에 넣지 않게끔
 */
public class Main_G5_14226_이모티콘 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Queue<int[]> queue = new LinkedList<>();
		boolean num[][] = new boolean[N + 1][N + 1];
		queue.offer(new int[] { 1, 0, 0 }); // 순서대로 화면, 클립보드, 시간
		num[1][0] = true;
		while (!queue.isEmpty()) {
			int temp[] = queue.poll();
			// 찾았다면
			if (temp[0] == N) {
				// 시간 출력하고 종료
				System.out.println(temp[2]);
				break;
			}
			// case1. 클립보드에 저장
			if(num[temp[0]][temp[0]]==false) {
				queue.offer(new int[] { temp[0], temp[0], temp[2] + 1 });
				num[temp[0]][temp[0]] = true;
			}
			// case2. 화면에 붙여넣기
			if ((temp[1] != 0)&&(temp[0]+temp[1]<=N)&&(num[temp[0]+temp[1]][temp[1]]==false)) {
				queue.offer(new int[] { temp[0] + temp[1], temp[1], temp[2] + 1 });
				num[temp[0]+temp[1]][temp[1]] = true;
			}
			// case3. 화면에 이모티콘 하나 없애기
			if ((temp[0] > 1)&&(num[temp[0]-1][temp[1]]==false)){
				queue.offer(new int[] { temp[0] - 1, temp[1], temp[2] + 1 });
				num[temp[0]-1][temp[1]] = true;
			}
		}
	}
}
