package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 아이디어1. Deque
 * - 양 방향에서 뺄 수 있다는 장점 사용
 * - 각 톱니바퀴별 큐를 생성해두고 비교하기
 * - 톱니바퀴의 3시방향, 9시방향 값을 확인하는 함수가 필요할 듯
 */
public class Main_14891_톱니바퀴 {
	public static void main(String[] args) throws Exception {
		Deque<Integer> wheel1 = new ArrayDeque<>();
		Deque<Integer> wheel2 = new ArrayDeque<>();
		Deque<Integer> wheel3 = new ArrayDeque<>();

		ArrayList<Deque<Integer>> wheels = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//톱니바퀴 1~4를 입력
		for (int i = 0; i < 4; i++) {
			String str1 = br.readLine();
			Deque<Integer> wheel = new ArrayDeque<>();
			for (int j = 0; j < 8; j++) {
				wheel.addLast(str1.charAt(j) - '0');
			}
			wheels.add(wheel);
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer str2 = new StringTokenizer(br.readLine());
			int WheelNum = Integer.parseInt(str2.nextToken());
			int direction = Integer.parseInt(str2.nextToken());
			int wheelDirection[] = new int[4];	//톱니바퀴들을 어느 방향으로 회전할지 적어두는 배열
			wheelDirection[WheelNum-1] = direction;
			// 톱니바퀴 기준 오른쪽들 확인
			for (int j = WheelNum; j < 4; j++) {
				direction = wheelDirection[j-1];
				int currentWheel = clock3OfWheel(wheels.get(j-1));	//현 톱니바퀴의 3시방향 
				int nextWheel = clock9OfWheel(wheels.get(j)); 	//오른쪽 톱니바퀴의 9시 방향
				//두 값이 같다면 즉시 종료
				if(currentWheel==nextWheel) {
					break;
				}
				else {
					wheelDirection[j] = direction * (-1);
				}
			}
			// 톱니바퀴 기준 왼쪽들 확인
			for (int j = WheelNum; j > 1; j--) {
				direction = wheelDirection[j-1];
				int currentWheel = clock9OfWheel(wheels.get(j-1));	//현 톱니바퀴의 9시방향 
				int nextWheel = clock3OfWheel(wheels.get(j-2)); 	//왼쪽 톱니바퀴의 3시 방향
				//두 값이 같다면 즉시 종료
				if(currentWheel==nextWheel) {
					break;
				}
				else {
					wheelDirection[j-2] = direction * (-1);
				}
			}
			//배열에 저장된 값을 바탕으로 각 톱니바퀴들 회전시키기
			for(int j=0;j<4;j++) {
				//시계방향으로 돌아야한다면
				if(wheelDirection[j]==1) {
					//마지막(11시)부분을 빼서 맨앞(12시)로 옮긴다.
					wheels.get(j).addFirst(wheels.get(j).pollLast());
				}
				//반시계방향으로 돌아야한다면
				else if(wheelDirection[j]==-1) {
					//맨앞(12시)부분을 빼서 마지막(11시)로 옮긴다.
					wheels.get(j).addLast(wheels.get(j).pollFirst());
				}
				//방향 없으면 패스
			}
		}
		
		//모든 실행이 끝나면 각 톱니바퀴의 12시 방향이 N극인지 S극인지 확인하기
		int sum = 0;
		for(int i =0; i<4; i++) {
			//S극이라면 2의 각 톱니바퀴 번호-1을 제곱한 만큼 더하기
			if(wheels.get(i).getFirst()==1) {
				sum += Math.pow(2, i);
			}
		}
		System.out.println(sum);
	}

	public static int clock9OfWheel(Deque<Integer> wheel) {
		// 맨 뒤에꺼(11시) 하나 빼기
		int eleven = wheel.pollLast();
		// 그 다음 맨 뒤에꺼(9시) 하나 빼기
		int nine = wheel.pollLast();
		// 원상복구
		wheel.addLast(nine);
		wheel.addLast(eleven);
		// 출력
		return nine;
	}

	public static int clock3OfWheel(Deque<Integer> wheel) {
		// 맨 앞에꺼(12시) 하나 빼기
		int twelve = wheel.pollFirst();
		// 그 다음 맨 앞에꺼(2시) 하나 빼기
		int two = wheel.pollFirst();
		// 그 다음 맨 앞에꺼(3시) 하나 빼기
		int three = wheel.pollFirst();
		// 원상복구
		wheel.addFirst(three);
		wheel.addFirst(two);
		wheel.addFirst(twelve);
		// 출력
		return three;
	}
}
