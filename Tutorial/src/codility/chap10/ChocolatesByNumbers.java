package codility.chap10;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChocolatesByNumbers {
	public int slu1(int N, int M) {
		int k = 0;
		int i = 0;
		do {
			k = (k + M) % N;
			i++;
		} while (k > 0);
		return i;
	}

	public int slu2(int X, int N, int M) {
		int a = X + M;
		int b = N;

		int k = a % b;
		if (k == 0)
			return 1;

		return 1 + slu2(k, N, M);
	}

	public int slu3(int N, int M) {
		int gcd = TheoryGCDEuclidean.gcdByBinary(N, M, 1);
		int x = slu1(N / gcd, M / gcd);
		return x;
	}

	public int solution(int N, int M) {
		// k = (k + M) % N;
		return slu1(N, M);
	}

	@Test
	public void testCase1() throws Exception {
		int result = solution(10, 4);
		System.out.println(result);
		assertEquals(5, result);
	}
}
