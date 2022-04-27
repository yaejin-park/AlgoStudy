package yejin.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * R : 뒤집기
 * D : 버리기 (배열이 비어있으면 error 출력)
 * 
 */
public class Main_G5_5430_AC {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			String p = br.readLine();	//수행할 함수 p(길이 1~100,000)

			int n = Integer.parseInt(br.readLine());	//배열에 들어있는 수의 개수
			String arr = br.readLine();	//배열 앞뒤 [] 없애기
			arr = arr.substring(1,arr.length()-1);
			StringTokenizer st = new StringTokenizer(arr,",");
			
			Deque<Integer> dq = new LinkedList<>();
			while(st.hasMoreTokens()) {
				dq.offer(Integer.parseInt(st.nextToken()));
			}
			
			boolean error = false;
			boolean isRevered = false;	//뒤집혔는지 여부
			
			//함수실행
			for (int j = 0, end = p.length(); j < end; j++) {
				char func = p.charAt(j);
				
				switch (func) {
				case 'R':
					isRevered = !isRevered;
					break;
				case 'D':
					if(dq.size()==0) {
						error = true;
					} else {
						if(isRevered) {
							dq.pollLast();
						} else {
							dq.pollFirst();
						}
					}
					break;
				}
				if(error) break;	//에러뜨면 빠져나오기
			}
			
			if(error) {
				sb.append("error");
			}else {
				sb.append("[");
				if(isRevered) {
					while(!dq.isEmpty()) {
						sb.append(dq.pollLast());
						if(dq.size()!=0) sb.append(",");
					}
				} else {
					while(!dq.isEmpty()) {
						sb.append(dq.pollFirst());
						if(dq.size()!=0) sb.append(",");
					}
				}
				sb.append("]");
			}
			sb.append(System.lineSeparator());
		}
		System.out.println(sb);
	}
}
