package ds.generate;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenCombinations extends Generator {
	
	public boolean next(int[] arr, int N)
	{
		int i = arr.length - 1;
		for (int f = 0; i >= 0; f++) {
			if (arr[i] < N - f) {
				arr[i]++;
				break;
			}
			i--;
		}

		if (i < 0)
			return false;

		int a = arr[i];
		while (++i < arr.length) {
			arr[i] = ++a;
		}
		return true;
	}
	
	public void gen(final int N, final int K) {
		System.out.println(calculateCombination(N, K));

		// 1 2 3
		// 1 2 4
		// 1 2 5
		// 1 3 4
		// 1 3 5
		// 1 4 5
		// 2 3 4
		// 2 3 5
		// 2 4 5

		int[] arr = new int[K];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}

		boolean hasNext = true;
		for (int l = 0; hasNext; l++) {
			System.out.println(l + ": " + join(arr));
			hasNext = next(arr, N);
		}
	}

	@Test
	public void testCase1() throws Exception {
		gen(9, 6);
	}

	// 10
	// 0: 1, 2, 3,
	// 1: 1, 2, 4,
	// 2: 1, 2, 5,
	// 3: 1, 3, 4,
	// 4: 1, 3, 5,
	// 5: 1, 4, 5,
	// 6: 2, 3, 4,
	// 7: 2, 3, 5,
	// 8: 2, 4, 5,
	// 9: 3, 4, 5,
}
