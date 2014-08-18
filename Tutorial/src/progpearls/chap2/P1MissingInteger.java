package progpearls.chap2;

import java.util.Arrays;

public class P1MissingInteger {

	public void findMiss(int[] A) {
		Arrays.sort(A);
		int trackValue = Math.min(A[0], 0);

		for (int i = 1; i < A.length; i++) {
			if (A[i] != trackValue) {
				if (A[i] != trackValue + 1)
				{
					for (int j = trackValue+1; j < A[i]; j++) {
						System.out.print(j + ", ");
					}
					System.out.println();
				}
				trackValue = A[i];
			}
		}

	}

	public static void main(String[] args) {
		int[] arr = new int[] {1, 3, 5, 7, 8, 11, 22, 11, 1, 3, 4, 15};
		new P1MissingInteger().findMiss(arr);
	}
}
