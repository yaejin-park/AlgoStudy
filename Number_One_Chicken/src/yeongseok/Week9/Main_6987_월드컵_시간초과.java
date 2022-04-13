package yeongseok.Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 6개 팀이 모두 한번씩 대결하는 경우의 수는 6C2 = 15이다.
 * 15개 대결마다 나올 수 있는 결과는 승리, 무승부 ,패패 3가지 이다.
 * 3^15승의 경우에 수에 대해서 하나의 경우를 만들때마다 1~4 예측결과에 대해서 가능 여부를 판단한다.
 * 1~4중 가능하다고 결론이 난 예측결과에 대해서는 이후 판단하지 않는다.
 * 
 * 1. 6C2의 조합을 구한다.
 */
public class Main_6987_월드컵_시간초과 {
	static class Fight{	//대결 대상 팀이 저장되는 클래스
		int team1;
		int team2;
		public Fight(int team1, int team2) {
			this.team1 = team1;
			this.team2 = team2;
		}
	}
	static boolean isPossible[]; 	//1~4 예상결과가 가능한 결과인지 여부가 저장된다.
	static int expect[][][];	//예상결과
	static final int WIN = 1, DRAW=2, LOSE=3;
	static List<Fight> fightTeams;	//대결할 두 팀의 조합 리스트
	static int [] fightResult;		//팀별 대결 결과
	static int res[];	//조합으로 뽑힌 두 팀
	public static void main(String[] args) throws Exception{
		isPossible = new boolean[5];
		res = new int[2];
		fightTeams = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		expect = new int[5][6+1][3+1];
		for(int t = 1 ; t <= 4; t++) {
			st=new StringTokenizer(br.readLine());
			for(int i = 1; i <= 6; i++) {
				for(int j = 1; j <=3; j++) {
					expect[t][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		combination(0,1);
		fightResult = new int[fightTeams.size()];
		DFS(0);
		for(int i = 1; i<5; i++) {
			if(isPossible[i]) System.out.print(1 + " ");
			else System.out.print(0 + " ");
		}
	}
	//15가지 대결에 대해서 승리,무승부,패패 3가지로 나눠서 깊이우선 탐색을 한다.
	private static void DFS(int index) {
		if(index == fightTeams.size() ) {
			//탐색 결과 대로 결과표를 만든다.
			int[][] resultTable = reflectFightResult();
			//깊이 우선 탐색의 결과가 1~4 예상 결과와 동일한지 확인한다.
			outer : for(int t =1; t<5; t++) {
				if( isPossible[t] ) continue;
				for(int i = 1 ; i <7; i++) {
					for(int j = 1; j <4; j++) {
						if(resultTable[i][j] != expect[t][i][j]) continue outer;
					}
				}
				isPossible[t] = true;
			} 
			return;
		}
		for(int i =1 ; i <4; i++) {
			fightResult[index] = i;
			DFS(index+1);
		}
	}
	private static int[][] reflectFightResult() {
		int [][] reflectResult= new int[7][4];
		for(int i =0; i<fightTeams.size(); i++) {
			Fight fight = fightTeams.get(i);
			switch(fightResult[i]) {
			case WIN:
				reflectResult[fight.team1][WIN]++;
				reflectResult[fight.team2][LOSE]++;
				break;
				
			case DRAW:
				reflectResult[fight.team1][DRAW]++;
				reflectResult[fight.team2][DRAW]++;
				break;
				
			case LOSE:
				reflectResult[fight.team1][LOSE]++;
				reflectResult[fight.team2][WIN]++;
				break;
			}
		}
		
		return reflectResult;
	}
	//6C2의 경우를 구해서 fightTeams에 저장한다.
	private static void combination(int resIndex, int targetIndex) {
		if(resIndex==2) {	//싸울 두 팀을 뽑았으면
			fightTeams.add(new Fight(res[0],res[1]));
			return;
		}
		for(int i = targetIndex; i <=6; i++) {
			res[resIndex] = i;
			combination(resIndex+1,i+1);
		}
	}
		
}
