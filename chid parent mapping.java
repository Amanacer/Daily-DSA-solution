import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 🔢 Step 1: Read input size
        int n = sc.nextInt();
        int[] a = new int[n]; // Array of size n

        // 📥 Step 2: Read array elements
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // 📥 Step 3: Read Q (pairs) and build parent → child mapping
        int q = sc.nextInt();
        Map<Integer, Integer> parent = new HashMap<>(); // parent[L] = R

        for (int i = 0; i < q; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            parent.put(l, r);
        }

        // 🧠 Step 4: Initialize frequency map
        Map<Integer, Integer> freq = new HashMap<>();

        int count = 0;

        // 🔁 Step 5: Process array elements one by one
        for (int j = 0; j < n; j++) {
            int val = a[j];

            // ✅ Add current frequency of this element's children
            count += freq.getOrDefault(val, 0);

            // 🔄 Now if this element is a parent, update its child's frequency
            if (parent.containsKey(val)) {
                int child = parent.get(val);
                freq.put(child, freq.getOrDefault(child, 0) + 1);
            }
        }

        // 🖨️ Output final count
        System.out.println(count);
    }
}
