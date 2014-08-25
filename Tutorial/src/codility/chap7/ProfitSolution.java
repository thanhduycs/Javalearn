package codility.chap7;
//https://codility.com/demo/results/demoB32WTS-G7A/
import static org.junit.Assert.*;

import org.junit.Test;

public class ProfitSolution {
    public int solution(int[] A) {
        int max_profit = 0;
        int curr_profit = 0;

        for (int i = 1; i < A.length; i++) {
            int this_profit = A[i] - A[i - 1];
            curr_profit = Math.max(curr_profit, 0) + this_profit;
            max_profit = Math.max(max_profit, curr_profit);
            // System.out.println(curr_profit);
        }
        return max_profit;
    }

    @Test
    public void testCase1() throws Exception {
        int arr[] = { 23171, 21011, 21123, 21366, 21013, 21367 };
        int result = solution(arr);
        assertEquals(result, 356);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(1);
        }
    }
}
