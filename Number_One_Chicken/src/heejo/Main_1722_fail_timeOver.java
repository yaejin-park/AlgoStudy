package heejo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 순열의 순서 백준 1722 골드 5
 * 
 * 현재 순열이 몇번째 순열인지 확인하시오.
 * 
 */
public class Main_1722_fail_timeOver {
	static int count;
	static boolean finished;
	static int N;
	static boolean isSelected[];
	static int selected[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		isSelected = new boolean[N];
		selected = new int[N];
		int small_num = sc.nextInt();
		if(small_num==1) {
			//k번째 순열을 출력
			int k = sc.nextInt();
			permu(0, 1, k, new int[]{0});
		}
		else if(small_num==2) {
			//해당하는 순열이 몇번째인지 찾기
			int array[] = new int[N];
			for(int i =0; i<N; i++) {
				array[i] = sc.nextInt();
			}
			permu(0, 2, -1, array);
		}
	}
	
	public static void permu(int cnt, int small_num, int k, int[] arr) {
		if(finished) {
			return;
		}
		if(cnt==N) {
			count++;
			if(count==k) {
				finished = true;
				for(int i = 0; i<N; i++) {
					System.out.print(selected[i] + " ");
				}
				System.out.println();
				return;
			}
			for(int i=0;i<N;i++) {
				if(selected[i]!=arr[i]) {
					return;
				}
			}
			System.out.println(count);
			return;
		}
		else {
			for(int i =0; i<N; i++) {
				if(isSelected[i]) {
					continue;
				}
				isSelected[i] = true;
				selected[cnt] = i+1;
				permu(cnt+1, small_num, k, arr);
				isSelected[i] = false;
			}
		}
	}
}
