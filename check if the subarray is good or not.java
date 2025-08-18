import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] b = new int[n + 2];
        b[0] = -1;
        b[n + 1] = (int) 1e9;
        for (int i = 1; i <= n; i++)
            b[i] = scanner.nextInt();

        int[] prefix = new int[n + 2];
        int[] suffix = new int[n + 2];

        suffix[n] = 1;
        suffix[n + 1] = 1;

        int i = n - 1;
        while (i >= 1) {
            if (b[i] < b[i + 1] && suffix[i + 1] == 1) {
                suffix[i] = 1;
            } else {
                break;
            }
            i--;
        }

        prefix[0] = 1;
        prefix[1] = 1;
        i = 2;
        while (i <= n) {
            if (b[i - 1] < b[i] && prefix[i - 1] == 1) {
                prefix[i] = 1;
            } else {
                break;
            }
            i++;
        }

        int count = 0;
        for (i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (b[i - 1] < b[j + 1] && prefix[i - 1] == 1 && suffix[j + 1] == 1) {
                    count++;
                }
            }
        }

        System.out.println("Count of good subarrays: " + (count - 1)); // subtracting 1 to remove case when whole array can be deleted
    }
}
