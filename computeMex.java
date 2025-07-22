import java.util.*;

public class Main {

    // Function to compute MEX of a full array
    public static int computeMex(List<Integer> arr) {
        Set<Integer> st = new HashSet<>(arr);
        for (int i = 0; i <= arr.size(); i++) {
            if (!st.contains(i)) return i;
        }
        return arr.size();
    }

    public static List<Integer> solve(List<Integer> a) {
        List<Integer> result = new ArrayList<>();

        while (!a.isEmpty()) {
            int totalMex = computeMex(a);

            Set<Integer> seen = new HashSet<>();
            int prefixMex = 0;

            for (int i = 0; i < a.size(); i++) {
                seen.add(a.get(i));
                
                while (seen.contains(prefixMex)) {
                    prefixMex++;
                }

                if (prefixMex == totalMex) {
                    // Store the current MEX
                    result.add(totalMex);

                    // Remaining part of array after i
                    List<Integer> remaining = new ArrayList<>();
                    for (int j = i + 1; j < a.size(); j++) {
                        remaining.add(a.get(j));
                    }

                    a = remaining;
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        List<Integer> res = solve(a);
        for (int x : res) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
