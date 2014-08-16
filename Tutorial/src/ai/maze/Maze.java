package ai.maze;


/**
 *
 * @author SONY VAIO
 */
public class  Maze {
    public static final int START = 0;
    public static final int ROAD = 1;
    public static final int WALL = 2;
    public static final int GOAL = 3;

    public int maze[][] = null;
    public int height = 0;
    public int width = 0;

    public Maze(int height, int width)
    {
        this.height = height;
        this.width = width;

        maze = new int[height][width];

        for(int i=0; i<maze.length; i++)
            for(int j=0; j<maze[i].length; j++)
                maze[i][j] = ROAD;      

        maze[0][0] = START;
        maze[this.height-1][this.width-1] = GOAL;

        RandomValue();
    }

    public void RandomValue()
    {
        for(int i=0; i<height*width/3; i++)
        {
            int x = (int) (Math.random()*1000) % this.width;
            int y = (int) (Math.random()*1000) % this.height;
            maze[y][x] = WALL;

            maze[0][0] = START;
            maze[this.height-1][this.width-1] = GOAL;
        }
    }

    public int get(int row, int column)
    {
        return maze[row][column];
    }

    public void set(int row, int column, int value)
    {
        maze[row][column] = value;
    }
}

