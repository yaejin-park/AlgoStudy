import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ���͸� óġ�ϰ� ������ Ȯ�ν����ִ� ������ �ʿ�
 * ���� ������ 4����
 * ����(1,2,3,4),��(5,6,7,8)�� ������ ������
 * �� ��, ���� ���ͺ��� �տ� �� �� ����.
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
					//���Ͷ��
					if(map[i][j]>0) {
						monsterAndCustomer[map[i][j]-1][0] = i;
						monsterAndCustomer[map[i][j]-1][1] = j;
					}
					//���̶��
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
//			System.out.print(Arrays.toString(selected));
			int distance = monsterAndCustomer[selected[0]][0] + monsterAndCustomer[selected[0]][1];
			for(int i =1; i<M; i++) {
				distance += Math.abs(monsterAndCustomer[selected[i]][0]-monsterAndCustomer[selected[i-1]][0])
						+Math.abs(monsterAndCustomer[selected[i]][1]-monsterAndCustomer[selected[i-1]][1]);
			}
//			System.out.println(" : " + distance);
			minDistance = Math.min(minDistance, distance);
			return;
		}
		else {
			for(int i = 0; i<M; i++) {
				//�ش� i �̹� ������ �н�
				if(isSelected[i]) {
					continue;
				}
				else {
					//���ε�
					if(i>=(M/2)) {
						//���� ó���� �������� �н�
						if(!isSelected[i%(M/2)]) {
							continue;
						}
						//���� ó���� ������ ����
					}
					//���͸� ����
					selected[cnt] = i;
					isSelected[i] = true;
					permutation(cnt+1);
					isSelected[i] = false;
				}
			}
		}
	}
}
