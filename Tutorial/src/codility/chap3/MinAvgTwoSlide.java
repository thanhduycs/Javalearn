//https://codility.com/demo/results/demoM2SNTU-JWZ/

package codility.chap3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinAvgTwoSlide {
	public void printArr(int[] A) {
		if (A.length > 100)
			return;
		System.out.print("{");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%2d, ", A[i]);
		}
		System.out.println(A[A.length - 1] + "}");
	}

	public int solution(int[] A) {
		//printArr(A);

		double min_avg_ending = (A[0] + A[1]) / 2;
		int min_avg_count = 2;
		int min_avg_ith = 0;

		int final_start_ith = 0;
		double final_min_avg = min_avg_ending;

		for (int i = 2; i < A.length; i++) {
			double extend = (min_avg_ending * min_avg_count + A[i])
					/ (min_avg_count + 1);
			
			double not_extend = (A[i] + A[i - 1]) / 2.0;
			
			if (extend <= not_extend) {
				min_avg_ending = extend;
				min_avg_count++;
			} else {
				min_avg_ith = i-1;
				min_avg_ending = not_extend;
				min_avg_count = 2;
			}

			//System.out.printf("%d. %d; %.2f\n", i, min_avg_ith, min_avg_ending);
			if (final_min_avg > min_avg_ending) {
				final_min_avg = min_avg_ending;
				final_start_ith = min_avg_ith; // update start position of slide
			}
		}
		return (int) final_start_ith;
	}
	
	@Test
	public void testCase1() throws Exception {
		int[] arr = {10, 10, -1,  2,  4, -1,  2, -1};
		int result = solution(arr);
		
		assertEquals(5, result);
	}
}
