package codility.chap7;

//https://codility.com/demo/results/demo7M6Z6U-X5C/
import static org.junit.Assert.*;

import org.junit.Test;

public class MaxDoubleSlideSolution {
	
	public void printArr(int[] A) {
		if (A.length > 100)
			return;
		System.out.print("{");
		for (int i =0; i<A.length-1; i++) {
			System.out.printf("%2d, ", A[i]);
		}
		System.out.println(A[A.length-1] + "}");
	}
	
    public int solution(int[] A) {
        int[] lefts = new int[A.length];
        int[] rights = new int[A.length];

        // printArr(A);

        // calucalte for left ->
        int max_ending = 0;
        for (int i = 1; i < A.length - 1; i++) {
            max_ending = Math.max(max_ending, 0) + A[i];
            lefts[i] = Math.max(max_ending, 0);
        }
        // printArr(lefts);

        // right <-
        max_ending = 0;
        for (int i = A.length - 2; i > 1; i--) {
            max_ending = Math.max(max_ending, 0) + A[i];
            rights[i] = Math.max(max_ending, 0);
        }
        // printArr(rights);

        int max_slide = 0;
        for (int i = 0; i < A.length - 2; i++) {
            int t = lefts[i] + rights[i + 2];
            max_slide = Math.max(max_slide, t);
            // System.out.println(i + "->" + t);
        }
        return max_slide;
    }
	
	@Test
	public void testCase1() throws Exception {
		int[] arr = {3, 2, 6, -1, 4, 5, -1, 2};
		int result = solution(arr);
		assertEquals(17, result);
	}
	
	@Test
	public void testCase2() throws Exception {
		int[] arr = {6, 1, 5, 6, 4, 2, 9, 4, };
		int result = solution(arr);
		assertEquals(26, result);
	}
	
	
	@Test
	public void testCase3() throws Exception {
		int[] arr = {-8, 10, 20, -5, -7, -4, };
		int result = solution(arr);
		assertEquals(30, result);
	}
	
	
	@Test
	public void testCase4() throws Exception {
		int[] arr = {0, 10, -5, -2, 0, };
		int result = solution(arr);
		assertEquals(10, result);
	}	
}
