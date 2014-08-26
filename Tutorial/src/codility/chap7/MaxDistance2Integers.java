package codility.chap7;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaxDistance2Integers {
    public int solution(int[] A) {
        
        for (int i = 1; i < A.length; i++) {
            System.out.printf("%d, ", A[i]-A[i-1]);
        }
        System.out.println();
        return 1;
    }

    @Test
    public void testCase1() throws Exception {
        int[] arr = { 20, 7, 8, 9, 10, 4, 5, 6, 1, 13, 11, 0 };
        int result = solution(arr);
    }
}
