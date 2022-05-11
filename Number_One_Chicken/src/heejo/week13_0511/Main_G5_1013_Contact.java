package week13_0511;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 분석
 * 1 -> 0 2개이상 -> 1 1개이상
 * 01
 * 
 */
public class Main_G5_1013_Contact {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String str = br.readLine();
			int length = str.length();
			boolean result = calc(str, length);
			if(result == true) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}

	public static boolean calc(String str, int length) {

		// 01 제거하기
		for (int i = 0; i < length - 1; i++) {
			if (str.charAt(i) == '0' && str.charAt(i + 1) == '1') {
				if (i == 0 || str.charAt(i - 1) != '0') {
					str = str.substring(0, i) + "##" + str.substring(i + 2);
				}
			}
		}

		String[] array = str.split("##");

		// 100+1+가 성립하는지 전부 확인
		for (int i = 0; i < array.length; i++) {
			if(array[i].length() == 0) {
				continue;
			}
			boolean temp = false;
			boolean stage1 = false;
			boolean stage2 = false;
			boolean stage3 = false;
			int stage2_zero_count = 0;
			int stage3_one_count = 0;
			for (int j = 0; j < array[i].length(); j++) {
				// 첫 글자는 1
				if (stage1 == false) {
					if (array[i].charAt(j) == '1') {
						stage1 = true;
						continue;
					} else {
						break;
					}
				}
				// 끝은 1
				else {
					// 중간은 0 2개이상
					if (stage2 == false) {
						if (array[i].charAt(j) == '0') {
							stage2_zero_count++;
							continue;
						}
						else {
							if (stage2_zero_count >= 2) {
								j--;
								stage2 = true;
								continue;
							}
							else {
								return false;
							}
						}
					}
					//끝은 1 여러개
					else if (stage3 == false) {
						if(array[i].charAt(j)=='1') {
							stage3_one_count++;
							temp = true;
							continue;
						}
						else {
							if (stage3_one_count >= 2) {
								j--;
								stage2 = false;
								stage3 = false;
								temp = false;
								stage2_zero_count = 0;
								stage3_one_count = 0;
								continue;
							}
							else {
								return false;
							}
							
						}
					}
				}
			}
			if (temp == false) {
				return false;
			}
		}

		return true;
	}
}
