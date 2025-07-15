import java.util.*;

public class Monsters {
    // Movement ke liye directions: U, D, L, R
    static int[] dx = {-1, 1, 0, 0}; // row movement
    static int[] dy = {0, 0, -1, 1}; // column movement
    static char[] dir = {'U', 'D', 'L', 'R'}; // direction characters

    // Cell class ek point (x, y) ko represent karta hai
    static class Cell {
        int x, y;
        Cell(int x, int y) {
            this.x = x; this.y = y;
        }

        public boolean equals(Object obj) {
            Cell c = (Cell) obj;
            return this.x == c.x && this.y == c.y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Grid ka size input lo
        int n = sc.nextInt(), m = sc.nextInt();
        sc.nextLine();

        // Grid aur supporting arrays
        char[][] grid = new char[n][m];
        int[][] monsterTime = new int[n][m]; // har cell pe monster kab pahuchta hai
        int[][] playerTime = new int[n][m];  // player ka time
        Cell[][] parent = new Cell[n][m];    // path trace karne ke liye
        char[][] move = new char[n][m];      // kis direction se aaye ho

        Queue<Cell> monsters = new LinkedList<>(); // sab monsters ko queue me daalenge
        Cell start = null; // player ki starting position

        // Grid input lena aur monsters/starting point identify karna
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
                monsterTime[i][j] = Integer.MAX_VALUE; // shuru me monster kahin nahi
                if (grid[i][j] == 'M') {
                    monsters.add(new Cell(i, j));
                    monsterTime[i][j] = 0; // monster yahan pe shuru se hai
                } else if (grid[i][j] == 'A') {
                    start = new Cell(i, j); // yeh player ki starting position hai
                }
            }
        }

        // Step 1: Sab monsters se ek saath BFS chalao
        Queue<Cell> q = new LinkedList<>(monsters);
        while (!q.isEmpty()) {
            Cell curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                // Valid aur floor cell ho, aur pehle monster yahan nahi gaya
                if (nx >= 0 && ny >= 0 && nx < n && ny < m &&
                    grid[nx][ny] == '.' && monsterTime[nx][ny] == Integer.MAX_VALUE) {
                    monsterTime[nx][ny] = monsterTime[curr.x][curr.y] + 1; // time badhao
                    q.add(new Cell(nx, ny)); // naye cell ko queue me daalo
                }
            }
        }

        // Step 2: Player se BFS chalao
        boolean[][] visited = new boolean[n][m];
        q.clear();
        q.add(start);
        visited[start.x][start.y] = true;
        playerTime[start.x][start.y] = 0;

        while (!q.isEmpty()) {
            Cell curr = q.poll();

            // Agar boundary pe pahuch gaye ho, to jeet gaye!
            if (curr.x == 0 || curr.y == 0 || curr.x == n - 1 || curr.y == m - 1) {
                // Step 3: Ab path wapas trace karo
                StringBuilder path = new StringBuilder();
                Cell p = curr;
                while (!p.equals(start)) {
                    char d = move[p.x][p.y]; // is cell pe kaise aaye the
                    path.append(d); // direction add karo
                    p = parent[p.x][p.y]; // parent cell pe jao
                }

                // Final output
                System.out.println("YES");
                System.out.println(path.length());
                System.out.println(path.reverse().toString());
                return;
            }

            // 4 direction me try karo
            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m &&
                    !visited[nx][ny] && grid[nx][ny] == '.') {

                    int nextTime = playerTime[curr.x][curr.y] + 1;

                    // Agar player monster se pehle pahuchta hai
                    if (nextTime < monsterTime[nx][ny]) {
                        visited[nx][ny] = true;
                        playerTime[nx][ny] = nextTime;
                        parent[nx][ny] = curr;   // parent set karo
                        move[nx][ny] = dir[d];   // direction save karo
                        q.add(new Cell(nx, ny)); // naye cell ko queue me daalo
                    }
                }
            }
        }

        // Agar kabhi boundary nahi pahuch paye to
        System.out.println("NO");
    }
}
