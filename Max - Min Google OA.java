import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> v = Arrays.asList(3, 4, 1, 9, 56, 7, 9, 12, 13);
        int p = 5;

        v.sort(null);
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= v.size() - p; i++) {
            ans = Math.min(ans, v.get(i + p - 1) - v.get(i));
        }

        System.out.println(ans);
    }
}
