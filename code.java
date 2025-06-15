import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        long[] p = new long[n + 1];
        long[] dp = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            p[i] = sc.nextLong();
        }
        dp[2] = p[1] - p[2]; 

        // Calculate minimum differences using dynamic programming
        for (int i = 3; i <= n; i++) {
            for (int j = i - 2; j >= 0; j--) {
                long difference = dp[j] + p[j + 1] - p[i];
                dp[i] = Math.min(dp[i], difference);
            }
        }

        // Output the result
        System.out.println(dp[n]);
        sc.close(); // Close the scanner to prevent resource leaks
    }
}
