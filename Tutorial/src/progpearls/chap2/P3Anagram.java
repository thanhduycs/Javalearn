package progpearls.chap2;

import static org.junit.Assert.*;

import org.junit.Test;

public class P3Anagram {

	public static String join(int[] arr) {
		StringBuilder buidler = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			int c = arr[i];
			buidler.append(c);
		}
		return buidler.toString();
	}
	
	static int count = 1;

	public void reArrange(int[] arr, int base, int end) {
		System.out.println(count++ + ". " + join(arr));
		int last = arr[end];
		int i = 0;
		for(i=end-1; i >= 0 && (arr[i+1] < arr[i] || arr[i] > last) ;i--)
		{
			arr[i+1] = arr[i];
		}
		
		//System.out.println(join(arr));
		//System.out.println(i);
		
		if (i > 0)
		{
			arr[i+1] = arr[i];
			arr[i] = last;
			//System.out.println(join(arr));
			reArrange( arr, base, end);
		}
	}
	
	public void reArrange(int[] arr) {
		reArrange(arr, arr.length-1, arr.length-1);
	}

	@Test
	public void testCase1() throws Exception {
		//1. 123456
		//2. 123465
		//3. 123546
		//4. 123564
		//5. 124356
		
		//...
		//654321
		
//		1. 123456
//		2. 123465
//		3. 123546
//		4. 123564
//		5. 123456
//		6. 123465
//		7. 123546
//		8. 123564
//		9. 123456
		reArrange(new int[] { 1, 2, 3, 4, 5, 6 });
	}
}
