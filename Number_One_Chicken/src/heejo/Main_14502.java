package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 연구소
 * 백준 14502
 * 골드5
 * 
 * 목표 : 연구실의 안전영역의 크기를 구해라
 * (맵 데이터 설명: 0은 빈칸, 1은 벽, 2는 바이러스)
 * 
 * 풀이 방법
 * 1. 바이러스를 전부 퍼지게 한다.
 * 2. 맵의 남아있는 0의 개수를 카운트하여 출력한다.
 * 
 * @author joy96
 *
 */
public class Main_14502 {
	static int N;
	static int M;
	static int map[][];
	static int wallN;
	static int wall[][];
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		N = Integer.parseInt(str1.nextToken());
		M = Integer.parseInt(str1.nextToken());
		map = new int[N][M];
		for(int i =0;i<N;i++) {			
			StringTokenizer str2 = new StringTokenizer(in.readLine());
			for(int j = 0;j<M;j++) {
				map[i][j] = Integer.parseInt(str2.nextToken());
				if(map[i][j]==0) {
					wallN++;	//벽을 세울 수 있는 자리 세기	
				}
			}
		}
		
		//벽을 세울 수 있는 곳들 데이터 정보(좌표) 배열에 저장하기
		wall = new int[wallN][2];
		wallN = 0;
		for(int i =0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) {
					wall[wallN][0] = i;
					wall[wallN][1] = j;
					wallN++;
				}
			}
		}
		
		comb(0,0);
		System.out.println(max);
	}
	
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	//바이러스 확산
	public static void func(int x, int y) {
		//상하좌우 탐색
		for(int i =0;i<4;i++) {
			int tempX = x + dr[i];
			int tempY = y + dc[i];
			
			//좌표가 지도 범위에 들어와있고
			if((tempX>=0)&&(tempX<N)&&(tempY>=0)&&(tempY<M)) {
				//아직 감염이 안됐다면 감염시키기
				if(map[tempX][tempY]==0) {
					map[tempX][tempY]=3;	//해당 좌표 감염
					func(tempX,tempY);		//해당 좌표로 확산
				}
			}
		}
	}
	
	public static void comb(int cnt, int start) {
		//벽 3개 골랐으면 그 상태로 바이러스 확산시켜보고 안전구역 확인해보기
		if(cnt==3) {
			//맵 초기화
			for(int i =0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]==3) {
						map[i][j]=0;
					}
				}
			}
			//바이러스 확산
			for(int i =0;i<N;i++) {
				for(int j=0;j<M;j++) {
					//바이러스를 발견한다면, 퍼트리자
					if(map[i][j]>1) {
						func(i,j);
					}
				}
			}
			//안전구역 카운트
			int count = 0;
			for(int i =0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]==0) {
						count++;
					}
				}
			}
			if(max<count) {
				max = count;
			}
			return;
		}
		else {
			for(int i = start; i<wallN; i++) {
				map[wall[i][0]][wall[i][1]] = 1;
				comb(cnt+1, i+1);
				map[wall[i][0]][wall[i][1]] = 0;
			}
		}
	}
}
