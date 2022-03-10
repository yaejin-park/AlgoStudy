package yejin.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_G5_15681_트리와쿼리 {
	/*
	 *  정점 U를 루트로 하는 서브트리에 속한 정점의 수 출력
	 *  Q줄에 결쳐 각 쿼리의 답을 정수 하나로 출력
	 *  
	 *  입력
	 *  N R Q
	 *  N-1줄에 걸쳐 U V 형태로 간선 정보 주어짐(U V가 연결)
	 *  Q에 걸쳐 U가 하나씩 주어짐
	 *  
	 *  어려워어ㅓ어어어어!!!구글링 거의 복붙수준..
	 *  
	 */
	static int N, R, Q;
	static ArrayList<ArrayList<Integer>> adList;	//인접리스트(입력값)
	static ArrayList<ArrayList<Integer>> subList;	//서브리스트
	static int parent[], size[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//정점 수
		R = Integer.parseInt(st.nextToken());	//루트 번호
		Q = Integer.parseInt(st.nextToken());	//쿼리 수
		
		size = new int[N+1];		//해당 U를 루트로하는 정점수
		parent = new int[N+1];		//해당 노드의 부모노드
		adList = new ArrayList<>();	//인접 노드 담는 리스트
		subList = new ArrayList<>();//서브 노드 담는 리스트
		
		for (int i = 0; i < N+1; i++) {
			adList.add(new ArrayList<Integer>());
			subList.add(new ArrayList<Integer>());
		}
		
		//인접 리스트 만들기
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			adList.get(U).add(V);
			adList.get(V).add(U);
		}
		
		makeTree(R, -1);		//부모, 자식들의 목록 저장
		countSubtreeNodes(R);
		
		//문제에 설명한 U
		for (int i = 0; i < Q; i++) {
			int U = Integer.parseInt(br.readLine());
			System.out.println(size[U]);
		}
		br.close();
	}
	
	static void makeTree(int cur, int p) {
		//인접 리스트 돌면서
		for(int ad : adList.get(cur)) {
			if(ad != p) {	//인접노드가 부모가 아니라면
				subList.get(cur).add(ad);	//cur노드의 자식노드 리스트에 저장
				parent[ad] = cur;			//자식노드에는 cur노드를 부모노드로 저장
				makeTree(ad, cur);			//인접노드 호출(cur 노드를 부모로 하고)
			}
		}
	}
	
	private static void countSubtreeNodes(int cur) {
		size[cur] = 1;	//자신도 자신을 루트로하는 서브트리에 포함
		//하위 노드들 돌면서 사이즈
		for(int node : subList.get(cur)) {
			countSubtreeNodes(node);	//먼저호출해서 자식들부터 사이즈 세서 더하기 (리프노드는 1)
			size[cur] += size[node];	//현재노드의 사이즈는 자식노드들의 사이즈를 다 더한 값.
		}
	}
}
