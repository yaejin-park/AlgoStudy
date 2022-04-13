package heejo.week09_0413;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 오답노트
 * - nC2 생각해봤는데 바로 시간초과
 * 
 * 아이디어
 * - 10만개를 절대값 순서로 오름차순으로 배치를 쫙 한다.
 * - 이후 양 옆에있는 숫자들끼리 더해보면서 최소값을 비교하면 계산은 99999번이 되므로 시간적으로 엄청난 절약이 된다.
 * - 데이터가 오름차순으로 주어지기 때문에, 음수들을 stack에 따로 받고 이후 데이터들은 stack과 비교해가며 queue에 입력한다.
 * - stack을 쓰는 이유는 절대값으로 따져봤을때, 음수의 순서는 역순이 되기 때문
 */

public class Main_G5_2467_용액 {
	public static void main(String[] args) throws Exception{
		//10만개를 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer str = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<>();	//음수용
		Queue<Integer> queue = new LinkedList<>();	//최종 정렬을 위함
		int temp = 0;
		for(int i =0 ; i<N; i++) {
			temp = Integer.parseInt(str.nextToken());
			
			if(temp<0) {
				//음수 전용 stack에 보관
				stack.push(temp);
			}
			else {
				//스택이 빌 때까지 반복
				while(!stack.isEmpty()) {
					//스택의 peek의 절대값이 temp보다 작거나 같다면
					if(Math.abs(stack.peek())<=temp) {
						//스택의 peek를 queue에 집어넣는다.
						queue.offer(stack.pop());
					}
					//스택의 peek의 절대값이 temp보다 크면
					else {
						//queue에는 temp가 들어가야 하기 때문에 while문 강제 종료
						break;
					}
				}
				//스택에 남은 데이터가 더이상 없다면 바로 temp를 queue에 넣는다.
				queue.offer(temp);
			}
		}
		//혹시 입력들이 다 끝났는데 스택에 데이터가 남아있다면
		while(!stack.isEmpty()) {
			//전부 queue에 집어넣는다.
			queue.offer(stack.pop());
		}
		
		int minFirst = 0;	//최소 특성값을 만들어내는 용액1
		int minSecond = 0;	//최소 특성값을 만들어내는 용액2
		int min = Integer.MAX_VALUE;	//최소 특성값
		
		int tempFirst = queue.poll();	//특성값을 만들어보는 임시용액1
		int tempSecond = 0;				//특성값을 만들어보는 임시용액2
		int tempMin;					//임시 용액들로 만들어낸 특성값
		
		for(int i = 0; i<N-1; i++) {
			tempSecond = queue.peek();	//임시용액2를 큐에서 빼지는 말고 확인만 한다.
			tempMin = Math.abs(tempFirst+tempSecond);	//임시용액1과 임시용액2로 임시특성값 만들어본다.
			//만들어낸 임시 특성값이 더 작다면 갱신 
			if(tempMin<min) {
				min = tempMin;
				minFirst = tempFirst;
				minSecond = tempSecond;
			}
			//확인만 했던 임시용액2를 다음 테스트에 사용하는 임시용액1로 설정한다.
			tempFirst = queue.poll();
			
		}
		
		//두 용액을 오름차순으로 출력
		if(minFirst<minSecond) {
			System.out.println(minFirst + " " + minSecond);
		}
		else {
			System.out.println(minSecond + " " + minFirst);
		}
	}
}
