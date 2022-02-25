package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S3_11399_ATM {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int waiting[] = new int[N];
		int sum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			waiting[i] = Integer.parseInt(st.nextToken());
		}
		
		//오름차순
		Arrays.sort(waiting);
		
		
		for (int i = 0; i < N; i++) {
			sum += waiting[i]*(N-i);
		}
		System.out.println(sum);
	}

}
