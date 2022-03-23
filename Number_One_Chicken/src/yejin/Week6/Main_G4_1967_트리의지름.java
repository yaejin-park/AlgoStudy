package yejin.Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
public class Main_G4_1967_트리의지름 {
	
	static class Edge implements Comparable<Edge>{
		int parent;
		int child;
		int weight;
		
		public Edge(int parent, int child, int weight) {
			super();
			this.parent = parent;
			this.child = child;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	static int N;
	static int[] parents;
	static Edge[] edgeList;
	
	static void makeSet() {
		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	//노드의 개수
		
		edgeList = new Edge[N];
		//부모 노드, 자식노드, 가중치
		for (int i = 0; i < N-1; i++) {
			
		}
	}

}
