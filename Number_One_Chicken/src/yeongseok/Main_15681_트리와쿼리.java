package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 트리를 어떤 자료구조로 구성할까 고민을 많이 했음.
 * 힌트 수도 코드의 -> for(Node in connect[currentNode]) 부분에서
 * connect가 배열의 배열임에 착안해서, 내 코드에서의 connect는 배열의 리스트로 구현함.
 * 트리 구성 순서
 *  1. 입력으로 들어온 간선들의 연결정보를 입력받고(a,b), a와 b 모두에게 각각 b,a를 추가한다. line 44
 *  2. makeTree 함수에서 Node == parent이면, Node를 리스트에서 삭제한다. line 73
 * @author 노영석
 *
 */
public class Main_15681_트리와쿼리 {
	static int N,R,Q;
	//최초에 입력으로 주어진 간선들의 연결정보가 담기는 배열이자,
	//트리의 연결정보가 담기는 배열
	static ArrayList<Integer>[] connect; //index == node, value == node에 연결된 nodes
	static int size[];	//index == node, value == 서브트리 노드 수
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		size = new int[N+1];
		connect = new ArrayList[N+1];
		for(int i = 1 ; i <N+1;i++) {
			connect[i] = new ArrayList<>();
		}
		int a,b;
		
		//간선 정보 입력받기
		for(int i =0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			//일단 연결 정보를 양쪽에 모두 추가한다.
			connect[a].add(b);
			connect[b].add(a);
		}
		
		makeTree(R,-1);
		countSubtreeNodes(R);

		for(int i = 0 ; i <Q; i++) {
			System.out.println(size[Integer.parseInt(br.readLine())]);
		}
		
	}
	
	
	/*
	 * 힌트 수도 코드
	 * def makeTree(currentNode, parent) :
    	 for(Node in connect[currentNode]) :
         if Node != parent:
            add Node to currentNode’s child
            set Node’s parent to currentNode
            makeTree(Node, currentNode)
	 */
	private static void makeTree(int currentNode, int parent) {
		//node == parent이면 해당 노드를 리스트에서 삭제할건데, 앞에서 부터 순회하면 index 정보가 꼬일수 있음으로 뒤에서부터
		for(int i = connect[currentNode].size()-1; i>-1; i--) {
			int node = connect[currentNode].get(i);
			if(node == parent) 
				connect[currentNode].remove(i);
			else {
				makeTree(node, currentNode);
			}
		}
	}
	/*
	 * 힌트 수도 코드
	 * def countSubtreeNodes(currentNode) :
	     size[currentNode] = 1 // 자신도 자신을 루트로 하는 서브트리에 포함되므로 0이 아닌 1에서 시작한다.
	     for Node in currentNode’s child:
	        countSubtreeNode(Node)
	        size[currentNode] += size[Node]
	 */
	private static void countSubtreeNodes(int currentNode) {
		size[currentNode] = 1; // 자신도 자신을 루트로 하는 서브트리에 포함되므로 0이 아닌 1에서 시작한다.
	    for(int node : connect[currentNode]) {
	    	countSubtreeNodes(node);
	        size[currentNode] += size[node];
	    }
	}
	    

}
