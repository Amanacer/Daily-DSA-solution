import java.util.*;
import java.io.*;

public class Main {
    static class MultiSet {
        private TreeMap<Long, Integer> map = new TreeMap<>();

        void add(long x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        void remove(long x) {
            if (map.containsKey(x)) {
                if (map.get(x) == 1) map.remove(x);
                else map.put(x, map.get(x) - 1);
            }
        }

        long getMin() {
            return map.firstKey();
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        long[] vl = new long[n + 1];
        int k = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            vl[i] = sc.nextLong();
        }

        long[] dp = new long[n + 1];
        Arrays.fill(dp, (long) 1e18);
        dp[0] = 0;

        MultiSet d1 = new MultiSet();
        d1.add(dp[0]);

        for (int i = 1; i <= n; i++) {
            dp[i] = d1.getMin() + vl[i];
            d1.add(dp[i]);
            if (i - k >= 0) {
                d1.remove(dp[i - k]);
            }
        }

        System.out.println(dp[n]);
    }

    // ---------- FastReader ----------
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
