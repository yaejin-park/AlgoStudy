package yejin.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_13023_ABCDE2 {
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	
	static class Node{
		int to;
		int cnt;
		public Node(int to, int cnt) {
			super();
			this.to = to;
			this.cnt = cnt;
		}
	}
	
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			list.get(A).add(B);
			list.get(B).add(A);
		}
		
		int answer = 0;
		
		//시작점
		for (int i = 0; i < N; i++) {
			if(bfs(i)) {	//존재하면
				answer = 1;
				break;
			}
		}
		System.out.println(answer);
	}

	public static boolean bfs(int i) {
		boolean visited[][] = new boolean[N][N];
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(i, 1));
		
		for (int j = 0; j < N; j++) {
			visited[j][i] = true;
		}
//		System.out.println(i+"i!!!"+1);
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			if(cur.cnt == 5) {
				return true;
			}
			
			for (int to : list.get(cur.to)) {
				if(visited[cur.to][to]) continue;
				visited[cur.to][to] = true;
//				System.out.println(to+","+(cur.cnt+1));
				
				queue.add(new Node(to, cur.cnt+1));
			}
		}
		return false;
	}

}
