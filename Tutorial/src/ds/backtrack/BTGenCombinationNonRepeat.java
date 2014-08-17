package ds.backtrack;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class BTGenCombinationNonRepeat extends BTGenerator {
	
	public int count = 0;
	
	private void generate(final int[] arr, final boolean[] flags, final int N, final int K, final int i)
	{
		if (i == K)
		{
			count++;
			System.out.println(join(arr));
			return;
		}
		
		for(int tryValue=1; tryValue <= N; tryValue++)
		{
			if (!flags[tryValue-1])
			{
				flags[tryValue-1] = true;
				arr[i] = tryValue;
				generate(arr, flags, N, K, i+1);
				flags[tryValue-1] = false;
			}
		}
		
	}
	
	public void generate(final int N, final int K) {
		int[] arr = new int[K];
		boolean[] flags = new boolean[N];
		generate(arr, flags, N, K, 0);
		System.out.println("Total permuation = " + calculatePermutation(N, K));
	}
	
	@Test
	public void testCase1() throws Exception {
		generate(6, 4);
		System.out.println("Count = " + count);
	}
}
