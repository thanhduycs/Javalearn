package codility.chap9;

import static org.junit.Assert.*;

import org.junit.Test;

///https://codility.com/demo/results/demoYWU84A-DPH/

public class CountNonDivisible {

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
		printArr(A, "inputArr:");
		
		int NN = A.length * 2 + 1;
		int[] divisorCounters = new int[NN];
		int i = 0;
		while (i < A.length) {
			int element = A[i];
			int k = element;
			while (k < NN) {
				divisorCounters[k]++;
				k += element;
			}
			printArr(divisorCounters, "divisorCounters:");
			i++;
		}

		int[] results = new int[A.length];
		i = 0;
		while (i < A.length) {
			int element = A[i];
			results[i] = A.length - divisorCounters[element];
			i++;
		}
		// printArr(results, "steves:");
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
