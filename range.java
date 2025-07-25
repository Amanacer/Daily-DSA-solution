import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int n = sc.nextInt();
        int[][] ranges = new int[n][2];
 
        for (int i = 0; i < n; i++) {
            ranges[i][0] = sc.nextInt(); // l
            ranges[i][1] = sc.nextInt(); // r
        }
 
        int maxGood = 0;
 
        for (int i = 0; i < n; i++) {
            int count = 1; // include range i itself
            int l1 = ranges[i][0], r1 = ranges[i][1];
 
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int l2 = ranges[j][0], r2 = ranges[j][1];
                if (Math.max(l1, l2) <= Math.min(r1, r2)) {
                    count++;
                }
            }
 
            maxGood = Math.max(maxGood, count);
        }
 
        int answer = n - maxGood;
        System.out.println(answer);
    }
}
 