package ai.sodoku;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;
/**
 *
 * @author SONY VAIO
 * THANH DUY
 */
public class Sodoku
{
    private int [][] Ksodoku = null;
    private boolean [][][] Domain = null;
    /*  [ Kind_Constraint ] [ index ] [ value ]
     *  [ 0: Row ] [] []
     *  [ 1: Column ] [] []
     *  [ 2: Box ] [] []
     */
    private int size;
    private Random random = new Random();
    
    private long sleep;

    public Sodoku(int size)
    {
        this.Ksodoku = new int[size][size];
        this.size = Ksodoku.length;
    }

    public void clean()
    {
        for(int ix=0; ix<size; ix++)
            for(int iy=0; iy<size; iy++)
                Ksodoku[ix][iy] = 0;
    }

    public void print()
    {
        for(int ix=0; ix<size; ix++)
        {
            for(int iy=0; iy<size; iy++)
                System.out.printf("%4d ", Ksodoku[ix][iy]);
            System.out.println();
        }
        System.out.println();
    }

    public boolean solver()
    {
        return solver(0);
    }

    public boolean solver(long sleep)
    {
        if(sleep >= 0) this.sleep = sleep;
        IntialDomain();
        return constraint();
    }

    private boolean constraint()
    {
        int loc[] = FindMinimumLoc();
        if(loc[0] == -1)
            return true;
        int values [] = new int[size];
        for(int e=0; e<size; e++)
            values[e] = e+1;
        for(int e=0; e<size; e++)
        {
            int index = (random.nextInt()&0xFFFFF) % size;
            int temp = values[e];
            values[e] = values[index];
            values[index] = temp;
        }
        for(int e=0; e<size; e++)
        {
            if( IsValidValue(values[e], loc[0], loc[1]) )
            {
                Ksodoku[ loc[0] ] [ loc[1] ] = values[e];
                try { Thread.sleep(sleep); } catch (InterruptedException IEx) {}
                ApplyConstraint(loc[0], loc[1]);
                if(constraint()) return true;
                RemoveConstraint(loc[0], loc[1]);
            }
        }
        Ksodoku[ loc[0] ] [ loc[1] ] = 0;
        return false;
    }

    public void IntialDomain()
    {
        size = Ksodoku.length;
        Domain = new boolean[3][size][size];
        for(int ik=0; ik<3; ik++)
            for(int index=0; index<size; index++)
                for(int value=0; value<size; value++)
                    Domain[ik][index][value] = true;

        for(int ix=0; ix<size; ix++)
            for(int iy=0; iy<size; iy++)
                ApplyConstraint(ix, iy);
    }

    public void ApplyConstraint(int ix, int iy)
    {
        int val = Ksodoku[ix][iy];
        if(val != 0)
        {
            int index = (ix-(ix%(int)Math.sqrt(size))) + iy/(int)Math.sqrt(size);
            Domain[1][iy][val-1] = Domain[0][ix][val-1] = false;
            Domain[2][index][val-1] = false;
        }
    }

    public void RemoveConstraint(int ix, int iy)
    {
        int val = Ksodoku[ix][iy];
        if(val != 0)
        {
            int index = (ix-(ix%(int)Math.sqrt(size))) + iy/(int)Math.sqrt(size);
            Domain[1][iy][val-1] = Domain[0][ix][val-1] = true;
            Domain[2][index][val-1] = true;
        }
    }

    public boolean IsValidValue(int value, int ix, int iy)
    {
        if (value == 0) return true;
        if (!(0<value && value <= size)) return false;
        int index = (ix-(ix%(int)Math.sqrt(size))) + iy/(int)Math.sqrt(size);
        return Domain[0][ix][value-1] && Domain[1][iy][value-1] && Domain[2][index][value-1];
    }

    private int [] FindMinimumLoc()
    {
        int location[] = {-1, -1, size+1};
        for(int ix=0; ix<size; ix++)
            for(int iy=0; iy<size; iy++)
            {
                int count = 0;
                for(int val=1; val<=size; val++)
                    if(IsValidValue(val, ix, iy)) count++;
                if(Ksodoku[ix][iy] == 0 && count < location[2])
                {
                    location[0] = ix;
                    location[1] = iy;
                    location[2] = count;
                }
            }
        return location;
    }


    public void Generate()
    {
        int ixys [] = new int[size];
        for(int e=0; e<size; e++)
            ixys[e] = e;
        for(int e=0; e<size; e++)
        {
            int index = (random.nextInt()&0xFFFFF) % size;
            int temp = ixys[e];
            ixys[e] = ixys[index];
            ixys[index] = temp;
        }
        IntialDomain();
        for(int ix=0; ix<size; ix++)
            for(int iy=0; iy<size; iy++)
            {
                RemoveConstraint(ixys[ix], ixys[iy]);
                int count = 0;
                for(int val=1; val<=size; val++)
                    if(IsValidValue(val, ixys[ix], ixys[iy])) count++;
                if(count == 1)
                    Ksodoku[ ixys[ix] ] [ ixys[iy] ] = 0;
                else
                    ApplyConstraint(ixys[ix], ixys[iy]);
            }
    }

    public void set(int val, int ix, int iy)
    {
        if(0 <= val && val <= size)
            Ksodoku[ix][iy] = val;
    }

    public int get(int ix, int iy)
    {
        return Ksodoku[ix][iy];
    }

    public static void main(String args[])
    {
        Sodoku ksdk = new Sodoku(16);
        ksdk.print();
        ksdk.solver();
        ksdk.print();
        ksdk.Generate();
        ksdk.print();
    }
}
