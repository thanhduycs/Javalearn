package ds.backtrack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DecomposeNumber {
	
	public int count = 0;
	
	public static String join(int[] arr, int length) {
		StringBuilder buidler = new StringBuilder();
		for (int i = 0; i < arr.length && arr[i] > 0; i++) {
			int c = arr[i];
			buidler.append(c);
			buidler.append(", ");
		}
		return buidler.toString();
	}
	
	public int decompose(final int[] arr, final int totalRemainValue, final int i, final int startValue)
	{
		if (totalRemainValue == 0)
		{
			System.out.println(++count + ": " + join(arr, i));
			return 0;
		}
		for(int tryValue=startValue; tryValue<=totalRemainValue; tryValue++)
		{
			arr[i] = tryValue;
			decompose(arr, totalRemainValue-tryValue, i+1, tryValue);
			arr[i] = 0;
		}
		return 0;
	}
	
	public int decompose(int N)
	{
		int [] arr = new int[N];
		decompose(arr, N, 0, 1);
		return 0;
	}
	
	@Test
	public void testCase1() throws Exception {
		decompose(6);
	}
}
