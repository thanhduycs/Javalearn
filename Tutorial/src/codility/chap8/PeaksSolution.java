package codility.chap8;

//https://codility.com/demo/results/demoM98FYD-8FR/
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class PeaksSolution {

    public void printArr(int[] A, String caption) {
        if (A.length > 100)
            return;
        // System.out.printf(caption + " {%d,", A[0]);
        for (int i = 1; i < A.length - 1; i++) {
            // System.out.printf("%2d, ", A[i]);
        }
        // System.out.printf("%2d}\n", A[A.length - 1]);
    }

    public int[] findDivisor(int n, int max_size) {
        int[] array = new int[n];
        int root = (int) Math.sqrt(n);
        int i = 0;
        for (int divisor = 2; divisor <= root; divisor++) {
            if (n % divisor == 0) {
                int b = n / divisor;
                if (divisor >= n / max_size)
                    array[i++] = divisor;
                if (b >= n / max_size)
                    array[i++] = b;
            }
        }
        return Arrays.copyOf(array, i);
    }

    public int[] findPeaks(int[] A) {
        int[] array = new int[A.length / 2];
        int i = 0;
        for (int p = 1; p < A.length - 1; p++) {
            if (A[p] > A[p - 1] && A[p] > A[p + 1]) {
                array[i++] = p;
            }
        }
        return Arrays.copyOf(array, i);
    }

    public int solution(int[] A) {
        // System.out.println("LENGTH = " + A.length);
        int[] peaks = findPeaks(A);

        if (peaks.length == 0) {
            return 0;
        }

        int[] divisors = findDivisor(A.length, peaks.length);

        Arrays.sort(divisors);

        boolean[] markers = new boolean[A.length];
        for (Integer d : peaks) {
            markers[d] = true;
        }

        for (int i = 0; i < markers.length; i++) {
            // System.out.printf(" %d ", i%10);
        }
        // System.out.println();

        int[] prefix_sum = new int[A.length];
        int count = 0;
        for (int i = 0; i < markers.length; i++) {
            boolean b = markers[i];
            if (b)
                count++;
            prefix_sum[i] = count;
            // System.out.printf("[%c]", b ? '*' : ' ');
        }
        // System.out.println();

        if (divisors.length == 0) {
            return 1;
        }

        printArr(divisors, "DIVISORS: ");
        printArr(peaks, "PEAKS:    ");

        int result = 1;

        for (int i = 0; i < divisors.length; i++) {
            int size = divisors[i]; //
            int x = 0;
            boolean flag = true;
            while (x < prefix_sum.length) {
                int a = x;
                int b = x + size - 1;
                int num = x == 0 ? prefix_sum[b] : prefix_sum[b]
                        - prefix_sum[a - 1];
                // System.out.printf("(%d, %d) = %d\n", a, b, num);
                x += size;
                if (num == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                result = A.length / size;
                break;
            }
        }
        return result;
    }

    @Test
    public void testCase1() throws Exception {
        int[] arr = { 1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
        int result = solution(arr);

        assertEquals(3, result);
    }

    @Test
    public void testCase2() throws Exception {
        int[] arr = { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0,
                1, 1, 0, 0, 1, 0, 1, 0 };
        int result = solution(arr);

        assertEquals(result, 3);
    }
}
