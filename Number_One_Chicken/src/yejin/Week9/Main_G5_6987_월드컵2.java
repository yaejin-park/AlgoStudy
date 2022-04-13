package yejin.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
 * 총 경기 30개
 * (30-무승부)/2 가 각각 승,패의 합
 * 무승부는 최소 2개이상의 국가에서 나야한다.
 * 승 합 = 패 합 / 승+패+무승부 = 30 / 무승부 합 짝수 & 무승부 합/2 < 각 국가 무승부합(X)
 * 
 * -> 한 나라와 여러번 경기해도 괜찮다고 생각
 * -> 여러나라와 각각 한번씩 경기로 다시 생각하자..
 * 
 * 1. 들어오는 입력이 5보다 크면 끝 0
 * 2. win, lose는 서로 나 제외, 큰 수부터 --하기
 * 3. 
 * 
 */
public class Main_G5_6987_월드컵2 {
	static Game[] game;
	static int isPossible = 1;	//가능하면 1, 불가능 0
	static PriorityQueue<Game> pq;
	
	static class Game implements Comparable<Game>{
		int nation;
		int win;
		int same;
		int lose;
		
		public Game(int nation, int win, int same, int lose) {
			super();
			this.nation = nation;
			this.win = win;
			this.same = same;
			this.lose = lose;
		}

		//진 수가 많은 나라부터 내림차순
		@Override
		public int compareTo(Game o) {
			return o.lose-this.lose;
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			game = new Game[6];
			isPossible = 1;
			pq = new PriorityQueue<>();
			
			for (int j = 0; j < 6; j++) {
				int win = Integer.parseInt(st.nextToken());
				int same = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				
				//각입력이 5넘으면 불가능  or 다 합친게 5가 아니면 불가능
				if(win >5 || same >5 || lose>5 || win+same+lose !=5) {
					isPossible = 0;
					break;
				}
				
				System.out.println(win+","+same+","+lose);
				
				game[j] = new Game(j, win, same, lose);
				pq.offer(new Game(j, win, same, lose));
			}
			
			for (int j = 0, end = pq.size(); j < end; j++) {
				System.out.print(pq.poll().lose);
			}
			System.out.println();
			
			if (isPossible==0) {
				sb.append(isPossible+" ");
			} else {
				//비교할 나라
				for (int j = 0; j < 6; j++) {
					System.out.println(j+"!");
					if(compare(j)==0) {	//불가능 나오면 비교 끝
						break;
					}
				}
				sb.append(isPossible+" ");
			}
		}
			
		System.out.println(sb);
	}

	//j : 시작할 나라
	private static int compare(int j) {
		boolean visited[] = new boolean[6];
		//win - lose
		while(game[j].win >0) {	//승리횟수가 0이아니면 반복문돌기
			Game cur = pq.peek();
			System.out.println(cur.lose+"??");
				if(j == cur.nation || visited[cur.nation]) continue;	//나 빼고
				
				if(cur.lose>0) {	//lose빼기
					--game[cur.nation].lose;
					if(--game[j].win ==0) {	//이긴 횟수 비교 완료하면 빠져나가기
						break;
					}
				}
				
				//비교 다햇는데도 이긴횟수 남아있으면 불가능 리턴
				if(j2 == 5) {
					isPossible = 0;
					return 0;
				}
		}
		
		//same
		if(game[j].same !=0) {
				if(j==i) continue;	//나 빼고
				
				if(game[i].same>0) {
					--game[i].same;
					if(--game[j].same == 0) {
						break;
					}
				}
				if(i == 5) {
					isPossible = 0;
					return 0;
				}
		}
		
		return 1;
	}
}
