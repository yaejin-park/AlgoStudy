package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * <> 태그안이면 그대로 출력
 * 태그 안이 아니면, 거꾸로 출력(stack에 쌓았다가 공백을 만나면 하나씩 팝해서 출력)
 * 거꾸로 출력 -> 입력은 차곡차곡 출력은 반대로
 * @author yaejin
 *
 */

public class Main_S3_17413_단어뒤집기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String S = br.readLine();
		br.close();
		
		Stack<Character> stack = new Stack<>();
		
		//태그 안인지 여부 체크
		boolean isTag = false;
		
		for (int i = 0, end = S.length(); i < end; i++) {
			//현재 인덱스의 character
			char cur = S.charAt(i);
			
			if(isTag) {	//태그 안이면
				if(cur =='>') {
					isTag = false;
					sb.append('>');
				} else {
					sb.append(cur);
				}
			} else {	//태그 밖이면
				if(cur =='<') {	//태그시작이면
					//스택 빌때까지 출력
					while(!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					isTag = true;
					sb.append('<');
				} else if(cur ==' ' || i==end-1) { //공백이면&마지막 글자면
					if(i==end-1) {
						stack.push(cur);
					}
					//스택 빌때까지 출력
					while(!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					if(cur==' ') {
						sb.append(' ');
					} 
				} else {
					stack.push(cur);
				}
			}
		}
		System.out.println(sb);
	}
}
