package yejin.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Main_G5_1662_압축 {
	
	static class Temp{
		int idx;
		int val;
		public Temp(int idx, int val) {
			super();
			this.idx = idx;
			this.val = val;
		}
	}
	static int cnt = 0;
	static Stack<Character> stack = new Stack<>(); 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Temp> list = new ArrayList<>();
		
		String str = br.readLine();
		for (int i = 0,end=str.length(); i < end; i++) {
			char cur = str.charAt(i);
			stack.add(cur);
		}
		
		System.out.println(recur(0, 0, false));
	}

	static private int recur(int answer,int cnt, boolean find) {
		if(stack.size()==1) {
			return answer+cnt;
		}
		char cur = stack.pop();
		
		if(cur==')') {
			recur(answer+recur(answer,cnt,true), cnt, false);
		} else if(cur=='(') {
			find = false;
			int multi = stack.pop() -'0';
			return multi * cnt;
		} else {	//숫자면
			recur(answer, cnt+1, find);
		}
		
		return -1;
	}

}
