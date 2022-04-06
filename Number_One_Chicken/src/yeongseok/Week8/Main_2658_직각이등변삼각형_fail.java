package yeongseok.Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2658_직각이등변삼각형_fail{
	static class Point implements Comparable<Point>{
		int r,c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Point o) {
			if(r == o.r) return Integer.compare(c, o.c);
			return Integer.compare(r, o.r);
		}
	}
	static int type;
	static Point[] triPointList = new Point[3];
	static int index=0;
	static char map [][] = new char[10][10];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for(int i = 0 ; i < 10 ; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();
		}
		
		outer : for(int i = 0; i<10; i++) {
			for(int j = 0; j<10; j++) {
				if(map[i][j] == '0') continue;
				type = checkType(i,j);
				switch(type) {
				//2,3,4 타입의 삼각형을 1 타입의 삼각형으로 회전시킨다.
				case 2:	//시계방향 180도 회전
					rorate(2);
					break;
				case 3:	//시계방향 270도 회전
					rorate(3);
					break;
				case 4:	//시계방향 90도 회전	
					rorate(1);
					break;
				case 5:	//직각이등변삼각형의 형태가 아닐때
					System.out.println(0);
					return;
				}
				break outer;
			}
		}
		if(checkTri()) {
			switch(type) {
			case 2:
				unrotate(2);
				break;
			case 3:
				unrotate(3);
				break;
			case 4:
				unrotate(1);
				break;
			}
			Arrays.sort(triPointList);
			for(int i = 0 ; i <3; i++) {
				System.out.println((triPointList[i].r+1) + " " + (triPointList[i].c+1));
				//System.out.println((Math.abs(triPointList[i].c-9)+1) + " " + (triPointList[i].r+1));
			}
		}else {
			System.out.println(0);
		}
	}
	private static void unrotate(int num) {
		for(int i = 0 ; i <num; i++) {
			for(int j = 0; j<triPointList.length; j++) {
				int rr = triPointList[j].r;
				int cc = triPointList[j].c;
				
				triPointList[j].r = Math.abs(cc-9);
				triPointList[j].c = rr;
			}
		}
	}
	private static boolean checkTri() {
		int a=0,b=0;
		//최초로 1이 나타나는 지점 구하기
		outer : for(a =0 ;a <10; a++) {
			for(b = 0 ; b <10; b++) {
				if(map[a][b] == '1') {
					break outer;
				}
			}
		}
		if(a == 10 && b == 10) return false;
		
		triPointList[index++] = new Point(a,b);
		int height = 1;
		while(true) {
			//삼각형 테두리 밖이 1이면 삼각형이 아니다
			int left = b-height; int right = b+height;
			if((left > -1 && map[a][left] == '1') || (right<10&& map[a][right] == '1')) return false;
			
			//사이가 1로 채워져있지 않다면 삼각형이 아님
			for(int i = left+1; i<right; i++) {
				if(i < 0 ||  i > 9 || map[a][i] != '1') return false;
			}
			if(a+1 == 10) {
				triPointList[index++] = new Point(a,left+1);
				triPointList[index++] = new Point(a,right-1);
				break;
			}
			if(map[a+1][b] == '0') {
				for(int i = b-(height+1)+1; i<right+1; i++) {
					if(i > -1 && i < 10 && map[a+1][i] == '1') return false;
				}
				triPointList[index++] = new Point(a,left+1);
				triPointList[index++] = new Point(a,right-1);
				break;
				
			}
			
			height++; a++;
		
		}
		if(height>1) return true;
		return false;
	}
	private static void rorate(int num) {
		for(int n = 1; n<= num; n++) {
			char copy[][] = new char[10][10];
			
			for(int i = 0; i<10; i++) {
				for(int j = 0; j<10; j++) {
					copy[j][10-i-1] = map[i][j];
				}
			}
			map = copy;
		}
	}
	private static int checkType(int r, int c) {
		if((checkRange(r+1,c-1)&&map[r+1][c-1] == '1' )
				&& (checkRange(r+1,c+1)&&map[r+1][c+1] == '1')) return 1;
		
		if((checkRange(r,c+1)&&map[r][c+1] == '1' )
				&& (checkRange(r+1,c+1)&&map[r+1][c+1] == '1')) return 2;
		
		if((checkRange(r+1,c)&&map[r+1][c] == '1' )
				&& (checkRange(r+1,c+1)&&map[r+1][c+1] == '1')) return 3;
		
		if((checkRange(r+1,c)&&map[r+1][c] == '1' )
				&& (checkRange(r+1,c-1)&&map[r+1][c-1] == '1')) return 4;

		return 5;
	}
	private static boolean checkRange(int r, int c) {
		if(r < 0 || r> 9 || c <0 || c >9) return false;
		return true;
	}

}
