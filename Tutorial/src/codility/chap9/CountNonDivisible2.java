package codility.chap9;

import static org.junit.Assert.*;

import org.junit.Test;


public class CountNonDivisible2 {

	public void printArr(int[] A, String caption) {
		if (A.length > 100)
			return;
		System.out.printf(caption + " {%d,", A[0]);
		for (int i = 1; i < A.length - 1; i++) {
			System.out.printf("%2d, ", A[i]);
		}
		System.out.printf("%2d}\n", A[A.length - 1]);
	}

	public int[] solution(int[] A) {
		int[] arrays = new int[2*A.length + 1];
		for (int i = 0; i < A.length; i++) {
		    arrays[A[i]]++;
        }
		
		printArr(arrays, "arrays");

		int[] results = new int[A.length];
		return results;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testCase1() throws Exception {
		int[] arr = { 3, 1, 2, 3, 6 };
		int[] expect = { 2, 4, 3, 2, 0 };
		int[] results = solution(arr);
		assertArrayEquals(expect, results);
	}

}
