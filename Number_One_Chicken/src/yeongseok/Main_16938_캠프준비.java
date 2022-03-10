package yeongseok;

import java.util.Scanner;
/**
 * 1. N개의 원소로 만들 수 있는 부분집합을 구한다.
 * 2. 만들어진 부분집합의 원소들이 조건을 만족시키는지 검증한다.
 * @author 노영석
 *
 */
public class Main_16938_캠프준비 {
	static int N;
	static int L;
	static int R;
	static int [] A;
	static boolean [] visitied;
	static int X;
	static int totalCnt=0;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		X = sc.nextInt();
		A = new int[N+1];
		visitied = new boolean[N+1];
		for(int i = 1 ; i<N+1; i++) {
			A[i] = sc.nextInt();
		}
		subset(1,0);
		System.out.println(totalCnt);
	}
	
	private static void subset(int index, int cnt) {
		if(index == N+1) {	//N개에 대한 부분집합을 구하면
			//문제 개수가 2문제 미만이면
			if(cnt<2) return;
			//조건을 만족하면
			if(isValid()) totalCnt++;
			return;
		}
		visitied[index] = true;
		subset(index+1, cnt+1);
		visitied[index] = false;
		subset(index+1, cnt);
	}
	/**
	 * 부분집합이 조건을 만족시키는지 검증하는 함수
	 * @return
	 */
	private static boolean isValid() {
		int sum=0;//난이도의 합
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i = 1; i <N+1; i++) {
			if(visitied[i]) {
				sum +=A[i];
				max = Integer.max(A[i], max);
				min = Integer.min(A[i], min);
			}
		}
		
		//난이도 기준이 부합하지 않으면
		if(sum < L || sum > R) return false;
		if(Math.abs(min-max)<X) return false;
		
		return true;
	}

}
