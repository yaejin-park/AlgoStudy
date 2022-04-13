package yejin.Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 아이디어)
 * 탐색하다가 방향이 바뀌면 꼭지점 저장(방향 2번만 바뀌고 시작점으로 와야함)
 * 안에도 다 1로 채워져있는지 검사
 * 직각이등변여부 알아보기 -> 꼭지점 각각의 길이 구한후 1:1:루트2면 직각 삼각형
 * 
 * null pointer 도대체 어디..?
 * @author yaejin
 *
 */
public class Main_G2_2658_직각이등변 {
	
	static class Point implements Comparable<Point>{
		int r;
		int c;
		
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if(this.r==o.r) {
				return this.c-o.c;
			} else {
				return this.r-o.r;
			}
		}
	}
	
	//우, 우하, 하
	//우하좌상 대각선(우하, 우상, 좌하, 좌상)
	static int dr[] = {0,1,0,-1,1,-1,1,-1};
	static int dc[] = {1,0,-1,0,1,1,-1,-1};
	
	static int [][]map;	//입력저장
	static boolean [][]visit;	//입력저장
	static int dir;		//방향
	static Point point[] = new Point[3]; //꼭지점저장
	static boolean isAvailable = true;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean isFirst = true;	//첫 꼭지점인지 여부
		//맵받기
		map = new int[12][12];
		visit = new boolean[12][12];
		
		boolean isExist = false;
		
		for (int i = 1; i < 11; i++) {
			String str = br.readLine();
			for (int j = 1; j < 11; j++) {
				int cur = str.charAt(j-1)-'0';
				map[i][j]= cur;
				
				//첫번째 1(첫 꼭지점 찾기)
				if(isFirst && cur == 1) {
					isFirst = false;
					point[0] = new Point(i, j);	//꼭지점 저장
					isExist = true;
				}
			}
		}
		
		if(!isExist) {
			System.out.println(0);
		} else {
			//테두리를 2로 채우기
			bfs();
			
			//첫시작점에서 시작해서 이어지는 선 방향 찾기
			dir = findDir(point[0].r, point[0].c);
			
			if(dir !=0 && dir !=1 && dir !=4 && dir ==-1) {	//첫 꼭지점에서 이어지는 선이 우, 우하, 하가 아니거나 -1이면 직각삼각형 불가
				System.out.println(0);
			} else {										//가능성 있으면
				//직각삼각형 여부 체크 & 채워진 여부 체크
				if(dfs(0, dir) && isTriangle() && isFilled()) {
					Arrays.sort(point);
					for (int i = 0; i < 3; i++) {
						System.out.println(point[i].r+" "+point[i].c);
					}
				} else {
					System.out.println(0);
				}
			}
		}
		
	}

	//0만 탐색 1번해서 테두리 2로 바꾸기
	private static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean [][] visited = new boolean[12][12];
		
		visited[0][0] = true;
		queue.add(new Point(0, 0));
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr<0 || nc<0 || nr>11 || nc>11 || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				if(map[nr][nc]==1) map[nr][nc] = 2;
				if(map[nr][nc]==0) queue.offer(new Point(nr, nc));
			}
		}
	}


	//안이 1로 채워져있는지 여부 찾기
	//테두리 돌면서 체크는 하니까 1이었는데 0으로 한번만 바뀌는건 가능으로 안이 채워졌는지 체크
	private static boolean isFilled() {
		for (int i = 0; i < 12; i++) {
			int now = 0;
			int change = 0;
			
			for (int j = 0; j < 12; j++) {
				if(now==0 && map[i][j] >0) {
					change++;
					now = 1;
				} else if(now==1 && map[i][j] ==0) {
					change++;
					now = 0;
				}
				if(change>2) return false;
			}
		}
		return true;
	}
	
	//직각 삼각형 여부 찾기 ( 변의 길이 찾고, 제곱한 값이 1:1:2가되어야함)
	private static boolean isTriangle() {
		int len[] = new int[3];
		for (int i = 0; i < 3; i++) {
			len[i] = (int) (Math.pow(point[i].r-point[i+1==3?0:i+1].r, 2) +  Math.pow(point[i].c-point[i+1==3?0:i+1].c, 2));
		}
		
		// 1: 1: 2
		Arrays.sort(len);
		
		if(len[0] == len[1] && len[1]*2 == len[2]) {
			return true;
		}
		return false;
	}

	//직각삼각형이 가능하면 true 리턴
	private static boolean dfs(int cnt, int dir) {
		Point cur = point[cnt];
		visit[point[cnt].r][point[cnt].c] = true;
		
		if(!isAvailable || cnt==3) return isAvailable;	//불가능 리턴
		
		int nr = cur.r, nc = cur.c;
		//방향 바뀔때까지
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			//마지막 변 && 시작점에 다다르면
			if(cnt==2 && nr>=1 && nr<11 && nc>=1 && nc<11 && point[0].r == nr && point[0].c == nc) {
				return isAvailable;
			}
			
			//방향 바뀌는 경우
			//1. 경계검사 불통과 -> 방향바꿔야함
			if(nr<1 || nc<1 || nr>10 || nc>10) {
				if(cnt+1 >2) {//꼭지점이 3개이상 생기는 경우면 false리턴
					isAvailable = false; 
					return isAvailable;	
				}
				
				point[cnt+1] = new Point(nr-dr[dir], nc-dc[dir]);
				dfs(cnt+1, findDir(nr-dr[dir], nc-dc[dir]));
				break;
			}
			
			//2. 0이 나오면
			if(map[nr][nc]==0) {
				if(cnt+1 >2) {//꼭지점이 3개이상 생기는 경우면 false리턴
					isAvailable = false; 
					return isAvailable;	
				}
				
				point[cnt+1] = new Point(nr-dr[dir], nc-dc[dir]);
				dfs(cnt+1, findDir(nr-dr[dir], nc-dc[dir]));
				break;
			}
			visit[nr][nc] = true;	//방문처리
		}
		return isAvailable;
	}
	
	//이어지는 변의 방향찾기
	private static int findDir(int r, int c) {
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			//경계검사
			if(nr<1 || nc<1 || nr>10 || nc>10) continue;
			
			//방문안한 곳이고, 1로 이어지면 해당 방향idx return 
			if(map[nr][nc]==2 && !visit[nr][nc]) {
				return i;
			}
		}
		isAvailable = false;
		return -1;
	}
}
