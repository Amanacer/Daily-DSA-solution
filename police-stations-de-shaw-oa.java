//Find the total sum of putting all the members; make sure the sum is minimum using pq (police)
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 2;  // Initial positions ki count (kitne log already available hain)
        int k = 4;  // Humein k naye log bulane hain

        // Min-heap priority queue based on distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Map to mark which numbers/positions are already used (visited)
        Map<Integer, Boolean> used = new HashMap<>();

        int sum = 0;  // Total distance ka sum store karega

        // Starting positions (already available log)
        int[] pol = {7, 8};

        // Har initial position ko queue mein daal rahe hain with distance = 0
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{0, pol[i]});  // {distance, position}
        }

        int count = 1;  // Kitne log total bulaye ja chuke hain (initial log bhi include)
        while (count <= k + n) {  // Jab tak k+n log nahi ho jate
            int[] top = pq.poll();  // Minimum distance wale element ko nikaal rahe
            int dist = top[0];      // Distance
            int current = top[1];   // Position (ya value)

            // Agar current position pehle se use nahi hui
            if (!used.getOrDefault(current, false)) {
                sum += dist;  // Distance ko total sum mein add karo
                System.out.println(dist + " " + current);  // Distance + Position print karo

                // Uske right position ko add karo queue mein (agar already used nahi hai)
                if (!used.getOrDefault(current + 1, false)) {
                    pq.offer(new int[]{dist + 1, current + 1});
                }

                // Uske left position ko bhi add karo (agar already used nahi hai)
                if (!used.getOrDefault(current - 1, false)) {
                    pq.offer(new int[]{dist + 1, current - 1});
                }

                count++;  // Ek naya person cover ho gaya
                used.put(current, true);  // Mark current position as used
            }
        }

        // Final distance ka sum print karo
        System.out.println(sum);
    }
}
