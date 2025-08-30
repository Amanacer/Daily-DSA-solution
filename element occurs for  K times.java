class Solution {
    // Helper function: pehli occurrence
    static int firstOccurrence(int[] b, int x) {
        int low = 0, high = b.length - 1, ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (b[mid] == x) {
                ans = mid;
                high = mid - 1; // left side check karna
            } else if (b[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Helper function: last occurrence
    static int lastOccurrence(int[] b, int x) {
        int low = 0, high = b.length - 1, ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (b[mid] == x) {
                ans = mid;
                low = mid + 1; // right side check karna
            } else if (b[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Main function
    static int cl(int[] b, int n, int k) {
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int l1 = firstOccurrence(b, b[mid]);
            int h1 = lastOccurrence(b, b[mid]);

            int d = Math.abs(l1 - h1) + 1;

            if (d < k) {
                return b[mid];  // answer mil gaya
            }

            // yeh thoda ambiguous tha pseudo me: mai assume kar raha hun tum 
            // (h1 - low + 1) % k check karna chahte ho
            if (((h1 - low + 1) % k) == 0) {
                // [low..h1] ek valid block hai
                low = h1 + 1;
            } else {
                high = l1 - 1;
            }
        }

        return -1;
    }

    // Test
    public static void main(String[] args) {
        int[] b = {1, 1, 2, 2, 2, 3, 3};
        int n = b.length;
        int k = 3;
        System.out.println(cl(b, n, k));
    }
}
