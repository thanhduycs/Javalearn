package ai.maze;

import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author SONY VAIO
 */
public class MazeGUI extends JFrame {
	private static final long serialVersionUID = 1898989L;
	
	Maze maze;
    int visited[][];
    int solution[][];
    int step = -1;
    // GUI
    JPanel panel;

    public MazeGUI() {
        maze = new Maze(8, 8);
        visited = new int[maze.height * maze.width + 3][2];
        Int();
    }

    public void Int() {
        panel = new JPanel();
        panel.setSize(29 * maze.width + 50 - 10, 29 * maze.height + 50 - 10);
        panel.setBackground(Color.white);
        panel.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        panel.setDoubleBuffered(false);
        panel.setRequestFocusEnabled(false);

        this.setTitle("Search");
        this.setDefaultCloseOperation(3);
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.add(panel);

        this.setSize(29 * maze.width + 30, 29 * maze.height + 50);
        this.setVisible(true);
    }

    // Begin Depthsearch
    public boolean DepthSearch() {
        step = -1;
        DepthSearch(0, 0, 0);
        return step != -1 ? true : false;
    }

    public void DepthSearch(int x, int y, int depth) {
        if (depth > this.step && this.step != -1) {
            return;
        }

        if (x < 0 || y < 0 || x >= maze.width || y >= maze.height || isVisitedPosition(x, y, depth) || maze.get(y, x) == Maze.WALL) {
            return;
        }

        visited[depth][0] = x;
        visited[depth][1] = y;
        depth++;

        Graphics g2 = panel.getGraphics();
        g2.setColor(Color.CYAN);
        g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
        g2.setColor(Color.black);
        g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);

        if (maze.get(y, x) != Maze.START && maze.get(y, x) != Maze.GOAL) {
            g2.drawString("" + (depth - 1), 16 + x * 29, 19 + y * 29);
        }

        sleep();

        if (maze.get(y, x) == Maze.GOAL) {
            if (step == -1 || depth < this.step) {
                solution = new int[depth][2];
                for (int i = 0; i < depth; i++) {
                    solution[i][0] = visited[i][0];
                    solution[i][1] = visited[i][1];
                }
                this.step = depth;
            }
            g2.drawString("" + step, 16 + x * 29, 19 + y * 29);
            return;
        }

        DepthSearch(x + 1, y, depth);
        DepthSearch(x, y + 1, depth);
        DepthSearch(x - 1, y, depth);
        DepthSearch(x, y - 1, depth);

        g2.setColor(Color.WHITE);
        g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
        g2.setColor(Color.black);
        g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
    }

    public boolean isVisitedPosition(int x, int y, int depth) {
        for (int i = 0; i < depth; i++) {
            if (visited[i][0] == x && visited[i][1] == y) {
                return true;
            }
        }
        return false;
    }
    //End depthsearch

    //Bread first search
    public boolean BreadSearch() {
        step = -1;
        BreadSearch(0, 0);
        return step != -1 ? true : false;
    }

    public void BreadSearch(int sx, int sy) {
        Graphics g2 = panel.getGraphics();
        Queue<Node> queue = new ArrayDeque<Node>();
        Stack<Node> stack = null;

        boolean visited[][] = new boolean[maze.height][maze.width];
        for (int i = 0; i < maze.height; i++) {
            for (int j = 0; j < maze.width; j++) {
                visited[i][j] = false;
            }
        }

        queue.add(new Node(null, sx, sy));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int depth = node.depth;

            if (x < 0 || y < 0 || x >= maze.width || y >= maze.height || visited[y][x] || maze.get(y, x) == Maze.WALL) {
                continue;
            }

            if (maze.get(y, x) == Maze.GOAL) {
                sleep(1000);
                stack = new Stack<Node>();
                while (node != null) {
                    int tx = node.x;
                    int ty = node.y;
                    if (maze.get(ty, tx) != Maze.START && maze.get(ty, tx) != Maze.GOAL) {
                        g2.setColor(Color.red);
                        g2.fillRect(6 + tx * 29, 3 + ty * 29, 29, 29);
                        g2.setColor(Color.black);
                        g2.drawRect(6 + tx * 29, 3 + ty * 29, 29, 29);

                        sleep();
                    }
                    stack.add(node);
                    node = node.parent;
                }
                break;
            }

            visited[y][x] = true;
            g2.setColor(Color.CYAN);
            g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
            g2.setColor(Color.black);
            g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
            if (maze.get(y, x) != Maze.START && maze.get(y, x) != Maze.GOAL) {
                g2.drawString("" + depth, 16 + x * 29, 19 + y * 29);
            }
            sleep();

            queue.add(new Node(node, x + 1, y));
            queue.add(new Node(node, x, y + 1));
            queue.add(new Node(node, x - 1, y));
            queue.add(new Node(node, x, y - 1));
        }

        if (stack != null) {
            solution = new int[stack.size()][2];
            for (step = 0; !stack.isEmpty(); step++) {
                Node node = stack.pop();
                solution[step][0] = node.x;
                solution[step][1] = node.y;
            }
        }
    }

    public void printSolution() {
        printSolution(false);
    }

    public void printSolution(boolean keepvalue) {
        int temp[][] = new int[maze.height][maze.width];
        for (int i = 0; i < maze.height; i++) {
            for (int j = 0; j < maze.width; j++) {
                temp[i][j] = maze.maze[i][j];
            }
        }

        for (int i = 0; i < step; i++) {
            int x = solution[i][0];
            int y = solution[i][1];
            temp[y][x] = 7;
        }

        for (int i = 0; i < maze.height; i++) {
            for (int j = 0; j < maze.width; j++) {
                if (keepvalue) {
                    System.out.printf("%d  ", temp[i][j]);
                } else {
                    switch (temp[i][j]) {
                        case Maze.WALL:
                            System.out.printf("|  ");
                            break;
                        case 7:
                            System.out.printf("=  ");
                            break;
                        default:
                            System.out.printf("%d  ", temp[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }

    public void paint(Graphics g) {
        Graphics g2 = panel.getGraphics();

        for (int x = 0; x < maze.width; x++) {
            for (int y = 0; y < maze.height; y++) {
                g2.setColor(Color.WHITE);
                g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                if (maze.get(y, x) == Maze.START) {
                    g2.setColor(Color.RED);
                    g2.drawString("S", 16 + x * 29, 19 + y * 29);
                } else if (maze.get(y, x) == Maze.WALL) {
                    g2.setColor(Color.lightGray);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (maze.get(y, x) == Maze.GOAL) {
                    g2.setColor(Color.RED);
                    g2.drawString("G", 16 + x * 29, 19 + y * 29);
                } else {
                    g2.setColor(Color.WHITE);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
                g2.setColor(Color.black);
                g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
            }
        }

        g2.setColor(Color.red);
        for (int i = 1; i < step - 1; i++) {
            int x = solution[i][0];
            int y = solution[i][1];
            g2.drawString("" + i, 16 + x * 29, 19 + y * 29);
        }
    }

    public void sleep() {
        sleep(50);
    }

    public void sleep(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException iEx) {
        }
    }

    public static void main(String args[]) {
        MazeGUI app = new MazeGUI();
        System.out.println("Maze:");
        app.printSolution(true);
        System.out.println("\n");
        //app.BreadSearch();
        app.DepthSearch();
        app.printSolution();
        app.repaint();
    }
}

class Node {

    Node parent;
    int x;
    int y;
    int depth = 0;

    public Node(Node parent, int x, int y) {
        this.parent = parent;
        if (parent != null) {
            depth = parent.depth + 1;
        }
        this.x = x;
        this.y = y;
    }
}
