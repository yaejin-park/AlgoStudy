package yejin.A;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {
	static int W,H;
	static int map[][];
	static boolean visited[][];
	
	static int finalR, finalC;
	
	static int min;
	
	//상하좌우
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new int[H+1][W+1];	//1부터 시작하게
			for (int i = 1; i < H+1 ; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j =1 ; j < W+1; j++) {
					int cur= Integer.parseInt(st.nextToken());
					map[i][j] = cur;
					
					if(cur ==3) {	//도착 지점 저장
						finalR = i;
						finalC = j;
					}
				}
			}
			
			min = Integer.MAX_VALUE;
			visited = new boolean[H+1][W+1];
			//시작점
			visited[H][1] = true;	
			dfs(H,1, 0, 1, 0);
			
			sb.append("#"+t+" "+min+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int r, int c, int jump, int status, int max) {
		//도착지점에 도착하면,
		if(r == finalR && c == finalC) {
			//최소값 비교
			min = Math.min(max, min);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			//가로는 내가 1이어야 갈 수 있다.
			if(status == 0 && i>1) continue;
			
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			//경계검사
			if(nr<1 || nc<1 || nr>H || nc>W) continue;
			
			//방문체크
			if(visited[nr][nc]) continue;
			
			if(i<2) {	//세로
				if(map[nr][nc]==0) {	//가려는 칸이 0이면
					visited[nr][nc] = true;
					dfs(nr,nc,jump+1,0,max);
					visited[nr][nc] = false;
				} else {				//가려는 칸이 1이면
					if(status == 0) {	//0이었다가 1이되면
						if(max<jump) {	//현재 점프가 현재 가장큰 점프보다 크면
							visited[nr][nc] = true;
							dfs(nr,nc,0,1,jump);
							visited[nr][nc] = false;
						} 
					} else {
						visited[nr][nc] = true;
						dfs(nr,nc,0,1,max);
						visited[nr][nc] = false;
					}
				}
			} else {	//가로
				if(map[nr][nc]==0) continue;	//가로로는 1이어야 갈 수 있다.
				else {	//1인가로
					visited[nr][nc] = true;
					dfs(nr,nc,0,1,max);
					visited[nr][nc] = false;
				}
			}
		}
	}
}
