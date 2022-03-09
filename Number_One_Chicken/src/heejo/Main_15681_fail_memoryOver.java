package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 15681
 * 트리와 쿼리
 * 골드5
 * 
 * 목표 : 주어진 트리 중 U를 루트로 하는 서브트리에 속한 정점의 수를 출력하기
 * 
 * 1. 트리 데이터를 입력받기
 * - 이중배열로 받기
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
public class Main_15681_fail_memoryOver {
	static int N;
	static int R;
	static boolean map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		N = Integer.parseInt(str1.nextToken());
		R = Integer.parseInt(str1.nextToken());
		int Q = Integer.parseInt(str1.nextToken());
		map = new boolean[N+1][N+1];

		int tempA;
		int tempB;
		for(int i =0; i<N-1; i++) {
			StringTokenizer str2 = new StringTokenizer(in.readLine());
			//일단 양방향으로 1 체크하기
			tempA = Integer.parseInt(str2.nextToken());
			tempB = Integer.parseInt(str2.nextToken());
			map[tempA][tempB] = true;
			map[tempB][tempA] = true;
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
			if(map[root][i]) {
				map[i][root]=false;	//역방향 끄기
				sorting(i);
			}
		}
	}
	
	//서브트리 내 노드 개수 카운트하기
	static int count;
	public static void counting(int root) {
		for(int i = 1; i<=N; i++) {
			if(map[root][i]) {
				count++;
				counting(i);
			}
		}
	}

}
