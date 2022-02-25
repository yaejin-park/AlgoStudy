package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S4_2567_색종이2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		//도화지
		int[][] map = new int[101][101];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			for (int j = r; j < r+10; j++) {
				for (int j2 = c; j2 < c+10; j2++) {
					map[j][j2]++;
				}
			}
		}
		
		for (int i = 0; i < 101; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
	}

}
