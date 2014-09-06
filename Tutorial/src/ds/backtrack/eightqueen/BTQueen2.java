package ds.backtrack.eightqueen;

import org.junit.Test;

public class BTQueen2 {
	public final static int N = 8;
	
	public int chess[];
	// index -> row
	// []    -> column 
	
	public boolean [] cross1;
	public boolean [] cross2;
	
	public int countSolve = 0;
	public int countSolution = 0;
	
	public BTQueen2()
	{
		this.chess = new int[N];
		for(int row=0; row<chess.length; row++)
		{
			chess[row] = -1;
		}
		
		this.cross1 = new boolean[2 * N];
		this.cross2 = new boolean[2 * N];
	}
	
	public boolean checkColum(int tryCol)
	{
		for(int row=0; row<chess.length; row++)
			if (chess[row] == tryCol) // already
				return false;
		return true;
	}
	
	public void setCross1(int tryRow, int tryCol, boolean value)
	{
		final int crossConst = tryRow - tryCol;
		cross1[crossConst + 7] = value;
	}
	
	public boolean checkCross1(int tryRow, int tryCol)
	{
		final int crossConst = tryRow - tryCol;
		return !cross1[crossConst + 7];
	}
	
	public void setCross2(int tryRow, int tryCol, boolean value)
	{
		final int crossConst = tryRow + tryCol;
		cross2[crossConst] = value;
	}
	
	public boolean checkCross2(int tryRow, int tryCol)
	{
		final int crossConst = tryRow + tryCol;
		return !cross2[crossConst];
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
			setCross1(tryRow, tryCol, true);
			setCross2(tryRow, tryCol, true);
			solve(tryRow+1);
			setCross1(tryRow, tryCol, false);
			setCross2(tryRow, tryCol, false);
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
