//https://codility.com/demo/results/demoBEJR29-KTR/

package codility.chap12;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinMaxDivision {
    public int id = 0;

    public void printArr(int[] A, String caption) {
        if (A.length > 100)
            return;
        System.out.printf("%s{", caption);
        for (int i = 0; i < A.length - 1; i++) {
            System.out.printf("%2d, ", A[i]);
        }
        System.out.println(A[A.length - 1] + "}");
    }
    
    private boolean verifySolution(int K, int[] A, final int cost) {
        int count = 1;
        int value = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > cost)
                return false;

            if (value + A[i] <= cost) {
                value += A[i];
            } else {
                value = A[i];
                count++;
                if (count > K)
                    return false;
            }
        }
        return true;
    }

    public int binarySearchSolution(int K, int[] A, int begin, int end) {
        if (end - begin < 2) {
            if(verifySolution(K, A, begin))
                return begin;
            return end;
        }
        int middle = (begin + end) / 2;
        if (verifySolution(K, A, middle)) {
            return binarySearchSolution(K, A, begin, middle);
        } else {
            return binarySearchSolution(K, A, middle, end);
        }
    }

    public int solution(int K, int M, int[] A) {
        int one = 0;
        for (int i = 0; i < A.length; i++) {
            one += A[i];
        }

        int idealCase = (int) (one / K);
        int result = binarySearchSolution(K, A, idealCase, one);
        return result;
    }

    @Test
    public void testCase1() throws Exception {
        int arr[] = { 2, 1, 1, 1, 5, 1, 2, 2, 2 };
        int result = solution(3, 5, arr);
        assertEquals(6, result);
    }

    // @Test
    // public void testCase2() throws Exception {
    // int arr[] = { 3, 5 };
    // int result = solution(2, 5, arr);
    // assertEquals(5, result);
    // }
    //
    // @Test
    // public void testCase3() throws Exception {
    // int arr[] = { 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1,
    // 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1,
    // 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0,
    // 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0,
    // 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 };
    // int result = solution(2, 100, arr);
    // assertEquals(27, result);
    // }
}
