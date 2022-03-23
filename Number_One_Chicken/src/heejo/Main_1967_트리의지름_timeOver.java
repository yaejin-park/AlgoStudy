package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 아이디어
 * - 트리를 저장할 때 [10001][3]으로 이중배열 사용하자
 * - [0]은 부모노드, [1]은 길이(가중치), [2]은 높이(단계)
 * - 노드들을 두개 집는다 (조합)
 * - 해당 노드들을 기준으로 길이를 재보고 최대값에 반영
 */

public class Main_1967_트리의지름_timeOver {
	static int n;	//노드개수
	static int array[][];	//트리 저장 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		array = new int[n + 1][3];
		array[1][0] = 0;
		array[1][1] = 0;
		array[1][2] = 0;
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int parentNode = Integer.parseInt(str.nextToken());
			int childNode = Integer.parseInt(str.nextToken());
			array[childNode][0] = parentNode;	//부모노드
			array[childNode][1] = Integer.parseInt(str.nextToken());	//길이
			array[childNode][2] = array[parentNode][2] + 1;	//높이
		}

		selected = new int[2];
		max = 0;
		comb(0,1);
		System.out.println(max);
	}

	public static int calc(int nodeA, int nodeB) {
		int lengthSum = 0;
		// 높이가 같은지 체크
		if (array[nodeA][2] != array[nodeB][2]) {
			// 높이가 다르다면 높이를 맞추면서 같아질 때까지 lengthSum 더하기
			while (array[nodeA][2] > array[nodeB][2]) {
				lengthSum += array[nodeA][1];
				nodeA = array[nodeA][0];
			}
			while (array[nodeA][2] < array[nodeB][2]) {
				lengthSum += array[nodeB][1];
				nodeB = array[nodeB][0];
			}
		}
		// 높이가 같아졌다면 while문 시작
		while (true) {
			//두 노드가 혹시 같은 노드라면 while문 종료
			if(nodeA==nodeB) {
				break;
			}
			
			// 각자의 가중치를 sum에 더한다.
			lengthSum += array[nodeA][1];
			lengthSum += array[nodeB][1];
			
			// 부모 값이 같다면 while문 종료
			if (array[nodeA][0] == array[nodeB][0]) {
				break;
			}
			
			// 부모 값이 다르다면 가중치 더하고 a와 b 값을 부모 노드로 이동
			else {
				nodeA = array[nodeA][0];
				nodeB = array[nodeB][0];
			}
		}
		// 끝나면 lengthSum 리턴
		return lengthSum;
	}

	static int selected[];
	static int max;

	public static void comb(int cnt, int num) {
		if (cnt == 2) {
			// 지름 계산
			int res = calc(selected[0], selected[1]);
			// 최대값 갱신
			max = Math.max(res, max);
			return;
		} else {
			for (int i = num; i <= n; i++) {
				selected[cnt] = i;
				comb(cnt + 1, i + 1);
			}
		}
	}
}
