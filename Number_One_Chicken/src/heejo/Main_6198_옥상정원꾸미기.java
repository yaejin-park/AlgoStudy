package group_study;

import java.util.Scanner;
import java.util.Stack;

/*
 * 제목 : 옥상 정원 꾸미기
 * 사이트 : 백준
 * 난이도 : G5
 * 번호 : 6198
 * 
 * 문제 목표 : 각 관리인들이 벤치마킹할 수 있는 빌딩 수의 합을 출력
 * 
 * 풀이 방식
 * 0. 기본 설정
 * 1. 스택은 현재 빌딩을 볼 수 있는 빌딩들을 나타낸다.
 * 2. 현재 빌딩 높이보다 작은 값은 스택에서 지우고, 큰 값은 남겨두며 이 개수를 가지고 합한다.
 * 3. 결과 출력
 * 
 * 예시
 * 10  3  7  4  12 2에서
 * 1-1. 첫번째 빌딩을 볼 수 있는 건물은 없다. => 스택에 아무것도 없음
 * 1-2. 첫번째 빌딩 높이를 스택에 삽입
 * 2-1. 두번째 빌딩을 볼 수 있는 건물은 첫번째 빌딩이다. => 스택에 첫번째 빌딩
 * 2-2. 두번째 빌딩 높이를 스택에 삽입
 * 3-1. 세번째 빌딩을 볼 수 있는 건물은 첫번째 빌딩이다. => 스택에 두번째 빌딩을 지우고 첫번째 빌딩을 남긴다.
 * 3-2. 세번째 빌딩 높이를 스택에 삽입  
 * 
 * 오답노트
 * 총합 변수를 int가 아닌 long으로 바꾸기
 */

public class Main_6198_옥상정원꾸미기 {
	public static void main(String[] args) {
		//0. 기본 설정
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	//빌딩 개수
		int map[] = new int[N];	//빌딩 높이 나와있는 배열
		for(int i =0; i<N; i++) {
			map[i] = sc.nextInt();
		}
		long sum = 0;	//총합 배열
		Stack<Integer> stack = new Stack<Integer>();
		for(int i =0; i<N; i++) {
			//2. stack 상태 확인 및 계산
			System.out.println(map[i] + " : " + stack);
			while((!stack.isEmpty())&&(stack.peek()<=map[i])) {
				//2-1. stack이 비어있지 않고 stack 마지막 값이 현재 높이보다 작으면 빼버리기
				//System.out.println(stack.pop() + ", ");
				stack.pop();
			}
			//2-2. stack의 건물 개수 파악
			sum += stack.size();
			//2-3. stack에 현재 건물 삽입
			stack.push(map[i]);			
			System.out.println(map[i] + " : " + stack);

		}
		//3. 결과 출력
		System.out.println(sum);
	}
}
