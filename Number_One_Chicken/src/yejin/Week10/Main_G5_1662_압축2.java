package yejin.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Main_G5_1662_압축2 {
	
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
		ArrayList<Temp> list = new ArrayList<>();
		Stack<Character> stack = new Stack<>(); 
		
		int temp = 0;	//계산된 값 담는 변수
		int cnt = 0;
		
		int tempIdx = 0;
		
		String str = br.readLine();
		for (int i = 0,end=str.length(); i < end; i++) {
			char cur = str.charAt(i);
			if(cur==')') {	//닫힌 괄호가 나오면
				while(true) {
					char a = stack.pop();
					
					if(a=='(') {	//열린괄호가 나오면
						int su = stack.pop()-'0';	//앞에 붙어있는 수 추출
						temp *= su;					//괄호 안에 있었던 수의 개수 * 앞의 수
						System.out.println(temp);
						stack.add((char)(tempIdx +'9'));
						list.add(new Temp(tempIdx++, temp));
						temp = 0;
						break;
					} else if(a > '9'){
						temp += list.get(a-'9').val;
						list.set(a-'9',new Temp(tempIdx, temp));
						stack.add((char)(tempIdx +'9'));
					}
					else{	//'('가 나올 때까지 나온 숫자 카운트
						temp++;
					}
				}
			} else {
				stack.push(cur);
			}
		}
		
		while(!stack.isEmpty()) {
			char a = stack.pop();
			if(a>'9') {
				cnt += list.get(a-'9').val;
			} else {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
