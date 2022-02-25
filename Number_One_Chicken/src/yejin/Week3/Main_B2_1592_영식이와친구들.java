package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2_1592_영식이와친구들 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//5, 3, 2
		int N = Integer.parseInt(st.nextToken());	//1~N명
		int M = Integer.parseInt(st.nextToken());	//한사람이 M번 받으면 끝
		int L = Integer.parseInt(st.nextToken());	//L번째 있는 사람에게 던지기(홀 : 시계)
		
		br.close();
		
		int arr[] = new int[N+1]; //1부터 시작
		
		int cur = 1;
		int cnt = 0; //몇 번 던지는지
		
		while(true) {
			arr[cur]++;	//받은 수 증가
			if(arr[cur] == M) {
				break;
			}
			cnt++;
			//홀수번 받았으면,
			if(arr[cur]%2 ==1) {
				//시계방향으로 던지기 (즉 더하기)
				if(cur+L>N) {
					cur = cur+L-N;
				} else {
					cur = cur+L;
				}
			} else {
				if((cur-L)<1) {
					cur = N+(cur-L);
				} else {
					cur = cur - L;
				}
			}
		}
		System.out.println(cnt);
	}
}
