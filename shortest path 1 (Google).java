import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        int[] b = new int[n + 1];
        
        List<Integer>[] G = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            G[i] = new ArrayList<>();
        }
        
        int i = 1;
        while (i <= n - 1) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            G[x].add(y);
            G[y].add(x);
            i++;
        }
        
        i = 1;
        while (i <= n) {
            b[i] = scanner.nextInt();
            i++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        int[] used = new int[n + 1];
        used[1] = 1;
        q.add(1);
        int[] answer = new int[n + 1];
        answer[1] = b[1];
        
        while (!q.isEmpty()) {
            int top = q.poll();
            int c = 0;
            
            for (int u : G[top]) {
                if (used[u] == 0) {
                    c++;
                    used[u] = 1;
                    q.add(u);
                    
                    if (b[u] == 1) {
                        answer[u] = answer[top] + 1;
                    } else {
                        answer[u] = answer[top];
                    }
                }
            }
        }
        
        i = 1;
        while (i <= n) {
            System.out.println(answer[i]);
            i++;
        }
    }
}
