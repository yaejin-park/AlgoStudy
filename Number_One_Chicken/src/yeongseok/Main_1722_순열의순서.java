package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 노영석
 */
public class Main_1722_순열의순서 {
	static int N; //숫자의 개수
	static int option;	//옵션 번호 (1,2)
	static long k;	//원하는 순열의 순서
	static boolean isPrinted[];
	static int target[];	// option2 일때 순서를 구해야 하는 순열
	static long cnt=0;	//target 순열이 몇번째 순열인지 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isPrinted = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		option = Integer.parseInt(st.nextToken());
		switch(option) {
		case 1:
			k = Long.parseLong(st.nextToken());
			recursive1(k,N);
			break;
			
		case 2:
			target = new int[N];
			for(int i = 0 ; i <N; i++) {
				target[i] = Integer.parseInt(st.nextToken());
			}
			recursive2(0,N);
			System.out.println(cnt);
			break;
		}
	}
	
	
	
	private static void recursive2(int targetIndex, int num) {
		if(num == 1) {
			cnt++;
			return;
		}
		//1 2 3 4 5
		//[1] ~ => 더하기
		//[2] [4] [5] [1] [3]
		//[2] [1] =>
		//[1] [2] [3] [4] [5]
		//[1] [2] [3] [5] [4]
		int k = target[targetIndex];
		int groupIndex=0;
		for(int i = 0 ; i < N; i++) {
			if(isPrinted[i])continue;
			if(i+1 == k) {	//몇번째 그룹인지 찾은 경우
				isPrinted[i] = true;
				break;
			}
			groupIndex++;
		}
		long fac = fac(num-1);			//num개로 만들 수 있는 순열(num_P_num == num * num-1 P num-1이다.)
										//즉, num-1개짜리 순열이 num개 있는 것이다. 
		cnt+=(fac*groupIndex);
		recursive2(targetIndex+1, num-1);
		
	}



	/**
	 * 1. N <=20 이니 팩토리얼 계산을 할 수 없다.(시간초과)
	 * 2. num개의 수의 집합 {1,2,3,...num}으로 만들 수 있는 모든 순열 numPnum 중에서 index번째 순열이 몇번째 그룹에 속하는지
	 * 구하고, 그 그룹에 제일 앞에 있는 수를 출력한다.
	 * 3. numPnum == num * num-1_P_num-1이다 
	 * 즉, {1, (num-1)!}, {2, (num-1)!}, ..... {num, (num-1)!} 개의 순열을 만들 수 있다.
	 * 4. {num, (num-1)!}은 총 (num-1)!개씩의 경우의 수가 존재함으로 
	 * 5. index번째 순열에 (num-1)!이 몇번 존재하는지 구한다 ( index/(num-1)! )
	 * 6. 윗줄에서 구한 수를 index에서 빼준다.
	 * 
	 * @param index 순열의 위치
	 * @param num 순열을 만들 집합 원소의 개수
	 */

	
	 public static void recursive1(long index, int num){
	 	if( num == 1 ){
	 		printGroupNumber(0);
	 		return;
	 	}
	 	long fac = fac(num-1);			//num개로 만들 수 있는 순열(num_P_num == num * num-1 P num-1이다.)
	 								//즉, num-1개짜리 순열이 num개 있는 것이다. 
	 	long group = (index-1)/fac;		//index가 몇번째 그룹에 속해있는지 구한다.
	 	//if(fac == 1) group -= 1;
	 	printGroupNumber(group);		//아직 출력되지 않은 숫자중 group번째 숫자를 출력한다.
	 	recursive1(index-(fac*group),num-1);
	 }
	private static long fac(int i) {
		if(i == 1) return 1;
		return fac(i-1)*i;
	}
	/**
	 * 1~N까지의 숫자 중에서 아직 출력이 한번도 되지 않은 group번째 숫자를 출력한다.
	 * @param group
	 */
	private static void printGroupNumber(long group) {
		for(int i = 0; i<N; i++) {
			if(group == 0) {
				if(isPrinted[i]) continue;
				 System.out.print((i+1) + " ");
				 isPrinted[i] = true;
				 return;
			}else {
				if(!isPrinted[i]) {
					group--;
				}
			}
			
		}
	}

}
