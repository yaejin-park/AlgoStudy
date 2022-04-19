package yejin.Week10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 	메모리 제한 잘 보기(구글링)
 *  
 *  <제곱근으로 소수판별>
 *  모든 수를 두 수의 곱으로 나타내면 반드시 두수 중 하나는 제곱근보다 작거나 같다.
 *  이때 그 작은 수가 나누어지는지 확인하면 그 수는 소수 아님
 *  
 *  풀이방법
 *  1. 왼쪽부터 하나, 둘, 셋~ N까지 자리가 다 소수여야 하므로
 *  하나가 소수면 -> 그뒤에 또 1~9더해서 그것까지 소수면 그 수 더하고 -> 반복
 * 
 */

public class Main_G5_2023_신기한소수 {
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dfs("", 0);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void dfs(String s, int cnt) {
		//N길이만큼 완성되면 출력 저장
		if(cnt == N) {
			sb.append(s).append(System.lineSeparator());
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			//string으로 수 붙이기 쉽게
			if(isPrime(Integer.parseInt(s+i))){
				dfs(s+i, cnt+1);
			}
		}
		
	}

	//소수판별
	public static boolean isPrime(int num) {
		if(num == 1) return false;
		
		//제곱근으로 소수판별
		int sqrt = (int)Math.sqrt(num);
		for (int i = 2; i <= sqrt; i++) {
			if(num%i == 0) return false;
		}
		
		return true;
	}

}
