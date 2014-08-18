package codility.chap4;

//https://codility.com/demo/results/demoM569EA-ECA/
public class TriangleSolution {
	public void printArr(int[] arr) {
		if (arr.length > 100)
			return;
		for (int e : arr)
			System.out.printf("%d, ", e);
		System.out.println();
	}

	public int solution(int[] A) {
		java.util.Arrays.sort(A); // quick sort.
		// printArr(A);

		for (int i = 0; i < A.length - 2; i++) {
			if ((long) A[i] + (long) A[i + 1] > (long) A[i + 2])
				return 1;
		}
		return 0;
	}
}
