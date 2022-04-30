package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * A-B-C-D-E라는 친구관계가 형성된다면 true, 아니면 false
 * 
 * 리스트를 만들어서 파도타기
 * BFS로 했다가 메모리초과(queue때문에 그런듯). DFS로 해보기
 */
public class Main_G5_13023_ABCDE {
	static int result = 0;
	static ArrayList<Integer>[] arrayList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람 수
		int M = Integer.parseInt(st.nextToken()); // 친구 관계 수
		arrayList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			arrayList[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arrayList[a].add(b);
			arrayList[b].add(a);
		}

		check = new boolean[N];
		for(int i = 0; i<N; i++) {
			if(result==0) {
				check[i] = true;
				calc(i, 0);
				check[i] = false;
			}
		}
		System.out.println(result);
	}
	
	static boolean check[];
	static void calc(int num, int count) {
		if(count==4) {
			result = 1;
			return;
		}
		else {
			int size =arrayList[num].size(); 
			for(int i =0; i<size; i++) {
				if(check[arrayList[num].get(i)]) {
					continue;
				}
				else {
					check[arrayList[num].get(i)]=true;
					calc(arrayList[num].get(i), count+1);
					check[arrayList[num].get(i)]=false;
				}
			}
		}
	}

}
