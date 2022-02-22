package yeongseok;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1. 10억개의 경로는 2초내에 탐색이 불가하다.
 * 2. 백트레킹 + 메모이제이션을 이용해서 최대한 탐색을 줄여야함
 * 3. dfs를 사용해서 목적지에 도착할때까지 탐색한다.
 * 4. 탐색이 끝나고 재귀 함수가 하나씩 return 될때 현재 위치에서 목적지까지 가는 경로의 수를 return한다.
 * 5. 재귀함수에서 반환한 값을 MAP에 음수로 표기한다.
 * 6. 다른 재귀함수가 지도를 탐색할 때 음수값을 만나면 탐색을 더이상 하지 않고 사방에서 얻은 음수 값을 더해서 자기 위치에 표기한다.
 * 7. (0,0)위치의 음수값을 절댓값을 취해서 반환한다.
 * @author 노영석
 */
public class Main_1520_내리막길 {
    

    private static int m,n;

    private static int[][] map;
    private static int[][] dp;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m+1][n+1];
        dp = new int[m+1][n+1];

        for (int i=1; i<=m; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(1,1));

    }

    private static int dfs(int x, int y) {

        if (x == m && y == n) {
            //도달했을때 추가탐색 필요 없다
            return 1;
        }
        if (dp[x][y] != -1) {
            //-1이 아닌 경우는 이미 방문했다(메모이제이션)
            return dp[x][y];
        } else {
            //-1인 경우만 방문
            dp[x][y] = 0;
            for (int i=0; i<dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > m || ny > n) {
                    continue;
                }

                if (map[x][y] > map[nx][ny]) {
                    //현재 값이 더 큰 경우
                    dp[x][y] += dfs(nx, ny);
                }
            }

        }

        return dp[x][y];
    }//dfs
}