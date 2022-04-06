package yeongseok.Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 코드가 DP 비슷한데 DP 아닌듯..ㅋㅋ
 * 개어려웠음.
 * @author 노영석
 *
 */
public class Main_2643_색종이올려놓기 {
	static class Paper{
		int width;
		int height;
		public Paper(int width, int height) {
			super();
			this.width = width;
			this.height = height;
		}
	}
	static int n;
	static Paper arr[];
	static int memo[];	//memo[i] 색종이 위에 올릴 수 있는 최대 색종이 개수
	static boolean check[];	//check[i] 색종이 위에 올릴 수 있는 최대 색종이 개수 계산 여부
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new Paper[n];
		memo = new int[n];
		check = new boolean[n];
		for(int i = 0 ; i <n ; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Paper(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < n; i++ ) {
			if(!check[i]) {
				recursive(i);	// i 번째 색종이 위에 올릴 수 있는 최대 색종이 개수를 구한다.
				max = Integer.max(max, memo[i]);
			}
		}
		System.out.println(max+1);
	}
	/*
	 * k번째 색종이 위에 올릴 수 있는 최대 색종이 개수를 구한다.
	 */
	private static void recursive(int current) {
		check[current] = true;	//방문체크
		
		int sameCnt=0;		//k번째 색종이와 크기가 동일한 색종이의 개수
		
		//색종이를 처음부터 순회
		for (int i = 0; i < n; i++) {
			if(i == current)continue; //자기 자신에 대해서는 검사 안함
			
			//현재 색종이와와 크키가 같은 색종이면
			if((arr[current].width == arr[i].width && arr[current].height == arr[i].height)
				||
				(arr[current].width == arr[i].height && arr[current].height == arr[i].width)) {
				
				if(!check[i]) {//이전에 최대 색종이 개수를 구한적이 없는 색종이면
					sameCnt++;
				}else {//이전에 계산된적이 있으면 어차피 크키가 동일한 색종이니까 이전에 계산한값 그대로 사용한다.
					memo[current] = memo[i];
					return;
				}
				
			//현재 색종이와 크키가 다른 색종이면	
			}else {
				//현재 색종이보다 큰 색종이면 패스
				if(!((arr[current].width >= arr[i].width && arr[current].height >= arr[i].height)
				||
				(arr[current].width >= arr[i].height && arr[current].height >= arr[i].width))) continue;
			
				//이전에 최대 색종이 개수를 구한적이 없는 색종이면
				if(!check[i]) {
					recursive(i);	//그 색종이에 올릴 수 있는 최대 색종이 개수 구하기
				}
				
				//======여기까지 수행됐으면 memo[i]에는 i번째 색종이 위에 올릴 수 있는 최대 색종이 개수가 구해져있다.
				
				
				
				memo[current] = Integer.max(memo[current],memo[i]+1);
			
			}
		}
		memo[current]+=sameCnt;
		
	}

}
