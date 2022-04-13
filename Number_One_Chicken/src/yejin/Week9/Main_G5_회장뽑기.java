package yejin.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 점수가 제일 작은 사람 : 회장
 * 한 사람의 점수 : 관계 가중치 중 최대값
 * 회장 : 점수가 젤 작은 사람
 * 
 */
public class Main_G5_회장뽑기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int array[][] = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i==j) continue;
				array[i][j] = Integer.MAX_VALUE/2;
			}
		}
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a==-1 && b==-1) break;
			
			array[a][b] = 1;
			array[b][a] = 1;
		}
		
		//경출도 (플로이드 워셜  : 모든쌍에 대한 최소)
		for (int k = 1; k <= N; k++) {				//경유지
			for (int i = 1; i <= N; i++) {			//출발
				if(k==i) continue;
				for (int j = 1; j <= N; j++) {		//도착
					if(array[i][j] > array[i][k] + array[k][j]) {
						array[i][j] = array[i][k] + array[k][j];
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;	//제일 작은 점수 저장
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {		//행고정
			int max = 0;	//그 사람의 관계 중 최대값 저장
			for (int j = 1; j <= N; j++) {
				if(max < array[i][j]) max = array[i][j];
			}
			//더 작은 점수를 가진 사람이 나오면 min 갱신, queue 비우고 다시 넣기
			if(min > max) {
				min = max;
				queue.clear();
				queue.offer(i);
			} else if(min == max) {
				queue.offer(i);
			}
		}
		
		System.out.println(min+" "+queue.size());
		while(!queue.isEmpty()) {
			System.out.print(queue.poll()+" ");
		}
	}

}
