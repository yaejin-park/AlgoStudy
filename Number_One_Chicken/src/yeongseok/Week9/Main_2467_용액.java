package yeongseok.Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2467_용액 {
	static boolean hasTwoKinds;	//두 종료의 용액 존재 여부
	static int flagIndex;	//두 수의 부호가 달라지는 지점
	static int arr[];	//용액들
	static int res[];
	static int n;
	static int minDif = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		res = new int[2];
		st = new StringTokenizer(br.readLine());
	
		for(int i = 1 ; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i]*arr[i-1]<0) {	//두 수의 부호가 다르면
				hasTwoKinds = true;
				flagIndex = i-1;	
			}
		}
		
		if(!hasTwoKinds) {	//한 종류의 용액만 입력으로 들어온 경우
			if(arr[1] > 0) {
				System.out.println(arr[1] + " " + arr[2]);
			}else {
				System.out.println(arr[arr.length-2] + " " + arr[arr.length-1]);
			}
			return;
		}else {
			if(flagIndex >1) {
				int abs = Math.abs(arr[flagIndex-1] + arr[flagIndex]);
				minDif = abs;
				res[0] = flagIndex-1; res[1] = flagIndex;
			}
			
			if(flagIndex+1 < n) {
				int abs = Math.abs(arr[flagIndex+1] + arr[flagIndex+2]);
				if(minDif > abs) {
					minDif = abs;
					res[0] = flagIndex+1; res[1] = flagIndex+2;	
				}
				
			}
		
			for(int i = flagIndex; i>0; i--) {
				//int before = Integer.MAX_VALUE;
				for(int j = flagIndex+1; j<=n; j++) {
					int k = Math.abs(arr[i] + arr[j]);
					if(k == 0) {
						System.out.println(arr[i] + " " + arr[j]);
						return;
					}
					if(k < minDif) {
						minDif = k;
						res[0] = i; res[1] =j;
					}
					
/*					int k = Math.abs(arr[i] + arr[j]);
					if(k == 0) {
						System.out.println(arr[i] + " " + arr[j]);
						return;
					}
					if(k >= before) break;
					before = k;
					if(before < minDif) {
						minDif = before;
						res[0] = i; res[1] =j;
					}*/
					
				}
			}
			System.out.println(arr[res[0]] + " " + arr[res[1]]);
		}
	}
	

}
