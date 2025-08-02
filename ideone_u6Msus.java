import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] a = new int[n + 1];
        int[] p = new int[n + 1];
        int[] s = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }

        for (int j = 1; j <= n; j++) {
            int i = j - 1;
            while (i >= 1) {
                if (a[i] < a[j]) {
                    p[j] = p[j] + 1;
                }
                i--;
            }
        }

        for (int k = 1; k <= n; k++) {
            int l = k + 1;
            while (l <= n) {
                if (a[k] < a[l]) {
                    s[k] = s[k] + 1;
                }
                l++;
            }
        }

        int gg = 0;
        int j = 1;
        while (j <= n) {
            int k = j + 1;
            while (k <= n) {
                if (a[j] < a[k]) {
                    gg = gg + p[j] * s[k];
                }
                k++;
            }
            j++;
        }

        System.out.println(gg);
    }
}
