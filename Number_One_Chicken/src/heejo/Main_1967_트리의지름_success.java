package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 오답노트(시간초과)
 * - 다른 방법을 사용해볼 자신이 없다
 * - 그렇다면 조사를 진행할 노드의 개수를 줄여보자
 * - 노드는 엣지노드(자녀노드가 없는 노드들)만 가지고 탐색을 하자
 * 
 * 오답노트2(틀렸습니다)
 * - 엣지노드들 말고도 계산이 이뤄져야 하는 노드가 뭐가 있을까
 * - 반례) 노드별로 자녀노드가 하나씩만 존재하는 경우에는 단 1개의 엣지노드와 루트노드로 지름이 정해진다.
 * - 조사를 하는 노드를 엣지노드들+루트노드로 결정하자 (일명 조사노드)
 */
public class Main_1967_트리의지름_success {
	static int n;	//노드개수
	static int array[][];	//트리 저장 배열
	static boolean edge[];	//엣지노드인지 아닌지 판단하는 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		array = new int[n + 1][3];
		edge = new boolean[n+1];
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
			edge[parentNode] = true;	//해당 노드의 부모노드는 엣지노드가 아님!
		}
		
		cnt = 1;	//조사노드의 개수 파악하기. 루트노드가 포함되어야하기 때문에 기본으로 1
		for(int i = 1; i<=n; i++) {
			//해당 노드가 엣지노드라면 cnt개수 1 더한다
			if(!edge[i]) {
				cnt++;
			}
		}
		
		num = new int[cnt];	//조사노드들을 담아두는 배열
		cnt = 0;
		num[cnt++] = 1;	//루트노드(1) 담기
		for(int i = 1; i<=n; i++) {
			//해당 노드가 엣지노드라면 조사노드 배열에 담는다
			if(!edge[i]) {
				num[cnt++] = i;
			}
		}

		//조사노드에서 2개를 선택해서 계산하자
		selected = new int[2];
		max = 0;
		comb(0,0);
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
	static int num[];
	static int cnt;
	public static void comb(int count, int number) {
		if (count == 2) {
			// 지름 계산
			int res = calc(selected[0], selected[1]);
			// 최대값 갱신
			max = Math.max(res, max);
			return;
		} else {
			for (int i = number; i < cnt; i++) {
				selected[count] = num[i];
				comb(count + 1, i + 1);
			}
		}
	}
}
