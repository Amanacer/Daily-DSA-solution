import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long k = scanner.nextLong();
        List<Long> y = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            y.add(scanner.nextLong());
        }

        Collections.sort(y);
        long d = y.get(y.size() - 1) - y.get(0);
        long v = 0;
        long low = 1;
        long b = 0;
        long high = d;

        while (low <= high && b == 0) {
            long mid = (low + high) / 2;

            if (check(y, k, mid)) {
                if (!check(y, k, mid + 1)) {
                    v = mid;
                    b = 1;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }

        System.out.println(v);
    }

    public static boolean check(List<Long> y, long k, long g) {
        long p = y.get(0);
        long c = 0;

        for (int i = 1; i < y.size(); i++) {
            long diff = Math.abs(p - y.get(i));

            if (diff >= g) {
                c++;
                p = y.get(i);
            }
        }

        return c + 1 >= k;
    }
}
