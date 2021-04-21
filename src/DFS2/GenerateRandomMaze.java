package DFS2;
/**
 * Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. For each pair of cells on the corridor, there must exist one and only one path between them. (Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.). The wall is denoted by 1 in the matrix and corridor is denoted by 0.
 *
 * Assumptions
 *
 * N = 2K + 1 and K >= 0
 * the top left corner must be corridor
 * there should be as many corridor cells as possible
 * for each pair of cells on the corridor, there must exist one and only one path between them
 * Examples
 *
 * N = 5, one possible maze generated is
 *
 *         0  0  0  1  0
 *
 *         1  1  0  1  0
 *
 *         0  1  0  0  0
 *
 *         0  1  1  1  0
 *
 *         0  0  0  0  0
 *
 *
 *  TC = O(m*n)
 * 题意要求生成的maze，由0组成连线的通路必须要是单线状的，这样任意两个0之间才能有唯一一种到达的关系，比如：
 *
 * 0 0 0
 *
 * 1 1 0
 *
 * 0 0 0
 *
 * 但如果某个部分生成了一片矩阵状的0，那么任意两个0之间的到达关系会不至于一种，就违法题意了，比如：
 *
 * 0 0 1
 *
 * 0 0 1
 *
 * 1 1 1
 *
 * 所以这个题的关键就是，每次必须往外走两步(advance by two steps), 以保证0组成一个线状的通路，而不是每次往外走一步which会导致0形成矩阵从而变成连成一大片的通路。
 *
 *
 * 初始化
 *
 * 0 1 1
 *
 * 1 1 1
 *
 * 1 1 1
 *
 * 除了左上起点都是1(1既表示wall，也表示not-visited), 最后无路可走(四个方向两步之外的点都不是1)即表示dfs搜索结束了
 */

public class GenerateRandomMaze {
    public int[][] maze(int n) {
        // Write your solution here.
        int[][] maze = new int[n][n];
        // initialize the matrix as only (0,0) is corridor,
        // other cells are all walls at the beginning.
        // later we are trying to break the walls to form corridors.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    maze[i][j] = 0;
                } else {
                    maze[i][j] = 1;
                }
            }
        }
        generate(maze, 0, 0);
        return maze;
    }

    private void generate(int[][] maze, int x, int y) {
        // get a random shuffle of all the possible directions,
        // and follow the shuffled order to do DFS & backtrack.
        Dir[] dirs = Dir.values();
        shuffle(dirs);
        for (Dir dir : dirs) {
            // advance by two steps.
            int nextX = dir.moveX(x, 2);
            int nextY = dir.moveY(y, 2);
            if (isValidWall(maze, nextX, nextY)) {
                // only if the cell is a wall (meaning we have not visited before),
                // we break the walls through to make it corridors.
                maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0;
                maze[nextX][nextY] = 0;
                generate(maze, nextX, nextY);
            }
        }
    }

    // Get a random order of the directions.
    private void shuffle(Dir[] dirs) {
        for (int i = 0; i < dirs.length; i++) {
            int index = (int) (Math.random() * (dirs.length - i));
            Dir tmp = dirs[i];
            dirs[i] = dirs[i + index];
            dirs[i + index] = tmp;
        }
    }

    // check if the position (x,y) is within the maze and it is a wall.
    private boolean isValidWall(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
    }

    // use enum :
    // enum is good and recommended way for representing a set of predefiend constants.
    enum Dir {
        NORTH(-1, 0), SOUTH(1, 0), WEST(0, -1), EAST(0, 1);

        int deltaX;
        int deltaY;

        Dir(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        // move certain times of deltax.
        public int moveX(int x, int times) {
            return x + times * deltaX;
        }

        // move certain times of deltay.
        public int moveY(int y, int times) {
            return y + times * deltaY;
        }

    }

    public static void main(String[] args) {
        GenerateRandomMaze g = new GenerateRandomMaze();
        int n = 5;
        int[][] maze = g.maze(n);
    }
}
