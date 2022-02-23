package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 문제 : 오큰수
 * 사이트 : 백준
 * 난이도 : 골드4
 * 번호 : 17298
 * 
 * 목표 : 자신보다 오른쪽에 있으면서 큰 수를 각각 출력하라.
 * 
 *  
 * 풀이 방법
 * 0. 초기 설정
 * 1. 스택을 이용해 값 비교 및 적용
 * 2. 출력
 * 
 * 오답노트
 * append를 사용하면 출력 부분에서 시간 단축이 된다.
 */
public class Main_17298_오큰수 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());	//개수
		int array[] = new int[N];
		StringTokenizer str = new StringTokenizer(in.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		//array 배열에 데이터 입력
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(str.nextToken());
		}
		
		//스택으로 비교 (스택에는 값이 아닌 index를 넣는다.)
		for (int i = 0; i < N; i++) {
			//스택이 비어있지 않다면
			while (!stack.isEmpty()) {
				//스택 끝의 실제 데이터값과 현재 번호의 실제 데이터 값 비교
				if (array[stack.peek()] < array[i]) {
					//현재 번호의 실제 데이터가 더 크다면 스택에서 빼면서 해당 데이터를 갱신
					array[stack.pop()] = array[i];
				} else {
					break;
				}
			}
			//스택에 index 집어넣고 다음으로 이동
			stack.push(i);
		}
		//계산이 끝나고, 아직 스택에 남은 인덱스가 있다면, 모두 -1로 처리
		while (!stack.isEmpty()) {
			array[stack.pop()] = -1;
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(array[i]).append(' ');
		}
		System.out.println(sb);
	}
}