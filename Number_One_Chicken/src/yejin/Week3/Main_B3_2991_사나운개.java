package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B3_2991_사나운개 {
	static boolean Aing = true;
	static boolean Bing = true;
	static int Aminute = 0;
	static int Rminute = 0;
	static int Bminute = 0;
	static int BRminute = 0;
	
	static int time[];
	
	static int AA, AR, BA, BR;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		AA = Integer.parseInt(st.nextToken());
		AR = Integer.parseInt(st.nextToken());
		BA = Integer.parseInt(st.nextToken());
		BR = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int P1 = Integer.parseInt(st.nextToken());
		int P2 = Integer.parseInt(st.nextToken());
		int P3 = Integer.parseInt(st.nextToken());
		int comeTime[] = {P1,P2,P3};
		
		//최대 시간
		int maxTime = Math.max(P1, P2);
		maxTime = Math.max(maxTime, P3);
		
		//공격중인지 여부 체크하는 시간 배열
		time = new int[maxTime+1];
		
		for (int i = 1; i <= maxTime; i++) {
			//A공격시간이면 time에 1추가
			if(Aing) {
				time[i]++;
				Aminute++;
				//공격시간 끝
				if (Aminute == AA) {
					Aing = false;
					Aminute = 0;
				}
			}else {
				Rminute++;
				//휴식시간끝
				if (Rminute == AR) {
					Aing = true;
					Rminute = 0;
				}
			}
			//B공격시간이면 time에 1추가
			if(Bing) {
				time[i]++;
				Bminute++;
				//공격시간 끝
				if (Bminute == BA) {
					Bing = false;
					Bminute = 0;
				}
			}else {
				BRminute++;
				//휴식시간끝
				if (BRminute == BR) {
					Bing = true;
					BRminute = 0;
				}
			}
		}
		
		//출력
		for (int j = 0; j < 3; j++) {
			System.out.println(time[comeTime[j]]);
		}
		
	}
	
}