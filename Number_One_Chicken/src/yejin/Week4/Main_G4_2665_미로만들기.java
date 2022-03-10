package yejin.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


/*
 * 	흰방끼리 통과 가능, 검은방 통과 불가
 * 	시작점 0,0 에서 n-1,n-1 까지 통과해야할 때, 최소로 검정방을 흰방으로 바꿔서 통과할 수 있는 개수 출력(안바꿔도되면 0출력)
 * 
 *  아이디어
 * 	1. 검정방으로  부분집합으로 골라서 dfs로 끝까지 가면 break
 * 	구글링 (다익스트라 다까먹..)
 * 	1. bfs로 가서 검은 칸의 개수 최소 값 저장
 */
public class Main_G4_2665_미로만들기 {
	static int n; //방 크기 n*n
	static boolean map[][];	//방 지도(흰방은 true, 검은방은 false)
	static int black[][];
	
	//상하좌우 델타배열
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new boolean[n][n];
		black = new int[n][n];
		
		//맵 받기
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = (str.charAt(j)=='1');	//흰방이면 true로 저장
				black[i][j] = Integer.MAX_VALUE;	//최대값으로 초기화
			}
		}
		
		bfs(0,0);
		
		System.out.println(black[n-1][n-1]);
	}

	private static void bfs(int r, int c) {
		Queue<Room> queue = new LinkedList<Room>();
		
		queue.add(new Room(r, c));
		black[r][c] = 0;	//아직 흰방
		
		while(!queue.isEmpty()) {
			Room cur = queue.poll();
			int row = cur.r;
			int col = cur.c;
			
			for (int i = 0; i < 4; i++) {
				int cr = row + dr[i];
				int cc = col + dc[i];
				
				//경계검사 & !!최대값이 초기화되어있으니 인접 값보다 더 큰 값이면 넣기(이걸로 방문체크와 검정방개수 한번에 처리가능) ??의문?? 방문체크 따로하고, 검정방개수 따로 하면 왜 안되는걸까?
				if(cr>-1 && cr<n && cc>-1 && cc<n && black[cr][cc] > black[row][col]) {
					//거쳐간 검정방 개수 저장
					if(!map[cr][cc]) {	//검정방이면
						black[cr][cc] = black[row][col]+1;	//1증가
					} else {			//흰 방이면
						black[cr][cc] = black[row][col];	//그대로
					}
					queue.add(new Room(cr, cc));
				}
			}
		}
	}
}

class Room{
	int r;
	int c;
	public Room(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}
