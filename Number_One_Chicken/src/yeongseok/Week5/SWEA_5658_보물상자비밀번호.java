package yeongseok.Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_5658_보물상자비밀번호 {
	static int T,N,K,numLen;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<T+1; t++) {
			LinkedList<Character> ll = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			numLen = N/4;	//사각형의 한 변의 길이(숫자를 만들 문자들의 개수)
			String s = br.readLine();
			//큐에 삽입
			for(int i = 0; i<s.length(); i++) {
				ll.offer(s.charAt(i));
			}
			//max heap (내림차순)
			PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<Long>() {
				
				@Override
				public int compare(Long o1, Long o2) {
					return Long.compare(o2, o1);
				}
			});
			
			for(int i = 0; i<N; i++) {
				
				
				//create numbers
				for(int a = numLen; a<=N; a+=numLen) {
					int k = 1; long hexDigit=0;
					
					// N/4 개의 문자들로 10진수 숫자를 만든다
					for(int b = a - 1; b >= a-numLen; b--) {
						char c = ll.get(b);
						int hex = charToHex(c);
						hexDigit += (k*hex);
						k*=16;
					}
					
					//중복 제거해서 heap에 넣는다.
					if(!pq.contains(hexDigit)) {
						pq.offer(hexDigit);
					}
				}
				
				//rotate
				//맨 뒤엣것을 꺼내서 맨앞에 넣는다.
				ll.offerFirst(ll.pollLast());
			}
			
			//k번째 숫자를 찾기 위한 과정
			for(int i =1 ; i< K; i++) {
				pq.poll();
			}
			System.out.println("#"+t + " " + pq.peek());
			
			
		}
		
	}
	/**
	 * c -> hex digit으로 변환하는 함수
	 * @param c
	 * @return hex digit
	 */
	private static int charToHex(char c) {
		if(c >= 'A' && c <='F') {
			return c - 'A' + 10;
		}else {
			return c - '0';
		}
	}

}
