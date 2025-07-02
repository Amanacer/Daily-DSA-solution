import java.util.*;

public class TreeHeights {
    static final int N = 100005;
    static List<Integer>[] tree = new ArrayList[N];
    static int[] height1 = new int[N]; // longest downward path
    static int[] height2 = new int[N]; // second longest
    static int[] longestV = new int[N];

    public static void dfs(int node, int parent) {
        int max1 = 0, max2 = 0;

        for (int child : tree[node]) {
            if (child != parent) {
                dfs(child, node);
                int h = height1[child];
                if (h > max1) {
                    max2 = max1;
                    max1 = h;
                } else if (h > max2) {
                    max2 = h;
                }
            }
        }

        height1[node] = 1 + max1;
        height2[node] = max2;

        if (height2[node] == 0) {
            longestV[node] = height1[node]; // only one path exists
        } else {
            longestV[node] = height1[node] + height2[node] - 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(1, -1); // start DFS from node 1

        for (int i = 1; i <= n; i++) {
            System.out.println("Node " + i + " => Height1: " + height1[i] + ", Height2: " + height2[i] + ", Longest V: " + longestV[i]);
        }
    }
}
