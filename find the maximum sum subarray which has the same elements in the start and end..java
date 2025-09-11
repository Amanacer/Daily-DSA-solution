import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long i = 1;
        long answer = Long.MIN_VALUE;
        Map<Long, Long> kk = new HashMap<>();
        long sum = 0;

        while (i <= n) {
            long yy = sc.nextLong();

            answer = Math.max(answer, yy);

            if (!kk.containsKey(yy)) {
                kk.put(yy, sum);
            } else {

                long gg = sum + yy - kk.get(yy);
                answer = Math.max(answer, gg);
                kk.put(yy, Math.min(kk.get(yy), sum));
            }
            sum = sum + yy;
            i++;
        }

        System.out.println(answer);

        sc.close();
    }
}
