package yejin.Week8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Algo2_서울_16반_박예진 {
	static boolean visited[][];	//측정가능한 구슬무게 체크할 배열(true:측정 가능,false:측정 불가)
	static int chuNum;			//추의 개수
	static int chu[];			//추 무게 저장 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	//입력받을 BufferedReader 선언
		StringBuilder sb = new StringBuilder();										//출력을 담을 StringBuilder
		StringTokenizer st = null;													//공백단위로 끊어서 읽을 StringTokenizer
		
		//숫자범위 입력받기
		chuNum = Integer.parseInt(br.readLine());					//추 개수
		chu = new int[chuNum+1];									//추 무게를 담을 배열을 추 개수 크기+1(chuNum)로 생성 (dfs arrayoutofIndex 안나게하려고 크기+1)
		visited = new boolean[chuNum+1][40001];
		st = new StringTokenizer(br.readLine());					//공백 단위로 입력 자르기
		for (int i = 0; i < chuNum; i++) {							//추 개수만큼 반복문 돌기
			chu[i] = Integer.parseInt(st.nextToken());				//추 배열(chu[])에 공백단위로 자른 값을 숫자로 변환하여 넣기
		}
		
		int ballNum = Integer.parseInt(br.readLine());				//구슬 개수
		int ball[] = new int[ballNum];								//구슬 무게를 담을 배열을 구슬 개수 크기(ballNum)로 생성
		st = new StringTokenizer(br.readLine());					//공백 단위로 입력 자르기
		for (int i = 0; i < ballNum; i++) {							//구슬 개수만큼 반복문 돌기
			ball[i] = Integer.parseInt(st.nextToken());				//구슬 배열(ball[])에 공백단위로 자른 값을 숫자로 변환하여 넣기
		}
		
		dp(0, chu[0]);											//dfs 호출(해당 추부터 시작)
		
		//측정가능한 구슬인지 체크해서출력 담는 배열
		for (int i = 0; i < ballNum; i++) {							//구슬 개수만큼 반복문 돌기
			if(visited[chuNum][ball[i]]) {									//측정가능한 구슬이면
				sb.append("Y");										//뒤 공백과 함께 "Y" 출력 StringBuilder에 담기
			}else {													//측정불가한 구슬이면
				sb.append("N");										//뒤 공백과 함께 "N" 출력 StringBuilder에 담기
			}
				
			if(i != ballNum-1) {									//마지막 반복문이 아니면
				sb.append(" ");										//공백 넣기
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	//출력을 위한 bufferedWriter 선언
		bw.write(sb.toString());	//출력
		bw.flush();					//bufferedWriter 플러쉬
		bw.close();					//bufferedWriter 닫기
	}

	private static void dp(int idx, int weight) {
		if( visited[idx][weight]) return;		//이미 체크한 무게면 리턴
		visited[idx][weight] = true;			//체크안된 무게면 체크
		
		if(idx == chuNum) return;					//추  개수크기만큼 다 돌면 러턴
		
		dp(idx+1, weight + chu[idx]);
		dp(idx+1, weight);
		dp(idx+1, Math.abs(weight - chu[idx]));
	}
}
