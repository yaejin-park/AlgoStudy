package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_7576_토마토 {
	static int R,C;
	static int MAP[][];
	static final int RIPED = 1;
	static final int UNRIPED = 0;
	static final int EMPTY = -1;
	
	//상하좌우
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	private static int minTime=0;	//모든 토마토가 익는데 걸리는 최소 시간
	
	//토마토 정보를 담은 클래스
	static class Tomato{
		int row;
		int col;
		int ripedTime;	//토마토가 익는데 걸리는 시간
		public Tomato(int row, int col, int ripedTime) {
			super();
			this.row = row;
			this.col = col;
			this.ripedTime = ripedTime;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] split;
		split = br.readLine().split(" ");
		
		R = Integer.parseInt(split[1]);
		C = Integer.parseInt(split[0]);
		
		MAP = new int[R][C];
		
		Queue<Tomato> queue = new LinkedList<>();
		
		//토마토 map 생성
		for(int i = 0 ; i <R; i++) {
			split = br.readLine().split(" ");
			for(int j = 0 ; j<C; j++) {
				MAP[i][j] = Integer.parseInt(split[j]);
				if(MAP[i][j] == RIPED) {
					queue.offer(new Tomato(i,j,0));
				}
			}
		}
		
		//익어있는 토마토가 없어서 토마토가 모두 익지 못하는 상태이면
		if(queue.isEmpty()) {
			System.out.println(-1);
			return;
		}
		//처음부터 모든 토마토가 익어있는 상태이면
		else if(queue.size() == R*C) {
			System.out.println(0);
			return;
		}
		
		//queue에 더이상 토마토가 없을때까지 반복한다.
		while(!queue.isEmpty()) {
			Tomato poll = queue.poll();
			
			//최소시간보다 크면 최소시간 업데이트
			if(poll.ripedTime > minTime) {
				minTime = poll.ripedTime ; 
			}
			int rr,cc;
			for(int i = 0 ; i<4; i++) {
				rr = (poll.row +dr[i]);
				cc = (poll.col +dc[i]);
				
				if(rr>R-1 || cc > C-1 || rr < 0 || cc < 0) continue;	//범위 밖 영역이면
				
				if(MAP[rr][cc] != UNRIPED) continue;	// (rr,cc)에 이미 익은 토마토가 있거나 비었으면 
				
				MAP[rr][cc] = RIPED;
				queue.offer(new Tomato(rr,cc,poll.ripedTime + 1));
			}
			
		}
		
		
		//모든 토마토가 익었는지 확인
		for(int i = 0 ; i <R; i++) {
			for(int j = 0 ; j<C; j++) {
				if(MAP[i][j] == UNRIPED) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		//최소시간 출력
		System.out.println(minTime);
	}
}
