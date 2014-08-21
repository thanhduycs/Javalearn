package codility.chap9;

//https://codility.com/demo/results/demo7N8AHU-HAN/
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
		boolean[] isPrimes = new boolean[N + 1];
		int[] counters = new int[N + 1];
		int i;

		i = 0;
		while (i <= N) {
			isPrimes[i] = true;
			i++;
		}

		i = 2;
		while (i <= N) {
			int k = i * i;
			while (k <= N && k >= 0) {
				isPrimes[k] = false;
				k += i;
			}
			i++;
		}

		i = 2;
		while (i <= N) {
			if (!isPrimes[i]) {
				i++;
				continue;
			}
			int k = i * i; // k = i * c
			int c = i;
			while (k <= N && k >= 0) {
				if (isPrimes[c])
					counters[k]++;
				k += i;
				c++;
			}
			i++;
		}

		int[] prefix_sum = new int[N + 1];
		i = 0;
		int count = 0;
		while (i <= N) {
			if (counters[i] == 1) {
				count++;
			}
			prefix_sum[i] = count;
			i++;
		}

		// int[] indexes = new int[N];
		// for (int y = 0; y < N; y++)
		// {
		// indexes[y] = y;
		// }
		// printArr(indexes, "indexes   ");
		// printArr(counters, "counter   ");
		// printArr(prefix_sum, "prefix_sum");

		int[] results = new int[P.length];
		i = 0;
		while (i < P.length) {
			int a = P[i] - 1;
			int b = Q[i];
			if (a < 0)
				results[i] = prefix_sum[b];
			else
				results[i] = prefix_sum[b] - prefix_sum[a];
			i++;
		}
		// printArr(results, "results");
		return results;
	}
	
	public static void main(String[] args) {
		
	}

	@Test
	public void testCase1() throws Exception {
		int[] P = { 1, 4 };
		int[] Q = { 26, 10 };
		solution(26, P, Q);
	}
}
