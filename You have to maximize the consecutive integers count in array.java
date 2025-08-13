import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] dp = new long[250000];
        long[] g = new long[250000];

        long n = scanner.nextLong();
        Map<Long, Long> kk = new HashMap<>();
        for (long i = 1; i <= n; i++) {
            long yy = scanner.nextLong();
            kk.put(yy, kk.getOrDefault(yy, 0L) + 1);
        }

        long answer = 0;
        for (long i = 1; i <= 100000 + 1; i++) {
            if (kk.getOrDefault(i, 0L) >= 1) {
                dp[(int) i] = 1 + dp[(int) (i - 1)];
            } else {
                if (kk.getOrDefault(i - 1, 0L) >= 1) {
                    dp[(int) i] = 1 + g[(int) (i - 1)];
                } else {
                    dp[(int) i] = 0;
                }
            }

            if (kk.getOrDefault(i, 0L) >= 2) {
                g[(int) i] = 1 + dp[(int) (i - 1)];
            } else if (kk.getOrDefault(i, 0L) == 1) {
                if (kk.getOrDefault(i - 1, 0L) >= 1) {
                    g[(int) i] = 1 + g[(int) (i - 1)];
                } else {
                    g[(int) i] = 0;
                }
            } else {
                g[(int) i] = 0;
            }

            answer = Math.max(answer, dp[(int) i]);
            // System.out.println(dp[(int) i]);
            // System.out.println();
        }
        System.out.println(answer);
    }
}
