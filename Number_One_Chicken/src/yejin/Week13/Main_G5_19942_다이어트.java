package yejin.Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_19942_다이어트 {
	static int N;

	static int minPay = Integer.MAX_VALUE;

	static class Nutrient {
		int a;
		int b;
		int c;
		int d;
		int price;
		
		public Nutrient() {
			super();
		}

		public Nutrient(int a, int b, int c, int d, int price) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.price = price;
		}
	}
	
	static Nutrient minNut = new Nutrient();
	static Nutrient[] food;
	static boolean isSelected[];
	static boolean minSelected[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		food = new Nutrient[N];
		
		isSelected = new boolean[N];
		minSelected = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		minNut = new Nutrient(a, b, c, d, 0);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());

			food[i] = new Nutrient(a, b, c, d, price);
		}

		subset(0, 0);
		StringBuilder sb= new StringBuilder();

		if(minPay == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			sb.append(minPay).append(System.lineSeparator());
			
			for (int i = 0; i < N; i++) {
				if(minSelected[i]) {
					sb.append((i+1)).append(" ");
				}
			}
			String answer = sb.toString();
			System.out.println(answer.substring(0,answer.length()-1));
		}
	}

	private static void subset(int cnt, int pay) {
		if (cnt == N) {
			int a = 0;
			int b = 0;
			int c = 0;
			int d = 0;
			int price= 0;
			
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					a += food[i].a;
					b += food[i].b;
					c += food[i].c;
					d += food[i].d;
					price += food[i].price;
					
					if(price > minPay) return;
				}
				
				if (a >= minNut.a && b >= minNut.b && c >= minNut.c && d >= minNut.d) {
					//최소값이 같으면
					if(minPay == price) {
						int originCnt = 0;
						int newCnt = 0;
						
						boolean isNew = false;
						//사전순
						for (int j = 0; j < N; j++) {
							if(minSelected[j]) {
								originCnt++;
							} 
							if(isSelected[j]) {
								newCnt++;
							}
							
							boolean isMore = false;
							if(originCnt < newCnt) {	//새로운 값이 더 빠른값
								//나머지(기존값)에서도 그 뒤에 값이 하나라도 있으면 true, 아니면 false
								for (int j2 = j; j2 < N; j2++) {
									if(minSelected[j2]) {
										isMore = true;
										break;
									}
								}
								if(isMore) {
									isNew = true;
								} else {
									isNew = false;
								}
								break;
							} else if(originCnt > newCnt) {	//기존값이 더 빠른값
								//나머지(기존값)에서도 그 뒤에 값이 하나라도 있으면 true, 아니면 false
								for (int j2 = j; j2 < N; j2++) {
									if(isSelected[j2]) {
										isMore = true;
										break;
									}
								}
								if(isMore) {
									isNew = false;
								} else {
									isNew = true;
								}
								break;
							}
						}
						
						if(isNew) {
							for (int j = 0; j < N; j++) {
								minSelected[j] = isSelected[j];
							}
						}
						
					} else {
						minPay = price;
						for (int j = 0; j < N; j++) {
							minSelected[j] = isSelected[j];
						}
					}
				}
			}
			return;
		}

		if (pay > minPay) return;

		isSelected[cnt] = true;
		subset(cnt + 1, pay + food[cnt].price);
		isSelected[cnt] = false;
		subset(cnt + 1, pay - food[cnt].price);
	}

}
