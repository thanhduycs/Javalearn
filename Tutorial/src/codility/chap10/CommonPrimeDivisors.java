//https://codility.com/demo/results/demo2KRYWC-EST/

package codility.chap10;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommonPrimeDivisors {
    // Binary Euclidean algorithm
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

    public int solution(int[] A, int[] B) {
        // X = (X + M) modulo N
        int result = 0;

        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            int b = B[i];
            int gcd = gcdByBinary(a, b, 1);
            if (gcd > 1) {
                a = a / gcd;
                b = b / gcd;
            }

            int g1 = 1;
            int g2 = 1;

            do {
                g1 = gcdByBinary(a, gcd, 1);
                a = a / g1;
            } while (g1 > 1);

            do {
                g2 = gcdByBinary(b, gcd, 1);
                b = b / g2;
            } while (g2 > 1);

            if (a == b) {
                result++;
            }
            // System.out.printf("%d, %d => %d, %d, %d\n", a, b, gcd, g1, g2);
        }

        return result;
    }

    @Test
    public void testCase1() throws Exception {
        int A[] = { 15 /* , 15, 10, 9 */};
        int B[] = { 75 /** 5, 75, 300, 5 */
        };
        solution(A, B);
    }
}
