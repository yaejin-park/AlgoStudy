package yeongseok.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
	static char map[][];
	static int [] pos = {3,7};	//left 방향일때는 2번 톱니를 조사해야하고, right 방향일때는 6번 톱니를 조사해야한다.
	static int [] LR = {-1,1};	//left right
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new char[6][9];
		
		
		//톱니바퀴 입력받기
		for(int i = 1; i <5; i++) {
			String readLine = br.readLine();
			for(int j = 1; j <9; j++) {
				map[i][j] = readLine.charAt(j-1);
			}
		}
		
		
		int n = Integer.parseInt(br.readLine());
		int gear, dir;
		for(int i =0; i <n; i++) {
			st = new StringTokenizer(br.readLine());
			gear = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			
			rotate(gear,dir,0);
		}
		
		
		//점수 계산하기
		int sum = 0;
		int k=1;
		for(int i = 1; i<5; i ++) {
			if(map[i][1] == '1') { //S극인 경우
				sum +=k;
			}
			k*=2;
		}
		System.out.println(sum);
	}
	/**
	 * 
	 * @param gear 톱니바퀴 index
	 * @param dir 시계, 반시계방향인지
	 * @param from 어느 톱니바퀴때문에 돌게 됐는지 index
	 */
	private static void rotate(int gear, int dir, int from) {
		
		//자신의 좌우 톱니바퀴에 대해서 조사한다.
		for(int i =0; i <2; i ++) {
			int nextGear = gear + LR[i];
			if(nextGear == 0 || nextGear == 5) continue;	// 1~4이외의 톱니바퀴면 건너뛴다.
			if(nextGear == from) continue;	//자기를 회전시킨 톱니바퀴에 대해서는 건너뛴다.
			//현재 톱니바퀴와 다음 톱니바퀴의 극이 같으면
			if(map[nextGear][pos[i]] == map[gear][pos[Math.abs(i-1)]]) continue;
			
			rotate(nextGear,dir*-1,gear);
		}
		
		
		//회전시킨다.
		switch(dir) {
		case 1:	//시계방향
			char temp = map[gear][8];
			for(int i = 8; i>1; i--) {
				map[gear][i] = map[gear][i-1];
			}
			map[gear][1] = temp;			
			break;
			
		case -1: //반시계방향
			char temp2 = map[gear][1];
			for(int i = 1; i<8; i++) {
				map[gear][i] = map[gear][i+1];
			}
			map[gear][8] = temp2;
			break;
		}
	}
}
