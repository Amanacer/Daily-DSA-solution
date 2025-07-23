import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] v = new long[n];
        for (int i = 0; i < n; i++) {
            v[i] = scanner.nextLong();
        }

        Arrays.sort(v);
        long my = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] <= 0) {
                long sum = 0;
                long k1 = 1;
                for (int j = i; j < n; j++) {
                    sum += k1 * v[j];
                    k1++;
                }
                my = Math.max(my, sum);
            } else {
                long sum = 0;
                long k1 = 1;
                for (int j = i; j < n; j++) {
                    sum += k1 * v[j];
                    k1++;
                }
                my = Math.max(my, sum);
                i = n;
            }
        }
        System.out.println(my);
    }
}
