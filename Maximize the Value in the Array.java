import java.util.Scanner;
import java.util.Vector;

public class Main {
    static Vector<Long> b = new Vector<>();
    static long n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        b.clear();
        n = scanner.nextLong();
        for (int i = 0; i <= n + 5; i++) {
            b.add(0L); // Initialize each element individually
        }
        for (int i = 1; i <= n; i++) {
            b.set(i, scanner.nextLong());
        }

        long low = 0;
        long k = 1;
        long high = getMaxValue(b);
        long rr = (long) 1e18;

        while (low <= high && k == 1) {
            long md = (low + high) / 2;

            if (check(md) && !check(md - 1)) {
                rr = md;
                k = 0;
            } else if (check(md)) {
                high = md;
            } else {
                low = md + 1;
            }
        }

        System.out.println(rr);
    }

    static boolean check(long vl) {
        Vector<Long> gg = new Vector<>(b);
        long i = n;

        while (i >= 2) {
            if (b.get((int) i) >= vl) {
                long diff = Math.abs(b.get((int) i) - vl);
                b.set((int) (i - 1), b.get((int) (i - 1)) + diff);
            }
            i--;
        }

        if (b.get(1) <= vl) {
            b = gg;
            return true;
        }

        b = gg;
        return false;
    }

    static long getMaxValue(Vector<Long> vector) {
        long max = Long.MIN_VALUE;
        for (long value : vector) {
            max = Math.max(max, value);
        }
        return max;
    }
}
