package yeongseok;

import java.util.*;
import java.io.*;

public class Main_2841_외계인의기타연주{
    static int N;// 음의 수
    static int P;// 프렛의 수
    static int cnt=0;// 손가락 움직임 횟수
    static List<Stack<Integer>> stackList = new ArrayList<>();
    public static void main(String args[]) throws Exception{
        for(int i = 0; i<6; i++){
            stackList.add(new Stack<>());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        P = Integer.parseInt(split[1]);
        
        int line, prat;
        Stack<Integer> s;
        for(int i = 0; i<N; i++){
            split = br.readLine().split(" ");
            line = Integer.parseInt(split[0]);
            prat = Integer.parseInt(split[1]);
            
            s = stackList.get(line-1);
            while(!s.isEmpty() && s.peek() > prat){
                s.pop(); cnt++;
            }
            
            if(s.isEmpty() || s.peek() != prat){
                s.push(prat);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
