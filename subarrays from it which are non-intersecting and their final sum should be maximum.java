import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n, k;
        n = scanner.nextInt();
        k = scanner.nextInt();

        long[] b = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextLong();
        }

        long[][][] dp = new long[10005][105][2];

        for (int i = 1; i <= 10000; i++) {
            for (int j = 1; j <= 100; j++) {
                dp[i][j][0] = Long.MIN_VALUE;
                dp[i][j][1] = Long.MIN_VALUE;
            }
        }

        dp[0][0][0] = 0;

        for (int l = 1; l <= k; l++) {
            for (int i = 1; i <= n; i++) {
                // dp[i][l][0]
                dp[i][l][0] = Math.max(dp[i - 1][l][0], dp[i - 1][l][1]);
                // dp[i][l][1]
                dp[i][l][1] = Math.max(b[i] + Math.max(dp[i - 1][l - 1][0], dp[i - 1][l - 1][1]), b[i] + dp[i - 1][l][1]);
            }
        }

        System.out.println(Math.max(dp[n][k][0], dp[n][k][1]));
    }
}
