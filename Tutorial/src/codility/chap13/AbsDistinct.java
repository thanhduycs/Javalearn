package codility.chap13;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsDistinct {
    public int solution(int[] A) {
        int distinct = 0;
        int i = 0;
        while (A[i] < 0)
            i++;

        int left = i - 1;
        int right = i;
        while (left >= 0 && right < A.length) {
            int lf_val = Math.abs(A[left]);
            int rt_val = (A[right]);
            while (right < A.length - 1 && A[right] == A[right + 1])
                right++;

            while (left > 0 && A[left] == A[left - 1])
                left--;

            if (lf_val == rt_val) {
                distinct++;
                right++;
            } else if (lf_val > rt_val) {
                distinct++;
                right++;
            } else {
                distinct++;
                left--;
            }
        }

        while (left > 0) {
            if (A[left] != A[left - 1])
                distinct++;
            left--;
        }

        while (right < A.length - 1) {
            if (A[right] != A[right + 1])
                distinct++;
            right++;
        }
        System.out.println(distinct);
        return distinct;
    }

    @Test
    public void testCase1() throws Exception {
        int[] arr = { -5, -3, -1, 0, 3, 6, 7, 8, 9 };
        solution(arr);
    }
}
