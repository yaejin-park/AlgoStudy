package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 제목 : 사촌
 * 사이트 : 백준
 * 난이도 : 골드4
 * 번호 : 9489
 * 
 * 문제 목표 : 특정 노드 번호 k의 사촌의 수를 구해라.
 * 
 * 풀이 방법
 * 0. 초기 설정
 * 1. 노드 데이터를 입력받으며 큐에 입력 (직전 사촌 개수를 파악하여 그룹들을 같은 사촌으로 하기)
 * 2. 이전 노드 개수가 몇개였는지, 같은 사촌은 몇명이 있는지 체크하면서 트리를 만든다.
 * 3. 번호 k의 사촌의 수를 찾아 계산한다.
 */
public class Main_9489_사촌 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer str1 = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(str1.nextToken());
			int k = Integer.parseInt(str1.nextToken());
			if ((n == 0) && (k == 0)) {
				break;
			}
			Queue<int[]> queue = new LinkedList<int[]>();
			StringTokenizer str2 = new StringTokenizer(in.readLine());
			int current_vertical_stage = 0;
			int current_horizontal_stage = 0;
			int previous_horizontal_stage = 0;
			queue.offer(new int[] {Integer.parseInt(str2.nextToken()), current_vertical_stage, current_horizontal_stage});
			current_vertical_stage++;
			current_horizontal_stage++;
			previous_horizontal_stage = current_horizontal_stage;
			current_horizontal_stage = 0;
			int a = Integer.parseInt(str2.nextToken());
			while(true) {
				int b = Integer.parseInt(str2.nextToken());
				if(b-a==1) {
					queue.offer(new int[] {a, current_vertical_stage, current_horizontal_stage});
				}
			}
		}
	}
}
