package yejin.Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_1013_Contact {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String type1 = "1023";	//2는 0의+, 3은 1의+
		String type2 = "01";
		int type;
		boolean finished = false;
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String answer = "YES";
			String str = br.readLine();
			
			int idx = 1;
			finished = false;
			if(str.charAt(0)=='1') {
				type = 1;
			}else {
				type = 2;
			}
			
			
			for (int j = 1, len = str.length(); j < len; j++) {
				char cur = str.charAt(j);
				if(type == 1) {
					char form = type1.charAt(idx);
					if(form == '3') {			//1+
						//1이나오면 ㄱㅊ, 0이나면 타입2일 가능성뿐
						if(cur=='0') {
							type = 2;
							idx = 1;
						} else {
							finished = true;
						}
					} else if(form == '2' ){	//0+
						if(cur=='1') {
							idx++;
							j--;
						} 
					} else {
						if(cur == form) {
							idx++;
						} else{
							answer = "NO";
							break;
						}
					}
				} else {	//type2
					if(cur != '1') {
						answer = "NO";
						break;
					}else {
						finished = true;
						j++;
						if(j < len) {
							finished = false;
							idx = 1;
							if(str.charAt(j) == '1') {
								type = 1;
							} else {
								type = 2;
							}
						}
					}
				}
			}
			sb.append(finished? answer : "NO").append(System.lineSeparator());
		}
		System.out.println(sb);
	}

}
