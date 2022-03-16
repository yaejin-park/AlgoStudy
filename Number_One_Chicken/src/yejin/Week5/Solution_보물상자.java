package yejin.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 0~F숫자 N개 입력
 * 생성가능한 수 내림차순 정렬하고 K번쨰로 큰 수 출력
 * 
 * 16진수는 0x~~, 
 * @author yaejin
 *
 */

public class Solution_보물상자 {
	static int T,N,K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		//TC만큼 반복
		T = Integer.parseInt(br.readLine());
		for (int i = 1; i < T+1; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//숫자개수
			K = Integer.parseInt(st.nextToken());	//크기 순서K
			
			int side = N/4;	//한변에 담을 수 있는 수 개수
			int answer = 0;
			
			String str =br.readLine();
			//번갈아가면서 넣고 빼고 하기
			Deque<String> queue = new LinkedList<String>();
			Deque<String> temp = new LinkedList<String>();
			//먼저 N개의 숫자 큐에 넣기
			for (int j = 0; j < N; j++) {
				queue.add(str.charAt(j)+"");	//꺼내 쓰기위한 큐
			}
			
			//우선순위큐로 내림차순으로 넣기
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			//중복제거위해 HashSet사용
			HashSet<Integer> set = new HashSet<>();
			
			//변에 들어갈수만큼 사각형 조합 가능
			for (int r = 0; r < side; r++) {
				//4변만큼 반복하며 만들어질 수 있는 수 저장
				for (int s = 0; s < 4; s++) {
					String su = "";
					//변에 들어가는 수만큼 반복
					for (int j = 0; j < side; j++) {
						if(r%2==0) {
							String cur = queue.pop();
							su+=cur;	//수 만들기
							temp.add(cur);
						} else {
							String cur = temp.pop();
							su+=cur;	//수 만들기
							queue.add(cur);
						}
					}
					//해시셋에 16진수->10진수로 저장(중복제거위해)
					set.add(Integer.parseInt(su,16));
				}
				//마지막을 맨앞으로 넣기
				if(r%2==0) {
					//temp 뒤에거 빼고 앞으로 넣기
					String last = temp.pollLast();
					temp.addFirst(last);
				} else {
					//queue 뒤에거 빼고 앞으로 넣기
					String last = queue.pollLast();
					queue.addFirst(last);
				}
			}
			//해시맵 -> 우선순위큐로 다시넣기(내림차순 정렬)
			for(int su : set) {
				pq.add(su);
			}
			
			//K만큼 반복해서 뽑기(K번째로 큰수 구하기위해)
			for (int j = 0; j < K; j++) {
				if(j==K-1) {
					answer = pq.poll();
				} else {
					pq.poll();
				}
			}
			sb.append("#"+i+" "+answer+"\n");
		}
		System.out.println(sb);
	
	}

}
