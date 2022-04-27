package heejo.week11_0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * https://redbinalgorithm.tistory.com/167
 * 
 * Tri
 */
public class Main_G4_5052_전화번호목록_google2 {
	static int T;
    static int N;
    static final int NUMS = 10;

    static class Trie {
        boolean isEndWord;
        Trie children[];

        public Trie() {
            isEndWord = false;
            children = new Trie[NUMS];
            for (int i = 0; i < NUMS; ++i)
                children[i] = null;
        }
    }

    static Trie root;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            root = new Trie();
            String[] str = new String[N];
            for (int n = 0; n < N; n++) {
                str[n] = br.readLine();
                insert(str[n]);
            }

            boolean ans = true;

            for (int n = 0; n < N; n++) {
                if (!available(str[n])) {
                    ans = false;
                    break;
                }
            }
            
            if(ans)
                sb.append("YES\n");
            else
                sb.append("NO\n");

        }
        System.out.print(sb.toString());
    }
    

    static void insert(String key) {
        Trie curTrie = root;
        int length = key.length();
        int level;
        int index;

        for (level = 0; level < length; ++level) {
            index = key.charAt(level) - '0';
            if (curTrie.children[index] == null) {
                curTrie.children[index] = new Trie();
            }
            curTrie = curTrie.children[index];
        }
        curTrie.isEndWord = true;
    }

    static boolean available(String key) {
        Trie curTrie = root;
        int length = key.length();
        int level;
        int index;

        for (level = 0; level < length; ++level) {
            index = key.charAt(level) - '0';
            if (curTrie.isEndWord)
                return false;
            curTrie = curTrie.children[index];
        }

        return true;
    }


}
