package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_1244_스위치 {
	static int S;
	static int status[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		S = Integer.parseInt(br.readLine());
		
		//스위치 상태
		status = new int[S+1];
		//스위치상태 배열로 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= S; i++) {
			status[i] = Integer.parseInt(st.nextToken());
		}
		
		//학생 수
		int Student = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < Student; i++) {
			st = new StringTokenizer(br.readLine());
			//성별
			int sex = Integer.parseInt(st.nextToken());
			//받은 수
			int num = Integer.parseInt(st.nextToken());
			change(sex, num);
		}
		
		//스위치 상태 sb에 넣기
		for (int i = 1; i <= S; i++) {
			sb.append(status[i]+" ");
			if(i%20==0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	private static void change(int sex, int num) {
		// 남자면
		if(sex == 1) {
			//스위치 만큼 돌면서
			for (int i = 1; i <= S; i++) {
				if(i%num==0) {	//배수면
					status[i] = status[i]==1?0:1;	//상태변경
				}
			}
		} else if(sex == 2) {	//여자면
			//일단 현재 스위치 값 바꾸기
			status[num] = status[num]==1?0:1;
			
			int gap = 1;
			while(true) {
				int a = num -gap;
				int b = num +gap;
				
				//경계검사 & 좌우대칭 값이 같은면
				if(a>=1 && b<=S && status[a] == status[b]) {
					status[a] = status[a]==1?0:1;
					status[b] = status[b]==1?0:1;
					gap++;
				}else{
					break;
				}
			}
		}
	}

}
