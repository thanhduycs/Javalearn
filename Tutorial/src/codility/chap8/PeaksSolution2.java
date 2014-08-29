package codility.chap8;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class PeaksSolution2 {

    public void printArr(int[] A, String caption) {
        if (A.length > 100)
            return;
        System.out.printf(caption + " {%d,", A[0]);
        for (int i = 1; i < A.length - 1; i++) {
            System.out.printf("%2d, ", A[i]);
        }
        System.out.printf("%2d}\n", A[A.length - 1]);
    }

    private boolean checkSol(int[] prefix_sum, int nblock, final int N) {
        if (nblock == 0){
            System.out.println();
        }
        int nsize = N / nblock;
        int i = 0;
        while (i < N) {
            int a = i == 0 ? 0 : i - 1;
            int b = i + nsize;

            if (prefix_sum[b] - prefix_sum[a] <= 0)
                return false;
            i += nsize;
        }
        return true;
    }

    public int[] findPossibeBlocks(int n, int max_value) {
        int[] array = new int[n];
        int root = (int) Math.sqrt(n);
        int i = 0;
        for (int divisor = 1; divisor <= root && divisor <= max_value; divisor++) {
            if (n % divisor == 0) {
                array[i++] = divisor;
            }
        }
        return Arrays.copyOf(array, i);
    }

    private int binarySol(final int N, int[] prefix_sum, int[] block_sizes,
            int begin, int end) {
        if (end - begin < 2) {
            if (checkSol(prefix_sum, block_sizes[end], N))
                return block_sizes[end];
            return block_sizes[begin];
        }

        int mid = (begin + end) / 2;
        int nblock = block_sizes[mid];
        boolean ok = checkSol(prefix_sum, nblock, N);

        if (ok) {
            return binarySol(N, prefix_sum, block_sizes, mid, end);
        } else {
            return binarySol(N, prefix_sum, block_sizes, begin, mid);
        }
    }

    public int solution(int[] A) {
        int[] prefix_sum = new int[A.length+1];
        int peak_count = 0;
        for (int p = 1; p < A.length - 1; p++) {
            if (A[p] > A[p - 1] && A[p] > A[p + 1]) {
                peak_count++;
            }
            prefix_sum[p] = peak_count;
        }
        prefix_sum[A.length-1] = peak_count;
        prefix_sum[A.length] = peak_count;
        int[] block_sizes = findPossibeBlocks(A.length, peak_count);
        return binarySol(A.length, prefix_sum, block_sizes, 1, peak_count);
    }

    @Test
    public void testCase1() throws Exception {
        int[] arr = { 1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
        int result = solution(arr);

        assertEquals(3, result);
    }

    // @Test
    // public void testCase2() throws Exception {
    // int[] arr = { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0,
    // 1, 1, 0, 0, 1, 0, 1, 0 };
    // int result = solution(arr);
    //
    // assertEquals(result, 3);
    // }
}
