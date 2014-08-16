package ds.generate;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenCombinations extends Generator {
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

		int[] first = new int[K];
		for (int i = 0; i < first.length; i++) {
			first[i] = i + 1;
		}

		for (int l = 0; ; l++) {
			System.out.println(l + ": " + join(first));
			int i = first.length - 1;
			for (int f = 0; i >= 0; f++) {
				if (first[i] < N - f) {
					first[i]++;
					break;
				}
				i--;
			}

			if (i < 0)
				return;

			int a = first[i];
			while (++i < first.length) {
				first[i] = ++a;
			}

		}
	}

	@Test
	public void testCase1() throws Exception {
		gen(9, 6);
	}
	
//	10
//	0: 1, 2, 3, 
//	1: 1, 2, 4, 
//	2: 1, 2, 5, 
//	3: 1, 3, 4, 
//	4: 1, 3, 5, 
//	5: 1, 4, 5, 
//	6: 2, 3, 4, 
//	7: 2, 3, 5, 
//	8: 2, 4, 5, 
//	9: 3, 4, 5, 
}
