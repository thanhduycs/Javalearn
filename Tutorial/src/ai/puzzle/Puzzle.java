package ai.puzzle;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author student
 */
public class Puzzle
{
    public final int GOALA[][] = { {1,2,3}, {8,0,4}, {7,6,5} };
    public final int GOALB[][] = { {1,2,3}, {4,5,6}, {7,8,0} };
    
    public final int KEEP_STATE = 0;
    public final int MOVE_LEFT = 1;
    public final int MOVE_RIGHT = 2;
    public final int MOVE_UP = 3;
    public final int MOVE_DOWN = 4;
    
    private int puzzle[][];
    private int goal[][];
    private int size = 3;

    public Queue<Integer> queue = null; //new LinkedList<Integer>();
   
    public Puzzle()
    {
        puzzle = new int[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                puzzle[i][j] = i*size + j;

        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
            {
                int fx = (int)(Math.random()*1000) % size;
                int fy = (int)(Math.random()*1000) % size;
                int sx = (int)(Math.random()*1000) % size;
                int sy = (int)(Math.random()*1000) % size;

                int temp = puzzle[fx][fy];
                puzzle[fx][fy] = puzzle[sx][sy];
                puzzle[sx][sy] = temp;
            }
        print();
    }

    public Puzzle(int puzzle[][])
    {
        this.puzzle = copy(puzzle);
    }

    private int [][] DetermineGoalState()
    {
        int sum = 0;
        for(int i=0; i<size; i++)
        for(int j=0; j<size; j++)
            for(int m=i; m<size; m++)
            for(int n=(m==i)?j+1:0; n<size; n++)
                if(puzzle[m][n] < puzzle[i][j] && puzzle[m][n] != 0)
                    sum++;
        
        if(sum % 2 != 0)
            return GOALA;
        else
            return GOALB;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  IterativeDeepingSearch
    ///////////////////////////////////////////////////////////////////////////
    public void IterativeDeepingSearch()
    {
        goal = DetermineGoalState();
        int maxdepth = 1;
        Stack<Node> stack = new Stack<Node>();
        Node root = new Node(null, copy(puzzle), KEEP_STATE);
        while(true)
        {
            if(stack.isEmpty())
            {
                stack.add(root);
                maxdepth++;
                continue;
            }

            Node node = stack.pop();
            if(Heuritic(node.puzzle) == 0)
            {
                Stack<Node> rstack = new Stack<Node>();
                while(node.parent != null)
                {
                    rstack.add(node);
                    node = node.parent;
                }
                queue = new LinkedList<Integer>();
                while(!rstack.isEmpty())
                    queue.add(rstack.pop().choice);
                break;
            }

            if(node.G > maxdepth)
                continue;

            Location loc = getPosition(0, node.puzzle);
            // 0 move left (<-)
            if(loc.x > 0 && node.choice != MOVE_RIGHT)
            {
                int a_left[][] = copy(node.puzzle);
                a_left[loc.y][loc.x] = a_left[loc.y][loc.x-1];
                a_left[loc.y][loc.x-1] = 0;
                stack.add(new Node(node, a_left, MOVE_LEFT));
            }
            // 0 move rigth (->)
            if(loc.x < size-1 && node.choice != MOVE_LEFT)
            {
                int a_right[][] = copy(node.puzzle);
                a_right[loc.y][loc.x] = a_right[loc.y][loc.x+1];
                a_right[loc.y][loc.x+1] = 0;
                stack.add(new Node(node, a_right, MOVE_RIGHT));
            }
            // 0 move up (^)
            if(loc.y > 0 && node.choice != MOVE_DOWN)
            {
                int a_up[][] = copy(node.puzzle);
                a_up[loc.y][loc.x] = a_up[loc.y-1][loc.x];
                a_up[loc.y-1][loc.x] = 0;
                stack.add(new Node(node, a_up, MOVE_UP));
            }
            // 0 move down (v)
            if(loc.y < size-1 && node.choice != MOVE_UP)
            {
                int a_down[][] = copy(node.puzzle);
                a_down[loc.y][loc.x] = a_down[loc.y+1][loc.x];
                a_down[loc.y+1][loc.x] = 0;
                stack.add(new Node(node, a_down, MOVE_DOWN));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    //  A* Search
    ///////////////////////////////////////////////////////////////////////////
    public void ASearch()
    {
        goal = DetermineGoalState();
        List<Node> list = new ArrayList<Node>();
        List<Node> olist = new ArrayList<Node>();
        Node root = new Node(null, copy(puzzle), KEEP_STATE);
        list.add(root);
        while(!list.isEmpty())
        {
            int F = -1, H = -1, index = 0;
            for(int i=0; i<list.size(); i++)
            {
                Node node = list.get(i);
                int h = Heuritic(node.puzzle);
                int f = node.G + h;
                //int f = h;
                if(F == -1 || f < F || h == 0)
                {
                    F = f;
                    H = h;
                    index = i;
                    if (h == 0) break;
                }
            }
            
            Node parent = list.get(index);
            list.remove(index);
            olist.add(parent);

            if(H == 0)
            {
                Node node = parent; // GOAL
                Stack<Node> stack = new Stack<Node>();
                while(node.parent != null)
                {
                    stack.add(node);
                    node = node.parent;
                }
                queue = new LinkedList<Integer>();
                while(!stack.isEmpty())
                    queue.add(stack.pop().choice);
                break;
            }


            Location loc = getPosition(0, parent.puzzle);
            // 0 move left (<-)
            if(loc.x > 0 && parent.choice != MOVE_RIGHT)
            {
                int a_left[][] = copy(parent.puzzle);
                a_left[loc.y][loc.x] = a_left[loc.y][loc.x-1];
                a_left[loc.y][loc.x-1] = 0;
                AddToList(a_left, parent, list, olist, MOVE_LEFT);
            }
            // 0 move rigth (->)
            if(loc.x < size-1 && parent.choice != MOVE_LEFT)
            {
                int a_right[][] = copy(parent.puzzle);
                a_right[loc.y][loc.x] = a_right[loc.y][loc.x+1];
                a_right[loc.y][loc.x+1] = 0;
                AddToList(a_right, parent, list, olist, MOVE_RIGHT);
            }
            // 0 move up (^)
            if(loc.y > 0 && parent.choice != MOVE_DOWN)
            {
                int a_up[][] = copy(parent.puzzle);
                a_up[loc.y][loc.x] = a_up[loc.y-1][loc.x];
                a_up[loc.y-1][loc.x] = 0;
                AddToList(a_up, parent, list, olist, MOVE_UP);
            }
            // 0 move down (v)
            if(loc.y < size-1 && parent.choice != MOVE_UP)
            {
                int a_down[][] = copy(parent.puzzle);
                a_down[loc.y][loc.x] = a_down[loc.y+1][loc.x];
                a_down[loc.y+1][loc.x] = 0;
                AddToList(a_down, parent, list, olist, MOVE_DOWN);
            }
        }
    }

    private void AddToList(int puzzle[][], Node parent, List<Node> list, List<Node> olist, int choice)
    {
        if(FinInList(puzzle, olist) == -1)
        {
            int find = FinInList(puzzle, list);
            if(find == -1)
            {
                list.add(new Node(parent, puzzle, choice));
            }
            else
            {
                Node onode = list.get(find);
                Node node = new Node(parent, puzzle, choice);
                int oh = onode.G + Heuritic(onode.puzzle);
                int h = node.G + Heuritic(node.puzzle);
                if(h < oh)
                {
                    list.remove(find);
                    list.add(node);
                }
            }
        }
    }

    public int FinInList(int arr[][], List<Node> list)
    {
        for(int i=0 ; i<list.size(); i++)
            if(isEqual(list.get(i).puzzle, arr))
                return i;
        return -1;
    }

    public boolean isEqual(int a[][], int b[][])
    {
        if(a.length != b.length) return false;
        for(int i=0; i<a.length; i++)
        {
            if(a[i].length != b[i].length) return false;
            for(int j=0; j<a[i].length; j++)
                if(a[i][j] != b[i][j])
                    return false;
        }
        return true;
    }

    public int Heuritic(int puzzle[][])
    {
        int H = 0;
        for(int i=1; i<size*size; i++)
        {
            Location Cloc = getPosition(i, puzzle);
            Location Gloc = getPosition(i, goal);
            H += getDistance(Cloc, Gloc);
        }
        return H;
    }
    
    public int getDistance(Location a, Location b)
    {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public Location getPosition(int value)
    {
        return getPosition(value, puzzle);
    }
    
    public Location getPosition(int value, int puzzle[][])
    {
        for(int i=0; i<puzzle.length; i++)
            for(int j=0; j<puzzle[i].length; j++)
                if(puzzle[i][j] == value)
                    return new Location(j, i);
        return null;
    }

    public int[][] copy(int arr[][])
    {
        int copy[][] = new int[arr.length][];
        for(int i=0; i<arr.length; i++)
        {
            copy[i] = new int [arr[i].length];
            for(int j=0; j<arr[i].length; j++)
                copy[i][j] = arr[i][j];
        }
        return copy;
    }

    public void showSolution()
    {
        print();
        while(!queue.isEmpty())
        {
            moveBySolution();
            print();
        }
    }

    public void moveBySolution()
    {
        if(!queue.isEmpty())
        {
            Location loc = getPosition(0, puzzle);
            int choice = queue.poll();
            if(choice == MOVE_LEFT)
            {
                puzzle[loc.y][loc.x] = puzzle[loc.y][loc.x-1];
                puzzle[loc.y][loc.x-1] = 0;
            }
            else if(choice == MOVE_RIGHT)
            {
                puzzle[loc.y][loc.x] = puzzle[loc.y][loc.x+1];
                puzzle[loc.y][loc.x+1] = 0;
            }
            else if(choice == MOVE_UP)
            {
                puzzle[loc.y][loc.x] = puzzle[loc.y-1][loc.x];
                puzzle[loc.y-1][loc.x] = 0;
            }
            else if(choice == MOVE_DOWN)
            {
                puzzle[loc.y][loc.x] = puzzle[loc.y+1][loc.x];
                puzzle[loc.y+1][loc.x] = 0;
            }
            else
            {
                System.out.println(choice + " :: Where i will go ?");
            }
        }
    }
    
    public void print()
    {
        System.out.println("");
        print(puzzle);
    }

    public void print(int arr[][])
    {
        System.out.println(arrToString(arr));
    }

    public String arrToString()
    {
        return arrToString(puzzle);
    }

    public String arrToString(int arr[][])
    {
        String string = "";
        for(int i=0; i<arr.length; i++)
        {
            for(int j=0; j<arr[i].length; j++)
                string += String.format("%3d", arr[i][j]);
            string += "\n";
        }
        return string;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  Accessor
    ///////////////////////////////////////////////////////////////////////////
    public int [][] getPuzzle()
    {
        return puzzle;
    }
    
    public void setValue(int x, int y, int value)
    {
        puzzle[y][x] = value;
    }

    public int getValue(int x, int y)
    {
        return puzzle[y][x];
    }

    public int getSize()
    {
        return size;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  Main
    ///////////////////////////////////////////////////////////////////////////

    public static void main(String args[])
    {
        Puzzle puzzle = new Puzzle();
        puzzle.ASearch();
        puzzle.showSolution();
    }
}

class Location
{
    int x;
    int y;
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

class Node
{
    Node parent = null;
    int puzzle[][];
    int choice = 0;
    int G = 0;
    public Node(Node parent, int [][] data, int choice)
    {
        this.parent = parent;
        this.puzzle = data;
        this.choice = choice;
        if(this.parent != null)
            G = parent.G + 1;
    }
}
