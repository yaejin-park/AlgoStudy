package heejo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16938 {
	static int N;
	static int L;
	static int R;
	static int X;
	static boolean isSelected[];
	static int selectedNum[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(str1.nextToken());
		L = Integer.parseInt(str1.nextToken());
		R = Integer.parseInt(str1.nextToken());
		X = Integer.parseInt(str1.nextToken());

		isSelected = new boolean[N];
		selectedNum = new int[N];
		StringTokenizer str2 = new StringTokenizer(in.readLine());
		for(int i =0; i<N; i++) {
			selectedNum[i] = Integer.parseInt(str2.nextToken());
		}
		
		func(0);
		
		System.out.println(count);
	}
	
	static int count;
	public static void func(int cnt) {
		if(cnt==N) {
			//합을 구하기
			int sum = 0;
			int temp_count = 0;
			for(int i =0; i<N; i++) {
				if(isSelected[i]) {
					sum += selectedNum[i];
					temp_count++;
				}
			}
			//문제가 두 문제 이상인가
			if(temp_count<2) {
				return;
			}
			//L이상인가
			if(sum<L) {
				return;
			}
			//R이하인가
			if(sum>R) {
				return;
			}
			//X보다 크거나 같은가
			if(findMax()-findMin()<X) {
				return;
			}
			//전부 통과했다면 count 1 증가
			count++;
			return;
		}
		else {
			isSelected[cnt] = true;
			func(cnt+1);
			isSelected[cnt] = false;
			func(cnt+1);
		}
	}
	
	//배열에서 최대값을 찾는 함수
	public static int findMax() {
		int res = -1;
		for(int i =0; i<N; i++) {
			if(isSelected[i]) {
				if(selectedNum[i]>res) {
					res = selectedNum[i];
				}
			}
		}
		return res;
	}
	
	//배열에서 최소값을 찾는 함수
	public static int findMin() {
		int res = Integer.MAX_VALUE;
		for(int i =0; i<N; i++) {
			if(isSelected[i]) {
				if(selectedNum[i]<res) {
					res = selectedNum[i];
				}
			}
		}
		return res;
	}
}
