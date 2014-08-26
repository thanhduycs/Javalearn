package codility.chap11;

public class TheoryFibonacci {
    public static void fibo1(int N) {
        int a = 0;
        int b = 1;
        System.out.print(a + ", ");
        System.out.print(b + ", ");
        while (b <= N) {
            int c = a + b;
            System.out.print(c + ", ");
            a = b;
            b = c;
        }
    }

    public static int fibo2(int N) {
        if (N <= 1)
            return N;
        else
            return fibo2(N - 1) + fibo2(N - 2);
    }

    public static void checkOverLoad() {
        int a = 0;
        int b = 1;
        int i = 0;
        while (a >= 0 && b >= 0) {
            int c = a + b;
            a = b;
            b = c;
            i++;
        }
        System.out.printf("%d/%d/%d\n", i, a, b);
    }
    
    public static void checkOverLoad64() {
        long a = 0;
        long b = 1;
        int i = 0;
        while (a >= 0 && b >= 0) {
            long c = a + b;
            a = b;
            b = c;
            i++;
        }
        System.out.printf("%d/%d/%d\n", i, a, b);
    }

    public static void main(String[] args) {
        fibo1(100);
        checkOverLoad();
        checkOverLoad64();
    }
}
