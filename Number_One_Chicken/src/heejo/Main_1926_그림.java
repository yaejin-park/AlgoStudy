package heejo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 제목 : 그림
 * 사이트 : 백준
 * 난이도 : 실버1
 * 번호 : 1926
 * 
 * 문제 목표 : 도화지의 그림(1)이 가장 큰 넓이를 구하시오.
 * 
 * 풀이 방법
 * 0. 초기 설정
 * 1. 0,0부터 1을 체크한다.
 * 2. 1이 확인이 된다면 findPicture 실행
 * 3. 실행이 종료되면 다음 위치로 이동하면서 1을 체크
 * 4. 종료되면 최대 넓이와 개수를 출력한다.
 * 
 * findPicture
 * - 현재 위치에서 다음 위치(상하좌우)를 파악하여 해당 위치로 이동하며 넓이 면적을 증가
 * - 넓이로 체크된 곳은 0으로 바뀌어, 다음 그림 체크시 중복이 없도록 한다.
 * 
 * 오답노트
 * - 78번 max_temp는 1의 개수를 세야 하는 수단이기 때문에, 덮어씌우는 것이 아닌 증가가 맞다.
 */

public class Main_1926_그림 {
	static int n, m;	//세로 n, 가로 m
	static int max;		//그림의 최대 넓이
	static int max_temp;	//알아보고 있는 현재 그림의 최대 넓이
	static int count;	//그림의 개수
	static int picture[][];	//도화지
	public static void main(String[] args) throws Exception{
		//0. 초기 설정
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		//0-1. n과 m 입력
		n = Integer.parseInt(str1.nextToken());
		m = Integer.parseInt(str1.nextToken());
		//0-2. 도화지 입력
		picture = new int[n][m];
		for(int i = 0 ; i<n; i++) {
			StringTokenizer str2 = new StringTokenizer(in.readLine());	
			for(int j =0; j<m; j++) {
				picture[i][j] = Integer.parseInt(str2.nextToken());
			}
		}
		
		//1~3. 실행
		for(int i = 0 ; i<n; i++) {
			for(int j =0; j<m; j++) {
				//1. 0,0부터 1을 체크한다. 1이 있다면
				if(picture[i][j]==1) {
					findPicture(i, j, 1);	//그림넓이 찾기 시작
					if(max_temp>max) {
						max = max_temp;	//찾은 그림이 기존 넓이보다 크다면 갱신
					}
					count++;	//그림 개수 1 증가
					max_temp = 0;	//임시 그림 넓이 변수 다시 0으로 초기화
				}
			}
		}
		
		//4. 출력
		System.out.println(count);
		System.out.println(max);
		
	}
	
	//현재 점이 포함된 그림 찾기
	public static void findPicture(int x, int y, int cnt) {
		picture[x][y] = 0;	//현재 자리는 0으로 지우기
		
		//넓이 계속 갱신
		max_temp += cnt;
		
		//위
		if((x-1>=0)&&(picture[x-1][y]==1)) {
			findPicture(x-1,y,1);	//해당 자리로 이동
		}
		//아래
		if((x+1<n)&&(picture[x+1][y]==1)) {
			findPicture(x+1,y,1);	//해당 자리로 이동
		}
		//왼쪽
		if((y-1>=0)&&(picture[x][y-1]==1)) {
			findPicture(x,y-1,1);	//해당 자리로 이동
		}
		//오른쪽
		if((y+1<m)&&(picture[x][y+1]==1)) {
			findPicture(x,y+1,1);	//해당 자리로 이동
		}
	}
}
