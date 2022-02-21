package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 1. 선택된 노드 & 자손 노드들을 삭제한다. (bfs)
 * 	  - 자손 노드를 삭제하기 위해서 아래를 반복한다.
 * 		- Queue에는 삭제된 노드들의 index가 들어간다
 * 		- Queue에서 빼내서 그 index를 부모로 하는 노드들을 삭제한다.
 * 2. leaf 노드를 탐색한다.
 *    - 자신을 부모로 하는 노드가 존재하는지 여부를 따진다.
 * @author 노영석
 */
public class Main_1068_트리 {
	static class Node{
		int index;
		int parent;
		public Node(int index, int parent) {
			this.index = index;
			this.parent = parent;
		}
	}
	static int num;		//노드 수
	static int deleteIndex;		//삭제 대상 노드 인덱스
	static List<Node> list = new ArrayList<>();
	static int leafCnt=0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		
		String[] split = br.readLine().split(" ");
		for(int i = 0 ; i< num; i++) {
			list.add(new Node(i,Integer.parseInt(split[i])));
		}
		
		deleteIndex = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visitied = new boolean[num];
		queue.offer(deleteIndex);
		list.remove(deleteIndex);
		visitied[deleteIndex] = true;
		
		//자손 노드 삭제
		while(!queue.isEmpty()) {
			int poll = queue.poll();	//이전에 삭제된 노드의 index
			int index;
			for(int i = list.size()-1; i>-1; i--) {	//노드 리스트 순회
				if(list.get(i).parent == poll) {	//이전에 삭제된 노드의 index를 부모로 하는 노드가 있으면
					index = list.get(i).index;		
					list.remove(i);					//삭제한다.
					
					if(!visitied[index]) {			//큐에 한번도 들어간 적이 없으면
						visitied[index] = true;		
						queue.offer(index);			//큐에 넣는다
					}
				}
			}
		}
		int index;
		//leaf 노드 탐색
		outer : for(Node n1 : list) {	//노드 리스트 순회
			index = n1.index;
			for(Node n2 : list) {
				if(n2.parent == index) {	//n1가 leaf 노드가 아닌 경우(index를 부모로 가지는 노드가 존재하면)
					continue outer;
				}
			}
			leafCnt++;	//n1가 leaf 노드인 경우
		}
		
		System.out.println(leafCnt);
	}

}
