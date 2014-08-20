package codility.chap4;

//you can also use imports, for example:
//import java.util.*;

//you can use System.out.println for debugging purposes, e.g.
//System.out.println("this is a debug message");

//https://codility.com/demo/results/demoS5DX2C-QDH/

class DistinctSolution {

	public void merge(int[] arr, int[] atemp, int begin, int middle, int end) {
		int a = begin;
		int b = middle;
		int i = begin;

		// merge 2 segments together in increasing order.
		while (a < middle && b < end) {
			if (arr[a] < arr[b])
				atemp[i++] = arr[a++];
			else
				atemp[i++] = arr[b++];
		}

		// copy remain elements on first segment.
		while (a < middle) {
			atemp[i++] = arr[a++];
		}

		// copy remain elements on last segment.
		while (b < end) {
			atemp[i++] = arr[b++];
		}

		// copy atemp to arr on range (begin, end)
		for (i = begin; i < end; i++) {
			arr[i] = atemp[i];
		}
	}

	public void mergeSort(int[] arr, int[] atemp, int begin, int end) {
		if (end - begin < 2)
			return; // only one element on segment -> it was sorted in order

		int middle = (begin + end) / 2;
		mergeSort(arr, atemp, begin, middle);
		mergeSort(arr, atemp, middle, end);

		merge(arr, atemp, begin, middle, end);
	}

	// main mergeSort
	public void mergeSort(int[] arr) {
		int[] atemp = new int[arr.length];
		mergeSort(arr, atemp, 0, arr.length);
	}

	public void printArr(int[] arr) {
		if (arr.length > 100)
			return;
		for (int e : arr)
			System.out.printf("%d, ", e);
		System.out.println();
	}

	public int solution(int[] A) {
		mergeSort(A);
		// printArr(A);

		int count = 0;
		if (A.length > 0) {
			count = 1;
			int val = A[0];
			for (int i = 1; i < A.length; i++) {
				if (val != A[i]) {
					count++;
					val = A[i];
				}
			}
		}

		return count;
	}
}
