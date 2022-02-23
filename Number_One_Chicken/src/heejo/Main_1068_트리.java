package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 트리
 * 백준
 * 골드5
 * 1068
 * 
 * 목표 : 트리가 주어지고 노드를 하나 지웠을 때, 리프 노드의 개수를 구해라
 * 
 * 풀이방법
 * 0. 기본 세팅
 * 1. 해당 노드를 지우고 그 뒤의 노드들까지 전부 지운다.
 * 2. 남아있는 노드들이 부모노드인지 확인한다.
 * 3. [출력] 리프 노드 : 남겨진 노드 - 부모노드
 */
public class Main_1068_트리 {
	static int N;	//노드 개수
	static int deleteNode;	//지울 노드
	static int originals[];	//기존 트리 정보들
	static boolean isDeleted[];	//이 노드는 삭제되었는지를 체크
	static boolean isParent[];	//이 노드는 누군가의 부모노드인지를 체크
	public static void main(String[] args) throws Exception {
		//0. 기본설정
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		originals = new int[N];
		for (int i = 0; i < N; i++) {
			originals[i] = Integer.parseInt(str1.nextToken());
		}
		deleteNode = Integer.parseInt(in.readLine());
		
		isParent = new boolean[N];	//누군가의 부모라면 true
		isDeleted = new boolean[N];	//지워진 노드의 위치에 true
		
		
		//1. 해당 노드를 지우고, 꼬리들까지 전부 지운다.
		isDeleted[deleteNode]=true;	//해당 노드를 지우고
		delete(deleteNode);	//해당 노드의 꼬리들을 전부 지운다
		
		//2. 남아있는 노드들을 가지고 부모인지 판단한다.
		for(int i = 0; i<N; i++) {
			if(isDeleted[i]==false) {
				if(originals[i]!=-1) {
					isParent[originals[i]] = true;					
				}
			}
		}
		
		//2-2. 부모 노드들의 개수와 사라진 노드들 개수를 파악한다.
		int parent_count = 0;	//부모인 노드들의 숫자
		int deleted_count = 0;	//사라진 노드들의 숫자
		for(int i = 0; i<N; i++) {
			if(isParent[i]==true) {
				parent_count++;
			}
			if(isDeleted[i]==true) {
				deleted_count++;
			}
		}
		
		//3. [출력] 리프노드 : 남겨진 노드들 중에서 부모 노드들을 뺀 값
		System.out.println((N-deleted_count)-parent_count);
	}
	
	//노드를 꼬리까지 지우는 함수
	public static void delete(int deleteNode) {
		for(int i = 0; i<N; i++) {
			if(originals[i]==deleteNode) {
				isDeleted[i]=true;
				delete(i);
			}
		}
	}
}
