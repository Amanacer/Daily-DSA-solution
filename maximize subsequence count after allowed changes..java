class Solution {
    public int maxSubsequenceWithKChanges(String s1, String s2, int K) {
        int n = s1.length();
        int m = s2.length();

        // dp[i][j][k] = max number of subsequences of s2[0..j]
        // in s1[0..i] with exactly k changes
        int[][][] dp = new int[n + 1][m + 1][K + 1];

        // Base case:
        // dp[0][*][*] = 0 (empty prefix of s1 can't contain s2 subsequence)
        // dp[*][0][*] = 1 (empty s2 always occurs as subsequence)

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= K; k++) {
                dp[i][0][k] = 1; // empty subsequence always possible
            }
        }

        // Fill DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k <= K; k++) {

                    // Option 1: Skip current char of s1
                    dp[i][j][k] = dp[i - 1][j][k];

                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        // Option 2: Match without change
                        dp[i][j][k] = Math.max(dp[i][j][k],
                                dp[i - 1][j - 1][k] + dp[i - 1][j][k]);
                    } else {
                        // Option 2: Change current char (if k > 0)
                        if (k > 0) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i - 1][j - 1][k - 1] + dp[i - 1][j][k - 1]);
                        }
                    }
                }
            }
        }

        // Final answer: max across all k from 0..K
        int ans = 0;
        for (int k = 0; k <= K; k++) {
            ans = Math.max(ans, dp[n][m][k]);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s1 = "abca";
        String s2 = "aa";
        int K = 1;
        System.out.println(sol.maxSubsequenceWithKChanges(s1, s2, K));
    }
}
