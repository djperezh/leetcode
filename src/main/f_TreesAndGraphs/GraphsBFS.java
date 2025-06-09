package main.f_TreesAndGraphs;

import java.util.*;

import javax.swing.tree.TreeNode;

public class GraphsBFS {
    /*
     * Example 1
     * 1091. Shortest Path in Binary Matrix (https://leetcode.com/problems/shortest-path-in-binary-matrix/)
     * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
     * A clear path is a path from the top-left cell (0, 0) to the bottom-right cell (n - 1, n - 1) such that all visited cells are 0.
     * You may move 8-directionally (up, down, left, right, or diagonally).
    */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0) return -1;

        int ans = 0;
        // BFS
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        grid[0][0] = 1; // mark as seen

        while (!q.isEmpty()) {
            ans++;
            int count = q.size();
            for (int i = 0; i < count; i++) {
                int[] cellIndexes = q.poll();
                if (cellIndexes[0] == grid.length - 1 && cellIndexes[1] == grid[grid.length - 1].length - 1) return ans;
                addNieghbors(grid, q, cellIndexes);
            }
        }
        return -1;
    }

    private static void addNieghbors(int[][] grid, Queue<int[]> q, int[] curr) {
        int[][] directions = new int[][] { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int len = grid.length;
        for (int[] dirs : directions) {
            int[] newCell = new int[] {curr[0] + dirs[0], curr[1] + dirs[1]};
            // if valid cell then add it to the queue
            if (newCell[0] >= 0 && newCell[0] < len && newCell[1] >= 0 && newCell[1] < len && grid[newCell[0]][newCell[1]] == 0) {
                q.offer(new int[] {newCell[0], newCell[1]});
                grid[newCell[0]][newCell[1]] = 1;
            }
        }
    }


    /*
     * Example 2
     * 863. All Nodes Distance K in Binary Tree (https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)
     * Given the root of a binary tree, a target node target in the tree, and an integer k,
     * return an array of the values of all nodes that have a distance k from the target node.
    */
    public static List<Integer> distanceK(BinaryTreeNode root, BinaryTreeNode target, int k) {
        // Since we will treat the Tree as a graph,
        // then We keep a reference for the Parent Node of each node,
        // so we can start traversing the graph from any Node
        Map<BinaryTreeNode, BinaryTreeNode> parents = new HashMap<>();
        dfsDistanceK(root, null, parents);

        // BFS starting from target
        Set<BinaryTreeNode> seen = new HashSet<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.offer(target);
        seen.add(target);
        int distance = 0;
        while (!q.isEmpty() && distance < k) {
            int count = q.size();
            for (int i = 0; i < count; i++) {
                BinaryTreeNode node = q.poll();
                // Trick to treat tree as Undirected graph:
                // enqueue Nodes 1 Level down (Children: Right & Left) and 1 Level Up (Parent)
                BinaryTreeNode[] nextLevelNodes = new BinaryTreeNode[] {node.left, node.right, parents.get(node)};
                for (BinaryTreeNode n : nextLevelNodes) {
                    if (n != null && !seen.contains(n)) {
                        q.offer(n);
                        seen.add(n);
                    }
                }
            }
            distance++;
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) ans.add(q.poll().val);
        return ans;
    }

    private static void dfsDistanceK(BinaryTreeNode node, BinaryTreeNode parent, Map<BinaryTreeNode, BinaryTreeNode> parents) {
        if (node == null) return;
        parents.put(node, parent);
        dfsDistanceK(node.left, node, parents);
        dfsDistanceK(node.right, node, parents);
    }

    /*
     * Example 3
     * 542. 01 Matrix (https://leetcode.com/problems/01-matrix/)
     * Given an m x n binary (every element is 0 or 1) matrix mat, find the distance of the nearest 0 for each cell.
     * The distance between adjacent cells (horizontally or vertically) is 1.
    */ 
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int distance = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] seen = new boolean[m][n];

        // look for the Zeros and enque them so we can do BFS from each of them as a starting point
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] == 0) {
                    seen[row][col] = true;
                    q.offer(new int[] {row, col});
                }
            }
        }

        // BFS from the Zeros
        while (!q.isEmpty()) {
            int count = q.size();
            distance++;
            for (int i = 0; i < count; i++) addUnseenNieghbors(mat, q, q.poll(), seen, distance);
        }
        return mat;
    }

    private static void addUnseenNieghbors(int[][] grid, Queue<int[]> q, int[] curr, boolean[][] seen, int distance) {
        int[][] directions = new int[][] { {-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        for (int[] dirs : directions) {
            int[] newCell = new int[] {curr[0] + dirs[0], curr[1] + dirs[1]};
            // if valid cell then add it to the queue
            if (newCell[0] >= 0 && newCell[0] < grid.length && newCell[1] >= 0 && newCell[1] < grid[newCell[0]].length && !seen[newCell[0]][newCell[1]]) {
                q.offer(new int[] {newCell[0], newCell[1]});
                seen[newCell[0]][newCell[1]] = true;
                grid[newCell[0]][newCell[1]] = distance;
            }
        }
    }

    /*
     * Example 4
     * 1293. Shortest Path in a Grid with Obstacles Elimination (https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/)
     * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
     * You can move up, down, left, or right from and to an empty cell in one step.
     * Return the minimum number of steps to walk from the upper left corner to the lower right corner given that you can eliminate at most k obstacles.
     * If it is not possible, return -1.
    */
    public static int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // BFS
        Set<String> seen = new HashSet<>();
        Queue<State> q = new LinkedList<>();
        q.offer(new State(new int[] {0, 0}, k, 0));
        seen.add(String.valueOf(0) + "-" + String.valueOf(0) + "-" + String.valueOf(k));

        while (!q.isEmpty()) {
            State s = q.poll();
            int row = s.cellIndexes[0];
            int col = s.cellIndexes[1];
            int steps = s.steps;

            // If we are at the lower right corner then we retunr the number of steps
            if (row == m - 1 && col == n - 1) return steps;
            
            // Enque neighbors eliminating up to K obstacles 
            addPotentialNieghbors(grid, k, s, seen, q); 
        }
        return -1;
    }

    private static void addPotentialNieghbors(int[][] grid, int k, State s, Set<String> seen, Queue<State> q) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = new int[][] {new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1}};

        int row = s.cellIndexes[0];
        int col = s.cellIndexes[1];
        int steps = s.steps;
        int remain = s.remain;

        for (int[] d : directions) {
            int newRow = row + d[0];
            int newCol = col + d[1];
            // new cell is inside the boundaries
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                String seenKey = String.valueOf(newRow) + "-" + String.valueOf(newCol) + "-";

                if (grid[newRow][newCol] == 0 && !seen.contains(seenKey + String.valueOf(remain))) {
                    q.offer(new State(new int[] {newRow, newCol}, remain, steps + 1));
                    seen.add(seenKey + String.valueOf(remain));
                } else if (remain > 0 && !seen.contains(seenKey + String.valueOf(remain - 1))) {
                    // if neighbor is an obstacle then check if we have remains to walk over it
                    q.offer(new State(new int[] {newRow, newCol}, remain - 1, steps + 1));
                    seen.add(seenKey + String.valueOf(remain - 1));
                }
            }
        }
    }

    private static class State {
        int[] cellIndexes;
        int remain;
        int steps;

        State(int[] cellIndexes, int remain, int steps){
            this.cellIndexes = cellIndexes;
            this.remain = remain;
            this.steps = steps;
        }
    }


    /*
     * Example 5
     * 1129. Shortest Path with Alternating Colors (https://leetcode.com/problems/shortest-path-with-alternating-colors/)
     * You are given a directed graph with n nodes labeled from 0 to n - 1.
     * Edges are red or blue in this graph.
     * You are given redEdges and blueEdges, where redEdges[i] and blueEdges[i] both have the format [x, y] indicating an edge from x to y in the respective color.
     * Return an array ans of length n, where answer[i] is the length of the shortest path from 0 to i where edge colors alternate, or -1 if no path exists.
     * 
     * Following Code was taken from: https://leetcode.com/explore/interview/card/leetcodes-interview-crash-course-data-structures-and-algorithms/707/traversals-trees-graphs/4631/
    */ 
    static int RED = 0;
    static int BLUE = 1;
    static Map<Integer, Map<Integer, List<Integer>>> graphByColor = new HashMap<>();
    
    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        graphByColor.put(RED, new HashMap<>());
        graphByColor.put(BLUE, new HashMap<>());
        
        addToGraph(RED, redEdges, n);
        addToGraph(BLUE, blueEdges, n);
        
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        
        Queue<ColorGraphState> queue = new LinkedList<>();
        queue.add(new ColorGraphState(0, RED, 0));
        queue.add(new ColorGraphState(0, BLUE, 0));
        
        boolean[][] seen = new boolean[n][2];
        seen[0][RED] = true;
        seen[0][BLUE] = true;
        
        while (!queue.isEmpty()) {
            ColorGraphState state = queue.remove();
            int node = state.node, color = state.color, steps = state.steps;
            ans[node] = Math.min(ans[node], steps);
            
            for (int neighbor: graphByColor.get(color).get(node)) {
                if (!seen[neighbor][1 - color]) {
                    seen[neighbor][1 - color] = true;
                    queue.add(new ColorGraphState(neighbor, 1 - color, steps + 1));
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (ans[i] == Integer.MAX_VALUE) {
                ans[i] = -1;
            }
        }
        
        return ans;
    }
    
    public static void addToGraph(int color, int[][] edges, int n) {
        for (int i = 0; i < n; i++) {
            graphByColor.get(color).put(i, new ArrayList<>());
        }
        
        for (int[] edge: edges) {
            int x = edge[0], y = edge[1];
            graphByColor.get(color).get(x).add(y);
        }
    }

    private static class ColorGraphState {
        int node;
        int color;
        int steps;
        ColorGraphState(int node, int color, int steps) {
            this.node = node;
            this.color = color;
            this.steps = steps;
        }
    }

    /*
     * 1926. Nearest Exit from Entrance in Maze (https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/description/)
     * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). 
     * You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
     * In one step, you can move one cell up, down, left, or right. 
     * You cannot step into a cell with a wall, and you cannot step outside the maze. 
     * Your goal is to find the nearest exit from the entrance. 
     * An exit is defined as an empty cell that is at the border of the maze. 
     * The entrance does not count as an exit. Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
    */
    int[][] directions;
    static Map<Integer, HashSet<Integer>> seenMap;
    static Queue<int[]> mazeQueue;
    
    public static int nearestExit(char[][] maze, int[] entrance) {
        int result = 0;

        mazeQueue = new LinkedList<>();
        seenMap = new HashMap<>();
        
        updateQueue(entrance);
        
        while (!mazeQueue.isEmpty()) {
            int counter = mazeQueue.size();
            for (int i = 0; i < counter; i++) {
                int[] cell = mazeQueue.remove();
                
                if (isExit(cell, entrance, maze)) return result;
                
                addNeighbors(cell, maze);
            }
            result++; // increment the step counter
        }
        return -1;
    }
    
    private static void addNeighbors(int[] cell, char[][] maze) {
        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction: directions) {
            int nextRow = cell[0] + direction[0];
            int nextCol = cell[1] + direction[1];
            int[] neighbor = new int[] {nextRow, nextCol};
            
            if (isValid(nextRow, nextCol, maze, neighbor)) updateQueue(neighbor);
        }
    }
    
    private static boolean isExit(int[] cell, int[] entrance, char[][] maze) {
        return ((entrance[0] != cell[0] || entrance[1] != cell[1]) &&
                (cell[0] == 0 || cell[0] == maze.length - 1 ||
                cell[1] == 0 || cell[1] == maze[0].length - 1));
    }

    private static boolean isValid(int nextRow, int nextCol, char[][] maze, int[] neighbor) {
        return (!seenMap.containsKey(neighbor[0]) || !seenMap.get(neighbor[0]).contains(neighbor[1])) &&
            0 <= nextRow && nextRow < maze.length &&
            0 <= nextCol && nextCol < maze[0].length &&
            maze[nextRow][nextCol] != '+';
    }
    
    private static void updateQueue(int[] cell) {
        mazeQueue.add(cell);
        if (!seenMap.containsKey(cell[0])) seenMap.put(cell[0], new HashSet<Integer>());
        seenMap.get(cell[0]).add(cell[1]);
    }

    /*
     * 909. Snakes and Ladders (https://leetcode.com/problems/snakes-and-ladders/description/)
     * You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
     * You start on square 1 of the board. In each move, starting from square curr, do the following:
     *     Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
     *         This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
     *     If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
     *     The game ends when you reach the square n2.
     * A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 are not the starting points of any snake or ladder.
     * Note that you only take a snake or ladder at most once per dice roll. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
     * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4. Return the least number of dice rolls required to reach the square n2. If it is not possible to reach the square, return -1.
    */
    public static int snakesAndLadders(int[][] board) {
        // coordinates map (cellNumber->[x,y]) handling the direction of the board (ZigZag starting form the bottom-left)
        Map<Integer, int[]> graphCoord = new HashMap<>();
        int cellNumber = 1;
        int j = 0;
        boolean goingRight = false;
        for (int i = board.length - 1; i >= 0 ; i--) {
            // Change cells' reading direction per row
            goingRight = !goingRight;  
            j = goingRight ? 0 : board.length - 1;
            
            for (int cols = 0; cols < board.length; cols++) {
                graphCoord.put(cellNumber, new int[] {i, j});
                j += goingRight ? 1 : -1;
                cellNumber++;
            }   
        }
        
        // Enqueue the first cell in a Boustrophedon style
        // use the coordenates: [board[borad.length - 1][0]]
        int result = 0; // since "1" is the starting point we don't count it
        
        boolean[] seen = new boolean[(board.length * board.length) + 1];
        Queue<Integer> q = new LinkedList<>();
        // Enque the cell and mark it as visited
        q.add(1);
        seen[1] = true;
        
        // DFS: Use Queue to find the min path
        while (q.size() > 0) {
            int stepItems = q.size();
            result++;
            
            for (int x = 0; x < stepItems; x++) {
                 // dequeue current cell and mark cell as seen
                int curr = q.remove();
                
                // Get Neighbors: get next possible move (use For from i = 1 to 6 to simulate dice)
                for (int i = 1; i <= 6 && curr + i <= seen.length - 1; i++) {
                    // next is already visited
                    if (seen[curr + i]) continue;
                    
                    
                    // use current coordinate and i to get the next cell
                    int[] next = graphCoord.get(curr + i);
                    int nextCellValue = board[next[0]][next[1]];
                    // If Snake/Ladder then we use that number instead (otherwise we use the next cell's number)
                    int n = (nextCellValue != -1) ? nextCellValue : curr + i;
                    
                    // Enque the cell and mark it as visited
                    q.add(n);
                    seen[curr + i] = true;
                    
                    // Check if next cell is the winner cell
                    if (n == seen.length - 1) return result;
                }               
            }
        }
        
        return -1;
    }
}
