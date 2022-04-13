package yeongseok.Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_2650_교차점개수_실패 {
	static class Cross{
		int cnt;
		Set<Integer> crossingLines;
		public Cross() {
			crossingLines = new HashSet<>();
		}
	}
	static class Point{
		double x,y;
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Point) {
				Point p = (Point)obj;
				if(this.x == p.x && this.y == p.y) return true;	
			}
			return false;
		}
	}
	static class Line{
		Point p1;
		Point p2;
		public Line(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	static Line[] lines;	//선분들
	static int lineSolution[];	//선분 별 해의 개수
 	static Map<Point,Cross> map;	// key == 두 선분의 해, value == Cross
	static final int MAX = 50;
	static int[] res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		lines = new Line[n/2];
		lineSolution = new int[n/2];
		map = new HashMap<>();
		res = new int[2];
		for(int i = 0 ; i <n/2; i++) {
			st = new StringTokenizer(br.readLine());
			Point p1 = makePoint(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			Point p2 = makePoint(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			lines[i] = new Line(p1,p2);
		}
		
		combination(0,0);
		System.out.println();
		
	}
	private static void combination(int resIndex, int targetIndex) {
		if(resIndex == 2) {
			//뽑힌 두 선분의 해를 구한다.
			intersection();
			return;
		}
		for(int i = targetIndex; i<lines.length; i++) {
			res[resIndex] = i;
			combination(resIndex+1, i+1);
		} 
	}
	/*
	 * 두 선이 교차하는 교차점 구하기
	 */
	private static void intersection() {
		Line line1 = lines[res[0]];
		Line line2 = lines[res[1]];
		
		//두 선의 방정식 구하기
		
		//y = ax + b
		double a = (line1.p1.y - line1.p2.y)/(line1.p1.x - line1.p2.x);	//기울기
		double b = line1.p1.y - line1.p1.x*a;
		
		//y = cx + d
		double c = (line2.p1.y - line2.p2.y)/(line2.p1.x - line2.p2.x);	//기울기
		double d = line2.p1.y - line2.p1.x*a;
		
		
		if(a==c) return; //기울기가 같으면 해가 존재할 수 없음
		
		double x = (d-b)/(a-c);
		if(x<0 || x>50) return;	//범위 밖의 해이면
		
		double y = a*x + b;
		if(y<0 || y>50) return;	//범위 밖의 해이면
		
		//범위 내의 해(x,y)가 존재하는 경우
		Point intersectPoint = new Point(x,y);
		if(!map.containsKey(intersectPoint)) {
			map.put(intersectPoint, new Cross());
		}
		Cross cross = map.get(intersectPoint);
		cross.cnt++;
		cross.crossingLines.add(res[0]);
		cross.crossingLines.add(res[1]);
		
		
	}
	private static Point makePoint(int type, int k) {
		switch(type) {
		case 1:
			return new Point(k,0);
		case 2:
			return new Point(k,MAX);
		case 3:
			return new Point(0,k);
		case 4:
			return new Point(MAX,k);
		}
		return null;
	}
}


















