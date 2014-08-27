package codility.chap10;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChocolatesByNumbers {
    public int slu1(int N, int M) {
        int k = 0;
        int i = 0;
        do {
            k = (k + M) % N;
            i++;
        } while (k > 0);
        return i;
    }

    public int slu2(int X, int N, int M) {
        int a = X + M;
        int b = N;

        int k = a % b;
        if (k == 0)
            return 1;

        return 1 + slu2(k, N, M);
    }

    public static int gcdByBinary(int a, int b, int res) {
        // k = a % b; do until k == 0;
        if (a == b)
            return res * a;

        if (a % 2 == 0 && b % 2 == 0)
            return gcdByBinary(a / 2, b / 2, 2 * res);
        if (a % 2 == 0)
            return gcdByBinary(a / 2, b, res);
        if (b % 2 == 0)
            return gcdByBinary(a, b / 2, res);
        if (a > b)
            return gcdByBinary(a - b, b, res);
        return gcdByBinary(a, b - a, res);
    }

    public int slu3(int N, int M) {
        int result = gcdByBinary(M + 0, N, 1);
        int n = N / result;
        return n;
    }

    public int solution(int N, int M) {
        // k = (k + M) % N;
        return slu3(N, M);
    }

    @Test
    public void testCase1() throws Exception {
        int result = solution(10, 4);
        System.out.println(result);
        assertEquals(5, result);
    }

    @Test
    public void testCase2() throws Exception {

        for (int i = 0; i < 1000; i++) {
            int a = (int) (Math.random() * 1000) + 10;
            int b = (int) (Math.random() * 1000) % 500 + 10;

            int result1 = slu1(a, b);
            int result3 = slu3(a, b);
            if (result1 != result3) {
                System.out.printf("(%d, %d);", a, b);
            }
            assertEquals(result1, result3);
        }
    }
}
