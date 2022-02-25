package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_S5_2941_크로아티아알파벳 {
	/**
	 * 	8개의 크로아티아 알파벳은 총 2-3 캐릭터로 이루어짐
	 *  총 단어의 길이에서 크로아티아 문자일경우, 2캐릭터는 -1, 3캐릭터는 -2하면 될듯
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		br.close();
		
		int answer = str.length();
		boolean check = false;
		
		String croAlpha[] = {"c=","c-","d-","lj","nj","s=","z="};
		//"dz="는 혼자만 세글자여서 따로 떼서 생각
		
		String word = "";
		
		for (int i = 0, end = str.length(); i < end; i++) {
			char cur = str.charAt(i);
			
			//체크 중
			if(check) {
				word += cur;
				
				//두번째 글자까지 포함한 결과
				for (int j = 0; j < 7; j++) {
					if(croAlpha[j].equals(word)) {
						answer--;
						check = false;
						word = "";
						break;
					}
				}
				
				//배열에 맞는 글자가 없는 경우이므로
				if(check) {
					if(word.equals("dz")) {
						//계속 찾기
					} else if(word.equals("dz=")){
						answer -= 2;
						check = false;
						word = "";
					} else {
						//알파벳이 아닌 경우,
						check = false;
						word = "";
						//여기서 또 알파벳 체크 시작
						if(cur=='c' || cur=='d' || cur=='l' || cur=='n' ||  cur=='s' || cur=='z') {
							check = true;
							word +=cur;
						} 
					}
				}
			}else {
//				체크할 글자 시작
				if(cur=='c' || cur=='d' || cur=='l' || cur=='n' ||  cur=='s' || cur=='z') {
					check = true;
					word +=cur;
				} 
			}
		}
		System.out.println(answer);
	}

}
