package progpearls.chap2;

import static org.junit.Assert.*;

import java.io.ObjectInputStream.GetField;

import org.junit.Test;

public class P3Anagram {
	
	public boolean debugMode = false;

	public static String join(int[] arr) {
		StringBuilder buidler = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			int c = arr[i];
			buidler.append(c);
			buidler.append(", ");
		}
		return buidler.toString();
	}

	static int count = 1;

	public void reArrange(int[] arr, int base, int end) {
		System.out.println(count++ + ". " + join(arr));
		int last = arr[end];
		int i = 0;
		for (i = end - 1; i >= 0 && (arr[i + 1] < arr[i] || arr[i] > last); i--) {
			arr[i + 1] = arr[i];
		}

		// System.out.println(join(arr));
		// System.out.println(i);

		if (i > 0) {
			arr[i + 1] = arr[i];
			arr[i] = last;
			// System.out.println(join(arr));
			reArrange(arr, base, end);
		}
	}

	public void reArrange(int[] arr) {
		reArrange(arr, arr.length - 1, arr.length - 1);
	}

	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public int[] nextNum(int[] orgNum) {
		int[] arr = new int[orgNum.length];
		System.arraycopy(orgNum, 0, arr, 0, orgNum.length);

		int LENGTH = arr.length;
		int last = arr[LENGTH - 1];
		int i = LENGTH - 1;
		while (arr[i - 1] > arr[i] && i > 1) {
			arr[i] = arr[i-1];
			i--;
		}

		if (i <= 0) {
			return null;
		}

		// [i-1] < [i]

//		int a = i;
//		int b = LENGTH - 2;
//
//		while (a < b) {
//			swap(arr, a, b);
//			a++;
//			b--;
//		}

		// int k = i;
		// while (k < LENGTH-1) {
		// arr[k+1] = arr[k];
		// }
		
		//swap(arr, i, i-1);
		//arr[i] = last;
		if (debugMode)
		{
			System.out.printf("i=%d, length=%d\n", i, LENGTH);
		}
		return arr;
	}

	public static void main(String[] args) {

		int[] numArr = new int[] { 0, 6, 5, 4, 3, 2, 1 };

		numArr = new int[] { 1, 2, 3, 4, 5 };
		//numArr = new int[] { 1, 2, 4, 5, 3 };

		int[] nextNumArr = numArr;
		System.out.println(" Current: " + join(numArr));

		P3Anagram anagram = new P3Anagram();
		for (int i = 0; nextNumArr != null; i++) {
			numArr = nextNumArr;
			nextNumArr = anagram.nextNum(numArr);
			System.out.println(i + "   Next: " + join(nextNumArr));
		}
	}

	@Test
	public void testCase1() throws Exception {
		// 1. 123456
		// 2. 123465
		// 3. 123546
		// 4. 123564
		// 5. 124356

		// ...
		// 654321

		// 0654321
		// 1023456

		// 1. 123456
		// 2. 123465
		// 3. 123546
		// 4. 123564
		// 5. 123456
		// 6. 123465
		// 7. 123546
		// 8. 123564
		// 9. 123456
		// reArrange(new int[] { 1, 2, 3, 4, 5, 6 });
	}

	@Test
	public void testCase2() throws Exception {
		P3Anagram anagram = new P3Anagram();
		int[] numArr = null;
		int[] expectArr = null;
		numArr = new int[] { 1, 2, 4, 5, 3 };
		expectArr = new int[] { 1, 3, 2, 4, 5 };

		int[] nextNumArr = anagram.nextNum(numArr);
		nextNumArr = anagram.nextNum(numArr);
		System.out.println(" Current: " + join(numArr));
		System.out.println("    Next: " + join(nextNumArr));

		assertArrayEquals(expectArr, nextNumArr);
	}
	
	@Test
	public void testCase3() throws Exception {
		P3Anagram anagram = new P3Anagram();
		anagram.setDebugMode(true);
		int[] numArr = null;
		int[] expArr = null;
		numArr = new int[] { 2, 3, 4, 5, 1 };
		expArr = new int[] { 2, 3, 5, 1, 4 };

		int[] nextNumArr = anagram.nextNum(numArr);
		nextNumArr = anagram.nextNum(numArr);
		System.out.println(" Current: " + join(numArr));
		System.out.println("    Next: " + join(nextNumArr));
		System.out.println("    Expc: " + join(expArr));

		assertArrayEquals(expArr, nextNumArr);
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
}
