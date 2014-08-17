package ds.backtrack;

import static org.junit.Assert.*;

import org.junit.Test;

public class BTBinaryNumber extends BTGenerator {
	public int count = 0;
	
	private void generateBinaryNumber(int[] arr, int i)
	{
		if(i == arr.length)
		{
			count++;
			System.out.println(join(arr));
		}
		else
		{
			arr[i] = 0;
			generateBinaryNumber(arr, i+1);
			arr[i] = 1;
			generateBinaryNumber(arr, i+1);
		}
	}
	
	public void generateBinaryNumber(int N)
	{
		int[] arr = new int[N];
		generateBinaryNumber(arr, 0);
	}
	
	@Test
	public void testCase1() throws Exception {
		final int LENGTH = 6;
		generateBinaryNumber(LENGTH);
		System.out.println(count);
		assertEquals((int)Math.pow(2, LENGTH), count);
	}
}
