import java.util.*;

public class Main {
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static List<String> grid;
    static int[][] visited;
    static int count, rowMin, rowMax, colMin, colMax;

    static void dfs(int r, int c) {
        if (visited[r][c] == 1 || grid.get(r).charAt(c) == '.') return;

        visited[r][c] = 1;
        count++;

        rowMin = Math.min(rowMin, r);
        rowMax = Math.max(rowMax, r);
        colMin = Math.min(colMin, c);
        colMax = Math.max(colMax, c);

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < grid.size() && nc >= 0 && nc < grid.get(0).length()) {
                dfs(nr, nc);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt();
            sc.nextLine();

            grid = new ArrayList<>();
            visited = new int[n][m];
            int[] emptyRow = new int[n];
            int[] emptyCol = new int[m];

            for (int i = 0; i < n; i++) {
                String row = sc.nextLine();
                grid.add(row);
                for (int j = 0; j < m; j++) {
                    if (row.charAt(j) == '.') {
                        emptyRow[i]++;
                        emptyCol[j]++;
                    }
                }
            }

            int[] prefixRow = new int[n + 1];
            int[] prefixCol = new int[m + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] == 1 || grid.get(i).charAt(j) == '.') continue;

                    count = 0;
                    rowMin = rowMax = i;
                    colMin = colMax = j;
                    dfs(i, j);

                    rowMin = Math.max(0, rowMin - 1);
                    rowMax = Math.min(n - 1, rowMax + 1);
                    colMin = Math.max(0, colMin - 1);
                    colMax = Math.min(m - 1, colMax + 1);

                    prefixRow[rowMin] += count;
                    prefixRow[rowMax + 1] -= count;
                    prefixCol[colMin] += count;
                    prefixCol[colMax + 1] -= count;
                }
            }

            int maxCells = 0;
            for (int i = 0; i < n; i++) {
                if (i > 0) prefixRow[i] += prefixRow[i - 1];
                maxCells = Math.max(maxCells, prefixRow[i] + emptyRow[i]);
            }
            for (int i = 0; i < m; i++) {
                if (i > 0) prefixCol[i] += prefixCol[i - 1];
                maxCells = Math.max(maxCells, prefixCol[i] + emptyCol[i]);
            }

            System.out.println(maxCells);
        }

        sc.close();
    }
}
