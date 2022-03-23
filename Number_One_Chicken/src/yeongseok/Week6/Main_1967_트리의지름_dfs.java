package yeongseok.Week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1967_트리의지름_dfs {
	static class Vertex{
		int to;		//연결된 노드의 인덱스
		int val;	//노드를 연결하는 비용
		public Vertex(int to, int val) {
			this.to = to;
			this.val = val;
		}
	}
	static int n;
	static List<Vertex>[] tree;
	static boolean[] hasChild;
	static int max = Integer.MIN_VALUE;
	static boolean[] visitied;
	static int start;
	static int root;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n+1];
		hasChild = new boolean[n+1];
		
		for(int i = 1; i<n+1; i++) {
			tree[i] = new ArrayList<>();
		}
		//tree 생성
		for(int i = 1; i< n; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); 
			int to = Integer.parseInt(st.nextToken()); 
			int val = Integer.parseInt(st.nextToken()); 
			
			hasChild[from]=true;
			
			tree[from].add(new Vertex(to,val));
			tree[to].add(new Vertex(from,val));
		}
		
		//root로 부터 최장 거리에 있는 노드를 구한다.
		root = 1;
		visitied = new boolean[n+1];
		visitied[1] = true;
		//루트 노드로 부터 가장 멀리있는 노드를 구한다.
		dfs(1,0);
		root = start;
		max = Integer.MIN_VALUE;
		visitied = new boolean[n+1];
		visitied[start] = true;
		dfs(start,0);
		System.out.println(max);
		
		
	}
	private static void dfs(int nodeIndex, int dis) {
		if(tree[nodeIndex].size() == 1 && nodeIndex != root) {	//leaf node 면
			if(max != Integer.max(max,dis)) {
				start = nodeIndex;
				max = dis;
			}
			return;
		}
		for(int i = 0; i < tree[nodeIndex].size(); i++) {
			Vertex vertex = tree[nodeIndex].get(i);
			 if(visitied[vertex.to]) continue;
			 visitied[vertex.to] = true;
			 dfs(vertex.to, dis+vertex.val);
			 visitied[vertex.to] = false;
		}
	}
	

}
