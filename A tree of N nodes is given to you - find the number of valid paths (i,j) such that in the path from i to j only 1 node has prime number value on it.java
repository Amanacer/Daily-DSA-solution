import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 100005;
    static int[] color = new int[MAX];

    public static boolean[] sieve(int my) {
        boolean[] isPrime = new boolean[my + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= my; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= my; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public static void main(String[] args) throws IOException {
        // For faster I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        boolean[] isPrime = sieve(1000001);

        int n = Integer.parseInt(br.readLine());
        int[] vl = new int[n + 1];
        String[] parts = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            vl[i] = Integer.parseInt(parts[i - 1]);
            color[i] = isPrime[vl[i]] ? 1 : 0;
            out.print(color[i] + " ");
        }

        out.println();

        ArrayList<ArrayList<Integer>> G = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            G.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            String[] edge = br.readLine().split(" ");
            int x = Integer.parseInt(edge[0]);
            int y = Integer.parseInt(edge[1]);
            G.get(x).add(y);
            G.get(y).add(x);
        }

        long answer = 0;

        for (int i = 1; i <= n; i++) {
            if (color[i] == 1) {
                ArrayList<Long> d = new ArrayList<>();
                for (int u : G.get(i)) {
                    if (color[u] == 0) {
                        long c = 0;
                        Queue<Integer> q = new LinkedList<>();
                        q.add(u);
                        boolean[] used = new boolean[n + 5];
                        used[u] = true;
                        while (!q.isEmpty()) {
                            int v = q.poll();
                            c++;
                            for (int p : G.get(v)) {
                                if (!used[p] && color[p] == 0) {
                                    q.add(p);
                                    used[p] = true;
                                }
                            }
                        }
                        d.add(c);
                    }
                }

                long sum = 0;
                for (long val : d) {
                    sum += val;
                }

                long d1 = 0, w = 0;
                for (long val : d) {
                    w += val;
                    d1 += val * (sum - w);
                }

                d1 += sum;
                answer += d1;
            }
        }

        out.println(answer);
        out.flush();
    }
}
