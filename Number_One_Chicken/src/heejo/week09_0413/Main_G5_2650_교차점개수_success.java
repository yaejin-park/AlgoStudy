package heejo.week09_0413;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 최선 : 모든 경우의 수에 따른 계산 방법을 "전부" 적기
 * 풀이순서
 * 1. 모든 입력값 받기 (이때, 두 점을 받는 과정에서는 오름차순으로 정렬하여 받기)
 * 2. 경우의 수를 나누면서 한 선분과 나머지 선분들을 비교하기
 * 3. 결과 출력
 * 
 * 크게 나눠보는 경우의 수
 * - 기준선이 한 쪽에 완전히 붙어있는 경우
 * 		- 비교선도 같은 쪽에 완전히 붙어있는 경우
 * 		- 비교선 중 하나의 점만 같은 쪽에 붙어있는 경우
 *  	- 비교선이 기준선과 떨어져있는 경우
 * - 비교선이 한 쪽에 완전히 붙어있는 경우
 *  	- 기준선 중 하나의 점만 같은 쪽에 붙어있는 경우
 *  	- 기준선이 비교선과 떨어져있는 경우
 * - 나머지 경우들은 각각을 따져보며 비교
 */
public class Main_G5_2650_교차점개수_success {
	static int width_length; // 사각형 가로의 길이
	static int height_length; // 사각형 세로의 길이
	static int count_per_line;
	static boolean print = false;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()) / 2;
		int array[][] = new int[N][4]; // 입력받는 데이터 정보
		// 모든 선을 등록해두고
		for (int i = 0; i < N; i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(str.nextToken());
			int b = Integer.parseInt(str.nextToken());
			int c = Integer.parseInt(str.nextToken());
			int d = Integer.parseInt(str.nextToken());
			if (a <= 2) {
				width_length = Math.max(width_length, b);
			} else {
				height_length = Math.max(height_length, b);
			}
			if (c <= 2) {
				width_length = Math.max(width_length, d);
			} else {
				height_length = Math.max(height_length, d);
			}

			if (a < c) {
				array[i][0] = a;
				array[i][1] = b;
				array[i][2] = c;
				array[i][3] = d;
			} else if (a > c) {
				array[i][0] = c;
				array[i][1] = d;
				array[i][2] = a;
				array[i][3] = b;
			} else {
				if (b < d) {
					array[i][0] = a;
					array[i][1] = b;
					array[i][2] = c;
					array[i][3] = d;
				} else {
					array[i][0] = c;
					array[i][1] = d;
					array[i][2] = a;
					array[i][3] = b;
				}
			}
		}
		width_length += 1;
		height_length += 1;
		int total_count = 0;
		int max_count_line = 0;
		if(print) {
		System.out.println("====");
		for(int i =0; i<N; i++) {
			for(int j =0; j<4; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("====");
		}
		for (int i = 0; i < N; i++) {
			count_per_line = 0;
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}
				if(print) {
				System.out.print(i + "와" + j + "는 : " +count_per_line);
				}
				// 기준선의 위치가 같은 상황이면
				if (array[i][0] == array[i][2]) {

					// 비교선의 위치가 같은 변일 때
					if ((array[j][0] == array[j][2]) && (array[j][0] == array[i][0])) {
						int Amin = Math.min(array[i][1], array[i][3]);
						int Amax = Math.max(array[i][1], array[i][3]);
						int Bmin = Math.min(array[j][1], array[j][3]);
						int Bmax = Math.max(array[j][1], array[j][3]);
						if (Bmax > Amax) {
							if ((Bmin > Amin) && (Bmin < Amax)) {
								count_per_line++;
							}
						} else {
							if ((Bmin < Amin) && (Bmax > Amin)) {
								count_per_line++;
							}
						}
					}
					// 비교선의 점 하나만 같은 변일 때
					else if (array[j][0] == array[i][0]) {
						if ((array[i][1] < array[j][1]) && (array[j][1] < array[i][3])) {
							count_per_line++;
						} else if ((array[i][3] < array[j][1]) && (array[j][1] < array[i][1])) {
							count_per_line++;
						}
					} else if (array[j][2] == array[i][0]) {
						if ((array[i][1] < array[j][3]) && (array[j][3] < array[i][3])) {
							count_per_line++;
						} else if ((array[i][3] < array[j][3]) && (array[j][3] < array[i][1])) {
							count_per_line++;
						}
					}
					// 비교선이랑 같은 점이 없다면
					else {
						// 패스
					}
				}
				// 비교선의 위치가 같은 상황이면
				else if (array[j][0] == array[j][2]) {

					// 비교선의 점 하나만 같은 변일 때
					if (array[i][0] == array[j][0]) {
						if ((array[j][1] < array[i][1]) && (array[i][1] < array[j][3])) {
							count_per_line++;
						} else if ((array[j][3] < array[i][1]) && (array[i][1] < array[j][1])) {
							count_per_line++;
						}
					} else if (array[i][2] == array[j][0]) {
						if ((array[j][1] < array[i][3]) && (array[i][3] < array[j][3])) {
							count_per_line++;
						} else if ((array[j][3] < array[i][3]) && (array[i][3] < array[j][1])) {
							count_per_line++;
						}
					}
					// 비교선이랑 같은 점이 없다면
					else {
						// 패스
					}
				}
				// 이제 중복되는 선분 없음
				else {
					swit(array[i], array[j]);
					swit(array[j], array[i]);
					swit2(array[j], array[i]);
				}
				if(print) {
				System.out.println("==> " +count_per_line);
				}
			}
			total_count += count_per_line;
			max_count_line = Math.max(count_per_line, max_count_line);
		}
		System.out.println(total_count/2);
		System.out.println(max_count_line);
	}
	public static void swit2(int array1[], int array2[]) {
		int category = array1[0] * 1000 + array1[2] * 100 + array2[0] * 10 + array2[2];
	
		switch (category) {
		// 1,2 1,2 12: 1 차이와 2차이 곱한게 음수가 나와야함
		case 1212:
			if((array1[1]-array2[1])*(array1[3]-array2[3])<0) {
				count_per_line++;
			}
			break;
		
		case 1313:
			if((array1[1]-array2[1])*(array1[3]-array2[3])<0) {
				count_per_line++;
			}
			break;
		
		// 1,4 1,4 14: 1차이와 4차이 곱한게 양수가 나와야함
		case 1414:
			if((array1[1]-array2[1])*(array1[3]-array2[3])>0) {
				count_per_line++;
			}
			break;
		
		// 2,3 2,3 :2차이와 3차이 곱한게 양수가 나와야함
		case 2323:
			if((array1[1]-array2[1])*(array1[3]-array2[3])>0) {
				count_per_line++;
			}
			break;
		
		// 2,4 2,4 : 2차이와 4차이 곱한게 음수가 나와야함
		case 2424:
			if((array1[1]-array2[1])*(array1[3]-array2[3])<0) {
				count_per_line++;
			}
			break;
		
		// 3,4 3,4
		case 3434:
			if((array1[1]-array2[1])*(array1[3]-array2[3])<0) {
				count_per_line++;
			}
			break;
		}
	}
	public static void swit(int array1[], int array2[]) {
		int category = array1[0] * 1000 + array1[2] * 100 + array2[0] * 10 + array2[2];
	
		switch (category) {
		// 1,2 1,3 1: 1,2가 1,3보다 1이 작아야함
		case 1213:
			if(array1[1]<array2[1]) {
				count_per_line++;
			}
			break;
		// 1,2 1,4 1: 1,2가 1,4보다 1이 커야함
		case 1214:
			if(array1[1]>array2[1]) {
				count_per_line++;
			}
			break;
		// 1,2 2,3 2: 1,2가 2,3보다 2가 작아야함
		case 1223:
			if(array1[3]<array2[1]) {
				count_per_line++;
			}
			break;
		// 1,2 2,4 2: 1,2가 2,4보다 2가 커야함
		case 1224:
			if(array1[3]>array2[1]) {
				count_per_line++;
			}
			break;
		// 1,2 3,4 항상O
		case 1234:
			count_per_line++;
			break;

		// 1,3 1,4 1: 1,3가 1,4보다 1이 커야함
		case 1314:
			if(array1[1]>array2[1]) {
				count_per_line++;
			}
			break;
		// 1,3 2,3 3: 1,3가 2,3보다 3이 커야함
		case 1323:
			if(array1[3]>array2[3]) {
				count_per_line++;
			}
			break;
		// 1,3 2,4 항상X
		case 1324:
			break;
		// 1,3 3,4 3: 1,3가 3,4보다 3이 커야함
		case 1334:
			if(array1[3]>array2[1]) {
				count_per_line++;
			}
			break;

		// 1,4 2,3 항상X
		case 1423:
			break;
		// 1,4 2,4 4: 1,4가 2,4보다 4가 커야함
		case 1424:
			if(array1[3]>array2[3]) {
				count_per_line++;
			}
			break;
		// 1,4 3,4 4: 1,4가 3,4보다 4가 커야함
		case 1434:
			if(array1[3]>array2[3]) {
				count_per_line++;
			}
			break;

		// 2,3 2,4 2: 2,3가 2,4보다 2가 커야함
		case 2324:
			if(array1[1]>array2[1]) {
				count_per_line++;
			}
			break;
		// 2,3 3,4 3: 2,3가 3,4보다 3이 작아야함
		case 2334:
			if(array1[3]<array2[1]) {
				count_per_line++;
			}
			break;
		
		// 2,4 3,4 4: 2,4가 3,4보다 4가 작아야함
		case 2434:
			if(array1[3]<array2[3]) {
				count_per_line++;
			}
			break;
		}
	}
}
