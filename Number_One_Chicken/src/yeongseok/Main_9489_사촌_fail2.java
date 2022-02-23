package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main_9489_사촌_fail2 {
	
	
	static int N; // 노드 수
	static int K; // 검사 대상 노드
	static Map<Integer,Integer> nodeMap = new HashMap<>();	//<nodeValue,parent>
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split ;
		while(true) {
			split = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			K = Integer.parseInt(split[1]);
			
			if(N == 0 && K == 0) break;
			
 			split = br.readLine().split(" ");
 			
 			
 			pq.clear();
 			nodeMap.clear();
 			cnt=0;
 			
			//root node
 			int pNode = Integer.parseInt(split[0]);
			pq.offer(pNode);
			nodeMap.put(pNode, 0);
			int index=1; //수열의 인덱스
			
			//tree 생성하기
			while(index < N) {
				pNode = pq.poll();
				//연속된 수 중에서 첫번째 자식 추가
				int frontChildNodeNum = Integer.parseInt(split[index++]);
				nodeMap.put(frontChildNodeNum,pNode);
				pq.offer(frontChildNodeNum);
				//숫자가 연속되는 동안 parent 노드에 노드를 단다
				int nextNode;
				while(index<N) {
					nextNode = Integer.parseInt(split[index]);
					if(nextNode == frontChildNodeNum +1) {	//앞 노드의 수와 차이가 1이면(연속된 수이면)
						frontChildNodeNum = nextNode;	
						index++;
						pq.offer(nextNode);
						nodeMap.put(nextNode,pNode);
					}else {
						break;
					}
				}
			}
			
			Integer parent = nodeMap.get(K);
			if(parent == null) {
				System.out.println("0"); continue;
			}
			Integer parentParent = nodeMap.get(parent);
			if(parentParent == null) {
				System.out.println("0"); continue;
			}
			for(int node : nodeMap.values()) {
				if(node != parent && nodeMap.get(node) == parentParent) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
		
		
	}

}
