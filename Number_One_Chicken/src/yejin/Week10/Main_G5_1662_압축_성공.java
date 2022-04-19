package yejin.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/*	//입력 중
 * 1. 숫자, ( : stack에 넣기
 * 2. ) : (가 나올때까지 stack에서 pop
 *  2-1 : 숫자 : cnt++ 
 *  2-2 : ( : 한번 더 pop해서 cnt랑 곱해서 결과값 stack에 넣기
 * 			     숫자인지 길이인지 비교해줘야하므로 그 수 넣고 *도 넣기
 *  2-3 : * : 한번 더 pop해서 그 값 cnt에 더하기
 *	//입력  끝
 * 3. stack에는 숫자 아니면 *므로 stack빌때까지
 * 
 * 
 */

public class Main_G5_1662_압축_성공 {
	
	static class Temp{
		int idx;
		int val;
		public Temp(int idx, int val) {
			super();
			this.idx = idx;
			this.val = val;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<String> stack = new Stack<>(); 
		
		String str = br.readLine();
		for (int i = 0,end=str.length(); i < end; i++) {
			char a = str.charAt(i);
			
			if(a==')') {
				int cnt = 0;	//길이
				while(true) {
					String pop = stack.pop();
					if(pop.equals("(")) {
						String multi = stack.pop();
						int result = Integer.parseInt(multi)*cnt;
						stack.add(result+"");
						stack.add("*");
						break;
					} else if(pop.equals("*")){
						pop = stack.pop();
						cnt += Integer.parseInt(pop);
					} else{	//숫자
						cnt++;
					}
				}
			}  else { //숫자, (
				stack.add(a+"");
			}
		}
		
		int cnt = 0;
		while(!stack.isEmpty()) {
			String pop = stack.pop();
			
			if(pop.equals("*")) {
				pop = stack.pop();
				
				cnt += Integer.parseInt(pop);
			} else {	//숫자면
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
