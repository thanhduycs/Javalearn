package codility.chap10;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommonPrimeDivisors {
	// Binary Euclidean algorithm
	public static int gcdByBinary(int a, int b, int res) {
		// k = a % b; do until k == 0;
		if (a == b)
			return res * a;
		if (a % 2 == 0 && b % 2 == 0)
			return gcdByBinary(a / 2, b / 2, 2 * res);
		if (a % 2 == 0)
			return gcdByBinary(a / 2, b, res);
		if (b % 2 == 0)
			return gcdByBinary(a, b / 2, res);
		if (a > b)
			return gcdByBinary(a - b, b, res);
		return gcdByBinary(a, b - a, res);
	}

	public int solution(int[] A, int[] B) {
	    //X = (X + M) modulo N
	    
	    for (int i = 0; i < A.length &&  i < 1; i++) {
			int gcd = gcdByBinary(A[i], B[i], 1);
			System.out.println(gcd);
		}
		
		
		return 0;
	}

	@Test
	public void testCase1() throws Exception {
		int A[] = { 15, 10, 9 };
		int B[] = { 75, 30, 5 };
		solution(A, B);
	}
}
