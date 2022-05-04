package yejin.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_G5_13023_ABCDE {
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
	static boolean visited[];
	static boolean isFive = false;

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
			visited = new boolean[N];
			visited[i] = true;
			isFive = false;
			dfs(i, 1);
			if(isFive) {	//존재하면
				answer = 1;
				break;
			}
		}
		System.out.println(answer);
	}

	private static void dfs(int cur, int depth) {
		if(isFive || depth == 5) {
			isFive = true;
			return;
		}
		
		for (int next : list.get(cur)) {
			if(visited[next]) continue;
			visited[next] = true;
			dfs(next, depth+1);
			visited[next] = false;
		}
		return;
	}
}
