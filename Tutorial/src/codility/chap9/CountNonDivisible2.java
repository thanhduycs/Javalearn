//https://codility.com/demo/results/demoPFG6SY-5KV/

package codility.chap9;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountNonDivisible2 {

    public void printArr(int[] A, String caption) {
        if (A.length > 100)
            return;
        System.out.printf(caption + " {%d,", A[0]);
        for (int i = 1; i < A.length - 1; i++) {
            System.out.printf("%2d, ", A[i]);
        }
        System.out.printf("%2d}\n", A[A.length - 1]);
    }

    public int[] solution(int[] A) {
        int[] counters = new int[2 * A.length + 2];
        int[] results = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            counters[A[i]]++;
        }

        int[] divisors = new int[2 * A.length + 2];
        for (int i = 1; i*i > 0 && i*i < counters.length; i++) {
            int count = counters[i];

            if (i * i < counters.length && i * i >= 0) {
                divisors[i * i] += count;
            }

            int x = i + 1;
            int k = i * x;

            while (k < counters.length && k >= 0) {
                if (counters[k] > 0) {
                    divisors[k] += count;
                    divisors[k] += counters[x];
                }
                k += i;
                x++;
            }
        }

        for (int i = 0; i < A.length; i++) {
            int element = A[i];
            results[i] = A.length - divisors[element];
        }

        return results;
    }

    @Test
    public void testCase1() throws Exception {
        int[] arr = { 3, 1, 2, 3, 6 };
        int[] expect = { 2, 4, 3, 2, 0 };
        int[] results = solution(arr);
        assertArrayEquals(expect, results);
    }

    @Test
    public void testCase2() throws Exception {
        int[] arr = { 2, 4 };
        int[] expect = { 1, 0 };
        int[] results = solution(arr);
        assertArrayEquals(expect, results);
    }

    @Test
    public void testCase3() throws Exception {
        int[] arr = { 6, 7, 2, 1, 4, 7, 4, 4, 1, 8, 10, 15 };
        int[] expect = { 8, 8, 9, 10, 6, 8, 6, 6, 10, 5, 8, 9 };
        int[] results = solution(arr);
        assertArrayEquals(expect, results);
    }

    @Test
    public void testCase4() throws Exception {
        int[] arr = { 95, 132, 134, 29, 3, 75, 55, 163, 139, 121, 112, 133, 30,
                89, 33, 182, 12, 164, 15, 138, 68, 81, 169, 4, 13, 184, 102,
                19, 198, 190, 23, 85, 28, 63, 125, 33, 140, 11, 35, 164, 81,
                84, 120, 96, 77, 7, 146, 194, 196, 133, 72, 73, 139, 135, 23,
                47, 73, 103, 163, 101, 6, 165, 87, 104, 45, 86, 78, 155, 21,
                110, 36, 140, 8, 56, 69, 128, 11, 92, 42, 57, 96, 192, 97, 192,
                34, 185, 73, 38, 150, 183, 2, 54, 155, 69, 49, 2, 9, 124, 197,
                200 };
        int[] expect = { 98, 89, 97, 99, 99, 97, 97, 98, 98, 97, 92, 96, 94,
                99, 95, 95, 94, 95, 98, 91, 95, 96, 98, 97, 99, 92, 94, 99, 90,
                94, 98, 99, 95, 95, 99, 95, 92, 98, 98, 95, 96, 89, 90, 91, 96,
                99, 94, 96, 93, 96, 90, 97, 98, 95, 98, 99, 97, 99, 98, 99, 96,
                92, 97, 94, 96, 97, 94, 98, 97, 94, 92, 92, 96, 93, 95, 95, 98,
                94, 93, 97, 91, 89, 99, 89, 97, 99, 97, 96, 92, 98, 98, 94, 98,
                95, 98, 98, 98, 96, 99, 95 };
        int[] results = solution(arr);
        assertArrayEquals(expect, results);
    }
}
