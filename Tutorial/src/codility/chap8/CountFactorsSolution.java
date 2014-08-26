package codility.chap8;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountFactorsSolution {
    public int solution(int N) {
        int result = 0;
        int root = (int) Math.sqrt(N);
        for (int i = 1; i <= root; i++) {
            if (N % i == 0) {
                result += 2;
            }
        }
        if (root * root == N)
            result--;
        return result;
    }

    @Test
    public void testCase1() throws Exception {
        assertEquals(5, solution(16));
    }
}
