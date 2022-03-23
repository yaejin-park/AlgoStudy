package yejin.Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 루트 노드는 1
 * 부모노드의 번호가 작은 것이 먼저 입력,
 * 부모노드가 같으면 자식 노드가 작은 것이 먼저 입력
 * 
 * 트리의 지름 ( 경로중 가장 긴 것)
 * 부모노드 한 개 공통으로 두고 리프토드 2개 선택(가장 가중치 큰것)
 * -> 부모노드 하나 선택 돌아가면서 -> dfs로 자식까지 가면서 더한값 비교
 * @author yaejin
 *
 */
public class Main_G4_1967_트리의지름2 {
	
	static class Edge{
		int child;
		int weight;
		
		public Edge(int child, int weight) {
			super();
			this.child = child;
			this.weight = weight;
		}

	}
	
	static int N;
	static List<List<Edge>> tree = new ArrayList<>();
	static List<Integer> parentList = new ArrayList<Integer>();
	static boolean[] parents;
	static int max = 0;
	static Edge childCombi[] = new Edge[2];
	static PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	//노드의 개수
		parents = new boolean[N+1];
		
		//1부터 N까지
		for (int i = 0; i < N+1; i++) {
			tree.add(new ArrayList<>());
		}
		
		//부모 노드, 자식노드, 가중치
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			tree.get(p).add(new Edge(c, w));	//트리 정보 넣기
			if(!parents[p]) {
				parentList.add(p);
				parents[p] = true;
			}
		}
		
		//부모노드 돌기
		for (int i = 0, end = parentList.size(); i < end; i++) {
			//시작할 부모노드
			int parentNode = parentList.get(i);
			
			//자식노드 수가 2개보다 적으면 하나만세기
			if(tree.get(parentNode).size() <2) {
				dfs(parentNode,0);
				max = Math.max(max, queue.poll());
				queue.clear();
			} else {
				//자식 조합 선정
				combi(0,0,parentNode);
			}
			
		}
		System.out.println(max);
	}

	private static void combi(int cnt,int start, int parentNode) {
		//자식 조합 두개 선정되면 dfs돌기
		if(cnt == 2) {
			int sum = 0;
			//해당 dfs 최대값 구해서 더하기
			for (int i = 0; i < 2; i++) {
//				System.out.println(parentNode+ "의 자식 : "+childCombi[i].child);
				dfs(childCombi[i].child,childCombi[i].weight);
				sum += queue.poll();
				queue.clear();
			}
			
			//해당 조합의 최댓값 max에 저장
			max = Math.max(max, sum);
			
			return;
		}
		
		for (int i = start,end = tree.get(parentNode).size(); i < end; i++) {
			childCombi[cnt] = new Edge(tree.get(parentNode).get(i).child, tree.get(parentNode).get(i).weight);
			combi(cnt+1, i+1,parentNode);
		}
	}

	private static void dfs(int parentNode, int sum) {
		//리프노드면
		if(!parents[parentNode]) {
			queue.add(sum);
//			System.out.println("queue에 "+sum);
			return;
		}
		
		//해당 부모노드의 자식 노드들 개수만큼 돌면서 들어가기
		for (int i = 0, end = tree.get(parentNode).size(); i < end; i++) {
			dfs(tree.get(parentNode).get(i).child, sum+tree.get(parentNode).get(i).weight);
		}
	}

}
