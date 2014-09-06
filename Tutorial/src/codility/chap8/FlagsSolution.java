package codility.chap8;


public class FlagsSolution {
    public void printArr(int[] A, String caption) {
        if (A.length > 100)
            return;
        System.out.printf(caption + " {%d,", A[0]);
        for (int i = 1; i < A.length - 1; i++) {
            System.out.printf("%2d, ", A[i]);
        }
        System.out.printf("%2d}\n", A[A.length - 1]);
    }

    public int[] findPeaks(int[] A) {
        int[] array = new int[A.length / 2];
        int i = 0;
        for (int p = 1; p < A.length - 1; p++) {
            if (A[p] > A[p - 1] && A[p] > A[p + 1]) {
                array[i++] = p;
            }
        }
        return java.util.Arrays.copyOf(array, i);
    }

    public boolean trySolution(int[] A, int[] peaks, final int tryMidSolution) {
        int distance = 0;
        int count = 1;
        for (int i = 1; i < peaks.length; i++) {
            distance += peaks[i] - peaks[i - 1];
            if (distance >= tryMidSolution) {
                count++;
                distance = 0;
            }
        }
        return count >= tryMidSolution;
    }

    public int binarySearch(int[] A, int[] peaks, int begin, int end) {
        if (end - begin < 2) {
            if (trySolution(A, peaks, end))
                return end;
            return begin;
        }

        int mid = (begin + end) / 2;
        if (trySolution(A, peaks, mid)) {
            return binarySearch(A, peaks, mid, end);
        } else {
            return binarySearch(A, peaks, begin, mid);
        }
    }

    public int solution(int[] A) {
        int[] peaks = findPeaks(A);
        if (peaks.length <= 1)
            return peaks.length;
        int max_possible_flag = (int)Math.min(peaks.length, Math.sqrt(A.length)) + 1;
        int max_flag = binarySearch(A, peaks, 1, max_possible_flag);
        return max_flag;
    }

    public static void main(String[] args) {
        FlagsSolution flagsSolution = new FlagsSolution();
        int[] arr = new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };

        flagsSolution.solution(arr);
    }
}
