import java.util.Scanner;
 
public class Main {
    static long[] b;
    static long[] c;
 
    static boolean check(long vl, long n, long d) {
        long tot = 0;
        for (long i = 1; i <= n; i++) {
            if (b[(int) i] >= vl) {
 
            } else {
                long u = vl / b[(int) i];
                long g = 0;
                if (vl % b[(int) i] == 0) {
                    g = vl / b[(int) i];
                } else {
                    g = vl / b[(int) i] + 1;
                }
                tot = tot + (g - 1) * c[(int) i];
            }
        }
        return tot <= d;
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
 
        b = new long[100005];
        c = new long[100005];
 
        long t = (long) 1e18;
        for (long i = 1; i <= n; i++) {
            b[(int) i] = scanner.nextLong();
        }
        for (long i = 1; i <= n; i++) {
            c[(int) i] = scanner.nextLong();
        }
        long d = scanner.nextLong();
        long low = 1;
        long high = d;
        long ooKK = 0;
        long k = 0;
        while (low <= high && k == 0) {
            long mid = (low + high) / 2;
            if (check(mid, n, d)) {
                if (check(mid + 1, n, d)) {
                    low = mid + 1;
                } else {
                    ooKK = mid;
                    k = 1;
                }
            } else {
                high = mid - 1;
            }
        }
 
        System.out.println(ooKK);
    }
}