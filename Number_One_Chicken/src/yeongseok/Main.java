package yeongseok;

import java.util.*;
import java.io.*;
/**
    1. 수열 앞에서부터 차례로 NGE를 구하려고 한다면, 수열이 내림차순일 경우 시간초과가
       발생한다. -> 뒤에서부터 처리해야한다.
    2. stack 사용해야함.
        -> ex) {a,b,c,d,e} 수열이 존재할 때 b의 NGE를 구하려면 e->d->c 순서로
        자료구조에 넣고, NGE여부 검사는 c->d->e 순서로 하기때문에 LIFO 구조여야 한다.    
        
    3. 출력용 stack도 사용해야함.
        -> 뒤에서부터 처리한 결과를 stack에 쌓고 마지막 결과 출력 단계에서는 stack에 
        요소들을 차례로 pop 시킨다.(그래야지 순서대로 출력할 수 있다.)
*/

public class Main{
    static int N;
    static int[] arr;
    static Stack<Integer> s = new Stack<>();
    static Stack<Integer> output = new Stack<>();
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        String[] split = br.readLine().split(" ");
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(split[i]);
        }
        
        for(int i = N-1; i> -1; i--){
            while(!s.isEmpty() && s.peek() <= arr[i]){
                s.pop();
            }
            if(s.isEmpty()) output.push(-1);
            else output.push(s.peek());
            
            s.push(arr[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            sb.append(output.pop() + " ");
        }
        
        System.out.print(sb.toString());
    }
}









