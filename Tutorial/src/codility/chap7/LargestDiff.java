package codility.chap7;

import static org.junit.Assert.*;

import org.junit.Test;

public class LargestDiff {
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
        int[] atemp = new int[A.length];
        for (int i = 1; i < atemp.length; i++) {
            atemp[i] = A[i] - A[i - 1];
        }

        printArr(atemp);
        int curr_diff = 0;
        int max_diff = 0;
        for (int i = 1; i < atemp.length; i++) {
            int atemp_i =  A[i] - A[i - 1];
            curr_diff = Math.max(0, curr_diff) + atemp_i;
            max_diff = Math.max(max_diff, curr_diff);
        }
        return max_diff;
    }

    @Test
    public void testCase1() throws Exception {
        int[] A = { 10, 3, 6, 8, 9, 4, 3 };
        int result = solution(A);
        assertEquals(6, result);
    }
}
