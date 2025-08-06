import java.util.*;

class Solution {

    // ➤ Function: Ek number ko fixed size string banata hai, jisme leading zeros add kiye jaate hain
    // Example: num = 10, digits = 3 => "010"
    private String makeDigit(int num, int digits) {
        String s = String.valueOf(num);
        while (s.length() < digits) {
            s = "0" + s;
        }
        return s;
    }

    // ➤ Function: Ek number se sabhi possible 0, 1, and 2-swap wale strings generate karta hai
    private Set<String> makeSwapChanges(int num, int digits) {
        // Pehle number ko fixed digit string bana lo
        String s = makeDigit(num, digits);

        // Set to store all unique strings
        Set<String> poss = new HashSet<>();
        poss.add(s); // original string (0 swap)

        // Convert string to char array for easy swapping
        char[] arr = s.toCharArray();

        // ➤ Sab possible 1st swap
        for (int i = 0; i < digits; i++) {
            for (int j = i + 1; j < digits; j++) {
                swap(arr, i, j); // 1st swap
                String after1Swap = new String(arr);
                poss.add(after1Swap); // 1-swap ka result add karo

                // ➤ Ab second swap bhi try karte hain 1-swap ke baad (to generate 2-swap forms)
                char[] arr2 = after1Swap.toCharArray();
                for (int i1 = 0; i1 < digits; i1++) {
                    for (int j1 = i1 + 1; j1 < digits; j1++) {
                        if (arr2[i1] != arr2[j1]) { // same index ka swap avoid karo
                            swap(arr2, i1, j1);
                            poss.add(new String(arr2)); // 2-swap ka result add karo
                            swap(arr2, i1, j1); // undo second swap
                        }
                    }
                }

                swap(arr, i, j); // undo first swap
            }
        }

        return poss;
    }

    // ➤ Simple utility to swap two characters in an array
    private void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // ➤ Main function: Count how many pairs (i < j) are "almost equal" (after at most 2 swaps)
    public int countPairs(int[] nums) {
        int n = nums.length;

        // ➤ Step 1: Sabse bada number find karo to decide kitne digits hone chahiye sabhi numbers me
        int digits = 0;
        for (int num : nums) {
            digits = Math.max(digits, String.valueOf(num).length());
        }

        // ➤ Map to store all previously seen fixed-digit strings and their counts
        Map<String, Integer> mp = new HashMap<>();
        int ans = 0; // final answer

        // ➤ Step 2: Har number ke liye:
        for (int i = 0; i < n; i++) {
            // ➤ Har number se uske 0,1,2 swap ke sabhi unique versions nikaalo
            Set<String> forms = makeSwapChanges(nums[i], digits);

            // ➤ Check karo ki in forms me se koi pehle aaya hua form map me already exist karta hai kya
            for (String s : forms) {
                ans += mp.getOrDefault(s, 0);
            }

            // ➤ Ab current number ko bhi map me add kar do future matching ke liye
            String base = makeDigit(nums[i], digits);
            mp.put(base, mp.getOrDefault(base, 0) + 1);
        }

        return ans;
    }
}
