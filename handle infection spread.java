import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE; // Bahut bada number jo infinite ko represent karta hai

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // Graph ke nodes ki sankhya
        int m = sc.nextInt(); // Graph ke edges ki sankhya

        int[] vl = new int[n + 1]; // Har node ka status: 0 = infected, 1 = safe

        List<List<Integer>> G = new ArrayList<>(); // Adjacency list ke roop mein graph

        for (int i = 0; i <= n; i++) {
            G.add(new ArrayList<>()); // Har node ke liye empty list
        }

        for (int i = 1; i <= n; i++) {
            vl[i] = sc.nextInt(); // Har node ka initial infection status input
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // edge ka ek endpoint
            int v = sc.nextInt(); // edge ka doosra endpoint
            G.get(u).add(v); // u se v ko jodne wala edge
            G.get(v).add(u); // v se u ko jodne wala edge (undirected graph)
        }

        int k = sc.nextInt(); // Infection kitni door tak phail sakta hai (distance k tak)

        // Universal node (0) ke saath ek naya graph banaya gaya
        List<List<Integer>> newG = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            newG.add(new ArrayList<>(G.get(i))); // purane graph ko copy kar rahe
        }

        // Sabhi pehle se infected nodes ko node 0 se connect kar rahe
        for (int i = 1; i <= n; i++) {
            if (vl[i] == 0) {
                newG.get(0).add(i); // 0 → infected node
                newG.get(i).add(0); // infected node → 0
            }
        }

        // BFS lagakar infection ko distance k tak phaila rahe
        int[] lvl = new int[n + 1]; // Har node ka level (0 se doori)
        Arrays.fill(lvl, INF); // Shuru mein sabhi ka level infinite
        Queue<Integer> q = new LinkedList<>(); // BFS queue
        q.add(0); // Universal node se BFS start
        lvl[0] = 0; // Universal node ka level 0

        while (!q.isEmpty()) {
            int v = q.poll(); // Current node nikali queue se
            for (int u : newG.get(v)) {
                if (lvl[u] == INF) { // Agar u pehle visit nahi hua
                    lvl[u] = lvl[v] + 1; // Uska level update karo
                    if (lvl[u] <= k + 1) { // Agar infection range ke andar hai
                        vl[u] = 0; // Is node ko bhi infected mark karo
                        q.add(u); // BFS ke liye queue mein daalo
                    }
                }
            }
        }

        // Saare infected nodes ko print karo
        for (int i = 1; i <= n; i++) {
            if (vl[i] == 0) {
                System.out.println(i);
            }
        }

        // Ab safe nodes par BFS karke 1 se n tak shortest path find karo
        int[] dist = new int[n + 1]; // Distance array
        Arrays.fill(dist, INF); // Initially sabka distance infinite

        if (vl[1] == 1) { // Agar starting node safe hai
            q.add(1); // BFS start node 1 se
            dist[1] = 0; // Starting node ka distance 0
        }

        while (!q.isEmpty()) {
            int v = q.poll(); // Queue se ek node nikali
            for (int u : G.get(v)) {
                if (vl[u] == 1 && dist[u] == INF) { // Agar u safe hai aur visit nahi hua
                    dist[u] = dist[v] + 1; // Distance update karo
                    q.add(u); // Queue mein daalo
                }
            }
        }

        // Agar destination tak pahuch sakte hain toh distance print karo, nahi toh -1
        System.out.println(dist[n] == INF ? -1 : dist[n]);

        sc.close(); // Scanner band karo
    }
}
