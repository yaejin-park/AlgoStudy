package yejin.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_2564_경비원 {
	static int width,height, totLength;
	static Store dg;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());

		//총 둘레
		totLength = 2 * (width + height);

		int s = Integer.parseInt(br.readLine());	//상점수
		Store []stores = new Store[s];
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int gap = Integer.parseInt(st.nextToken());
			
			stores[i] = new Store(dir, gap);
		}
		
		//동근 위치
		st = new StringTokenizer(br.readLine());
		int dir = Integer.parseInt(st.nextToken());
		int gap = Integer.parseInt(st.nextToken());
		dg = new Store(dir, gap);
		
		int sum = 0;
		for (int i = 0; i < s; i++) {
			sum += findDistance(stores[i]);
		}
		System.out.println(sum);
	}

	static private int findDistance(Store store) {
		int storeDir= store.dir;
		int storeGap= store.gap;
		
		switch (storeDir) {
		case 1:
			switch (dg.dir) {
			case 1:
				return Math.abs(storeGap-dg.gap);
			case 2:
				return Math.min( height+storeGap+dg.gap , totLength - (height+storeGap+dg.gap));
			case 3:
				return storeGap+dg.gap;
			case 4:
				return width-storeGap+dg.gap;
			}
			break;
		case 2:
			switch (dg.dir) {
			case 1:
				return Math.min( height+storeGap+dg.gap , totLength - (height+storeGap+dg.gap));
			case 2:
				return Math.abs(storeGap-dg.gap);
			case 3:
				return storeGap + height-dg.gap;
			case 4:
				return width-storeGap+height-dg.gap;
			}
			break;
		case 3:
			switch (dg.dir) {
			case 1:
				return storeGap + dg.gap;
			case 2:
				return height-storeGap+dg.gap;
			case 3:
				return Math.abs(storeGap-dg.gap);
			case 4:
				return Math.min(width+storeGap+dg.gap, totLength-(width+storeGap+dg.gap));
			}
			break;
		case 4:
			switch (dg.dir) {
			case 1:
				return storeGap+width-dg.gap;
			case 2:
				return height-storeGap+width-dg.gap;
			case 3:
				return Math.min(width+dg.gap+storeGap , totLength - (width+dg.gap+storeGap));
			case 4:
				return Math.abs(storeGap-dg.gap);
			}
			break;
		}
		return 1;
	}
}

class Store{
	int dir;
	int gap;
	
	public Store(int dir, int gap) {
		super();
		this.dir = dir;
		this.gap = gap;
	}
}
