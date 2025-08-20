import java.util.Scanner;

public class Main {
    static final int MOD = 1000000007;
    static long[][] dp;
    static long[][] v;
    static long[][] h;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        dp = new long[1005][1005];
        v = new long[1005][1005];
        h = new long[1005][1005];

        dp[1][1] = 1;

        int i = 1;
        while (i <= n) {
            int j = 1;
            while (j <= m) {
                // up
                dp[i][j] = (dp[i][j] + v[i - 1][j]) % MOD;

                // left
                dp[i][j] = (dp[i][j] + h[i][j - 1]) % MOD;

                h[i][j] = (h[i][j - 1] + dp[i][j]) % MOD;
                v[i][j] = (v[i - 1][j] + dp[i][j]) % MOD;

                j++;
            }
            i++;
        }

        System.out.println(dp[n][m] % MOD);

        scanner.close();
    }
}
