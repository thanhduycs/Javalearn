package codility.chap6;

import static org.junit.Assert.*;

import org.junit.Test;

//https://codility.com/demo/results/demoNSTM5V-DUD/

public class DominatorSolution {
	public void printArr(int[] A, String caption) {
		if (A.length > 100)
			return;
		System.out.printf("%s{", caption);
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + ", ");
		System.out.println("}");
	}

	public int solution(int[] A) {
		//printArr(A, "Arr");
		int val = 0, count = 0;
		for (int i = 0; i < A.length; i++) {
			if (count == 0) {
				val = A[i];
				count++;
			} else if (val == A[i]) {
				count++;
			} else {
				count--;
			}
		}

		if (count == 0)
			return -1;

		int pos = -1;
		count = 0;
		for (int i = 0; i < A.length; i++) {
			if (val == A[i]) {
				count++;
				if (pos == -1)
					pos = i;
			}
		}

		if (count <= (A.length / 2))
			return -1;

		return pos;
	}
	
	
	@Test
	public void testCase1() throws Exception {
		int[] arr ={1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
		int result = solution(arr);
		
		assertEquals(-1, result);
	}
	
	@Test
	public void testCase2() throws Exception {
		int[] arr ={4, 3, 4, 4, 4, 2};
		int result = solution(arr);
		
		assertEquals(0, result);
	}
}
