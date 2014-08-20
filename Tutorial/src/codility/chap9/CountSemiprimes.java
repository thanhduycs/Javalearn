package codility.chap9;

import static org.junit.Assert.*;

import org.junit.Test;


public class CountSemiprimes {
	public void printArr(int[] A, String caption) {
		if (A.length > 100)
			return;
		System.out.printf(caption + " {%d,", A[0]);
		for (int i = 1; i < A.length - 1; i++) {
			System.out.printf("%2d, ", A[i]);
		}
		System.out.printf("%2d}\n", A[A.length - 1]);
	}

	public int[] solution(int N, int[] P, int[] Q) {
		int[] counters = new int[N + 1];
		int i = 2;
		while (i <= N) {
			int k = i;
			while (k <= N) {
				counters[k]++;
				k += i;
			}
			i++;
		}

		int[] prefix_sum = new int[N + 1];
		i = 0;
		int count = 0;
		while (i <= N) {
			if (counters[i] == 1) {
				System.out.print(i + ", ");
				count++;
			}
			prefix_sum[i] = count;
			i++;
		}
		System.out.println();

		printArr(counters,   "counter   ");
		printArr(prefix_sum, "prefix_sum");

		int[] results = new int[P.length];
		i = 0;
		while (i < P.length) {
			int a = P[i] - 1;
			int b = Q[i];
			results[i] = prefix_sum[b] - prefix_sum[a];
			i++;
		}
		printArr(results, "results");
		return results;
	}
	
	@Test
	public void testCase1() throws Exception {
		int [] P = {1,  4};
		int [] Q = {26, 10};
		solution(26, P, Q);
	}
}
