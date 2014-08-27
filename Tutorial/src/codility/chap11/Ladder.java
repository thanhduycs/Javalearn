package codility.chap11;

import org.junit.Test;

@SuppressWarnings("unused")
public class Ladder {

    public void printArr(int[] A, String caption) {
        System.out.printf("%s {", caption);
        for (int i = 0; i < A.length - 1; i++)
            System.out.printf("%d, ", A[i]);
        System.out.printf("%d}\n", A[A.length - 1]);
    }

    public int[] solution(int[] A, int[] B) {
        // Elements of B have value in range[1-30]
        int[] power2 = new int[31];
        power2[0] = 1;
        for (int i = 1; i < power2.length; i++) {
            power2[i] = power2[i - 1] * 2;
            // System.out.println(i + " => " + power2[i]);
        }

        long[] fibos = new long[Math.max(B.length + 1, 2)];
        fibos[0] = 1;
        fibos[1] = 1;

        for (int i = 2; i <= B.length; i++) {
            fibos[i] = fibos[i - 1] + fibos[i - 2];
            fibos[i] = fibos[i] % power2[30]; //!important
        }

        int[] results = new int[B.length];
        for (int i = 0; i < B.length; i++) {
            results[i] = (int) (fibos[A[i]] % power2[B[i]]);
        }
        return results;
    }

    @Test
    public void testCase1() throws Exception {
        int[] A = { 4, 4, 5, 5, 1 };
        int[] B = { 3, 2, 4, 3, 1 };

        int[] results = solution(A, B);
        printArr(results, "results");
    }
}