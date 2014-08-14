package codility.chap7;

import static org.junit.Assert.*;

import org.junit.Test;


//Max ending
public class Theory {
	public int maxSlide(int[] array){
		int max_slide = array[0];
		int current_max = 0;
		
		for (int i=0; i<array.length; i++)
		{
			current_max = Math.max(current_max, 0) + array[i];
			max_slide = Math.max(current_max, max_slide);
			//System.out.println(max_slide);
		}
		return max_slide;
	}
	
	@Test
	public void testCase1() throws Exception {
		int [] arr = {5, -7, 3, 5, -2, 4, -1};
		int result = maxSlide(arr);
		assertEquals(result, 10);
	}
}
