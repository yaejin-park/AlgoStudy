package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 제목 : 트럭
 * 사이트 : 백준
 * 난이도 : 실버1
 * 번호 : 13335
 * 
 * 문제 목표 : 모든 트럭이 다리를 건너는 최단시간을 구하라
 * 
 * 풀이 방법
 * 0. 기본 설정
 * 1. 다리 큐에 다리 길이만큼 0을 삽입한다.
 * 2. 다리의 맨 앞 트럭을 뺀다. (동시에 트럭 총 무게에도 뺀다.)
 * 3. 현재 다리의 트럭들 무게와 큐에서 대기중인 트럭의 무게가 L을 넘는지 비교한다.
 * 4. L보다 적다면 다리에 추가한다. L보다 크다면 0을 다리에 추가한다.
 * 5. 2~4번을 반복
 * 6. 결과 출력 
 */

public class Main_13335_트럭 {
	public static void main(String[] args) throws Exception{
		//0. 초기 설정
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> trucks = new LinkedList<Integer>();	//대기중인 트럭을 나타내는 큐
		Queue<Integer> bridge = new LinkedList<Integer>();	//다리위 트럭을 나타내는 큐
		
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(str1.nextToken());	//트럭 수
		int w = Integer.parseInt(str1.nextToken());	//다리 길이
		int L = Integer.parseInt(str1.nextToken());	//다리 위 가능 무게
		int truck_weight = 0;	//다리 위 트럭 무게
		
		StringTokenizer str2 = new StringTokenizer(in.readLine());
		for(int i =0; i<n; i++) {
			//큐에 트럭들을 입력한다.
			trucks.offer(Integer.parseInt(str2.nextToken()));
		}
		//1. 다리(큐)에 0을 다리길이만큼 올려둔다.
		int time = 0;
		for(int i=0; i<w; i++) {
			bridge.offer(0);			
		}
		
		//2~5. 실행
		while(true) {
			//마지막 트럭이 다리위에 놓였으니, 이 트럭이 도착하는 시간은 w만큼이다.
			if(trucks.isEmpty()) {
				time+=w;
				break;
			}
			
			//2. 다리 위 트럭 무게 합에 맨 앞 트럭을 뺀다.
			truck_weight -= bridge.poll();
			
			//3. 다리위트럭무게합과 다음 대기중인 트럭의 합을 L과 비교한다.
			if(truck_weight+trucks.peek()<=L) {
				//4-1. 크지 않다면, 다리 위 트럭 무게를 추가하고 다리에 트럭을 놓는다.
				truck_weight += trucks.peek();
				bridge.offer(trucks.poll());
			}
			else {
				//4-2. 크다면, 다리 위에는 0을 올린다.
				bridge.offer(0);
			}
			//1단위시간이 지났다.
			time++;
		}
		
		//6. 결과 출력
		System.out.println(time);
	}
}
