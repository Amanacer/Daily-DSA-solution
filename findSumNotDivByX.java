import java.util.*;

public class Main {

    public static int findSumNotDivByX(int[] arr, int x) {
        // Special case: if x == 1, all numbers % 1 == 0, so no valid subarray
        if (x == 1) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int currentSum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            int mod = currentSum % x;

            if (mod != 0) {
                maxLength = Math.max(maxLength, i + 1);
            }

            if (!map.containsKey(mod)) {
                map.put(mod, i);
            }

            // If the same mod has appeared before
            if (map.containsKey(mod)) {
                maxLength = Math.max(maxLength, i - map.get(mod));
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int result = findSumNotDivByX(arr, x);
        System.out.println(result);
    }
}
