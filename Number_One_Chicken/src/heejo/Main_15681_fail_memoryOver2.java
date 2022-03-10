package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 백준 15681
 * 트리와 쿼리
 * 골드5
 * 
 * 목표 : 주어진 트리 중 U를 루트로 하는 서브트리에 속한 정점의 수를 출력하기
 * 
 * 1. 트리 데이터를 입력받기
 * - 리스트로 받기
 * - 양방향으로 받기
 * 
 * 2. 트리를 정렬하기
 * - 방향은 부모->자녀 노드 (반대방향은 없애기)
 * 
 * 3. U를 루트로 하여 정점 수 체크하기
 * 
 * 실패 : 메모리 초과
 * 
 * @author joy96
 *
 */
public class Main_15681_fail_memoryOver2 {
	static int N;
	static int R;
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		N = Integer.parseInt(str1.nextToken());
		R = Integer.parseInt(str1.nextToken());
		int Q = Integer.parseInt(str1.nextToken());
		tree = new ArrayList[N+1];
		for(int i = 0; i<=N; i++) {
			tree[i] = new ArrayList<>();
		}

		int tempA;
		int tempB;
		for(int i =0; i<N-1; i++) {
			StringTokenizer str2 = new StringTokenizer(in.readLine());
			//일단 양방향으로 1 체크하기
			tempA = Integer.parseInt(str2.nextToken());
			tempB = Integer.parseInt(str2.nextToken());
			tree[tempA].add(tempB);
			tree[tempB].add(tempA);
		}
		
		//루트 번호 R로 재정렬
		sorting(R);
		
		
		for(int i = 0; i<Q; i++) {
			int u = Integer.parseInt(in.readLine());
			//간선 개수 구하기
			count = 1;	//본인 포함
			counting(u);
			System.out.println(count);
		}
	}
	
	//트리 정렬하기
	public static void sorting(int root) {
		for(int i = 1; i<=N; i++) {
			if(tree[root].contains(i)) {
				for(int j = 0; j<tree[i].size(); j++) {
					if(tree[i].get(j)==root) {
						tree[i].remove(j);
						break;
					}
				}
				sorting(i);
			}
		}
	}
	
	//서브트리 내 노드 개수 카운트하기
	static int count;
	public static void counting(int root) {
		for(int i = 1; i<=N; i++) {
			if(tree[root].contains(i)) {
				count++;
				counting(i);
			}
		}
	}

}
