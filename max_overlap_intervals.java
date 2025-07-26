import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        long l, r;

        Pair(long l, long r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Pair other) {
            return Long.compare(this.l, other.l);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Pair[] ranges = new Pair[n];

        for (int i = 0; i < n; i++) {
            long l = sc.nextLong();
            long r = sc.nextLong();
            ranges[i] = new Pair(l, r);
        }

        Arrays.sort(ranges);

        TreeMap<Long, Integer> rSet = new TreeMap<>();
        ArrayList<Long> starts = new ArrayList<>();

        for (Pair range : ranges) {
            starts.add(range.l);
        }

        int maxOverlap = 0;

        for (int i = 0; i < n; i++) {
            long li = ranges[i].l;
            long ri = ranges[i].r;

            // Count how many previous r >= li
            int countBefore = 0;
            for (Map.Entry<Long, Integer> entry : rSet.tailMap(li, true).entrySet()) {
                countBefore += entry.getValue();
            }

            // Count how many future l <= ri
            int low = upperBound(starts, ri);
            int countAfter = low - (i + 1);

            int totalOverlap = countBefore + countAfter + 1; // including current interval
            maxOverlap = Math.max(maxOverlap, totalOverlap);

            // Insert current r to rSet
            rSet.put(ri, rSet.getOrDefault(ri, 0) + 1);
        }

        int answer = n - maxOverlap;
        System.out.println(answer);
    }

    static int upperBound(ArrayList<Long> arr, long key) {
        int low = 0, high = arr.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) <= key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
