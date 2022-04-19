package yejin.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 알파벳 조합 0~9 (총 10개)
 * 나온 알파벳 개수 세서 개수만큼 9~(9-알파벳개수)만큼 범위 잡고 알파벳 조합 짜기
 * 나온 조합으로 가장 큰 합 출력
 * 
 */
import java.util.ArrayList;
public class Main_G4_1339_단어수학_실패 {
	static int alpha[] = new int[26];
	static String[] arr;
	static boolean[] visited = new boolean[10];
	static ArrayList<Integer> list = new ArrayList<>();
	static int alpahCnt = 0;
	static int N;
	static int max = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new String[N]; 
	
		for (int i = 0; i < N; i++) {
			String str =br.readLine(); 
			arr[i] = str;
			for (int j = 0, end=str.length(); j < end; j++) {
				char cur = str.charAt(j);
				if(alpha[cur-'A'] == 0) {
					list.add(cur-'A');
					alpha[cur-'A'] = -1;
					alpahCnt++;
				}
			}
		}
		
		permu(0);
		System.out.println(max);
	}

	private static void permu(int cnt) {
		if(cnt == alpahCnt) {
			//계산
			int sum = 0;
			for (int i = 0; i < N; i++) {
				String str = arr[i];
				for (int j = 0,size = str.length(); j < size; j++) {
					char cur = str.charAt(j);
					sum +=alpha[cur-'A'] * Math.pow(10, j);
				}
			}
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 9; i > 9-alpahCnt; i--) {
			if(visited[i]) continue;
			
			int cur = list.get(cnt);
			if(alpha[cur] == -1) {
				visited[i] = true;
				alpha[cur] = i;
				permu(cnt+1);
				alpha[cur] = -1;
				visited[i] = false;
			}
		}
		
	}

}
