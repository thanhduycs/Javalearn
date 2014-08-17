package ds.generate;

public abstract class Generator {

	private boolean debugMode = false;

	public static String join(int[] arr) {
		StringBuilder buidler = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			int c = arr[i];
			buidler.append(c);
			buidler.append(", ");
		}
		return buidler.toString();
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public boolean isDebugMode() {
		return this.debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	private static int factorial(int n)
	{
		int result = 1;
		while(n > 1)
			result = result * n--;
		return result;
	}
	
	//nCr = C(n,r) = n! / ( r! (n - r)! )
	public static int calculateCombination(int n, int r)
	{
		return factorial(n) / (factorial(r) * factorial(n - r));
	}
	
	//nPr = P(n,r) = n! / (n - r)!
	public static int calculatePermutation(int n, int r)
	{
		return factorial(n) / factorial(n - r);
	}
	
//	Factorial Calculator  n!
//	Combinations Calculator  nCr = C(n,r) = n! / ( r! (n - r)! )
//	Permutations Calculator  nPr = P(n,r) = n! / (n - r)!
//
//	Combinations Replacement Calculator  CR(n,r) = C(n+r-1,r)
//	Permutations Replacement Calculator  PR(n,r) = nr
//
//	Even Permutations Calculator  n! / 2
//	Odd Permutations Calculator  n! / 2, n >= 2
//	Circular Permutation Calculator  Pn = P(n) = (n - 1)!
}
