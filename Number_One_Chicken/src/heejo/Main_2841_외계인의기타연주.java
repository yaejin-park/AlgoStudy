package heejo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 제목 : 외계인의 기타 연주
 * 사이트 : 백준
 * 난이도 : 실버1
 * 번호 : 2841
 * 
 * 문제 목표 : 손가락을 가장 적게 움직이게 하는 횟수를 구해라
 * 
 * 풀이 방법
 * 0. 초기 설정
 * 1. 6개의 스택 생성
 * 2. 계산하면서 손가락 카운트 세기 (스택에 숫자가 몇번 왔다갔다하는지)
 * 3. 결과 출력
 */
public class Main_2841_외계인의기타연주 {
	static int count;
	public static void main(String[] args) throws Exception {
		//0. 초기 설정
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(in.readLine());

		//0-1. N, P, count 변수 입력 
		int N = Integer.parseInt(str1.nextToken());	//입력받을 내용 개수
		int P = Integer.parseInt(str1.nextToken());	//프렛의 개수
		count = 0;	//손가락을 튕기는 횟수
		
		//1. 6개의 스택 생성. 각각 1~6번줄을 의미한다.
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		Stack<Integer> stack3 = new Stack<Integer>();
		Stack<Integer> stack4 = new Stack<Integer>();
		Stack<Integer> stack5 = new Stack<Integer>();
		Stack<Integer> stack6 = new Stack<Integer>();

		//2. 실행
		for (int i = 0; i < N; i++) {
			StringTokenizer str2 = new StringTokenizer(in.readLine());
			int temp1 = Integer.parseInt(str2.nextToken());	//줄
			int temp2 = Integer.parseInt(str2.nextToken());	//프렛
			//몇번째 줄인지에 따라 스택이 달라진다.
			switch (temp1) {
			case 1:
				fingertips(stack1, temp2);
				break;
			case 2:
				fingertips(stack2, temp2);
				break;
			case 3:
				fingertips(stack3, temp2);
				break;
			case 4:
				fingertips(stack4, temp2);
				break;
			case 5:
				fingertips(stack5, temp2);
				break;
			case 6:
				fingertips(stack6, temp2);
				break;
			}
		}
		
		//3. 결과 출력
		System.out.println(count);
	}
	
	//줄이 정해지면 해당 줄의 스택을 이용하여 실행하기
	public static void fingertips(Stack<Integer> stack, int temp) {
		//스택이 비어있지 않다면 추가 실행
		while (!stack.isEmpty()) {
			if(stack.peek() > temp) {
				//stack값이 더 크면 pop, 손가락 사용하니 count 1증가
				stack.pop();
				count++;
			}
			else if (stack.peek() == temp){
				//값이 같다면 손가락을 움직일 일이 없다. 바로 다음으로 넘어가기
				return;
			}
			else {
				//stack값이 더 작으면 반복 종료
				break;
			}
		}
		//temp값을 새로 추가. 손가락 사용하니 count 1증가
		stack.push(temp);
		count++;
	}
}
