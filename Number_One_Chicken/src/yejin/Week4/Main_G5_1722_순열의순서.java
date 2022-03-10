package yejin.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_1722_순열의순서 {
	static int N;
	static int arr1[], arr2[];
	static int k, cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr1 = new int[N];
		arr2 = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int type = Integer.parseInt(st.nextToken());	//소문제 번호
		
		if(type == 1) {	
			//k번째 순열 나타내는 N개의 수
			k = Integer.parseInt(st.nextToken());
			//순열
			
		} else {
			int cnt = 0;
			//몇번째 순열인지
			for (int i = 0; i < N; i++) {
				arr2[i] = Integer.parseInt(st.nextToken());
			}
			q2(arr2);
			
		}
	}

	//해당 순열의 순서 구하기
	private static void q2(int[] arr) {
		for (int i = 0; i < N; i++) {
			//인덱스와 의 차만큼
//			(arr[i] - (i+1)) * 
		}
	}

//	private static void permu(int depth, int flag) {
//		if(depth==N) {
//			cnt++;
//			if(cnt==k) {
//				for (int i = 0; i < N; i++) {
//					System.out.print(arr1[i]+" ");
//				}
//				System.out.println();
//			}
//			return;
//		}
//		
//		for (int i = 1; i <= N; i++) {
//			if((flag & 1<<i) ==0) {
//				arr1[depth] = i;
//				permu(depth+1, flag | 1<<i);
//			}
//		}
//	}
}
