import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: n = number of rankings (rows), m = number of elements (per row)
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // c[i][j] = how many times element i appeared before j
        int[][] c = new int[500][500];

        // b stores the current ranking row
        List<Integer> b = new ArrayList<>();

        // g[i] = list of nodes (elements) that should come after i (directed edge: i → j)
        List<Integer>[] g = new ArrayList[m];

        // in[i] = in-degree of node i (used in topological sort)
        int[] in = new int[m];

        // Initialize graph, in-degree and temporary array
        for (int i = 0; i < m; i++) {
            b.add(0);                // placeholder for current ranking values
            in[i] = 0;               // initially, in-degree is 0
            g[i] = new ArrayList<>(); // empty adjacency list
        }

        // Read all n rankings
        for (int i = 0; i < n; i++) {
            // Read 1 row of rankings into list 'b'
            for (int l = 0; l < m; l++) {
                b.set(l, scanner.nextInt()); // ranking of element at position l
            }

            // Count pairwise wins: if x appears before y in ranking → c[x][y]++
            for (int i1 = 0; i1 < m; i1++) {
                for (int j1 = i1 + 1; j1 < m; j1++) {
                    int x = b.get(i1); // x comes before y
                    int y = b.get(j1);
                    c[x][y] = c[x][y] + 1;
                }
            }
        }

        // Build graph based on pairwise majority
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (c[i][j] > (n / 2)) {
                    // More than half voted i before j → edge i → j
                    g[i].add(j);
                    in[j]++;
                } else if (n % 2 == 0) {
                    // Tie case (only valid for even n)
                    if (c[i][j] == (n / 2)) {
                        g[i].add(j); // Arbitrarily choose i → j
                        in[j]++;
                    } else {
                        g[j].add(i);
                        in[i]++;
                    }
                } else {
                    // Odd number of rankings but c[i][j] ≤ n/2 → j → i
                    g[j].add(i);
                    in[i]++;
                }
            }
        }

        // Topological Sort using Kahn's Algorithm
        Queue<Integer> q = new LinkedList<>();

        // Add all nodes with in-degree 0 to the queue
        for (int i = 0; i < m; ++i) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        // Process the queue
        while (!q.isEmpty()) {
            int current = q.poll(); // remove node with in-degree 0
            System.out.print(current + " "); // print node in topological order

            // Reduce in-degree of neighbors, add them if in-degree becomes 0
            for (int neighbor : g[current]) {
                in[neighbor]--;
                if (in[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }
    }
}
