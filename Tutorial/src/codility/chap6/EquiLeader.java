package codility.chap6;

//https://codility.com/demo/results/demoNZHJKA-G6C/

import static org.junit.Assert.*;

import org.junit.*;

public class EquiLeader {

    public int findLeader(int[] A) {
        int val = 0, count = 0;
        for (int i = 0; i < A.length; i++) {
            if (count == 0) {
                val = A[i];
                count++;
            } else if (val == A[i]) {
                count++;
            } else {
                count--;
            }
        }

        if (count == 0)
            return -1;

        int pos = -1;
        count = 0;
        for (int i = 0; i < A.length; i++) {
            if (val == A[i]) {
                count++;
                if (pos == -1)
                    pos = i;
            }
        }

        if (count < A.length / 2)
            return -1;
        return pos;
    }

    public void printArr(int[] A) {
        if (A.length > 100)
            return;
        System.out.print("{");
        for (int i = 0; i < A.length; i++)
            System.out.print(A[i] + ", ");
        System.out.println("}");
    }

    public int solution(int[] A) {
        int pos = findLeader(A);
        if (pos == -1)
            return 0;

        int val = A[pos];
        int countTotalLeader = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == val)
                countTotalLeader++;
        }

        int result = 0;
        int firstLeaderCount = 0;
        for (int i = 0; i < A.length; i++) {
            int elem = A[i];
            if (elem == val) {
                firstLeaderCount++;
            }

            if (firstLeaderCount > (i + 1) / 2) { /* equipLeader on first segment */
                int secondHalfAllElements = (A.length - i - 1) / 2;
                int lastLeaderCount = countTotalLeader - firstLeaderCount;
                if (lastLeaderCount > secondHalfAllElements) { /* equipLeader on last segment */
                    result++;
                }
            }

        }
        return result;
    }

    @Test
    public void testCase() {
        int[] arr = { 4, 3, 4, 4, 4, 2 };
        int result = solution(arr);

        assertEquals(result, 2);
    }

    @Test
    public void testCase2() {
        int[] arr = { 4, 4, 2, 5, 3, 4, 4, 4 };
        int result = solution(arr);
        assertEquals(result, 3);
    }

    @Test
    public void testCase3() {
        int[] arr = { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0,
                1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0,
                1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1,
                0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1 };
        int result = solution(arr);
        assertEquals(result, 79);
    }

}
