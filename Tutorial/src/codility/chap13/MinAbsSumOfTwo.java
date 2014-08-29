//https://codility.com/demo/results/demoMQRW9S-DRT/
//https://codility.com/demo/results/demoD5SW3C-BB3/ (100%)

package codility.chap13;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinAbsSumOfTwo {
    public int solution(int[] A) {
        java.util.Arrays.sort(A); // quickSort

        int i = 0;
        while (i < A.length - 1 && Math.abs(A[i]) > A[i + 1]) {
            i++;
        }

        int left = i;
        int right = i;

        int minimum_sum = Math.abs(A[i] + A[i]);
        if (i > 0) {
            minimum_sum = (int) Math.min(minimum_sum,
                    Math.abs(A[i - 1] + A[i - 1]));
        }
        
        //System.out.println(i);

        while (left >= 0 && right < A.length) {
            int lf_val = Math.abs(A[left]);
            int rt_val = Math.abs(A[right]);

            minimum_sum = (int) Math.min(minimum_sum,
                    Math.abs(A[left] + A[right]));

            if (lf_val == rt_val)
            {
                right++;
            } else if (lf_val < rt_val) {
                left--;
            } else {
                right++;
            }
        }
        return minimum_sum;
    }
    
    @Test
    public void testCase1() throws Exception {
        System.out.println(solution(new int[]{2000, -2000, 100, -500}));
    }
}
