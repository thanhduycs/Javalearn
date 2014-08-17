package ds.backtrack;

import static org.junit.Assert.*;

import org.junit.Test;

public class BTQueen {
	public final static int N = 8;
	
	public int chess[];
	// index -> row
	// []    -> column 
	
	public int countSolve = 0;
	public int countSolution = 0;
	
	public BTQueen()
	{
		this.chess = new int[N];
		for(int row=0; row<chess.length; row++)
			chess[row] = -1;
	}
	
	public boolean checkColum(int tryCol)
	{
		for(int row=0; row<chess.length; row++)
			if (chess[row] == tryCol) // already
				return false;
		return true;
	}
	
	public boolean checkCross1(int tryRow, int tryCol)
	{
		final int crossConst = tryRow - tryCol;
		for(int row=0; row<chess.length; row++)
		{
			if (chess[row] != -1)
			{
				if (row - chess[row] == crossConst)
					return false;
			}
		}
		return true;
	}
	
	public boolean checkCross2(int tryRow, int tryCol)
	{
		final int crossConst = tryRow + tryCol;
		for(int row=0; row<chess.length; row++)
		{
			if (chess[row] != -1)
			{
				if (row + chess[row] == crossConst)
					return false;
			}
		}
		return true;
	}
	
	public void solve(int tryRow)
	{
		countSolve ++;
		
		if (tryRow == N)
		{
			countSolution++;
			printSolution();
			return;
		}
		
		for(int tryCol=0; tryCol < N; tryCol++)
		{
			if (!checkColum(tryCol))
				continue;
			
			if (!checkCross1(tryRow, tryCol))
				continue;
			
			if (!checkCross2(tryRow, tryCol))
				continue;
			
			chess[tryRow] = tryCol;
			solve(tryRow+1);
			chess[tryRow] = -1; //clean up. ! very important
		}
	}
	
	public void printSolution()
	{
		for(int row=0; row<chess.length; row++)
		{
			System.out.printf("(%d, %d), ", row+1, chess[row]+1);
		}
		System.out.println();
		
		for(int row=0; row<chess.length; row++)
		{
			for (int i=0 ; i<N; i++)
			{
				if (chess[i] == row)
					System.out.print(" x");
				else
					System.out.print(" *");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	@Test
	public void testCase1() throws Exception {
		solve(0);
		
		System.out.println("Count call solve = " + countSolve);
		System.out.println("Count solution = " + countSolution);
	}
}
