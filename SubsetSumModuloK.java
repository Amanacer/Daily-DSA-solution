import java.util.Scanner;

public class SubsetSumModuloK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        long n = sc.nextLong(); // Number of elements
        long m = sc.nextLong(); // Modulo value
        long k = sc.nextLong(); // Maximum number of elements in the subset

        long[] b = new long[(int) n + 1];
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextLong();
        }

        long[][][] dp = new long[(int) n + 1][(int) m][(int) k + 1];
        dp[0][0][0] = 1; // Base case

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                for (int l1 = 0; l1 <= k; l1++) {
                    dp[i][j][l1] += dp[i - 1][j][l1]; // Do not include b[i]

                    if (l1 > 0) { // Include b[i] only if l1 > 0
                        long v1 = b[i] % m;
                        long l;
                        if (j >= v1) {
                            l = j - v1;
                        } else {
                            l = m - v1 + j;
                        }
                        dp[i][j][l1] += dp[i - 1][(int) l][l1 - 1]; // Include b[i]
                    }
                }
            }
        }

        System.out.println(dp[(int) n][0][(int) k]); // Count of subsets with sum divisible by m using exactly k elements
        sc.close();
    }
}
