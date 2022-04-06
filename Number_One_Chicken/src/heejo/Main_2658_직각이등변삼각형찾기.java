package group_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/*
 * (주의) 코드가 매우 복잡합니다.
 * 
 * 전반적인 흐름
 * 1. 현재 모양의 꼭지점 세 곳을 찾기.
 *   => 찾지 못하는 경우 삼각형이 아니므로 탈락
 * 2. 세 꼭지점을 기준으로 삼각형 직접 그려보기.
 *   => 기존 지도와 비교해서 다르면 탈락
 * 3. 직각인지 확인
 *   => 피타고라스 정리가 성립되지 않으면 탈락
 * 4. 이등변인지 확인하기
 *   => 세 변 모두 길이가 다르다면 탈락
 * 5. 모두 통과했다면, 꼭지점 세 곳을 출력하기
 * 
 * 아이디어
 * - 직각이등변삼각형이 맞는지를 확인하기
 * - 직각이등변삼각형의 모양은 크게 두가지이다.
 * - 모양에 따라 풀이과정 중 1,2가 다르기 때문에 주의한다.
 * - 모양1. 가장 큰 변이  x,y축과 평행 (type==1)
 *   00010
 *   00110
 *   01110
 *   00110
 *   00010
 * - 모양2. 작은 두 변이 x,y축과 평행 (type==2)
 *   00000
 *   01000
 *   01100
 *   01110
 *   00000
 * 
 * 모양별 세부 풀이과정
 * 모양1-1. 꼭지점 세 곳을 찾기
 *  - 꼭지점을 찾는 기본적인 방식은 꼭지점 기준 상하좌우에 1이 딱 한개만 존재하는 경우이다.
 *  - 그러나 모양1에서는 직각인 부분에서 상하좌우가 1이 두개가 존재한다.
 *  - 이는 나머지 꼭지점 두 곳(a,b), (c,d)을 기준으로 (a,d)나 (b,c)가 꼭지점인지 직접 확인하여 구한다.
 *  
 * 모양2-1. 꼭지점 세 곳을 찾기
 *  - 앞에서 설명한 꼭지점의 기본적인 방식만으로 세 곳을 모두 찾을 수 있다.
 *  
 * 모양 1-2. 꼭지점을 기준으로 삼각형 직접 그려보기
 *  - 모양 1에서 가능한 삼각형은 네 종류다. (튀는 꼭지점이 왼쪽 위, 왼쪽 아래, 오른쪽 위, 오른쪽 아래)
 *  - ex) 튀는 꼭지점이 왼쪽 위라면
 *        00000
 *        01000
 *        01100
 *        01110
 *        01111 
 *  - 이런 경우, 밑변의 길이를 기준으로 길이를 1씩 줄여가며 한칸씩 위로 올라가며 삼각형을 채운다.
 * 
 * 모양 2-2. 꼭지점을 기준으로 삼각형 직접 그려보기
 *  - 모양 2에서 가능한 삼각형은 네 종류다. (직각 꼭지점이 상, 하, 좌, 우)
 *  - ex) 직각 꼭지점이 상이라면
 *        00000
 *        00100
 *        01110
 *        11111
 *        00000
 *  - 이런 경우, 밑변의 길이를 기준으로 길이를 2씩 줄여가며 한칸씩 위로 올라가며 삼각형을 채운다.
 */
public class Main_2658_직각이등변삼각형찾기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean array[][] = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			String str = br.readLine();
			for (int j = 0; j < 10; j++) {
				if (str.charAt(j) == '1') {
					array[i][j] = true;
				} else {
					array[i][j] = false;
				}
			}
		}
		//실행
		calc(array);
	}
	
	//실패하는 경우 이동하는 함수
	public static void fail() {
		System.out.println(0);
	}

	//성공하는 경우 이동하는 함수
	public static void success(int point[][]) {
		// 0,0부터 시작했으나, 문제 기준은 1,1이니 각각 1을 더해준다.
		for (int i = 0; i < 3; i++) {
			System.out.println((point[i][0] + 1) + " " + (point[i][1] + 1));
		}
	}

	//계산
	public static void calc(boolean array[][]) {
		// 삼각형 타입 1(x,y축에 평행한 두 변) or 2(x,y축에 평행한 한 변)
		int type = 0;

		// 이론상으로 꼭지점은 3개
		int point[][] = new int[3][2];	//꼭지점 배열
		int p_count = 0;	//현재 꼭지점 개수

		int dr[] = { -1, 1, 0, 0 };
		int dc[] = { 0, 0, -1, 1 };

		// 조건 1. 꼭지점 3개 찾기
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (array[i][j]) {
					// 상하좌우 중 3군데 이상이 전부 0이거나 배열 밖이라면, 이는 꼭지점이다
					int count = 0;
					for (int k = 0; k < 4; k++) {
						int nextX = i + dr[k];
						int nextY = j + dc[k];
						if ((nextX < 0) || (nextX >= 10) || (nextY < 0) || (nextY >= 10)) {
							count++;
						} else if (array[nextX][nextY] == false) {
							count++;
						}
					}
					//꼭지점 조건이 성립하기 때문에 꼭지점 배열에 추가한다.
					if (count >= 3) {
						//만약 꼭지점이 발견됐는데 꼭지점 개수가 이미 3개라면 탈락
						if (p_count >= 3) {
							fail();
							return;
						}
						point[p_count][0] = i;
						point[p_count][1] = j;
						p_count++;
						//꼭지점 개수가 3개가 됐다면, 이는 타입2 삼각형일 가능성이 높다.
						if (p_count == 3) {
							type = 2;
						}
					}
				}
			}
		}
		// 만약 구해진 꼭지점이 두개라면 이는 타입1 삼각형일 가능성이 높다. 해당 교차지점 두 곳을 보자.
		if (p_count == 2) {
			type = 1;
			//예상1: 기존꼭지점1의 x좌표, 기존꼭지점2의 y좌표에 있는 곳이 꼭지점일 것이다
			if (array[point[0][0]][point[1][1]]) {
				int count = 0;
				//상하좌우 탐방
				for (int k = 0; k < 4; k++) {
					int nextX = point[0][0] + dr[k];
					int nextY = point[1][1] + dc[k];
					if ((nextX < 0) || (nextX >= 10) || (nextY < 0) || (nextY >= 10)) {
						count++;
					} else if (array[nextX][nextY] == false) {
						count++;
					}
				}
				//카운트가 2 이상이라면 (2가 나오는게 정상이긴 하다)
				if (count >= 2) {
					point[p_count][0] = point[0][0];
					point[p_count][1] = point[1][1];
					p_count++;
				}
			}
			//예상2: 기존꼭지점1의 y좌표, 기존꼭지점2의 x좌표에 있는 곳이 꼭지점일 것이다
			else if (array[point[1][0]][point[0][1]]) {
				int count = 0;
				for (int k = 0; k < 4; k++) {
					int nextX = point[1][0] + dr[k];
					int nextY = point[0][1] + dc[k];
					if ((nextX < 0) || (nextX >= 10) || (nextY < 0) || (nextY >= 10)) {
						count++;
					} else if (array[nextX][nextY] == false) {
						count++;
					}
				}
				//카운트가 2 이상이라면 (2가 나오는게 정상이긴 하다)
				if (count >= 2) {
					point[p_count][0] = point[1][0];
					point[p_count][1] = point[0][1];
					p_count++;
				}
			}

			// 재정렬. 꼭지점 배열은 애초에 문제 결과에서 요구하는 배열 순서로 정렬되도록 한다.
			Arrays.sort(point, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[0] == o2[0]) {
						return o1[1] - o2[1];
					} else {
						return o1[0] - o2[0];
					}
				}
			});
		}
		//그래도 여전히 카운트 개수가 3개가 아니라면 탈락
		if (p_count != 3) {
			fail();
			return;
		}
		
		// 조건 2. 데이터 값이 삼각형 모양 안에 정확히 채워져있는지 확인
		boolean tempCheck[][] = new boolean[10][10];
		if (type == 1) {
			int direction = 0; // 세로로 같다면 1, 가로로 같다면 2
			int sameVal = 0;
			int difVal = 0;
			if (point[0][0] == point[1][0]) {
				if (point[0][1] == point[2][1]) {
					// 남은 꼭지점은 좌측 아래
					int length = point[1][1] - point[0][1];
					for (int i = point[0][0]; i <= point[2][0]; i++) {
						for (int j = 0; j <= length; j++) {
							tempCheck[i][point[0][1] + j] = true;
						}
						length -= 1;
					}
				} else {
					// 남은 꼭지점은 우측 아래
					int length = point[1][1] - point[0][1];
					for (int i = point[0][0]; i <= point[2][0]; i++) {
						for (int j = 0; j <= length; j++) {
							tempCheck[i][point[1][1] - j] = true;
						}
						length -= 1;
					}
				}
			} else if (point[1][0] == point[2][0]) {
				if (point[0][1] == point[1][1]) {
					// 남은 꼭지점은 좌측 위
					int length = point[2][1] - point[1][1];
					for (int i = point[2][0]; i >= point[0][0]; i--) {
						for (int j = 0; j <= length; j++) {
							tempCheck[i][point[1][1] + j] = true;
						}
						length -= 1;
					}
				} else {
					// 남은 꼭지점은 우측 위
					int length = point[2][1] - point[1][1];
					for (int i = point[2][0]; i >= point[0][0]; i--) {
						for (int j = 0; j <= length; j++) {
							tempCheck[i][point[2][1] - j] = true;
						}
						length -= 1;
					}
				}
			}
		} else if (type == 2) {
			int direction = 0; // 세로로 같다면 1, 가로로 같다면 2
			int sameVal = 0;
			int difVal = 0;
			// 세로로 같은지 가로로 같은지 확인
			if (point[0][1] == point[1][1]) {
				//세로로 같으며 튀는 꼭지점이 오른쪽에 있다.
				direction = 1;
				sameVal = 0;
				difVal = 2;
			} else if (point[0][1] == point[2][1]) {
				//세로로 같으며 튀는 꼭지점이 왼쪽에 있다.
				direction = 1;
				sameVal = 0;
				difVal = 1;
			} else if (point[0][0] == point[1][0]) {
				//가로로 같으며 튀는 꼭지점이 아래쪽에 있다.
				direction = 2;
				sameVal = 0;
				difVal = 2;
			} else if (point[1][0] == point[2][0]) {
				//가로로 같으며 튀는 꼭지점이 위쪽에 있다.
				direction = 2;
				sameVal = 1;
				difVal = 0;
			}
			// 세로로 같다면
			if (direction == 1) {
				// 좌에서 우로 탐방하는가?
				if (point[sameVal][1] < point[difVal][1]) {
					int length = Math.abs(point[sameVal][0] - point[3 - sameVal - difVal][0]);
					for (int i = point[sameVal][1]; i <= point[difVal][1]; i++) {
						for (int j = 0; j <= length; j++) {
							tempCheck[(i - point[sameVal][1]) + j
									+ Math.min(point[sameVal][0], point[3 - sameVal - difVal][0])][i] = true;
						}
						length -= 2;
					}
				}
				// 우에서 좌로 탐방하는가?
				else {
					int length = Math.abs(point[sameVal][0] - point[3 - sameVal - difVal][0]);
					for (int i = point[sameVal][1]; i >= point[difVal][1]; i--) {
						for (int j = 0; j <= length; j++) {
							tempCheck[(point[sameVal][1] - i) + j
									+ Math.min(point[sameVal][0], point[3 - sameVal - difVal][0])][i] = true;
						}
						length -= 2;
					}
				}
			}
			// 가로로 같다면
			else if (direction == 2) {
				// 위에서 아래로 탐방하는가?
				if (point[sameVal][0] < point[difVal][0]) {
					int length = Math.abs(point[sameVal][1] - point[3 - sameVal - difVal][1]);
					for (int i = point[sameVal][0]; i <= point[difVal][0]; i++) {
						for (int j = 0; j <= length; j++) {
							tempCheck[i][(i - point[sameVal][0]) + j
									+ Math.min(point[sameVal][1], point[3 - sameVal - difVal][1])] = true;
						}
						length -= 2;
					}
				}
				// 아래에서 위로 탐방하는가?
				else {
					int length = Math.abs(point[sameVal][1] - point[3 - sameVal - difVal][1]);
					for (int i = point[sameVal][0]; i >= point[difVal][0]; i--) {
						for (int j = 0; j <= length; j++) {
							tempCheck[i][(point[sameVal][0] - i) + j
									+ Math.min(point[sameVal][1], point[3 - sameVal - difVal][1])] = true;
						}
						length -= 2;
					}
				}
			}
			//direction이 정해지지 않았다면 탈락
			else {
				fail();
				return;
			}
		}
		//type이 정해지지 않았다면 탈락
		else {
			fail();
			return;
		}
		//기존 그림과 방금 만든 삼각형 그림 비교
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				//한 부분이라도 다르면 탈락
				if (tempCheck[i][j] != array[i][j]) {
					fail();
					return;
				}
			}
		}

		double lengthA = Math.pow((point[0][0] - point[1][0]), 2) + Math.pow((point[0][1] - point[1][1]), 2);
		double lengthB = Math.pow((point[1][0] - point[2][0]), 2) + Math.pow((point[1][1] - point[2][1]), 2);
		double lengthC = Math.pow((point[0][0] - point[2][0]), 2) + Math.pow((point[0][1] - point[2][1]), 2);

		// 조건3. 직각인지 확인. 피타고라스 정리가 성립하지 않으면 탈락
		if ((lengthC > lengthA) && (lengthC > lengthB)) {
			//C가 제일 긴 변인 경우
			if (lengthC != lengthA + lengthB) {
				fail();
				return;
			}
		}
		if ((lengthB > lengthA) && (lengthB > lengthC)) {
			//B가 제일 긴 변인 경우
			if (lengthB != lengthA + lengthC) {
				fail();
				return;
			}
		}
		if ((lengthA > lengthC) && (lengthA > lengthB)) {
			//A가 제일 긴 변인 경우
			if (lengthA != lengthC + lengthB) {
				fail();
				return;
			}
		}

		// 조건4. 이등변인지 확인
		if ((lengthC != lengthA) && (lengthB != lengthC) && (lengthA != lengthB)) {
			//모든 변의 길이가 다 다르다면 탈락
			fail();
			return;
		}

		// 여기까지 왔다면 직각이등변삼각형이 맞다. 결과 출력하러 가기
		success(point);
		return;
	}
}
