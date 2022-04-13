package yeongseok.Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 예상 결과가 가능한지 판단하기 위해서 A~F의 승무패가 가능한 조합을 모두 구하는 방법을 생각했다.
 * ex) A => 승:2, 무:2, 패:1 일때
 * 	     승리를 거둔팀, 무승부인팀, 패배한 팀이 각각 어디일지 경우의 수를 구해봤다.
 * 	   5C2 x 3C2 x 1C1
 *     이거를 A~F까지 각각 구해보면서 하나 구할때마다 예상 결과 표와 비교해서 어긋나는 부분이 있는지 확인하려 했음
 *     경우의 수는 최대 30^6(7억)가지 였으나 backtracking 하면 될거라고 무모한 상상을 함..
 *     구현하다 중간에 포기
 * @author 노영석
 */
public class Main_6987_월드컵_포기 {
	static int expect[][];	//예상결과
	static int res[][];	//경우의수 조합해서 나온 결과
	static boolean isPossible;
	static final int WIN = 1, DRAW=2, LOSE=3;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int t = 0 ; t < 4; t++) {
			expect = new int[6+1][3+1];
			st=new StringTokenizer(br.readLine());
			for(int i = 1; i <= 6; i++) {
				for(int j = 1; j <=3; j++) {
					expect[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			isPossible = false;
			inspect();
			
		}
		
	}
	//예상 결과가 가능한 결과인지 검사
	private static void inspect() {
		res = new int[7][4];
		
		dfs(1);
		
	}
	//index 나라의 예상 결과에 맞는 조합을 구성해본다.
	private static void dfs(int index) {
		if(!isPossible) {
			if(index == 7) {	//F나라의 조합까지 완성했다면
				if(isPossible()) isPossible = true;
				return;
			}
			int [] comb = new int[7];//	A~F가 각각 이겼는지 비겼는지 졌는지 기록함.
			combination(5,index,1,comb);
		}
	}
	
	//index == 몇번째 국가에 대해서 조합을 구하고 있는지
	//type == win or draw or lose
	private static void combination(int n, int index, int type, int[] comb) {
		if(type == 4) { //win,draw,lose에대한 조합을 모두 구했다면
			if(reflectComb(comb)) {
				dfs(index+1);
			}
			return;
		}
		NCR(0,expect[index][type],type,comb,index);
		combination(n, index,type+1,comb);
		
	}
	//n개중에 r개를 뽑는 조합
	private static void NCR(int cnt, int r,int type, int[] comb,int index) {
		if(cnt == r) {
			return;
		}
		for(int i = 1; i<7; i++) {
			
		}
	}
	private static boolean reflectComb(int[] comb) {
		for(int i = 1; i <7; i++) {
			int type = comb[i];		//type == win or draw or lose 
			res[i][type]++;
			if(res[i][type] > expect[i][type]) return false;
		}
		return true;
	}
	//res배열과 expect 배열을 1:1비교한다.
	private static boolean isPossible() {
		for(int i = 1; i <7; i++) {
			for(int j = 1; j <4; j++) {
				if(res[i][j] != expect[i][j])return false;
			}
		}
		return true;
	}

}
