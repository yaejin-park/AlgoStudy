package heejo.week09_0413;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 G5 2660 회장 뽑기
 * - 어레이리스트 이용해서 양방향으로 연결
 * - 한 사람씩 돌아가면서, 다른 사람들과의 거리 확인하기
 *	 - 계산이 끝난 후 가장 길었던 거리를 그 사람의 최종 점수로 매기기
 *	 - 사람의 최소 점수, 인원 수는 따로 저장해두기
 * - 모든 사람의 계산이 끝난 후, 최소 점수를 가진 사람들을 찾아 출력
 * 
 */
public class Main_2660_G5_회장뽑기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		// 2차원 ArrayList 생성
		ArrayList<Integer>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		//관계 정보 입력받기
		while (true) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(str.nextToken());
			int b = Integer.parseInt(str.nextToken());
			if ((a == -1) && (b == -1)) {
				break;
			}
			if (a==b) {	// [★오답노트★] 자기 자신으로 가는 관계는 필요 없다.
				continue;
			}
			// 양방향으로 입력
			list[a-1].add(b-1);
			list[b-1].add(a-1);
		}
		
		// 각 멤버들의 점수 기록하는 배열
		int score[] = new int[N];
		int score_min = Integer.MAX_VALUE;	//최소 점수
		int score_min_count = 0;	//최소 점수를 가진 인원 수
		
		// 나를 기준으로 한명씩과의 모든 관계를 파악한다.
		for (int me = 0; me < N; me++) {
			// 관계 파악했는지 체크 배열
			boolean check[] = new boolean[N];
			// 큐를 사용해볼까?
			Queue<int[]> queue = new LinkedList<>();
			int me_score = 0; // 최종 점수 확인하는 변수
			queue.offer(new int[] { me, 0 });
			check[me]= true;	// [★오답노트★] 자기 자신은 확인할 필요가 없기 때문에 true 체크 해줘야함
			while (!queue.isEmpty()) {
				int temp[] = queue.poll();
				me_score = temp[1];		//계속해서 관계 점수 갱신하는 역할
				
				//해당 사람과 직접 관계가 있는 연결된 사람들 전부 탐색
				for (int i = 0; i < list[temp[0]].size(); i++) {
					//이미 그 사람은 탐색이 됐다면 패스
					if (check[list[temp[0]].get(i)]) {
						continue;
					}
					//새롭게 탐색이 필요한 사람은 큐에 추가
					else {
						check[list[temp[0]].get(i)] = true;
						queue.offer(new int[] { list[temp[0]].get(i), temp[1] + 1 });
					}
				}
			}
			score[me] = me_score;
			
			//기존 최소값이랑 같다면
			if(score_min == score[me]) {
				score_min_count++;
			}
			//최소값이 새로 갱신된다면
			else if(score_min>score[me]) {
				score_min = score[me];	//최소값 재설정
				score_min_count = 1;	//최소값 인원 리셋
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(score_min + " ");			// 회장 후보의 점수	
		sb.append(score_min_count + "\n");	// 회장 후보의 수
		for(int i =0; i<N; i++) {
			//해당 인원의 점수가 최소값이라면 출력
			if(score[i]==score_min) {
				sb.append((i+1) + " ");
			}
		}
		bw.write(sb + "\n");
		bw.flush();
		bw.close();
	}
}
