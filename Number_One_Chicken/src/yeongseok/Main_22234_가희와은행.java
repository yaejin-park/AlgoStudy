package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_22234_가희와은행 {
	static int N;	//open 전 대기손님
	static int M;	//open 후 입장손님
	static int T;	//고객당 대응 시간
	static int W;	//총 응대 시간
	static Queue<Cust> queue = new LinkedList<>();		//대기 큐 
	static PriorityQueue<Cust> afterOpenQueue = new PriorityQueue<>(new Comparator<Cust>() {	//입력이(cx) 시간순으로 주어지지 않아서 정렬하고자 pq를 씀

		@Override
		public int compare(Cust o1, Cust o2) {
			return o1.inTime - o2.inTime;
		}
	});
	static class Cust{
		int id;	
		int remainTime;	//응대 받아야 할 시간
		int inTime;	//은행에 들어온 시간
		public Cust(int id, int rt,int it) {
			this.id = id;
			remainTime = rt;
			inTime = it;
		}
		public Cust() {}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(System.in));
		String[] split;
		// 입력 - 시작
		split = br.readLine().split(" ");
		
		N = Integer.parseInt(split[0]);
		T = Integer.parseInt(split[1]);
		W = Integer.parseInt(split[2]);
		int p, t, c;
		for(int i = 0; i<N; i++) {
			split = br.readLine().split(" ");
			p = Integer.parseInt(split[0]);
			t = Integer.parseInt(split[1]);
			queue.offer(new Cust(p,t,0));
		}
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i<M; i++) {
			split = br.readLine().split(" ");
			p = Integer.parseInt(split[0]);
			t = Integer.parseInt(split[1]);
			c = Integer.parseInt(split[2]);
			afterOpenQueue.offer(new Cust(p,t,c));
		}
		// 입력 - 끝
		

		int time = 1; 		//은행을 영업한 시간
		int service=0;	//특정 고객의 응대 지속 시간 (0 == 응대고객 없음, other == 고객 응대 지속 시간)
		Cust customer;
		
		//고객 응대 loop
		while(time < W+1) {		//시간이 1초식 증가함에 따라서 반복
			customer = queue.peek();
			
			System.out.println(customer.id);
			
			//새로 들어온 손님이 있으면 큐에 추가
			if(!afterOpenQueue.isEmpty() && afterOpenQueue.peek().inTime == time) {
				queue.offer(afterOpenQueue.poll());
			}
			
			//응대
			customer.remainTime-=1;
			service++;
			
			//고객의 업무가 다 끝났으면
			if(customer.remainTime == 0) {
				queue.poll(); 
				service = 0;
			} 
			//고객 응대시간 종료되면
			else if(service == T) {
				queue.poll();
				queue.offer(customer);	//아직 완전히 응대받지 못했음으로 대기열 맨 뒤에 추가
				service = 0;
			}
			
			
			time++;
		}
		
	}

}
