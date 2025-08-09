import java.util.*;
public class ColorComponentPairs {
    static long c = 0; // current component ka size

    // DFS: color-wise component ko traverse karega
    public static void dfs(int node, List<Integer>[] G, int[] used, int[] parent) {
        used[node] = 1; // node ko visited mark karo
        c++; // component size badhao

        for (int u : G[node]) {
            if (used[u] == 0) {
                parent[u] = node; // parent set karo
                dfs(u, G, used, parent); // recursive DFS call
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of nodes

        int[][] s = new int[n][2]; // edge list
        int[] b = new int[n + 1];  // node colors (1-based indexing)

        // Graph banane ke liye adjacency list
        List<Integer>[] G = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            G[i] = new ArrayList<>();
        }

        // Input edges (tree edges)
        for (int i = 1; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            s[i][0] = u;
            s[i][1] = v;
        }

        // Node ke color input lo
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextInt();
        }

        // Graph ko build karo — sirf unhi nodes ko jodo jinke colors same hain
        for (int i = 1; i < n; i++) {
            int u = s[i][0];
            int v = s[i][1];

            // agar color same hai to edge lagao
            if (b[u] == b[v]) {
                G[u].add(v);
                G[v].add(u);
            }
        }

        int[] used = new int[n + 1];   // visited array for DFS
        int[] parent = new int[n + 1]; // parent tracking
        Arrays.fill(parent, -1);       // initially -1

        long ans = 0; // final answer

        // Har unvisited node ke liye DFS chalao
        for (int i = 1; i <= n; i++) {
            if (used[i] == 0) {
                c = 0; // component size reset
                dfs(i, G, used, parent);

                // har component ka triplet/pair count nikaalo
                ans += (c - 1) * (c - 2) / 2;
            }
        }

        System.out.println(ans); // final answer print karo
        sc.close(); // Scanner close
    }
}



