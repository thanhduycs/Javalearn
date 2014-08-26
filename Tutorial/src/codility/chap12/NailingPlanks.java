//https://codility.com/demo/results/demoZ6KPW2-JF5/

package codility.chap12;

public class NailingPlanks {
    private int[] atemp;
    private int[] prefix_sum;

    public void printArr(int[] A, String caption) {
        System.out.printf("%s {", caption);
        for (int i = 0; i < A.length - 1; i++)
            System.out.printf("%d, ", A[i]);
        System.out.printf("%d}\n", A[A.length - 1]);
    }

    public boolean guess(int count, int[] A, int[] B, int[] C) {
        atemp = new int[2 * C.length + 1];
        prefix_sum = new int[2 * C.length + 1];

        for (int i = 0; i < count; i++) {
            int c = C[i];
            atemp[c]++;
        }

        int t = 0;
        for (int i = 0; i <= 2 * C.length; i++) {
            t += atemp[i];
            prefix_sum[i] = t;
        }

        // printArr(prefix_sum, "prefix_sum");

        boolean flag = true;
        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            int b = B[i];

            // System.out.printf("@(%d, %d) => (%d, %d)\n", a, b,
            // prefix_sum[b], prefix_sum[a-1]);

            if (prefix_sum[b] - prefix_sum[a - 1] == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public int solution(int[] A, int[] B, int[] C) {
        int begin = 0;
        int end = C.length;
        int result = -1;

        while (end - begin >= 2) {
            int mid = (begin + end) / 2;
            boolean ok = guess(mid, A, B, C);
            // System.out.printf("\nguess: (%d, %d, %d) = %b\n",begin,mid,end,
            // ok);
            if (ok) {
                result = mid;
                end = mid;
            } else {
                begin = mid;
            }
        }

        if (result == -1 && end - begin == 1) {
            if (guess(end, A, B, C))
                return end;
        }

        return result;
    }
}
