package yejin.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌 {
	static StringBuilder sb = new StringBuilder();
	
	static int N,W,H;
	static List<Queue<Integer>> map = new ArrayList<Queue<Integer>>();
	static List<Queue<Integer>> tempMap = new ArrayList<Queue<Integer>>();
	static int block; //남은 블록 개수
	//상하좌우
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = { 0, 0, -1, 1};
	
	//가장 위의 row값 저장
	static int top[];
	
	//떨어트릴 열 조합
	static int ball[];
	static boolean isSelected[];	//방문체크
	static int temp;		//구슬개수 임시
	static int minBall = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < T+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < W; j++) {
				map.add(new LinkedList<Integer>());
				tempMap.add(new LinkedList<Integer>());
			}
			
			ball = new int[N];
			isSelected = new boolean[W];
			
			block = 0; //벽돌 개수
			minBall = Integer.MAX_VALUE;
			
			//맵 입력 받기 
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					int cur = Integer.parseInt(st.nextToken());
					//벽돌이면
					if(cur != 0) {
						block++; //총벽돌수 증가
						map.get(c).add(cur);//map의 queue에 넣기
					}
				}
			}
			
			//구슬 떨어트릴 열의 순열 -> dfs
			permu(0);
			sb.append("#"+i+" "+minBall+"\n");
		}
		System.out.println(sb);
	}

	//볼 떨어트릴 열 순열
	private static void permu(int cnt) {
		if(cnt==N) {
			//공 N번 순서대로 떨어트리기
			for (int i = 0; i < N; i++) {
				//임시변수들
				temp = block;
				//map -> tempMap 깊은복사(이게 안돼..ㅠㅠㅠ)
				Object tempArr[] = map.toArray();
				
				for (int j = 0; j < tempArr.length; j++) {
//					tempMap.add();
				}
//				
				dfs(ball[i]);
				minBall = Math.min(minBall, temp);	//최소값 저장
			}
			return;
		}
		
		for (int i = 0; i < W; i++) {
			if(isSelected[i]) return;
			isSelected[i] = true;
			ball[cnt] = i;
			permu(cnt+1);
			isSelected[i] = false;
		}
	}

	//떨어졌을때 블록 삭제 
	private static void dfs(int col) {
		int val = tempMap.get(col).poll();
		temp--;
		
		//더이상 없앨 블록이 없으면 그만두기
		if(temp == 0) return;
		
		//아래로 터트리기
		if(tempMap.get(col).size()!=0) {
			int bottomAd = tempMap.get(col).poll();
			temp--;
			dfs(bottomAd);
		}
		
		//가로 터트리기
		for (int i = 1; i < val; i++) {
			if(tempMap.get(i).size()>=val) {
				int horizonAd = tempMap.get(i).poll();
				temp--;
				dfs(horizonAd);
			}
		}
	}
}

