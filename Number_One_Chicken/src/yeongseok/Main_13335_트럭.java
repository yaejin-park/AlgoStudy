package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Queue를 사용하는 문제
 * 2. 최초 queue에 다리 길이 만큼 0을 채운다.
 * 3. 모든 트럭이 다리 위에 올라갈때까지 반복한다
 * 	1)queue.poll
 * 	2)truck이 다리위에 올라갈 수 있으면 queue에 트럭을 추가한다.
 * 	3)그렇지 않으면 0을 추가한다.
 * 
 * @author 노영석
 */
public class Main_13335_트럭 {
	static int W,L,N;	//w == 길이, l == 하중, n == 기차 수
	static int time = 0;
	static int inQTruckCnt=0;
	static int inQTruckWeight=0;
	static int arr[];
	static Queue<Integer> queue;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//==========입력 - 시작==========
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		W = Integer.parseInt(split[1]);
		L = Integer.parseInt(split[2]);
		arr = new int[N];
		split = br.readLine().split(" ");
		for(int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(split[i]);
		}
		
		//==========입력 - 끝==========
		
		int arrIndex=0;
		queue = new LinkedList<>();
		
		//다리 길이만큼 0을 추가한다.
		for(int i = 0; i <W; i++) {
			queue.offer(0);
		}
		
		
		int poll=0;
		
		// 마지막 트럭이 다리위에 올라갈때까지 반복
		while(arrIndex != N) {		
			time++;
			poll = queue.poll();
			if(poll != 0) {	//트럭이 다리를 빠져나왔으면
				inQTruckWeight-=poll;
			}
			
			int truck = arr[arrIndex];
			if(isBridgeAvailable(truck)) {	//트럭이 다리위에 올라갈 수 있으면
				queue.offer(truck);
				inQTruckWeight+=truck;
				arrIndex++;
			}else {	//트럭이 다리위에 올라갈 수 없으면
				queue.offer(0);
			}
			
			
		}
		//마지막으로 올라간 트럭이 다리를 빠져나오는데 걸리는 시간을 추가함
		time += W;	
		
		System.out.println(time);
	}
	/**
	 * truck이 다리위에 올라갈 수 있는지 여부를 판단한다.
	 * @param truck
	 * @return
	 */
	private static boolean isBridgeAvailable(int truck) {
		if(inQTruckWeight+truck > L) return false;	// 다리가 버틸 수 있는 하중 초과
		return true;
	}
}
