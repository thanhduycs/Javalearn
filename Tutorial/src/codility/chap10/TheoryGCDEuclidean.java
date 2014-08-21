package codility.chap10;

public class TheoryGCDEuclidean {
	public static int gcdByDivision(int a, int b)
	{
		if (a % b == 0)
			return b;
		return gcdByDivision(b, a % b);
	}
	
	//Binary Euclidean algorithm
	public static int gcdByBinary(int a, int b, int res)
	{
		if (a == b)
			return res * a;
		if (a % 2 == 0 && b % 2 == 0)
			return gcdByBinary(a/2, b/2, 2*res);
		if (a % 2 == 0)
			return gcdByBinary(a/2, b, res);
		if (b % 2 == 0)
			return gcdByBinary(a, b/2, res);
		if (a > b)
			return gcdByBinary(a-b, b, res);
		return gcdByBinary(a, b-a, res);
	}
}
