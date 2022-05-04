package yejin.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_S2_2304_창고다각형 {
	//x 오름차순
	static class PillarXComparator implements Comparator<Pillar>{
		@Override
		public int compare(Pillar o1, Pillar o2) {
			return o1.x - o2.x;
		}
	}
	
	//h 내림차순
	static class PillarHComparator implements Comparator<Pillar>{
		@Override
		public int compare(Pillar o1, Pillar o2) {
			return o2.h - o1.h;
		}
	}
	
	
	static class Pillar {
		int x;
		int h;
		
		public Pillar(int x, int h) {
			super();
			this.x = x;
			this.h = h;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int maxH = 0;	//가장 높은 기둥 높이
		int minL = 1001;//가장 높은 기둥의 x좌표 최소
		int maxL = 0;	//가장 높은 기둥의 x좌표 최대
		
		int maxX = 0;	//마지막 기둥의 x좌표
		
		ArrayList<Pillar> arr = new ArrayList<>(N); 
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			if(h > maxH) {
				maxH = h;
				maxL = l;
				minL = l;
			} else if(h==maxH) {
				maxL = Math.max(maxL, l);
				minL = Math.min(minL, l);
			}
			maxX = Math.max(maxX, l);
			arr.add(new Pillar(l, h));
		}
		
		//x순서대로 정렬
		Collections.sort(arr, new PillarXComparator());
//		for (int i = 0; i < N; i++) {
//			System.out.println(arr.get(i).x +","+arr.get(i).h);
//		}
		
		int curH = 0;				//현재 H
		int curX = arr.get(0).x;	//첫번째 x
		int answer = 0;
		
		//최대 높이 전까지 넓이 더하기
		for (int i = 0; i <N; i++) {
			Pillar pillar = arr.get(i);
			int x = pillar.x;
			int h = pillar.h;
			
			//높이가 더 높으면
			if(curH < h) {
				//그때까지의 넓이 더하기
				answer += (x - curX) * curH;	
//				System.out.println(x+","+h+":"+answer);
				curX = x;
				curH = h;						
//				System.out.println("1 : "+answer);
			} 
			
			//최대높이에 도달하면
			if(x == minL) {
//				System.out.println(x+"끝"+answer);
				break;
			}
		}
		
		//최대높이의 넓이 더하기
		answer += (maxL - minL+1) * maxH;
//		System.out.println("2 : "+answer);
		curX = maxL;	//현재 좌표x 업데이트해주기
		
		//높이 순 정렬
		Collections.sort(arr, new PillarHComparator());
//		for (int i = 0; i < N; i++) {
//			System.out.println(arr.get(i).h);
//		}
		
		//높이 높은것부터 빼기
		for (int i = 0; i <N; i++) {
			Pillar pillar = arr.get(i);
			
			int x = pillar.x;
			int h = pillar.h;
			
			//현재 x좌표보다 더 크면
			if(x > curX) {
				//넓이 더하기
				answer += (x -curX) * h;
//				System.out.println(x+","+h+":"+answer);
//				System.out.println("3 : "+answer);
				curX = x;
				curH = h;
			} else {	//현재 도달한 x좌표보다 작으면 스킵
				continue;
			}
			
			//끝까지 가면
			if(x == maxX) {
				answer += (x-curX) * h;
//				System.out.println((x-curX)+","+h+":"+answer);
				break;
			}
		}
		
		System.out.println(answer);
	}

}
