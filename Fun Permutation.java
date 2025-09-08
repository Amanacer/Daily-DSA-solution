import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = sc.nextInt(); // size of array
            long[] p = new long[n]; // array declaration fixed

            for (int i = 0; i < n; i++) {
                p[i] = sc.nextLong(); // read each element
            }

            for (int i = 0; i < n; i++) { // loop should go till n-1
                System.out.println((n + 1) - p[i]); // output calculation
            }
        }

        sc.close();
    }
}
