package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main_9489_사촌_fail {
	
	static class Node implements Comparable<Node>{
		int id;
		Node parent;
		List<Node> children;
		public Node(int id, Node parent) {
			this.id = id;
			this.parent = parent;
			this.children = new ArrayList<>();
		}
		//Tree(){}
		@Override
		public int compareTo(Node o) {
			return this.id-o.id;
		};
	}
	static int N; // 노드 수
	static int K; // 검사 대상 노드
	static List<Node> nodeList;
	static PriorityQueue<Node> pq;
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
 			
 			
 			pq = new PriorityQueue<>();
 			nodeList = new ArrayList<>();
 			cnt=0;
 			
			//root node
 			Node pNode = new Node(Integer.parseInt(split[0]),null);
			pq.offer(pNode);
			nodeList.add(pNode);
			int index=1; //수열의 인덱스
			
			//tree 생성하기
			while(index < N) {
				pNode = pq.poll();
				//연속된 수 중에서 첫번째 자식 추가
				int frontChildNodeNum = Integer.parseInt(split[index++]);
				Node fronChildNode = new Node(frontChildNodeNum, pNode);
				pNode.children.add(fronChildNode);
				nodeList.add(fronChildNode);
				pq.offer(fronChildNode);
				//숫자가 연속되는 동안 parent 노드에 노드를 단다
				int cNode;
				while(index<N) {
					cNode = Integer.parseInt(split[index]);
					if(cNode == frontChildNodeNum +1) {	//앞 노드의 수와 차이가 1이면(연속된 수이면)
						Node node = new Node(cNode, pNode);
						pNode.children.add(node);	//부모 노드에 추가
						frontChildNodeNum = cNode;	
						index++;
						pq.offer(node);
						nodeList.add(node);
					}else {
						break;
					}
				}
			}
			
			
			for(Node n : nodeList) {
				if(n == null || n.id != K) continue;
				if(n.parent == null) break;	// n == root노드인 경우
				Node parentParent = n.parent.parent;
				if(n.parent.parent == null) { // n == root의 자식 노드인 경우
					break;
				}
				for(Node parent : parentParent.children) {
					if(parent.id != n.parent.id) cnt+=parent.children.size();
				}
			}
			
			System.out.println(cnt);
		}
		
		
	}

}
