import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int n = classroom.length;
        int m = classroom[0].length();

        int sr = 0, sc = 0, k = 0;

        int[][] id = new int[n][m];
        for (int[] row : id) Arrays.fill(row, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    id[i][j] = k++;
                }
            }
        }

        int states = 1 << k;
        int target = states - 1;

        int[][][] best = new int[n][m][states];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                Arrays.fill(best[i][j], -1);

        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{sr, sc, energy, 0, 0});
        best[sr][sc][0] = energy;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int e = cur[2];
            int mask = cur[3];
            int moves = cur[4];

            if (mask == target)
                return moves;

            if (e == 0)
                continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                    continue;

                char cell = classroom[nr].charAt(nc);

                if (cell == 'X')
                    continue;

                int ne = e - 1;
                int nmask = mask;

                if (cell == 'L')
                    nmask |= 1 << id[nr][nc];

                if (cell == 'R')
                    ne = energy;

                if (ne > best[nr][nc][nmask]) {
                    best[nr][nc][nmask] = ne;
                    queue.offer(new int[]{
                        nr, nc, ne, nmask, moves + 1
                    });
                }
            }
        }

        return -1;
    }
}