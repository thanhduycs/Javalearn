package codility.chap4;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class NumberOfDiscIntersections3 {
	
	public void printArr(int[] arr) {
		if (arr.length > 100)
			return;
		for (int e : arr)
			System.out.printf("%d, ", e);
		System.out.println();
	}
	
	public int solution(int[] A) {
		
		int[] startPoints = new int[A.length];
		int[] endPoints = new int[A.length];
		
		for (int i = 0; i < A.length; i++) {
			startPoints[i] = i - A[i];
			endPoints[i] = i + A[i];
		}
		
		
		Arrays.sort(startPoints);
		Arrays.sort(endPoints);
		
		printArr(startPoints);
		printArr(endPoints);
		
		//count: number start point before end point of current circle.
		//i: current 
		
		int total = 0;
		int count = 0;
		for (int i = 0; i < endPoints.length-1; i++) {
//			count = 0;
			while(count < startPoints.length
					&& startPoints[count] <= endPoints[i])
			{
				count++;
			}
			int c = count - i - 1;
			total += c;
//			System.out.println(c);
		}
		return total;
	}
	
	@Test
	public void testCase1() throws Exception {
		int[] arr = new int[]{1, 5, 2, 1, 4, 0};
		int result = solution(arr);
		assertEquals(11, result);
	}
}
