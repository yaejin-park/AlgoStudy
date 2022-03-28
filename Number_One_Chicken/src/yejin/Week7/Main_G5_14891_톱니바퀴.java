package yejin.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 서로 맞닿은 극이 다르면 반대방향으로 회전, 같으면 회전X

	8개의 톱니, 톱니바퀴 4개, N,S극 가짐
	
	K번회전
	
	입력)
	4개의 톱니바퀴 상태(4줄) 12방향부터 시계방향 순서대로 (N :0 , S:1)
	회전횟수
	(K줄)회전방법 : 톱니바퀴번호, 방향(1이면 시계방향, -1이면 반시계 방향)
	
	출력) 
	
	풀이)
	만나는 톱니
	(12방향부터 0 이라고 생각하면)
	1. 왼쪽 : 6
	2. 오른쪽 : 2
	
	비교할 톱니 인덱스 저장해두기
	
	오른쪽 톱니와 계속 비교(3번 비교)
	다르면 해당 톱니바퀴의 톱니 인덱스 변경(반시계 : -1/ 시계 : +1) 만약 변경된 인덱스가 8이상이되면 0/ -1이되면 7로
 * @author yaejin
 *
 */

public class Main_G5_14891_톱니바퀴 {
	static int Gear[][] = new int[4][8];
	static int Compare[][] = new int[4][2];	//비교할 idx
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//톱니바퀴 입력
		for (int i = 0; i < 4; i++) {
			String info = br.readLine();
			for (int j = 0; j < 8; j++) {
				Gear[i][j] = info.charAt(j) =='1'?1:0;
			}
		}
		
		//비교할 톱니 idx초기화
		for (int i = 0; i < 4; i++) {
			Compare[i][0] = 6;	//왼쪽 톱니비교
			Compare[i][1] = 2;	//오른쪽 톱니비교
		}
		
		//회전 입력
		int K = Integer.parseInt(br.readLine());	//회전수
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken())-1;	//회전할 기어 넘버(인덱스는 0부터라서 -1)
			int rot = Integer.parseInt(st.nextToken());			//회전 방향(1:시계, -1:반시계)
			
			//옆 톱니바퀴로 가면서 비교
			int rotate[] = new int[4];	//톱니바퀴마다 회전해야회전방향 저장해두기
			rotate[gearNum] = rot;		//현재 톱니바퀴 회전 저장
			//gearNum 왼쪽 비교
			int comIdx = 0;
			int tempRot = rot;
			while(gearNum-comIdx-1 >= 0) {	//비교할 톱니바퀴가 0번째보다 클경우
				//톱니비교 (현재기어톱니  왼쪽 & 왼쪽기어톱니 오른쪽이 다르면) 회전
				if(Gear[gearNum-comIdx][Compare[gearNum-comIdx++][0]] != Gear[gearNum-comIdx][Compare[gearNum-comIdx][1]]) {
					tempRot = tempRot== 1?-1:1;//현재 바퀴의 반대로 회전 방향 저장
					rotate[gearNum-comIdx] = tempRot;	//회전 저장
				} else {
					break;
				}
			}
			
			//gearNum 오른쪽 비교
			comIdx = 0;
			tempRot = rot;
			while(gearNum+comIdx+1 < 4) {	//비교할 톱니바퀴가 4번째보다 작을 경우
				//톱니비교 (현재기어톱니  오른쪽 & 오른쪽기어톱니 왼쪽이 다르면) 회전
				if(Gear[gearNum+comIdx][Compare[gearNum+comIdx++][1]] != Gear[gearNum+comIdx][Compare[gearNum+comIdx][0]]) {
					tempRot = tempRot== 1?-1:1;//현재 바퀴의 반대로 회전 방향 저장
					rotate[gearNum+comIdx] = tempRot;	//회전 저장
				} else {
					break;
				}
			}
			
			//저장한 회전 정보(rotate배열)대로 바퀴 회전
			for (int j = 0; j < 4; j++) {
				//(반시계 : -1/ 시계 : +1) 만약 변경된 인덱스가 8이상이되면 0/ -1이되면 7로
				if(rotate[j]==-1) { 		//반시계 방향
					for (int c = 0; c < 2; c++) {	
						Compare[j][c]= Compare[j][c]+1 ==8? 0 : Compare[j][c]+1;
					}
				} else if(rotate[j]==1) {	//시계 방향
					for (int c = 0; c < 2; c++) {
						Compare[j][c]= Compare[j][c]-1 ==-1? 7 : Compare[j][c]-1;
					}
				}
			}
		}
		
		
		//점수계산 12방향 톱니가 N :0 / S : 1
		int score =0;
		for (int i = 0; i < 4; i++) {
			if(Gear[i][Compare[i][1]-2<0?Compare[i][1]-2+8:Compare[i][1]-2] == 1) {
				score += Math.pow(2, i);
			}
		}
		System.out.println(score);
	}
}
