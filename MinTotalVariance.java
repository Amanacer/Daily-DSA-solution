import java.util.*;

public class MinTotalVariance {
    public static int minimizeVariance(int[] arr) {
        int n = arr.length;

        // Step 1: Sort the array
        Arrays.sort(arr);

        // Step 2: DP table
        int[][] dp = new int[n][n];

        // Step 3: Base Case - for size 2 subarrays
        for (int i = 0; i + 1 < n; i++) {
            dp[i][i + 1] = arr[i + 1] - arr[i];
        }

        // Step 4: Fill DP table for bigger intervals
        for (int length = 3; length <= n; length++) {
            for (int i = 0; i + length - 1 < n; i++) {
                int j = i + length - 1;

                dp[i][j] = Math.min(
                    dp[i][j - 1] + arr[j] - arr[i],
                    dp[i + 1][j] + arr[j] - arr[i]
                );
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4};
        System.out.println(minimizeVariance(arr));  // Output: 4
    }
}
