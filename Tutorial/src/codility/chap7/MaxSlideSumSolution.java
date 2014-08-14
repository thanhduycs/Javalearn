package codility.chap7;

//https://codility.com/demo/results/demoSD9BKX-AJE/

import static org.junit.Assert.*;

import org.junit.Test;

public class MaxSlideSumSolution {
	public int solution(int[] A) {
        int max_ending = 0;
        int max_slide = A[0];
        
        for(int i=0; i<A.length; i++)
        {
            max_ending = Math.max(0, max_ending) + A[i];
            max_slide = Math.max(max_slide, max_ending);
        }
        return max_slide;
    }
	
	@Test
	public void testCase1() throws Exception {
		int result = solution(new int[]{3,2,-6,4,0});
		assertEquals(5, result);
	}
}
