package ds.generate;

import org.junit.Test;

public class GenPermutation extends Generator {

	public boolean next(int[] arr) {
		final int LAST = arr.length - 1;
		int m = 0;
		int n = 0;

		// find the last not decrease value, start from last.
		m = LAST;
		while (m > 0 && arr[m - 1] > arr[m]) {
			m--;
		}

		if (m == 0) {
			// reach maximum. all decrease. example: 6 5 4 3 2 1
			return false;
		}

		m--;
		// first position not decrease
		// example: 4 5* 6 3 2 1 <--<--<--<--
		// so, at this time, we can say that 5* less than at least one value in
		// [6, 3, 2, 1]

		n = LAST;
		while (n >= 0 && arr[n] <= arr[m]) {
			n--;
		}

		swap(arr, m, n);
		int a = m + 1;
		int b = LAST;
		while (a < b) {
			swap(arr, a, b);
			a++;
			b--;
		}
		return true;
	}

	public void generate(final int N) {
		System.out.println(calculatePermutation(N, N));

		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++)
			arr[i] = i + 1;

		boolean hashNext = true;
		for (int i = 0; hashNext; i++) {
			System.out.println(i + ": " + join(arr));
			hashNext = next(arr);
		}
	}

	@Test
	public void testCase1() throws Exception {
		// 1 2 3
		// 1 3 2
		// 2 1 3
		// 2 3 1
		// 3 1 2
		// 3 2 1

		// 0 3 2 1
		// 1 0 2 3
		generate(6);
	}

	// 4 5 6 3 2 1
	// .4 6 5 3 2 1
	// . .......
	// 4 6 1 2 3 5

	// 4 6 5 3 2 1
	// .5 6 4 3 2 1
	// 5 1 2 3 4 6

	// 1 3 4 2
	// .2 3 4 1
	// .2 1 4 3
}
