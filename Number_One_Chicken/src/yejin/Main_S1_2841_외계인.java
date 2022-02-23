package yejin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_S1_2841_외계인 {
	/**
	 *  1. 문제
	 *    1) 설명
	 *     1-6개 줄, 한 줄당 1-P 프렛
	 *     높은 프렛은 이전에 누른 프렛 떼지않고,
	 *     낮은 프렛은 이젠에 누른 프렛 떼고 연주
	 *     (한 번 누르거나 떼는 것이 한번 움직임)
	 *     
	 *    2) 입력
	 *     	N : 멜로디에 포함되어 있는 음의 수 (N<= 500,000)
	 *      P : 한 줄의 프렛의 수	(2<=P<=300,000)
	 *      
	 *    3) 출력
	 *    	가장 적게 움직이는 횟수 출력
	 *     
	 *  2. 아이디어
	 *  	처음에는 arraylist<arraylist<integer>>로 하려다가 애먹음...
	 *  	나중에서야 작은값 -> 큰값  stack으로 활용할 수 있다는 것을 알게됨
	 *  	근데 아직 arraylist 사용법, 선언법? 헷갈려..ㅠㅠ
	 *  
	 *  	1) 해당 줄 스택없으면 move +1 & 스택넣기
	 *  	2) 해당 줄 스택 있으면
	 *  		- 스택 마지막 값이 나보다 작으면 move +1 & 스택넣기
	 *  		- 스택 마지막 값이 나랑 같으면  move 0
	 *  		- 스택 마지막 값이 나보다 크면? 마지막값 <=나 될때까지 빼기(move+1) -> 나랑 같아지면 move 0, 나보다 작으면  move+1 +스택넣기
	 *  		즉, 스택 마지막 값이 나보다 작아질때까지 while 안에서 빼기
	 *  		그 후 같으면 0, 작으면  move+1
	 */
	
	static int N, P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());	//음의 수
		P = Integer.parseInt(st.nextToken());	//한 줄의 프렛의 수
		
		int move = 0;							//손가락 움직임 카운트
		
		//6개의 줄의 프랫을 담은 리스트 (1부터 시작하니 6+1)
		Stack<Integer>[] stack = new Stack[7];
		
		//이렇게 또 선언을 해야하는 것인가..
		for (int i = 0; i < 7; i++) {
			stack[i] = new Stack<>();
		}
		
		//연주 수
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			
			int line =Integer.parseInt(st1.nextToken());	//연주할 줄
			int plat =Integer.parseInt(st1.nextToken());	//연주할 프렛
			
			//해당 줄 스택이 비어있으면,
			if(stack[line].isEmpty()) {
				stack[line].add(plat);	//해당 줄에 프렛 스택 넣고
				move++;					//움직임 증가
			} else { //해당 줄 스택이 존재하면
				//스택 마지막 값이 나보다 크면 계속 빼기
				while(stack[line].peek() > plat) {
					stack[line].pop();	//스택에서 프렛보다 큰 값 빼기
					move++;				//움직임 증가
					if(stack[line].isEmpty()) {	//스택이 비면
						break;					//브레이크
					}
				}
				//비교할 마지막 값이 없어지거나 마지막 값이 나보다 작아지면(마지막값이 없어지거나 조건을 앞에 넣어야함!!)
				if(stack[line].isEmpty() || stack[line].peek() < plat ) {
					stack[line].add(plat);	//해당 줄에 프렛 스택 넣고
					move++;					//움직임 증가
				}
			}
		}
		System.out.println(move);	//결과 움직임 출력
	}
}
