package heejo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 가희와 은행
 * 백준 G5
 * 22234
 * 
 * 문제 목표 : W-1초가 지날 때 어떤 고객의 업무를 처리하는지 찾아라
 * 
 * 풀이 방법
 * 0. 기본 설정
 * 1. 고객 정보, 시간 고려하며 실행
 */
public class Main_22234_가희와은행 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str1 = new StringTokenizer(in.readLine());
		//0-1. 기본 변수 설정
		int N = Integer.parseInt(str1.nextToken());	//영업 시작 전 대기 손님 N명
		int T = Integer.parseInt(str1.nextToken());	//인당 업무 처리 제한 시간
		int W = Integer.parseInt(str1.nextToken());	//몇초까지 출력을 할 것인지
		Queue<int[]> queue = new LinkedList<int[]>();	//은행의 대기 순서

		//0-2. 영업 시작 전에 대기 손님들 명단 (queue로 생성)
		for(int i = 0; i<N; i++) {
			StringTokenizer str2 = new StringTokenizer(in.readLine());
			int Px = Integer.parseInt(str2.nextToken());
			int Tx = Integer.parseInt(str2.nextToken());
			queue.offer(new int[] {Px, Tx});
		}
		
		//0-3. 영업 시작 이후에 몇분이 오시는지
		int M = Integer.parseInt(in.readLine());
		
		//0-4. 영업 시작 이후에 오시는 손님들 (정렬을 위해 배열 생성)
		int customer_after_open[][] = new int[M][3];	//영업 이후 들어오는 고객들 정보
		for(int i = 0 ;i<M; i++) {
			StringTokenizer str3 = new StringTokenizer(in.readLine());
			customer_after_open[i][1] = Integer.parseInt(str3.nextToken());	//고객아이디
			customer_after_open[i][2] = Integer.parseInt(str3.nextToken());	//고객의소요시간
			customer_after_open[i][0] = Integer.parseInt(str3.nextToken());	//몇초뒤에들어오나
		}
		
		//0-5. 영업 이후 들어온 고객들을 들어온 시간을 기준으로 정렬
		Arrays.sort(customer_after_open, Comparator.comparingInt(o1 -> o1[0]));

		//0-6. 실행에서 사용되는 변수들
		double total_time = 0;	//총 시간
		int customer_time = 0;	//고객당 사용된 시간
		int[] temp = queue.poll();	//현재 응대중인 고객 정보
		int num = 0;	//임시 배열 순서
		
		//1. 실행
		while(total_time<W) {
			temp[1]--;	//현재 고객의 총 남은 시간 1 감소
			total_time++;	//은행 시간 1 증가
			customer_time++;	//현재 고객의 사용 시간 1 증가
			
			System.out.println(temp[0]);	//현재 이용중인 고객의 ID번호 출력

			//현재 시간에 고객이 들어온다면 queue에 추가
			if((num<M)&&(customer_after_open[num][0]==total_time)) {
				queue.offer(new int[] {customer_after_open[num][1], customer_after_open[num][2]});
				num++;
			}

			//현재 처리하고 있는 고객의 남은 시간이 없다 = 다음 고객을 받자.
			if(temp[1] == 0) {
				customer_time = 0;
				temp = queue.poll();				
			}

			//고객의 개인 시간이 T를 다 사용했다면
			if(customer_time==T) {
				queue.offer(new int[] {temp[0], temp[1]});
				customer_time = 0;
				temp = queue.poll();	
			}
		}
	}
}
