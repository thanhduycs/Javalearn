package codility.chap8;

import java.util.Arrays;

public class FlagsSolution {
	public void printArr(int[] A, String caption) {
		if (A.length > 100)
			return;
		System.out.printf(caption + " {%d,", A[0]);
		for (int i = 1; i < A.length - 1; i++) {
			System.out.printf("%2d, ", A[i]);
		}
		System.out.printf("%2d}\n", A[A.length - 1]);
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

	public int solution(int[] A) {
		int[] peaks = findPeaks(A);
		printArr(peaks, "PEAKS:  ");

		boolean[] markers = new boolean[A.length];
		for (int i = 0; i < peaks.length; i++) {
			markers[peaks[i]] = true;
		}

		int count = 0;
		int[] prefix_sum = new int[A.length];
		for (int i = 0; i < markers.length; i++) {
			boolean b = markers[i];
			if (b)
				count++;
			prefix_sum[i] = count;
		}

		printArr(prefix_sum, "PREFIX: ");

		int[] differents = new int[peaks.length];

		int mark = peaks[0];
		int min_diff = Integer.MAX_VALUE;

		for (int i = 1; i < peaks.length; i++) {
			int t = peaks[i] - mark;
			min_diff = Math.min(t, min_diff);
			mark = peaks[i];
			differents[i] = t;
		}
		
		int max_flag = peaks.length;
		
		while(max_flag > 1)
		{
			int size = (int)(A.length / (double)max_flag);
		}
		
		
		printArr(differents, "DIFF:   ");

		System.out.println(min_diff);
		return max_flag;
	}

	public static void main(String[] args) {
		FlagsSolution flagsSolution = new FlagsSolution();
		int[] arr = new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };

		flagsSolution.solution(arr);
	}
}
