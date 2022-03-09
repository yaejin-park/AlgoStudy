package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 적록색약 백준 10026 골드5
 * 
 * 목표 : 적록색약이 아닌 사람이 보는 그림의 구역 수와 적록색약인 사람이 보는 그림의 구역 수를 출력 (적록색약 : 빨강과 초록의 차이를
 * 못느낀다)
 * 
 * 풀이 방법 1. 배열을 지나가며 해당하는 자리에 색이 있는지 확인 2. 주변이 같은색인지 확인 3. 이 때, 확인된 곳은 체크처리를
 * 따로하여 다음 1번탐색에 중복확인 안되게 하기
 * 
 * @author joy96
 *
 */
public class Main_10026 {
	static int N;
	static char arr[][];
	static boolean isSelected[][];
	static int normal_count;
	static int color_blindness_count;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = (in.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		//적록색약x 기준에서 그림 구역 수 구하기
		isSelected = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!isSelected[i][j]) {
					//그림을 찾으면 구역 카운트 +1 하고 구역 그림들 찾아서 체크하기
					normal_count++;
					search(i,j,arr[i][j],false);
				}
			}
		}
		
		//적록색약o 기준에서 그림 구역 수 구하기
		isSelected = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!isSelected[i][j]) {
					//그림을 찾으면 구역 카운트 +1 하고 구역 그림들 찾아서 체크하기
					color_blindness_count++;
					search(i,j,arr[i][j],true);
				}
			}
		}
		
		//출력
		System.out.println(normal_count + " " + color_blindness_count);
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void search(int x, int y, char color, boolean blind) {
		if (isSelected[x][y]) {	//이미 탐색한 그림이라면 종료
			return;
		}
		//탐색 시작
		else {
			isSelected[x][y] = true;	//탐색했다는 체크하기
			// 상, 하, 좌, 우 순으로 탐색
			for (int i = 0; i < 4; i++) {
				int tempX = x + dr[i];
				int tempY = y + dc[i];
				//배열 범위 안에 들어온 상태에서
				if ((tempX >= 0) && (tempX < N) && (tempY >= 0) && (tempY < N)) {
					//적록색약이라면
					if (blind) {
						//블루만 구별 가능
						if (color == 'B') {
							if (color == arr[tempX][tempY]) {
								search(tempX, tempY, arr[tempX][tempY], blind);
							}
						}
						//나머지는 R이나 G을 같게 취급한다.
						else {
							if((arr[tempX][tempY]=='R')||(arr[tempX][tempY]=='G')) {
								search(tempX, tempY, arr[tempX][tempY], blind);
							}
						}
					}
					//적록색약 아니라면
					else {
						//모든 컬러 구별 가능
						if (color == arr[tempX][tempY]) {
							search(tempX, tempY, arr[tempX][tempY], blind);
						}
					}
				}
			}
		}
	}
}
