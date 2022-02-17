package yeongseok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
/**
    1. 자신을 볼 수 있는 빌딩을 수를 센다.
    2. stack에 빌딩들을 차례로 넣는다.
    3. 하나의 빌딩을 넣을 때 마다 그 빌딩보다 높이가 작거나 같은 빌딩들을 스텍에서 pop
    4. 앞에서부터 순차적으로 넣은 빌딩들을 3번에서, 가장 마지막에 넣은 빌딩부터 높이를 비교하기때문에
       last in first out 이므로 스택을 사용한다.
    5. 8만개가 내림차순으로 입력으로 주어질 경우에 대비해서 cnt를 long type으로 선언해야 한다...ㅎ
*/
public class Main_6198_옥상정원꾸미기{
    static int N;    // 빌딩 수
    static Stack<Integer> s = new Stack<>();
    static long cnt=0;    //벤치마킹 가능한 빌딩 수의 합
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int k;
        for(int i = 0; i<N; i++){
            k = Integer.parseInt(br.readLine());
            //현재 입력으로 들어온 빌딩보다 높이가 작거나 같은 빌딩은 pop
            while(!s.isEmpty() && s.peek() <= k){
                s.pop();
            }
            cnt += s.size();
            s.push(k);
        }
        System.out.println(cnt);
    }
}