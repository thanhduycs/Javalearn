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

	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public boolean nextNum(int[] arr) {

		int i = arr.length - 1;
		while (i > 0 && arr[i] < arr[i - 1]) {
			i--;
		}
		i--;

		if (i < 0) {
			return false;
		}

		int l = arr.length - 1;
		while (arr[l] < arr[i])
			l--;

		swap(arr, i, l);

		i++;
		l = arr.length - 1;
		while (i < l) {
			swap(arr, i, l);
			i++;
			l--;
		}
		return true;
	}

	public static void main(String[] args) {
		int[] numArr = new int[] { 1, 2, 3, 4 };

		final String text = "pots";
		numArr = new int[text.length()];
		for (int i = 0; i < numArr.length; i++)
			numArr[i] = i;

		P3Anagram anagram = new P3Anagram();

		boolean hashNext = true;
		for (int i = 0; hashNext; i++) {
			for (int j = 0; j < numArr.length; j++) {
				System.out.print(text.charAt(numArr[j]));
			}
			System.out.println();
			hashNext = anagram.nextNum(numArr);
			// System.out.println(i + "   Next: " + join(numArr));
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
		int[] expArr = null;
		numArr = new int[] { 1, 2, 4, 5, 3 };
		expArr = new int[] { 1, 2, 5, 3, 4 };

		int[] nextNumArr = numArr.clone();
		anagram.nextNum(nextNumArr);
		System.out.println(" Current: " + join(numArr));
		System.out.println("    Next: " + join(nextNumArr));
		System.out.println("    Expc: " + join(expArr));
		assertArrayEquals(expArr, nextNumArr);
	}

	@Test
	public void testCase3() throws Exception {
		P3Anagram anagram = new P3Anagram();
		anagram.setDebugMode(true);
		int[] numArr = null;
		int[] expArr = null;
		numArr = new int[] { 2, 3, 4, 5, 1 };
		expArr = new int[] { 2, 3, 5, 1, 4 };

		int[] nextNumArr = numArr.clone();
		anagram.nextNum(nextNumArr);
		System.out.println(" Current: " + join(numArr));
		System.out.println("    Next: " + join(nextNumArr));
		System.out.println("    Expc: " + join(expArr));

		assertArrayEquals(expArr, nextNumArr);
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
}
