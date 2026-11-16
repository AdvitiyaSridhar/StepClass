package Week11;


import java.util.*;

public class W11P5 {

    static int[][] exitDistance(char[][] plan) {
        int rows = plan.length;
        int cols = plan[0].length;

        int[][] dist = new int[rows][cols];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (plan[i][j] == '#') {
                    dist[i][j] = -1;
                } else {
                    dist[i][j] = -1;

                    if (plan[i][j] == 'E') {
                        dist[i][j] = 0;
                        q.add(new int[]{i, j});
                    }
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < rows &&
                    nc >= 0 && nc < cols &&
                    plan[nr][nc] != '#' &&
                    dist[nr][nc] == -1) {

                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int[] row : result) {
            for (int d : row) {
                System.out.print(d + " ");
            }
            System.out.println();
        }
    }
}

