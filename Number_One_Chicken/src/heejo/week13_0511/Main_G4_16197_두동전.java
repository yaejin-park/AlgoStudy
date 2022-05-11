package week13_0511;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs를 이용해보자
 * 메모이제이션을 이용해서 시간 단축하기
 */
public class Main_G4_16197_두동전 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char array[][] = new char[N][M];	//지도. 다만 동전을 직접 올리지는 않는다.
		int coins[][] = new int[2][2];	//코인1과 코인2의 row, column
		boolean check[][] = new boolean[N*M][N*M];	//이미 만들어졌던 경우인지 체크
		Queue<int[]> queue= new LinkedList<>();
		int temp_index = 0;
		for(int i =0; i<N; i++) {
			String str = br.readLine();
			for(int j =0; j<M; j++) {
				if(str.charAt(j)=='o') {
					coins[temp_index][0] = i;
					coins[temp_index][1] = j;
					temp_index++;
					array[i][j] = '.';
				}
				else {
					array[i][j] = str.charAt(j);					
				}
			}
		}
		int result = -1;
		int dr[] = {-1, 1, 0, 0};
		int dc[] = {0, 0, -1, 1};
		
		//실행하기 전에, 현재 시작위치에 놓인 두 동전의 위치를 check배열에 넣어두기
		check[coins[0][0]*M+coins[0][1]][coins[1][0]*M+coins[1][1]] = true; 
		check[coins[1][0]*M+coins[1][1]][coins[0][0]*M+coins[0][1]] = true; 
		
		//BFS실행
		queue.offer(new int[] {coins[0][0], coins[0][1], 0});
		queue.offer(new int[] {coins[1][0], coins[1][1], 0});
		outer : while(!queue.isEmpty()) {
			int[][] coin = new int[2][];
			coin[0] = queue.poll();	//코인1
			coin[1] = queue.poll();	//코인2
			
			//버튼을 누르기
			coin[0][2] +=1;
			coin[1][2] +=1;
			
			//count가 10번보다 많다면
			if(coin[0][2]>10) {
				break;
			}
			for(int i =0; i<4; i++) {
				int[][] temp = new int[2][3];
				temp[0][0] = coin[0][0];
				temp[0][1] = coin[0][1];
				temp[0][2] = coin[0][2];
				temp[1][0] = coin[1][0];
				temp[1][1] = coin[1][1];
				temp[1][2] = coin[1][2];
				int coin_count = 2;
				//코인선택
				for(int j = 0; j<2; j++) {
					
					//다음 위치 확인
					int next_row = temp[j][0] + dr[i];
					int next_column = temp[j][1] + dc[i];
					
					//코인이 탈락하는지 확인
					if(next_row<0 || next_row>=N || next_column<0 || next_column>=M) {
						//범위 밖이라면 코인 떨구고 탈락
						next_row = -1;
						next_column = -1;
						coin_count--;
						continue;
					}
					//코인이 벽을 만났는지 확인
					else if(array[next_row][next_column] == '#') {
						next_row -= dr[i];
						next_column -= dc[i];
					}
					
					temp[j][0] = next_row;
					temp[j][1] = next_column;
				}
				
				//코인이 1개라면 결과 출력
				if(coin_count==1) {
					result = temp[0][2];
					break outer;
				}
				//코인이 2개라면 큐에 삽입
				else if(coin_count==2) {
					//만들어지지 않았던 배치인 경우에만 새롭게 등록
					if(check[temp[0][0]*M+temp[0][1]][temp[1][0]*M+temp[1][1]]==false) {
						queue.offer(temp[0]);
						queue.offer(temp[1]);
						check[temp[0][0]*M+temp[0][1]][temp[1][0]*M+temp[1][1]] = true;
						check[temp[1][0]*M+temp[1][1]][temp[0][0]*M+temp[0][1]] = true;
					}
				}
			}
		}
		System.out.println(result);
	}
}
