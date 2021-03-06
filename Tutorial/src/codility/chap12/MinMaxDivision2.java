package codility.chap12;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinMaxDivision2 {
    public int id = 0;
    
    public void printArr(int[] A, String caption) {
        if (A.length > 100)
            return;
        //System.out.printf("%s{", caption);
        for (int i = 0; i < A.length - 1; i++) {
            //System.out.printf("%2d, ", A[i]);
        }
        //System.out.println(A[A.length - 1] + "}");
    }

    private int solv(int K, int M, int[] A, int[] prefix_sum, int[] results,
            int i, int start_index) {
        
        if (i == K - 1) {
            int last_sum = -1;
            if (start_index == 0)
                last_sum = prefix_sum[prefix_sum.length - 1];
            else
                last_sum = prefix_sum[prefix_sum.length - 1]
                        - prefix_sum[start_index - 1];
            results[0] = Math.max(last_sum, results[0]);
            results[1] = Math.min(results[0], results[1]);
            //System.out.println(results[0] + " vs " + results[1] + " => " + this.id++);
            return results[0];
        }

        int beforeChange = results[0];
        for (int num_element = 0; num_element <= A.length - start_index; num_element++) {
            results[0] = beforeChange;
            if (num_element == 0) {
                //System.out.printf("%d:0, ", i);
                solv(K, M, A, prefix_sum, results, i + 1, start_index);
                continue;
            }

            int sum = -1;
            if (start_index == 0)
                sum = prefix_sum[num_element - 1];
            else
                sum = prefix_sum[start_index + num_element - 1]
                        - prefix_sum[start_index - 1];

            //System.out.printf("%d:%d, ", i, sum);
            if (i == 0) {
                results[0] = sum;
            } else {
                results[0] = Math.max(sum, results[0]);
            }
            solv(K, M, A, prefix_sum, results, i + 1, start_index + num_element);
        }
        //results[0] = beforeChange;
        return results[0];
    }

    public int solution(int K, int M, int[] A) {
        //System.out.printf("%d/%d/%d;$;\n", K, M, A.length);
        int[] prefix_sum = new int[A.length];
        prefix_sum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefix_sum[i] = prefix_sum[i - 1] + A[i];
        }

        printArr(A, "Array:      ");
        printArr(prefix_sum, "Prefix_Sum: ");

        int[] results = new int[2];
        results[0] = -1;
        results[1] = Integer.MAX_VALUE;
        solv(K, M, A, prefix_sum, results, 0, 0);
        return results[1];
    }

    @Test
    public void testCase1() throws Exception {
        int arr[] = { 2, 1, 5, 1, 2, 2, 2 };
        int result = solution(3, 5, arr);
        assertEquals(6, result);
    }

    @Test
    public void testCase2() throws Exception {
        int arr[] = { 3, 5 };
        int result = solution(2, 5, arr);
        assertEquals(5, result);
    }

    @Test
    public void testCase3() throws Exception {
        int arr[] = { 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1,
                1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1,
                1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0,
                1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0,
                0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 };
        int result = solution(2, 100, arr);
        assertEquals(27, result);
    }
}
