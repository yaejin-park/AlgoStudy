package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B1_3985_박예진 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int cake[] = new int[L+1];
		
		int think = 0;	//가장 많은 조각 예상되는 방청객 번호(여러명이면 번호 작은 사람)
		int thinkCake = 0;
		
		int real = 0;	//실제로 가장 많은 조각 방청객 번호
		int realCake = 0;
		
		StringTokenizer st = null;
		
		//방청객 번호
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			//원하는 케이크
			int wantCake = to-from+1;
			if(wantCake > thinkCake) {
				thinkCake = wantCake;
				think = i;
			}
			
			int get = 0;
			//실제 케이크
			for (int j = from; j <= to; j++) {
				if(cake[j] == 0) {
					cake[j] = i;
					get++;
				} 
			}
			if(get > realCake) {
				real = i;
				realCake = get;
			}
		}
		
		System.out.println(think);
		System.out.println(real);
	}
}
