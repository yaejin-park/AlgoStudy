package yejin.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이분탐색, 투포인터
 * 
 * 투포인터 : 배열을 한번만 탐색하므로 정렬된 경우 O(n), 정렬되지 않아도 O(nlogn) / 이분탐색의 경우 O(logn)
 * 
 * 정렬 후 가장 작은 숫자 0과 n-1을 더함 -> 0보다 크면 n-1값 줄이기
 * 
 */
public class Main_G5_2467_용액 {
	static int arr[];
	static int N;
	static int min = Integer.MAX_VALUE;
	static int a, b;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		//값 받기
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = N-1;
		
		while(start < end) {
			int plus= arr[start] + arr[end];
			
			if(Math.abs(plus) <= min) {
				min = Math.abs(plus);
				a = arr[start];
				b = arr[end];
			}
			
			if(plus >0) {
				end--;
			} else if(plus < 0) {
				start++;
			} else {
				a = arr[start];
				b = arr[end];
				break;
			}
			
//			int mid = (start+end)/2;
//			
//			if(plus > 0) {
//				end = mid-1;
//			} else if(plus < 0) {
//				start = mid+1;
//			} else {
//				a = arr[start];
//				b = arr[end];
//				break;
//			}
		}
		
		System.out.println(a+" "+b);
	}

}
