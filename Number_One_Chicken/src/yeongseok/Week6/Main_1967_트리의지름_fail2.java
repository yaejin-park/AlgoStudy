package yeongseok.Week6;

import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
//1.2억 바이트
public class Main_1967_트리의지름_fail2 {
	static class Node{
		int parent;		//연결된 노드의 인덱스
		int val;	//노드를 연결하는 비용
		public Node(int to, int val) {
			this.parent = to;
			this.val = val;
		}
	}
	static int n;
	static Node[] tree;
	static int [] dis = new int[10001];
	static boolean [] visitied = new boolean[10001];
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		if( n == 1 ) {
			System.out.println(0);
			return;
		}
		tree = new Node[n+1];
		
		
		//tree 생성
		for(int i = 1; i< n; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken()); 
			int self = Integer.parseInt(st.nextToken()); 
			int val = Integer.parseInt(st.nextToken()); 
			
			
			tree[self] = new Node(parent,val);
		}
		
		
		
		for(int i = 1; i<n; i++) {
			for(int j = i+1; j<n+1; j++) {
				int calDistance = calDistance(i,j);
				max = Integer.max(calDistance, max);
			}
		}
		System.out.println(max);
	}
	private static int calDistance(int from, int to) {
		Queue<Integer> q  = new LinkedList<>();
		for(int i = 0 ; i < n+1; i++) {
			dis[i] = 0;
			visitied[i] = false;
			
		}
		q.offer(from); visitied[from] = true;
		q.offer(to); visitied[to] = true;
		while(!q.isEmpty()) {
			int child = q.poll();
			if(tree[child] == null) {	//root이면
				visitied[child] = true; continue;
			}
			int parent = tree[child].parent;
			if(!visitied[parent]) {
				q.offer(parent);
				dis[parent] = dis[child] + tree[child].val; 
				visitied[parent] = true;
			}else {
				return dis[parent] + dis[child] + tree[child].val;
			}
			
		}
		return 0;
	}

}
