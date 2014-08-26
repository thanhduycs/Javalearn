package codility.chap11;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class FrogJump {
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144,
    public int solution(int[] A) {
        final int N = A.length;
        int fibos[] = new int[Math.max(A.length, 10)];
        fibos[1] = 1;
        fibos[2] = 1;

        for (int i = 3; i < fibos.length; i++) {
            fibos[i] = fibos[i - 1] + fibos[i - 2];
            if (fibos[i] > N + 2)
                break;
        }

        java.util.Queue<Integer> qe = new java.util.LinkedList<Integer>();
        qe.add(-1); // start position

        int[] passed = new int[A.length + 2];

        while (qe.size() > 0) {
            int currPos = qe.poll();
            int currStep = 0;
            if (currPos >= 0) {
                currStep = passed[currPos];
            }

            int i = 2; // duplicate 1
            while (true) {
                int nextPos = currPos + fibos[i];
                if (nextPos > N)
                    break;

                if (fibos[i] == 0)
                    throw new RuntimeException(
                            "This case never happen, but it happen, please check!!");

                if (nextPos == N) {
                    // System.out.println("GoalGoalGoalGoal: " + (currStep +
                    // 1));
                    return currStep + 1;
                }

                if (passed[nextPos] == 0 && A[nextPos] == 1) {
                    passed[nextPos] = currStep + 1;
                    qe.add(nextPos);
                }
                i++;
            }
        }
        return -1;
    }

    @Test
    public void testCase1() throws Exception {
        int[] A = { 0, 0 };
        int result = solution(A);
    }
    
    @Test
    public void testCase2() throws Exception {
        int[] A = { 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0 };
        int result = solution(A);
        assertEquals(3, result);
    }
}
