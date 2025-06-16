import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ValidPairsCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements
        int n = scanner.nextInt();

        // Read the array b (1-based indexing)
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        // Initialize prefix sum array
        int[] p = new int[n + 1];
        p[0] = 0; // Base case for prefix sum

        // Compute prefix sums
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] + b[i];
        }

        // HashMap to store frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1); // Initialize with the base case

        long c = 0; // Counter for valid pairs

        // Main logic to count valid pairs
        for (int j = 1; j <= n; j++) {
            int RHS = p[j] - j; // Calculate RHS

            // Count how many times RHS has appeared in previous indices
            c += freq.getOrDefault(RHS, 0);

            // Update frequency for the current prefix sum
            int keyToIncrement = p[j - 1] - (j - 1);
            freq.put(keyToIncrement, freq.getOrDefault(keyToIncrement, 0) + 1);
        }

        // Print the result
        System.out.println(c);

        scanner.close(); // Close the scanner
    }
}
