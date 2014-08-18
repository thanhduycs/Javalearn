package codility.chap4;

public class MaxProductOfThree {
	public int solution(int[] A) {
		java.util.Arrays.sort(A); // quick sort

		// case1: A[0], A[1] is smallest negative, A[A.length-1] is largest
		// positive.
		int m1 = A[0] * A[1] * A[A.length - 1];

		// case2: top 3 largest positive numbers
		int m2 = A[A.length - 3] * A[A.length - 2] * A[A.length - 1];

		return Math.max(m1, m2);
	}
}
