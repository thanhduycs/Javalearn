package progpearls.chap2;

import static org.junit.Assert.*;

import org.junit.Test;

public class P3Anagram {
	
	public static <T> String join(T[] arr)
	{
		StringBuilder buidler = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			T c = arr[i];
			buidler.append(c);
		}
		return buidler.toString();
	}
	
	public void rearrange(char[] arr)
	{
		//String s = join<String>(new String[]{});
		//System.out.println(s);
	}
	
	@Test
	public void testCase1() throws Exception {
		rearrange("abcd".toCharArray());
	}
}
