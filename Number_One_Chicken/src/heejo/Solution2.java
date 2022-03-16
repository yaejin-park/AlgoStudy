import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 몬스터를 처치하고 고객에게 확인시켜주는 과정이 필요
 * 몬스터 개수는 4이하
 * 몬스터(1,2,3,4),고객(5,6,7,8)로 순열을 만들어보자
 * 이 때, 고객이 몬스터보다 앞에 올 수 없다.
 * @author joy96
 *
 */
public class Solution2 {
	static int N;
	static int selected[];
	static boolean isSelected[];
	static int map[][];
	static int M;
	static int monsterAndCustomer[][];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			M = 0;
			for(int i =0; i<N; i++) {
				StringTokenizer str= new StringTokenizer(br.readLine());
				for(int j =0; j<N; j++) {
					map[i][j] = Integer.parseInt(str.nextToken());
					if(map[i][j]!=0) {
						M++;
					}
				}
			}
			monsterAndCustomer = new int [M][2];
			for(int i =0; i<N; i++) {
				for(int j =0; j<N; j++) {
					//몬스터라면
					if(map[i][j]>0) {
						monsterAndCustomer[map[i][j]-1][0] = i;
						monsterAndCustomer[map[i][j]-1][1] = j;
					}
					//고객이라면
					else if(map[i][j]<0) {
						monsterAndCustomer[(Math.abs(map[i][j])-1)+(M/2)][0] = i;
						monsterAndCustomer[(Math.abs(map[i][j])-1)+(M/2)][1] = j;
					}
				}
			}
			isSelected = new boolean[M];
			selected = new int[M];
			minDistance = Integer.MAX_VALUE;
			permutation(0);
			System.out.println("#" + test_case + " " + minDistance);
		}
	}
	static int minDistance;
	public static void permutation(int cnt) {
		if(cnt==M) {
			int distance = monsterAndCustomer[selected[0]][0] + monsterAndCustomer[selected[0]][1];
			for(int i =1; i<M; i++) {
				distance += Math.abs(monsterAndCustomer[selected[i]][0]-monsterAndCustomer[selected[i-1]][0])
						+Math.abs(monsterAndCustomer[selected[i]][1]-monsterAndCustomer[selected[i-1]][1]);
			}
			minDistance = Math.min(minDistance, distance);
			return;
		}
		else {
			for(int i = 0; i<M; i++) {
				//해당 i 이미 들어갔으면 패스
				if(isSelected[i]) {
					continue;
				}
				else {
					//고객인데
					if(i>=(M/2)) {
						//몬스터 처리를 안했으면 패스
						if(!isSelected[i%(M/2)]) {
							continue;
						}
						//몬스터 처리를 했으면 가능
					}
					//몬스터면 가능
					selected[cnt] = i;
					isSelected[i] = true;
					permutation(cnt+1);
					isSelected[i] = false;
				}
			}
		}
	}
}
