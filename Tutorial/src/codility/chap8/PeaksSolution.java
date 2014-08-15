package codility.chap8;

import static org.junit.Assert.*;

import org.junit.Test;

public class PeaksSolution {

	public void printArr(int[] A) {
		if (A.length > 100)
			return;
		System.out.print("{");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%2d, ", A[i]);
		}
		System.out.println(A[A.length - 1] + "}");
	}

	public int findNumberPeas(int[] A) {
		printArr(A);
		int count = 0;
		for (int i = 0; i < A.length - 2; i++) {
			if (A[i + 1] > A[i] && A[i + 1] > A[i + 2]) {
				count++;
			}
		}
		return count;
	}

	public int solution(int[] A) {
		int numPeas = findNumberPeas(A);

		// System.out.println(numPeas);
		return numPeas;
	}

	@Test
	public void testCase1() throws Exception {
		int[] arr = { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 };
		int result = solution(arr);

		assertEquals(result, 1);

	}
}
