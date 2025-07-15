import java.util.*; // List, Queue, Scanner ke liye

public class MaxBlueInShortestPath {

    // BFS queue me ek node ke saath us tak ka distance aur blue count store karne ke liye class
    static class Pair {
        int node, dist, blue; // node number, ab tak ka distance, aur blue nodes ki sankhya
        Pair(int node, int dist, int blue) {
            this.node = node;
            this.dist = dist;
            this.blue = blue;
        }
    }

    // Yeh function shortest path me sabse zyada blue nodes count return karta hai
    public static int maxBlueInShortestPath(int V, List<List<Integer>> graph, char[] color, int src, int dest) {

        // visited[node][0] → us node tak ka shortest distance
        // visited[node][1] → us distance par sabse zyada blue nodes
        int[][] visited = new int[V + 1][2];

        // Pehle sabko infinity set karo (Integer.MAX_VALUE)
        for (int[] row : visited)
            Arrays.fill(row, Integer.MAX_VALUE);

        // BFS ke liye queue
        Queue<Pair> q = new LinkedList<>();

        // source node agar blue hai to blueCount = 1, warna 0
        int startBlue = color[src] == 'B' ? 1 : 0;

        // Queue me source node daal diya (start)
        q.add(new Pair(src, 0, startBlue));

        // source tak distance 0 aur blue count bhi set kar diya
        visited[src][0] = 0;
        visited[src][1] = startBlue;

        // Destination tak pahuchne pe blue nodes count store karne ke liye
        int maxBlueAtDest = -1;

        // BFS chalao
        while (!q.isEmpty()) {
            Pair curr = q.poll(); // ek node nikalo
            int node = curr.node;
            int dist = curr.dist;
            int blue = curr.blue;

            // Agar destination node mil gaya
            if (node == dest) {
                // Agar ab tak ka path chhota hai
                if (dist < visited[dest][0]) {
                    visited[dest][0] = dist; // shortest distance update
                    visited[dest][1] = blue; // blue count bhi update
                    maxBlueAtDest = blue;
                }
                // Agar distance same hai to blue count check karo
                else if (dist == visited[dest][0]) {
                    visited[dest][1] = Math.max(visited[dest][1], blue);
                    maxBlueAtDest = Math.max(maxBlueAtDest, blue);
                }
                continue; // destination mil gaya to next iteration me jao
            }

            // Har neighbor ke liye
            for (int nei : graph.get(node)) {
                // agar neighbor blue hai to blue count badhao
                int newBlue = blue + (color[nei] == 'B' ? 1 : 0);

                // Agar naya path chhota hai to update karo
                if (dist + 1 < visited[nei][0]) {
                    visited[nei][0] = dist + 1;
                    visited[nei][1] = newBlue;
                    q.add(new Pair(nei, dist + 1, newBlue)); // BFS me daal do
                }
                // Agar same distance hai, par zyada blue nodes mil rahe hain
                else if (dist + 1 == visited[nei][0] && newBlue > visited[nei][1]) {
                    visited[nei][1] = newBlue;
                    q.add(new Pair(nei, dist + 1, newBlue)); // update karke add karo
                }
            }
        }

        // Jab BFS pura ho jaye to destination ka max blue return karo
        return maxBlueAtDest;
    }

    // Driver function
    public static void main(String[] args) {
        int V = 6; // total nodes ki sankhya

        // graph banaya (1-based index ke liye ek dummy list add ki)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());

        // Edges add kiye (undirected graph)
        graph.get(1).add(2); graph.get(2).add(1);
        graph.get(2).add(3); graph.get(3).add(2);
        graph.get(1).add(4); graph.get(4).add(1);
        graph.get(4).add(5); graph.get(5).add(4);
        graph.get(5).add(3); graph.get(3).add(5);
        graph.get(3).add(6); graph.get(6).add(3);

        // Node colors: index 1 to V (R = red, B = blue)
        // index 0 is dummy
        char[] color = new char[] {'X', 'R', 'B', 'R', 'B', 'B', 'R'};

        // source aur destination nodes
        int src = 1, dest = 6;

        // Function call karke result store kiya
        int result = maxBlueInShortestPath(V, graph, color, src, dest);

        // Final result print kiya
        System.out.println("Answer: " + result); // Output expected: 2
    }
}
