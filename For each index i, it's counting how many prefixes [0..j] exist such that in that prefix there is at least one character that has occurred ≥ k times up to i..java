import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input le lo: string aur integer k
        String s = scanner.next();
        int k = scanner.nextInt();

        int n = s.length();

        // Har character ka index list store karne ke liye
        Map<Integer, List<Integer>> y = new HashMap<>();

        int cnt = 0; // Final answer

        // Loop har character par chalega
        for (int i = 0; i < n; i++) {
            int maxi = -1; // Valid start index ka max value track karega

            // Current character ka integer index (0 to 25)
            int charIndex = s.charAt(i) - 'a';

            // Map me current character ka index add kar do
            y.computeIfAbsent(charIndex, key -> new ArrayList<>()).add(i);

            // Check all 26 characters
            for (int ch = 0; ch < 26; ch++) {
                // Agar character ch k baar nahi aaya hai ab tak, skip
                if (!y.containsKey(ch) || y.get(ch).size() < k)
                    continue;

                // agar aaya hai, toh uska k-th last occurrence nikalo
                // i.e., agar list = [2, 5, 7, 9], k=3 => index = 7
                // y.get(ch).size() - k => k-th last
                maxi = Math.max(maxi, y.get(ch).get(y.get(ch).size() - k));
            }

            // agar koi valid prefix mila
            if (maxi != -1)
                cnt = cnt + (maxi + 1); // starting indices 0 to maxi tak honge => maxi+1
        }

        // Answer print karo
        System.out.println(cnt);
    }
}
