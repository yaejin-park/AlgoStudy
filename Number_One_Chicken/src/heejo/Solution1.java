import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ������ �ڸ����
 * 
 * ���Ա��� ���� ���� ����� �� 6����
 * 
 * @author joy96
 *
 */
class Solution1 {

	// ����[6][3]
	static int permutation[][] = {{1, 2, 3}, {1, 3, 2}, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 }, { 3, 2, 1 }};
	static int minLength;
	static int myPlace[];
	static int exit_locate_people[][];
	static int N;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			exit_locate_people = new int[3][2];
			for (int i = 0; i < 3; i++) {
				StringTokenizer str = new StringTokenizer(br.readLine());
				exit_locate_people[i][0] = Integer.parseInt(str.nextToken()) - 1; // ���Ա�i�� ��ġ
				exit_locate_people[i][1] = Integer.parseInt(str.nextToken()); // ���Ա�i�� �����
			}
			minLength = Integer.MAX_VALUE;
			
			// 6���� ����� ���� ���� ����غ���
			for (int i = 0; i < 6; i++) {
				myPlace = new int[N]; // ���ò۵��� �ڸ�
				fill(0, permutation[i]);	//����ϱ�
			}
			
			System.out.println("#" + test_case + " " + minLength);	//����� ���
		}
	}

	public static void fill(int cnt, int turn[]) {
		//��� ���ò۵��� ���ٸ�
		if (cnt == 3) {
			//�Ÿ��� ����ϱ�
			int totalDistance = 0;
			for(int i =0; i<N; i++) {
				if(myPlace[i]>0) {
					//�ش� ��ġ�� ���ò۰� �� ���ò��� ���� ����Ʈ�� �Ÿ� ����Ͽ� ���ϱ�
					totalDistance += Math.abs(exit_locate_people[myPlace[i]-1][0]-i)+1;
				}
			}
			//�ּҰ� ���ϱ�
			minLength = Math.min(minLength, totalDistance);
			return;
		}
		else {
			int gateWhere = exit_locate_people[turn[cnt] - 1][0];	//���� ����Ʈ�� ��� ��ġ�� �ִ���
			int gatePeople = exit_locate_people[turn[cnt] - 1][1];	//���� ����Ʈ�� ����� ��� ��ٸ�����

			int restPeople = gatePeople;	//���� ����Ʈ�� ���� ����� �������
			int i = 0;
			while (true) {
				if (i > N) {
					//[�� ������ ��ٸ�] Ʋ�����̴�.
					return;
				}
				//���ڵ��� ���������� ä�����ٸ� ���� ����Ʈ�� �Ѿ��.
				if (restPeople == 0) {
					fill(cnt + 1, turn);
					return;
				}
				// ���� ������ 1�� ���Ҵµ�, ��Ī���� ä���� �� �ִ� ��Ȳ�̶��
				if ((restPeople == 1)) {
					if ((gateWhere + i < N) && (myPlace[gateWhere + i] == 0) && (gateWhere - i >= 0) && (myPlace[gateWhere - i] == 0)) {
						int tempPlace[] = new int[N];
						for (int j = 0; j < N; j++) {
							tempPlace[j] = myPlace[j];
						}
						//1. �������� ä���� ���� ����Ʈ�� �Ѿ��
						myPlace[gateWhere + i] = turn[cnt];
						fill(cnt + 1, turn);
						
						//�ʱ�ȭ�� ������
						for (int j = 0; j < N; j++) {
							myPlace[j] = tempPlace[j];
						}
						
						//2. ������ ä���� ���� ����Ʈ�� �Ѿ��.
						myPlace[gateWhere - i] = turn[cnt];
						fill(cnt + 1, turn);
						return;
					}
				}
				//�����ʿ� ä�� �� �ִ� ��Ȳ�̶�� ä���
				if ((gateWhere + i < N) && (myPlace[gateWhere + i] == 0)) {
					myPlace[gateWhere + i] = turn[cnt];
					restPeople--;
				}
				
				//���ʿ� ä�� �� �ִ� ��Ȳ�̶�� ä���
				if ((gateWhere - i >= 0) && (myPlace[gateWhere - i] == 0)) {
					myPlace[gateWhere - i] = turn[cnt];
					restPeople--;
				}
				//i ����
				i++;
			}
		}
	}
}