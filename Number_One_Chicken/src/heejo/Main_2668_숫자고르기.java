package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 아이디어
 * - 숫자들끼리 사이클이 형성되는지 확인하자
 * - 한 숫자를 기준으로 사이클 출발 => 형성 되면 ok, 실패하면 복구
 * - 복구를 위해 탐방하는 숫자들은 queue에 저장해두자
 * - 실패하는 경우 queue를 이용하여 원상복구
 * - 성공하는 경우 queue 크기만큼 최종 카운트에 합산  
 * 
 * 주의사항
 * - 숫자들끼리 사이클이 만들어져야 선택할 수 있다
 * - 사이클은 여러개 만들어질 수 있다 
 */
public class Main_2668_숫자고르기 {
	static int array[];
	static boolean check[];
	static int total_count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		array = new int[N + 1];		//표
		check = new boolean[N + 1];	//탐색 여부 확인. true가 이미 탐색했다는 것을 의미
		for (int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		// 첫번째 배열부터 순차적으로 실행
		for (int i = 1; i <= N; i++) {
			//해당 i가 탐색이 되지 않은 곳이라면 탐색하기 
			if (check[i] == false) {
				Queue<Integer> queue = new LinkedList<>();
				check[i] = true;	//시작점을 탐색한다고 체크
				queue.offer(i);		//큐에 시작 위치 저장
				calc(i, i, queue);	//계산 시작
			}
		}
		
		//결과 출력1. 뽑힌 총 개수
		System.out.println(total_count);
		
		//결과 출력2. 선택된 숫자들 오름차순으로 출력
		for(int i = 1; i<=N; i++) {
			if(check[i] == true) {
				System.out.println(i);
			}
		}
	}
	
	// 계산
	public static void calc(int start, int root, Queue<Integer> queue) {
		// 사이클이 만들어졌다면 성공 후 종료
		if (array[start] == root) {
			//현재 사이클을 밟은 곳들의 개수만큼 count에 합산
			total_count+=queue.size();
			return;
		}
		// 해당 부분이 아직 탐색이 안됐다면 탐색 계속
		else if (check[array[start]] == false) {
			check[array[start]] = true;	//탐색 완료 표시를 하면서
			queue.offer(array[start]);	//현재 길을 큐에 기록 해두자
			calc(array[start], root, queue);	//현재 위치에 저장된 값을 시작으로 하는 다음을 탐색 
		}
		// 그 외에는 전부 사이클 생성 실패이기 때문에 종료 (탐색이 된 부분인데, root가 아니므로 사이클이 만들어지지 않았다)
		else {
			//밟은 기록들을 다시 false로 바꾸자
			while (!queue.isEmpty()) {
				check[queue.poll()] = false;
			}
		}
	}
}
