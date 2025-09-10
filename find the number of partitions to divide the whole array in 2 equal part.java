import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();

        while (t-- > 0) {
            long p = 0;
            HashMap<Long, Long> k = new HashMap<>();
            long n = sc.nextLong();
            long[] b = new long[(int) n + 1];
            long tot = 0;

            for (int i = 1; i <= n; i++) {
                b[i] = sc.nextLong();
                tot += b[i];
            }

            long s = 0;
            HashMap<Long, Long> sf = new HashMap<>();

            for (int i = (int) n; i >= 1; i--) {
                s += b[i];
                sf.put(s, sf.getOrDefault(s, 0L) + 1);
            }

            long d = tot;
            for (int i = 1; i <= n; i++) {
                sf.put(d, sf.get(d) - 1);
                d -= b[i];
                long r = 0;
                long change = -1 * b[i];
                long newsum = tot + change;

                if (newsum % 2 == 0) {
                    long needsum = newsum / 2;
                    r += k.getOrDefault(needsum, 0L);
                    r += sf.getOrDefault(needsum, 0L);
                }

                System.out.println(r);
                p += b[i];
                k.put(p, k.getOrDefault(p, 0L) + 1);
            }

            long good = 0;
            if (tot % 2 == 0) {
                long current_sum = 0;
                for (int i = 1; i <= n - 1; i++) {
                    current_sum += b[i];
                    if (current_sum == tot / 2) {
                        good++;
                    }
                }
            }

            System.out.println(good);
        }

        sc.close();
    }
}
