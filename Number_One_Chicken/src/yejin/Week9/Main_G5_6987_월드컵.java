package yejin.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 1) 실패
 * 총 경기 30개
 * (30-무승부)/2 가 각각 승,패의 합
 * 무승부는 최소 2개이상의 국가에서 나야한다.
 * 승 합 = 패 합 / 승+패+무승부 = 30 / 무승부 합 짝수 & 무승부 합/2 < 각 국가 무승부합(X)
 * 
 * -> 한 나라와 여러번 경기해도 괜찮다고 생각
 * -> 여러나라와 각각 한번씩 경기로 다시 생각하자..
 * 
 * 2) 실패 : 여러나라와 각각한번 아니고, 또 그리디하게..
 * 1. 들어오는 입력이 5보다 크면 끝 0
 * 2. win, lose는 서로 나 제외, 큰 수부터 --하기
 * 3. 
 * 
 * 3) 구글링 도움
 * -> DFS (아직 이러한 형태의 dfs가 어렵..)
 * 
 */
public class Main_G5_6987_월드컵 {
	static Game[] game;
	static int isPossible = 1;	//가능하면 1, 불가능 0
	
	//A와 B팀이 붙을 수 있는 경우의 수
	static int p1[] = new int[15], p2[] = new int[15];
	
	static class Game {
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
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//경기 조합
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = i+1; j < 6; j++) {
				p1[cnt] = i;
				p2[cnt++] = j;
			}
		} 
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			game = new Game[6];
			isPossible = 1;
			
			for (int j = 0; j < 6; j++) {
				int win = Integer.parseInt(st.nextToken());
				int same = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				
				//다 합친게 5가 아니면 불가능
				if(win+same+lose !=5) {
					isPossible = 0;
					break;
				}
				
				game[j] = new Game(j, win, same, lose);
			}
			
			if (isPossible==0) {
				sb.append(isPossible+" ");
			} else {
				isPossible = 0;
				dfs(0);
				sb.append(isPossible+" ");
			}
		}
			
		System.out.println(sb);
	}


	private static void dfs(int idx) {
		//이건 왜??하는거?? 리턴할때 리턴시키려고??
		if(isPossible == 1) {
			return;
		}
		
		if(idx == 15) {
			isPossible = 1;
			return;
		}
		
		int a = p1[idx];
		int b = p2[idx];
		
		//a가 이기는 경우
		if(game[a].win >0 && game[b].lose >0) {
			game[a].win--;
			game[b].lose--;
			dfs(idx+1);
			game[a].win++;
			game[b].lose++;
		}
		
		//비기는 경우
		if(game[a].same >0 && game[b].same >0) {
			game[a].same--;
			game[b].same--;
			dfs(idx+1);
			game[a].same++;
			game[b].same++;
		}
		
		//b가 이기는 경우
		if(game[a].lose >0 && game[b].win >0) {
			game[a].lose--;
			game[b].win--;
			dfs(idx+1);
			game[a].lose++;
			game[b].win++;
		}
	}
}
