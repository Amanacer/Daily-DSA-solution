import java.util.*; // Scanner and List ke liye

public class TreeHeights {
    static final int N = 100005; // Max number of nodes

    // Har node ke neighbours store karne ke liye tree
    static List<Integer>[] tree = new ArrayList[N];

    // height1[node] => node ke neeche ka sabse lamba path (maximum depth)
    static int[] height1 = new int[N];

    // height2[node] => node ke neeche ka doosra sabse lamba path
    static int[] height2 = new int[N];

    // longestV[node] => agar node ek V ka center ho, to maximum V shape length kya hai
    static int[] longestV = new int[N];

    // ========================= DFS Function ===========================
    public static void dfs(int node, int parent) {
        int max1 = 0, max2 = 0; // max1 = longest child height, max2 = second longest

        // Har child ke liye DFS
        for (int child : tree[node]) {
            if (child != parent) { // parent ko skip karte hain
                dfs(child, node); // child pe DFS

                int h = height1[child]; // child ka longest height nikaalo

                // Top 2 heights maintain karni hain
                if (h > max1) {
                    max2 = max1; // pehle max1 ko max2 bna do
                    max1 = h;    // current child ki height ko max1 bna do
                } else if (h > max2) {
                    max2 = h; // agar sirf second max se badi hai toh update max2
                }
            }
        }

        // height1[node] = 1 (khud ka level) + sabse bada child path
        height1[node] = 1 + max1;

        // height2 = second longest child path
        height2[node] = max2;

        // longestV[node] calculate karo:
        if (height2[node] == 0) {
            // Agar second longest path nahi mila, toh V shape nahi ban sakti
            longestV[node] = height1[node]; // sirf ek side ka path hi consider
        } else {
            // V shape => left branch + right branch + center node
            // But height1 already 1 add kar chuka => subtract 1 for center
            longestV[node] = height1[node] + height2[node] - 1;
        }
    }

    // ========================= Main Function ===========================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Number of nodes

        // Har node ke liye empty list initialize
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // n-1 edges ka input lo (since tree has n-1 edges)
        for (int i = 1; i < n; i++) {
            int u = sc.nextInt(); // edge ka pehla node
            int v = sc.nextInt(); // edge ka doosra node

            // Undirected tree => dono taraf edge lagani padegi
            tree[u].add(v);
            tree[v].add(u);
        }

        // DFS start from root node 1, parent is -1 (kyunki root ka parent koi nahi hota)
        dfs(1, -1);

        // Final result print karo
        for (int i = 1; i <= n; i++) {
            System.out.println("Node " + i + 
                " => Height1: " + height1[i] + 
                ", Height2: " + height2[i] + 
                ", Longest V: " + longestV[i]);
        }
    }
}
