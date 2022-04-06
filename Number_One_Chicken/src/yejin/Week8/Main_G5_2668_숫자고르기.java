package yejin.Week8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main_G5_2668_숫자고르기 {
	/*
	 * 	1. 인덱스 = 값 : 무조건 뽑기
	 * 	2. 사이클 : 뽑기
	 * 	틀린점) 사이클 여러개도 가능!
	 * 	
	 * 	1<=N<=100
	 * 
	 *  아이디어 ) 1부터 N까지 순회하며 방문안되어있으면 dfs 들어가기,
	 *  시작점으로 돌아오면(사이클이 생기면) + 인덱스=값이면 뽑기
	 *  
	 *  뽑힌 정수를 작은수부터 오름차순 출력해야하므로 priority queue에 넣기
	 */
	static boolean checked[];	//방문체크
	static int map[];
	static boolean flag;				//사이클 존재 여부
	static ArrayList<Integer> picked; 	//dfs돌면서 뽑힌 수 넣기
	static PriorityQueue<Integer> pq = new PriorityQueue<>();	//뽑힌 수 오름차순 저장
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N =Integer.parseInt(br.readLine());
		
		//방문체크
		checked = new boolean[N+1];
		
		//입력받기
		map = new int[N+1];	//1부터 시작
		for (int i = 1; i < N+1; i++) {
			map[i] = Integer.parseInt(br.readLine());
			//인덱스랑 값이 같으면 저장해두기
			if(i==map[i]) {
				checked[i] = true;	//방문처리
				pq.offer(i);
			}
		}
		
		//dfs 시작점
		for (int i = 1; i <=N ; i++) {
			//방문안했으면 dfs들어가기
			if(!checked[i]) {
				picked = new ArrayList<>(N+1);	//뽑힌 수 초기화, 선언
				flag = false;					//사이클 존재여부 초기화
				dfs(i,i);
				
				//사이클이 존재했으면, pq에 넣기
				if(flag) {
					for (int pick : picked) {
						pq.offer(pick);
					}
				} 
				//사이클 존재안했으면, 방문체크 되돌리기
				else {
					for (int pick : picked) {
						checked[pick] = false;
					}
				}
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(pq.size()+"\n");
		for (int i = 0, end =pq.size(); i < end; i++) {
			bw.write(pq.poll()+"\n");
		}
		bw.flush();
		bw.close();
	}

	
	static void dfs(int n, int start) {
		//방문체크
		checked[n] = true;
		picked.add(n);
		int val = map[n]; //해당 인덱스의 값
		
		//시작점 도착하면 끝(사이클 존재하면)
		if(val==start) {
			flag = true;	//사이클 존재여부 체크
			return;
		}
		
		//방문안했으면 dfs더 들어가기
		if(!checked[val]) {
			dfs(val, start);
		}
	}
}
