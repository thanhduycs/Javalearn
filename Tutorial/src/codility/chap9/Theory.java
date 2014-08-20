package codility.chap9;

import java.util.Arrays;

public class Theory {

	public final int[] PRIMES = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
			41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
			109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
			181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251,
			257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
			337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
			419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487,
			491, 499, 503, 509, 521, 523, 541 };

	public boolean[] sieveOfEratosthenes(int n) {
		boolean[] sieve = new boolean[n + 1];

		for (int i = 2; i * i < n; i++) {
			if (!sieve[i]) {
				int k = i * i;
				while (k <= n) {
					sieve[k] = true;
					k += i;
				}
			}
		}
		return sieve;
	}

	private int[] factorization(int n) {
		int[] factors = new int[n + 1];
		for (int i = 2; i * i <= n; i++) {
			if (factors[i] == 0) {
				int k = i * i;
				while (k <= n) {
					factors[k] = i;
					k += i;
				}
			}
		}
		return factors;
	}
	
	public int [] factorizationOf(int x, int[] F)
	{
		int [] primeFactors = new int[100];
		int length = 0;
		while(F[x] >= 0)
		{
			primeFactors[length++] = F[x];
			x /= F[x];
		}
		primeFactors[length++] = x;
		return Arrays.copyOf(primeFactors, length);
	}

	public static void main(String[] args) {
		Theory theory = new Theory();
		theory.sieveOfEratosthenes(100);

		int[] array = theory.factorization(20);

		for (int i = 0; i < array.length; i++) {
			int a = array[i];
			System.out.print(a + ", ");
		}
	}
}
