package yejin.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main_G4_전화번호2 {
	
	static class Num implements Comparable<Num>{
		String num;
		int len;
		
		public Num(String num) {
			super();
			this.num = num;
			this.len = num.length();
		}
		//길이 내림차순
		@Override
		public int compareTo(Num o) {
			if(this.len==o.len) {
				return this.num.compareTo(o.num);
			} else {
				return this.len-o.len;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int n =Integer.parseInt(br.readLine());	//전화번호수(100000)
			
			boolean isNo = false;
			PriorityQueue<Num> pq = new PriorityQueue<>();
			Stack<Num> tempStore = new Stack<>();
			
			//전화번호 수만큼
			loop:
			for (int j = 0; j < n; j++) {
				//현재 전화번호
				String cur = br.readLine();
				int curLen = cur.length();
				
				//담긴 전화번호
				while(!pq.isEmpty()) {
					Num comp = pq.peek();
					if(comp.len >= curLen) break;
					comp = pq.poll();
					tempStore.add(comp);

					String mix = comp.num + cur.substring(comp.len);
					
					if(mix.equals(cur)) {
						isNo = true;
						break loop;
					}
				}
				
				//비교한 전화번호 poll한만큼 다시 집어넣기
				while(!tempStore.isEmpty()) {
					pq.add(tempStore.pop());
				}
				
				//현재 전화번호 집어넣기
				pq.add(new Num(cur));
			}
			
			if(isNo) {
				sb.append("NO");
			} else {
				sb.append("YES");
			}
			sb.append(System.lineSeparator());
			
		}
		System.out.println(sb);
	}

}