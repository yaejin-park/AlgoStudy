package yeongseok.Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main_2668_숫자고르기 {
	static int n;
	static int map[][] = new int [3][101];
	static boolean check[][] = new boolean [3][101];
	static List<Integer> resList = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		//create map
		for(int i = 1; i<n+1;i++) {
			map[1][i] = i;
			map[2][i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1; i <n+1; i++) {
			if(!check[1][i]) {
				check[1][i] = true;
				if(recursive(i)) {
					resList.add(i);
				}
				check[1][i] = false;
			}
		}
		resList.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1, o2);
			}
		});
		sb.append(resList.size() + "\n");
		for(int i = 0 ; i <resList.size(); i++) {
			sb.append(resList.get(i)+ "\n");
		}
		System.out.print(sb.toString().trim());
	}
	/**
	 * 함수의 목적 : 원소가 일치하는 두 집합을 찾을 때까지 탐색한다.
	 * 방법 : 2행에 있는 값들중에(map[2][xx]), 1행의 index에 있는 값(map[1][index])과 같은 값이 존재하는지 탐색한다.
	 * 존재하는 경우
	 * 		case 1: map[1][xx]가 방문된적이 있는 경우
	 * 				-> 원소가 일치하는 두 집합이 생성됬다고 본다
	 * 				-> return true
	 * 		case 2: map[1][xx]가 방문된적이 없는 경우
	 * 				-> xx index에 대해서 재귀 호출을 한다.
	 * 
	 * 존재하지 않은 경우 - return false
	 * 
	 * @param index 검사 대상 index
	 * @return boolean 
	 */
	private static boolean recursive(int index) {
		int target = map[1][index];
		for(int i = 1; i <n+1; i++) {
			if(check[2][i]) continue;
			if(map[2][i] != target) continue;
			
			check[2][i] = true;
			
			if(check[1][i]) {
				return true;
				//resList에 결과를 저장해야 함.
			}else {
				check[1][i] = true;
				if(recursive(i)) {
					resList.add(i);
					return true;
				}
				check[1][i] = false;
				check[2][i] = false;
			}
			
		}
		return false;
	}

}
