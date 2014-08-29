//https://codility.com/demo/results/demo5TRYZW-2V4/

package codility.chap13;

public class CountTriangles {
    public int solution(int[] A) {
        java.util.Arrays.sort(A); // use quickSort

        int result = 0;
        for (int x = 0; x < A.length - 2; x++) {
            int z = x + 2;
            for (int y = x + 1; y < A.length - 1; y++) {
                while (z < A.length && A[x] + A[y] > A[z]) {
                    z++;
                }
                result += z - y - 1;
                // System.out.printf("%d, %d, %d\n", x, y, z);
            }
        }
        return result;
    }
}
