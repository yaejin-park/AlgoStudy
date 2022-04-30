package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 아이디어
 * - 증가하는 부분
 * - 일정한 부분
 * - 감소하는 부분
 * 세 곳으로 나눠서 계산하기
 * 
 * 오답노트
 * - 최대 높이의 시작점과 끝점을 찾는 과정에서 문제 발생.
 * - 오름차순으로 정렬을 한 후에 시작점과 끝점을 찾도록 하자.
 */

public class Main_S2_2304_창고다각형 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int array[][] = new int[N][2];
		int max_height_start[] = new int[2]; // 최대 높이의 위치와 값
		int max_height_end[] = new int[2]; // 최대 높이의 위치와 값
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			array[i][0] = Integer.parseInt(st.nextToken()); // 위치
			array[i][1] = Integer.parseInt(st.nextToken()); // 높이
		}
		// 배열 정렬
		Arrays.sort(array, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});
		
		for(int i =0; i<N; i++) {
			if (max_height_start[1] < array[i][1]) {
				max_height_start[0] = array[i][0]; // 최대 높이의 위치
				max_height_start[1] = array[i][1]; // 최대 높이의 값
			}
			// 최대값인 기둥즐 중 마지막 기둥의 위치를 기억하기
			if (max_height_start[1] == array[i][1]) {
				max_height_end[0] = array[i][0];
				max_height_end[1] = array[i][1];
			}
		}

		Stack<int[]> stack_asc = new Stack<>();
		Stack<int[]> stack_desc = new Stack<>();

		stack_asc.push(new int[] { 0, 0 });
		stack_desc.push(max_height_end);
		int total_area = 0;
		boolean asc = false;
		// 기둥으로 지붕 덮기
		for (int i = 0; i < N; i++) {
			// 오르막길인 경우
			if (array[i][0] <= max_height_start[0]) {
				asc = true;
				if (!stack_asc.isEmpty() && (stack_asc.peek()[1] > array[i][1])) {
					// 기존 스택보다 작다면 pass
					continue;
				} else {
					// 아닌 경우 stack에 추가
					stack_asc.push(array[i]);
				}
			}
			// 쭉 최대 높이 기둥들이라면
			else if (array[i][0] <= max_height_end[0]) {
				continue; // 얘는 따로 나중에 더한다.
			}
			// 이제 내리막길
			else {
				// stack의 값이 최대값이 나올 때까지 빼기
				while (!stack_desc.isEmpty() && (stack_desc.peek()[1] <= array[i][1])) {
					stack_desc.pop();
				}
				// stack에 추가
				stack_desc.push(array[i]);
			}
		}
		
		int temp_area_asc = 0;
		int temp_area_desc = 0;
		int temp_area_same = 0;
		
		// 오름차순의 창고 면적 계산하기
		while (!stack_asc.isEmpty()) {
			int temp[] = stack_asc.pop();
			if (stack_asc.isEmpty()) {
				break;
			}
			temp_area_asc += (temp[0] - stack_asc.peek()[0]) * stack_asc.peek()[1];
		}
		total_area += temp_area_asc;
		
		// 내림차순의 창고 면적 계산하기
		while (!stack_desc.isEmpty()) {
			int temp[] = stack_desc.pop();
			if (stack_desc.isEmpty()) {
				break;
			}
			temp_area_desc += (temp[0] - stack_desc.peek()[0]) * temp[1];
		}
		total_area += temp_area_desc;
		// 평평한 최대높이 길이의 창고 면적 계산하기
//		System.out.println(max_height_start[0]);
//		System.out.println(max_height_end[0]);
		temp_area_same = (max_height_end[0] - max_height_start[0] + 1) * max_height_start[1];
		total_area += temp_area_same;
//		System.out.println("ASC  : " + temp_area_asc);
//		System.out.println("DESC : " + temp_area_desc);
//		System.out.println("SAME : " + temp_area_same);
		System.out.println(total_area);
	}
}
