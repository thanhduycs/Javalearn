package codility.chap8;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class PeaksSolution2 {

	public void printArr(int[] A, String caption) {
		if (A.length > 100)
			return;
		System.out.printf(caption + " {%d,", A[0]);
		for (int i = 1; i < A.length - 1; i++) {
			System.out.printf("%2d, ", A[i]);
		}
		System.out.printf("%2d}\n", A[A.length - 1]);
	}

	public int[] findDivisor(int n, int max_size) {
		int[] array = new int[n];
		int root = (int) Math.sqrt(n);
		int i = 0;
		for (int divisor = 2; divisor <= root; divisor++) {
			if (n % divisor == 0) {
				int b = n / divisor;
				if (divisor >= n / max_size)
					array[i++] = divisor;
				if (b >= n / max_size)
					array[i++] = b;
			}
		}
		return Arrays.copyOf(array, i);
	}

	public int[] findPeaks(int[] A) {
		int[] array = new int[A.length / 2];
		int i = 0;
		for (int p = 1; p < A.length - 1; p++) {
			if (A[p] > A[p - 1] && A[p] > A[p + 1]) {
				array[i++] = p;
			}
		}
		return Arrays.copyOf(array, i);
	}

	public int findMaxDivided(int[] A, int n) {
		return 0;
	}

	public int k(int[] A) {

		System.out.println("LENGTH = " + A.length);
		int[] peaks = findPeaks(A);

		if (peaks.length == 0) {
			return 0;
		}

		int[] divisors = findDivisor(A.length, peaks.length);

		Arrays.sort(divisors);

		boolean[] markers = new boolean[A.length];
		for (Integer d : peaks) {
			markers[d] = true;
		}

		for (int i = 0; i < markers.length; i++) {
			System.out.printf(" %d ", i%10);
		}
		System.out.println();

		int[] prefix_sum = new int[A.length];
		int count = 0;
		for (int i = 0; i < markers.length; i++) {
			boolean b = markers[i];
			if (b)
				count++;
			prefix_sum[i] = count;
			System.out.printf("[%c]", b ? '*' : ' ');
		}
		System.out.println();

		for (int i = 0; i < prefix_sum.length; i++) {
			int j = prefix_sum[i];
			System.out.printf("[%d]", j);
		}
		System.out.println();

		if (divisors.length == 0) {
			return 1;
		}

		printArr(divisors, "DIVISORS: ");
		printArr(peaks, "PEAKS:    ");

		int result = 1;

		for (int i = 0; i < divisors.length; i++) {
			int size = divisors[i]; //
			int x = 0;
			boolean flag = true;
			while (x < prefix_sum.length) {
				int a = x;
				int b = x + size - 1;
				int num = x == 0 ? prefix_sum[b] : prefix_sum[b]
						- prefix_sum[a - 1];
				System.out.printf("(%d, %d) = %d\n", a, b, num);
				x += size;
				if (num == 0) {
					flag = false;
					break;
				}
			}

			if (flag) {
				result = A.length / size;
				break;
			}
		}
		return result;
	}

	public int[] decomposeNumber1(int number) {
		int[] results = new int[100];
		int length = 0;
		int k = 2;
		while (number > 1) {
			if (number % k == 0) {
				results[length++] = k;
				number = number / k;
			} else {
				k = k + 1;
			}
		}
		return Arrays.copyOf(results, length);
	}

	public int[] decomposeNumber2(int number) {
		if (number <= 2) {
			return new int[] { number };
		}
		int[] results = new int[100];
		int length = 0;
		int k = 2;
		double root = Math.sqrt(number);
		while (number > 1) {
			if (number % k == 0) {
				results[length++] = k;
				number = number / k;
				root = Math.sqrt(number);
			} else {
				k = PrimeNumberHelper.next(k);
				if (k > root)
				{
					results[length++] = k;
					break;
				}
			}
		}
		return Arrays.copyOf(results, length);
	}
	
	int split(int[] divisors, int begin, int end)
	{
		return 0;
	}

	public int solution(int[] A) {
		int[] peaks = findPeaks(A);
		int[] divisors = decomposeNumber2(A.length);
		
		printArr(divisors, "DIVISORS:");

		return 0;
	}

	@Test
	public void testCase1() throws Exception {
		int[] arr = { 1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
		int result = solution(arr);

		assertEquals(3, result);
	}

	// @Test
	// public void testCase2() throws Exception {
	// int[] arr = { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0,
	// 1, 1, 0, 0, 1, 0, 1, 0 };
	// int result = solution(arr);
	//
	// assertEquals(result, 3);
	// }
}
