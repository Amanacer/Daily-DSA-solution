import java.util.*;

public class Main {
    public static boolean isPossible(int mid, int[] comp, int[] stock, int[] cost, long budget) {
        long totalCost = 0;
        for (int i = 0; i < comp.length; i++) {
            long required = (long) comp[i] * mid;
            if (required > stock[i]) {
                long toBuy = required - stock[i];
                totalCost += toBuy * cost[i];
                if (totalCost > budget) return false;
            }
        }
        return totalCost <= budget;
    }

    public static int maxAlloys(int[] comp, int[] stock, int[] cost, int budget) {
        int low = 0, high = (int) 1e9;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(mid, comp, stock, cost, budget)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] composition = {2, 2, 3, 1};
        int[] stock = {3, 2, 1, 4};
        int[] cost = {2, 3, 1, 6};
        int budget = 30;

        int result = maxAlloys(composition, stock, cost, budget);
        System.out.println(result); // Output should be 3
    }
}
