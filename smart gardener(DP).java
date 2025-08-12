import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] power = new long[n + 1];
        long[] cost = new long[n + 1];
        long[] dp = new long[n + 1];

        for (int i = 0; i <= n; i++) {
            power[i] = sc.nextLong();
        }

        for (int i = 0; i <= n; i++) {
            cost[i] = sc.nextLong();
        }

        for (int i = 1; i <= n; i++) {
            long g = Long.MAX_VALUE;
            long j = i;

            while (j >= 0) {
                if (power[(int) j] != 0) {
                    long left = j - power[(int) j];
                    long right = j + power[(int) j];

                    if (right >= i) {
                        long p = Long.MAX_VALUE;

                        if (left <= 0) {
                            // then you don't need to add any extra cost apart from turning on sprinkler
                            p = Math.min(p, cost[(int) j]);
                        } else {
                            p = Math.min(p, cost[(int) j] + dp[(int) left]);
                        }

                        g = Math.min(g, p);
                    }
                }

                j--;
            }

            dp[i] = g;
        }

        if (dp[n] == Long.MAX_VALUE) {
            dp[n] = -1;
        }

        System.out.println(dp[n]);
    }
}
