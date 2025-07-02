import java.util.*;

public class TreeDP {

    static final int MAX = 200005; // Maximum size of nodes (for sieve + arrays)

    // Tree DP arrays
    static long[] down = new long[MAX]; // down[node] = subtree mein kitne non-prime nodes hain
    static long[] up = new long[MAX];   // up[node] = root se lekar current node ke upar tak kitne non-prime nodes hain
    static boolean[] pr = new boolean[MAX]; // pr[i] = true if i is a prime number

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sieve(MAX); // Prime numbers ko pehle hi mark kar lete hain (0 to MAX)

        int n = sc.nextInt(); // Number of nodes
        List<List<Integer>> G = new ArrayList<>(); // Tree ka adjacency list

        // Har node ke liye ek empty list add kar rahe hain
        for (int i = 0; i <= n + 2; i++) G.add(new ArrayList<>());

        // Tree edges input le rahe hain
        for (int i = 1; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            G.get(x).add(y); // x se y edge
            G.get(y).add(x); // y se x edge (undirected tree)
        }

        int[] parent = new int[n + 5]; // Parent array for each node
        Arrays.fill(parent, -1); // Sabhi ko -1 se init kar diya

        boolean[] used = new boolean[n + 5]; // DFS mein visited check karne ke liye

        dfs_down(1, G, used, parent); // DFS chala rahe hain root=1 se to fill down[]

        // BFS for calculating up[]
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 5];
        visited[1] = true;
        q.offer(1); // root node se BFS start

        // BFS for calculating up[] values
        while (!q.isEmpty()) {
            int s = q.poll(); // current node
            int par = parent[s]; // iska parent node
            int d = 0;

            if (!pr[s]) {
                d = (int) down[s] + 1; // agar current node non-prime hai to uska contribution bhi jod lo
            }

            if (par != -1) {
                long gg = up[par] + 1; // parent ke upar jitne non-primes the, unka count +1 (parent khud)
                if (!pr[par]) {
                    gg = gg + down[par] - d; // parent ka pura down leke, apne subtree ka hissa minus kar diya
                }
                up[s] = gg; // up value store kar diya
            }

            // BFS traversal
            for (int adj : G.get(s)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    q.offer(adj);
                }
            }
        }

        long rr = 0; // Final result

        // Har node pe check karte hain agar prime hai to
        for (int i = 1; i <= n; i++) {
            if (pr[i]) {
                rr += down[i] * up[i]; // Prime ke neeche aur upar ke non-primes ke beech pairing

                List<Long> v = new ArrayList<>(); // har child ka non-prime subtree collect karte hain
                for (int u : G.get(i)) {
                    if (u != parent[i] && !pr[u]) {
                        v.add(down[u] + 1); // us non-prime child ka full subtree + khud
                    }
                }

                rr += kk(v) + up[i]; // subtree combinations + upper non-primes ka contribution
            }
        }

        System.out.println(rr); // Final answer print
    }

    // ============================== DFS DOWN ==============================
    static void dfs_down(int node, List<List<Integer>> G, boolean[] used, int[] parent) {
        used[node] = true; // current node visited
        for (int u : G.get(node)) {
            if (!used[u]) {
                parent[u] = node; // parent assign
                dfs_down(u, G, used, parent); // child pe DFS
            }
        }

        long count = 0; // down count
        for (int u : G.get(node)) {
            if (u != parent[node] && !pr[u]) {
                count += 1 + down[u]; // non-prime child ke contribution ko count mein jod do
            }
        }
        down[node] = count; // final down value
    }

    // ============================== SIEVE ==============================
    static void sieve(int n) {
        Arrays.fill(pr, true); // sab prime assume kar lete hain
        pr[0] = pr[1] = false; // 0 and 1 are not prime
        for (int i = 2; i * i <= n; i++) {
            if (pr[i]) {
                for (int j = i * i; j <= n; j += i) {
                    pr[j] = false; // multiples of i are not prime
                }
            }
        }
    }

    // ============================== KK FUNCTION ==============================
    static long kk(List<Long> v) {
        long sum = 0;

        for (long val : v) sum += val; // total sum of all elements

        long total = sum; // start with total = sum
        for (long val : v) {
            sum -= val; // sum mein se current hatao
            total += val * sum; // val * sum of all values after it (cross multiplication)
        }
        return total; // final sum of all a[i]*a[j] + sum(v)
    }
}
