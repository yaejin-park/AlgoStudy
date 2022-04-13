package heejo.week09_0413;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 생각했던 방법
 * - 두 점을 이용하여 일차함수를 만들자.
 * - 두 일차함수의 교차지점이 사각형 범위 내에 있는지를 판단하기
 * 바로 실패(ㅋㅋㅋㅋㅋㅋㅋ)
 * 
 * 오답노트
 * - 1. 한 변에 붙어있는 선분에 대한 교차 범위 제한
 * - 2. 한 변에 두 선분이 존재하는 경우에 대한 계산값(1-4, 2-3은 안만나고 1-3,2-4는 만나고)
 * - 3. 일대일대응이 되는 보장이 없다. (x=3인 직선) => 이런 경우에는 slope를 100으로 제한하며 특수 계산
 * - 4. 정올에서 딱 한개만 틀렸다. 그런데 점이 50개인 데이터.... 디버깅 포기 
50
2 22 3 41 
1 42 3 6 
4 3 4 22 
4 19 4 7 
3 39 1 9 
4 27 3 46 
1 5 1 8 
4 17 3 16 
3 5 4 8 
4 30 2 24 
3 8 4 1 
1 26 3 42 
1 6 2 6 
4 34 2 36 
3 1 3 27 
2 3 4 18 
4 50 4 9 
2 26 1 3 
2 43 1 2 
2 41 2 35 
4 10 3 32 
3 12 4 13 
2 9 1 16 
1 46 3 23 
4 38 3 48 
 */

public class Main_G5_2650_교차점개수_fail {
	static int width_length; // 사각형 가로의 길이
	static int height_length; // 사각형 세로의 길이

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()) / 2;
		int array[][] = new int[N][4]; // 입력받는 데이터 정보
		int coordinate[][] = new int[N][4]; // 좌표로 변환한 점들의 정보. 점1의 X좌표, 점1의 Y좌표, 점2의 X좌표, 점2의 Y좌표
		// 모든 선을 등록해두고
		for (int i = 0; i < N; i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				array[i][(j * 2) + 0] = Integer.parseInt(str.nextToken());
				array[i][(j * 2) + 1] = Integer.parseInt(str.nextToken());
				if (array[i][(j * 2) + 0] <= 2) { // 가로
					width_length = Math.max(width_length, array[i][(j * 2) + 1]);
				} else { // 세로
					height_length = Math.max(height_length, array[i][(j * 2) + 1]);
				}
			}
		}
		width_length += 1;
		height_length += 1;

		// 점들의 정보를 좌표로 변경하자
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				int temp_location = array[i][(j * 2) + 0];
				int temp_distance = array[i][(j * 2) + 1];
				switch (temp_location) {
				case 1: // 윗변
					coordinate[i][(j * 2) + 0] = temp_distance; // X좌표
					coordinate[i][(j * 2) + 1] = height_length; // Y좌표
					break;
				case 2: // 밑변
					coordinate[i][(j * 2) + 0] = temp_distance; // X좌표
					coordinate[i][(j * 2) + 1] = 0; // Y좌표
					break;
				case 3: // 왼쪽 변
					coordinate[i][(j * 2) + 0] = 0; // X좌표
					coordinate[i][(j * 2) + 1] = height_length - temp_distance; // Y좌표
					break;
				case 4: // 오른쪽 변
					coordinate[i][(j * 2) + 0] = width_length; // X좌표
					coordinate[i][(j * 2) + 1] = height_length - temp_distance; // Y좌표
					break;
				}
			}
		}

//		for(int i = 0; i<N; i++) {
//			System.out.print(coordinate[i][0] + " ");			
//			System.out.print(coordinate[i][1] + " ");			
//			System.out.print(coordinate[i][2] + " ");			
//			System.out.print(coordinate[i][3] + " ");
//			System.out.println();
//		}
		int total_count = 0;
		int max_count_per_line = 0;
		// 선1부터 마지막선까지 하나씩 뽑아서(selected_line) 실행
		for (int selected_line = 0; selected_line < N; selected_line++) {
			// 기준이 될 선의 일차함수 계산
			double line_s[] = makeLinearFunction(coordinate[selected_line][0], coordinate[selected_line][1],
					coordinate[selected_line][2], coordinate[selected_line][3]);
			int count_per_line = 0;
			for (int compare_line = 0; compare_line < N; compare_line++) {
				// 두 선이 같다면 패스
				if (selected_line == compare_line) {
					continue;
				}
//				System.out.println(selected_line + "-> " + compare_line);
				// 비교할 선의 일차함수 계산
				double line_c[] = makeLinearFunction(coordinate[compare_line][0], coordinate[compare_line][1],
						coordinate[compare_line][2], coordinate[compare_line][3]);

				//혹시 두 선분 중에 사각형 테두리에 붙은 선분이 있다면, 따로 계산을 해준다.
				if (parallel_to_side(coordinate[selected_line], coordinate[compare_line])) {
					//기준선도 평행한데 비교선도 평행한 경우
					if(calc_parallel_two(coordinate[selected_line], coordinate[compare_line])) {
						//만약 한 선이 한 선의 내부에 완벽하게 들어간다면, 이는 카운트하지 않으므로 continue
//						System.out.println("  [X]선이 한 선의 내부에 들어감");
						continue;
					}
					//기준선이 평행한데 비교선과 겹치는 경우
					if (calc_parallel_to_side(coordinate[selected_line], coordinate[compare_line])) {
//						System.out.println("  [O]기준선 평행, 비교선 겹침");
						count_per_line++;
					}
					//비교선이 평행한데 기준선과 겹치는 경우
					else if (calc_parallel_to_side(coordinate[compare_line], coordinate[selected_line])) {
//						System.out.println("  [O]비교선 평행, 기준선 겹침");
						count_per_line++;
					}
					//평행한데, 겹치지 않으면 패스
					else {
//						System.out.println("  [X]평행한데 겹치지 않음");
						continue;
					}
				}
				//평행한 선분이 없다면, 두 선의 교차점이 사각형 범위 내에 있는지 확인한다.
				else {
					// 두 선이 교차한다면
					if (compareTwoLinearFunction(line_s, line_c)) {
//						System.out.println("  [O]두선 교차");
						count_per_line++;
						continue;
					}
//					System.out.println("  [X]교차 안해");
				}
			}

			// 교차점 개수 갱신 (가장 많은 교차점을 갖는 연결선의 교차점 개수를 찾기 위함)
			max_count_per_line = Math.max(max_count_per_line, count_per_line);
			total_count += count_per_line;
//			System.out.println(selected_line + " : " + count_per_line);
		}
		// 마지막에 count를 2로 나눈다.
		total_count /= 2;

		// 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		sb.append(total_count + "\n");
		sb.append(max_count_per_line + "\n");
		bw.write(sb + "");
		bw.flush();
		bw.close();
	}

	public static boolean parallel_to_side(int A[], int B[]) {
		if (A[0] == A[2]) { // 선분A의 두 점의 x좌표가 같다면
			if((A[0]==0)||(A[0]==width_length)) {
				return true;				
			}
		}
		if (A[1] == A[3]) { // 선분A의 두 점의 y좌표가 같다면
			if((A[1]==0)||(A[1]==height_length)) {
				return true;				
			}
		}
		if (B[0] == B[2]) { // 선분B의 두 점의 x좌표가 같다면
			if((B[0]==0)||(B[0]==width_length)) {
				return true;				
			}
		}
		if (B[1] == B[3]) { // 선분B의 두 점의 y좌표가 같다면
			if((B[1]==0)||(B[1]==height_length)) {
				return true;				
			}
		}
		return false;
	}

	public static boolean calc_parallel_two(int A[], int B[]) {
		//A의 점1,점2의 x좌표와  B의 점1,점2의 x좌표가 같은 경우
		if((A[0]==A[2])&&(B[0]==B[2])&&(A[0]==B[0])) {
			int Amin = Math.min(A[1], A[3]);
			int Amax = Math.max(A[1], A[3]);
			int Bmin = Math.min(B[1], B[3]);
			int Bmax = Math.max(B[1], B[3]);
			
			//A의 두점의 y좌표가 B의 두 점의 y좌표 사이에 있다면 true
			if((Bmin<Amin)&&(Amax<Bmax)) {
				return true;
			}
			//B의 두 점의 y좌표가 A의 두 점의 y좌표 사이에 있다면 true
			if((Amin<Bmin)&&(Bmax<Amax)) {
				return true;
			}
		}
		//A의 점1,점2의 y좌표와  B의 점1,점2의 y좌표가 같은 경우
		if((A[1]==A[3])&&(B[1]==B[3])&&(A[1]==B[1])) {
			int Amin = Math.min(A[0], A[2]);
			int Amax = Math.max(A[0], A[2]);
			int Bmin = Math.min(B[0], B[2]);
			int Bmax = Math.max(B[0], B[2]);
			
			//A의 두점의 y좌표가 B의 두 점의 y좌표 사이에 있다면 true
			if((Bmin<Amin)&&(Amax<Bmax)) {
				return true;
			}
			//B의 두 점의 y좌표가 A의 두 점의 y좌표 사이에 있다면 true
			if((Amin<Bmin)&&(Bmax<Amax)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean calc_parallel_to_side(int A[], int B[]) {
		// 선분 A의 x좌표가 같은 경우
		if (A[0] == A[2]) {
			// 선분 A의 x가 0도 아니고 widthlength도 아니라면
			if((A[0]!=0)&&(A[0]!=width_length)) {
				
			}
			// 선분 B가 선분 A의 y범위 안에 들어가야한다.
			if (B[0] == A[0]) {
				// B의 점1이 해당 좌표에 있는 경우
				if ((B[1] >= A[1]) && (B[1] <= A[3])) {
					// 만약 B가 선분A의 y값들 사이에 있다면 true
					return true;
				}
				else if ((B[1] <= A[1]) && (B[1] >= A[3])) {
					// 만약 B가 선분A의 y값들 사이에 있다면 true
					return true;
				}
			}
			else if (B[2] == A[0]) {
				// B의 점1이 해당 좌표에 있는 경우
				if ((B[3] >= A[1]) && (B[3] <= A[3])) {
					// 만약 B가 선분A의 y값들 사이에 있다면 true
					return true;
				}
				else if ((B[3] <= A[1]) && (B[3] >= A[3])) {
					// 만약 B가 선분A의 y값들 사이에 있다면 true
					return true;
				}
			}
		}
		
		// 선분 A의 y좌표가 같은 경우
		else if (A[1] == A[3]) {
				// 선분 B가 선분 A의 x범위 안에 들어가야한다.
				if (B[1] == A[1]) {
					// B의 점1이 해당 좌표에 있는 경우
					if ((B[0] >= A[0]) && (B[0] <= A[2])) {
						// 만약 B가 선분A의 x값들 사이에 있다면 true
						return true;
					}
					else if ((B[0] <= A[0]) && (B[0] >= A[2])) {
						// 만약 B가 선분A의 x값들 사이에 있다면 true
						return true;
					}
				}
				else if (B[3] == A[1]) {
					// B의 점1이 해당 좌표에 있는 경우
					if ((B[2] >= A[0]) && (B[2] <= A[2])) {
						// 만약 B가 선분A의 x값들 사이에 있다면 true
						return true;
					}
					else if ((B[2] <= A[0]) && (B[2] >= A[2])) {
						// 만약 B가 선분A의 x값들 사이에 있다면 true
						return true;
					}
				}
			}
		return false;
	}

	// 두 점을 이용하여 선분 만들기
	public static double[] makeLinearFunction(int x1, int y1, int x2, int y2) {
		double slope = 0;
		double y = 0;
		if(x2-x1==0) {
			slope = 100;
			y=x1;
		}
		else {			
			slope = ((double) y2 - y1) / (x2 - x1);
			y = ((double) y1) - (slope * x1);
		}
		return new double[] { slope, y };
	}

	// 선분이 교차하는 지점을 찾아, 사각형 범위 안에 있는지 확인
	public static boolean compareTwoLinearFunction(double line1[], double line2[]) {
		double slope1 = line1[0];
		double y1 = line1[1];
		double slope2 = line2[0];
		double y2 = line2[1];
		
		
		// 혹시 두 변이 평행하다면 false 리턴
		if (slope1 == slope2) {
			return false;
		}

		//slope1만 100이라면
		if(slope1==100) {
			double calc = (slope2*y1) + y2; 
			if((0<=calc)&&(calc<=height_length)) {
				return true;
			}
			else {
				return false;
			}
		}
		//slope2만 100이라면
		if(slope2==100) {
			double calc = (slope1*y2) + y1; 
			if((0<=calc)&&(calc<=height_length)) {
				return true;
			}
			else {
				return false;
			}
		}

		// 교차하는 점 위치 계산
		double x = (y2 - y1) / (slope1 - slope2);
		double y = (slope1 * x) + y1;

		if ((x >= 0) && (x <= width_length) && (y >= 0) && (y <= height_length)) {
			return true;
		} else {
			return false;
		}
	}
}
