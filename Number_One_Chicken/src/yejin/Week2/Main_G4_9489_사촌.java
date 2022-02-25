package yejin.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 	1.문제설명
 * 		첫 정수는 트리의 루트 노드
 * 		그 다음엔 루트의 자식 연속적으로
 * 		그 다음 연속수 : 아직 자식 없는 노드의 자식
 * 		수 연속성X -> 구분
 * 
 */
public class Main_G4_9489_사촌 {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//0입력까지 TC받기
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());	//노드의 수
			int k = Integer.parseInt(st.nextToken());	//사촌을 구해야하는 수(사촌 : 부모는 다르지만 부모가 서로 형제, 즉 같은 레벨인데 부모다른 경우)
			
			//0입력 받으면 반복문 빠져나가기
			if(n==0) {
				break;
			}
			
			Queue<Node> queue = new LinkedList<Node>();
			
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int root = Integer.parseInt(st1.nextToken());		//루트 노드값
			
			//찾는 수가 루트노드면 0출력
			if(k==root) {
				System.out.println(0);
				System.exit(0); //바로 종료
			}
			
			queue.offer(new Node(1, false));	//루트노드 큐에 넣기
			
			int next = Integer.parseInt(st1.nextToken());	//다음노드 수(현재 노드와 다음노드가 연속되지 않으면 큐에 저장하기 위해)
			int size = 0;									//연속된 수의 갯수
			boolean here = false; 							//찾는 값의 위치를 표시(Node class에서 find 속성으로 넣을 것)
			
			//n개의 노드 반복해서 받기 (두번째 노드부터 시작 1인덱스부터 시작)
			for (int i = 1; i < n; i++) {
				//현재 노드
				int cur = next;

				//다음 노드
				if(i < n-1) {
					next = Integer.parseInt(st1.nextToken());
				}
				
				//찾는 수를 찾으면
				if(cur == k) {
					//불리언 값을 true로 설정
					here = true;
				}
				
				//다음 노드랑 연속되지 않거나(현재노드 수+1 != 다음 노드수), 마지막 노드라면 (저장&초기화)
				if(cur+1 != next || i == n-1) {
					size++;
					//큐에 해당 노드 정보 담기
					queue.offer(new Node(size, here));
//					System.out.println(cur+"(큐담기)size : "+size+","+here);
					//값 초기화
					size = 0;		//연속된 수의 갯수 1로 초기화
					here = false;	//찾는값 존재 여부 초기화
				} else {
					//저번노드랑 연속되면
					//연속된 수 갯수+1
					size++;
//					System.out.println(cur+"(연속)size : "+size+","+here);
				}
			}
			
			//사촌수를 찾기 위해 카운트
			int cousin = 0;
			boolean finish = false;
			int re = 1;
			
			//큐에서 빼면서 찾아야하는 수의 사촌의 수 찾기
			loop:
			while(!queue.isEmpty()) {
				//한 노드의 반복수
				//사촌수 초기화
				cousin = 0;
				
				//다음 레벨 노드만큼 반복
				for (int i = 0; i < re; i++) {
					//현재 노드 가져오기
					Node cur = queue.poll();
					//찾아야하는 수를 찾으면 사촌수에는 안 더하고 피니시 불리언에 표시
					if(cur.find) {
//						System.out.println("찾음");
						finish = true;
					} else {
						cousin+= cur.cnt;
//						System.out.println("cousin : "+cousin);
					}
				}
				//한 레벨의 노드갯수 더한만큼 반복문 돌리기
				re = cousin;
//				System.out.println("Re : " + re);
				
				//사촌수 찾으면 반복문 빠져나오기
				if(finish) {
//					System.out.println("사촌수 찾아서 빠져나오기");
					break loop;
				}
			}
			System.out.println(cousin);
		}
	}
}

class Node{
	int cnt;
	boolean find;
	
	public Node(int cnt, boolean find) {
		this.cnt = cnt;
		this.find = find;
	}
}
