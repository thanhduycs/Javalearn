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

    private int solv(int K, int M, int[] A, int[] results, int begin, int end) {
        System.out.printf("(%d, %d)\n", begin, end);
        if (end - begin < 2) {
            return A[begin];
        }
        int mid = (end + begin) / 2;
        int left = solv(K, M, A, results, begin, mid);
        int right = solv(K, M, A, results, mid + 1, end);
        System.out.printf("  ===> (%d, %d) : %d, %d\n", begin, end, left, right);

        if (left <= right) {
            // +left
            left = left + A[mid];
        } else {
            right = right + A[mid];
        }
        return right;
    }

    public int solution(int K, int M, int[] A) {
        int[] prefix_sum = new int[A.length];
        prefix_sum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefix_sum[i] = prefix_sum[i - 1] + A[i];
        }

        printArr(A, "Array:      ");
        // printArr(prefix_sum, "Prefix_Sum: ");

        int[] results = new int[2];
        results[0] = -1;
        results[1] = Integer.MAX_VALUE;
        solv(K, M, A, results, 0, A.length);
        return results[1];
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
