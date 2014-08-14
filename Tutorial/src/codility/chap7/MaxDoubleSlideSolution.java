package codility.chap7;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaxDoubleSlideSolution {
	
	public void printArr(int[] A) {
		if (A.length > 100)
			return;
		System.out.print("{");
		for (int i : A) {
			System.out.print(i + ", ");
		}
		System.out.println("}");
	}
	
	public int solution(int[] A)
	{
		int [] lefts = new int [A.length];
        int [] rights = new int[A.length];
        
        printArr(A);
        
        //calucalte for left ->
        int max_ending = 0;
        for(int i=1; i<A.length-1; i++)
        {
            max_ending = Math.max(max_ending,0) + A[i];
            lefts[i] = max_ending;
        }
        printArr(lefts);
        
        //right <-
        max_ending= 0;
        for(int i=A.length-2; i>1; i--)
        {
            max_ending = Math.max(max_ending,0) + A[i];
            rights[i] = max_ending;
        }
        printArr(rights);
        
        int max_slide = 0;
        for(int i=0; i<A.length-2; i++)
        {
            int t = lefts[i] + rights[i+2];
            max_slide = Math.max(max_slide, t);
            max_slide = Math.max(max_slide, lefts[i+1]);
//            max_slide = Math.max(max_slide, rights[i]);
//            max_slide = Math.max(max_slide, lefts[i]);
            System.out.println(i + "->" + t);
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
	
	
	
	@Test
	public void testCase5() throws Exception {
		int[] arr = {3, 2, 6, -1, 4, 5, -1, 2, };
		int result = solution(arr);
		assertEquals(14, result);
	}
	
	
	
}
