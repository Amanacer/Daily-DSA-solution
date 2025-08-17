import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        
        long[] b = new long[(int)n + 1];
        long[] p = new long[(int)n + 1];
        
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextLong();
        }
        
        for (int i = 2; i <= n; i++) {
            if (b[i] < b[i - 1]) {
                p[i] = b[i - 1] - b[i];
                b[i] = b[i - 1];
            }
        }
        
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            if (p[i] > p[i - 1]) {
                sum += (p[i] - p[i - 1]);
            }
        }
        
        System.out.println(sum);
    }
}
