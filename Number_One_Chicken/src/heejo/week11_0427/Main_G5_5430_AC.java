package heejo.week11_0427;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 아이디어
 *  - 양 끝에서 삽입삭제가 가능한 deque를 이용해보자
 *  - direction 변수를 통해서 앞에서부터 지울지, 뒤에서부터 지울지 정하기
 *  
 *  오답노트
 *  - 시간초과 발생
 *  - StringBuilder로 시간을 단축하자
 */

public class Main_G5_5430_AC {
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb = new StringBuilder();
			Deque<Integer> deque = new ArrayDeque<>();
			String func = br.readLine(); // 수행할 함수
			int func_len = func.length(); // 수행할 함수의 길이
			int N = Integer.parseInt(br.readLine()); // 수의 개수
			String temp = br.readLine(); // 수가 들어있는 배열
			if (N > 0) {
				temp = temp.substring(1, temp.length() - 1);
				String temp_split[] = temp.split(",");
				int temp_len = temp_split.length;
				// deque에 초기 배열 데이터 입력
				for (int i = 0; i < temp_len; i++) {
					deque.add(Integer.parseInt(temp_split[i]));
				}
			}
			exe(deque, func, func_len); // 실행
			bw.write(sb + "");
			bw.flush();
		}
		bw.close();
	}
	public static void exe(Deque<Integer> deque, String func, int func_len) {
		boolean reverse = false; // true는 역방향, false는 순방향
		for (int i = 0; i < func_len; i++) {
			switch (func.charAt(i)) {
			case 'R':
				reverse = (!reverse); // reverse 바꾸기
				break;
			case 'D':
				if (deque.isEmpty()) {
					sb.append("error\n");
					return;
				}
				if (reverse) {
					// 역방향일때
					deque.removeLast();
				} else {
					// 순방향일때
					deque.removeFirst();
				}
				break;
			}
		}
		int final_len = deque.size();
		sb.append("[");
		if (reverse) {
			for (int i = 0; i < final_len; i++) {
				sb.append(deque.removeLast());
				if (i != final_len - 1) {
					sb.append(",");
				}
			}
			sb.append("]\n");
		} else {
			for (int i = 0; i < final_len; i++) {
				sb.append(deque.removeFirst());
				if (i != final_len - 1) {
					sb.append(",");
				}
			}
			sb.append("]\n");
		}
		return;
	}
}
