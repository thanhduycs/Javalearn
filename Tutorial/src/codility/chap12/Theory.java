package codility.chap12;

public class Theory {
    // Binary search in O(log n).
    public int boards(int[] A, int k) {
        int n = A.length;
        int beg = 1;
        int end = n;
        int result = -1;
        while (beg < end) {
            int mid = (beg + end) / 2;
            if (check(A, mid) <= k) {
                end = mid - 1;
                result = mid;
            } else {
                beg = mid + 1;
            }
        }
        return result;
    }

    // 12.3: Greedily check in O(n).
    private int check(int[] A, int mid) {
        int boards = 0;
        int last = -1;
        //    i + mid - 1 < i
        //<=> mid - 1 < 0
        // We add a new board only if
        // there is a hole that is not covered by the last board
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1 && last < i) {
                boards++;
                last = i + mid - 1;
            }
        }
        return boards;
    }
    
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Theory theory = new Theory();
        //theory.boards(A, k);
    }
}
