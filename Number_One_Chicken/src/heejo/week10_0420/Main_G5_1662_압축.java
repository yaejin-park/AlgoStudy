package group_study;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/*
 * 아이디어
 * - 괄호를 잘 묶는게 중요해보인다.
 * - (과 )를 구별하는 것이 중요하니까 stack을 사용해보자.
 * 
 * [실패]풀이과정(스택)
 * - '('를 만나면 괄호의 수를 센다(몇번 압축해제가 필요한지)
 * - ')'를 만나면 stack의 내용들을 pop하면서 '('를 찾는다.
 * - pop한 개수와 '(' 앞의 숫자를 곱한다.
 * - 압축해제가 끝났는데도 스택에 숫자들이 남아있다면, 이 개수만큼 문자열 개수를 증가시킨다.
 * 
 * [실패]주의사항
 * - 1. 압축해제는 현재 압축해제가 괄호 안에 있는지/없는지 구별해야 한다. (start)
 * 		- 괄호 안에 있다면, 지금 우리는 문자열 개수를 세고있고 stack 안에 숫자들은 그냥 char 자체이므로 계산에 헷갈리지 말것
 * 		- 괄호 안에 없다면, 
 * - 2. 현재 덩어리 내 압축된 단계가 몇단계인지 체크한다. (gualHo)
 * 
 * 오답노트
 * - [반례] 3(11)2(11)와 같은 상황에서는 해결 불가
 * 
 * 해결방안
 * - 좀 더 단순한 접근을 해야겠다 생각
 * - 현재까지 압축해제된 문자열 개수를 또 다른 stack에 보관하자
 * - gualHo를 이용해 현재 압축 해제가 몇 단계인지 파악이 가능하므로
 * - 현재 단계에서 이 문자열 개수가 사용되는지를 파악한 후 사용된다면 pop을 하여 Q에 추가하기 
 */
public class Main_G5_1662_압축 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		char[] trans = new char[str.length()];
		int len = str.length();
		for (int i = 0; i < len; i++) {
			trans[i] = str.charAt(i);
		}
		Stack<Character> stackChar = new Stack<>();
		Stack<Integer> stackTempSum = new Stack<>();
		int gualHo = 0; // 괄호의 개수를 파악하며 압축해제가 몇번 필요한지 체크

		// 문자열 체크 시작. 한 글자 한 글자 순서대로 체크하기
		for (int i = 0; i < len; i++) {
			char now = trans[i];
			switch (now) {
			case ')': // 닫는 괄호가 나온다면, 압축 해제를 하자
				int Qcount = 0;
				while (!stackTempSum.isEmpty()) {
					if (stackTempSum.peek() == gualHo) {	//현재 단계에 사용되는 문자열 개수인지 확인
						//맞다면 Q에 추가
						stackTempSum.pop();
						Qcount += stackTempSum.pop();
					}
					else {
						break;	//아니라면 break
					}
				}
				while (stackChar.peek() != '(') {
					stackChar.pop();
					Qcount++;
				}
				stackChar.pop(); // ( 없애기
				int K = stackChar.pop() - '0'; // K

				stackTempSum.push(K * Qcount); // K*Q
				stackTempSum.push(--gualHo); // 압축단계 1감소
				break;
			case '(':
				gualHo++; // 여는 괄호를 이용해 현재 덩어리 내 압축이 몇단계인지 1씩 증가
			default:
				stackChar.push(now); // 압축 해제가 아직 시작안됐다면 스택에 삽입
			}
		}

		int sumCount = 0; // 총 문자열 개수
		
		//계산이 모두 끝난 이후 최종 문자열 개수를 더한다
		while(!stackTempSum.isEmpty()) {
			stackTempSum.pop();
			sumCount += stackTempSum.pop();
		}
		// 모든 압축해제가 끝났음에도 아직 char스택이 비어있지 않다면, 해당 개수만큼 더하기
		if (!stackChar.isEmpty()) {
			sumCount += stackChar.size();
		}
		
		//결과 출력
		System.out.println(sumCount);
	}
}
