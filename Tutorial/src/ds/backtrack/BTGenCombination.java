package ds.backtrack;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class BTGenCombination extends BTGenerator {
	
	public int count = 0;
	
	private void generateCombination(final int[] arr, final int N, final int K, final int i)
	{
		if (i == K)
		{
			count++;
			System.out.println(join(arr));
			return;
		}
		
		int start = i == 0 ? 1 : arr[i-1] + 1;
		int end = N - K + i + 1;

		for(int t=start; t <= end; t++)
		{
			arr[i] = t;
			generateCombination(arr, N, K, i+1);
		}
		
	}
	
	public void generateCombination(final int N, final int K) {
		int[] arr = new int[K];
		generateCombination(arr, N, K, 0);
	}
	
	@Test
	public void testCase1() throws Exception {
		generateCombination(9, 6);
		System.out.println(count);
	}
}
