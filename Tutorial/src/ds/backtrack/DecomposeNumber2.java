package ds.backtrack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DecomposeNumber2 {
	
	public int count = 0;
	
	public static String join(int[] arr, int length) {
		StringBuilder buidler = new StringBuilder();
		for (int i = 1; i < arr.length && arr[i] > 0; i++) {
			int c = arr[i];
			buidler.append(c);
			buidler.append(", ");
		}
		return buidler.toString();
	}
	
	public int decompose(final int[] arr, final int[] sums, 
			final int N, final int i)
	{
		if (sums[i-1] == N)
		{
			System.out.println(++count + ": " + join(arr, i));
			return 0;
		}
		for(int tryValue=arr[i-1]; tryValue<=N-sums[i-1]; tryValue++)
		{
			arr[i] = tryValue;
			sums[i] = sums[i-1] + tryValue;
			decompose(arr, sums, N, i+1);
			arr[i] = 0;
		}
		return 0;
	}
	
	public int decompose(int N)
	{
		int [] arr = new int[N+1];
		int [] sums = new int[N+1];
		arr[0] = 1;
		sums[0] = 0;
		decompose(arr, sums, N, 1);
		return 0;
	}
	
	@Test
	public void testCase1() throws Exception {
		decompose(6);
	}
}
