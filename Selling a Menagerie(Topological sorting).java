import java.util.*;

public class Main {

    // DFS function - ek component ke sare nodes visit karta hai
    static void dfs(long node, List<Long>[] finalGraph, List<Long> component, boolean[] visited) {
        visited[(int) node] = true;  // node visit ho chuka
        component.add(node);        // component me add karo

        // har neighbour ke liye DFS call
        for (long neighbor : finalGraph[(int) node]) {
            if (!visited[(int) neighbor]) {
                dfs(neighbor, finalGraph, component, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long t = sc.nextLong(); // kitne test cases hai

        while (t-- > 0) {
            long n = sc.nextLong(); // kitne nodes hain

            long[] a = new long[(int) n + 1];     // har node kis node pe point karta hai
            long[] in = new long[(int) n + 1];    // in-degree count karne ke liye
            long[] l = new long[(int) n + 1];     // unused array (ignore)
            long[] c = new long[(int) n + 1];     // har node ki cost
            List<Pair> p = new ArrayList<>();     // sare edges (i, a[i]) store karne ke liye
            long[] G = new long[(int) n + 1];     // direct mapping of a[i]

            // Graph banate hain
            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextLong();     // node i -> a[i]
                G[i] = a[i];              // same value G me store kar rahe
                in[(int) a[i]]++;         // a[i] ka in-degree badhao
                p.add(new Pair(i, a[i])); // edge store karo
            }

            // cost array input lo
            for (int i = 1; i <= n; i++) {
                c[i] = sc.nextLong();
            }

            // Queue banate hain topological sort ke liye
            Queue<Long> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (in[i] == 0) {          // jiska in-degree 0 ho
                    q.add((long) i);      // queue me dalo
                }
            }

            boolean[] added = new boolean[(int) n + 1]; // track karo kon topological sort me aa chuka

            // Topological sort start karo
            while (!q.isEmpty()) {
                long node = q.poll();
                System.out.print(node + " ");     // print karo node
                added[(int) node] = true;         // mark as added

                long u = G[(int) node];           // jiske upar point kar raha
                in[(int) u]--;                    // uska in-degree kam karo
                if (in[(int) u] == 0) {
                    q.add(u);                     // agar ab zero ho gaya to queue me dalo
                }
            }

            // Ab cycle waale nodes ke liye graph banate hain
            List<Long>[] finalGraph = new ArrayList[(int) n + 1];
            for (int i = 0; i <= n; i++) {
                finalGraph[i] = new ArrayList<>();
            }

            // Sirf unhi edges ko add karo jo topological me nahi aaye (cycle me ho sakte hain)
            for (Pair pair : p) {
                long x = pair.x, y = pair.y;
                if (!added[(int) x] && !added[(int) y]) {
                    finalGraph[(int) x].add(y); // edge x -> y add karo
                }
            }

            boolean[] visited = new boolean[(int) n + 1]; // DFS ke liye visited array

            // Har cycle component ko DFS se explore karo
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && !finalGraph[i].isEmpty()) {
                    List<Long> component = new ArrayList<>();
                    dfs(i, finalGraph, component, visited); // cycle find karo

                    // Minimum-cost node find karo component me
                    long minNode = component.get(0);
                    int minIndex = 0;

                    for (int j = 0; j < component.size(); j++) {
                        long node = component.get(j);
                        if (c[(int) node] < c[(int) minNode]) {
                            minNode = node;       // minimum cost update
                            minIndex = j;         // uska index bhi
                        }
                    }

                    // Component ko rotate karke print karo min-cost node se start karte hue
                    for (int j = minIndex + 1; j < component.size(); j++) {
                        System.out.print(component.get(j) + " ");
                    }
                    for (int j = 0; j <= minIndex; j++) {
                        System.out.print(component.get(j) + " ");
                    }
                }
            }

            System.out.println(); // newline after every test case
        }
        sc.close();
    }

    // Pair class - ek edge ko represent karta hai
    static class Pair {
        long x, y;

        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
\\