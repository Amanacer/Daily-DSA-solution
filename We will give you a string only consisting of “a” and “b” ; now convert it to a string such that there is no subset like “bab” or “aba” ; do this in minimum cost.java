import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();

        int[] prefix_a = new int[n + 1];
        int[] prefix_b = new int[n + 1];
        int[] suffix_a = new int[n + 2];
        int[] suffix_b = new int[n + 2];

        // Fill prefix_a
        prefix_a[0] = s.charAt(0) == 'a' ? 0 : 1;
        prefix_b[0] = s.charAt(0) == 'b' ? 0 : 1;

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            prefix_a[i] = prefix_a[i - 1] + (c == 'a' ? 0 : 1);
            prefix_b[i] = prefix_b[i - 1] + (c == 'b' ? 0 : 1);
        }

        // Fill suffix_a and suffix_b
        suffix_a[n - 1] = s.charAt(n - 1) == 'a' ? 0 : 1;
        suffix_b[n - 1] = s.charAt(n - 1) == 'b' ? 0 : 1;

        for (int i = n - 2; i >= 0; i--) {
            char c = s.charAt(i);
            suffix_a[i] = suffix_a[i + 1] + (c == 'a' ? 0 : 1);
            suffix_b[i] = suffix_b[i + 1] + (c == 'b' ? 0 : 1);
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // Split at i: first part [0..i], second part [i+1..n-1]
            int cost1 = prefix_a[i] + (i + 1 < n ? suffix_b[i + 1] : 0);
            int cost2 = prefix_b[i] + (i + 1 < n ? suffix_a[i + 1] : 0);
            result = Math.min(result, Math.min(cost1, cost2));
        }

        System.out.println(result);
    }
}
