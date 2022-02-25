package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
/**
 * 1. 입력을 받으면서 tree를 생성한다.
 * 2. tree모든 노드들을 순회하면서 K노드와 부모는 다르지만 부모의부모는 같은 노드의 수를 센다
 * 
 * @author 노영석
 *
 */
public class Main_9489_사촌 {
	
	
	static int N; // 노드 수
	static int K; // 검사 대상 노드
	static int nodes[] = new int[1001];	//index == node index, value == node value
	static int parent[] = new int[1001];	//index == node index, value == parent node index
	static int cnt;	//사촌의 수
	static int kIndex;	//검사 대상 노드 index
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split ;
		while(true) {
			split = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			K = Integer.parseInt(split[1]);
			
			if(N == 0 && K == 0) break;
			
			split = br.readLine().split(" ");
 			
 			
 			cnt=0;
 			parent[0] = -1;
 			nodes[0] = -1;
			
 			int idx = -1;
 			//트리 생성
			for(int i=1; i<=N; i++) {
				nodes[i] = Integer.parseInt(split[i-1]);
				if(nodes[i] == K) kIndex = i;	//검사 대상 노드의 인덱스를 미리 저장해놓음
				if(nodes[i] != nodes[i-1]+1) idx++;	//연속된 수가 아니면
				parent[i] = idx;
			}
			
			int answer = 0;
			//트리 순회하면서 부모는 다르고 부모의부모는 같은 노드의 수를 센다
			for(int i=1; i<=N; i++) {
				//부모는 다르고						부모의 부모는 같으면
				if(parent[i] != parent[kIndex] && parent[parent[i]] == parent[parent[kIndex]]) {
					answer++;
				}
			}
			System.out.println(answer);
		}
		
		
	}

}
