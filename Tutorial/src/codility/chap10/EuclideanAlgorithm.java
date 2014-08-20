package codility.chap10;

import static org.junit.Assert.*;

import org.junit.Test;

public class EuclideanAlgorithm {
	public int GreatestCommonDivisor(int a, int b) {
		
		while(true)
		{
			if (a == b)
				return a;
			if (a==0)
				return b;
			if (b==0)
				return a;
			if (a > b)
				a = a % b;
			else
				b = b % a;
		}

		//asume that k is greatest.
		//a = k * m
		//b = k * n
		
		//a = b * x + m
		//gcd(a,b) = gcd(b,m)
	}
	
	@Test
	public void testCase1() throws Exception {
		int result = GreatestCommonDivisor(11, 22);
		assertEquals(11, result);
	}
}
