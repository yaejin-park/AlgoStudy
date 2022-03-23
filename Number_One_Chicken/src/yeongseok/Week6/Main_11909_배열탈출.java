package yeongseok.Week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 지도 cell 개수가 5백만개인데, dfs하면 overflow 날것 같음... 그래서 bfs로 한다.
 * 
 * 처음에 메모리 초과 떴음.
 * 이유 : 큐에 중복적으로 값이 들어가서
 * 결론 : 중복된 데이터가 들어가지 않기 위해서 백트레킹을 꼼꼼히 해줘야함
 * @author 노영석
 */
public class Main_11909_배열탈출 {
	static int n;
	static int dr[] = {1,0};
	static int dc[] = {0,1};
	static class Point{
		int r, c, cost;

		public Point(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}
	static int map[][];
	static int check[][];	//해당 위치까지 오는데 드는 최소비용
	static int min = Integer.MAX_VALUE;
	static Queue<Point> q;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		check = new int[n][n];
		
		//create map
		for(int i = 0 ; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q = new LinkedList<>();
		q.offer(new Point(0,0,1));
		while(!q.isEmpty()) {
			Point poll = q.poll();
			if(poll.r == n-1 && poll.c == n-1) {	//목적지에 도착하면
				min = Integer.min(min,poll.cost);
				continue;
			}
			//메모리초과 이유!!!!!!!! 중복된 데이터가 들어가는걸 방지해줘야함.
			if(check[poll.r][poll.c] != 0 && check[poll.r][poll.c] < poll.cost) continue;
			int nextr, nextc;
			if(poll.r == n-1) {	
				nextr = poll.r;
				nextc = poll.c+1;
				move(poll.r,poll.c, poll.cost, nextr,nextc);
			}else if(poll.c == n-1) {
				nextr = poll.r+1;
				nextc = poll.c;
				move(poll.r,poll.c, poll.cost, nextr,nextc);
			}else {
				for(int i =0; i<2; i++) {
					nextr = poll.r + dr[i];
					nextc = poll.c + dc[i];
					move(poll.r,poll.c, poll.cost, nextr,nextc);
				}
			}
		}
		
		System.out.println(min-1);
		
		
	}
	/**
	 * 이동 가능 여부를 판단하고 이동시킨다.
	 */
  	private static void move(int origr, int origc, int origCost, int nextr, int nextc) {
		if(!checkRange(nextr, nextc)) return;	//범위 체크
		int addCost=origCost;
		if(map[origr][origc] <= map[nextr][nextc]){	//움직일 곳이 현재 위치모다 값이 큰 경우 문제의 제약사항 충족 못함.
			addCost += (map[nextr][nextc] - map[origr][origc] + 1);
		}
		
		if(check[nextr][nextc] != 0) 	//예전에 방문한 적이 있으면
 			if(check[nextr][nextc]<= addCost) return;
 		
		q.offer(new Point(nextr, nextc, addCost));
		check[nextr][nextc] = addCost;
		return;
	}
	
	
	static boolean checkRange(int i, int j) {
		if(i>-1 && j > -1 && i < n && j < n) return true;
		return false;
	}

}









