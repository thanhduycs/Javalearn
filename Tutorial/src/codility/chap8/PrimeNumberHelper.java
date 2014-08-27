package codility.chap8;

import java.util.ArrayList;


public class PrimeNumberHelper {
	private static ArrayList<Integer> primeNumbers = new ArrayList<Integer>(100);

	static
	{
		primeNumbers.add(1);
		primeNumbers.add(2);
		primeNumbers.add(3);
	}
	
	private PrimeNumberHelper()
	{
		//DONOT create instance
	}
	
	public static boolean isPrime(int number) {
		int root = (int) Math.sqrt(number);
		for (int i = 1; i < primeNumbers.size(); i++) {
			int prime = primeNumbers.get(i);
			if (prime > root)
				break;
			if (number % prime == 0)
				return false;
		}
		return true;
	}

	public static int next(int currPrimeNum) {
		int nextPrime;

		if (currPrimeNum < 1)
			return 1;

		int i = primeNumbers.indexOf(currPrimeNum);
		if (i >= 0 && i < primeNumbers.size() - 1)
			return primeNumbers.get(i + 1);

		nextPrime = currPrimeNum;
		if (nextPrime % 2 == 0)
			nextPrime++;
		while (true) {
			nextPrime += 2;
			if (isPrime(nextPrime)) {
				primeNumbers.add(nextPrime);
				return nextPrime;
			}
		}
	}

	public static void main(String[] args) {
		int nextPrime = 1;
		for (int i = 0; i < 10000; i++) {
			nextPrime = next(nextPrime);
			System.out.print(nextPrime + ", ");
		}
	}
}
