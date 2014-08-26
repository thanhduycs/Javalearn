package codility.chap8;

public class MinPerimeterRectangleSolution {
    public int solution(int N) {
        int root = (int) Math.sqrt(N);
        int min_perimeter = 2 * (N + 1); // one possible perimeter

        for (int a = 1; a <= root; a++) {
            int b = N / a;
            if (a * b == N) {
                int perimeter = 2 * (a + b);
                min_perimeter = Math.min(min_perimeter, perimeter);
            }
        }
        return min_perimeter;
    }
}
