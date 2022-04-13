package yeongseok.Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 모든 노드들을 기준으로 BFS를 돌려서 최단거리 중 최대값을 자신의 점수로 갖는다.
 * 점수들중 최소값을 찾는다.
 * @author 노영석
 */
public class Main_2660_회장뽑기 {
	static int n;
	static List<Integer> map[];
	static int minScore = Integer.MAX_VALUE;
	static List<Integer> candidates;
	static class Node{
		int index;
		int dis;
		public Node(int index, int dis) {
			this.index = index;
			this.dis = dis;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		candidates = new ArrayList<>();
		map = new List[n+1];
		for(int i = 1; i <= n ; i++) {
			map[i] = new ArrayList<>();
		}
		
		//친구 관계 입력받기
		int a,b;
		while(true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(a == -1 && b == -1)break;
			map[a].add(b);
			map[b].add(a);
		}
		
		//모든 회원들의 점수를 구한다.
		for(int i =1 ; i<=n; i++) {
			int score = BFS(i);
			if(score == minScore) {
				candidates.add(i);
			}else if(score < minScore) {
				minScore = score;
				candidates.clear();
				candidates.add(i);
			}
		}
		//정렬
		candidates.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		
		System.out.println(minScore + " " + candidates.size());
		for(int i : candidates) {
			System.out.print(i + " ");
		}
	}
	/*
	 * i번째 회원의 다른 회원과의 최단 거리 중 최대값을 찾는다.
	 */
	private static int BFS(int i) {
		int maxDis=0;	//최단거리중 최대값
		Queue<Node> q= new LinkedList<>();
		boolean vis [] = new boolean[n+1];
		vis[i] = true;
		q.offer(new Node(i,0));
		while(!q.isEmpty()) {
			Node poll = q.poll();
			maxDis = Math.max(maxDis, poll.dis);
			
			//자신과 연결된 노드들 중 아직 방문하지 않은 노드를 방문한다.
			for(int a = 0; a<map[poll.index].size(); a++) {
				int index = map[poll.index].get(a);
				if(vis[index])continue;
				vis[index] = true;
				q.offer(new Node(index,poll.dis+1));
			}
		}
		return maxDis;
	}

}
